package webcat.service.impl;

import mf.entity.MfPushEntity;
import mf.entity.MfUserEntity;
import mf.service.MfPushLogService;
import mf.service.MfPushService;
import mf.service.MfRechargeOrderService;
import mf.service.MfUserService;
import mf.utils.Base64Utils;
import mf.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcat.Interaction.Message;
import webcat.Interaction.Template;
import webcat.cache.PushCache;
import webcat.service.PushService;
import webcat.utils.MessageConstants;

/**
 * Created by dengfan on 2017/3/7.
 */
@Service("pushService")
public class PushServiceImpl implements PushService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfUserService mfUserService;
    @Autowired
    private MfRechargeOrderService mfRechargeOrderService;
    @Autowired
    private MfPushService mfPushService;

    public int checkNewUser(String openId){
        //检查是否是新用户,是新用户，推送两天
        MfUserEntity user = mfUserService.queryObject(openId);

        if(DateUtils.daysBetween(DateUtils.getTodayDate(), user.getExpireDate()) >= 1){
            return 1;
        }
        else if(DateUtils.daysBetween(DateUtils.getTodayDate(), user.getExpireDate()) > 1 &&
                DateUtils.daysBetween(DateUtils.getTodayDate(), user.getExpireDate()) <= 2){
            return 2;
        }
        else if(DateUtils.daysBetween(DateUtils.getTodayDate(), user.getExpireDate()) > 2 &&
                DateUtils.daysBetween(DateUtils.getTodayDate(), user.getExpireDate()) <= 3){
            return 2;
        }

        return 0;
    }

    /**
     * 检查用户是否可以推送
     *
     * @param openId
     * @return
     */
    @Override
    public Boolean checkUserPush(String openId) {

        //检查是否是会员
        MfUserEntity user = mfUserService.queryObject(openId);

        if(DateUtils.daysBetween(DateUtils.getTodayDate(), user.getExpireDate())< 0){
            return true;
        }

        //检查蜗牛币是否足够
        if(user.getWnb() > 0){
            return true;
        }

        return false;
    }

    /**
     * 推送成功，回调消息
     *
     * @param openId
     */
    @Override
    public void pushCallBack(String openId) {

        MfUserEntity user = mfUserService.queryObject(openId);

        //包月会员，不做任何记录
        if(DateUtils.daysBetween(DateUtils.getTodayDate(), user.getExpireDate())< 0){

        }
        else{
            Template t = new Template();
            Message m = new Message();

            //更新蜗牛币
            user.setWnb(user.getWnb() - 1);
            mfUserService.update(user);

            //50个的时候推送蜗牛币不足提醒
            if(user.getWnb() == 50){
                m.sendWnb50(user.getOpenId());
            }

            if(user.getWnb() <= 0){
                //关掉推送
                mfPushService.delete(openId);
                //暂时不刷新缓存

                //推送蜗牛币为0的消息
                try {
                    t.sendYebzWnb(new String(Base64Utils.decode(user.getNickname())), user.getOpenId());
                } catch (Exception e) {
                    logger.info("昵称解码失败.", e);
                }
            }

        }
    }
}
