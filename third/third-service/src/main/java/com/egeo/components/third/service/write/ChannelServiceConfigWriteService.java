package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.ChannelServiceConfigDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelServiceConfigWriteService {

    public Long insertChannelServiceConfigWithTx(ChannelServiceConfigDTO dto);

    public int updateChannelServiceConfigWithTx(ChannelServiceConfigDTO dto);

    public int deleteChannelServiceConfigWithTx(ChannelServiceConfigDTO dto);
}
