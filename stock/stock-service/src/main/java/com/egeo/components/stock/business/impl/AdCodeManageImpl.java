package com.egeo.components.stock.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.AdCodeManage;
import com.egeo.components.stock.facade.AdCodeFacade;
import com.egeo.components.stock.dto.AdCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("adCode")
public class AdCodeManageImpl implements AdCodeManage{

	
	@Resource(name="adCodeFacade")
	private AdCodeFacade adCodeFacade;

	@Override
	public AdCodeDTO findAdCodeById(AdCodeDTO dto) {
		return adCodeFacade.findAdCodeById(dto);
	}

	@Override
	public PageResult<AdCodeDTO> findAdCodeOfPage(AdCodeDTO dto, Pagination page) {
		return adCodeFacade.findAdCodeOfPage(dto, page);
	}

	@Override
	public List<AdCodeDTO> findAdCodeAll(AdCodeDTO dto) {
		return adCodeFacade.findAdCodeAll(dto);
	}

	@Override
	public Long insertAdCodeWithTx(AdCodeDTO dto) {
		return adCodeFacade.insertAdCodeWithTx(dto);
	}

	@Override
	public int updateAdCodeWithTx(AdCodeDTO dto) {
		return adCodeFacade.updateAdCodeWithTx(dto);
	}

	@Override
	public int deleteAdCodeWithTx(AdCodeDTO dto) {
		return adCodeFacade.deleteAdCodeWithTx(dto);
	}


}
	