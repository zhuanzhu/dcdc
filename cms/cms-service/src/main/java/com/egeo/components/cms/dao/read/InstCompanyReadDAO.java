package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.InstCompanyPO;
import com.egeo.orm.BaseReadDAO;

public interface InstCompanyReadDAO extends BaseReadDAO<InstCompanyPO> {

	/**
	 * 根据实例id和公司id查询实例与公司关系列表
	 * 
	 * @param id
	 * @param companyId
	 * @return
	 */
	List<InstCompanyPO> queryInstCompanyListByInstIdAndCompanyId(@Param("id") Long id,
			@Param("companyId") Long companyId, @Param("companyIdByType") Long companyIdByType);

}
