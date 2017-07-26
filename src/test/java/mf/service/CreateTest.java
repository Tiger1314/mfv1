package mf.service;

import mf.entity.MfHouseInfoEntity;
import mf.entity.MfPushEntity;
import mf.entity.MfUserEntity;
import mf.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webcat.Interaction.Menu;
import webcat.cache.PushCache;
import webcat.entity.MessageEntity;
import webcat.service.PushService;
import webcat.utils.ConEnum;
import webcat.utils.MessageConstants;
import webcat.utils.PushSetUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by dengfan on 2017/4/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mf.xml"})
public class CreateTest {

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
    @Autowired
    private MfPushLogService mfPushLogService;


    @org.junit.Test
    public void createPush(){
//        List<MfHouseInfoEntity> houselist = mfHouseInfoService.getNewHouse();
//
//        logger.info("################找到房源："+houselist.size()+"条#######################");

        MfHouseInfoEntity house = mfHouseInfoService.queryObject(160462L);

//        for(MfHouseInfoEntity house : houselist){
            if(house == null){
                return;
            }

            int h = DateUtils.hourBetween(house.getPublicTime(), DateUtils.getTodayDate());
            if(h > 1){
                house.setPushedStatus(3);
                //为了测试暂时注释掉
                mfHouseInfoService.update(house);
            }

            logger.info("加载推送配置");

            //清空推送缓存
            PushCache.cache.invalidateAll();

            //获取所有需要推送的设置

            Map<String, Object> map = new HashMap<String, Object>();

            List<MfPushEntity> list = mfPushService.queryList(map);

            for(MfPushEntity entity : list){

                logger.info("为用户：" + entity.getOpenId() + "配置推送设置.");

                PushSetUtil.setPush(entity);
            }


            logger.info("加载推送配置完毕");

            logger.info("######################获取到新房源#########################################");
            logger.info("##############房源标题："+house.getTitle()+"##########################");
            logger.info("###################省："+house.getProvinceCode()+"##########################");
            logger.info("###################市："+house.getCityCode()+"##########################");
            logger.info("#################地区："+house.getDistrictCode()+"##########################");
            logger.info("#################商圈："+house.getBusinessCode()+"##########################");
            logger.info("######################获取到新房源##########################################");

            String provinceCode = house.getProvinceCode() + "";
            String cityCode = house.getCityCode() + "";
            String districtCode = house.getDistrictCode() + "";
            String businessCode = house.getBusinessCode() + "";

            try{
                Map<String, MfPushEntity> provinceMap = PushCache.cache.get(provinceCode, new Callable<Map<String, MfPushEntity>>() {
                    @Override
                    public Map<String, MfPushEntity> call() throws Exception {
                        return new HashMap<String, MfPushEntity>();
                    }
                });

                Map<String, MfPushEntity> cityMap = PushCache.cache.get(cityCode, new Callable<Map<String, MfPushEntity>>() {
                    @Override
                    public Map<String, MfPushEntity> call() throws Exception {
                        return new HashMap<String, MfPushEntity>();
                    }
                });

                Map<String, MfPushEntity> districtMap = PushCache.cache.get(districtCode, new Callable<Map<String, MfPushEntity>>() {
                    @Override
                    public Map<String, MfPushEntity> call() throws Exception {
                        return new HashMap<String, MfPushEntity>();
                    }
                });

                Map<String, MfPushEntity> businessMap = PushCache.cache.get(businessCode, new Callable<Map<String, MfPushEntity>>() {
                    @Override
                    public Map<String, MfPushEntity> call() throws Exception {
                        return new HashMap<String, MfPushEntity>();
                    }
                });

                Map<String, MessageEntity> pushmap = new HashMap<String, MessageEntity>();
                Map<String, Integer> wrmap = new HashMap<String, Integer>();
                Map<String, Integer> nightmap = new HashMap<String, Integer>();
                Map<String, Integer> bymap = new HashMap<String, Integer>();

                formarPushUser(provinceMap.values(), pushmap, wrmap, nightmap, bymap,  house);
                formarPushUser(cityMap.values(), pushmap, wrmap, nightmap, bymap, house);
                formarPushUser(districtMap.values(), pushmap, wrmap, nightmap, bymap, house);
                formarPushUser(businessMap.values(), pushmap, wrmap, nightmap, bymap, house);

                //添加推送消息
                for(MessageEntity entity : pushmap.values()){
                    MessageConstants.addMessage(entity);
                    logger.info("为用户：" + entity.getOpenId() + "添加了一条正常推送消息.");

                    //为用户添加推送消息
//                    savePushLog(entity.getOpenId(), entity.getHouseId(), 1);
                }

                //添加勿扰消息
                for(String openId : wrmap.keySet()){
                    MessageConstants.addWr(openId);
                    logger.info("为用户：" + openId + "添加了一条勿扰间消息.");

                    //为用户添加推送消息
//                    savePushLog(openId, wrmap.get(openId), 2);
                }

                //添加夜间消息
                for(String openId : nightmap.keySet()){
                    MessageConstants.addNigth(openId);
                    logger.info("为用户：" + openId + "添加了一条夜间消息.");

                    //为用户添加推送消息
//                    savePushLog(openId, nightmap.get(openId), 3);
                }

                for(String openId : bymap.keySet()){
                    MessageConstants.addZd(openId);
                    logger.info("为用户：" + openId + "添加了一条整点消息.");

                    //为用户添加推送消息
//                    savePushLog(openId, bymap.get(openId), 4);
                }

                mfHouseInfoService.updateHouseInfo(house);

                logger.info("还有" + MessageConstants.getMessageCount() + "条消息未推送");
            }
            catch (Exception e){
                house.setPushedStatus(4);
                //为了测试暂时注释掉
                mfHouseInfoService.update(house);
                logger.error("生成推送消息失败", e);
            }
//        }
    }



    public void formarPushUser(Collection<MfPushEntity> pushList, Map<String, MessageEntity> pushmap, Map<String, Integer> wrmap, Map<String, Integer> nightmap, Map<String, Integer> bymap, MfHouseInfoEntity house){

        for(MfPushEntity push : pushList){

            //价格是否满足设置

            String houseTypes = push.getHouseType();

            boolean hf = false;

            for(String h : houseTypes.split(",")){
                if(Integer.valueOf(h) == house.getHouseType()){
                    hf = true;
                }
            }

            if(!hf){
                continue;
            }

            boolean sell = false;
            boolean buy = false;
            boolean rent = false;
            boolean qz = false;


            //出售
            int is_sell = push.getIsSell();

            if(is_sell == 1 && house.getInfoType() == 1){

                sell = check1(house, push.getSellArea(), push.getSellPrice());

            }

            //求购
            int is_buy = push.getIsBuy();

            if(is_buy == 1 && house.getInfoType() == 3){

                buy = check1(house, push.getBuyArea(), push.getBuyPrice());

            }

            //出租
            int is_rent = push.getIsRent();

            if(is_rent == 1 && house.getInfoType() == 2){

                String rent_type = push.getRentType();

                Integer info_type = house.getInfoType();

                boolean f = false;

                if(StringUtils.isNotBlank(rent_type)){

                    for(String t : rent_type.split(",")){
                        if(Integer.valueOf(t) == 1){
                            f = true;
                            break;
                        }
                        else if(Integer.valueOf(t) == 3 && info_type == 5){
                            f = true;
                            break;
                        }
                        else if(Integer.valueOf(t) == 2 && info_type == 2){
                            f = true;
                            break;
                        }
                    }

                }

                if(f){
                    rent = check2(house, push.getRentArea(), push.getRentPrice());
                }
                else {
                    rent = false;
                }
            }

            //求租
            int is_qz = push.getIsQz();

            if(is_qz == 1 && house.getInfoType() == 4){

                qz = check2(house, push.getQzArea(), push.getQzPrice());

            }


            if(sell || buy || rent || qz){
                //判断是否是夜间
                if(DateUtils.isNight()){//夜间
                    nightmap.put(push.getOpenId(), house.getId().intValue());

                }
                else {
                    //是否设置了勿扰
                    MfUserEntity user = mfUserService.queryObject(push.getOpenId());

//                    if(user.getWrStatus() == 1){
//                        wrmap.put(push.getOpenId(), house.getId().intValue());
//                    }
//                    else{
//                        int newUser = pushService.checkNewUser(push.getOpenId());
//
//                        MessageEntity entity = null;
//
//                        //非新手用户
//                        if(newUser == 0){
//                            //正常推送
//                            entity = formatMessage(house, push.getOpenId(), Menu.getMenuUrl(ConEnum.Menu.FYXQ.getValue() + house.getId()));
//                        }
//                        //新手用户
//                        else{
//                            if(newUser == 1){
//                                //正常推送
//                                entity = formatMessage(house, push.getOpenId(), Menu.getMenuUrl(ConEnum.Menu.FYXQ.getValue() + house.getId()));
//                            }
//                            else if(newUser == 2){
//                                //推送-连接跳转到充值
//                                entity = formatMessage(house, push.getOpenId(), Menu.getMenuUrl(ConEnum.Menu.JRHY.getValue()));
//                            }
//                        }
////                        MessageConstants.addMessage(entity);
//                        pushmap.put(push.getOpenId(), entity);
//
//                    }

                    //判断用户是否有蜗牛币
                    if(user.getWnb() > 0){

                        if(user.getWrStatus() == 1){
                            wrmap.put(push.getOpenId(), house.getId().intValue());
                        }
                        else{
                            MessageEntity entity = formatMessage(house, push.getOpenId(), Menu.getMenuUrl(ConEnum.Menu.FYXQ.getValue() + house.getId()));
                            pushmap.put(push.getOpenId(), entity);
                        }
                    }

                    //包月用户
                    if(user.getExpireDate() != null && user.getExpireDate().getTime() > DateUtils.getTodayDate().getTime()){
                        if(user.getWrStatus() == 1){
                            wrmap.put(push.getOpenId(), house.getId().intValue());
                        }
                        else{
                            bymap.put(push.getOpenId(), house.getId().intValue());
                        }
                    }
                }
            }

        }

    }

    private boolean check1(MfHouseInfoEntity house, String areas, String prices){

        boolean flag = false;

        //面积是否满足

        flag = checkArea(house, areas);

        //价格是否满足
        if(flag){

            flag = false;

            Integer price = house.getTotalPrice() == null ? 0 : house.getTotalPrice();

            for(String p : prices.split(",")){
                if(p.equals("1")){
                    flag = true;
                }
                else if(p.equals("2") && price < 600000 ){
                    flag = true;
                }
                else if(p.equals("3") && price >= 600000 && price < 1000000){
                    flag = true;
                }
                else if(p.equals("4") && price >= 1000000 && price < 1500000){
                    flag = true;
                }
                else if(p.equals("5") && price >= 1500000 && price < 2000000){
                    flag = true;
                }
                else if(p.equals("6") && price >= 2000000){
                    flag = true;
                }
            }
        }

        return flag;
    }


    private boolean check2(MfHouseInfoEntity house, String areas, String prices){

        boolean flag = false;

        //面积是否满足

        flag = checkArea(house, areas);

        //价格是否满足
        if(flag){

            flag = false;

            Integer price = house.getUnitPrice() == null ? 0 : house.getUnitPrice();

            for(String p : prices.split(",")){
                if(p.equals("1")){
                    flag = true;
                }
                else if(p.equals("2") && price < 1000 ){
                    flag = true;
                }
                else if(p.equals("3") && price >= 1000 && price < 2000){
                    flag = true;
                }
                else if(p.equals("4") && price >= 2000 && price < 3000){
                    flag = true;
                }
                else if(p.equals("5") && price >= 3000 && price < 5000){
                    flag = true;
                }
                else if(p.equals("6") && price >= 5000 && price < 10000){
                    flag = true;
                }
                else if(p.equals("7") && price >= 10000){
                    flag = true;
                }
            }
        }

        return flag;
    }


    private boolean checkArea(MfHouseInfoEntity house, String areas){

        try{
            for(String a : areas.split(",")){

                if(a.equals("1")){
                    return true;
                }
                else if(a.equals("2") && Double.valueOf(house.getArea()) < 50 ){
                    return true;
                }
                else if(a.equals("3") && Double.valueOf(house.getArea()) >= 50 && Double.valueOf(house.getArea()) < 100){
                    return true;
                }
                else if(a.equals("4") && Double.valueOf(house.getArea()) >= 100 && Double.valueOf(house.getArea()) < 150){
                    return true;
                }
                else if(a.equals("5") && Double.valueOf(house.getArea()) >= 150 && Double.valueOf(house.getArea()) < 200){
                    return true;
                }
                else if(a.equals("6") && Double.valueOf(house.getArea()) >= 200){
                    return true;
                }
            }
        }
        catch (Exception  e){

        }

        return false;
    }

    private MessageEntity formatMessage(MfHouseInfoEntity house, String openId, String url){
        MessageEntity entity = new MessageEntity();

        entity.setOpenId(openId);
        entity.setHouseId(house.getId().intValue());
        entity.setAddDate(DateUtils.getTodayDate());

        String source = "";

        if(house.getSourceId() == 1){
            source = "58同城";
        }
        else if(house.getSourceId() == 2){
            source = "赶集";
        }
        else if(house.getSourceId() == 3){
            source = "房天下";
        }
        else if(house.getSourceId() == 4){
            source = "亿房";
        }
        else if(house.getSourceId() == 5){
            source = "安居客";
        }
        else if(house.getSourceId() == 6){
            source = "百姓网";
        }

        //房源类型
        String type = "";

        if(house.getHouseType() == 1){
            type = "住宅";
        }
        else{
            type = "写字楼";
        }

        //房源地址
        String address = "";

        if(StringUtils.isNotBlank(house.getAddress())){
            address = house.getAddress();
        }
        else{
            address = "未知";
        }

        if(house.getInfoType() == 1 || house.getInfoType() == 3){
            String str = house.getInfoType() == 1 ? "出售" : "求购";
            entity.setTitle("【" + type + "-" + str + "】" + house.getTitle());
            //总价
            if(house.getTotalPrice() != null && house.getTotalPrice() != 0){
                entity.setPrice(house.getTotalPrice() + "万元");
            }
            else{
                entity.setPrice("面议");
            }
            //单价
            if(house.getUnitPrice() != null && house.getUnitPrice() != 0){
                entity.setUnitPrice(house.getUnitPrice() + "元/㎡");
            }
            else {
                entity.setUnitPrice("面议");
            }

            StringBuffer desc = new StringBuffer();
            desc.append("房源价格：" + entity.getPrice()).append("\\n");
            desc.append("房源单价：" + entity.getUnitPrice()).append("\\n");
            desc.append("房源地址：" + address).append("\\n");
            desc.append(house.getContact() + "["+house.getPhone()+"]在"+source+"发布了一条" + "【" + type + "-" + str + "】" + "房源");

            entity.setDesc(desc.toString());
        }
        //1为出售，2为出租(整租)，3为求购，4为求租，5出租（合租）
        else if(house.getInfoType() == 2 || house.getInfoType() == 4 || house.getInfoType() == 5){
            String str = "";
            if(house.getInfoType() == 2){
                str = "出租";
            }
            else if(house.getInfoType() == 4){
                str = "求租";
            }
            else if(house.getInfoType() == 5){
                str = "出租";
            }

            entity.setTitle("【" + type + "-" + str + "】" + house.getTitle());

            if(house.getUnitPrice() != null && house.getUnitPrice() != 0){
                entity.setPrice(house.getUnitPrice() + "元/月");
            }
            else{
                entity.setPrice("面议");
            }

            StringBuffer desc = new StringBuffer();
            desc.append("房源价格：" + entity.getPrice()).append("\\n");
            desc.append("房源地址：" + address).append("\\n");
            desc.append(house.getContact() + "["+house.getPhone()+"]在"+source+"发布了一条" + "【" + type + "-" + str + "】" + "房源");

            entity.setDesc(desc.toString());
        }

        if(StringUtils.isNotBlank(house.getArea())){
            entity.setArea(house.getArea() + "㎡");
        }
        else{
            entity.setArea("未知");
        }

        if(StringUtils.isNotBlank(house.getUnit())){
            entity.setUnit(house.getUnit());
        }
        else{
            entity.setUnit("未知");
        }

        entity.setUrl(url);

        return entity;
    }

}
