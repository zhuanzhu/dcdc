package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;


public interface ChannelPriceLimitUploadWriteService {

	public Long insertChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto);

	public int updateChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto);

	public int deleteChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto);
}
