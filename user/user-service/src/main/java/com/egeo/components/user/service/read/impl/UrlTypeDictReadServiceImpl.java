package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UrlTypeDictConverter;
import com.egeo.components.user.dto.UrlTypeDictDTO;
import com.egeo.components.user.manage.read.UrlTypeDictReadManage;
import com.egeo.components.user.po.UrlTypeDictPO;
import com.egeo.components.user.service.read.UrlTypeDictReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("urlTypeDictReadService")
public class UrlTypeDictReadServiceImpl implements UrlTypeDictReadService {
	@Autowired
	private UrlTypeDictReadManage urlTypeDictReadManage;

	@Override
	public UrlTypeDictDTO findUrlTypeDictById(UrlTypeDictDTO dto) {
		UrlTypeDictPO po = UrlTypeDictConverter.toPO(dto);
		UrlTypeDictPO list = urlTypeDictReadManage.findUrlTypeDictById(po);		
		return UrlTypeDictConverter.toDTO(list);
	}

	@Override
	public PageResult<UrlTypeDictDTO> findUrlTypeDictOfPage(UrlTypeDictDTO dto, Pagination page) {
		UrlTypeDictPO po = UrlTypeDictConverter.toPO(dto);
        PageResult<UrlTypeDictPO> pageResult = urlTypeDictReadManage.findUrlTypeDictOfPage(po, page);
        
        List<UrlTypeDictDTO> list = UrlTypeDictConverter.toDTO(pageResult.getList());
        PageResult<UrlTypeDictDTO> result = new PageResult<UrlTypeDictDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UrlTypeDictDTO> findUrlTypeDictAll(UrlTypeDictDTO dto) {
		UrlTypeDictPO po = UrlTypeDictConverter.toPO(dto);
		List<UrlTypeDictPO> list = urlTypeDictReadManage.findUrlTypeDictAll(po);		
		return UrlTypeDictConverter.toDTO(list);
	}
}
	