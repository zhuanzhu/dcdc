package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.ChannelCategoryDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelCategoryWriteService {
    public Long insertChannelCategoryWithTx(ChannelCategoryDTO dto);

    public int updateChannelCategoryWithTx(ChannelCategoryDTO dto);

    public int deleteChannelCategoryWithTx(ChannelCategoryDTO dto);
}
