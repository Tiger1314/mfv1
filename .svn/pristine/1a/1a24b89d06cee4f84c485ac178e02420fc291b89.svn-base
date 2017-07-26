package webcat.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * access_token 缓存
 * @author 邓凡
 *
 */
public class AccessTokenCache {
	public static Cache<String, String> cache = CacheBuilder.newBuilder()  
            .expireAfterWrite(7000, TimeUnit.SECONDS).maximumSize(1)
            .build();
}
