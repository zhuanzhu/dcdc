package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ChannelProductTextPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductTextWriteManage {


    public Long insertChannelProductTextWithTx(ChannelProductTextPO po);

    public int updateChannelProductTextWithTx(ChannelProductTextPO po);

    public int deleteChannelProductTextWithTx(ChannelProductTextPO po);
}
