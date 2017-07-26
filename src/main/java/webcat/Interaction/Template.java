package webcat.Interaction;

import com.alibaba.fastjson.JSONObject;
import mf.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webcat.entity.MessageEntity;
import webcat.utils.ConEnum;
import webcat.utils.HttpClientUtils;

import java.text.DecimalFormat;
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

        JSONObject json = JSONObject.parseObject(mes);

        return json.getString("errcode");
    }


    /**
     * 充值成功提醒-包月
     * @return
     */
    public String sendCztxBy(String openId, Double price, Integer days, Date begintime, Date endtime){

        StringBuffer mes = new StringBuffer();

        DecimalFormat df = new DecimalFormat("#");

        mes.append(" {");
        mes.append("\"touser\":\""+openId+"\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.CZTX_BY.getValue() + "\",");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"充值成功提醒\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"" + df.format(price) + "元\"");
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

        DecimalFormat df = new DecimalFormat("#");

        mes.append(" {");
        mes.append("\"touser\":\""+openId+"\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.CZTX_WNB.getValue() + "\",");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"充值成功提醒\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"" + df.format(price) + "元\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"" + DateUtils.format(cztime, DateUtils.DATE_TIME_PATTERN_CN) + "\"");
        mes.append("},");
        mes.append("\"keyword3\":{");
        mes.append("\"value\":\"" + wnk + "枚蜗牛壳\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\"主人！您已充值成功。 接下来的日子小蜗会精心挑选每一条房源信息，及时为主人奉上！\"");
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
        mes.append("\"template_id\":\""+ ConEnum.Model.WNB_0.getValue() + "\",");
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
        mes.append("\"template_id\":\""+ ConEnum.Model.HYDQ.getValue() + "\",");
        mes.append("\"url\":\"" + Menu.getMenuUrl(ConEnum.Menu.JRHY.getValue()) + "\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"主人！ 小蜗为您提供的信息服务到期啦！\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"闪电房\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"房源信息服务\"");
        mes.append("},");
        mes.append("\"keyword3\":{");
        mes.append("\"value\":\"" + timeStr + "\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\"还有一大波优质房源等着主人接收，请及时补充能量啊！【立即充值】\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");

        return sendTemplateMessage(mes.toString());

    }

    /**
     * 房源消息推送提醒-模板
     * @param message
     * @return
     */
    public String houseInfo(MessageEntity message){
        StringBuffer mes = new StringBuffer();

        mes.append(" {");
        mes.append("\"touser\":\""+message.getOpenId()+"\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.GTCG_M.getValue() + "\",");
        mes.append("\"url\":\"" + message.getUrl() + "\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\""+message.getTitle()+"\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\""+message.getAddress()+"\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"" + message.getUnit() + "\"");
        mes.append("},");
        mes.append("\"keyword3\":{");
        mes.append("\"value\":\"" + message.getArea() + "\"");
        mes.append("},");
        mes.append("\"keyword4\":{");
        mes.append("\"value\":\"" + message.getPrice() + "\"");
        mes.append("},");
        mes.append("\"keyword5\":{");
        mes.append("\"value\":\"" + message.getHouseType() + "\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\""+message.getDesc()+"\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");


        return sendTemplateMessage(mes.toString());
    }

    public String houseInfoNew(MessageEntity message){
        StringBuffer mes = new StringBuffer();

        mes.append(" {");
        mes.append("\"touser\":\""+message.getOpenId()+"\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.WTCG_NEW.getValue() + "\",");
        mes.append("\"url\":\"" + message.getUrl() + "\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\""+message.getTitle()+"\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\""+message.getArea()+"\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"" + message.getUnit() + "\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\""+message.getDesc()+"\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");


        return sendTemplateMessage(mes.toString());
    }


    /**
     * 房源消息推送-客服消息
     * @param message
     * @return
     */
    public String houseInfoKF(MessageEntity message){
        StringBuffer mes = new StringBuffer();

        mes.append("{");
        mes.append("\"touser\":\""+message.getOpenId()+"\",");
        mes.append("\"msgtype\":\"news\",");
        mes.append("\"news\":{");
        mes.append("\"articles\": [");
        mes.append("{");
        mes.append("\"title\":\"委托成功提醒\",");
        mes.append("\"description\":\"" + message.getTitle() + "\\n");
        mes.append("房源面积：" + message.getArea() + "\\n");
        mes.append("房源户型：" + message.getUnit() + "\\n");
        mes.append("" + message.getDesc() + "\",");
        mes.append("\"url\":\"" + message.getUrl() + "\"");
        mes.append("}");
        mes.append("]");
        mes.append("}");
        mes.append("}");

        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

        AccessToken token = new AccessToken();

        url += token.getAccessToken();

        HttpClientUtils hc = new HttpClientUtils();

        String m = hc.post(url, mes.toString());

        JSONObject json = JSONObject.parseObject(m);

        return json.getString("errcode");
    }

    /**
     * 夜间提醒
     * @param openId
     * @return
     */
    public String nightKf(String openId){
        StringBuffer mes = new StringBuffer();

        mes.append("{");
        mes.append("\"touser\":\""+ openId +"\",");
        mes.append("\"msgtype\":\"news\",");
        mes.append("\"news\":{");
        mes.append("\"articles\": [");
        mes.append("{");
        mes.append("\"title\":\"夜间模式开始\",");
        mes.append("\"description\":\"主人，夜已深，小蜗就不打扰您了，明早会把今晚找到的消息告诉您的，晚安！\",");

        mes.append("\"url\":\"\"");
        mes.append("}");
        mes.append("]");
        mes.append("}");
        mes.append("}");

        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

        AccessToken token = new AccessToken();

        url += token.getAccessToken();

        HttpClientUtils hc = new HttpClientUtils();

        String m = hc.post(url, mes.toString());

        JSONObject json = JSONObject.parseObject(m);

        return json.getString("errcode");
    }


    /**
     * 发送整点消息
     * @param openId
     * @param count
     * @return
     */
    public String sendZdMessage(String openId, Integer count){
        StringBuffer mes = new StringBuffer();

        StringBuffer r = new StringBuffer();

        int h = DateUtils.getHour();

        r.append("服务时段：" + (h - 1) + ":00至" + h + ":00").append("\\n");
        r.append("主人！ 您选择的是整点信息服务，这段时间小蜗找了一波优质房源信息，请主人及时批阅！【查看详情】");

        mes.append(" {");
        mes.append("\"touser\":\"" + openId + "\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.WR_ZD.getValue() + "\",");
        mes.append("\"url\":\"" + Menu.getMenuUrl(ConEnum.Menu.FYGC.getValue()) + "\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"主人！小蜗为您提供定制服务啦！\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"整点信息服务\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"收集" + count + "条房源信息\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\""+ r.toString() +"\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");

        return sendTemplateMessage(mes.toString());
    }

    /**
     * 发送勿扰消息
     * @param openId
     * @param count
     * @return
     */
    public String sendWrMessage(String openId, Integer count){
        StringBuffer mes = new StringBuffer();

        StringBuffer r = new StringBuffer();

        int h = DateUtils.getHour();

        if(h == 12){
            r.append("服务时段：8:00至12:00").append("\\n");
        }
        else if(h == 16){
            r.append("服务时段：12:00至16:00").append("\\n");
        }
        else if(h == 20){
            r.append("服务时段：16:00至20:00").append("\\n");
        }

        r.append("主人！ 您选择的是勿扰模式，这段时间小蜗找了一波优质房源信息，请主人及时批阅！【查看详情】");

        mes.append(" {");
        mes.append("\"touser\":\"" + openId + "\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.WR_ZD.getValue() + "\",");
        mes.append("\"url\":\"" + Menu.getMenuUrl(ConEnum.Menu.FYGC.getValue()) + "\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"主人！小蜗为您提供定制服务啦！\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"勿扰模式\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"收集" + count + "条房源信息\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\""+ r.toString() +"\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");

        return sendTemplateMessage(mes.toString());
    }

    /**
     * 发送夜间消息
     * @param openId
     * @param count
     * @return
     */
    public String sendYjMessage(String openId, Integer count){
        StringBuffer mes = new StringBuffer();

        StringBuffer r = new StringBuffer();

        int h = DateUtils.getHour();

        r.append("服务时段：21:00至8:00").append("\\n");
        r.append("主人！ 您选择的是夜间信息服务，这段时间小蜗找了一波优质房源信息，请主人及时批阅！【查看详情】");

        mes.append(" {");
        mes.append("\"touser\":\"" + openId + "\",");
        mes.append("\"template_id\":\""+ ConEnum.Model.WR_ZD.getValue() + "\",");
        mes.append("\"url\":\"" + Menu.getMenuUrl(ConEnum.Menu.FYGC.getValue()) + "\", ");
        mes.append("\"data\":{");
        mes.append("\"first\":{");
        mes.append("\"value\":\"主人！小蜗为您提供定制服务啦！\"");
        mes.append("},");
        mes.append("\"keyword1\":{");
        mes.append("\"value\":\"夜间信息服务\"");
        mes.append("},");
        mes.append("\"keyword2\":{");
        mes.append("\"value\":\"收集" + count + "条房源信息\"");
        mes.append("},");
        mes.append("\"remark\":{");
        mes.append("\"value\":\""+ r.toString() +"\"");
        mes.append("}");
        mes.append("}");
        mes.append("}");

        return sendTemplateMessage(mes.toString());
    }

}
