package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.ChannelProductDescriptionConverter;
import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.manage.write.ChannelProductDescriptionWriteManage;
import com.egeo.components.product.po.ChannelProductDescriptionPO;
import com.egeo.components.product.service.write.ChannelProductDescriptionWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelProductDescriptionWriteService")
public class ChannelProductDescriptionWriteServiceImpl implements ChannelProductDescriptionWriteService {



    @Autowired
    private ChannelProductDescriptionWriteManage channelProductDescriptionWriteManage;
    @Override
    public Long insertChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto) {
        ChannelProductDescriptionPO po = ChannelProductDescriptionConverter.toPO(dto);
        Long rt = channelProductDescriptionWriteManage.insertChannelProductDescriptionWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto) {
        ChannelProductDescriptionPO po = ChannelProductDescriptionConverter.toPO(dto);
        int rt = channelProductDescriptionWriteManage.updateChannelProductDescriptionWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelProductDescriptionWithTx(ChannelProductDescriptionDTO dto) {
        ChannelProductDescriptionPO po = ChannelProductDescriptionConverter.toPO(dto);
        int rt = channelProductDescriptionWriteManage.deleteChannelProductDescriptionWithTx(po);
        return rt;
    }
}
