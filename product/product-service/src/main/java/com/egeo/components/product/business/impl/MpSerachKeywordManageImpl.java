package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MpSerachKeywordManage;
import com.egeo.components.product.facade.MpSerachKeywordFacade;
import com.egeo.components.product.dto.MpSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("mpSerachKeyword")
public class MpSerachKeywordManageImpl implements MpSerachKeywordManage{

	
	@Resource(name="mpSerachKeywordFacade")
	private MpSerachKeywordFacade mpSerachKeywordFacade;

	@Override
	public MpSerachKeywordDTO findMpSerachKeywordById(MpSerachKeywordDTO dto) {
		return mpSerachKeywordFacade.findMpSerachKeywordById(dto);
	}

	@Override
	public PageResult<MpSerachKeywordDTO> findMpSerachKeywordOfPage(MpSerachKeywordDTO dto, Pagination page) {
		return mpSerachKeywordFacade.findMpSerachKeywordOfPage(dto, page);
	}

	@Override
	public List<MpSerachKeywordDTO> findMpSerachKeywordAll(MpSerachKeywordDTO dto) {
		return mpSerachKeywordFacade.findMpSerachKeywordAll(dto);
	}

	@Override
	public Long insertMpSerachKeywordWithTx(MpSerachKeywordDTO dto) {
		return mpSerachKeywordFacade.insertMpSerachKeywordWithTx(dto);
	}

	@Override
	public int updateMpSerachKeywordWithTx(MpSerachKeywordDTO dto) {
		return mpSerachKeywordFacade.updateMpSerachKeywordWithTx(dto);
	}

	@Override
	public int deleteMpSerachKeywordWithTx(MpSerachKeywordDTO dto) {
		return mpSerachKeywordFacade.deleteMpSerachKeywordWithTx(dto);
	}


}
	