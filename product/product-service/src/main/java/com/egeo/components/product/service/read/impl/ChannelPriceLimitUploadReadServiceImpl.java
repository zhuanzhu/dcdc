package com.egeo.components.product.service.read.impl;

import com.egeo.components.product.converter.ChannelPriceLimitUploadConverter;
import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;
import com.egeo.components.product.manage.read.ChannelPriceLimitUploadReadManage;
import com.egeo.components.product.po.ChannelPriceLimitUploadPO;
import com.egeo.components.product.service.read.ChannelPriceLimitUploadReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("channelPriceLimitUploadReadService")
public class ChannelPriceLimitUploadReadServiceImpl implements ChannelPriceLimitUploadReadService {
	@Autowired
	private ChannelPriceLimitUploadReadManage channelPriceLimitUploadReadManage;

	@Override
	public ChannelPriceLimitUploadDTO findChannelPriceLimitUploadById(ChannelPriceLimitUploadDTO dto) {
		ChannelPriceLimitUploadPO po = ChannelPriceLimitUploadConverter.toPO(dto);
		ChannelPriceLimitUploadPO list = channelPriceLimitUploadReadManage.findChannelPriceLimitUploadById(po);
		return ChannelPriceLimitUploadConverter.toDTO(list);
	}

	@Override
	public PageResult<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadOfPage(ChannelPriceLimitUploadDTO dto, Pagination page) {
		ChannelPriceLimitUploadPO po = ChannelPriceLimitUploadConverter.toPO(dto);
		PageResult<ChannelPriceLimitUploadPO> pageResult = channelPriceLimitUploadReadManage.findChannelPriceLimitUploadOfPage(po, page);

		List<ChannelPriceLimitUploadDTO> list = ChannelPriceLimitUploadConverter.toDTO(pageResult.getList());
		PageResult<ChannelPriceLimitUploadDTO> result = new PageResult<>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadAll(ChannelPriceLimitUploadDTO dto) {
		ChannelPriceLimitUploadPO po = ChannelPriceLimitUploadConverter.toPO(dto);
		List<ChannelPriceLimitUploadPO> list = channelPriceLimitUploadReadManage.findChannelPriceLimitUploadAll(po);
		return ChannelPriceLimitUploadConverter.toDTO(list);
	}
}
