package webcat.Interaction;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webcat.cache.AccessTokenCache;
import webcat.utils.Constants;
import webcat.utils.HttpClientUtils;

import java.util.concurrent.Callable;

/**
 * 获取accesstoken
 * @author 邓凡
 *
 */
public class AccessToken {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private final String url = "https://api.weixin.qq.com/cgi-bin/token";
	
	private String getToken(){
		
		String access_token = "";
		
		String send_url = url + "?grant_type=client_credential&appid=" + Constants.appID + "&secret=" + Constants.appsecret;
		
		HttpClientUtils hc = new HttpClientUtils();
		
		String mes = hc.get(send_url);
		
		JSONObject mesMap = JSON.parseObject(mes);

		//正确
		if(!mesMap.containsKey("errcode")){
			access_token = mesMap.get("access_token").toString();
			logger.info("get token : " + access_token);
		}
		else{
			logger.error("token error.");
		}
		return access_token;
	}
	
	
	public synchronized String getAccessToken(){
		String token = "";
		try{
			token =  AccessTokenCache.cache.get("access_token", new Callable<String>(){
				public String call(){
					return getToken();
				}
			});
		}
		catch(Exception e){
			logger.error("token error.", e);
		}
		return token;
	}
	
}
