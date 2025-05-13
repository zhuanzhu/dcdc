package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.CmsLocalParamReadService;
import com.egeo.components.cms.service.write.CmsLocalParamWriteService;
import com.egeo.components.cms.dto.CmsLocalParamDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CmsLocalParamFacade {
	
	@Resource
	private CmsLocalParamReadService cmsLocalParamReadService;
	
	@Resource
	private CmsLocalParamWriteService cmsLocalParamWriteService;
	
	
	public CmsLocalParamDTO findCmsLocalParamById(CmsLocalParamDTO dto){
		
		return cmsLocalParamReadService.findCmsLocalParamById(dto);
	}

	public PageResult<CmsLocalParamDTO> findCmsLocalParamOfPage(CmsLocalParamDTO dto,Pagination page){
		
		return cmsLocalParamReadService.findCmsLocalParamOfPage(dto, page);
		
	}

	public List<CmsLocalParamDTO> findCmsLocalParamAll(CmsLocalParamDTO dto){
		
		return cmsLocalParamReadService.findCmsLocalParamAll(dto);
		
	}

	public Long insertCmsLocalParamWithTx(CmsLocalParamDTO dto){
		
		return cmsLocalParamWriteService.insertCmsLocalParamWithTx(dto);
	}

	public int updateCmsLocalParamWithTx(CmsLocalParamDTO dto){
		
		return cmsLocalParamWriteService.updateCmsLocalParamWithTx(dto);
	}

	public int deleteCmsLocalParamWithTx(CmsLocalParamDTO dto){
		
		return cmsLocalParamWriteService.deleteCmsLocalParamWithTx(dto);
		
	}

}
	