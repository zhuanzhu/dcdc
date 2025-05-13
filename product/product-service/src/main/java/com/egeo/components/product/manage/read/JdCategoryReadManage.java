package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.JdCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdCategoryReadManage {

	public JdCategoryPO findJdCategoryById(JdCategoryPO po);

	public PageResult<JdCategoryPO> findJdCategoryOfPage(JdCategoryPO po,Pagination page);

	public List<JdCategoryPO> findJdCategoryAll(JdCategoryPO po);


	List<Long> findJdCategoryIdByCatClass(int catClass);
}
	