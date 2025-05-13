package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.ChannelProductConverter;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.manage.write.ChannelProductWriteManage;
import com.egeo.components.product.po.ChannelProductPO;
import com.egeo.components.product.service.write.ChannelProductWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelProductWriteService")
public class ChannelProductWriteServiceImpl implements ChannelProductWriteService {



    @Autowired
    private ChannelProductWriteManage channelProductWriteManage;
    @Override
    public Long insertChannelProductWithTx(ChannelProductDTO dto) {
        ChannelProductPO po = ChannelProductConverter.toPO(dto);
        Long rt = channelProductWriteManage.insertChannelProductWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelProductWithTx(ChannelProductDTO dto) {
        ChannelProductPO po = ChannelProductConverter.toPO(dto);
        int rt = channelProductWriteManage.updateChannelProductWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelProductWithTx(ChannelProductDTO dto) {
        ChannelProductPO po = ChannelProductConverter.toPO(dto);
        int rt = channelProductWriteManage.deleteChannelProductWithTx(po);
        return rt;
    }
}
