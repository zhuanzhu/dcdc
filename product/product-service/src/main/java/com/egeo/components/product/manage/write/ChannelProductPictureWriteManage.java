package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ChannelProductPicturePO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductPictureWriteManage {

    public Long insertChannelProductPictureWithTx(ChannelProductPicturePO po);

    public int updateChannelProductPictureWithTx(ChannelProductPicturePO po);

    public int deleteChannelProductPictureWithTx(ChannelProductPicturePO po);

    public int deleteByParaWithTx(ChannelProductPicturePO po);
}
