package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardUnitCompanyWriteDAO extends BaseWriteDAO<StandardUnitCompanyPO> {
	/**
	 * 根据suid和福利企业集合id删除其余关系
	 * @param merchantProductId
	 * @param companyId
	 * @return
	 */
	int delByStandardUnitCompanyId(@Param("standardUnitId")Long standardUnitId, @Param("ids")List<Long> companyId);
	/**
	 * 批量保存su商品公司信息
	 * @param standardUnitCompanys
	 */
	void insertAll(@Param("poList")List<StandardUnitCompanyPO> standardUnitCompanys);

    void saveStandardUnitCompany(@Param("poList")List<StandardUnitCompanyPO> standardUnitCompanyPOList);
}
	