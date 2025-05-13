package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.ChannelCategoryConverter;
import com.egeo.components.product.converter.ChannelProductConverter;
import com.egeo.components.product.dto.ChannelCategoryDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.manage.write.ChannelCategoryWriteManage;
import com.egeo.components.product.manage.write.ChannelProductWriteManage;
import com.egeo.components.product.po.ChannelCategoryPO;
import com.egeo.components.product.po.ChannelProductPO;
import com.egeo.components.product.service.write.ChannelCategoryWriteService;
import com.egeo.components.product.service.write.ChannelProductWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelCategoryWriteService")
public class ChannelCategoryWriteServiceImpl implements ChannelCategoryWriteService {

    @Autowired
    private ChannelCategoryWriteManage channelCategoryWriteManage;
    @Override
    public Long insertChannelCategoryWithTx(ChannelCategoryDTO dto) {
        ChannelCategoryPO po = ChannelCategoryConverter.toPO(dto);
        Long rt = channelCategoryWriteManage.insertChannelCategoryWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelCategoryWithTx(ChannelCategoryDTO dto) {
        ChannelCategoryPO po = ChannelCategoryConverter.toPO(dto);
        int rt = channelCategoryWriteManage.updateChannelCategoryWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelCategoryWithTx(ChannelCategoryDTO dto) {
        ChannelCategoryPO po = ChannelCategoryConverter.toPO(dto);
        int rt = channelCategoryWriteManage.deleteChannelCategoryWithTx(po);
        return rt;
    }
}
