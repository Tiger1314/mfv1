package webcat.task;

import mf.entity.MfPushEntity;
import mf.entity.MfUserEntity;
import mf.service.*;
import mf.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webcat.Interaction.Menu;
import webcat.Interaction.Message;
import webcat.Interaction.Template;
import webcat.cache.MessageCache;
import webcat.entity.MessageEntity;
import webcat.service.PushService;
import webcat.utils.ConEnum;
import webcat.utils.MessageConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengfan on 2017/3/7.
 */
@Component("pushTask")
public class PushTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfUserService mfUserService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private MfPushLogService mfPushLogService;
    @Autowired
    private MfPushService mfPushService;

    /**
     * 推送普通消息
     */
    public void pushMessage(){

        //一次推送10条

        int h = DateUtils.getHour();

        if(h >= 8 && h <= 21 ){
            int count = Integer.valueOf(sysConfigService.getValue("PUSH_COUNT_ONE_TIME", "100"));

            for(int i = 0; i < count; i++ ){

                MessageEntity entity = MessageConstants.getMessage();

                if(entity == null){
                    logger.info("结束当前消息推送");
                    break;
                }

                MfUserEntity user = mfUserService.queryObject(entity.getOpenId());

                //包月用户不发送及时消息
                if(user.getWnb() > 0 && user.getWrStatus() != 1){
                    Template t = new Template();

                    //先发送客服消息
                    String return_code = t.houseInfoKF(entity);

                    if(!return_code.equals("0")){

                        return_code = t.houseInfoNew(entity);

                    }

                    if(return_code.equals("0")){
                        mfUserService.deductions(entity.getOpenId(), entity.getTitle(), "蜗牛壳", "-1", 1, entity.getHouseId());

                        logger.info("为用户：" + entity.getOpenId() + " 推送了一条房源消息.");
                    }
                }
            }
        }
        else{
            logger.info("不在及时小时推送时段");
        }

        logger.info("还剩余：" + MessageConstants.getMessageCount() + "条消息未推送");
    }

    /**
     * 推送夜间消息 21:00 - 8:00
     */
    public void pushNightMessage(){
        int h = DateUtils.getHour();
        int m = DateUtils.getMinute();
        if(h == 8 && m <= 10){

            Map<String, Object> map = new HashMap<String, Object>();

            List<MfUserEntity> list = mfUserService.queryList(map);

            for(MfUserEntity user : list){

                try{
                    MfPushEntity pushEntity = mfPushService.queryObject(user.getOpenId());

                    if(((user.getExpireDate() != null && user.getExpireDate().getTime() > DateUtils.getTodayDate().getTime()) || user.getWnb() > 0) && pushEntity != null && pushEntity.getStatus() == 1){

                        int count = 0;

                        if(MessageConstants.NIGHT_HOUSE_MESSAGE.get(user.getOpenId()) != null){
                            count = MessageConstants.NIGHT_HOUSE_MESSAGE.get(user.getOpenId());
                        }

                        Template t = new Template();

                        String rm = t.sendYjMessage(user.getOpenId(), count);

                        if("0".equals(rm)){
                            mfUserService.deductions(user.getOpenId(), "夜间消息", "蜗牛壳", "-1", 0, 0);

                            MessageConstants.NIGHT_HOUSE_MESSAGE.put(user.getOpenId(), 0);
                        }

                        //为每一个用户推送消息
                        logger.info("为用户：" + user.getOpenId() + " 推送了一条夜间消息.共有房源：" + count );
                    mfPushLogService.savePushLog(user.getOpenId(), count, 11);

                    }
                    else {
                        logger.info("用户：" + user.getOpenId() + " 不是会员或者没有蜗牛币，不推送.");
                    }
                }
                catch (Exception e){
                    logger.error("为用户：" + user.getOpenId() + "推送夜间消息失败.");
                }
            }
        }
        else{
            logger.info("不在夜间消息推送时段.");
        }

    }

    /**
     * 夜间消息开始推送通知
     */
    public void pushNightStartMessage(){
        int h = DateUtils.getHour();
        int m = DateUtils.getMinute();
        if(h == 8 && m <= 10){
            Map<String, Object> map = new HashMap<String, Object>();
            List<MfPushEntity> list = mfPushService.queryList(map);

            Template t;

            for(MfPushEntity entity : list){

                t = new Template();
                String return_code = t.nightKf(entity.getOpenId());

                if(return_code.equals("0")){
                    logger.info("用户：" + entity.getOpenId() + "发送夜间开始消息通知成功。");
                }
                else{
                    logger.info("用户：" + entity.getOpenId() + "发送夜间开始消息通知失败。");
                }
            }
        }
        else{
            logger.info("不在夜间消息开始推送通知时段.");
        }
    }

    /**
     * 推送勿扰消息 12:00   16:00   20:00
     */
    public void pushWrMessage(){

        int h = DateUtils.getHour();
        int m = DateUtils.getMinute();

        if((h == 12 || h == 16 || h == 20) && m <= 5){
            Map<String, Object> map = new HashMap<String, Object>();

            List<MfUserEntity> list = mfUserService.queryList(map);

            for(MfUserEntity user : list){

                try{
                    MfPushEntity pushEntity = mfPushService.queryObject(user.getOpenId());

                    if(((user.getExpireDate() != null && user.getExpireDate().getTime() > DateUtils.getTodayDate().getTime()) || user.getWnb() > 0)
                            && pushEntity != null && pushEntity.getStatus() == 1 && user.getWrStatus() == 1){

                        int count = 0;

                        if(MessageConstants.WR_HOUSE_MESSAGE.get(user.getOpenId()) != null){
                            count = MessageConstants.WR_HOUSE_MESSAGE.get(user.getOpenId());
                        }

                        Template t = new Template();

                        String rm = t.sendWrMessage(user.getOpenId(), count);

                        //发送成功扣费
                        if("0".equals(rm)){
                            MessageConstants.WR_HOUSE_MESSAGE.put(user.getOpenId(), 0);

                            mfUserService.deductions(user.getOpenId(), "勿扰模式", "蜗牛壳", "-1", 0, 0);
                        }

                        //为每一个用户推送消息
                        logger.info("为用户：" + user.getOpenId() + " 推送了一条勿扰消息.共有房源：" + count);
                        mfPushLogService.savePushLog(user.getOpenId(), count, 12);
                    }
                    else {
                        logger.info("用户：" + user.getOpenId() + " 不是会员或者没有蜗牛币，不推送.");
                    }
                }
                catch(Exception e){
                    logger.error("为用户：" + user.getOpenId() + "推送勿扰消息失败.");
                }
            }
        }
        else{
            logger.info("不在勿扰消息推送时间.");
        }
    }

    /**
     * 包月整点
     */
    public void pushZdInfo(){
        int h = DateUtils.getHour();
        int m = DateUtils.getMinute();

        Map<String, Object> map = new HashMap<String, Object>();

        List<MfUserEntity> list = mfUserService.queryList(map);

        if(h >= 8 && h <= 21 && list.size() > 0 && m <= 2){
            //正点消息只针对包月用户，所以不扣费
            for(MfUserEntity entity : list) {

                MfPushEntity pushEntity = mfPushService.queryObject(entity.getOpenId());

                if (entity.getWrStatus() != 1 && entity.getExpireDate() != null
                        && entity.getExpireDate().getTime() >= DateUtils.getTodayDate().getTime()
                        && pushEntity != null && pushEntity.getStatus() == 1) {

                    int count = 0;

                    if(MessageConstants.ZD_HOUSE_MESSAGE.get(entity.getOpenId()) != null){
                        count = MessageConstants.ZD_HOUSE_MESSAGE.get(entity.getOpenId());
                    }

                    Template t = new Template();

                    String rm = t.sendZdMessage(entity.getOpenId(), count);

                    logger.info("为用户：" + entity.getOpenId() + " 推送了一条整点消息.共有房源：" + count);

                    mfPushLogService.savePushLog(entity.getOpenId(), MessageConstants.ZD_HOUSE_MESSAGE.get(entity.getOpenId()), 13);

                    MessageConstants.ZD_HOUSE_MESSAGE.put(entity.getOpenId(), 0);
                }
            }
        }
    }

    /**
     * 48小时未互动
     */
    public void push48hous(){

        String openId = MessageConstants.getHous48();

        if(StringUtils.isNotBlank(openId)){
            String mes = "主人，你已经好多天没理小蜗了~！每天好多好房源都等着主人呢。" +
                    "<a href='" + Menu.getMenuUrl(ConEnum.Menu.DYSZ.getValue()) + "'>>>>订阅设置</a>\n"+
                    "<a href='" + Menu.getMenuUrl(ConEnum.Menu.JRHY.getValue()) + "'>>>>立即充值</a>";

            Message message = new Message();
            message.sendTextMessage(mes, openId);

            MessageCache.cache.put(openId, "1");

            logger.info("推送48小时未互动消息");
        }
    }

}
