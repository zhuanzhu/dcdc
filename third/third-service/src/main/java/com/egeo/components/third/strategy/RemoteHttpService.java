package com.egeo.components.third.strategy;

import com.egeo.components.third.dto.ChannelServiceConfigDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface RemoteHttpService {

    public String getChannelServiceMethod();

    String send(ChannelServiceConfigDTO dto, Object paramObject);
}
