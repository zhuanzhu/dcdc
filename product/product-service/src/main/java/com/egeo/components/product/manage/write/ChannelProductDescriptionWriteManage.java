package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ChannelProductDescriptionPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductDescriptionWriteManage {

    public Long insertChannelProductDescriptionWithTx(ChannelProductDescriptionPO po);

    public int updateChannelProductDescriptionWithTx(ChannelProductDescriptionPO po);

    public int deleteChannelProductDescriptionWithTx(ChannelProductDescriptionPO po);
}
