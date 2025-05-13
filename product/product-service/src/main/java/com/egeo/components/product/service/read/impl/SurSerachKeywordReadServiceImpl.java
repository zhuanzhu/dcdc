package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SurSerachKeywordReadService;
import com.egeo.components.product.manage.read.SurSerachKeywordReadManage;
import com.egeo.components.product.converter.SurSerachKeywordConverter;
import com.egeo.components.product.dto.SurSerachKeywordDTO;
import com.egeo.components.product.po.SurSerachKeywordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("surSerachKeywordReadService")
public class SurSerachKeywordReadServiceImpl  implements SurSerachKeywordReadService {
	@Autowired
	private SurSerachKeywordReadManage surSerachKeywordReadManage;

	@Override
	public SurSerachKeywordDTO findSurSerachKeywordById(SurSerachKeywordDTO dto) {
		SurSerachKeywordPO po = SurSerachKeywordConverter.toPO(dto);
		SurSerachKeywordPO list = surSerachKeywordReadManage.findSurSerachKeywordById(po);		
		return SurSerachKeywordConverter.toDTO(list);
	}

	@Override
	public PageResult<SurSerachKeywordDTO> findSurSerachKeywordOfPage(SurSerachKeywordDTO dto, Pagination page) {
		SurSerachKeywordPO po = SurSerachKeywordConverter.toPO(dto);
        PageResult<SurSerachKeywordPO> pageResult = surSerachKeywordReadManage.findSurSerachKeywordOfPage(po, page);
        
        List<SurSerachKeywordDTO> list = SurSerachKeywordConverter.toDTO(pageResult.getList());
        PageResult<SurSerachKeywordDTO> result = new PageResult<SurSerachKeywordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SurSerachKeywordDTO> findSurSerachKeywordAll(SurSerachKeywordDTO dto) {
		SurSerachKeywordPO po = SurSerachKeywordConverter.toPO(dto);
		List<SurSerachKeywordPO> list = surSerachKeywordReadManage.findSurSerachKeywordAll(po);		
		return SurSerachKeywordConverter.toDTO(list);
	}
}
	