package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ChannelProductBatchPO;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductBatchWriteManage {

    public Long insertChannelProductBatchWithTx(ChannelProductBatchPO po);

    public int updateChannelProductBatchWithTx(ChannelProductBatchPO po);

    public int deleteChannelProductBatchWithTx(ChannelProductBatchPO po);

    public int updateChannelBatchSkuState(List<String> skuIdList, String channelCode);
}
