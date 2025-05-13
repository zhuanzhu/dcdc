package com.egeo.components.promotion.manage.read;

import com.egeo.components.promotion.po.UserCardRecordPO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface UserCardRecordReadManage {

	public UserCardRecordPO findUserCardRecordById(UserCardRecordPO po);

	public PageResult<UserCardRecordPO> findUserCardRecordOfPage(UserCardRecordPO po,Pagination page);

	public List<UserCardRecordPO> findUserCardRecordAll(UserCardRecordPO po);

	Integer countUserCardRecord(UserCardRecordPO po);

	List<UserCardRecordPO> sumUserCardTypeAmount(UserCardRecordPO po);
}
