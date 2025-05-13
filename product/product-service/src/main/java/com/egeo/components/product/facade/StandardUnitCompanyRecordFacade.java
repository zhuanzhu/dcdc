package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitCompanyRecordReadService;
import com.egeo.components.product.service.write.StandardUnitCompanyRecordWriteService;
import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitCompanyRecordFacade {
	
	@Resource
	private StandardUnitCompanyRecordReadService standardUnitCompanyRecordReadService;
	
	@Resource
	private StandardUnitCompanyRecordWriteService standardUnitCompanyRecordWriteService;
	
	
	public StandardUnitCompanyRecordDTO findStandardUnitCompanyRecordById(StandardUnitCompanyRecordDTO dto){
		
		return standardUnitCompanyRecordReadService.findStandardUnitCompanyRecordById(dto);
	}

	public PageResult<StandardUnitCompanyRecordDTO> findStandardUnitCompanyRecordOfPage(StandardUnitCompanyRecordDTO dto,Pagination page){
		
		return standardUnitCompanyRecordReadService.findStandardUnitCompanyRecordOfPage(dto, page);
		
	}

	public List<StandardUnitCompanyRecordDTO> findStandardUnitCompanyRecordAll(StandardUnitCompanyRecordDTO dto){
		
		return standardUnitCompanyRecordReadService.findStandardUnitCompanyRecordAll(dto);
		
	}

	public Long insertStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto){
		
		return standardUnitCompanyRecordWriteService.insertStandardUnitCompanyRecordWithTx(dto);
	}

	public int updateStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto){
		
		return standardUnitCompanyRecordWriteService.updateStandardUnitCompanyRecordWithTx(dto);
	}

	public int deleteStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto){
		
		return standardUnitCompanyRecordWriteService.deleteStandardUnitCompanyRecordWithTx(dto);
		
	}

}
	