package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SurSerachKeywordManage;
import com.egeo.components.product.facade.SurSerachKeywordFacade;
import com.egeo.components.product.dto.SurSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("surSerachKeyword")
public class SurSerachKeywordManageImpl implements SurSerachKeywordManage{

	
	@Resource(name="surSerachKeywordFacade")
	private SurSerachKeywordFacade surSerachKeywordFacade;

	@Override
	public SurSerachKeywordDTO findSurSerachKeywordById(SurSerachKeywordDTO dto) {
		return surSerachKeywordFacade.findSurSerachKeywordById(dto);
	}

	@Override
	public PageResult<SurSerachKeywordDTO> findSurSerachKeywordOfPage(SurSerachKeywordDTO dto, Pagination page) {
		return surSerachKeywordFacade.findSurSerachKeywordOfPage(dto, page);
	}

	@Override
	public List<SurSerachKeywordDTO> findSurSerachKeywordAll(SurSerachKeywordDTO dto) {
		return surSerachKeywordFacade.findSurSerachKeywordAll(dto);
	}

	@Override
	public Long insertSurSerachKeywordWithTx(SurSerachKeywordDTO dto) {
		return surSerachKeywordFacade.insertSurSerachKeywordWithTx(dto);
	}

	@Override
	public int updateSurSerachKeywordWithTx(SurSerachKeywordDTO dto) {
		return surSerachKeywordFacade.updateSurSerachKeywordWithTx(dto);
	}

	@Override
	public int deleteSurSerachKeywordWithTx(SurSerachKeywordDTO dto) {
		return surSerachKeywordFacade.deleteSurSerachKeywordWithTx(dto);
	}


}
	