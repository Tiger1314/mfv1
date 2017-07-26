package webcat.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mf.entity.MfAreaEntity;

import java.util.List;

/**
 * Created by dengfan on 2017/3/5.
 */
public class AreaUtil {

    public static JSONObject getArea(MfAreaEntity entity){

        JSONObject area = new JSONObject();

        area.put("id", entity.getId());
        area.put("name", entity.getName());
        area.put("code", entity.getCode());

        return area;
    }


    public static JSONArray getAreas(List<MfAreaEntity> entityList){

        JSONArray array = new JSONArray();

        for(MfAreaEntity entity : entityList){
            array.add(getArea(entity));
        }

        return array;
    }

}
