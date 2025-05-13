package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.SuListReadService;
import com.egeo.components.cms.manage.read.SuListReadManage;
import com.egeo.components.cms.converter.SuListConverter;
import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.components.cms.po.SuListPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("suListReadService")
public class SuListReadServiceImpl  implements SuListReadService {
	@Autowired
	private SuListReadManage suListReadManage;

	@Override
	public SuListDTO findSuListById(SuListDTO dto) {
		SuListPO po = SuListConverter.toPO(dto);
		SuListPO list = suListReadManage.findSuListById(po);		
		return SuListConverter.toDTO(list);
	}

	@Override
	public PageResult<SuListDTO> findSuListOfPage(SuListDTO dto, Pagination page) {
		SuListPO po = SuListConverter.toPO(dto);
        PageResult<SuListPO> pageResult = suListReadManage.findSuListOfPage(po, page);
        
        List<SuListDTO> list = SuListConverter.toDTO(pageResult.getList());
        PageResult<SuListDTO> result = new PageResult<SuListDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SuListDTO> findSuListAll(SuListDTO dto) {
		SuListPO po = SuListConverter.toPO(dto);
		List<SuListPO> list = suListReadManage.findSuListAll(po);		
		return SuListConverter.toDTO(list);
	}

	@Override
	public SuListDTO querySuListByInstId(Long instId) {
		
		return SuListConverter.toDTO(suListReadManage.querySuListByInstId(instId));
	}
}
	