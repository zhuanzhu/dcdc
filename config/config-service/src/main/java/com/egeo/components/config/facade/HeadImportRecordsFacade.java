package com.egeo.components.config.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.service.read.HeadImportRecordsReadService;
import com.egeo.components.config.service.write.HeadImportRecordsWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class HeadImportRecordsFacade {
	
	@Autowired
	private HeadImportRecordsReadService headImportRecordsReadService;
	
	@Autowired
	private HeadImportRecordsWriteService headImportRecordsWriteService;
	
	
	public HeadImportRecordsDTO findHeadImportRecordsById(HeadImportRecordsDTO dto){
		
		return headImportRecordsReadService.findHeadImportRecordsById(dto);
	}

	public PageResult<HeadImportRecordsDTO> findHeadImportRecordsOfPage(HeadImportRecordsDTO dto,Pagination page){
		
		return headImportRecordsReadService.findHeadImportRecordsOfPage(dto, page);
		
	}

	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO dto){
		
		return headImportRecordsReadService.findHeadImportRecordsAll(dto);
		
	}

	public Long insertHeadImportRecordsWithTx(HeadImportRecordsDTO dto){
		
		return headImportRecordsWriteService.insertHeadImportRecordsWithTx(dto);
	}

	public int updateHeadImportRecordsWithTx(HeadImportRecordsDTO dto){
		
		return headImportRecordsWriteService.updateHeadImportRecordsWithTx(dto);
	}

	public int deleteHeadImportRecordsWithTx(HeadImportRecordsDTO dto){
		
		return headImportRecordsWriteService.deleteHeadImportRecordsWithTx(dto);
		
	}

}
	