package com.egeo.components.order.common;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * *********************************************************
 * <p/>
 * Author: jinjin Date: -- : Version: default .. Class description：
 * <p>
 * Cache Demo
 * </p>
 * <p/>
 * *********************************************************
 */
public class CacheUtils {

	private static LoadingCache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(10000000)
			.expireAfterWrite(1, TimeUnit.DAYS).recordStats().build(new CacheLoader<Object, Object>() {

				@Override
				public Object load(Object key) throws Exception {
					return key;
				}
			});

	public static int get(Object key) throws ExecutionException {
		Object var = cache.get(key);
		if (var.equals(key)) {
			var = 0;
			put(key, var);
		}
		return (int) var;
	}

	public static void put(Object key, Object value) {
		cache.put(key, value);
	}

	public static Long getPhone(Object key) throws ExecutionException {
		Object obj = cache.get(key);
		if (obj.equals(key)) {
			return null;
		} else {
			if (obj instanceof Long) {
				Long time = (Long) obj;
				return time;
			}
		}
		return null;
	}
}
