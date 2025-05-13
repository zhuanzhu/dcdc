package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.vo.ChannelVO;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ChannelManage {

	public Map<String, Object> findChannelById(ChannelDTO dto);	

	public PageResult<Map<String, Object>> findChannelOfPage(ChannelDTO dto,Pagination page);

	public List<Map<String, Object>> findChannelAll(ChannelDTO dto);

	Long insertChannelWithTx(ChannelDTO dto);

	int updateChannelWithTx(ChannelDTO dto);

	int deleteChannelWithTx(ChannelDTO dto);
	/**
	 * 根据版本类型：1、安卓 2、ios查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> findChannelByType(int type);
	/**
	 * 根据版本id查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> findChannelByVersionsId(Long versionsId);

    List<ChannelVO> findChannelByPlatformId(Long platformId);
}
	