package com.egeo.components.product.service.read;

import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelProductBatchReadService {

    public ChannelProductBatchDTO findChannelProductBatchById(ChannelProductBatchDTO dto);

    public PageResult<ChannelProductBatchDTO> findChannelProductBatchOfPage(ChannelProductBatchDTO dto, Pagination page);

    public List<ChannelProductBatchDTO> findChannelProductBatchAll(ChannelProductBatchDTO dto);

    public PageResult<ChannelSupplierProductResponseVO> findChannelProductOfPage(ChannelSupplierProductRequestVO vo, Pagination page);

}
