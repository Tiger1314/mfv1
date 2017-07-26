package webcat.utils;

import mf.entity.MfPushEntity;
import org.apache.commons.lang.StringUtils;
import webcat.cache.PushCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Created by dengfan on 2017/3/10.
 */
public class PushSetUtil {

    /**
     * 设置推送缓存
     * @param entity
     */
    public static void setPush(MfPushEntity entity){

        String areaCode = entity.getPushArea().replaceAll("\"", "");

        if(StringUtils.isNotBlank(areaCode)){

            for(String code : areaCode.split(",")){

                Map<String, MfPushEntity> map = null;

                try {
                    map = PushCache.cache.get(code, new Callable<Map<String, MfPushEntity>>(){
                        public Map<String, MfPushEntity> call(){
                            return new HashMap<String, MfPushEntity>();
                        }
                    });
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(map == null){
                    map = new HashMap<String, MfPushEntity>();
                }

                //保存或刷新数据
                map.put(entity.getOpenId(), entity);

                //放入到缓存中
                PushCache.cache.put(code, map);
            }

        }

    }


}
