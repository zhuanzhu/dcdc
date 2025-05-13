package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.StandardUnitCompanyPO;


public interface StandardUnitCompanyWriteManage {

	Long insertStandardUnitCompanyWithTx(StandardUnitCompanyPO po);

	int updateStandardUnitCompanyWithTx(StandardUnitCompanyPO po);

	int deleteStandardUnitCompanyWithTx(StandardUnitCompanyPO po);
	/**
	 * 根据suid删除su福利企业关系信息
	 * @param merchantProdId
	 * @return
	 */
	int deleteByStandardUnitIdWithTx(Long standardUnitId);
	/**
	 * 根据suid和福利企业集合id删除其余关系
	 * @param merchantProductId
	 * @param companyId
	 * @return
	 */
	int delByStandardUnitIdCompanyId(Long standardUnitId, List<Long> companyId);
	/**
	 * 根据suid删除su福利企业关系
	 * @param merchantProductId
	 * @return
	 */
	int delByStandardUnitId(Long standardUnitId);

    void saveStandardUnitCompany(List<StandardUnitCompanyPO> standardUnitCompanyPOList);
}
	