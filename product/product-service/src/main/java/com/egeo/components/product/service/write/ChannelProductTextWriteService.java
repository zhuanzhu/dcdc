package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.channel.ChannelProductTextDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductTextWriteService {
    public Long insertChannelProductTextWithTx(ChannelProductTextDTO dto);

    public int updateChannelProductTextWithTx(ChannelProductTextDTO dto);

    public int deleteChannelProductTextWithTx(ChannelProductTextDTO dto);
}
