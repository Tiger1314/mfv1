package mf.controller;

import com.alibaba.fastjson.JSONObject;
import mf.utils.qiniu.Const;
import mf.utils.qiniu.QiNiuUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dengfan on 2017/3/3.
 */
@RestController
@RequestMapping("/sys/upload")
public class SysUploadController extends AbstractController {

    /**
     * 获取七牛token
     * @return
     */
    @ResponseBody
    @RequestMapping("/token")
    public JSONObject getToken(){

        String key = "uptoken";
        String token = QiNiuUtil.getUpToken(Const.QN_BUCKETNAME);

        JSONObject mes = new JSONObject();
        mes.put(key, token);

        return mes;
    }

}
