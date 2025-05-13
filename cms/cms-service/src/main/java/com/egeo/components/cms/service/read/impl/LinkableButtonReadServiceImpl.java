package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.LinkableButtonReadService;
import com.egeo.components.cms.manage.read.LinkableButtonReadManage;
import com.egeo.components.cms.converter.LinkableButtonConverter;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.po.LinkableButtonPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("linkableButtonReadService")
public class LinkableButtonReadServiceImpl  implements LinkableButtonReadService {
	@Autowired
	private LinkableButtonReadManage linkableButtonReadManage;

	@Override
	public LinkableButtonDTO findLinkableButtonById(Long id) {
		LinkableButtonPO po = new LinkableButtonPO();
		po.setId(id);
		LinkableButtonPO list = linkableButtonReadManage.findLinkableButtonById(po);		
		return LinkableButtonConverter.toDTO(list);
	}

	@Override
	public PageResult<LinkableButtonDTO> findLinkableButtonOfPage(LinkableButtonDTO dto, Pagination page) {
		LinkableButtonPO po = LinkableButtonConverter.toPO(dto);
        PageResult<LinkableButtonPO> pageResult = linkableButtonReadManage.findLinkableButtonOfPage(po, page);
        
        List<LinkableButtonDTO> list = LinkableButtonConverter.toDTO(pageResult.getList());
        PageResult<LinkableButtonDTO> result = new PageResult<LinkableButtonDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LinkableButtonDTO> findLinkableButtonAll(LinkableButtonDTO dto) {
		LinkableButtonPO po = LinkableButtonConverter.toPO(dto);
		List<LinkableButtonPO> list = linkableButtonReadManage.findLinkableButtonAll(po);		
		return LinkableButtonConverter.toDTO(list);
	}
}
	