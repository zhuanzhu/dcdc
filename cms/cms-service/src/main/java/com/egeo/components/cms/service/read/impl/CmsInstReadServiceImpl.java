package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsInstReadService;
import com.egeo.components.cms.manage.read.CmsInstReadManage;
import com.egeo.components.cms.converter.CmsInstConverter;
import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.components.cms.po.CmsInstPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsInstReadService")
public class CmsInstReadServiceImpl  implements CmsInstReadService {
	@Autowired
	private CmsInstReadManage cmsInstReadManage;

	@Override
	public CmsInstDTO findCmsInstById(CmsInstDTO dto) {
		CmsInstPO po = CmsInstConverter.toPO(dto);
		CmsInstPO list = cmsInstReadManage.findCmsInstById(po);		
		return CmsInstConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsInstDTO> findCmsInstOfPage(CmsInstDTO dto, Pagination page) {
		CmsInstPO po = CmsInstConverter.toPO(dto);
        PageResult<CmsInstPO> pageResult = cmsInstReadManage.findCmsInstOfPage(po, page);
        
        List<CmsInstDTO> list = CmsInstConverter.toDTO(pageResult.getList());
        PageResult<CmsInstDTO> result = new PageResult<CmsInstDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsInstDTO> findCmsInstAll(CmsInstDTO dto) {
		CmsInstPO po = CmsInstConverter.toPO(dto);
		List<CmsInstPO> list = cmsInstReadManage.findCmsInstAll(po);		
		return CmsInstConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsInstDTO> findByPageAndCompanyOfPage(CmsInstDTO dto, Long companyId, Long companyAllId,
			Pagination page) {
		CmsInstPO po = CmsInstConverter.toPO(dto);
        PageResult<CmsInstPO> pageResult = cmsInstReadManage.findByPageAndCompanyOfPage(po, companyId, companyAllId, page);
        
        List<CmsInstDTO> list = CmsInstConverter.toDTO(pageResult.getList());
        PageResult<CmsInstDTO> result = new PageResult<CmsInstDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
}
	