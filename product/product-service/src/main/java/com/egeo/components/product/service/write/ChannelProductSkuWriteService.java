package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductSkuWriteService {

    public Long insertChannelProductSkuWithTx(ChannelProductSkuDTO dto);

    public int updateChannelProductSkuWithTx(ChannelProductSkuDTO dto);

    public int deleteChannelProductSkuWithTx(ChannelProductSkuDTO dto);

    public int updateChannelProductSkuStateWithTx(List<String> skuIdList, String channelCode);

}
