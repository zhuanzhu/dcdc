package com.egeo.components.user.service.read;


import java.util.Date;
import java.util.List;

import com.egeo.components.user.dto.InfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InfoReadService {

	public InfoDTO findInfoById(InfoDTO dto);

	public PageResult<InfoDTO> findInfoOfPage(InfoDTO dto,Pagination page);

	public List<InfoDTO> findInfoAll(InfoDTO dto);
	/**
	 * 根据当前用户id分页显示当前不同消息列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<InfoDTO> findUserInfoOfPage(InfoDTO dto, Pagination page);
	/**
	 * 根据用户id消息类型和平台id查询用户未读数量
	 * @param userId
	 * @param type
	 * @param platformId
	 * @return
	 */
	public int findUnreadInfoSum(Long userId, int type, Long platformId);
	
	/**
	 * 查询消息组件显示的用户消息
	 * @param userId
	 * @param isRead
	 * @param type
	 * @param platformId
	 * @param createTime
	 * @param count
	 * @return
	 */
	List<InfoDTO> findUserInfoForMsgBox(Long userId, Integer isRead, Integer type, Long platformId, Date createTime, Integer count);

}
	