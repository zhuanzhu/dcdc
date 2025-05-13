package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.SuListPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SuListReadManage {

	public SuListPO findSuListById(SuListPO po);

	public PageResult<SuListPO> findSuListOfPage(SuListPO po,Pagination page);

	public List<SuListPO> findSuListAll(SuListPO po);

	/**
	 * 根据实例id查询商品列表
	 * @param instId
	 * @return
	 */
	public SuListPO querySuListByInstId(Long instId);
}
	