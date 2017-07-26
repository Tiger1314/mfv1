package webcat.utils;

import com.alibaba.fastjson.JSONObject;
import mf.entity.MfPushEntity;

/**
 * Created by dengfan on 2017/3/12.
 */
public class PushUtil {

    public static JSONObject getPush(MfPushEntity entity){

        JSONObject json = new JSONObject();

        if(entity != null){
            json.put("house_type", entity.getHouseType());

            json.put("is_sell", entity.getIsSell());
            json.put("sell_type", entity.getSellType());
            json.put("sell_area", entity.getSellArea());
            json.put("sell_price", entity.getSellPrice());

            json.put("is_buy", entity.getIsBuy());
            json.put("buy_type", entity.getBuyType());
            json.put("buy_area", entity.getBuyArea());
            json.put("buy_price", entity.getBuyPrice());

            json.put("is_rent", entity.getIsRent());
            json.put("rent_type", entity.getRentType());
            json.put("rent_area", entity.getRentArea());
            json.put("rent_price", entity.getRentPrice());

            json.put("is_qz", entity.getIsQz());
            json.put("qz_type", entity.getQzType());
            json.put("qz_area", entity.getQzArea());
            json.put("qz_price", entity.getQzPrice());

            json.put("push_area", entity.getPushArea());
        }

        return json;
    }

}
