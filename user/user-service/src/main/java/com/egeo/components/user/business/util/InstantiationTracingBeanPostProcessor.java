package com.egeo.components.user.business.util;


import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.egeo.components.user.business.UrlWhiteListManage;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent>{
	
	@Resource(name="urlWhiteList")
	private UrlWhiteListManage urlWhiteListManage;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){

			/*cache.del(CacheKeyConstant.MENU_CACHE_LIST_KEY);
			cache.set(CacheKeyConstant.MENU_CACHE_LIST_KEY, urlManage.getUrlList());*/
			urlWhiteListManage.refreshCacheUrlWhiteList();
	      }
		
	}

	
	
	
}
