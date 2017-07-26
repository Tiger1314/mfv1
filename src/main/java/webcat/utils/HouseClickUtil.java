package webcat.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mf.entity.MfHouseClickEntity;
import mf.utils.Base64Utils;

import java.net.URLDecoder;
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
        if(entity.getUser() != null){
            c.put("headimgurl", entity.getUser().getHeadimgurl());
        }
        try {
            c.put("nikename", URLDecoder.decode(entity.getUser().getNickname(), "UTF-8"));
        } catch (Exception e) {
            c.put("nikename", "/");
        }
        c.put("isliked", entity.getIsliked());
        c.put("signature", entity.getUser().getSignature());
        c.put("rank", entity.getRank());

        return c;
    }

    public static JSONObject getHouseClicks(List<MfHouseClickEntity> entityList, Integer isshared){

        JSONObject json = new JSONObject();

        json.put("isshared", isshared);

        JSONArray array = new JSONArray();

        int i;

        for(MfHouseClickEntity entity : entityList){
            array.add(getHouseClick(entity));
        }
        json.put("data", array);

        return json;
    }

}
