package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.egeo.common.CacheKeyConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.business.UrlWhiteListManage;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.dto.UrlWhiteListDTO;
import com.egeo.components.user.facade.PlatformFacade;
import com.egeo.components.user.facade.UrlWhiteListFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;

@Service("urlWhiteList")
public class UrlWhiteListManageImpl implements UrlWhiteListManage {

	private Logger logger = LoggerFactory.getLogger(UrlWhiteListManageImpl.class);
	
	@Resource(name="urlWhiteListFacade")
	private UrlWhiteListFacade urlWhiteListFacade;
	
	@Resource
	private JedisUtil cache;
	
	@Resource(name="platformFacade")
	private PlatformFacade platformFacade;

	@Override
	public UrlWhiteListDTO findUrlWhiteListById(UrlWhiteListDTO dto) {
		return urlWhiteListFacade.findUrlWhiteListById(dto);
	}

	@Override
	public PageResult<UrlWhiteListDTO> findUrlWhiteListOfPage(UrlWhiteListDTO dto, Pagination page) {
		return urlWhiteListFacade.findUrlWhiteListOfPage(dto, page);
	}

	@Override
	public List<UrlWhiteListDTO> findUrlWhiteListAll(UrlWhiteListDTO dto) {
		return urlWhiteListFacade.findUrlWhiteListAll(dto);
	}

	@Override
	public List<String> findUrlWhiteList(Long platformId) {
		return urlWhiteListFacade.findUrlWhiteList(platformId);
	}

	@Override
	public Long insertUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList) {
		return urlWhiteListFacade.insertUrlWhiteListWithTx(dto, platformIdList);
	}

	@Override
	public int updateUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList) {
		return urlWhiteListFacade.updateUrlWhiteListWithTx(dto, platformIdList);
	}

	@Override
	public int deleteUrlWhiteListWithTx(UrlWhiteListDTO dto) {
		return urlWhiteListFacade.deleteUrlWhiteListWithTx(dto);
	}

	@Override
	public void refreshCacheUrlWhiteList() {
		logger.info("开始加载授权url列表");
		List<UrlWhiteListDTO> whiteUrlList = findUrlWhiteListAll(new UrlWhiteListDTO());
		Map<String, List<String>> urlMap = new HashMap<>();
		setDifferUrlList(whiteUrlList, urlMap);
		clearCache();
		for (Entry<String, List<String>> entry : urlMap.entrySet()) {
			cache.set(entry.getKey(), entry.getValue());
		}
		logger.info("授权url列表加载成功");
	}
	
	private void clearCache() {
		cache.del(CacheKeyConstant.WHITE_URL_LIST_KEY + "_" + PlatformKeyConstant.FGJ_PLATFORM_ID);
		cache.del(CacheKeyConstant.WHITE_URL_LIST_KEY + "_" + PlatformKeyConstant.MYY_PLATFORM_ID);
	}
	
	private void setDifferUrlList(List<UrlWhiteListDTO> whiteUrlList, Map<String, List<String>> urlMap) {
		if (EmptyUtil.isNotEmpty(whiteUrlList)) {
			for (UrlWhiteListDTO urlDTO : whiteUrlList) {
				addToCacheMap(urlDTO, urlMap);
			}
		}
	}
	
	private void addToCacheMap(UrlWhiteListDTO urlDTO, Map<String, List<String>> urlMap) {
		List<PlatformDTO> platformList = platformFacade.findAllPlatform();
		if (urlDTO.getPlatformId().equals(-1L)) {
			for (PlatformDTO platform : platformList) {
				StringBuilder key = new StringBuilder();
				key.append(CacheKeyConstant.WHITE_URL_LIST_KEY);
				key.append("_").append(platform.getId());
				String keyStr = key.toString();
				if (urlMap.containsKey(keyStr)) {
					urlMap.get(keyStr).add(urlDTO.getUrl());
				} else {
					List<String> urlList = new ArrayList<>();
					urlList.add(urlDTO.getUrl());
					urlMap.put(keyStr, urlList);
				}
			}
		} else {
			StringBuilder key = new StringBuilder();
			key.append(CacheKeyConstant.WHITE_URL_LIST_KEY);
			key.append("_").append(urlDTO.getPlatformId());
			String keyStr = key.toString();
			if (urlMap.containsKey(keyStr)) {
				urlMap.get(keyStr).add(urlDTO.getUrl());
			} else {
				List<String> urlList = new ArrayList<>();
				urlList.add(urlDTO.getUrl());
				urlMap.put(keyStr, urlList);
			}
		}
	}
	
	
}
	