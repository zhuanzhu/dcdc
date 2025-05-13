package com.egeo.components.order.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoCustomerServicePO;
import com.egeo.orm.BaseReadDAO;

public interface SoCustomerServiceReadDAO extends BaseReadDAO<SoCustomerServicePO>{

	/**
	 * 根据子订单id查询售后信息
	 * @param scId
	 * @return
	 */
	SoCustomerServicePO queryCustomerServiceBySoChildId(@Param("scId")Long scId);
}
	