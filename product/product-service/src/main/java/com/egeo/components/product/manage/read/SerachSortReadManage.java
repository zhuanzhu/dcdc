package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SerachSortPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SerachSortReadManage {

	public SerachSortPO findSerachSortById(SerachSortPO po);

	public PageResult<SerachSortPO> findSerachSortOfPage(SerachSortPO po,Pagination page);

	public List<SerachSortPO> findSerachSortAll(SerachSortPO po);
}
	