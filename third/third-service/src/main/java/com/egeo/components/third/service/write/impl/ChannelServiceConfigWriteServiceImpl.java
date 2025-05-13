package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.ChannelServiceConfigConverter;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.manage.write.ChannelServiceConfigWriteManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.service.write.ChannelServiceConfigWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelServiceConfigWriteService")
public class ChannelServiceConfigWriteServiceImpl implements ChannelServiceConfigWriteService {

    @Autowired
    private ChannelServiceConfigWriteManage channelServiceConfigWriteManage;
    @Override
    public Long insertChannelServiceConfigWithTx(ChannelServiceConfigDTO dto) {
        ChannelServiceConfigPO po = ChannelServiceConfigConverter.toPO(dto);
        Long rt = channelServiceConfigWriteManage.insertChannelServiceConfigWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelServiceConfigWithTx(ChannelServiceConfigDTO dto) {
        ChannelServiceConfigPO po = ChannelServiceConfigConverter.toPO(dto);
        int rt = channelServiceConfigWriteManage.updateChannelServiceConfigWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelServiceConfigWithTx(ChannelServiceConfigDTO dto) {
        ChannelServiceConfigPO po = ChannelServiceConfigConverter.toPO(dto);
        int rt = channelServiceConfigWriteManage.deleteChannelServiceConfigWithTx(po);
        return rt;
    }
}
