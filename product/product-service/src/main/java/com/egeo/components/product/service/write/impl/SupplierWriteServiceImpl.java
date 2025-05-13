package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.SupplierWriteService;
import com.egeo.components.product.manage.write.SupplierWriteManage;
import com.egeo.components.product.converter.SupplierConverter;
import com.egeo.components.product.dto.SupplierDTO;
import com.egeo.components.product.po.SupplierPO;

@Service("supplierWriteService")
public class SupplierWriteServiceImpl  implements SupplierWriteService {
	@Autowired
	private SupplierWriteManage supplierWriteManage;

	@Override
	public Long insertSupplierWithTx(SupplierDTO dto) {
		SupplierPO po = SupplierConverter.toPO(dto);
		Long rt = supplierWriteManage.insertSupplierWithTx(po);		
		return rt;
	}

	@Override
	public int updateSupplierWithTx(SupplierDTO dto) {
		SupplierPO po = SupplierConverter.toPO(dto);
		int rt = supplierWriteManage.updateSupplierWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSupplierWithTx(SupplierDTO dto) {
		SupplierPO po = SupplierConverter.toPO(dto);
		int rt = supplierWriteManage.deleteSupplierWithTx(po);		
		return rt;
	}
}
	