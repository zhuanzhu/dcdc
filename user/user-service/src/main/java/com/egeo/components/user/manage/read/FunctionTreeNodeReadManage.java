package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.FunctionTreeNodePO;
import com.egeo.components.user.po.UrlPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FunctionTreeNodeReadManage {

	public FunctionTreeNodePO findFunctionTreeNodeById(FunctionTreeNodePO po);

	public PageResult<FunctionTreeNodePO> findFunctionTreeNodeOfPage(FunctionTreeNodePO po,Pagination page);

	public List<FunctionTreeNodePO> findFunctionTreeNodeAll(FunctionTreeNodePO po);

	public List<UrlPO> findUrlList(Long id);

}
	