package webcat.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import mf.entity.MfPushEntity;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by dengfan on 2017/3/12.
 */
public class MessageCache {

    public static Cache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();
}
