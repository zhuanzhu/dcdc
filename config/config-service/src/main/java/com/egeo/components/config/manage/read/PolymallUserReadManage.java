package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.PolymallUserPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PolymallUserReadManage {

	public PolymallUserPO findPolymallUserById(PolymallUserPO po);

	public PageResult<PolymallUserPO> findPolymallUserOfPage(PolymallUserPO po,Pagination page);

	public List<PolymallUserPO> findPolymallUserAll(PolymallUserPO po);
}
	