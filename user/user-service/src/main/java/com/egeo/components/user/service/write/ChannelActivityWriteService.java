package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.ChannelActivityDTO;


public interface ChannelActivityWriteService {

	public Long insertChannelActivityWithTx(ChannelActivityDTO dto);

	public int updateChannelActivityWithTx(ChannelActivityDTO dto);

	public int deleteChannelActivityWithTx(ChannelActivityDTO dto);
}
	