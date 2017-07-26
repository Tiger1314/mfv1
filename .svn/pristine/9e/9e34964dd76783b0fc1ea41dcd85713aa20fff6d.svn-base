package webcat.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mf.entity.MfRechargeOrderEntity;
import mf.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dengfan on 2017/3/12.
 */
public class OrderUtil {

    public static JSONObject getOrder(MfRechargeOrderEntity entity){
        JSONObject json = new JSONObject();

        json.put("time", DateUtils.format(entity.getCreateTime(), DateUtils.TIME_PATTERN));
        json.put("title", entity.getTitle());
        json.put("oper_Type", entity.getOperType());
        json.put("dec", entity.getDes());

        return json;
    }

    public static JSONObject getOrders(List<MfRechargeOrderEntity> list, Integer wnb){

        JSONObject json = new JSONObject();

        JSONArray array;

        json.put("wnb", wnb);

        Map<String, JSONArray> map = new HashMap<String, JSONArray>();

        for(MfRechargeOrderEntity entity : list){

            String key = DateUtils.format(entity.getCreateTime(), DateUtils.DATE_PATTERN);

            if(map.containsKey(key)){
                array = map.get(key);
                array.add(getOrder(entity));
            }
            else{
                array = new JSONArray();
                array.add(getOrder(entity));
            }

            map.put(key, array);
        }

        json.put("data", map);

        return json;
    }

}
