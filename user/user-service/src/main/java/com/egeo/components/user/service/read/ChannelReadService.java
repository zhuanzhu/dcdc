package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ChannelReadService {

	public ChannelDTO findChannelById(ChannelDTO dto);

	public PageResult<ChannelDTO> findChannelOfPage(ChannelDTO dto,Pagination page);

	public List<ChannelDTO> findChannelAll(ChannelDTO dto);
	/**
	 * 根据版本类型：1、安卓 2、ios查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<ChannelDTO> findChannelByType(int type);
}
	