package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SerachSortManage;
import com.egeo.components.product.facade.SerachSortFacade;
import com.egeo.components.product.dto.SerachSortDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("serachSort")
public class SerachSortManageImpl implements SerachSortManage{

	
	@Resource(name="serachSortFacade")
	private SerachSortFacade serachSortFacade;

	@Override
	public SerachSortDTO findSerachSortById(SerachSortDTO dto) {
		return serachSortFacade.findSerachSortById(dto);
	}

	@Override
	public PageResult<SerachSortDTO> findSerachSortOfPage(SerachSortDTO dto, Pagination page) {
		return serachSortFacade.findSerachSortOfPage(dto, page);
	}

	@Override
	public List<SerachSortDTO> findSerachSortAll(SerachSortDTO dto) {
		return serachSortFacade.findSerachSortAll(dto);
	}

	@Override
	public Long insertSerachSortWithTx(SerachSortDTO dto) {
		return serachSortFacade.insertSerachSortWithTx(dto);
	}

	@Override
	public int updateSerachSortWithTx(SerachSortDTO dto) {
		return serachSortFacade.updateSerachSortWithTx(dto);
	}

	@Override
	public int deleteSerachSortWithTx(SerachSortDTO dto) {
		return serachSortFacade.deleteSerachSortWithTx(dto);
	}


}
	