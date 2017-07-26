package webcat.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mf.entity.MfHouseInfoEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dengfan on 2017/3/5.
 */
public class HouseUtils {


    public static JSONObject getHouse(MfHouseInfoEntity entity){
        JSONObject obj = new JSONObject();

        obj.put("id", entity.getId());
        obj.put("phone", entity.getPhone());
        obj.put("contact", entity.getContact());
        obj.put("address", entity.getAddress());
        obj.put("unit", entity.getUnit());
        obj.put("house_type", entity.getHouseType());
        obj.put("info_type", entity.getInfoType());
        obj.put("unit_price", entity.getUnitPrice());
        obj.put("total_price", entity.getTotalPrice());
        obj.put("area", entity.getArea());
        obj.put("title", entity.getTitle());
        obj.put("introduction", entity.getIntroduction());
        obj.put("pics", entity.getPics());
        obj.put("source_id", entity.getSourceId());
        obj.put("profile", entity.getProfile());
        obj.put("public_time", entity.getPublicTime());
        obj.put("status", entity.getStatus());
        obj.put("url", entity.getUrl());
        obj.put("source_name", ConEnum.HouseSource.getValue(entity.getSourceId()));
        obj.put("source_img", ConEnum.HouseSource.getUrl(entity.getSourceId()));
        if(StringUtils.isNotBlank(entity.getTags())){
            obj.put("tags", entity.getTags().split(","));
        }
        obj.put("iscollected", entity.getIscollected());

        return obj;
    }

    public static JSONArray getHouses(List<MfHouseInfoEntity> entityList){

        JSONArray array = new JSONArray();

        for(MfHouseInfoEntity entity : entityList){

            array.add(getHouse(entity));

        }

        return array;
    }

    public static JSONObject getCollect(List<MfHouseInfoEntity> entityList, int count){

        JSONObject json = new JSONObject();

        JSONArray array = new JSONArray();

        for(MfHouseInfoEntity entity : entityList){

            array.add(getHouse(entity));

        }

        json.put("total_count", count);
        json.put("data", array);

        return json;
    }

}
