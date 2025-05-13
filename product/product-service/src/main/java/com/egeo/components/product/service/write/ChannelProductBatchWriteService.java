package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductBatchWriteService {
    public Long insertChannelProductBatchWithTx(ChannelProductBatchDTO dto);

    public int updateChannelProductBatchWithTx(ChannelProductBatchDTO dto);

    public int deleteChannelProductBatchWithTx(ChannelProductBatchDTO dto);

    public int updateChannelBatchSkuState(List<String> skuIdList, String channelCode);
}
