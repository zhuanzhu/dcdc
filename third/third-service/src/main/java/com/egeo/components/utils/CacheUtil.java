package com.egeo.components.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
* *********************************************************
* <p/>
* Author:     jinjin
* Date:       -- :
* Version:    default ..
* Class description：
* <p>Cache Demo</p>
* <p/>
* *********************************************************
*/
public class CacheUtil {

   private static LoadingCache<Object, Object> cache = CacheBuilder.newBuilder()
           .maximumSize(10000000)
           .expireAfterWrite(30, TimeUnit.MINUTES)
           .recordStats()
           .build(new CacheLoader<Object, Object>() {

               @Override
               public Object load(Object key) throws Exception {
                   return key;
               }
           });

   public static Object get(Object key) throws ExecutionException {
       Object var = cache.get(key);
       if (var.equals(key)) {
           Object object = "not found";
           put(key, object);
       } else {
    	   var = cache.get(key);
       }
       return var;
   }

   public static void put(Object key, Object value) {
       cache.put(key, value);
   }

   public static void invalidate(Object key ) {
       cache.invalidate(key);
   }
}
