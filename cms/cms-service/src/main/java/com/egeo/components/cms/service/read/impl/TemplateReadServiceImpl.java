package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.TemplateReadService;
import com.egeo.components.cms.manage.read.TemplateReadManage;
import com.egeo.components.cms.converter.TemplateConverter;
import com.egeo.components.cms.dto.TemplateDTO;
import com.egeo.components.cms.po.TemplatePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("templateReadService")
public class TemplateReadServiceImpl  implements TemplateReadService {
	@Autowired
	private TemplateReadManage templateReadManage;

	@Override
	public TemplateDTO findTemplateById(Long id) {
		TemplatePO po = new TemplatePO();
		po.setId(id);
		TemplatePO res = templateReadManage.findTemplateById(po);
		return TemplateConverter.toDTO(res);
	}

	@Override
	public PageResult<TemplateDTO> findTemplateOfPage(TemplateDTO dto, Pagination page) {
		TemplatePO po = TemplateConverter.toPO(dto);
		PageResult<TemplatePO> pageResult = templateReadManage.findTemplateOfPage(po, page);

		List<TemplateDTO> list = TemplateConverter.toDTO(pageResult.getList());
		PageResult<TemplateDTO> result = new PageResult<TemplateDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<TemplateDTO> findTemplateAll(TemplateDTO dto) {
		TemplatePO po = TemplateConverter.toPO(dto);
		List<TemplatePO> list = templateReadManage.findTemplateAll(po);
		return TemplateConverter.toDTO(list);
	}

	@Override
	public PageResult<TemplateDTO> queryTemplatePage(String name, Integer type, Pagination page, Integer status,
			Integer clientType, Integer companyType,Long platformId) {
		PageResult<TemplatePO> poPage = templateReadManage.queryTemplatePage(name, type, page, status, clientType,
				companyType,platformId);
		PageResult<TemplateDTO> dtoPage = new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(TemplateConverter.toDTO(poPage.getList()));
		return dtoPage;
	}

	@Override
	public TemplateDTO queryInUseTemplateByClientType(Integer clientType, Integer type, Integer companyType,Long platformId) {

		return TemplateConverter.toDTO(templateReadManage.queryInUseTemplateByClientType(clientType, type,companyType,platformId));
	}
}
