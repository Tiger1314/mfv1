package webcat.Interaction;

import com.alibaba.fastjson.JSONObject;
import mf.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webcat.utils.ConEnum;
import webcat.utils.HttpClientUtils;

import java.util.Date;

/**
 * Created by dengfan on 2017/3/3.
 */
public class Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * 发送模板消息
     * @param sendMes json格式字符串
     * @return
     */
    public String sendTemplateMessage(String sendMes){
        String mes = "";

        AccessToken token = new AccessToken();

        String send_url = url + token.getAccessToken();

        HttpClientUtils hc = new HttpClientUtils();
        mes = hc.post(send_url, sendMes);

        logger.info("模板消息发送完成");

        return mes;
    }


    /**
     * 充值成功提醒-包月
     * @return
     */
    public String sendCztxBy(String openId,Double price, Integer days, Date begintime, Date endtime){

        StringBuffer mes = new StringBuffer();

        mes.append(" {");
        mes.append("\"touser\":\""+openId+"\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.CZTX_BY.getValue() + "\",");
        mes.append("\"url\":\"http://weixin.qq.com/download\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"充值成功提醒\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"" + price + "元\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"" + days + "天\"");
        mes.append("},");
        mes.append("\"keyword3\":{");
        mes.append("\"value\":\"" + DateUtils.format(begintime, DateUtils.DATE_TIME_PATTERN_CN) + "\"");
        mes.append("},");
        mes.append("\"keyword4\":{");
        mes.append("\"value\":\"" + DateUtils.format(endtime, DateUtils.DATE_TIME_PATTERN_CN) + "\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\"主人，您已充值成功。小蜗被主人包月啦！接下来的日子小蜗会更卖力的找房源。\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");

        return sendTemplateMessage(mes.toString());
    }

    /**
     * 3.充值成功提醒-蜗牛壳
     * @param openId
     * @param price
     * @param wnk
     * @param cztime
     * @return
     */
    public String sendCztxWnb(String openId, Double price, Integer wnk, Date cztime){

        StringBuffer mes = new StringBuffer();

        mes.append(" {");
        mes.append("\"touser\":\""+openId+"\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.CZTX_BY.getValue() + "\",");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"充值成功提醒\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"" + price + "元\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"" + DateUtils.format(cztime, DateUtils.DATE_TIME_PATTERN_CN) + "\"");
        mes.append("},");
        mes.append("\"keyword3\":{");
        mes.append("\"value\":\"" + wnk + "蜗牛壳\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\"主人，您已充值成功。小蜗被主人包月啦！接下来的日子小蜗会更卖力的找房源。\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");

        return sendTemplateMessage(mes.toString());

    }

    /**
     * 蜗牛壳为0提醒（模板消息）（0蜗牛壳）
     * @param nikename
     * @param openId
     * @return
     */
    public String sendYebzWnb(String nikename, String openId){

        StringBuffer mes = new StringBuffer();

        mes.append(" {");
        mes.append("\"touser\":\""+openId+"\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.CZTX_BY.getValue() + "\",");
        mes.append("\"url\":\"" + Menu.getMenuUrl(ConEnum.Menu.JRHY.getValue()) + "\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"余额不足提醒\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"" + nikename + "\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"0\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\"主人！您的蜗牛壳已用完，赶紧补充能量吧！小蜗乐意为主人继续效力！【充值中心】\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");

        return sendTemplateMessage(mes.toString());

    }

    /**
     * 会员到期提醒
     * @param openId
     * @param timeStr
     * @return
     */
    public String hydq(String openId, String timeStr){

        StringBuffer mes = new StringBuffer();

        mes.append(" {");
        mes.append("\"touser\":\""+openId+"\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.CZTX_BY.getValue() + "\",");
        mes.append("\"url\":\"" + Menu.getMenuUrl(ConEnum.Menu.JRHY.getValue()) + "\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"会员到期提醒\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"蜗牛秒房会员\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"" + timeStr + "\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\"您好，您的会员已经到期，为了能继续收到房源信息，请及时充值！【充值中心】\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");

        return sendTemplateMessage(mes.toString());

    }

}
