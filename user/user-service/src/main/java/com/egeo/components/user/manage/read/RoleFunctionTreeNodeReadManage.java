package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.RoleFunctionTreeNodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface RoleFunctionTreeNodeReadManage {

	public RoleFunctionTreeNodePO findRoleFunctionTreeNodeById(RoleFunctionTreeNodePO po);

	public PageResult<RoleFunctionTreeNodePO> findRoleFunctionTreeNodeOfPage(RoleFunctionTreeNodePO po,Pagination page);

	public List<RoleFunctionTreeNodePO> findRoleFunctionTreeNodeAll(RoleFunctionTreeNodePO po);
}
	