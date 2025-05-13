package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.CmsDictManage;
import com.egeo.components.cms.facade.CmsDictFacade;
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsDict")
public class CmsDictManageImpl implements CmsDictManage{

	
	@Resource(name="cmsDictFacade")
	private CmsDictFacade cmsDictFacade;

	@Override
	public CmsDictDTO findCmsDictById(CmsDictDTO dto) {
		return cmsDictFacade.findCmsDictById(dto);
	}

	@Override
	public PageResult<CmsDictDTO> findCmsDictOfPage(CmsDictDTO dto, Pagination page) {
		return cmsDictFacade.findCmsDictOfPage(dto, page);
	}

	@Override
	public List<CmsDictDTO> findCmsDictAll(CmsDictDTO dto) {
		return cmsDictFacade.findCmsDictAll(dto);
	}

	@Override
	public Long insertCmsDictWithTx(CmsDictDTO dto) {
		return cmsDictFacade.insertCmsDictWithTx(dto);
	}

	@Override
	public int updateCmsDictWithTx(CmsDictDTO dto) {
		return cmsDictFacade.updateCmsDictWithTx(dto);
	}

	@Override
	public int deleteCmsDictWithTx(CmsDictDTO dto) {
		return cmsDictFacade.deleteCmsDictWithTx(dto);
	}


}
	