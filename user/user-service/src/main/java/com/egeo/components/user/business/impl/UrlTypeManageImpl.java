package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UrlTypeManage;
import com.egeo.components.user.facade.UrlTypeFacade;
import com.egeo.components.user.dto.UrlTypeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("urlType")
public class UrlTypeManageImpl implements UrlTypeManage{

	
	@Resource(name="urlTypeFacade")
	private UrlTypeFacade urlTypeFacade;

	@Override
	public UrlTypeDTO findUrlTypeById(UrlTypeDTO dto) {
		return urlTypeFacade.findUrlTypeById(dto);
	}

	@Override
	public PageResult<UrlTypeDTO> findUrlTypeOfPage(UrlTypeDTO dto, Pagination page) {
		return urlTypeFacade.findUrlTypeOfPage(dto, page);
	}

	@Override
	public List<UrlTypeDTO> findUrlTypeAll(UrlTypeDTO dto) {
		return urlTypeFacade.findUrlTypeAll(dto);
	}

	@Override
	public Long insertUrlTypeWithTx(UrlTypeDTO dto) {
		return urlTypeFacade.insertUrlTypeWithTx(dto);
	}

	@Override
	public int updateUrlTypeWithTx(UrlTypeDTO dto) {
		return urlTypeFacade.updateUrlTypeWithTx(dto);
	}

	@Override
	public int deleteUrlTypeWithTx(UrlTypeDTO dto) {
		return urlTypeFacade.deleteUrlTypeWithTx(dto);
	}


}
	