package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.ChannelProductSkuConverter;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.manage.write.ChannelProductSkuWriteManage;
import com.egeo.components.product.po.ChannelProductSkuPO;
import com.egeo.components.product.service.write.ChannelProductSkuWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelProductSkuWriteService")
public class ChannelProductSkuWriteServiceImpl implements ChannelProductSkuWriteService {


    @Autowired
    private ChannelProductSkuWriteManage channelProductSkuWriteManage;
    @Override
    public Long insertChannelProductSkuWithTx(ChannelProductSkuDTO dto) {
        ChannelProductSkuPO po = ChannelProductSkuConverter.toPO(dto);
        Long rt = channelProductSkuWriteManage.insertChannelProductSkuWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelProductSkuWithTx(ChannelProductSkuDTO dto) {
        ChannelProductSkuPO po = ChannelProductSkuConverter.toPO(dto);
        int rt = channelProductSkuWriteManage.updateChannelProductSkuWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelProductSkuWithTx(ChannelProductSkuDTO dto) {
        ChannelProductSkuPO po = ChannelProductSkuConverter.toPO(dto);
        int rt = channelProductSkuWriteManage.deleteChannelProductSkuWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelProductSkuStateWithTx(List<String> skuIdList,String channelCode){
        int rt = channelProductSkuWriteManage.updateChannelProductSkuStateWithTx(skuIdList,channelCode);
        return rt;
    }
}
