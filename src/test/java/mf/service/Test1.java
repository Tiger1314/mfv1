package mf.service;

import mf.entity.MfHouseInfoEntity;
import mf.entity.MfPushEntity;
import mf.entity.MfRechargeOrderEntity;
import mf.entity.MfUserEntity;
import org.apache.commons.lang.StringUtils;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import webcat.entity.ParamData;
import webcat.utils.HouseUtils;
import webcat.utils.OrderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengfan on 2017/3/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mf.xml"})
public class Test1 {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MfHouseInfoService mfHouseInfoService;

    @Autowired
    private MfPushService mfPushService;
    @Autowired
    private MfPushLogService mfPushLogService;

    @org.junit.Test
    public void test1() throws IOException {
        try{

            //通过订阅设置获取房源
            Map<String, Object> map = new HashMap<String, Object>();

            int page = 1;

            String openId = "o9F5mwfu3UWg70Uck_no7vOxg4Jk";

            map.put("offset", (page - 1) * 10);
            map.put("limit", 10);
            map.put("openId", openId);

            MfPushEntity push = mfPushService.queryObject(openId);

            //出售
            if(push.getIsSell() == 1){

                map.put("isSell", 1);

                //面积
                List<Object> area = new ArrayList<Object>();
                for(String a : push.getSellArea().split(",")){
                    if("1".equals(a)){
                        for(int i = 1; i <= 6; i ++){
                            area.add(i);
                        }
                        break;
                    }
                    else{
                        area.add(Integer.valueOf(a));
                    }
                }
                map.put("sellArea", area.toArray());

                //价格
                List<Object> price = new ArrayList<Object>();
                for(String p : push.getSellPrice().split(",")){
                    if("1".equals(p)){
                        for(int i = 1; i <= 7; i ++){
                            price.add(i);
                        }
                        break;
                    }
                    else{
                        price.add(Integer.valueOf(p));
                    }
                }
                map.put("sellPrice", price.toArray());
            }
            //求购
            if(push.getIsBuy() == 1){

                map.put("isBuy", 1);

                //面积
                List<Object> area = new ArrayList<Object>();
                for(String a : push.getBuyArea().split(",")){
                    if("1".equals(a)){
                        for(int i = 1; i <= 6; i ++){
                            area.add(i);
                        }
                        break;
                    }
                    else{
                        area.add(Integer.valueOf(a));
                    }
                }
                map.put("buyArea", area.toArray());

                //价格
                List<Object> price = new ArrayList<Object>();
                for(String p : push.getBuyPrice().split(",")){
                    if("1".equals(p)){
                        for(int i = 1; i <= 7; i ++){
                            price.add(i);
                        }
                        break;
                    }
                    else{
                        price.add(Integer.valueOf(p));
                    }
                }
                map.put("buyPrice", price.toArray());
            }
            //出租
            if(push.getIsRent() == 1){

                map.put("isRent", 1);

                //类型
                List<Object> type = new ArrayList<Object>();
                for(String t : push.getRentType().split(",")){
                    if("1".equals(t)){
                        for(int i = 2; i <= 3; i ++){
                            type.add(i);
                        }
                        break;
                    }
                    else{
                        type.add(Integer.valueOf(t));
                    }
                }
                map.put("rentType", type.toArray());

                //面积
                List<Object> area = new ArrayList<Object>();
                for(String a : push.getRentArea().split(",")){
                    if("1".equals(a)){
                        for(int i = 1; i <= 6; i ++){
                            area.add(i);
                        }
                        break;
                    }
                    else{
                        area.add(Integer.valueOf(a));
                    }
                }
                map.put("rentArea", area.toArray());

                //价格
                List<Object> price = new ArrayList<Object>();
                for(String p : push.getRentPrice().split(",")){
                    if("1".equals(p)){
                        for(int i = 1; i <= 7; i ++){
                            price.add(i);
                        }
                        break;
                    }
                    else{
                        price.add(Integer.valueOf(p));
                    }
                }
                map.put("rentPrice", price.toArray());

            }
            //求租
            if(push.getIsQz() == 1){

                map.put("isQz", 1);

                //面积
                List<Object> area = new ArrayList<Object>();
                for(String a : push.getQzArea().split(",")){
                    if("1".equals(a)){
                        for(int i = 1; i <= 6; i ++){
                            area.add(i);
                        }
                        break;
                    }
                    else{
                        area.add(Integer.valueOf(a));
                    }
                }
                map.put("qzArea", area.toArray());

                //价格
                List<Object> price = new ArrayList<Object>();
                for(String p : push.getQzPrice().split(",")){
                    if("1".equals(p)){
                        for(int i = 1; i <= 7; i ++){
                            price.add(i);
                        }
                        break;
                    }
                    else{
                        price.add(Integer.valueOf(p));
                    }
                }
                map.put("qzPrice", price.toArray());
            }
            map.put("houseType", push.getHouseType().split(","));
            map.put("pushArea", push.getPushArea().replaceAll("\"", "").split(","));

            List<MfHouseInfoEntity> list = mfPushLogService.queryMyHouse(map);

            for(MfHouseInfoEntity entity : list){
                logger.info("#############房源： " + entity.getId() + "#################");
            }

        }
        catch(Exception e){
            logger.error("houselist", e);
        }
    }

}
