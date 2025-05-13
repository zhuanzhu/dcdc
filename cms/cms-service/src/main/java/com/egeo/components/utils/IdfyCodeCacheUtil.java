package com.egeo.components.utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * *********************************************************
 * <p/>
 * Author: jinjin
 * Date: -- :
 * Version: default ..
 * Class description：
 * <p>
 * Cache Demo
 * </p>
 * <p/>
 * *********************************************************
 */
public class IdfyCodeCacheUtil {

    private static LoadingCache<Object, Object> cache = CacheBuilder.newBuilder()
            .maximumSize(10000000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .recordStats()
            .build(new CacheLoader<Object, Object>() {

                @Override
                public Object load(Object key) throws Exception {
                    return key;
                }
            });

    public static Integer get(Object key) throws ExecutionException {

        Object values = (Object) cache.get(key);
        Integer var = null;
        if (values instanceof String) {
            put(key, 1);
        } else {
            var = (Integer) cache.get(key);
            Integer count = var.intValue() + 1;
            put(key, count);
        }
        return var;
    }

    public static void put(Object key, Integer value) {
        cache.put(key, value);
    }

    public static void putObj(Object key, Object value) {
        cache.put(key, value);
    }

    public static void delete(Object key) {
        cache.refresh(key);
        // cache.cleanUp();
    }
}
