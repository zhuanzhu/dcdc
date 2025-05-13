package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.LinkableParamReadService;
import com.egeo.components.cms.manage.read.LinkableParamReadManage;
import com.egeo.components.cms.converter.LinkableParamConverter;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.cms.po.LinkableParamPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("linkableParamReadService")
public class LinkableParamReadServiceImpl  implements LinkableParamReadService {
	@Autowired
	private LinkableParamReadManage linkableParamReadManage;

	@Override
	public LinkableParamDTO findLinkableParamById(LinkableParamDTO dto) {
		LinkableParamPO po = LinkableParamConverter.toPO(dto);
		LinkableParamPO list = linkableParamReadManage.findLinkableParamById(po);		
		return LinkableParamConverter.toDTO(list);
	}

	@Override
	public PageResult<LinkableParamDTO> findLinkableParamOfPage(LinkableParamDTO dto, Pagination page) {
		LinkableParamPO po = LinkableParamConverter.toPO(dto);
        PageResult<LinkableParamPO> pageResult = linkableParamReadManage.findLinkableParamOfPage(po, page);
        
        List<LinkableParamDTO> list = LinkableParamConverter.toDTO(pageResult.getList());
        PageResult<LinkableParamDTO> result = new PageResult<LinkableParamDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LinkableParamDTO> findLinkableParamAll(LinkableParamDTO dto) {
		LinkableParamPO po = LinkableParamConverter.toPO(dto);
		List<LinkableParamPO> list = linkableParamReadManage.findLinkableParamAll(po);		
		return LinkableParamConverter.toDTO(list);
	}
}
	