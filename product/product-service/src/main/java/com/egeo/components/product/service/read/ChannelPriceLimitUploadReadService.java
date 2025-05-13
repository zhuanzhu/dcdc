package com.egeo.components.product.service.read;


import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface ChannelPriceLimitUploadReadService {

	public ChannelPriceLimitUploadDTO findChannelPriceLimitUploadById(ChannelPriceLimitUploadDTO dto);

	public PageResult<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadOfPage(ChannelPriceLimitUploadDTO dto,Pagination page);

	public List<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadAll(ChannelPriceLimitUploadDTO dto);
}
