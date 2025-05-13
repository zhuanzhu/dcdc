package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductDescriptionWriteService {
    public Long insertChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto);

    public int updateChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto);

    public int deleteChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto);
}
