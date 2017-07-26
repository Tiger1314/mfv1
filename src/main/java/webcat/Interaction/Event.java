package webcat.Interaction;

import mf.entity.MfUserEntity;
import mf.service.MfUserService;
import mf.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webcat.cache.MessageCache;
import webcat.utils.*;

import java.util.concurrent.Callable;

/**
 * 事件处理类
 * Created by dengfan on 2017/3/3.
 */
public class Event {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String eventController(String xml)throws Exception{

        String message = "success";

        ReceiveXmlProcess xmlreceive = new ReceiveXmlProcess();
        FormatXmlProcess xmlFormat = new FormatXmlProcess();

        ReceiveXmlEntity entity = xmlreceive.getMsgEntity(xml);

        MfUserService mfUserService = SpringContextUtils.getBean("mfUserService", MfUserService.class);

        if(entity != null){
            //event事件
            if("event".equals(entity.getMsgType())){

                //关注
                if(entity.getEvent().equals("subscribe")){

                    logger.info("subscribe");

                    //判断用户是否存在
                    MfUserEntity user = mfUserService.queryObject(entity.getFromUserName());
                    if(user != null){
                        //直接重新关注-用户改成正常状态
                        mfUserService.subscribe(user.getOpenId());
                    }
                    else{
                        user = new MfUserEntity();
                        user.setOpenId(entity.getFromUserName());
                        user.setStatus(Constant.UserStatus.N.getValue());//新用户
                        user.setSystem(Integer.valueOf(PropertyUtil.getProperty("system")));
                        user.setLastTime(DateUtils.getTodayDate());

                        user.setWnb(0);

                        mfUserService.save(user);
                    }

                    //关注成功，回复消息
                    StringBuffer mes = new StringBuffer();
                    mes.append("主人！终于盼到您了，小蜗将秒速提供全网最新个人房源，点击下方【订阅设置】并完成设置后即可开始秒房之旅，还有神秘礼包哟！").append("");
                    mes.append("[<a href='" + Menu.getMenuUrl(ConEnum.Menu.DYSZ.getValue()) + "'>订阅设置</a>]");

                    message = xmlFormat.formatXmlAnswer(entity.getFromUserName(), Constants.weixin, mes.toString());
                }
                //取消关注
                else if(entity.getEvent().equals("unsubscribe")){
                    logger.info("unsubscribe");

                    mfUserService.unsubscribe(entity.getFromUserName());
                    //删除订阅设置
                }
                else if(entity.getEvent().equals("CLICK")){
                    //客服MM
                    if(entity.getEventKey().equals("kfmm")){
                        //发送一段文字，
                        Message m = new Message();
                        m.sendTextMessage("亲，如果您有好的建议或问题可以直接加我们的客服MM详聊哦！您的伴随是我们进步的源动力。", entity.getFromUserName());
                        //发送图文信息
                        message = xmlFormat.formatXmlAnswerPng(entity.getFromUserName(),Constants.weixin);
                    }
                    //意见反馈
                    else if(entity.getEventKey().equals("yjfk")){
                        //发送一段文字，
                        message = xmlFormat.formatXmlAnswer(entity.getFromUserName(), Constants.weixin, "您的伴随是我们进步的动力，如有建议或意见可直接在本公众号内提交（底部左边按钮切换成键盘模式后输入发送）。");
                    }
                    //勿扰模式
                    else if(entity.getEventKey().equals("wrset")){
                        String openId = entity.getFromUserName();

                        //获取用户是否设置了勿扰模式

                        MfUserEntity user = mfUserService.queryObject(openId);

                        int wrSet = user.getWrStatus();

                        if(wrSet == 0){
                            user.setWrStatus(1);
                            mfUserService.update(user);
                            //发送消息
                            message = xmlFormat.formatXmlAnswer(entity.getFromUserName(), Constants.weixin, "主人！您已开启【勿扰模式】啦~小蜗每天仅推送三次房源信息提醒，请主人及时批阅！");
                        }
                        else{
                            user.setWrStatus(0);
                            mfUserService.update(user);
                            //发送消息
                            message = xmlFormat.formatXmlAnswer(entity.getFromUserName(), Constants.weixin, "您已关闭【勿扰模式】，小蜗会在第一时间把每条房源信息发送给主人！");
                        }
                    }

                }
            }
            //直接回复客服信息
            else{
                String type = "";
                //判断你是否是某些消息的回复
                try{
                    type = MessageCache.cache.get(entity.getFromUserName(), new Callable<String>(){
                        public String call(){
                            return "";
                        }
                    });
                }
                catch (Exception e){
                    type = "";
                }

                if(StringUtils.isNotBlank(type) && "1".equals(type)){
                    if("1".equals(entity.getContent())){
                        message = xmlFormat.formatXmlAnswer(entity.getFromUserName(), Constants.weixin, "谢谢主人的夸奖，小蜗可开心了！");
                    }
                    else{
                        message = xmlFormat.formatXmlAnswer(entity.getFromUserName(), Constants.weixin, "55555，小蜗会加倍努力工作的！");
                    }
                    MessageCache.cache.invalidate(entity.getFromUserName());
                }
                else{
                    //发送一段文字，
                    message = xmlFormat.formatXmlAnswer(entity.getFromUserName(), Constants.weixin, "消息已记录，感谢您对闪电房的支持！");
                }
            }

            MfUserEntity u = mfUserService.queryObject(entity.getFromUserName());
            logger.info("#####################FromUserName"+entity.getFromUserName()+"#############################");
            if(u != null){
                u.setLastTime(DateUtils.getTodayDate());
                u.setAttr1(null);
                mfUserService.update(u);
            }
        }

        return message;
    }

}
