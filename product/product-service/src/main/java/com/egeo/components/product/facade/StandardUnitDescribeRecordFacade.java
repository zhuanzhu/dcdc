package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitDescribeRecordReadService;
import com.egeo.components.product.service.write.StandardUnitDescribeRecordWriteService;
import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitDescribeRecordFacade {
	
	@Resource
	private StandardUnitDescribeRecordReadService standardUnitDescribeRecordReadService;
	
	@Resource
	private StandardUnitDescribeRecordWriteService standardUnitDescribeRecordWriteService;
	
	
	public StandardUnitDescribeRecordDTO findStandardUnitDescribeRecordById(StandardUnitDescribeRecordDTO dto){
		
		return standardUnitDescribeRecordReadService.findStandardUnitDescribeRecordById(dto);
	}

	public PageResult<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordOfPage(StandardUnitDescribeRecordDTO dto,Pagination page){
		
		return standardUnitDescribeRecordReadService.findStandardUnitDescribeRecordOfPage(dto, page);
		
	}

	public List<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordAll(StandardUnitDescribeRecordDTO dto){
		
		return standardUnitDescribeRecordReadService.findStandardUnitDescribeRecordAll(dto);
		
	}

	public Long insertStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto){
		
		return standardUnitDescribeRecordWriteService.insertStandardUnitDescribeRecordWithTx(dto);
	}

	public int updateStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto){
		
		return standardUnitDescribeRecordWriteService.updateStandardUnitDescribeRecordWithTx(dto);
	}

	public int deleteStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto){
		
		return standardUnitDescribeRecordWriteService.deleteStandardUnitDescribeRecordWithTx(dto);
		
	}

}
	