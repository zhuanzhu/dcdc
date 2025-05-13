package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.AddressPO;


public interface AddressWriteManage {

	Long insertAddressWithTx(AddressPO po);

	int updateAddressWithTx(AddressPO po);

	int deleteAddressWithTx(AddressPO po);
}
	