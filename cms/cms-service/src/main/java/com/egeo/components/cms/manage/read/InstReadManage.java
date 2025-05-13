package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.InstPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InstReadManage {

	public InstPO findInstById(InstPO po);

	public PageResult<InstPO> findInstOfPage(InstPO po,Pagination page);

	public List<InstPO> findInstAll(InstPO po);

	/**
	 * 根据组件id拆查询实例
	 * @param elementId
	 * @return
	 */
	public InstPO queryInstByElementId(Long elementId);
}
	