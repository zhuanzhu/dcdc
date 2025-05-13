package com.egeo.components.cms.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.cms.dto.PageTabDTO;
import com.egeo.components.cms.service.read.PageTabReadService;
import com.egeo.components.cms.service.write.PageTabWriteService;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class PageTabFacade {
	
	@Resource
	private PageTabReadService pageTabReadService;
	
	@Resource
	private PageTabWriteService pageTabWriteService;
	
	@Autowired
	private CompanyClient companyReadService;
	
	public PageTabDTO findPageTabById(PageTabDTO dto){
		
		return pageTabReadService.findPageTabById(dto);
	}

	public PageResult<PageTabDTO> findPageTabOfPage(PageTabDTO dto,Pagination page){
		
		return pageTabReadService.findPageTabOfPage(dto, page);
		
	}

	public List<PageTabDTO> findPageTabAll(PageTabDTO dto){
		
		return pageTabReadService.findPageTabAll(dto);
		
	}

	public Long insertPageTabWithTx(PageTabDTO dto){
		
		return pageTabWriteService.insertPageTabWithTx(dto);
	}

	public int updatePageTabWithTx(PageTabDTO dto){
		
		return pageTabWriteService.updatePageTabWithTx(dto);
	}

	public int deletePageTabWithTx(PageTabDTO dto){
		
		return pageTabWriteService.deletePageTabWithTx(dto);
		
	}

	public List<PageTabDTO> findPageTabAllForCheck(PageTabDTO dto) {
		
		return pageTabReadService.findPageTabAllForCheck(dto);
	}

	/**
	 * 模糊查询分页tab列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<PageTabDTO> findPageTabOfPageByBlurry(PageTabDTO dto, Pagination page) {
		
		return pageTabReadService.findPageTabOfPageByBlurry(dto, page);
	}

	/**
	 * 通过公司id集合查询所有tab分页
	 * @param dto
	 * @param companyIdList
	 * @return
	 */
	public List<PageTabDTO> findPageTabAllByCompanyId(PageTabDTO dto, List<Long> companyIdList) {
		
		return pageTabReadService.findPageTabAllByCompanyId(dto, companyIdList);
	}

}
	