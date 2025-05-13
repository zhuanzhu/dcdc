package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.write.StandardProductUnitAttValueWriteService;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardProductUnitAttValueFacade {
	
	@Resource
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;
	
	@Resource
	private StandardProductUnitAttValueWriteService standardProductUnitAttValueWriteService;
	
	
	public StandardProductUnitAttValueDTO findStandardProductUnitAttValueById(StandardProductUnitAttValueDTO dto){
		
		return standardProductUnitAttValueReadService.findStandardProductUnitAttValueById(dto);
	}

	public PageResult<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueOfPage(StandardProductUnitAttValueDTO dto,Pagination page){
		
		return standardProductUnitAttValueReadService.findStandardProductUnitAttValueOfPage(dto, page);
		
	}

	public List<StandardProductUnitAttValueDTO> findStandardProductUnitAttValueAll(StandardProductUnitAttValueDTO dto){
		
		return standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(dto);
		
	}

	public Long insertStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto){
		
		return standardProductUnitAttValueWriteService.insertStandardProductUnitAttValueWithTx(dto);
	}

	public int updateStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto){
		
		return standardProductUnitAttValueWriteService.updateStandardProductUnitAttValueWithTx(dto);
	}

	public int deleteStandardProductUnitAttValueWithTx(StandardProductUnitAttValueDTO dto){
		
		return standardProductUnitAttValueWriteService.deleteStandardProductUnitAttValueWithTx(dto);
		
	}
	/**
	 * 根据spuid查询spu关键词
	 * @param id
	 * @param platformId
	 * @return
	 */
	public List<String> keyWordByStandardProductUnitId(Long standardProductUnitId, Long platformId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttValueReadService.keyWordByStandardProductUnitId(standardProductUnitId, platformId);
	}

}
	