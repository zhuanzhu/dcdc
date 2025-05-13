package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UrlTypeDictManage;
import com.egeo.components.user.facade.UrlTypeDictFacade;
import com.egeo.components.user.dto.UrlTypeDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("urlTypeDict")
public class UrlTypeDictManageImpl implements UrlTypeDictManage{

	
	@Resource(name="urlTypeDictFacade")
	private UrlTypeDictFacade urlTypeDictFacade;

	@Override
	public UrlTypeDictDTO findUrlTypeDictById(UrlTypeDictDTO dto) {
		return urlTypeDictFacade.findUrlTypeDictById(dto);
	}

	@Override
	public PageResult<UrlTypeDictDTO> findUrlTypeDictOfPage(UrlTypeDictDTO dto, Pagination page) {
		return urlTypeDictFacade.findUrlTypeDictOfPage(dto, page);
	}

	@Override
	public List<Map<String, Object>> findUrlTypeDictAll(UrlTypeDictDTO dto) {
		List<Map<String, Object>>  maps = new ArrayList<>();
		List<UrlTypeDictDTO> list = urlTypeDictFacade.findUrlTypeDictAll(dto);
		for (UrlTypeDictDTO urlTypeDictDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", urlTypeDictDTO.getId());
			map.put("name", urlTypeDictDTO.getName());
			maps.add(map);
		}
		return maps;
	}

	@Override
	public Long insertUrlTypeDictWithTx(UrlTypeDictDTO dto) {
		return urlTypeDictFacade.insertUrlTypeDictWithTx(dto);
	}

	@Override
	public int updateUrlTypeDictWithTx(UrlTypeDictDTO dto) {
		return urlTypeDictFacade.updateUrlTypeDictWithTx(dto);
	}

	@Override
	public int deleteUrlTypeDictWithTx(UrlTypeDictDTO dto) {
		return urlTypeDictFacade.deleteUrlTypeDictWithTx(dto);
	}


}
	