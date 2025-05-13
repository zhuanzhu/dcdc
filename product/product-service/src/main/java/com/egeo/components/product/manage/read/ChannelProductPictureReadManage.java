package com.egeo.components.product.manage.read;

import com.egeo.components.product.condition.ChannelProductPicCondition;
import com.egeo.components.product.po.ChannelProductPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductPictureReadManage {

    public ChannelProductPicturePO findChannelProductPictureById(ChannelProductPicturePO po);

    public PageResult<ChannelProductPicturePO> findChannelProductPictureOfPage(ChannelProductPicturePO po, Pagination page);

    public List<ChannelProductPicturePO> findChannelProductPictureAll(ChannelProductPicturePO po);

    public List<ChannelProductPicturePO> findChannelPicByProductIds(List<String> productIds, String channelCode);

}
