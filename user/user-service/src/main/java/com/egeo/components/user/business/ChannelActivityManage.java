package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.vo.ChannelActivityVO;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ChannelActivityManage {

	public Map<String, Object> findChannelActivityById(Long channelActivityId);	

	public PageResult<Map<String, Object>> findChannelActivityOfPage(ChannelActivityDTO dto,Pagination page);

	public List<ChannelActivityDTO> findChannelActivityAll(ChannelActivityDTO dto);

	Long insertChannelActivityWithTx(ChannelActivityDTO dto);

	int updateChannelActivityWithTx(ChannelActivityDTO dto);

	int deleteChannelActivityWithTx(ChannelActivityDTO dto);

	List<ChannelActivityVO> findChannelActivityByChannelId(Long channelId, Long platformId);
}
	