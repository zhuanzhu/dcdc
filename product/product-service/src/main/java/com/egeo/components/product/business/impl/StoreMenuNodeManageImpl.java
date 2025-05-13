package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StoreMenuNodeManage;
import com.egeo.components.product.facade.StoreMenuNodeFacade;
import com.egeo.components.product.facade.StoreMenuTreeFacade;
import com.egeo.components.product.dto.StoreMenuNodeDTO;
import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("storeMenuNode")
public class StoreMenuNodeManageImpl implements StoreMenuNodeManage{

	
	@Resource(name="storeMenuNodeFacade")
	private StoreMenuNodeFacade storeMenuNodeFacade;
	
	@Resource(name="storeMenuTreeFacade")
	private StoreMenuTreeFacade storeMenuTreeFacade;

	@Override
	public Map<String, Object> findStoreMenuNodeById(StoreMenuNodeDTO dto) {
		Map<String, Object> map = new HashMap<>();
		StoreMenuNodeDTO storeMenuNodeDTO = storeMenuNodeFacade.findStoreMenuNodeById(dto);
		map.put("id", storeMenuNodeDTO.getId());
		map.put("name", storeMenuNodeDTO.getName());
		map.put("sortValue", storeMenuNodeDTO.getSortValue());
		map.put("description", storeMenuNodeDTO.getDescription());
		return map;
	}

	@Override
	public PageResult<StoreMenuNodeDTO> findStoreMenuNodeOfPage(StoreMenuNodeDTO dto, Pagination page) {
		return storeMenuNodeFacade.findStoreMenuNodeOfPage(dto, page);
	}

	@Override
	public List<Map<String, Object>> findStoreMenuNodeAll(StoreMenuNodeDTO dto) {
		StoreMenuTreeDTO storeMenuTreeDTO = new StoreMenuTreeDTO();
		storeMenuTreeDTO.setStoreId(dto.getStoreId());
		storeMenuTreeDTO.setPlatformId(dto.getPlatformId());
		List<StoreMenuTreeDTO> storeMenuTreeList = 
				storeMenuTreeFacade.findStoreMenuTreeAll(storeMenuTreeDTO);
		if(EmptyUtil.isEmpty(storeMenuTreeList))
			throw new BusinessException("门店菜单类目树异常");
		
		StoreMenuNodeDTO storeMenuNodeDTO = new StoreMenuNodeDTO();
		storeMenuNodeDTO.setStoreMenuTreeId(storeMenuTreeList.get(0).getId());
		storeMenuNodeDTO.setPlatformId(dto.getPlatformId());
		List<StoreMenuNodeDTO> storeMenuNodeList = storeMenuNodeFacade.findStoreMenuNodeAll(storeMenuNodeDTO);
		List<Map<String, Object>> list = new ArrayList<>(storeMenuNodeList.size());
		for (StoreMenuNodeDTO storeMenuNodeDTO2 : storeMenuNodeList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", storeMenuNodeDTO2.getId());
			map.put("name", storeMenuNodeDTO2.getName());
			map.put("sortValue", storeMenuNodeDTO2.getSortValue());
			map.put("description", storeMenuNodeDTO2.getDescription());
			map.put("isAll", storeMenuNodeDTO2.getIsAll());
			list.add(map);
		}
		return list;
	}

	@Override
	public Long insertStoreMenuNodeWithTx(StoreMenuNodeDTO dto) {
		return storeMenuNodeFacade.insertStoreMenuNodeWithTx(dto);
	}

	@Override
	public int updateStoreMenuNodeWithTx(StoreMenuNodeDTO dto) {
		return storeMenuNodeFacade.updateStoreMenuNodeWithTx(dto);
	}

	@Override
	public int deleteStoreMenuNodeWithTx(StoreMenuNodeDTO dto) {
		return storeMenuNodeFacade.deleteStoreMenuNodeWithTx(dto);
	}

	@Override
	public Map<String, Object> findByStoreIdOfPage(Long storeId, Long platformId, Pagination page) {
		StoreMenuTreeDTO storeMenuTreeDTO = new StoreMenuTreeDTO();
		storeMenuTreeDTO.setStoreId(storeId);
		storeMenuTreeDTO.setPlatformId(platformId);
		List<StoreMenuTreeDTO> storeMenuTreeList = 
				storeMenuTreeFacade.findStoreMenuTreeAll(storeMenuTreeDTO);
		if(EmptyUtil.isEmpty(storeMenuTreeList))
			throw new BusinessException("门店菜单类目树异常");
		
		Map<String, Object> data = new HashMap<>();
		StoreMenuNodeDTO storeMenuNodeDTO = new StoreMenuNodeDTO();
		storeMenuNodeDTO.setStoreMenuTreeId(storeMenuTreeList.get(0).getId());
		storeMenuNodeDTO.setPlatformId(platformId);
		PageResult<StoreMenuNodeDTO> rt = storeMenuNodeFacade.findStoreMenuNodeOfPage(storeMenuNodeDTO, page);
		List<StoreMenuNodeDTO> storeMenuNodeList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>(storeMenuNodeList.size());
		for (StoreMenuNodeDTO storeMenuNodeDTO2 : storeMenuNodeList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", storeMenuNodeDTO2.getId());
			map.put("name", storeMenuNodeDTO2.getName());
			map.put("sortValue", storeMenuNodeDTO2.getSortValue());
			map.put("description", storeMenuNodeDTO2.getDescription());
			map.put("isAll", storeMenuNodeDTO2.getIsAll());
			list.add(map);
		}
        PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
        
        data.put("storeMenuTreeId", storeMenuTreeList.get(0).getId());
        data.put("result", result);
		return data;
	}


}
	