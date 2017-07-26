package webcat.utils;

import com.alibaba.fastjson.JSONObject;
import mf.entity.MfAdEntity;
import mf.entity.MfUserEntity;
import mf.entity.SysUserEntity;
import mf.utils.DateUtils;

import java.util.Date;

/**
 * Created by dengfan on 2017/3/6.
 */
public class AdUtil {

    public static JSONObject getAd(MfAdEntity entity, MfUserEntity user){

        JSONObject ad = new JSONObject();

        ad.put("id", entity.getId());
        ad.put("title", entity.getTitle());
        ad.put("url", entity.getUrl());

        //获取最后一次的显示时间
        Date lastShowTime = user.getAdShow();
        if(lastShowTime == null){
            ad.put("show", 1);
        }
        else{
            if(DateUtils.hourBetween(lastShowTime, DateUtils.getTodayDate()) > 48){
                ad.put("show", 1);
            }
            else{
                ad.put("show", 0);
            }
        }

        return ad;
    }

}
