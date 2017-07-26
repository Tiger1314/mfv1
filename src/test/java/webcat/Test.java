package webcat;

import webcat.Interaction.AccessToken;
import webcat.utils.HttpClientUtils;

/**
 * Created by dengfan on 2017/3/4.
 */
public class Test {


    public static void main(String[] args) {

        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

        AccessToken token = new AccessToken();

        url += token.getAccessToken();

        String mes = "{\n" +
                "    \"touser\":\"oBrPNwBn1bP4DAb6EbGMVwZFzC_M\",\n" +
                "    \"msgtype\":\"news\",\n" +
                "    \"news\":{\n" +
                "        \"articles\": [\n" +
                "         {\n" +
                "             \"title\":\"委托成功通知\",\n" +
                "             \"description\":\"马沧湖路单位宿舍两房78万出售\\n" +
                "房源地址：钟家村 - 汉阳钟家村\\n" +
                "房源户型：2室1厅1卫\\n" +
                "房源面积：69平\\n" +
                "房源价格：69万元\\n" +
                "房源类型：住宅\\n\\n" +
                "王女士在58同城发布了一条售房消息\",\n" +
                "             \"url\":\"www.baidu.com\"\n" +
                "         }\n" +
                "         ]\n" +
                "    }\n" +
                "}";

//        String m = "马沧湖路单位宿舍两房78万出售\n房源地址：钟家村 - 汉阳钟家村\n房源户型：2室1厅1卫\n房源面积：69平\n房源价格：69万元\n房源类型：住宅\n";

//        String mes = "{\n" +
//                "    \"touser\":\"oBrPNwJTUAvMgxKkirgT9xLGlo2c\",\n" +
//                "    \"msgtype\":\"text\",\n" +
//                "    \"text\":\n" +
//                "    {\n" +
//                "         \"content\":\"" + m + "\"\n" +
//                "    }\n" +
//                "}";

        HttpClientUtils hc = new HttpClientUtils();

        for(int i = 1; i <= 1; i ++){
            System.out.println("第" + i + "次发送:" + hc.post(url, mes));
        }
    }
}
