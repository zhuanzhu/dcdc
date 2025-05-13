package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ExternalLinkReadService;
import com.egeo.components.cms.manage.read.ExternalLinkReadManage;
import com.egeo.components.cms.converter.ExternalLinkConverter;
import com.egeo.components.cms.dto.ExternalLinkDTO;
import com.egeo.components.cms.po.ExternalLinkPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("externalLinkReadService")
public class ExternalLinkReadServiceImpl  implements ExternalLinkReadService {
	@Autowired
	private ExternalLinkReadManage externalLinkReadManage;

	@Override
	public ExternalLinkDTO findExternalLinkById(Long id) {
		ExternalLinkPO po = new ExternalLinkPO();
		po.setId(id);
		ExternalLinkPO list = externalLinkReadManage.findExternalLinkById(po);		
		return ExternalLinkConverter.toDTO(list);
	}

	@Override
	public PageResult<ExternalLinkDTO> findExternalLinkOfPage(ExternalLinkDTO dto, Pagination page) {
		ExternalLinkPO po = ExternalLinkConverter.toPO(dto);
        PageResult<ExternalLinkPO> pageResult = externalLinkReadManage.findExternalLinkOfPage(po, page);
        
        List<ExternalLinkDTO> list = ExternalLinkConverter.toDTO(pageResult.getList());
        PageResult<ExternalLinkDTO> result = new PageResult<ExternalLinkDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ExternalLinkDTO> findExternalLinkAll(ExternalLinkDTO dto) {
		ExternalLinkPO po = ExternalLinkConverter.toPO(dto);
		List<ExternalLinkPO> list = externalLinkReadManage.findExternalLinkAll(po);		
		return ExternalLinkConverter.toDTO(list);
	}
}
	