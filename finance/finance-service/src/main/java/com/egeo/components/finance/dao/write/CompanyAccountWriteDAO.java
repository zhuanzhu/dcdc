package com.egeo.components.finance.dao.write;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.AccountUpdatePO;
import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.orm.BaseWriteDAO;

public interface CompanyAccountWriteDAO extends BaseWriteDAO<CompanyAccountPO> {

	/**
	 * 更改公司账户有效性
	 * @param id
	 * @param disabled
	 * @return
	 */
	int updateAccountDisable(@Param("id")Long id, @Param("disabled")Integer disabled);

	/**
	 * 修改账户余额
	 * @param accountId
	 * @param sum_
	 * @param isRecharge 充值会影响最后一次充值之间字段
	 * @return
	 */
	int increaseAccountBalance(@Param("accountId")Long accountId, @Param("sum")BigDecimal sum,@Param("isRecharge")boolean isRecharge,@Param("salt")String salt);

	/**
	 * 批量修改账户余额
	 * @param aus
	 * @param b
	 * @return
	 */
	int batchIncreaseAccountBalance(@Param("aus")List<AccountUpdatePO> aus,@Param("isRecharge")boolean isRecharge);

}
	