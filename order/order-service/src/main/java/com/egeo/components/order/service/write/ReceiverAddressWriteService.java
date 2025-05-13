package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.ReceiverAddressDTO;


public interface ReceiverAddressWriteService {

	public Long insertReceiverAddressWithTx(ReceiverAddressDTO dto);

	public int updateReceiverAddressWithTx(ReceiverAddressDTO dto);

	public int deleteReceiverAddressWithTx(ReceiverAddressDTO dto);

	public void modifyReceiverAddress(Long soChildId, ReceiverAddressDTO receiverAddressDTO);
}
	