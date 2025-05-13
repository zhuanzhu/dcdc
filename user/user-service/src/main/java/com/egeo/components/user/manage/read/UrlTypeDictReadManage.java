package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.UrlTypeDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UrlTypeDictReadManage {

	public UrlTypeDictPO findUrlTypeDictById(UrlTypeDictPO po);

	public PageResult<UrlTypeDictPO> findUrlTypeDictOfPage(UrlTypeDictPO po,Pagination page);

	public List<UrlTypeDictPO> findUrlTypeDictAll(UrlTypeDictPO po);
}
	