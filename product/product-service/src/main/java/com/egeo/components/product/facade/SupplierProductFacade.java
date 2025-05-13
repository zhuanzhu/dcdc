package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SupplierProductReadService;
import com.egeo.components.product.service.write.SupplierProductWriteService;
import com.egeo.components.product.dto.SupplierProductDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SupplierProductFacade {
	
	@Resource
	private SupplierProductReadService supplierProductReadService;
	
	@Resource
	private SupplierProductWriteService supplierProductWriteService;
	
	
	public SupplierProductDTO findSupplierProductById(SupplierProductDTO dto){
		
		return supplierProductReadService.findSupplierProductById(dto);
	}

	public PageResult<SupplierProductDTO> findSupplierProductOfPage(SupplierProductDTO dto,Pagination page){
		
		return supplierProductReadService.findSupplierProductOfPage(dto, page);
		
	}

	public List<SupplierProductDTO> findSupplierProductAll(SupplierProductDTO dto){
		
		return supplierProductReadService.findSupplierProductAll(dto);
		
	}

	public Long insertSupplierProductWithTx(SupplierProductDTO dto){
		
		return supplierProductWriteService.insertSupplierProductWithTx(dto);
	}

	public int updateSupplierProductWithTx(SupplierProductDTO dto){
		
		return supplierProductWriteService.updateSupplierProductWithTx(dto);
	}

	public int deleteSupplierProductWithTx(SupplierProductDTO dto){
		
		return supplierProductWriteService.deleteSupplierProductWithTx(dto);
		
	}

}
	