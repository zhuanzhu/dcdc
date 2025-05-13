package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.LinkableButtonPageReadService;
import com.egeo.components.cms.manage.read.LinkableButtonPageReadManage;
import com.egeo.components.cms.condition.LinkableButtonPageCondition;
import com.egeo.components.cms.converter.LinkableButtonPageConverter;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.po.LinkableButtonPagePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("linkableButtonPageReadService")
public class LinkableButtonPageReadServiceImpl  implements LinkableButtonPageReadService {
	@Autowired
	private LinkableButtonPageReadManage linkableButtonPageReadManage;

	@Override
	public LinkableButtonPageDTO findLinkableButtonPageById(LinkableButtonPageDTO dto) {
		LinkableButtonPagePO po = LinkableButtonPageConverter.toPO(dto);
		LinkableButtonPagePO list = linkableButtonPageReadManage.findLinkableButtonPageById(po);		
		return LinkableButtonPageConverter.toDTO(list);
	}

	@Override
	public PageResult<LinkableButtonPageDTO> findLinkableButtonPageOfPage(LinkableButtonPageDTO dto, Pagination page) {
		LinkableButtonPagePO po = LinkableButtonPageConverter.toPO(dto);
        PageResult<LinkableButtonPagePO> pageResult = linkableButtonPageReadManage.findLinkableButtonPageOfPage(po, page);
        
        List<LinkableButtonPageDTO> list = LinkableButtonPageConverter.toDTO(pageResult.getList());
        PageResult<LinkableButtonPageDTO> result = new PageResult<LinkableButtonPageDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LinkableButtonPageDTO> findLinkableButtonPageAll(LinkableButtonPageDTO dto) {
		LinkableButtonPagePO po = LinkableButtonPageConverter.toPO(dto);
		List<LinkableButtonPagePO> list = linkableButtonPageReadManage.findLinkableButtonPageAll(po);		
		return LinkableButtonPageConverter.toDTO(list);
	}

	@Override
	public List<LinkableButtonPageDTO> findCmsPageByLinkableId(Long linkableId) {
		List<LinkableButtonPageCondition> list = linkableButtonPageReadManage.findCmsPageByLinkableId(linkableId);
		return LinkableButtonPageConverter.conditionToDTO(list);
	}
}
	