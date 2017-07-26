package webcat.task;

import mf.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webcat.Interaction.Menu;
import webcat.Interaction.Message;
import webcat.cache.MessageCache;
import webcat.entity.MessageEntity;
import webcat.service.PushService;
import webcat.utils.ConEnum;
import webcat.utils.MessageConstants;

/**
 * Created by dengfan on 2017/3/7.
 */
@Component("pushTask")
public class PushTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PushService pushService;

    /**
     * 推送普通消息
     */
    public void pushMessage(){

        //一次推送10条

        for(int i = 0; i < 50; i++ ){

            MessageEntity entity = MessageConstants.getMessage();

            if(entity == null){
                logger.info("结束当前消息推送");
                break;
            }
            logger.info("为用户：" + entity.getOpenId() + " 推送了一条房源消息.");
            //推送消息
        }

        logger.info("还剩余：" + MessageConstants.getMessageCount() + "条消息未推送");
    }

    /**
     * 推送夜间消息 21:00 - 8:00
     */
    public void pushNightMessage(){

        if(MessageConstants.NIGHT_HOUSE_MESSAGE.size() != 0){

            for(String openId : MessageConstants.NIGHT_HOUSE_MESSAGE.keySet()){

                String mes = "主人！昨晚休息得还好吗？  小蜗一宿没睡，为您收集了" +
                        MessageConstants.NIGHT_HOUSE_MESSAGE.get(openId) +
                        "条房源信息，请主人及时批阅！" +
                        "<a href='" + Menu.getMenuUrl(ConEnum.Menu.FYGC.getValue()) + "'>【查看详情】</a>";

                Message message = new Message();
                boolean flag = message.sendTextMessage(mes, openId);

                //发送成功扣费
                if(flag){
                    pushService.pushCallBack(openId);
                    MessageConstants.NIGHT_HOUSE_MESSAGE.remove(openId);
                }

                //为每一个用户推送消息
                logger.info("为用户：" + openId + " 推送了一条夜间消息.共有房源：" + MessageConstants.NIGHT_HOUSE_MESSAGE.get(openId));
            }
        }
    }

    /**
     * 推送勿扰消息 12:00   17:00   21:00
     */
    public void pushWrMessage(){
        if(MessageConstants.WR_HOUSE_MESSAGE.size() != 0){

            for(String openId : MessageConstants.WR_HOUSE_MESSAGE.keySet()){

                String smes = "主人，您已开启勿扰模式，这段时间小蜗为您收集了" +
                        MessageConstants.WR_HOUSE_MESSAGE.get(openId) +
                        "条房源信息，请主人及时批阅！" +
                        "<a href='" + Menu.getMenuUrl(ConEnum.Menu.FYGC.getValue()) + "'>【查看详情】</a>";

                Message message = new Message();
                boolean flag = message.sendTextMessage(smes, openId);

                //发送成功扣费
                if(flag){
                    pushService.pushCallBack(openId);
                    MessageConstants.WR_HOUSE_MESSAGE.remove(openId);
                }

                //为每一个用户推送消息
                logger.info("为用户：" + openId + " 推送了一条勿扰消息.共有房源：" + MessageConstants.WR_HOUSE_MESSAGE.get(openId));
            }
        }
    }

    /**
     * 推送勿扰消息 8:00
     */
    public void pushWrMessage8AM(){
        if(MessageConstants.WR_HOUSE_MESSAGE.size() != 0){

            for(String openId : MessageConstants.WR_HOUSE_MESSAGE.keySet()){

                String mes = "主人！昨晚休息得还好吗？  小蜗一宿没睡，为您收集了" +
                        MessageConstants.WR_HOUSE_MESSAGE.get(openId) +
                        "条房源信息，请主人及时批阅！" +
                        "<a href='" + Menu.getMenuUrl(ConEnum.Menu.FYGC.getValue()) + "'>【查看详情】</a>";

                Message message = new Message();
                boolean flag = message.sendTextMessage(mes, openId);

                //发送成功扣费
                if(flag){
                    pushService.pushCallBack(openId);
                    MessageConstants.WR_HOUSE_MESSAGE.remove(openId);
                }

                //为每一个用户推送消息
                logger.info("为用户：" + openId + " 推送了一条勿扰消息.共有房源：" + MessageConstants.WR_HOUSE_MESSAGE.get(openId));
            }
        }
    }

    public void push48hous(){

        String openId = MessageConstants.getHous48();

        String mes = "主人，您已经有几天没关注小蜗发来的消息了，是对我的表现不满意吗？~~>_<~~ \n" +
                "1.表现还行，再接再厉"+
                "2.表现不好，出去罚站";

        Message message = new Message();
        message.sendTextMessage(mes, openId);

        MessageCache.cache.put(openId, "1");

        logger.info("推送48小时未互动消息");
    }

}
