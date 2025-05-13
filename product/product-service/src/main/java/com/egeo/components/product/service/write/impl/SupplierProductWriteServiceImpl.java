package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SupplierProductWriteService;
import com.egeo.components.product.manage.write.SupplierProductWriteManage;
import com.egeo.components.product.converter.SupplierProductConverter;
import com.egeo.components.product.dto.SupplierProductDTO;
import com.egeo.components.product.po.SupplierProductPO;

@Service("supplierProductWriteService")
public class SupplierProductWriteServiceImpl  implements SupplierProductWriteService {
	@Autowired
	private SupplierProductWriteManage supplierProductWriteManage;

	@Override
	public Long insertSupplierProductWithTx(SupplierProductDTO dto) {
		SupplierProductPO po = SupplierProductConverter.toPO(dto);
		Long rt = supplierProductWriteManage.insertSupplierProductWithTx(po);		
		return rt;
	}

	@Override
	public int updateSupplierProductWithTx(SupplierProductDTO dto) {
		SupplierProductPO po = SupplierProductConverter.toPO(dto);
		int rt = supplierProductWriteManage.updateSupplierProductWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSupplierProductWithTx(SupplierProductDTO dto) {
		SupplierProductPO po = SupplierProductConverter.toPO(dto);
		int rt = supplierProductWriteManage.deleteSupplierProductWithTx(po);		
		return rt;
	}
}
	