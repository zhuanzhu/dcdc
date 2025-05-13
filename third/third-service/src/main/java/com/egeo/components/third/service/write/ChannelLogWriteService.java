package com.egeo.components.third.service.write;

import com.egeo.components.third.dto.ChannelLogDTO;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:28
 * @Version V1.0
 **/
public interface ChannelLogWriteService {

    public Long insertChannelLogWithTx(ChannelLogDTO dto);

    public int updateChannelLogWithTx(ChannelLogDTO dto);

    public int deleteChannelLogWithTx(ChannelLogDTO dto);
}
