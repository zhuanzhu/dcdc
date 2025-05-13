package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.ChannelPriceLimitUploadConverter;
import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;
import com.egeo.components.product.manage.write.ChannelPriceLimitUploadWriteManage;
import com.egeo.components.product.po.ChannelPriceLimitUploadPO;
import com.egeo.components.product.service.write.ChannelPriceLimitUploadWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("channelPriceLimitUploadWriteService")
public class ChannelPriceLimitUploadWriteServiceImpl implements ChannelPriceLimitUploadWriteService {
	@Autowired
	private ChannelPriceLimitUploadWriteManage channelPriceLimitUploadWriteManage;

	@Override
	public Long insertChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto) {
		ChannelPriceLimitUploadPO po = ChannelPriceLimitUploadConverter.toPO(dto);
		Long rt = channelPriceLimitUploadWriteManage.insertChannelPriceLimitUploadWithTx(po);
		return rt;
	}

	@Override
	public int updateChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto) {
		ChannelPriceLimitUploadPO po = ChannelPriceLimitUploadConverter.toPO(dto);
		int rt = channelPriceLimitUploadWriteManage.updateChannelPriceLimitUploadWithTx(po);
		return rt;
	}

	@Override
	public int deleteChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto) {
		ChannelPriceLimitUploadPO po = ChannelPriceLimitUploadConverter.toPO(dto);
		int rt = channelPriceLimitUploadWriteManage.deleteChannelPriceLimitUploadWithTx(po);
		return rt;
	}
}
