package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SupplierProductReadService;
import com.egeo.components.product.manage.read.SupplierProductReadManage;
import com.egeo.components.product.converter.SupplierProductConverter;
import com.egeo.components.product.dto.SupplierProductDTO;
import com.egeo.components.product.po.SupplierProductPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("supplierProductReadService")
public class SupplierProductReadServiceImpl  implements SupplierProductReadService {
	@Autowired
	private SupplierProductReadManage supplierProductReadManage;

	@Override
	public SupplierProductDTO findSupplierProductById(SupplierProductDTO dto) {
		SupplierProductPO po = SupplierProductConverter.toPO(dto);
		SupplierProductPO list = supplierProductReadManage.findSupplierProductById(po);		
		return SupplierProductConverter.toDTO(list);
	}

	@Override
	public PageResult<SupplierProductDTO> findSupplierProductOfPage(SupplierProductDTO dto, Pagination page) {
		SupplierProductPO po = SupplierProductConverter.toPO(dto);
        PageResult<SupplierProductPO> pageResult = supplierProductReadManage.findSupplierProductOfPage(po, page);
        
        List<SupplierProductDTO> list = SupplierProductConverter.toDTO(pageResult.getList());
        PageResult<SupplierProductDTO> result = new PageResult<SupplierProductDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SupplierProductDTO> findSupplierProductAll(SupplierProductDTO dto) {
		SupplierProductPO po = SupplierProductConverter.toPO(dto);
		List<SupplierProductPO> list = supplierProductReadManage.findSupplierProductAll(po);		
		return SupplierProductConverter.toDTO(list);
	}
}
	