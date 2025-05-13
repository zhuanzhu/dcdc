package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.IconGroupPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface IconGroupReadManage {

	public IconGroupPO findIconGroupById(IconGroupPO po);

	public PageResult<IconGroupPO> findIconGroupOfPage(IconGroupPO po,Pagination page);

	public List<IconGroupPO> findIconGroupAll(IconGroupPO po);

	/**
	 * 根据实例id查询icon组
	 * @param instId
	 * @return
	 */
	public IconGroupPO queryIconGroupByInstId(Long instId);
}
	