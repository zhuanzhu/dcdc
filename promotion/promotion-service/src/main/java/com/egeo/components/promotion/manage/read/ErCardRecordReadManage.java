package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.ErCardRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ErCardRecordReadManage {

	public ErCardRecordPO findErCardRecordById(ErCardRecordPO po);

	public PageResult<ErCardRecordPO> findErCardRecordOfPage(ErCardRecordPO po,Pagination page);

	public List<ErCardRecordPO> findErCardRecordAll(ErCardRecordPO po);
}
	