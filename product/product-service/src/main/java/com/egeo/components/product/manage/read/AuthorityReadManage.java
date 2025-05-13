package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.AuthorityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AuthorityReadManage {

	public AuthorityPO findAuthorityById(AuthorityPO po);

	public PageResult<AuthorityPO> findAuthorityOfPage(AuthorityPO po,Pagination page);

	public List<AuthorityPO> findAuthorityAll(AuthorityPO po);
}
	