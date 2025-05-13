package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.FeedbackConverter;
import com.egeo.components.user.dao.read.FeedbackReadDAO;
import com.egeo.components.user.dto.FeedbackDTO;
import com.egeo.components.user.po.FeedbackPO;
import com.egeo.components.user.service.read.FeedbackReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("feedbackReadService")
public class FeedbackReadServiceImpl implements FeedbackReadService {
	@Autowired
	private FeedbackReadDAO feedbackReadDAO;

	@Override
	public FeedbackDTO findFeedbackById(FeedbackDTO dto) {
		FeedbackPO po = FeedbackConverter.toPO(dto);
		FeedbackPO feedbackpo = new FeedbackPO();
		feedbackpo.setId(po.getId());
		FeedbackPO list = feedbackReadDAO.findById(feedbackpo);		
		return FeedbackConverter.toDTO(list);
	}

	@Override
	public PageResult<FeedbackDTO> findFeedbackOfPage(FeedbackDTO dto, Pagination page) {
		FeedbackPO po = FeedbackConverter.toPO(dto);
		
		PageResult<FeedbackPO> pageResult = new PageResult<FeedbackPO>();
		List<FeedbackPO> listT = null;

		int cnt = feedbackReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = feedbackReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<FeedbackPO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
        
        List<FeedbackDTO> list = FeedbackConverter.toDTO(pageResult.getList());
        PageResult<FeedbackDTO> result = new PageResult<FeedbackDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FeedbackDTO> findFeedbackAll(FeedbackDTO dto) {
		FeedbackPO po = FeedbackConverter.toPO(dto);
		List<FeedbackPO> list = feedbackReadDAO.findAll(po,null);		
		return FeedbackConverter.toDTO(list);
	}
}
	