package webcat.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import webcat.entity.ParamData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dengfan on 2017/2/6.
 */
public abstract class AbstractController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected Integer limit = 10;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;



    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * 得到ModelAndView
     *
     * @return
     */
    public ModelAndView getModelAndView() {
        return new ModelAndView();
    }

    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 得到request对象
     *
     * @return
     */
    public HttpServletRequest getResp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public ParamData getParamData(){
        return new ParamData(this.getRequest());
    }

    public JSONObject sendSuccess(){
        JSONObject mes = new JSONObject();
        mes.put("code", "success");
        return mes;
    }

    public JSONObject sendFailure(){
        JSONObject mes = new JSONObject();
        mes.put("code", "failure");
        return mes;
    }

    public JSONObject sendFailure(String message){
        JSONObject mes = new JSONObject();
        mes.put("code", "failure");
        mes.put("message", message);
        return mes;
    }

    protected String getOpenId(){
        return (String)session.getAttribute("open_id");
//        return "oBrPNwJTUAvMgxKkirgT9xLGlo2c";
    }
}
