package webcat.Interaction;

import webcat.utils.ConEnum;
import webcat.utils.Constants;
import webcat.utils.HttpClientUtils;

/**
 * 设置菜单
 * Created by dengfan on 2017/3/3.
 */
public class Menu {


    private final String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    public void setMunu(){

        StringBuffer menu = new StringBuffer();

        menu.append("{");
        menu.append("    \"button\":[");
        menu.append("    {");
        menu.append("         \"type\":\"view\",");
        menu.append("         \"name\":\"房源广场\",");
        menu.append("         \"url\":\"" + getMenuUrl(ConEnum.Menu.FYGC.getValue()) + "\"");
        menu.append("     },");
        menu.append("     {");
        menu.append("          \"name\":\"设置\",");
        menu.append("          \"sub_button\":[");
        menu.append("           {");
        menu.append("              \"type\":\"click\",");
        menu.append("              \"name\":\"勿扰模式\",");
        menu.append("              \"key\":\"wrset\"");
        menu.append("           },");
        menu.append("           {");
        menu.append("              \"type\":\"view\",");
        menu.append("              \"name\":\"订阅设置\",");
        menu.append("              \"url\":\"" + getMenuUrl(ConEnum.Menu.DYSZ.getValue()) + "\"");
        menu.append("           }]");
        menu.append("      },");
        menu.append("     {");
        menu.append("          \"name\":\"我的\",");
        menu.append("          \"sub_button\":[");
        menu.append("           {");
        menu.append("              \"type\":\"view\",");
        menu.append("              \"name\":\"充值中心\",");
        menu.append("              \"url\":\"" + getMenuUrl(ConEnum.Menu.JRHY.getValue()) + "\"");
        menu.append("           },");
        menu.append("           {");
        menu.append("              \"type\":\"view\",");
        menu.append("              \"name\":\"个人中心\",");
        menu.append("              \"url\":\"" + getMenuUrl(ConEnum.Menu.JRHY.getValue()) + "\"");
        menu.append("           },");
        menu.append("           {");
        menu.append("              \"type\":\"view\",");
        menu.append("              \"name\":\"用户帮助\",");
        menu.append("              \"url\":\"" + getMenuUrl(ConEnum.Menu.JRHY.getValue()) + "\"");
        menu.append("           },");
        menu.append("           {");
        menu.append("              \"type\":\"click\",");
        menu.append("              \"name\":\"意见反馈\",");
        menu.append("              \"key\":\"yjfk\"");
        menu.append("           }]");
        menu.append("      }]");
        menu.append("}");

        HttpClientUtils hc = new HttpClientUtils();

        AccessToken token = new AccessToken();

        String send_url = url + token.getAccessToken();

        System.out.println(hc.post(send_url, menu.toString()));
    }



    public static String getMenuUrl(String url){
        StringBuffer sb = new StringBuffer();
        sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.appID + "&redirect_uri=");
        sb.append(url);
        sb.append("&response_type=code&scope=snsapi_base&state=123#wechat_redirect");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMenuUrl(ConEnum.Menu.FYGC.getValue()));
    }

}
