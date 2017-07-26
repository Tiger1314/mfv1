package webcat.utils;

import com.alibaba.fastjson.JSONObject;
import mf.entity.MfUserEntity;

import java.net.URLDecoder;

/**
 * Created by dengfan on 2017/3/5.
 */
public class UserUtil {

    public static JSONObject getUser(MfUserEntity entity){

        JSONObject user = new JSONObject();

        user.put("open_id", entity.getOpenId());
        user.put("mobile", entity.getMobile());
        user.put("expire_date", entity.getExpireDate());
        try {
            user.put("nickname", URLDecoder.decode(entity.getNickname(), "UTF-8"));
        } catch (Exception e) {
            user.put("nickname", "/");
        }
        user.put("headimgurl", entity.getHeadimgurl());
        user.put("sex", entity.getSex());
        user.put("city", entity.getCity());
        user.put("country", entity.getCountry());
        user.put("province", entity.getProvince());
        user.put("remark", entity.getRemark());
        user.put("wnb", entity.getWnb());
        user.put("lave_days", entity.getLaveDays());
        user.put("collect_count", entity.getCollectCount());
        user.put("wr_set", entity.getWrStatus());
        user.put("signature", entity.getSignature());

        return user;
    }

}
