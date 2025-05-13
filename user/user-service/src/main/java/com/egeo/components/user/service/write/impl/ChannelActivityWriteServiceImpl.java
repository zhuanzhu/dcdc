package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.ChannelActivityConverter;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.manage.write.ChannelActivityWriteManage;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.components.user.service.write.ChannelActivityWriteService;

@Service("channelActivityWriteService")
public class ChannelActivityWriteServiceImpl implements ChannelActivityWriteService {
	@Autowired
	private ChannelActivityWriteManage channelActivityWriteManage;

	@Override
	public Long insertChannelActivityWithTx(ChannelActivityDTO dto) {
		ChannelActivityPO po = ChannelActivityConverter.toPO(dto);
		Long rt = channelActivityWriteManage.insertChannelActivityWithTx(po);		
		return rt;
	}

	@Override
	public int updateChannelActivityWithTx(ChannelActivityDTO dto) {
		ChannelActivityPO po = ChannelActivityConverter.toPO(dto);
		int rt = channelActivityWriteManage.updateChannelActivityWithTx(po);		
		return rt;
	}

	@Override
	public int deleteChannelActivityWithTx(ChannelActivityDTO dto) {
		ChannelActivityPO po = ChannelActivityConverter.toPO(dto);
		int rt = channelActivityWriteManage.deleteChannelActivityWithTx(po);		
		return rt;
	}
}
	