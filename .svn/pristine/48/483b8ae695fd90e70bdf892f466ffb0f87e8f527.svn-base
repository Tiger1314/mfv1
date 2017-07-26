package webcat.utils;

import java.util.Date;
/**
 * 封装最终的xml格式结果
 * @author pamchen-1
 *
 */
public class FormatXmlProcess {
	/**
	 * 封装文字类的返回消息
	 * @param to
	 * @param from
	 * @param content
	 * @return
	 */
	public String formatXmlAnswer(String to, String from, String content) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml><ToUserName><![CDATA[");
		sb.append(to);
		sb.append("]]></ToUserName><FromUserName><![CDATA[");
		sb.append(from);
		sb.append("]]></FromUserName><CreateTime>");
		sb.append(date.getTime());
		sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
		sb.append(content);
		sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
		return sb.toString();
	}
	
	/**
	 * 封装图片类的返回消息
	 * @param to
	 * @param from
	 * @return
	 */
	public String formatXmlAnswerPng(String to, String from) {
		StringBuffer sb = new StringBuffer();
		Date date = new Date();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[" + to + "]]></ToUserName>");
		sb.append("<FromUserName><![CDATA["+from+"]]></FromUserName>");
		sb.append("<CreateTime>"+date.getTime()+"</CreateTime>");
		sb.append("<MsgType><![CDATA[image]]></MsgType>");
		sb.append("<Image>");
		sb.append("<MediaId><![CDATA["+Constants.png+"]]></MediaId>");
		sb.append("</Image>");
		sb.append("</xml>");
		return sb.toString();
	}
}
