package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.channel.ChannelProductDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductWriteService {
    public Long insertChannelProductWithTx(ChannelProductDTO dto);

    public int updateChannelProductWithTx(ChannelProductDTO dto);

    public int deleteChannelProductWithTx(ChannelProductDTO dto);
}
