package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.ChannelAddressDTO;


public interface ChannelAddressWriteService {

	public Long insertChannelAddressWithTx(ChannelAddressDTO dto);

	public int updateChannelAddressWithTx(ChannelAddressDTO dto);

	public int deleteChannelAddressWithTx(ChannelAddressDTO dto);
}
