package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.AdCodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AdCodeReadManage {

	public AdCodePO findAdCodeById(AdCodePO po);

	public PageResult<AdCodePO> findAdCodeOfPage(AdCodePO po,Pagination page);

	public List<AdCodePO> findAdCodeAll(AdCodePO po);
}
	