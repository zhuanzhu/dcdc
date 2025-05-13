package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.orm.BaseReadDAO;

public interface StandardUnitCompanyReadDAO extends BaseReadDAO<StandardUnitCompanyPO>{

	/**
	 * 查询su可读公司、客户端的数量
	 * @param suId
	 * @param companyId
	 * @return
	 */
	int querySuCompanyAvailableCount(@Param("suId")Long suId, @Param("companyId")Long companyId,
			@Param("clientId")Long clientId, @Param("companyType")Integer companyType);

	List<StandardUnitCompanyPO> findSuCompanyByCompanyIdAndTypeAndSuIds(@Param("po") StandardUnitCompanyPO po,@Param("suIds") List<Long> suIds);
}
	