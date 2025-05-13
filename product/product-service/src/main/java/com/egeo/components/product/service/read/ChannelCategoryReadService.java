package com.egeo.components.product.service.read;

import com.egeo.components.product.dto.ChannelCategoryDTO;
import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelCategoryReadService {

    public ChannelCategoryDTO findChannelCategoryById(ChannelCategoryDTO dto);

    public PageResult<ChannelCategoryDTO> findChannelCategoryOfPage(ChannelCategoryDTO dto, Pagination page);

    public List<ChannelCategoryDTO> findChannelCategoryAll(ChannelCategoryDTO dto);

}
