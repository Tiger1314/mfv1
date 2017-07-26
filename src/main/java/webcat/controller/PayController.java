package webcat.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import com.alibaba.fastjson.JSONObject;
import mf.entity.MfPushEntity;
import mf.entity.MfRechargeEntity;
import mf.entity.MfRechargeOrderEntity;
import mf.entity.MfUserEntity;
import mf.service.MfPushService;
import mf.service.MfRechargeOrderService;
import mf.service.MfRechargeService;
import mf.service.MfUserService;
import mf.utils.Constant;
import mf.utils.DateUtils;
import mf.utils.WXUtil;
import mf.utils.XMLUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webcat.Interaction.JsapiTicket;
import webcat.Interaction.Template;
import webcat.entity.ParamData;
import webcat.utils.Constants;
import webcat.utils.pay.PayCommonUtil;

/**
 * 支付接口
 * Created by dengfan on 2017/3/5.
 */
@Controller
@RequestMapping(value = "/pay")
public class PayController extends AbstractController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfRechargeService mfRechargeService;
    @Autowired
    private MfRechargeOrderService mfRechargeOrderService;
    @Autowired
    private MfUserService mfUserService;
    @Autowired
    private MfPushService mfPushService;

    /**
     * 获取支付环境
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getwxconfig", produces = "application/json")
    public Object getWxConfig(){

        ParamData pd = this.getParamData();

        String url = pd.getString("url");

        JsapiTicket jt = new JsapiTicket();

        JSONObject object = new JSONObject();
        object.put("appId", Constants.appID);
        object.put("timeStamp", WXUtil.getTimeStamp());
        object.put("nonceStr", WXUtil.getNonceStr());
        object.put("signature", jt.getSignature(String.valueOf(object.get("nonceStr")), String.valueOf(object.get("timeStamp")), url));

        return object;

    }

    /**
     * 发起支付
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendpay", produces = "application/json")
    public Object sendPay(){

        ParamData pd = this.getParamData();

        String id = pd.getString("push_id");

        if(StringUtils.isBlank(id) || StringUtils.isBlank(getOpenId())){
            return sendFailure("充值ID错误.");
        }

        MfRechargeEntity rechargeEntity = mfRechargeService.queryObject(Integer.valueOf(id));

        if(rechargeEntity == null){
            return sendFailure("充值ID错误.");
        }
        //发起充值
        String ipAddress = request.getRemoteAddr();
        //订单号
        String orderNumber = System.currentTimeMillis()+new Random().nextInt(100) + "";
        //支付金额（分）
        String payFee = (int)Math.floor(Double.parseDouble(String.valueOf(rechargeEntity.getPrice())) * 100) + "";
        //消息体
        String body = Constant.WEBCATNAME;

        try {

            String open_id = getOpenId();

            Map<String, String> map = PayCommonUtil.getPrepayId(payFee, ipAddress, orderNumber, body, open_id);
            String  prepay_id = map.get("prepay_id");

            MfUserEntity user = mfUserService.queryObject(open_id);
            //保存充值信息-状态为待支付
            MfRechargeOrderEntity order = new MfRechargeOrderEntity();
            order.setOpenId(getOpenId());
            order.setStatus(0);
            order.setNickname(user.getNickname());
            order.setCreateTime(DateUtils.getTodayDate());
            order.setDes(body);
            order.setOrderNo(orderNumber);
            order.setTitle(rechargeEntity.getTitle() + "-价格：" + rechargeEntity.getPrice());
            order.setId(orderNumber);
            order.setRechargeId(rechargeEntity.getId());
            order.setOrderType(2);

            if(rechargeEntity.getType() == 1){
                order.setOperType("包月服务");
            }
            else{
                order.setOperType("蜗牛币充值");
            }
            mfRechargeOrderService.save(order);

            return PayCommonUtil.createPackageValue(prepay_id);
        } catch (Exception e) {
           logger.error("获取预支付ID失败.", e);
        }
        return sendFailure("发起支付失败.");
    }

    /**
     * 支付成功之后微信回调
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/callback", produces = "application/xml")
    public String callback(){

        try {
            /** 读取接收到的xml消息 */
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            String xml = sb.toString();	//次即为接收到微信端发送过来的xml数据

            logger.info("充值成功回调：" + xml);

            Map<String, String> xmlMap = XMLUtil.doXMLParse(xml);
            //获取订单ID
            String orderNumber = xmlMap.get("out_trade_no");
            String open_id = xmlMap.get("openid");

            // 调用微信查询订单接口，
            Map<String, String> orderMap = PayCommonUtil.queryWeiXinOrder(orderNumber);
            if (orderMap.get("return_code") != null&& orderMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
                if (orderMap.get("result_code") != null&& orderMap.get("result_code").equalsIgnoreCase("SUCCESS")) {
                    if (orderMap.get("trade_state") != null&& orderMap.get("trade_state").equalsIgnoreCase("SUCCESS")) {

                        //支付成功，调用方法
                        Template t = new Template();
                        //更改订单状态
                        MfRechargeOrderEntity order = mfRechargeOrderService.queryObjectByOrderNo(orderNumber);

                        if(order != null && order.getStatus() == 0){

                            MfUserEntity user = mfUserService.queryObject(open_id);

                            order.setStatus(1);
                            order.setNickname(user.getNickname());
                            order.setOpenId(user.getOpenId());

                            mfRechargeOrderService.update(order);
                            //更改用户状态

                            MfRechargeEntity rechargeEntity = mfRechargeService.queryObject(order.getRechargeId());

                            Date beginTime = null;

                            if(rechargeEntity.getType() == 1){
                                if(user.getExpireDate() != null && user.getExpireDate().getTime() > DateUtils.getTodayDate().getTime()){
                                    beginTime = user.getExpireDate();
                                    user.setExpireDate(DateUtils.getFetureDate(user.getExpireDate(), Integer.valueOf(rechargeEntity.getDays())));
                                }
                                else{
                                    beginTime = DateUtils.getTodayDate();
                                    user.setExpireDate(DateUtils.getFetureDate(Integer.valueOf(rechargeEntity.getDays())));
                                }

//                                mfRechargeOrderService.addOrder(order.getOpenId(), order.getTitle(), "包月服务", rechargeEntity.getDays() + "天");

                                //发送提醒消息
                                t.sendCztxBy(order.getOpenId(), rechargeEntity.getPrice(), Integer.valueOf(rechargeEntity.getDays()), beginTime, DateUtils.getFetureDate(beginTime, Integer.valueOf(rechargeEntity.getDays())));

                                mfUserService.update(user);
                            }
                            else if(rechargeEntity.getType() == 2){
                                user.setWnb(user.getWnb() + rechargeEntity.getWnb());

//                                mfRechargeOrderService.addOrder(order.getOpenId(), order.getTitle(), "蜗牛壳", "+" + rechargeEntity.getWnb());

                                //发送消息提醒
                                t.sendCztxWnb(order.getOpenId(), rechargeEntity.getPrice(), user.getWnb(), DateUtils.getTodayDate());

                                //更新蜗牛币状态
                                mfUserService.update(user);
                            }
                            //为用户开启推送配置
                            MfPushEntity pushEntity = mfPushService.queryObject(user.getOpenId());
                            if(pushEntity != null){
                                pushEntity.setStatus(1);
                                mfPushService.update(pushEntity);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("支付回调失败", e);
        }

        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

}
