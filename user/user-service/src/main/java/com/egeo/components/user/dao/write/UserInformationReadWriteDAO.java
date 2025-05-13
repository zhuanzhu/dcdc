package com.egeo.components.user.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.UserInformationReadPO;
import com.egeo.orm.BaseWriteDAO;

public interface UserInformationReadWriteDAO extends BaseWriteDAO<UserInformationReadPO> {
	/**
	 * 根据消息id逻辑删除
	 * @param userInformationId
	 * @return
	 */
	int deleteByUserInformationIdWithTx(@Param("userInformationId")Long userInformationId);
}
	