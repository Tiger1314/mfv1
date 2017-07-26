package webcat.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * jsapiticket 缓存
 * @author dengfan
 *
 */
public class JsapiTicketCache {
	public static Cache<String, String> cache = CacheBuilder.newBuilder()  
            .expireAfterWrite(3500, TimeUnit.SECONDS).maximumSize(7000)
            .build();
}
