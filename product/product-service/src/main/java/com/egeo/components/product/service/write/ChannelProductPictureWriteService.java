package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.channel.ChannelProductPictureDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductPictureWriteService {
    public Long insertChannelProductPictureWithTx(ChannelProductPictureDTO dto);

    public int updateChannelProductPictureWithTx(ChannelProductPictureDTO dto);

    public int deleteChannelProductPictureWithTx(ChannelProductPictureDTO dto);

    public int deleteByParaWithTx(ChannelProductPictureDTO dto);
}
