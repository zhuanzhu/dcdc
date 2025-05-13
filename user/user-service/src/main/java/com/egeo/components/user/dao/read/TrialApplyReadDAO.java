package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.TrialApplyPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface TrialApplyReadDAO extends BaseReadDAO<TrialApplyPO>{

	/**
	 * 模糊搜索试用申请分页信息
	 * @param po
	 * @return
	 */
	int countOfPageByBlurry(@Param("po")TrialApplyPO po);

	List<TrialApplyPO> findOfPageByBlurry(@Param("po")TrialApplyPO po, @Param("page")Pagination page);
}
	