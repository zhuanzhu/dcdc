package com.egeo.components.order.manage.read;

import java.util.List;

import com.egeo.components.order.condition.SoPackageCondition;
import com.egeo.components.order.po.SoPackagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoPackageReadManage {

	public SoPackagePO findSoPackageById(SoPackagePO po);

	public PageResult<SoPackagePO> findSoPackageOfPage(SoPackagePO po,Pagination page);

	public List<SoPackagePO> findSoPackageAll(SoPackagePO po);

	public List<SoPackagePO> packageByOrderCode(String orderCode);

	public PageResult<SoPackageCondition> findSoPackageAndBoxOfPage(SoPackagePO po, Pagination page);

	/**
	 * 根据子订单id查询包裹
	 * @param id
	 * @return
	 */
	public List<SoPackagePO> queryPackageBySoChildId(Long id);

    String findDeliveryCompanyNameBySoChildId(Long soChildId);
    
    public List<SoPackagePO> findUnReceive(SoPackagePO po);
}
	