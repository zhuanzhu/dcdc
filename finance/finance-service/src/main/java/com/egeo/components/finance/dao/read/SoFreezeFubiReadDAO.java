package com.egeo.components.finance.dao.read;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.SoFreezeFubiPO;
import com.egeo.orm.BaseReadDAO;

public interface SoFreezeFubiReadDAO extends BaseReadDAO<SoFreezeFubiPO>{
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	BigDecimal findSoFreezeBalanceBySoId(@Param("soId")Long soId);
}
	