package com.egeo.components.order.dao.write;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.ReceiverAddressPO;
import com.egeo.orm.BaseWriteDAO;

public interface ReceiverAddressWriteDAO extends BaseWriteDAO<ReceiverAddressPO> {
	/**
	 * 根据用户id取消默认地址
	 * @param userId
	 * @return
	 */
	int cancelReceiverAddressByUserId(@Param("userId")Long userId);
}
	