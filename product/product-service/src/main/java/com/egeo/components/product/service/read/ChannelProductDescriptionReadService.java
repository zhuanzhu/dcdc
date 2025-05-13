package com.egeo.components.product.service.read;

import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductDescriptionReadService {


    public ChannelProductDescriptionDTO findChannelProductDescriptionById(ChannelProductDescriptionDTO dto);

    public PageResult<ChannelProductDescriptionDTO> findChannelProductDescriptionOfPage(ChannelProductDescriptionDTO dto, Pagination page);

    public List<ChannelProductDescriptionDTO> findChannelProductDescriptionAll(ChannelProductDescriptionDTO dto);

}
