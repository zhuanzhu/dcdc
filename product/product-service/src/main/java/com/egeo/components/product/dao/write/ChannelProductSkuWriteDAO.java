package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.ChannelProductSkuPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductSkuWriteDAO extends BaseWriteDAO<ChannelProductSkuPO> {

    public int updateChannelProductSkuState(@Param("skuIdList")List<String> skuIdList,@Param("channelCode") String channelCode);
}
