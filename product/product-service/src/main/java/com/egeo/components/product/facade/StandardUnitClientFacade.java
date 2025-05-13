package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.StandardUnitClientReadService;
import com.egeo.components.product.service.write.StandardUnitClientWriteService;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StandardUnitClientFacade {
	
	@Resource
	private StandardUnitClientReadService standardUnitClientReadService;
	
	@Resource
	private StandardUnitClientWriteService standardUnitClientWriteService;
	
	
	public StandardUnitClientDTO findStandardUnitClientById(StandardUnitClientDTO dto){
		
		return standardUnitClientReadService.findStandardUnitClientById(dto);
	}

	public PageResult<StandardUnitClientDTO> findStandardUnitClientOfPage(StandardUnitClientDTO dto,Pagination page){
		
		return standardUnitClientReadService.findStandardUnitClientOfPage(dto, page);
		
	}

	public List<StandardUnitClientDTO> findStandardUnitClientAll(StandardUnitClientDTO dto){
		
		return standardUnitClientReadService.findStandardUnitClientAll(dto);
		
	}

	public Long insertStandardUnitClientWithTx(StandardUnitClientDTO dto){
		
		return standardUnitClientWriteService.insertStandardUnitClientWithTx(dto);
	}

	public int updateStandardUnitClientWithTx(StandardUnitClientDTO dto){
		
		return standardUnitClientWriteService.updateStandardUnitClientWithTx(dto);
	}

	public int deleteStandardUnitClientWithTx(StandardUnitClientDTO dto){
		
		return standardUnitClientWriteService.deleteStandardUnitClientWithTx(dto);
		
	}
	/**
	 * 根据suid删除su客户端关系表
	 * @param merchantProdId
	 * @return
	 */
	public int deleteByStandardUnitIdWithTx(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardUnitClientWriteService.deleteByStandardUnitIdWithTx(standardUnitId);
	}

}
	