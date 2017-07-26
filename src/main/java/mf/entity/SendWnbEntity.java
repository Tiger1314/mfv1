package mf.entity;

import java.io.Serializable;

/**
 * Created by dengfan on 2017/4/26.
 */
public class SendWnbEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //赠送用户
    private String ids;
    //赠送蜗牛币个数
    private Integer wnb;
    //说明
    private String desc;
    //是否通知
    private Integer tzFlag;
    //通知消息
    private String message;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Integer getWnb() {
        return wnb;
    }

    public void setWnb(Integer wnb) {
        this.wnb = wnb;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getTzFlag() {
        return tzFlag;
    }

    public void setTzFlag(Integer tzFlag) {
        this.tzFlag = tzFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
