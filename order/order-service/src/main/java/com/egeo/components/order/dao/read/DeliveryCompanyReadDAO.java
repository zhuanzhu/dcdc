package com.egeo.components.order.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.DeliveryCompanyPO;
import com.egeo.orm.BaseReadDAO;

public interface DeliveryCompanyReadDAO extends BaseReadDAO<DeliveryCompanyPO>{

	/**
	 * 根据名称查询物流公司
	 * @param name
	 * @return
	 */
	DeliveryCompanyPO queryDeliveryCompanyByName(@Param("name")String name);
}
	