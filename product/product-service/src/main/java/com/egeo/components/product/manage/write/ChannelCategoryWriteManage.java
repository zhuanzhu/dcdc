package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ChannelCategoryPO;
import com.egeo.components.product.po.ChannelProductPO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelCategoryWriteManage {

    public Long insertChannelCategoryWithTx(ChannelCategoryPO po);

    public int updateChannelCategoryWithTx(ChannelCategoryPO po);

    public int deleteChannelCategoryWithTx(ChannelCategoryPO po);
}
