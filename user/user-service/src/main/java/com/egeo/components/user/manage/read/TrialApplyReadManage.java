package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.TrialApplyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface TrialApplyReadManage {

	public TrialApplyPO findTrialApplyById(TrialApplyPO po);

	public PageResult<TrialApplyPO> findTrialApplyOfPage(TrialApplyPO po,Pagination page);

	public List<TrialApplyPO> findTrialApplyAll(TrialApplyPO po);

	/**
	 * 模糊搜索试用申请分页信息
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<TrialApplyPO> findTrialApplyOfPageByBlurry(TrialApplyPO po, Pagination page);
}
	