package com.egeo.components.product.dao.read;

import com.egeo.components.product.condition.ChannelProductPicCondition;
import com.egeo.components.product.po.ChannelProductPicturePO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductPictureReadDAO extends BaseReadDAO<ChannelProductPicturePO> {

    public List<ChannelProductPicturePO> findChannelPicByProductIds(@Param("productIds")List<String> productIds,@Param("channelCode") String channelCode);
}
