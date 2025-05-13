package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.ReceiverAddressPO;


public interface ReceiverAddressWriteManage {

	Long insertReceiverAddressWithTx(ReceiverAddressPO po);

	int updateReceiverAddressWithTx(ReceiverAddressPO po);

	int deleteReceiverAddressWithTx(ReceiverAddressPO po);

	void modifyReceiverAddressWithTx(Long soChildId, ReceiverAddressPO receiverAddressPO);
}
	