package com.egeo.components.order.service.write.impl;

import com.egeo.components.order.converter.ChannelAddressConverter;
import com.egeo.components.order.dto.ChannelAddressDTO;
import com.egeo.components.order.manage.write.ChannelAddressWriteManage;
import com.egeo.components.order.po.ChannelAddressPO;
import com.egeo.components.order.service.write.ChannelAddressWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("channelAddressWriteService")
public class ChannelAddressWriteServiceImpl implements ChannelAddressWriteService {
	@Autowired
	private ChannelAddressWriteManage addressWriteManage;

	@Override
	public Long insertChannelAddressWithTx(ChannelAddressDTO dto) {
		ChannelAddressPO po = ChannelAddressConverter.toPO(dto);
		Long rt = addressWriteManage.insertChannelAddressWithTx(po);
		return rt;
	}

	@Override
	public int updateChannelAddressWithTx(ChannelAddressDTO dto) {
		ChannelAddressPO po = ChannelAddressConverter.toPO(dto);
		int rt = addressWriteManage.updateChannelAddressWithTx(po);
		return rt;
	}

	@Override
	public int deleteChannelAddressWithTx(ChannelAddressDTO dto) {
		ChannelAddressPO po = ChannelAddressConverter.toPO(dto);
		int rt = addressWriteManage.deleteChannelAddressWithTx(po);
		return rt;
	}
}
