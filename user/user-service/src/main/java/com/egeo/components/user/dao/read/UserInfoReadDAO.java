package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserInfoPO;
import com.egeo.orm.BaseReadDAO;

public interface UserInfoReadDAO extends BaseReadDAO<UserInfoPO>{

	int findUserSumByInfoId(@Param("infoId")Long infoId, @Param("platformId")Long platformId);
	/**
	 * 根据当前用户id查询当前用户消息数量
	 * @param po
	 * @return
	 */
	int findUserInfoSumByUserId(@Param("po")UserInfoPO po);
	/**
	 * 根据用户id消息类型查询用户消息id集合
	 * @param userId
	 * @param type
	 * @param platformId
	 * @return
	 */
	List<Long> findByUserIdType(
			@Param("userId")Long userId,
			@Param("type")Long type, 
			@Param("platformId")Long platformId);
}
	