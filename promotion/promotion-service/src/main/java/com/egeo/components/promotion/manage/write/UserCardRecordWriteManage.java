package com.egeo.components.promotion.manage.write;

import com.egeo.components.promotion.po.UserCardRecordPO;

import java.math.BigDecimal;


public interface UserCardRecordWriteManage {

	Long insertUserCardRecordWithTx(UserCardRecordPO po);

	int updateUserCardRecordWithTx(UserCardRecordPO po);

	int deleteUserCardRecordWithTx(UserCardRecordPO po);

	public Boolean useCardWithTx(Long id, BigDecimal amount,Integer useState);

	public Boolean refundUserCardRecordWithTx(Long id, BigDecimal amount,Integer useState);
}
