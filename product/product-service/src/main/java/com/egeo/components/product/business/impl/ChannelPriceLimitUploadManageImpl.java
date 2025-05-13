package com.egeo.components.product.business.impl;


import com.egeo.components.product.business.ChannelPriceLimitUploadManage;
import com.egeo.components.product.business.JdPriceLimitUploadManage;
import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.components.product.facade.ChannelPriceLimitUploadFacade;
import com.egeo.components.product.facade.JdPriceLimitUploadFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("channelPriceLimitUpload")
public class ChannelPriceLimitUploadManageImpl implements ChannelPriceLimitUploadManage {

	@Resource(name="channelPriceLimitUploadFacade")
	private ChannelPriceLimitUploadFacade channelPriceLimitUploadFacade;

	@Override
	public ChannelPriceLimitUploadDTO findChannelPriceLimitUploadById(ChannelPriceLimitUploadDTO dto) {
		return channelPriceLimitUploadFacade.findChannelPriceLimitUploadById(dto);
	}

	@Override
	public PageResult<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadOfPage(ChannelPriceLimitUploadDTO dto, Pagination page) {
		return channelPriceLimitUploadFacade.findChannelPriceLimitUploadOfPage(dto, page);
	}

	@Override
	public List<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadAll(ChannelPriceLimitUploadDTO dto) {
		return channelPriceLimitUploadFacade.findChannelPriceLimitUploadAll(dto);
	}

	@Override
	public Long insertChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto) {
		return channelPriceLimitUploadFacade.insertChannelPriceLimitUploadWithTx(dto);
	}

	@Override
	public int updateChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto) {
		return channelPriceLimitUploadFacade.updateChannelPriceLimitUploadWithTx(dto);
	}

	@Override
	public int deleteChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto) {
		return channelPriceLimitUploadFacade.deleteChannelPriceLimitUploadWithTx(dto);
	}


}
