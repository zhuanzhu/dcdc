package com.egeo.components.user.manage.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.user.po.InfoPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoReadManage {

	public InfoPO findInfoById(InfoPO po);

	public PageResult<InfoPO> findInfoOfPage(InfoPO po,Pagination page);

	public List<InfoPO> findInfoAll(InfoPO po);
	/**
	 * 根据当前用户id分页显示当前不同消息列表
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<InfoPO> findUserInfoOfPage(InfoPO po, Pagination page);
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
	List<InfoPO> findUserInfoForMsgBox(Long userId, Integer isRead, Integer type, Long platformId, Date createTime, Integer count);
	
}
	