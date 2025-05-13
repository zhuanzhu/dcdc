package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.AddressWriteService;
import com.egeo.components.order.manage.write.AddressWriteManage;
import com.egeo.components.order.converter.AddressConverter;
import com.egeo.components.order.dto.AddressDTO;
import com.egeo.components.order.po.AddressPO;

@Service("addressWriteService")
public class AddressWriteServiceImpl  implements AddressWriteService {
	@Autowired
	private AddressWriteManage addressWriteManage;

	@Override
	public Long insertAddressWithTx(AddressDTO dto) {
		AddressPO po = AddressConverter.toPO(dto);
		Long rt = addressWriteManage.insertAddressWithTx(po);		
		return rt;
	}

	@Override
	public int updateAddressWithTx(AddressDTO dto) {
		AddressPO po = AddressConverter.toPO(dto);
		int rt = addressWriteManage.updateAddressWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAddressWithTx(AddressDTO dto) {
		AddressPO po = AddressConverter.toPO(dto);
		int rt = addressWriteManage.deleteAddressWithTx(po);		
		return rt;
	}
}
	