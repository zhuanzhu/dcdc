package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.TrialApplyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface TrialApplyReadService {

	public TrialApplyDTO findTrialApplyById(TrialApplyDTO dto);

	public PageResult<TrialApplyDTO> findTrialApplyOfPage(TrialApplyDTO dto,Pagination page);

	public List<TrialApplyDTO> findTrialApplyAll(TrialApplyDTO dto);

	/**
	 * 模糊搜索试用申请分页信息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<TrialApplyDTO> findTrialApplyOfPageByBlurry(TrialApplyDTO dto, Pagination page);
}
	