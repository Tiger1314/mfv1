package webcat.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import mf.entity.MfPushEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 推送缓存设置
 * Created by dengfan on 2017/3/10.
 */
public class PushCache {
    public static Cache<String, Map<String, MfPushEntity>> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(48, TimeUnit.HOURS)
            .maximumSize(1000000)
            .build();
}
