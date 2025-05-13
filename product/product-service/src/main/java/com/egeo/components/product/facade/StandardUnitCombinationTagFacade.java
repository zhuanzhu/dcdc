package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitCombinationTagReadService;
import com.egeo.components.product.service.write.StandardUnitCombinationTagWriteService;
import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitCombinationTagFacade {
	
	@Resource
	private StandardUnitCombinationTagReadService standardUnitCombinationTagReadService;
	
	@Resource
	private StandardUnitCombinationTagWriteService standardUnitCombinationTagWriteService;
	
	
	public StandardUnitCombinationTagDTO findStandardUnitCombinationTagById(StandardUnitCombinationTagDTO dto){
		
		return standardUnitCombinationTagReadService.findStandardUnitCombinationTagById(dto);
	}

	public PageResult<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagOfPage(StandardUnitCombinationTagDTO dto,Pagination page){
		
		return standardUnitCombinationTagReadService.findStandardUnitCombinationTagOfPage(dto, page);
		
	}

	public List<StandardUnitCombinationTagDTO> findStandardUnitCombinationTagAll(StandardUnitCombinationTagDTO dto){
		
		return standardUnitCombinationTagReadService.findStandardUnitCombinationTagAll(dto);
		
	}

	public Long insertStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto){
		
		return standardUnitCombinationTagWriteService.insertStandardUnitCombinationTagWithTx(dto);
	}

	public int updateStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto){
		
		return standardUnitCombinationTagWriteService.updateStandardUnitCombinationTagWithTx(dto);
	}

	public int deleteStandardUnitCombinationTagWithTx(StandardUnitCombinationTagDTO dto){
		
		return standardUnitCombinationTagWriteService.deleteStandardUnitCombinationTagWithTx(dto);
		
	}
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public List<Map<String, Object>> findTagByStandardUnitCombinationId(Long standardUnitCombinationId) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<StandardUnitCombinationTagDTO> standardUnitCombinationTagList = standardUnitCombinationTagReadService.findTagByStandardUnitCombinationId(standardUnitCombinationId);
		for (StandardUnitCombinationTagDTO standardUnitCombinationTagDTO : standardUnitCombinationTagList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", standardUnitCombinationTagDTO.getId());
			map.put("tagName", standardUnitCombinationTagDTO.getTagName());
			list.add(map);
		}
		return list;
	}
	/**
	 * 批量保存su组合id与标签关系信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public Integer saveStandardUnitCombinationTagWithTx(StandardUnitCombinationDTO standardUnitCombinationDTO,
			List<Long> tagIdList) {
		// TODO Auto-generated method stub
		return standardUnitCombinationTagWriteService.saveStandardUnitCombinationTagWithTx(standardUnitCombinationDTO,tagIdList);
	}

}
	