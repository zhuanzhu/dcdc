package com.egeo.components.third.service.write.impl;

import com.egeo.components.third.converter.ChannelServiceConfigConverter;
import com.egeo.components.third.converter.ChannelServiceFieldConfigConverter;
import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;
import com.egeo.components.third.manage.write.ChannelServiceConfigWriteManage;
import com.egeo.components.third.manage.write.ChannelServiceFieldConfigWriteManage;
import com.egeo.components.third.po.ChannelServiceConfigPO;
import com.egeo.components.third.po.ChannelServiceFieldConfigPO;
import com.egeo.components.third.service.write.ChannelServiceConfigWriteService;
import com.egeo.components.third.service.write.ChannelServiceFieldConfigWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelServiceFieldConfigWriteService")
public class ChannelServiceFieldConfigWriteServiceImpl implements ChannelServiceFieldConfigWriteService {

    @Autowired
    private ChannelServiceFieldConfigWriteManage channelServiceFieldConfigWriteManage;
    @Override
    public Long insertChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto) {
        ChannelServiceFieldConfigPO po = ChannelServiceFieldConfigConverter.toPO(dto);
        Long rt = channelServiceFieldConfigWriteManage.insertChannelServiceFieldConfigWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto) {
        ChannelServiceFieldConfigPO po = ChannelServiceFieldConfigConverter.toPO(dto);
        int rt = channelServiceFieldConfigWriteManage.updateChannelServiceFieldConfigWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelServiceFieldConfigWithTx(ChannelServiceFieldConfigDTO dto) {
        ChannelServiceFieldConfigPO po = ChannelServiceFieldConfigConverter.toPO(dto);
        int rt = channelServiceFieldConfigWriteManage.deleteChannelServiceFieldConfigWithTx(po);
        return rt;
    }
}
