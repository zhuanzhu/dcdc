package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.AddressDTO;


public interface AddressWriteService {

	public Long insertAddressWithTx(AddressDTO dto);

	public int updateAddressWithTx(AddressDTO dto);

	public int deleteAddressWithTx(AddressDTO dto);
}
	