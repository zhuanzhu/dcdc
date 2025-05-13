package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.JdPriceConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdPriceConfigReadManage {

	public JdPriceConfigPO findJdPriceConfigById(JdPriceConfigPO po);

	public PageResult<JdPriceConfigPO> findJdPriceConfigOfPage(JdPriceConfigPO po,Pagination page);

	public List<JdPriceConfigPO> findJdPriceConfigAll(JdPriceConfigPO po);
}
	