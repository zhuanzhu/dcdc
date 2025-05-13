package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SupplierReadService;
import com.egeo.components.product.manage.read.SupplierReadManage;
import com.egeo.components.product.converter.SupplierConverter;
import com.egeo.components.product.dto.SupplierDTO;
import com.egeo.components.product.po.SupplierPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("supplierReadService")
public class SupplierReadServiceImpl  implements SupplierReadService {
	@Autowired
	private SupplierReadManage supplierReadManage;

	@Override
	public SupplierDTO findSupplierById(SupplierDTO dto) {
		SupplierPO po = SupplierConverter.toPO(dto);
		SupplierPO list = supplierReadManage.findSupplierById(po);		
		return SupplierConverter.toDTO(list);
	}

	@Override
	public PageResult<SupplierDTO> findSupplierOfPage(SupplierDTO dto, Pagination page) {
		SupplierPO po = SupplierConverter.toPO(dto);
        PageResult<SupplierPO> pageResult = supplierReadManage.findSupplierOfPage(po, page);
        
        List<SupplierDTO> list = SupplierConverter.toDTO(pageResult.getList());
        PageResult<SupplierDTO> result = new PageResult<SupplierDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SupplierDTO> findSupplierAll(SupplierDTO dto) {
		SupplierPO po = SupplierConverter.toPO(dto);
		List<SupplierPO> list = supplierReadManage.findSupplierAll(po);		
		return SupplierConverter.toDTO(list);
	}

	@Override
	public List<SupplierDTO> findByIdList(List<Long> ids) {
		return SupplierConverter.toDTO(supplierReadManage.findByIdList(ids));
	}
}
	