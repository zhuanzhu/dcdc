package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.CmsPageTabReadService;
import com.egeo.components.cms.service.write.CmsPageTabWriteService;
import com.egeo.components.cms.dto.CmsPageTabDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CmsPageTabFacade {
	
	@Resource
	private CmsPageTabReadService cmsPageTabReadService;
	
	@Resource
	private CmsPageTabWriteService cmsPageTabWriteService;
	
	
	public CmsPageTabDTO findCmsPageTabById(CmsPageTabDTO dto){
		
		return cmsPageTabReadService.findCmsPageTabById(dto);
	}

	public PageResult<CmsPageTabDTO> findCmsPageTabOfPage(CmsPageTabDTO dto,Pagination page){
		
		return cmsPageTabReadService.findCmsPageTabOfPage(dto, page);
		
	}

	public List<CmsPageTabDTO> findCmsPageTabAll(CmsPageTabDTO dto){
		
		return cmsPageTabReadService.findCmsPageTabAll(dto);
		
	}

	public Long insertCmsPageTabWithTx(CmsPageTabDTO dto){
		
		return cmsPageTabWriteService.insertCmsPageTabWithTx(dto);
	}

	public int updateCmsPageTabWithTx(CmsPageTabDTO dto){
		
		return cmsPageTabWriteService.updateCmsPageTabWithTx(dto);
	}

	public int deleteCmsPageTabWithTx(CmsPageTabDTO dto){
		
		return cmsPageTabWriteService.deleteCmsPageTabWithTx(dto);
		
	}

	public List<CmsPageTabDTO> findCmsPageTabFront(CmsPageTabDTO dto) {
		
		return cmsPageTabReadService.findCmsPageTabCondition(dto);
	}

	public List<CmsPageTabDTO> findDefaultCmsPageTabFront(Long platformId) {
		return cmsPageTabReadService.findDefaultCmsPageTabFront(platformId);
	}
}
	