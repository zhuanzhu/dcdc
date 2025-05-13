package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.KeywordConverter;
import com.egeo.components.config.dto.KeywordDTO;
import com.egeo.components.config.manage.read.KeywordReadManage;
import com.egeo.components.config.po.KeywordPO;
import com.egeo.components.config.service.read.KeywordReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("keywordReadService")
public class KeywordReadServiceImpl implements KeywordReadService {
	@Autowired
	private KeywordReadManage keywordReadManage;

	@Override
	public KeywordDTO findKeywordById(KeywordDTO dto) {
		KeywordPO po = KeywordConverter.toPO(dto);
		KeywordPO list = keywordReadManage.findKeywordById(po);		
		return KeywordConverter.toDTO(list);
	}

	@Override
	public PageResult<KeywordDTO> findKeywordOfPage(KeywordDTO dto, Pagination page) {
		KeywordPO po = KeywordConverter.toPO(dto);
        PageResult<KeywordPO> pageResult = keywordReadManage.findKeywordOfPage(po, page);
        
        List<KeywordDTO> list = KeywordConverter.toDTO(pageResult.getList());
        PageResult<KeywordDTO> result = new PageResult<KeywordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<KeywordDTO> findKeywordAll(KeywordDTO dto) {
		KeywordPO po = KeywordConverter.toPO(dto);
		List<KeywordPO> list = keywordReadManage.findKeywordAll(po);		
		return KeywordConverter.toDTO(list);
	}
}
	