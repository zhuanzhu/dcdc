package com.egeo.components.promotion.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.business.FuCoinManage;
import com.egeo.components.promotion.facade.FuCoinFacade;
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("fuCoin")
public class FuCoinManageImpl implements FuCoinManage{

	
	@Resource(name="fuCoinFacade")
	private FuCoinFacade fuCoinFacade;

	@Override
	public FuCoinDTO findFuCoinById(FuCoinDTO dto) {
		return fuCoinFacade.findFuCoinById(dto);
	}

	@Override
	public PageResult<FuCoinDTO> findFuCoinOfPage(FuCoinDTO dto, Pagination page) {
		return fuCoinFacade.findFuCoinOfPage(dto, page);
	}

	@Override
	public List<FuCoinDTO> findFuCoinAll(FuCoinDTO dto) {
		return fuCoinFacade.findFuCoinAll(dto);
	}

	@Override
	public Long insertFuCoinWithTx(FuCoinDTO dto) {
		return fuCoinFacade.insertFuCoinWithTx(dto);
	}

	@Override
	public int updateFuCoinWithTx(FuCoinDTO dto) {
		return fuCoinFacade.updateFuCoinWithTx(dto);
	}

	@Override
	public int deleteFuCoinWithTx(FuCoinDTO dto) {
		return fuCoinFacade.deleteFuCoinWithTx(dto);
	}


}
	