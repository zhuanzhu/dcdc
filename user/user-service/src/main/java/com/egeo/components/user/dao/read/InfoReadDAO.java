package com.egeo.components.user.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.InfoPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface InfoReadDAO extends BaseReadDAO<InfoPO>{
	/**
	 * 根据当前用户id分页显示当前不同消息条数
	 * @param po
	 * @return
	 */
	int countUserInfoOfPage(@Param("po")InfoPO po);
	/**
	 * 根据当前用户id分页显示当前不同消息列表
	 * @param po
	 * @param page
	 * @return
	 */
	List<InfoPO> findUserInfoOfPage(@Param("po")InfoPO po, @Param("page")Pagination page);
	/**
	 * 根据用户id和类型查询其他未读数量
	 * @param userId
	 * @param i
	 * @param platformId
	 * @return
	 */
	int findUnreadInfoSum(@Param("userId")Long userId, @Param("type")int type, @Param("platformId")Long platformId);
	
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
	List<InfoPO> findUserInfoForMsgBox(@Param("userId")Long userId, @Param("isRead")Integer isRead, @Param("type")Integer type, 
			@Param("platformId")Long platformId, @Param("createTime")Date createTime, @Param("count")Integer count);
	
}
	