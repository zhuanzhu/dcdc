package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.IconGroupCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface IconGroupCompanyReadManage {

	public IconGroupCompanyPO findIconGroupCompanyById(IconGroupCompanyPO po);

	public PageResult<IconGroupCompanyPO> findIconGroupCompanyOfPage(IconGroupCompanyPO po,Pagination page);

	public List<IconGroupCompanyPO> findIconGroupCompanyAll(IconGroupCompanyPO po);
}
	