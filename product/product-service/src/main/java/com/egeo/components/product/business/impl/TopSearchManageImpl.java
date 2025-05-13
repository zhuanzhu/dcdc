package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.TopSearchManage;
import com.egeo.components.product.facade.TopSearchFacade;
import com.egeo.components.product.dto.TopSearchDTO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("topSearch")
public class TopSearchManageImpl implements TopSearchManage{

	
	@Resource(name="topSearchFacade")
	private TopSearchFacade topSearchFacade;

	@Override
	public Map<String, Object> findTopSearchById(TopSearchDTO dto) {
		TopSearchDTO topSearchDTO = topSearchFacade.findTopSearchById(dto);
		Map<String, Object> topSearch = new HashMap<>();
		topSearch.put("id", topSearchDTO.getId());
		topSearch.put("name", topSearchDTO.getName());
		topSearch.put("sortValue", topSearchDTO.getSortValue());
		
		// 查询排序最大值
		int sortValueMax = topSearchFacade.sortValueMax();
		Map<String, Object> map = new HashMap<>();
		map.put("topSearch", topSearch);
		map.put("sortValueMax", sortValueMax);
		return map;
	}

	@Override
	public Map<String, Object> findTopSearchOfPage(TopSearchDTO dto, Pagination page) {
		PageResult<TopSearchDTO> pageResult = topSearchFacade.findTopSearchOfPage(dto, page);
		List<TopSearchDTO> topSearchList = pageResult.getList();
		List<Map<String, Object>> maps = new ArrayList<>();
		for (TopSearchDTO topSearchDTO : topSearchList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", topSearchDTO.getId());
			map.put("name", topSearchDTO.getName());
			map.put("sortValue", topSearchDTO.getSortValue());
			map.put("isStart", topSearchDTO.getIsStart());
			maps.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(maps);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        
        // 查询启用的热搜条数
        int startTopSearchNum = topSearchFacade.findStartTopSearchNum(dto.getPlatformId());
		Map<String, Object> data = new HashMap<>();
		data.put("result", result);
		data.put("startTopSearchNum", startTopSearchNum);
		return data;
	}

	@Override
	public List<TopSearchDTO> findTopSearchAll(TopSearchDTO dto) {
		return topSearchFacade.findTopSearchAll(dto);
	}

	@Override
	public Long insertTopSearchWithTx(TopSearchDTO dto) {
		TopSearchDTO topSearchDTO = new TopSearchDTO();
		topSearchDTO.setName(dto.getName());
		List<TopSearchDTO> topSearchList = topSearchFacade.findTopSearchAll(topSearchDTO);
		if(EmptyUtil.isNotEmpty(topSearchList))
			throw new BusinessException("热搜名称重复");
		return topSearchFacade.insertTopSearchWithTx(dto);
	}

	@Override
	public int updateTopSearchWithTx(TopSearchDTO dto) {
		TopSearchDTO topSearchDTO = new TopSearchDTO();
		topSearchDTO.setName(dto.getName());
		List<TopSearchDTO> topSearchList = topSearchFacade.findTopSearchAll(topSearchDTO);
		if(EmptyUtil.isNotEmpty(topSearchList) && dto.getId() != topSearchList.get(0).getId())
			throw new BusinessException("热搜名称重复");
		return topSearchFacade.updateTopSearchWithTx(dto);
	}

	@Override
	public int deleteTopSearchWithTx(TopSearchDTO dto) {
		return topSearchFacade.deleteTopSearchWithTx(dto);
	}
	/**
	 * 根据id启用停用热搜
	 * @param topSearchId
	 * @return
	 */
	@Override
	public int startStopTopSearchWithTx(Long topSearchId) {
		// TODO Auto-generated method stub
		return topSearchFacade.startStopTopSearchWithTx(topSearchId);
	}
	/**
	 * 显示启用的热搜
	 */
	@Override
	public Map<String, Object> findStartTopSearchAll(TopSearchDTO dto) {
		List<Map<String, Object>> maps = new ArrayList<>();
		dto.setIsStart(1);
		List<TopSearchDTO> list = topSearchFacade.findTopSearchAll(dto);
		for (TopSearchDTO topSearchDTO : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("topSearchId", topSearchDTO.getId());
			map.put("name", topSearchDTO.getName());
			maps.add(map);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("topSearchs", maps);
		return data;
	}


}
	