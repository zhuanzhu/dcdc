package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.FuCoinHistoryManage;
import com.egeo.components.promotion.facade.FuCoinHistoryFacade;
import com.egeo.components.promotion.dto.FuCoinHistoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("fuCoinHistory")
public class FuCoinHistoryManageImpl implements FuCoinHistoryManage{

	
	@Resource(name="fuCoinHistoryFacade")
	private FuCoinHistoryFacade fuCoinHistoryFacade;

	@Override
	public FuCoinHistoryDTO findFuCoinHistoryById(FuCoinHistoryDTO dto) {
		return fuCoinHistoryFacade.findFuCoinHistoryById(dto);
	}

	@Override
	public PageResult<FuCoinHistoryDTO> findFuCoinHistoryOfPage(FuCoinHistoryDTO dto, Pagination page) {
		return fuCoinHistoryFacade.findFuCoinHistoryOfPage(dto, page);
	}

	@Override
	public List<FuCoinHistoryDTO> findFuCoinHistoryAll(FuCoinHistoryDTO dto) {
		return fuCoinHistoryFacade.findFuCoinHistoryAll(dto);
	}

	@Override
	public Long insertFuCoinHistoryWithTx(FuCoinHistoryDTO dto) {
		return fuCoinHistoryFacade.insertFuCoinHistoryWithTx(dto);
	}

	@Override
	public int updateFuCoinHistoryWithTx(FuCoinHistoryDTO dto) {
		return fuCoinHistoryFacade.updateFuCoinHistoryWithTx(dto);
	}

	@Override
	public int deleteFuCoinHistoryWithTx(FuCoinHistoryDTO dto) {
		return fuCoinHistoryFacade.deleteFuCoinHistoryWithTx(dto);
	}


}
	