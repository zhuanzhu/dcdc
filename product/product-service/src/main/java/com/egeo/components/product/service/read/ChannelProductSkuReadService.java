package com.egeo.components.product.service.read;

import com.egeo.components.product.condition.ChannelProductAndSkuCondition;
import com.egeo.components.product.dto.ChannelProductAndSkuListDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductSkuReadService {


    public ChannelProductSkuDTO findChannelProductSkuById(ChannelProductSkuDTO dto);

    public PageResult<ChannelProductSkuDTO> findChannelProductSkuOfPage(ChannelProductSkuDTO dto, Pagination page);

    public List<ChannelProductSkuDTO> findChannelProductSkuAll(ChannelProductSkuDTO dto);

    public List<ChannelProductSkuDTO> findChannelProductSkuBySkuIds(List<String> skuList,String channelCode);

    public PageResult<ChannelProductAndSkuCondition> getChannelProductAndSkuListOfPage(ChannelProductAndSkuListDTO dto, Pagination page);

    public List<ChannelProductSkuDTO> findChannelProductSkuBySkuCodes(List<String> skuList,String channelCode);
}
