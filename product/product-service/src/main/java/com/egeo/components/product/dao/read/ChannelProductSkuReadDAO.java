package com.egeo.components.product.dao.read;

import com.egeo.components.product.condition.ChannelProductAndSkuCondition;
import com.egeo.components.product.dto.ChannelProductAndSkuListDTO;
import com.egeo.components.product.po.ChannelProductSkuPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductSkuReadDAO extends BaseReadDAO<ChannelProductSkuPO> {

    List<ChannelProductSkuPO> findChannelProductSkuBySkuIds(@Param("skuList") List<String> skuList,@Param("channelCode")String channelCode);

    List<ChannelProductAndSkuCondition> getChannelProductAndSkuList(@Param("dto") ChannelProductAndSkuListDTO dto);

    List<ChannelProductAndSkuCondition> getChannelProductAndSkuListOfPage(@Param("dto") ChannelProductAndSkuListDTO dto, @Param("page") Pagination page);

    int getChannelProductAndSkuListCountOfPage(@Param("dto") ChannelProductAndSkuListDTO dto);

    List<ChannelProductSkuPO> findChannelProductSkuBySkuCodes(@Param("skuList") List<String> skuList,@Param("channelCode")String channelCode);

}
