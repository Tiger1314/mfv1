package webcat.Interaction;

import com.alibaba.fastjson.JSONObject;
import mf.entity.MfUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webcat.utils.HttpClientUtils;

import java.net.URLEncoder;

/**
 * 获取用户信息
 * Created by dengfan on 2017/3/3.
 */
public class User {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";

    public void getUserInfo(MfUserEntity entity){

        try {
            AccessToken token = new AccessToken();

            String send_url = url + token.getAccessToken() + "&openid=" + entity.getOpenId() + "&lang=zh_CN";

            HttpClientUtils hc = new HttpClientUtils();

            String mes = new String(hc.get(send_url).getBytes("ISO-8859-1"), "utf-8");

            JSONObject object = JSONObject.parseObject(mes);

            if(object.getIntValue("subscribe") != 0){
                entity.setNickname(URLEncoder.encode(object.getString("nickname"), "UTF-8"));
                entity.setSex(object.getInteger("sex"));
                entity.setCity(object.getString("city"));
                entity.setCountry(object.getString("country"));
                entity.setProvince(object.getString("province"));
                entity.setHeadimgurl(object.getString("headimgurl"));
            }

        } catch (Exception e) {
            logger.error("拉取用户信息失败", e);
        }
    }

}
