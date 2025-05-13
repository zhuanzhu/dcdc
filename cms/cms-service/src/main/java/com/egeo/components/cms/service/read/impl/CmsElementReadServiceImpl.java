package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsElementReadService;
import com.egeo.components.cms.manage.read.CmsElementReadManage;
import com.egeo.components.cms.converter.CmsElementConverter;
import com.egeo.components.cms.dto.CmsElementDTO;
import com.egeo.components.cms.po.CmsElementPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsElementReadService")
public class CmsElementReadServiceImpl  implements CmsElementReadService {
	@Autowired
	private CmsElementReadManage cmsElementReadManage;

	@Override
	public CmsElementDTO findCmsElementById(CmsElementDTO dto) {
		CmsElementPO po = CmsElementConverter.toPO(dto);
		CmsElementPO list = cmsElementReadManage.findCmsElementById(po);		
		return CmsElementConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsElementDTO> findCmsElementOfPage(CmsElementDTO dto, Pagination page) {
		CmsElementPO po = CmsElementConverter.toPO(dto);
        PageResult<CmsElementPO> pageResult = cmsElementReadManage.findCmsElementOfPage(po, page);
        
        List<CmsElementDTO> list = CmsElementConverter.toDTO(pageResult.getList());
        PageResult<CmsElementDTO> result = new PageResult<CmsElementDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsElementDTO> findCmsElementAll(CmsElementDTO dto) {
		CmsElementPO po = CmsElementConverter.toPO(dto);
		List<CmsElementPO> list = cmsElementReadManage.findCmsElementAll(po);		
		return CmsElementConverter.toDTO(list);
	}

	@Override
	public List<CmsElementDTO> findCmsElementByTemplateId(Long id) {
		List<CmsElementPO> list = cmsElementReadManage.findCmsElementByTemplateId(id);
		return CmsElementConverter.toDTO(list);
	}

	@Override
	public List<CmsElementDTO> findMaxVersionByElementIdList(List<Long> elementIdList) {
		List<CmsElementPO> pos = cmsElementReadManage.findMaxVersionByElementIdList(elementIdList);
		return CmsElementConverter.toDTO(pos);
	}
}
	