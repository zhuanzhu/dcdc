package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.ChannelConverter;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.manage.write.ChannelWriteManage;
import com.egeo.components.user.po.ChannelPO;
import com.egeo.components.user.service.write.ChannelWriteService;

@Service("channelWriteService")
public class ChannelWriteServiceImpl implements ChannelWriteService {
	@Autowired
	private ChannelWriteManage channelWriteManage;

	@Override
	public Long insertChannelWithTx(ChannelDTO dto) {
		ChannelPO po = ChannelConverter.toPO(dto);
		Long rt = channelWriteManage.insertChannelWithTx(po);		
		return rt;
	}

	@Override
	public int updateChannelWithTx(ChannelDTO dto) {
		ChannelPO po = ChannelConverter.toPO(dto);
		int rt = channelWriteManage.updateChannelWithTx(po);		
		return rt;
	}

	@Override
	public int deleteChannelWithTx(ChannelDTO dto) {
		ChannelPO po = ChannelConverter.toPO(dto);
		int rt = channelWriteManage.deleteChannelWithTx(po);		
		return rt;
	}

}
	