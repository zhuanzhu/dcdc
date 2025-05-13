package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ChannelActivityReadService {

	public ChannelActivityDTO findChannelActivityById(ChannelActivityDTO dto);

	public PageResult<ChannelActivityDTO> findChannelActivityOfPage(ChannelActivityDTO dto,Pagination page);

	public List<ChannelActivityDTO> findChannelActivityAll(ChannelActivityDTO dto);
	/**
	 * 据活动短码查询活动
	 * @param shortCode
	 * @return
	 */
	public ChannelActivityDTO findByShortCode(String shortCode);
}
	