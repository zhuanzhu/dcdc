package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.SaltPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SaltReadManage {

	public SaltPO findSaltById(SaltPO po);

	public PageResult<SaltPO> findSaltOfPage(SaltPO po,Pagination page);

	public List<SaltPO> findSaltAll(SaltPO po);

	/**
	 * 根据uuid获取盐
	 * @param uuid
	 * @return
	 */
	public SaltPO querySaltByUUID(String uuid);
}
	