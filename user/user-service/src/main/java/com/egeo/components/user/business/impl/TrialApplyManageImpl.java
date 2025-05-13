package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.TrialApplyManage;
import com.egeo.components.user.facade.TrialApplyFacade;
import com.egeo.components.user.facade.UserFacade;
import com.egeo.components.user.dto.TrialApplyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("trialApply")
public class TrialApplyManageImpl implements TrialApplyManage{

	
	@Resource(name="trialApplyFacade")
	private TrialApplyFacade trialApplyFacade;
	
	@Resource(name="userFacade")
	private UserFacade userFacade;

	@Override
	public TrialApplyDTO findTrialApplyById(TrialApplyDTO dto) {
		TrialApplyDTO result = trialApplyFacade.findTrialApplyById(dto);
		if (EmptyUtil.isNotEmpty(result) && EmptyUtil.isNotEmpty(result.getDealUser())) {
			// 查询处理人信息
			UserDTO dealUser = userFacade.findUserByID(result.getDealUser());
			result.setDealUserName(dealUser != null ? dealUser.getLoginName() : null);
		}
		return result;
	}

	@Override
	public PageResult<TrialApplyDTO> findTrialApplyOfPage(TrialApplyDTO dto, Pagination page) {
		return trialApplyFacade.findTrialApplyOfPageByBlurry(dto, page);
	}

	@Override
	public List<TrialApplyDTO> findTrialApplyAll(TrialApplyDTO dto) {
		return trialApplyFacade.findTrialApplyAll(dto);
	}

	@Override
	public Long insertTrialApplyWithTx(TrialApplyDTO dto) {
		return trialApplyFacade.insertTrialApplyWithTx(dto);
	}

	@Override
	public int updateTrialApplyWithTx(TrialApplyDTO dto) {
		return trialApplyFacade.updateTrialApplyWithTx(dto);
	}

	@Override
	public int deleteTrialApplyWithTx(TrialApplyDTO dto) {
		return trialApplyFacade.deleteTrialApplyWithTx(dto);
	}


}
	