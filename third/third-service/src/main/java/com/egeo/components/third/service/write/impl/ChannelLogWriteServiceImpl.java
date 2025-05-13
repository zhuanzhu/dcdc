package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.ChannelLogConverter;
import com.egeo.components.third.dto.ChannelLogDTO;
import com.egeo.components.third.manage.write.ChannelLogWriteManage;
import com.egeo.components.third.po.ChannelLogPO;
import com.egeo.components.third.service.write.ChannelLogWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:28
 * @Version V1.0
 **/
@Service("channelLogWriteService")
public class ChannelLogWriteServiceImpl implements ChannelLogWriteService {

    @Autowired
    private ChannelLogWriteManage channelLogWriteManage;
    @Override
    public Long insertChannelLogWithTx(ChannelLogDTO dto) {
        ChannelLogPO po = ChannelLogConverter.toPO(dto);
        Long rt = channelLogWriteManage.insertChannelLogWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelLogWithTx(ChannelLogDTO dto) {
        ChannelLogPO po = ChannelLogConverter.toPO(dto);
        int rt = channelLogWriteManage.updateChannelLogWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelLogWithTx(ChannelLogDTO dto) {
        ChannelLogPO po = ChannelLogConverter.toPO(dto);
        int rt = channelLogWriteManage.deleteChannelLogWithTx(po);
        return rt;
    }
}
