package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.FreightTemplateReadService;
import com.egeo.components.product.manage.read.FreightTemplateReadManage;
import com.egeo.components.product.converter.FreightTemplateConverter;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.components.product.po.FreightTemplatePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("freightTemplateReadService")
public class FreightTemplateReadServiceImpl  implements FreightTemplateReadService {
	@Autowired
	private FreightTemplateReadManage freightTemplateReadManage;

	@Override
	public FreightTemplateDTO findFreightTemplateById(FreightTemplateDTO dto) {
		FreightTemplatePO po = FreightTemplateConverter.toPO(dto);
		FreightTemplatePO list = freightTemplateReadManage.findFreightTemplateById(po);		
		return FreightTemplateConverter.toDTO(list);
	}

	@Override
	public PageResult<FreightTemplateDTO> findFreightTemplateOfPage(FreightTemplateDTO dto, Pagination page) {
		FreightTemplatePO po = FreightTemplateConverter.toPO(dto);
        PageResult<FreightTemplatePO> pageResult = freightTemplateReadManage.findFreightTemplateOfPage(po, page);
        
        List<FreightTemplateDTO> list = FreightTemplateConverter.toDTO(pageResult.getList());
        PageResult<FreightTemplateDTO> result = new PageResult<FreightTemplateDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<FreightTemplateDTO> findFreightTemplateAll(FreightTemplateDTO dto) {
		FreightTemplatePO po = FreightTemplateConverter.toPO(dto);
		List<FreightTemplatePO> list = freightTemplateReadManage.findFreightTemplateAll(po);		
		return FreightTemplateConverter.toDTO(list);
	}
}
	