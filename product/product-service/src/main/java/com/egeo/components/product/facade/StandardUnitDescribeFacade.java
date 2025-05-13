package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitDescribeReadService;
import com.egeo.components.product.service.write.StandardUnitDescribeWriteService;
import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitDescribeFacade {
	
	@Resource
	private StandardUnitDescribeReadService standardUnitDescribeReadService;
	
	@Resource
	private StandardUnitDescribeWriteService standardUnitDescribeWriteService;
	
	
	public StandardUnitDescribeDTO findStandardUnitDescribeById(StandardUnitDescribeDTO dto){
		
		return standardUnitDescribeReadService.findStandardUnitDescribeById(dto);
	}

	public PageResult<StandardUnitDescribeDTO> findStandardUnitDescribeOfPage(StandardUnitDescribeDTO dto,Pagination page){
		
		return standardUnitDescribeReadService.findStandardUnitDescribeOfPage(dto, page);
		
	}

	public List<StandardUnitDescribeDTO> findStandardUnitDescribeAll(StandardUnitDescribeDTO dto){
		
		return standardUnitDescribeReadService.findStandardUnitDescribeAll(dto);
		
	}

	public Long insertStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto){
		
		return standardUnitDescribeWriteService.insertStandardUnitDescribeWithTx(dto);
	}

	public int updateStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto){
		
		return standardUnitDescribeWriteService.updateStandardUnitDescribeWithTx(dto);
	}

	public int deleteStandardUnitDescribeWithTx(StandardUnitDescribeDTO dto){
		
		return standardUnitDescribeWriteService.deleteStandardUnitDescribeWithTx(dto);
		
	}
	/**
	 * 根据su商品id查询su商品星详情
	 * @param standardUnitId
	 * @return
	 */
	public String findContentByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardUnitDescribeReadService.findContentByStandardUnitId(standardUnitId);
	}

}
	