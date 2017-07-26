package webcat.Interaction;


import webcat.utils.ConEnum;
import webcat.utils.HttpClientUtils;
import webcat.utils.ReturnMessage;

/**
 * 消息发送
 */
public class Message {

	private final String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

	/**
	 * 发送文本消息
	 * @return
	 */
	public Boolean sendTextMessage(String message, String open_id){
		
		AccessToken token = new AccessToken();
		String send_url = url + token.getAccessToken();
		
		StringBuffer msg = new StringBuffer();
		msg.append("{");
		msg.append("\"touser\":\""+open_id+"\",");
		msg.append("\"msgtype\":\"text\",");
		msg.append("\"text\":");
		msg.append("{");
		msg.append("\"content\":\"" + message + "\"");
		msg.append("}");
		msg.append("}");
		
		HttpClientUtils hc = new HttpClientUtils();
		return ReturnMessage.getReturnCode(hc.post(send_url, msg.toString()));
	}

	/**
	 * 蜗牛币不足
	 * @param openId
	 * @return
	 */
	public Boolean sendWnb50(String openId){

		String mes = "主人！您的蜗牛壳不多啦，赶紧补充能量吧！一大波优质房源信息等着您！" +
				"<a href='" + Menu.getMenuUrl(ConEnum.Menu.JRHY.getValue()) + "'>【充值中心】</a>";

		return sendTextMessage(mes, openId);
	}


	/**
	 * 会员到期提醒
	 * @param openId
	 * @param timeStr
	 * @return
	 */
	public Boolean hydq(String openId, String timeStr){
		String mes = "主人！您参加的包月活动已经结束（" + timeStr + "）" +
				"请确认您的账户留有余额，小蜗很乐意继续为您提供房源信息服务！";

		return sendTextMessage(mes, openId);
	}
}
