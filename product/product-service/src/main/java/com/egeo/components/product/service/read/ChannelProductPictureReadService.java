package com.egeo.components.product.service.read;

import com.egeo.components.product.condition.ChannelProductPicCondition;
import com.egeo.components.product.dto.channel.ChannelProductPictureDTO;
import com.egeo.components.product.po.ChannelProductPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductPictureReadService {


    public ChannelProductPictureDTO findChannelProductPictureById(ChannelProductPictureDTO dto);

    public PageResult<ChannelProductPictureDTO> findChannelProductPictureOfPage(ChannelProductPictureDTO dto, Pagination page);

    public List<ChannelProductPictureDTO> findChannelProductPictureAll(ChannelProductPictureDTO dto);

    public List<ChannelProductPicturePO> findChannelPicByProductIds(List<String> productIds, String channelCode);

}
