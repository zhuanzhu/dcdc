package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.UrlWhiteListCondition;
import com.egeo.components.user.converter.UrlWhiteListConverter;
import com.egeo.components.user.dto.UrlWhiteListDTO;
import com.egeo.components.user.manage.read.UrlWhiteListReadManage;
import com.egeo.components.user.po.UrlWhiteListPO;
import com.egeo.components.user.service.read.UrlWhiteListReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("urlWhiteListReadService")
public class UrlWhiteListReadServiceImpl implements UrlWhiteListReadService {
	@Autowired
	private UrlWhiteListReadManage urlWhiteListReadManage;

	@Override
	public UrlWhiteListDTO findUrlWhiteListById(UrlWhiteListDTO dto) {
		UrlWhiteListPO po = UrlWhiteListConverter.toPO(dto);
		List<UrlWhiteListCondition> list = urlWhiteListReadManage.findUrlWhiteListAll(po);
		if (StringUtils.isNotEmpty(list)) {
			return UrlWhiteListConverter.conditionToDTO(list.get(0));
		}
		return null;
	}

	@Override
	public PageResult<UrlWhiteListDTO> findUrlWhiteListOfPage(UrlWhiteListDTO dto, Pagination page) {
		UrlWhiteListPO po = UrlWhiteListConverter.toPO(dto);
        PageResult<UrlWhiteListCondition> pageResult = urlWhiteListReadManage.findUrlWhiteListOfPage(po, page);
        
        List<UrlWhiteListDTO> list = UrlWhiteListConverter.conditionToDTO(pageResult.getList());
        PageResult<UrlWhiteListDTO> result = new PageResult<UrlWhiteListDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UrlWhiteListDTO> findUrlWhiteListAll(UrlWhiteListDTO dto) {
		UrlWhiteListPO po = UrlWhiteListConverter.toPO(dto);
		List<UrlWhiteListCondition> list = urlWhiteListReadManage.findUrlWhiteListAll(po);		
		return UrlWhiteListConverter.conditionToDTO(list);
	}
	@Override
	public List<String> findUrlWhiteList(Long platformId) {
		List<String> list = urlWhiteListReadManage.findUrlWhiteList(platformId);
		return list;
	}

}
	