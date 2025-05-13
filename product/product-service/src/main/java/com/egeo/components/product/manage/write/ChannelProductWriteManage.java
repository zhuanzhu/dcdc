package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ChannelProductPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductWriteManage {

    public Long insertChannelProductWithTx(ChannelProductPO po);

    public int updateChannelProductWithTx(ChannelProductPO po);

    public int deleteChannelProductWithTx(ChannelProductPO po);
}
