package webcat.controller;

import com.alibaba.fastjson.JSONObject;
import mf.entity.MfRechargeEntity;
import mf.entity.MfRechargeOrderEntity;
import mf.entity.MfUserEntity;
import mf.service.MfRechargeOrderService;
import mf.service.MfRechargeService;
import mf.service.MfUserService;
import mf.utils.DateUtils;
import mf.utils.WXUtil;
import mf.utils.XMLUtil;
import org.apache.commons.lang.StringUtils;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webcat.Interaction.JsapiTicket;
import webcat.entity.ParamData;
import webcat.utils.Constants;
import webcat.utils.pay.PayCommonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Random;

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

        String id = pd.getString("id");

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
        String body = "蜗牛秒房充值：" + rechargeEntity.getTitle();

        try {
            Map<String, String> map = PayCommonUtil.getPrepayId(payFee, ipAddress, orderNumber, body, getOpenId());
            String  prepay_id = map.get("prepay_id");

            //保存充值信息-状态为待支付
            MfRechargeOrderEntity order = new MfRechargeOrderEntity();
            order.setOpenId(getOpenId());
            order.setStatus(0);
            order.setCreateTime(DateUtils.getTodayDate());
            order.setDes(body);
            order.setOrderNo(orderNumber);
            order.setTitle(rechargeEntity.getTitle());
            order.setId(orderNumber);
            order.setRechargeId(rechargeEntity.getId());

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
    @RequestMapping("/callback")
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

                        //更改订单状态
                        MfRechargeOrderEntity order = new MfRechargeOrderEntity();
                        order.setId(orderNumber);
                        order.setStatus(1);
                        mfRechargeOrderService.update(order);
                        //更改用户状态

                        MfRechargeEntity rechargeEntity = mfRechargeService.queryObject(order.getRechargeId());
                        MfUserEntity user = mfUserService.queryObject(order.getOpenId());

                        if(rechargeEntity.getType() == 1){
                            if(DateUtils.daysBetween(user.getExpireDate(), DateUtils.getTodayDate()) < 0){
                                user.setExpireDate(DateUtils.getFetureDate(user.getExpireDate(), Integer.valueOf(rechargeEntity.getDays())));
                            }
                            else{
                                user.setExpireDate(DateUtils.getFetureDate(Integer.valueOf(rechargeEntity.getDays())));
                            }
                        }
                        else if(rechargeEntity.getType() == 2){
                            user.setWnb(user.getWnb() + rechargeEntity.getWnb());
                        }
                        mfUserService.update(user);

                        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                    }
                }
            }
        } catch (Exception e) {
            logger.error("支付回调失败", e);
        }

        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }

}
