package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.ChannelProductTextConverter;
import com.egeo.components.product.dto.channel.ChannelProductTextDTO;
import com.egeo.components.product.manage.write.ChannelProductTextWriteManage;
import com.egeo.components.product.po.ChannelProductTextPO;
import com.egeo.components.product.service.write.ChannelProductTextWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelProductTextWriteService")
public class ChannelProductTextWriteServiceImpl implements ChannelProductTextWriteService {



    @Autowired
    private ChannelProductTextWriteManage channelProductTextWriteManage;
    @Override
    public Long insertChannelProductTextWithTx(ChannelProductTextDTO dto) {
        ChannelProductTextPO po = ChannelProductTextConverter.toPO(dto);
        Long rt = channelProductTextWriteManage.insertChannelProductTextWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelProductTextWithTx(ChannelProductTextDTO dto) {
        ChannelProductTextPO po = ChannelProductTextConverter.toPO(dto);
        int rt = channelProductTextWriteManage.updateChannelProductTextWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelProductTextWithTx(ChannelProductTextDTO dto) {
        ChannelProductTextPO po = ChannelProductTextConverter.toPO(dto);
        int rt = channelProductTextWriteManage.deleteChannelProductTextWithTx(po);
        return rt;
    }
}
