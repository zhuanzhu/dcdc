package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SupplierReadService;
import com.egeo.components.product.service.write.SupplierWriteService;
import com.egeo.components.product.dto.SupplierDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SupplierFacade {
	
	@Resource
	private SupplierReadService supplierReadService;
	
	@Resource
	private SupplierWriteService supplierWriteService;
	
	
	public SupplierDTO findSupplierById(SupplierDTO dto){
		
		return supplierReadService.findSupplierById(dto);
	}

	public PageResult<SupplierDTO> findSupplierOfPage(SupplierDTO dto,Pagination page){
		
		return supplierReadService.findSupplierOfPage(dto, page);
		
	}

	public List<SupplierDTO> findSupplierAll(SupplierDTO dto){
		
		return supplierReadService.findSupplierAll(dto);
		
	}

	public List<SupplierDTO> findByIdList(List<Long> ids){
		return supplierReadService.findByIdList(ids);
	}

	public Long insertSupplierWithTx(SupplierDTO dto){
		
		return supplierWriteService.insertSupplierWithTx(dto);
	}

	public int updateSupplierWithTx(SupplierDTO dto){
		
		return supplierWriteService.updateSupplierWithTx(dto);
	}

	public int deleteSupplierWithTx(SupplierDTO dto){
		
		return supplierWriteService.deleteSupplierWithTx(dto);
		
	}

}
	