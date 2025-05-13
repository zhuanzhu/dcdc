package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.ChannelDTO;


public interface ChannelWriteService {

	public Long insertChannelWithTx(ChannelDTO dto);

	public int updateChannelWithTx(ChannelDTO dto);

	public int deleteChannelWithTx(ChannelDTO dto);

}
	