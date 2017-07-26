package webcat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import webcat.Interaction.Event;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by dengfan on 2017/3/14.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends AbstractController {

    /**
     * 微信消息核心处理方法
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/mfcontroller", produces = "application/json; charset=utf-8")
    public String mainController()throws Exception {
        /** 读取接收到的xml消息 */
        StringBuffer sb = new StringBuffer();
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String xml = sb.toString();	//次即为接收到微信端发送过来的xml数据

        //判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回
        String echostr = request.getParameter("echostr");
        if (echostr != null && echostr.length() > 1) {
            return echostr;
        } else {
            //正常的微信处理流程
            Event event = new Event();

            return event.eventController(xml);
        }
    }

}
