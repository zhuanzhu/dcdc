package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.condition.VersionsChildCondition;
import com.egeo.components.user.po.VersionsChildPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface VersionsChildReadManage {

	public VersionsChildPO findVersionsChildById(VersionsChildPO po);

	public PageResult<VersionsChildPO> findVersionsChildOfPage(VersionsChildPO po,Pagination page);

	public List<VersionsChildPO> findVersionsChildAll(VersionsChildPO po);
	/**
	 * 根据条件分页查询子版本和及其渠道信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<VersionsChildCondition> versionsChildAndChannelOfPage(VersionsChildPO po, Pagination page);
}
	