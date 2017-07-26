package webcat.Interaction;

import java.util.concurrent.Callable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import mf.utils.Sha1Util;
import mf.utils.WXUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webcat.cache.JsapiTicketCache;
import webcat.utils.Constants;
import webcat.utils.HttpClientUtils;

public class JsapiTicket {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private final String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";
	
	
	private String jt(){
		
		String jsapiticket = "";
		
		AccessToken token = new AccessToken();
		
		String send_url = url + token.getAccessToken() + "&type=jsapi";
		
		HttpClientUtils hc = new HttpClientUtils();
		
		String mes = hc.get(send_url);
		
		JSONObject mesMap = JSON.parseObject(mes);
		
		jsapiticket = mesMap.getString("ticket");
		
		return jsapiticket;
	}
	
	public synchronized String getJsapiTicket(){
		String token = "";
		try{
			token =  JsapiTicketCache.cache.get("jsapiTicket", new Callable<String>(){
				public String call(){
					return jt();
				}
			});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return token;
	}
	
	
	public String getSignature(String nonceStr, String timeStamp, String url){
		String jsapi_ticket = getJsapiTicket();
		if(StringUtils.isBlank(url)){
			url = Constants.service + "index.html";
		}
		
		String str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timeStamp +
                "&url=" + url;

        //sha1加密

		logger.info(str);

        String signature = Sha1Util.getSha1(str);
		
        return signature;
	}


	public static void main(String[] args) {
		JsapiTicket jt = new JsapiTicket();

		JSONObject object = new JSONObject();
		object.put("appId", Constants.appID);
		object.put("timeStamp", WXUtil.getTimeStamp());
		object.put("nonceStr", WXUtil.getNonceStr());
		object.put("signature", jt.getSignature(String.valueOf(object.get("nonceStr")), String.valueOf(object.get("timeStamp")), null));
	}
}
