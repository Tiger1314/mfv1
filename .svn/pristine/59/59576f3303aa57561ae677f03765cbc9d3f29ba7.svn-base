package webcat.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mf.entity.MfHouseClickEntity;
import mf.utils.Base64Utils;

import java.util.List;

/**
 * Created by dengfan on 2017/3/5.
 */
public class HouseClickUtil {

    public static JSONObject getHouseClick(MfHouseClickEntity entity){

        JSONObject c = new JSONObject();

        c.put("id", entity.getId());
        c.put("open_id", entity.getOpenId());
        c.put("house_id", entity.getHouseId());
        c.put("create_time", entity.getCreateTime());
        c.put("like_count", entity.getLikes());
        c.put("headimgurl", entity.getUser().getHeadimgurl());
        try {
            c.put("nikename", new String(Base64Utils.decode(entity.getUser().getNickname())));
        } catch (Exception e) {
            c.put("nikename", "/");
        }
        c.put("isliked", entity.getIsliked());
        c.put("signature", entity.getUser().getSignature());

        return c;
    }

    public static JSONArray getHouseClicks(List<MfHouseClickEntity> entityList){

        JSONArray array = new JSONArray();

        for(MfHouseClickEntity entity : entityList){
            array.add(getHouseClick(entity));
        }

        return array;
    }

}
