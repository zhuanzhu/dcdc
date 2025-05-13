package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SupplierManage;
import com.egeo.components.product.facade.SupplierFacade;
import com.egeo.components.product.dto.SupplierDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("supplier")
public class SupplierManageImpl implements SupplierManage{

	
	@Resource(name="supplierFacade")
	private SupplierFacade supplierFacade;

	@Override
	public SupplierDTO findSupplierById(SupplierDTO dto) {
		return supplierFacade.findSupplierById(dto);
	}

	@Override
	public PageResult<SupplierDTO> findSupplierOfPage(SupplierDTO dto, Pagination page) {
		return supplierFacade.findSupplierOfPage(dto, page);
	}

	@Override
	public List<SupplierDTO> findSupplierAll(SupplierDTO dto) {
		return supplierFacade.findSupplierAll(dto);
	}

	@Override
	public Long insertSupplierWithTx(SupplierDTO dto) {
		return supplierFacade.insertSupplierWithTx(dto);
	}

	@Override
	public int updateSupplierWithTx(SupplierDTO dto) {
		return supplierFacade.updateSupplierWithTx(dto);
	}

	@Override
	public int deleteSupplierWithTx(SupplierDTO dto) {
		return supplierFacade.deleteSupplierWithTx(dto);
	}


}
	