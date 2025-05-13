package com.egeo.components.product.service.write.impl;

import com.egeo.components.product.converter.ChannelProductPictureConverter;
import com.egeo.components.product.dto.channel.ChannelProductPictureDTO;
import com.egeo.components.product.manage.write.ChannelProductPictureWriteManage;
import com.egeo.components.product.po.ChannelProductPicturePO;
import com.egeo.components.product.service.write.ChannelProductPictureWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelProductPictureWriteService")
public class ChannelProductPictureWriteServiceImpl implements ChannelProductPictureWriteService {


    @Autowired
    private ChannelProductPictureWriteManage channelProductPictureWriteManage;
    @Override
    public Long insertChannelProductPictureWithTx(ChannelProductPictureDTO dto) {
        ChannelProductPicturePO po = ChannelProductPictureConverter.toPO(dto);
        Long rt = channelProductPictureWriteManage.insertChannelProductPictureWithTx(po);
        return rt;
    }

    @Override
    public int updateChannelProductPictureWithTx(ChannelProductPictureDTO dto) {
        ChannelProductPicturePO po = ChannelProductPictureConverter.toPO(dto);
        int rt = channelProductPictureWriteManage.updateChannelProductPictureWithTx(po);
        return rt;
    }

    @Override
    public int deleteChannelProductPictureWithTx(ChannelProductPictureDTO dto) {
        ChannelProductPicturePO po = ChannelProductPictureConverter.toPO(dto);
        int rt = channelProductPictureWriteManage.deleteChannelProductPictureWithTx(po);
        return rt;
    }

    @Override
    public int deleteByParaWithTx(ChannelProductPictureDTO dto) {
        ChannelProductPicturePO po = ChannelProductPictureConverter.toPO(dto);
        int rt = channelProductPictureWriteManage.deleteByParaWithTx(po);
        return rt;
    }
}
