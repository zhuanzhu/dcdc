package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.CmsLocalParamManage;
import com.egeo.components.cms.facade.CmsLocalParamFacade;
import com.egeo.components.cms.dto.CmsLocalParamDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsLocalParam")
public class CmsLocalParamManageImpl implements CmsLocalParamManage{

	
	@Resource(name="cmsLocalParamFacade")
	private CmsLocalParamFacade cmsLocalParamFacade;

	@Override
	public CmsLocalParamDTO findCmsLocalParamById(CmsLocalParamDTO dto) {
		return cmsLocalParamFacade.findCmsLocalParamById(dto);
	}

	@Override
	public PageResult<CmsLocalParamDTO> findCmsLocalParamOfPage(CmsLocalParamDTO dto, Pagination page) {
		return cmsLocalParamFacade.findCmsLocalParamOfPage(dto, page);
	}

	@Override
	public List<CmsLocalParamDTO> findCmsLocalParamAll(CmsLocalParamDTO dto) {
		return cmsLocalParamFacade.findCmsLocalParamAll(dto);
	}

	@Override
	public Long insertCmsLocalParamWithTx(CmsLocalParamDTO dto) {
		return cmsLocalParamFacade.insertCmsLocalParamWithTx(dto);
	}

	@Override
	public int updateCmsLocalParamWithTx(CmsLocalParamDTO dto) {
		return cmsLocalParamFacade.updateCmsLocalParamWithTx(dto);
	}

	@Override
	public int deleteCmsLocalParamWithTx(CmsLocalParamDTO dto) {
		return cmsLocalParamFacade.deleteCmsLocalParamWithTx(dto);
	}


}
	