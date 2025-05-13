package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.UrlTypePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface UrlTypeReadManage {

	public UrlTypePO findUrlTypeById(UrlTypePO po);

	public PageResult<UrlTypePO> findUrlTypeOfPage(UrlTypePO po,Pagination page);

	public List<UrlTypePO> findUrlTypeAll(UrlTypePO po);
}
	