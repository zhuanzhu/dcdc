package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsLocalParamReadService;
import com.egeo.components.cms.manage.read.CmsLocalParamReadManage;
import com.egeo.components.cms.converter.CmsLocalParamConverter;
import com.egeo.components.cms.dto.CmsLocalParamDTO;
import com.egeo.components.cms.po.CmsLocalParamPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsLocalParamReadService")
public class CmsLocalParamReadServiceImpl  implements CmsLocalParamReadService {
	@Autowired
	private CmsLocalParamReadManage cmsLocalParamReadManage;

	@Override
	public CmsLocalParamDTO findCmsLocalParamById(CmsLocalParamDTO dto) {
		CmsLocalParamPO po = CmsLocalParamConverter.toPO(dto);
		CmsLocalParamPO list = cmsLocalParamReadManage.findCmsLocalParamById(po);		
		return CmsLocalParamConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsLocalParamDTO> findCmsLocalParamOfPage(CmsLocalParamDTO dto, Pagination page) {
		CmsLocalParamPO po = CmsLocalParamConverter.toPO(dto);
        PageResult<CmsLocalParamPO> pageResult = cmsLocalParamReadManage.findCmsLocalParamOfPage(po, page);
        
        List<CmsLocalParamDTO> list = CmsLocalParamConverter.toDTO(pageResult.getList());
        PageResult<CmsLocalParamDTO> result = new PageResult<CmsLocalParamDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsLocalParamDTO> findCmsLocalParamAll(CmsLocalParamDTO dto) {
		CmsLocalParamPO po = CmsLocalParamConverter.toPO(dto);
		List<CmsLocalParamPO> list = cmsLocalParamReadManage.findCmsLocalParamAll(po);		
		return CmsLocalParamConverter.toDTO(list);
	}
}
	