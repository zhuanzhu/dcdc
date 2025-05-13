package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.ReceiverAddressWriteService;
import com.egeo.components.order.manage.write.ReceiverAddressWriteManage;
import com.egeo.components.order.converter.ReceiverAddressConverter;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.po.ReceiverAddressPO;

@Service("receiverAddressWriteService")
public class ReceiverAddressWriteServiceImpl  implements ReceiverAddressWriteService {
	@Autowired
	private ReceiverAddressWriteManage receiverAddressWriteManage;

	@Override
	public Long insertReceiverAddressWithTx(ReceiverAddressDTO dto) {
		ReceiverAddressPO po = ReceiverAddressConverter.toPO(dto);
		Long rt = receiverAddressWriteManage.insertReceiverAddressWithTx(po);		
		return rt;
	}

	@Override
	public int updateReceiverAddressWithTx(ReceiverAddressDTO dto) {
		ReceiverAddressPO po = ReceiverAddressConverter.toPO(dto);
		int rt = receiverAddressWriteManage.updateReceiverAddressWithTx(po);		
		return rt;
	}

	@Override
	public int deleteReceiverAddressWithTx(ReceiverAddressDTO dto) {
		ReceiverAddressPO po = ReceiverAddressConverter.toPO(dto);
		int rt = receiverAddressWriteManage.deleteReceiverAddressWithTx(po);		
		return rt;
	}

	@Override
	public void modifyReceiverAddress(Long soChildId, ReceiverAddressDTO receiverAddressDTO) {
		ReceiverAddressPO receiverAddressPO = ReceiverAddressConverter.toPO(receiverAddressDTO);
		receiverAddressWriteManage.modifyReceiverAddressWithTx(soChildId,receiverAddressPO);
	}
}
	