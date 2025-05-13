package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.InstCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InstCompanyReadManage {

	public InstCompanyPO findInstCompanyById(InstCompanyPO po);

	public PageResult<InstCompanyPO> findInstCompanyOfPage(InstCompanyPO po,Pagination page);

	public List<InstCompanyPO> findInstCompanyAll(InstCompanyPO po);

	/**
	 * 根据实例id和公司id查询实例与公司关系列表
	 * @param id
	 * @param companyId
	 * @return
	 */
	public List<InstCompanyPO> queryInstCompanyListByInstIdAndCompanyId(Long id, Long companyId, Long companyIdByType);

}
	