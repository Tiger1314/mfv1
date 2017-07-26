package webcat.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mf.entity.MfRechargeEntity;

import java.util.List;

/**
 * Created by dengfan on 2017/3/5.
 */
public class RechargeUtil {

    public static JSONObject getRecharge(MfRechargeEntity entity){

        JSONObject recharge = new JSONObject();

        recharge.put("id", entity.getId());
        recharge.put("title", entity.getTitle());
        recharge.put("old_price", entity.getOldPrice());
        recharge.put("discount", entity.getDiscount());
        recharge.put("days", entity.getDays());
        recharge.put("price", entity.getPrice());
        recharge.put("type", entity.getType());
        recharge.put("wnb", entity.getWnb());

        return recharge;
    }

    public static JSONArray getRecharges(List<MfRechargeEntity> entityList){

        JSONArray array = new JSONArray();

        for(MfRechargeEntity entity : entityList){
            array.add(getRecharge(entity));
        }

        return array;
    }

}
