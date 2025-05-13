package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.SuSerachKeywordReadService;
import com.egeo.components.product.manage.read.SuSerachKeywordReadManage;
import com.egeo.components.product.converter.SuSerachKeywordConverter;
import com.egeo.components.product.dto.SuSerachKeywordDTO;
import com.egeo.components.product.po.SuSerachKeywordPO;

import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;

@Service("suSerachKeywordReadService")
public class SuSerachKeywordReadServiceImpl  implements SuSerachKeywordReadService {
	@Autowired
	private SuSerachKeywordReadManage suSerachKeywordReadManage;

	@Override
	public SuSerachKeywordDTO findSuSerachKeywordById(SuSerachKeywordDTO dto) {
		SuSerachKeywordPO po = SuSerachKeywordConverter.toPO(dto);
		SuSerachKeywordPO list = suSerachKeywordReadManage.findSuSerachKeywordById(po);		
		return SuSerachKeywordConverter.toDTO(list);
	}

	@Override
	public PageResult<SuSerachKeywordDTO> findSuSerachKeywordOfPage(SuSerachKeywordDTO dto, Pagination page) {
		SuSerachKeywordPO po = SuSerachKeywordConverter.toPO(dto);
        PageResult<SuSerachKeywordPO> pageResult = suSerachKeywordReadManage.findSuSerachKeywordOfPage(po, page);
        
        List<SuSerachKeywordDTO> list = SuSerachKeywordConverter.toDTO(pageResult.getList());
        PageResult<SuSerachKeywordDTO> result = new PageResult<SuSerachKeywordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SuSerachKeywordDTO> findSuSerachKeywordAll(SuSerachKeywordDTO dto) {
		SuSerachKeywordPO po = SuSerachKeywordConverter.toPO(dto);
		List<SuSerachKeywordPO> list = suSerachKeywordReadManage.findSuSerachKeywordAll(po);		
		return SuSerachKeywordConverter.toDTO(list);
	}

	@Override
	public List<String> keywordByStandardUnitId(Long suId, Long platformId) {
		if(suId == null)
			throw new BusinessException("suId不能为空");
		if(platformId == null)
			throw new BusinessException("平台Id不能为空");
		return suSerachKeywordReadManage.keywordByStandardUnitId(suId, platformId);
	}
}
	