package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SupplierProductManage;
import com.egeo.components.product.facade.SupplierProductFacade;
import com.egeo.components.product.dto.SupplierProductDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("supplierProduct")
public class SupplierProductManageImpl implements SupplierProductManage{

	
	@Resource(name="supplierProductFacade")
	private SupplierProductFacade supplierProductFacade;

	@Override
	public SupplierProductDTO findSupplierProductById(SupplierProductDTO dto) {
		return supplierProductFacade.findSupplierProductById(dto);
	}

	@Override
	public PageResult<SupplierProductDTO> findSupplierProductOfPage(SupplierProductDTO dto, Pagination page) {
		return supplierProductFacade.findSupplierProductOfPage(dto, page);
	}

	@Override
	public List<SupplierProductDTO> findSupplierProductAll(SupplierProductDTO dto) {
		return supplierProductFacade.findSupplierProductAll(dto);
	}

	@Override
	public Long insertSupplierProductWithTx(SupplierProductDTO dto) {
		return supplierProductFacade.insertSupplierProductWithTx(dto);
	}

	@Override
	public int updateSupplierProductWithTx(SupplierProductDTO dto) {
		return supplierProductFacade.updateSupplierProductWithTx(dto);
	}

	@Override
	public int deleteSupplierProductWithTx(SupplierProductDTO dto) {
		return supplierProductFacade.deleteSupplierProductWithTx(dto);
	}


}
	