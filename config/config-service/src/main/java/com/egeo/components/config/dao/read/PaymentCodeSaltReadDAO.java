package com.egeo.components.config.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.config.po.PaymentCodeSaltPO;
import com.egeo.orm.BaseReadDAO;

public interface PaymentCodeSaltReadDAO extends BaseReadDAO<PaymentCodeSaltPO>{
	/**
	 * 根据uuId查询用户支付密钥
	 * @param paymentCodeUuid
	 * @return
	 */
	String findSaltByUUID(@Param("paymentCodeUuid")String paymentCodeUuid);
}
	