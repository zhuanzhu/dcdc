package com.egeo.components.cms.manage.read;

import java.util.List;

import com.egeo.components.cms.po.IconCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface IconCompanyReadManage {

	public IconCompanyPO findIconCompanyById(IconCompanyPO po);

	public PageResult<IconCompanyPO> findIconCompanyOfPage(IconCompanyPO po,Pagination page);

	public List<IconCompanyPO> findIconCompanyAll(IconCompanyPO po);

	/**
	 * 根据iconId和公司id查询icon与公司关系列表
	 * @param id
	 * @param companyId
	 * @return
	 */
	public List<IconCompanyPO> queryIconCompanysByIconIdAndCompanyId(Long id, Long companyId);
}
	