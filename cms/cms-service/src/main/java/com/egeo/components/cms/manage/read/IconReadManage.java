package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.IconPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface IconReadManage {

	public IconPO findIconById(IconPO po);

	public PageResult<IconPO> findIconOfPage(IconPO po,Pagination page);

	public List<IconPO> findIconAll(IconPO po);

	/**
	 * 根据组id查询icon列表
	 * @param groupId
	 * @return
	 */
	public List<IconPO> queryIconsByGroupId(Long groupId);
}
	