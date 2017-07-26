package webcat.Interaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 上传素材消息
 * Created by dengfan on 2017/3/3.
 */
public class Media {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";

    public String uploadMedia(){
        String mediaId = "";

        AccessToken token = new AccessToken();

        String sendUrl = url + token.getAccessToken() + "&type=image";



        return mediaId;
    }
}
