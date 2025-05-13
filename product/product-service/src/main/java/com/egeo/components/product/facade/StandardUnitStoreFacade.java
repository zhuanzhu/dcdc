package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitStoreReadService;
import com.egeo.components.product.service.write.StandardUnitStoreWriteService;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitStoreFacade {
	
	@Resource
	private StandardUnitStoreReadService standardUnitStoreReadService;
	
	@Resource
	private StandardUnitStoreWriteService standardUnitStoreWriteService;
	
	
	public StandardUnitStoreDTO findStandardUnitStoreById(StandardUnitStoreDTO dto){
		
		return standardUnitStoreReadService.findStandardUnitStoreById(dto);
	}

	public PageResult<StandardUnitStoreDTO> findStandardUnitStoreOfPage(StandardUnitStoreDTO dto,Pagination page){
		
		return standardUnitStoreReadService.findStandardUnitStoreOfPage(dto, page);
		
	}

	public List<StandardUnitStoreDTO> findStandardUnitStoreAll(StandardUnitStoreDTO dto){
		
		return standardUnitStoreReadService.findStandardUnitStoreAll(dto);
		
	}

	public Long insertStandardUnitStoreWithTx(StandardUnitStoreDTO dto){
		
		return standardUnitStoreWriteService.insertStandardUnitStoreWithTx(dto);
	}

	public int updateStandardUnitStoreWithTx(StandardUnitStoreDTO dto){
		
		return standardUnitStoreWriteService.updateStandardUnitStoreWithTx(dto);
	}

	public int deleteStandardUnitStoreWithTx(StandardUnitStoreDTO dto){
		
		return standardUnitStoreWriteService.deleteStandardUnitStoreWithTx(dto);
		
	}

	public Map<String, Object> standardUnitStoreByStandardUnitId(StandardUnitStoreDTO dto) {
		Map<String, Object> data = new HashMap<>();
		List<StandardUnitStoreDTO> standardUnitStoreList = standardUnitStoreReadService.standardUnitStoreByStandardUnitId(dto);
		List<Map<String, Object>> list = new ArrayList<>(standardUnitStoreList.size());
		for (StandardUnitStoreDTO standardUnitStoreDTO : standardUnitStoreList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", standardUnitStoreDTO.getId());
			map.put("storeName", standardUnitStoreDTO.getStoreName());
			list.add(map);
		}
		data.put("stores", list);
		return data;
	}

}
	