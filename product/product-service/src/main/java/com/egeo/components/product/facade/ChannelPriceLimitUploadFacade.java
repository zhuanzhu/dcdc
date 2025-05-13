package com.egeo.components.product.facade;

import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;
import com.egeo.components.product.service.read.ChannelPriceLimitUploadReadService;
import com.egeo.components.product.service.write.ChannelPriceLimitUploadWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class ChannelPriceLimitUploadFacade {

	@Resource
	private ChannelPriceLimitUploadReadService channelPriceLimitUploadReadService;

	@Resource
	private ChannelPriceLimitUploadWriteService channelPriceLimitUploadWriteService;


	public ChannelPriceLimitUploadDTO findChannelPriceLimitUploadById(ChannelPriceLimitUploadDTO dto){

		return channelPriceLimitUploadReadService.findChannelPriceLimitUploadById(dto);
	}

	public PageResult<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadOfPage(ChannelPriceLimitUploadDTO dto,Pagination page){

		return channelPriceLimitUploadReadService.findChannelPriceLimitUploadOfPage(dto, page);

	}

	public List<ChannelPriceLimitUploadDTO> findChannelPriceLimitUploadAll(ChannelPriceLimitUploadDTO dto){

		return channelPriceLimitUploadReadService.findChannelPriceLimitUploadAll(dto);

	}

	public Long insertChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto){

		return channelPriceLimitUploadWriteService.insertChannelPriceLimitUploadWithTx(dto);
	}

	public int updateChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto){

		return channelPriceLimitUploadWriteService.updateChannelPriceLimitUploadWithTx(dto);
	}

	public int deleteChannelPriceLimitUploadWithTx(ChannelPriceLimitUploadDTO dto){

		return channelPriceLimitUploadWriteService.deleteChannelPriceLimitUploadWithTx(dto);

	}

}
