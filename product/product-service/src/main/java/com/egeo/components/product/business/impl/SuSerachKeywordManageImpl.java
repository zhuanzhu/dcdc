package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.SuSerachKeywordManage;
import com.egeo.components.product.facade.SuSerachKeywordFacade;
import com.egeo.components.product.dto.SuSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("suSerachKeyword")
public class SuSerachKeywordManageImpl implements SuSerachKeywordManage{

	
	@Resource(name="suSerachKeywordFacade")
	private SuSerachKeywordFacade suSerachKeywordFacade;

	@Override
	public SuSerachKeywordDTO findSuSerachKeywordById(SuSerachKeywordDTO dto) {
		return suSerachKeywordFacade.findSuSerachKeywordById(dto);
	}

	@Override
	public PageResult<SuSerachKeywordDTO> findSuSerachKeywordOfPage(SuSerachKeywordDTO dto, Pagination page) {
		return suSerachKeywordFacade.findSuSerachKeywordOfPage(dto, page);
	}

	@Override
	public List<SuSerachKeywordDTO> findSuSerachKeywordAll(SuSerachKeywordDTO dto) {
		return suSerachKeywordFacade.findSuSerachKeywordAll(dto);
	}

	@Override
	public Long insertSuSerachKeywordWithTx(SuSerachKeywordDTO dto) {
		return suSerachKeywordFacade.insertSuSerachKeywordWithTx(dto);
	}

	@Override
	public int updateSuSerachKeywordWithTx(SuSerachKeywordDTO dto) {
		return suSerachKeywordFacade.updateSuSerachKeywordWithTx(dto);
	}

	@Override
	public int deleteSuSerachKeywordWithTx(SuSerachKeywordDTO dto) {
		return suSerachKeywordFacade.deleteSuSerachKeywordWithTx(dto);
	}


}
	