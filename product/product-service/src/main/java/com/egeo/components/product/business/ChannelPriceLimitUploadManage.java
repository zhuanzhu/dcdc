package com.egeo.components.product.business;

import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface ChannelPriceLimitUploadManage {

	public ChannelPriceLimitUploadDTO findChannelPriceLimitUploadById(ChannelPriceLimitUploadDTO dto);

	public PageResult<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadOfPage(ChannelPriceLimitUploadDTO dto,Pagination page);

	public List<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadAll(ChannelPriceLimitUploadDTO dto);

	Long insertChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto);

	int updateChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto);

	int deleteChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto);
}
