package com.egeo.components.product.manage.read;

import com.egeo.components.product.condition.ChannelProductAndSkuCondition;
import com.egeo.components.product.dto.ChannelProductAndSkuListDTO;
import com.egeo.components.product.po.ChannelProductSkuPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductSkuReadManage {


    public ChannelProductSkuPO findChannelProductSkuById(ChannelProductSkuPO po);

    public PageResult<ChannelProductSkuPO> findChannelProductSkuOfPage(ChannelProductSkuPO po, Pagination page);

    public List<ChannelProductSkuPO> findChannelProductSkuAll(ChannelProductSkuPO po);

    public List<ChannelProductSkuPO> findChannelProductSkuBySkuIds(List<String> skuList,String channelCode);

    public PageResult<ChannelProductAndSkuCondition> getChannelProductAndSkuListOfPage(ChannelProductAndSkuListDTO dto, Pagination page);

    public List<ChannelProductSkuPO> findChannelProductSkuBySkuCodes(List<String> skuList,String channelCode);

}
