package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitStoreReadService;
import com.egeo.components.product.manage.read.StandardUnitStoreReadManage;
import com.egeo.components.product.manage.read.StoreTreeNodeReadManage;
import com.egeo.components.product.condition.StandardUnitStoreCondition;
import com.egeo.components.product.converter.StandardUnitStoreConverter;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.po.StandardUnitStorePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitStoreReadService")
public class StandardUnitStoreReadServiceImpl  implements StandardUnitStoreReadService {
	@Autowired
	private StandardUnitStoreReadManage standardUnitStoreReadManage;
	@Autowired
	private StoreTreeNodeReadManage storeTreeNodeReadManage;

	@Override
	public StandardUnitStoreDTO findStandardUnitStoreById(StandardUnitStoreDTO dto) {
		StandardUnitStorePO po = StandardUnitStoreConverter.toPO(dto);
		StandardUnitStorePO list = standardUnitStoreReadManage.findStandardUnitStoreById(po);		
		return StandardUnitStoreConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitStoreDTO> findStandardUnitStoreOfPage(StandardUnitStoreDTO dto, Pagination page) {
		StandardUnitStorePO po = StandardUnitStoreConverter.toPO(dto);
        PageResult<StandardUnitStoreCondition> pageResult = standardUnitStoreReadManage.findStandardUnitStoreOfPage(po, page);
        List<StandardUnitStoreCondition> standardUnitStoreConditionList = pageResult.getList();
        List<StandardUnitStoreDTO> list = new ArrayList<>(standardUnitStoreConditionList.size());
        for (StandardUnitStoreCondition standardUnitStoreCondition : standardUnitStoreConditionList) {
        	StandardUnitStoreDTO standardUnitStoreDTO = StandardUnitStoreConverter.toDTO(standardUnitStoreCondition);
        	standardUnitStoreDTO.setStandardUnitName(standardUnitStoreCondition.getStandardUnitName());
    		standardUnitStoreDTO.setStandardUnitSerialNumber(standardUnitStoreCondition.getStandardUnitSerialNumber());
    		standardUnitStoreDTO.setSalePrice(standardUnitStoreCondition.getSalePrice());
    		standardUnitStoreDTO.setPromotionPrice(standardUnitStoreCondition.getPromotionPrice());
    		standardUnitStoreDTO.setMarketPrice(standardUnitStoreCondition.getMarketPrice());
    		standardUnitStoreDTO.setStatus(standardUnitStoreCondition.getStatus());
    		standardUnitStoreDTO.setIsVisible(standardUnitStoreCondition.getIsVisible());
    		list.add(standardUnitStoreDTO);
		}
        PageResult<StandardUnitStoreDTO> result = new PageResult<StandardUnitStoreDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitStoreDTO> findStandardUnitStoreAll(StandardUnitStoreDTO dto) {
		StandardUnitStorePO po = StandardUnitStoreConverter.toPO(dto);
		List<StandardUnitStoreCondition> list = standardUnitStoreReadManage.findStandardUnitStoreAll(po);	
		List<StandardUnitStoreDTO> standardUnitStoreList = new ArrayList<>(list.size());
		for (StandardUnitStoreCondition standardUnitStoreCondition : list) {
			StandardUnitStoreDTO standardUnitStoreDTO = StandardUnitStoreConverter.toDTO(standardUnitStoreCondition);
			standardUnitStoreDTO.setStandardUnitName(standardUnitStoreCondition.getStandardUnitName());
			standardUnitStoreList.add(standardUnitStoreDTO);
		}
		return standardUnitStoreList;
	}

	@Override
	public Integer standardUnitSumByStoreId(Long storeId, Long platformId) {
		return standardUnitStoreReadManage.standardUnitSumByStoreId(storeId, platformId);
	}

	@Override
	public List<Long> findByPuIdsByStoreId(Long storeId, Long platformId) {
		return standardUnitStoreReadManage.findByPuIdsByStoreId(storeId, platformId);
	}

	@Override
	public List<StandardUnitStoreDTO> standardUnitStoreByStandardUnitId(StandardUnitStoreDTO dto) {
		List<StandardUnitStoreCondition> standardUnitStoreList = standardUnitStoreReadManage.standardUnitStoreByStandardUnitId(StandardUnitStoreConverter.toPO(dto));
		List<StandardUnitStoreDTO> list = new ArrayList<>();
		for (StandardUnitStoreCondition standardUnitStoreCondition : standardUnitStoreList) {
			StandardUnitStoreDTO standardUnitStoreDTO = StandardUnitStoreConverter.toDTO(standardUnitStoreCondition);
			// 根据pids查询门店名称信息
			List<String> storeNames = storeTreeNodeReadManage.findByPids(standardUnitStoreCondition.getPids());
			// 拼接门店配置名称
			StringBuffer storeName = new StringBuffer();
			for (int i = 0; i < storeNames.size(); i++) {
				storeName.append(storeNames.get(i));
				storeName.append("-");
			}
			storeName.append(standardUnitStoreCondition.getStoreName());
			standardUnitStoreDTO.setStoreName(storeName.toString());
			list.add(standardUnitStoreDTO);
		}
		return list;
	}
	
	@Override
	public List<StandardUnitStoreDTO> findByStoreAndSu(List<Long> suIdList, Long storeId, Long platformId) {
		return StandardUnitStoreConverter.toDTO(standardUnitStoreReadManage.findByStoreAndSu(suIdList, storeId, platformId));
	}

	@Override
	public int findStandardUnitStoreCount(Long suId, Long storeId) {
		return standardUnitStoreReadManage.findStandardUnitStoreCount(suId,storeId);
	}
}
	