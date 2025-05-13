package com.egeo.components.product.service.read;

import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductReadService {

    public ChannelProductDTO findChannelProductById(ChannelProductDTO dto);

    public PageResult<ChannelProductDTO> findChannelProductOfPage(ChannelProductDTO dto, Pagination page);

    public List<ChannelProductDTO> findChannelProductAll(ChannelProductDTO dto);

}
