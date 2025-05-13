package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.TrialApplyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface TrialApplyManage {

	public TrialApplyDTO findTrialApplyById(TrialApplyDTO dto);	

	public PageResult<TrialApplyDTO> findTrialApplyOfPage(TrialApplyDTO dto,Pagination page);

	public List<TrialApplyDTO> findTrialApplyAll(TrialApplyDTO dto);

	Long insertTrialApplyWithTx(TrialApplyDTO dto);

	int updateTrialApplyWithTx(TrialApplyDTO dto);

	int deleteTrialApplyWithTx(TrialApplyDTO dto);
}
	