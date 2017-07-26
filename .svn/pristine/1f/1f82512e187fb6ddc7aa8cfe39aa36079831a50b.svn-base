package webcat;

import webcat.Interaction.AccessToken;
import webcat.utils.HttpClientUtils;

/**
 * Created by dengfan on 2017/3/13.
 */
public class UserTest {

    public static void main(String[] args) {

        AccessToken token = new AccessToken();

        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token.getAccessToken()+"&next_openid=oBrPNwBRmwXdMuGNyWPyicCvDsF8";

        HttpClientUtils hc = new HttpClientUtils();

        String mes = hc.post(url, "");

        System.out.println(mes);
    }
}
