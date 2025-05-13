package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StoreReadService;
import com.egeo.components.product.manage.read.StoreReadManage;
import com.egeo.components.product.manage.read.StoreTreeNodeReadManage;
import com.egeo.components.product.condition.StoreCondition;
import com.egeo.components.product.condition.StoreTreeNodeCondition;
import com.egeo.components.product.converter.StoreConverter;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.po.StorePO;
import com.egeo.components.product.po.StoreTreeNodePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("storeReadService")
public class StoreReadServiceImpl  implements StoreReadService {
	@Autowired
	private StoreReadManage storeReadManage;
	
	@Autowired
	private StoreTreeNodeReadManage storeTreeNodeReadManage;

	@Override
	public StoreDTO findStoreById(StoreDTO dto) {
		StorePO po = StoreConverter.toPO(dto);
		StoreCondition storeCondition = storeReadManage.findStoreById(po);
		StoreDTO storeDTO = StoreConverter.toExeDTO(storeCondition);
		if(EmptyUtil.isNotEmpty(storeDTO)){
			storeDTO.setStoreMenuTreeId(storeCondition.getStoreMenuTreeId());
		}
		return storeDTO;
	}

	@Override
	public PageResult<StoreDTO> findStoreOfPage(StoreDTO dto, Pagination page) {
		StorePO po = StoreConverter.toPO(dto);
        PageResult<StorePO> pageResult = storeReadManage.findStoreOfPage(po, page);
        
        List<StoreDTO> list = StoreConverter.toDTO(pageResult.getList());
        PageResult<StoreDTO> result = new PageResult<StoreDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreDTO> findStoreAll(StoreDTO dto) {
		StorePO po = StoreConverter.toPO(dto);
		List<StorePO> list = storeReadManage.findStoreAll(po);		
		return StoreConverter.toDTO(list);
	}

	@Override
	public List<StoreDTO> findRootStoreAll(StoreDTO dto) {
		StorePO po = StoreConverter.toPO(dto);
		List<StoreCondition> list = storeReadManage.findRootStoreAll(po);		
		return StoreConverter.toExeDTO(list);
	}

	@Override
	public PageResult<StoreDTO> findRootStoreOfPage(StoreDTO dto, Pagination page) {
		StorePO po = StoreConverter.toPO(dto);
        PageResult<StoreCondition> pageResult = storeReadManage.findRootStoreOfPage(po, page);
        
        List<StoreDTO> list = StoreConverter.toExeDTO(pageResult.getList());
        PageResult<StoreDTO> result = new PageResult<StoreDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StoreDTO> findStoreAllByTreeId(Long storeTreeId) {
		List<StoreCondition> list = storeReadManage.findStoreAllByTreeId(storeTreeId);		
		return StoreConverter.toExeDTO(list);
	}
	/**
	 * 根据门店id查询总店信息
	 */
	@Override
	public StoreDTO findHeadStoreByStoreId(Long storeId) {
		StorePO storePO = storeReadManage.findHeadStoreByStoreId(storeId);
		return StoreConverter.toDTO(storePO);
	}

	@Override
	public StoreDTO findStoreByNodeId(Long nodeId) {
		StoreCondition storeCondition = storeReadManage.findStoreByNodeId(nodeId);
		StoreDTO storeDTO = StoreConverter.toExeDTO(storeCondition);
		return storeDTO;
	}
	
	public List<StoreDTO> findCouponStore(StoreDTO storeDTO) {
		List<StoreDTO> storeDTOList = new ArrayList<>();
		List<StoreTreeNodeCondition> storeTreeNodeConditionList = storeTreeNodeReadManage.findByStoreIdAndName(storeDTO.getIds(), storeDTO.getName(), storeDTO.getPlatformId());
		storeTreeNodeConditionList.forEach(storeTreeNodeCondition -> {
			List<String> storeNames = storeTreeNodeReadManage.findByPids(storeTreeNodeCondition.getPids());
			StringBuffer storeName = new StringBuffer();
			for (int i = 0; i < storeNames.size(); i++) {
				storeName.append(storeNames.get(i));
				storeName.append("-");
			}
			storeName.append(storeTreeNodeCondition.getName());
			StoreDTO s = new StoreDTO(); 
			s.setName(storeName.toString());
			s.setId(storeTreeNodeCondition.getStoreId());
			storeDTOList.add(s);
		});
		return storeDTOList;
	}

	@Override
	public List<Long> findStoreByName(String storeName) {
		return storeReadManage.findStoreByName(storeName);

	}

	@Override
	public List<StoreDTO> findStoreByPlatformIdAndStoreMenu(StoreDTO dto) {
		StorePO storePO = StoreConverter.toPO(dto);
		return StoreConverter.toDTO(storeReadManage.findStoreByPlatformIdAndStoreMenu(storePO));
	}
}
	