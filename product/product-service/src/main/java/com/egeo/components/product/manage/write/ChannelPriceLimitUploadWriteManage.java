package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ChannelPriceLimitUploadPO;


public interface ChannelPriceLimitUploadWriteManage {

	Long insertChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadPO po);

	int updateChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadPO po);

	int deleteChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadPO po);
}
