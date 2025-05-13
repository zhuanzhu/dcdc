package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.ChannelProductBatchConverter;
import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.manage.write.ChannelProductBatchWriteManage;
import com.egeo.components.product.po.ChannelProductBatchPO;
import com.egeo.components.product.service.write.ChannelProductBatchWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelProductBatchWriteService")
public class ChannelProductBatchWriteServiceImpl implements ChannelProductBatchWriteService {



    @Autowired
    private ChannelProductBatchWriteManage channelProductBatchWriteManage;
    @Override
    public Long insertChannelProductBatchWithTx(ChannelProductBatchDTO dto) {
        ChannelProductBatchPO po = ChannelProductBatchConverter.toPO(dto);
        Long rt = channelProductBatchWriteManage.insertChannelProductBatchWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelProductBatchWithTx(ChannelProductBatchDTO dto) {
        ChannelProductBatchPO po = ChannelProductBatchConverter.toPO(dto);
        int rt = channelProductBatchWriteManage.updateChannelProductBatchWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelProductBatchWithTx(ChannelProductBatchDTO dto) {
        ChannelProductBatchPO po = ChannelProductBatchConverter.toPO(dto);
        int rt = channelProductBatchWriteManage.deleteChannelProductBatchWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelBatchSkuState(List<String> skuIdList, String channelCode){

        return channelProductBatchWriteManage.updateChannelBatchSkuState(skuIdList,channelCode);
    }
}
