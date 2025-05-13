package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.TrialApplyReadService;
import com.egeo.components.user.dto.TrialApplyDTO;
import com.egeo.components.user.po.TrialApplyPO;
import com.egeo.components.user.dao.read.TrialApplyReadDAO;
import com.egeo.components.user.converter.TrialApplyConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("trialApplyReadService")
public class TrialApplyReadServiceImpl implements TrialApplyReadService {
	@Autowired
	private TrialApplyReadDAO trialApplyReadDAO ;

	@Override
	public TrialApplyDTO findTrialApplyById(TrialApplyDTO dto){
		TrialApplyPO po = TrialApplyConverter.toPO(dto);
		TrialApplyPO trialApplypo = new TrialApplyPO();
		trialApplypo.setId(po.getId());
		TrialApplyPO list = trialApplyReadDAO.findById(trialApplypo);
		return TrialApplyConverter.toDTO(list);
	}
	@Override
	public PageResult<TrialApplyDTO> findTrialApplyOfPage(TrialApplyDTO dto, Pagination page) {
		TrialApplyPO po = TrialApplyConverter.toPO(dto);
		PageResult<TrialApplyPO> pageResult = new PageResult<TrialApplyPO>();
		List<TrialApplyPO> listT = null;
		int cnt = trialApplyReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = trialApplyReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<TrialApplyPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<TrialApplyDTO> list = TrialApplyConverter.toDTO(pageResult.getList());
        PageResult<TrialApplyDTO> result = new PageResult<TrialApplyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<TrialApplyDTO> findTrialApplyAll(TrialApplyDTO dto) {
		TrialApplyPO po = TrialApplyConverter.toPO(dto);
		List<TrialApplyPO> list = trialApplyReadDAO.findAll(po,null);
		return TrialApplyConverter.toDTO(list);
	}

	@Override
	public PageResult<TrialApplyDTO> findTrialApplyOfPageByBlurry(TrialApplyDTO dto, Pagination page) {
		TrialApplyPO po = TrialApplyConverter.toPO(dto);
		PageResult<TrialApplyPO> pageResult = new PageResult<TrialApplyPO>();
		List<TrialApplyPO> listT = null;

		int cnt = trialApplyReadDAO.countOfPageByBlurry(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = trialApplyReadDAO.findOfPageByBlurry(po, page);
		} else {
			listT = new ArrayList<TrialApplyPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<TrialApplyDTO> list = TrialApplyConverter.toDTO(pageResult.getList());
        PageResult<TrialApplyDTO> result = new PageResult<TrialApplyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
}
	