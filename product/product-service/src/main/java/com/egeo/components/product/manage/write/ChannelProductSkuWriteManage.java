package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ChannelProductSkuPO;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductSkuWriteManage {

    public Long insertChannelProductSkuWithTx(ChannelProductSkuPO po);

    public int updateChannelProductSkuWithTx(ChannelProductSkuPO po);

    public int deleteChannelProductSkuWithTx(ChannelProductSkuPO po);

    public int updateChannelProductSkuStateWithTx(List<String> skuIdList,String channelCode);
}
