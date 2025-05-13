package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsPageReadService;
import com.egeo.components.cms.manage.read.CmsPageReadManage;
import com.egeo.components.cms.condition.CmsPageCondition;
import com.egeo.components.cms.converter.CmsPageConverter;
import com.egeo.components.cms.dto.CmsPageDTO;
import com.egeo.components.cms.po.CmsPagePO;
import com.egeo.components.cms.po.CmsTemplatePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsPageReadService")
public class CmsPageReadServiceImpl  implements CmsPageReadService {
	@Autowired
	private CmsPageReadManage cmsPageReadManage;

	@Override
	public CmsPageDTO findCmsPageById(CmsPageDTO dto) {
		CmsPagePO po = CmsPageConverter.toPO(dto);
		CmsPagePO list = cmsPageReadManage.findCmsPageById(po);		
		return CmsPageConverter.toDTO(list);
	}


	@Override
	public List<CmsPageDTO> findCmsPageAll(CmsPageDTO dto) {
		CmsPagePO po = CmsPageConverter.toPO(dto);
		List<CmsPagePO> list = cmsPageReadManage.findCmsPageAll(po);		
		return CmsPageConverter.toDTO(list);
	}
	
	@Override
	public List<CmsPageDTO> findCmsPageAllByClientType(CmsPageDTO dto) {
		CmsPageCondition condition = CmsPageConverter.dtoToCondition(dto);
		List<CmsPageCondition> list = cmsPageReadManage.findCmsPageAllByClientType(condition);	
		
		return CmsPageConverter.conditionToDTO(list);
	}

	@Override
	public int findSupportPageByVersionCode(Long pageId, Long platformId, Integer androidVersionCode,
			Integer iosVersionCode) {
		return cmsPageReadManage.findSupportPageByVersionCode(pageId, platformId, androidVersionCode, iosVersionCode);
	}

	@Override
	public Long findSupportPageByPageTypeAndVersionCode(Integer pageType, Long platformId, Integer androidVersionCode,
			Integer iosVersionCode, Long companyId, Long companyAllId) {
		return cmsPageReadManage.findSupportPageByPageTypeAndVersionCode(pageType, platformId, androidVersionCode, iosVersionCode, companyId, companyAllId);
	}

	@Override
	public PageResult<CmsPageDTO> findCmsPageOfPage(CmsPageDTO dto, Pagination page) {
		
		CmsPageCondition condition = CmsPageConverter.dtoToCondition(dto);
        PageResult<CmsPageCondition> pageResult = cmsPageReadManage.findCmsPageOfPage(condition, page);
        
        List<CmsPageDTO> list = CmsPageConverter.conditionToDTO(pageResult.getList());
        PageResult<CmsPageDTO> result = new PageResult<CmsPageDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}


	@Override
	public CmsPageDTO findCmsPageByPageId(CmsPageDTO dto) {
		CmsPagePO po = CmsPageConverter.toPO(dto);
		CmsPageCondition condition = cmsPageReadManage.findCmsPageByPageId(po);
		return CmsPageConverter.conditionToDTO(condition);
	}


	@Override
	public List<CmsPageDTO> findCmsPageByCompanyIdAndVersion(CmsPageDTO dto) {
		CmsPageCondition condition = CmsPageConverter.dtoToCondition(dto);
		return CmsPageConverter.conditionToDTO(cmsPageReadManage.findCmsPageByCompanyIdAndVersion(condition));
	}
}
	