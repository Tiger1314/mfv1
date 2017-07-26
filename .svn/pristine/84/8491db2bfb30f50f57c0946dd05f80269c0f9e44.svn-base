package webcat.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by dengfan on 2017/3/12.
 */
public class ReturnMessage {

    public static Boolean getReturnCode(String mes){
        JSONObject json = JSONObject.parseObject(mes);

        if("0".equals(json.getString("errcode"))){
            return true;
        }
        else{
            return false;
        }
    }

}
