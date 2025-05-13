package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelServiceFieldConfigWriteService {

    public Long insertChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto);

    public int updateChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto);

    public int deleteChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto);
}
