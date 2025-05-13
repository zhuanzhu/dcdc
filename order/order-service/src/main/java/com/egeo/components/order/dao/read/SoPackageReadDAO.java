package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.condition.SoPackageCondition;
import com.egeo.components.order.po.SoPackagePO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface SoPackageReadDAO extends BaseReadDAO<SoPackagePO>{

	List<SoPackageCondition> packageBySoId(@Param("id")Long id);

	List<SoPackagePO> packageByOrderCode(@Param("id")String orderCode);

	List<SoPackageCondition> findSoPackageAndBoxOfPage(@Param("po")SoPackagePO po, @Param("page")Pagination page);

	/**
	 * 根据子订单id查询包裹
	 * @param id
	 * @return
	 */
	List<SoPackagePO> queryPackageBySoChildId(@Param("id")Long id);

    String findDeliveryCompanyNameBySoChildId(@Param("soChildId")Long soChildId);

    List<SoPackagePO> findUnReceive(@Param("po")SoPackagePO po);
}
	