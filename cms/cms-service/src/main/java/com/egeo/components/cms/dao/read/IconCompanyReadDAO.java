package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.IconCompanyPO;
import com.egeo.orm.BaseReadDAO;

public interface IconCompanyReadDAO extends BaseReadDAO<IconCompanyPO>{

	/**
	 * 根据iconId和公司id查询icon与公司关系列表
	 * @param id
	 * @param companyId
	 * @return
	 */
	List<IconCompanyPO> queryIconCompanysByIconIdAndCompanyId(@Param("id")Long id, @Param("companyId")Long companyId);
}

	