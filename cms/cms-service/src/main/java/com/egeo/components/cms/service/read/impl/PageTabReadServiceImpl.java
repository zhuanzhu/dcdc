package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.PageTabReadService;
import com.egeo.components.cms.manage.read.PageTabReadManage;
import com.egeo.components.cms.converter.PageTabConverter;
import com.egeo.components.cms.dto.PageTabDTO;
import com.egeo.components.cms.po.PageTabPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("pageTabReadService")
public class PageTabReadServiceImpl  implements PageTabReadService {
	@Autowired
	private PageTabReadManage pageTabReadManage;

	@Override
	public PageTabDTO findPageTabById(PageTabDTO dto) {
		PageTabPO po = PageTabConverter.toPO(dto);
		PageTabPO list = pageTabReadManage.findPageTabById(po);		
		return PageTabConverter.toDTO(list);
	}

	@Override
	public PageResult<PageTabDTO> findPageTabOfPage(PageTabDTO dto, Pagination page) {
		PageTabPO po = PageTabConverter.toPO(dto);
        PageResult<PageTabPO> pageResult = pageTabReadManage.findPageTabOfPage(po, page);
        
        List<PageTabDTO> list = PageTabConverter.toDTO(pageResult.getList());
        PageResult<PageTabDTO> result = new PageResult<PageTabDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<PageTabDTO> findPageTabAll(PageTabDTO dto) {
		PageTabPO po = PageTabConverter.toPO(dto);
		List<PageTabPO> list = pageTabReadManage.findPageTabAll(po);		
		return PageTabConverter.toDTO(list);
	}

	@Override
	public List<PageTabDTO> findPageTabAllForCheck(PageTabDTO dto) {
		PageTabPO po = PageTabConverter.toPO(dto);
		List<PageTabPO> list = pageTabReadManage.findPageTabAllForCheck(po);		
		return PageTabConverter.toDTO(list);
	}

	@Override
	public PageResult<PageTabDTO> findPageTabOfPageByBlurry(PageTabDTO dto, Pagination page) {
		PageTabPO po = PageTabConverter.toPO(dto);
        PageResult<PageTabPO> pageResult = pageTabReadManage.findPageTabOfPageByBlurry(po, page);
        
        List<PageTabDTO> list = PageTabConverter.toDTO(pageResult.getList());
        PageResult<PageTabDTO> result = new PageResult<PageTabDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<PageTabDTO> findPageTabAllByCompanyId(PageTabDTO dto, List<Long> companyIdList) {
		PageTabPO po = PageTabConverter.toPO(dto);
		List<PageTabPO> list = pageTabReadManage.findPageTabAllByCompanyId(po, companyIdList);		
		return PageTabConverter.toDTO(list);
	}
}
	