package webcat.task;

import mf.entity.MfHouseInfoEntity;
import mf.entity.MfPushEntity;
import mf.entity.MfUserEntity;
import mf.service.MfHouseInfoService;
import mf.service.MfPushService;
import mf.service.MfUserService;
import mf.service.SysConfigService;
import mf.utils.Base64Utils;
import mf.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webcat.Interaction.Template;
import webcat.Interaction.User;
import webcat.cache.PushCache;
import webcat.service.PushService;
import webcat.utils.MessageConstants;
import webcat.utils.PushSetUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 初始化各种推送消息
 * Created by dengfan on 2017/3/7.
 */
@Component("createTask")
public class CreateTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfPushService mfPushService;
    @Autowired
    private MfUserService mfUserService;
    @Autowired
    private PushService pushService;
    @Autowired
    private MfHouseInfoService mfHouseInfoService;
    @Autowired
    private SysConfigService sysConfigService;
    /**
     * 生成推送的房源消息
     */
    public void createHouseTask(){

        MfHouseInfoEntity house = mfHouseInfoService.getNewHouse();

        String provinceCode = house.getProvinceCode() + "";
        String cityCode = house.getCityCode() + "";
        String districtCode = house.getDistrictCode() + "";
        String businessCode = house.getBusinessCode() + "";

        try{
            Map<String, MfPushEntity> provinceMap = PushCache.cache.get(provinceCode, new Callable<Map<String, MfPushEntity>>() {
                @Override
                public Map<String, MfPushEntity> call() throws Exception {
                    return null;
                }
            });

            Map<String, MfPushEntity> cityMap = PushCache.cache.get(cityCode, new Callable<Map<String, MfPushEntity>>() {
                @Override
                public Map<String, MfPushEntity> call() throws Exception {
                    return null;
                }
            });

            Map<String, MfPushEntity> districtMap = PushCache.cache.get(districtCode, new Callable<Map<String, MfPushEntity>>() {
                @Override
                public Map<String, MfPushEntity> call() throws Exception {
                    return null;
                }
            });

            Map<String, MfPushEntity> businessMap = PushCache.cache.get(businessCode, new Callable<Map<String, MfPushEntity>>() {
                @Override
                public Map<String, MfPushEntity> call() throws Exception {
                    return null;
                }
            });

            Map<String, MfPushEntity> pushMap = new HashMap<String, MfPushEntity>();

            pushMap.putAll(provinceMap);
            pushMap.putAll(cityMap);
            pushMap.putAll(districtMap);
            pushMap.putAll(businessMap);

            for(String openId : pushMap.keySet()){

                int newUser = pushService.checkNewUser(openId);

                //非新手用户
                if(newUser == 0){
                    //正常推送
                }
                //新手用户
                else{
                    if(newUser == 1){
                        //正常推送
                    }
                    else if(newUser == 2){
                        //推送-连接跳转到充值
                    }
                }

            }
        }
        catch (Exception e){
            logger.error("生成推送消息失败", e);
        }
    }

    /**
     * 会员到期
     */
    public void createVipEnd(){
        //获取会员日期超过3天的会员
        List<MfUserEntity> list = mfUserService.get3DayUser();
        //推送到期消息

        for(MfUserEntity user : list){

            //推送到期消息
            Template t = new Template();
            t.hydq(user.getOpenId(), DateUtils.format(user.getExpireDate(), DateUtils.DATE_TIME_PATTERN_CN));
        }

        //关闭推送
    }

    /**
     * 生成推送的缓存  24小时执行一次
     */
    public void createPuseSet(){

        //获取所有需要推送的设置

        Map<String, Object> map = new HashMap<String, Object>();

        List<MfPushEntity> list = mfPushService.queryList(map);

        for(MfPushEntity entity : list){

            logger.info("为用户：" + entity.getOpenId() + "配置推送设置.");

            PushSetUtil.setPush(entity);
        }
    }

    /**
     * 生成48小时未互动推送消息
     */
    public void create48Hous(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hous48", 1);

        List<MfUserEntity> list = mfUserService.queryList(map);

        for(MfUserEntity entity : list){
            MessageConstants.addHous48(entity.getOpenId());
        }
    }

    /**
     * 给刚关注的用户拉取用户消息
     */
    public void loadUserInfo(){

        List<MfUserEntity> list = mfUserService.getSubscribe();

        String days = sysConfigService.getValue("MF_DAYS", "0");

        User user = new User();

        for(MfUserEntity entity : list){

            user.getUserInfo(entity);
            entity.setExpireDate(DateUtils.getFetureDate(Integer.valueOf(days)));
            entity.setWnb(0);
            //设置用户为正常状态
            entity.setStatus(1);
            mfUserService.update(entity);

            try {
                logger.info("为用户："+ new String(Base64Utils.decode(entity.getNickname())) +"拉取信息成功");
            } catch (Exception e) {
                logger.error("解码失败.", e);
            }
        }

    }

}
