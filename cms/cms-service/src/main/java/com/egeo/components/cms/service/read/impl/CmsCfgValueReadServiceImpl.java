package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsCfgValueReadService;
import com.egeo.components.cms.manage.read.CmsCfgValueReadManage;
import com.egeo.components.cms.converter.CmsCfgValueConverter;
import com.egeo.components.cms.dto.CmsCfgValueDTO;
import com.egeo.components.cms.po.CmsCfgValuePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsCfgValueReadService")
public class CmsCfgValueReadServiceImpl  implements CmsCfgValueReadService {
	@Autowired
	private CmsCfgValueReadManage cmsCfgValueReadManage;

	@Override
	public CmsCfgValueDTO findCmsCfgValueById(CmsCfgValueDTO dto) {
		CmsCfgValuePO po = CmsCfgValueConverter.toPO(dto);
		CmsCfgValuePO list = cmsCfgValueReadManage.findCmsCfgValueById(po);		
		return CmsCfgValueConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsCfgValueDTO> findCmsCfgValueOfPage(CmsCfgValueDTO dto, Pagination page) {
		CmsCfgValuePO po = CmsCfgValueConverter.toPO(dto);
        PageResult<CmsCfgValuePO> pageResult = cmsCfgValueReadManage.findCmsCfgValueOfPage(po, page);
        
        List<CmsCfgValueDTO> list = CmsCfgValueConverter.toDTO(pageResult.getList());
        PageResult<CmsCfgValueDTO> result = new PageResult<CmsCfgValueDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsCfgValueDTO> findCmsCfgValueAll(CmsCfgValueDTO dto) {
		CmsCfgValuePO po = CmsCfgValueConverter.toPO(dto);
		List<CmsCfgValuePO> list = cmsCfgValueReadManage.findCmsCfgValueAll(po);		
		return CmsCfgValueConverter.toDTO(list);
	}
}
	