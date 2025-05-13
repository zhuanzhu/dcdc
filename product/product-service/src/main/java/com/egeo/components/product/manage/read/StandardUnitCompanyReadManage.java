package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCompanyReadManage {

	public StandardUnitCompanyPO findStandardUnitCompanyById(StandardUnitCompanyPO po);

	public PageResult<StandardUnitCompanyPO> findStandardUnitCompanyOfPage(StandardUnitCompanyPO po,Pagination page);

	public List<StandardUnitCompanyPO> findStandardUnitCompanyAll(StandardUnitCompanyPO po);

	/**
	 * 查询su是否对公司、客户端可见
	 * @param suId
	 * @param companyId
	 * @return
	 */
	public boolean querySuCompanyAvailable(Long suId, Long companyId,Long clientId, Integer companyType);

	public List<StandardUnitCompanyPO> findSuCompanyByCompanyIdAndTypeAndSuIds(StandardUnitCompanyPO po,List<Long> suIds);
}
	