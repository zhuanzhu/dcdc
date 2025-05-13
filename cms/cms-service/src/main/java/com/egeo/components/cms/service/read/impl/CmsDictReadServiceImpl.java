package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CmsDictReadService;
import com.egeo.components.cms.manage.read.CmsDictReadManage;
import com.egeo.components.cms.converter.CmsDictConverter;
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.components.cms.po.CmsDictPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("cmsDictReadService")
public class CmsDictReadServiceImpl  implements CmsDictReadService {
	@Autowired
	private CmsDictReadManage cmsDictReadManage;

	@Override
	public CmsDictDTO findCmsDictById(CmsDictDTO dto) {
		CmsDictPO po = CmsDictConverter.toPO(dto);
		CmsDictPO list = cmsDictReadManage.findCmsDictById(po);		
		return CmsDictConverter.toDTO(list);
	}

	@Override
	public PageResult<CmsDictDTO> findCmsDictOfPage(CmsDictDTO dto, Pagination page) {
		CmsDictPO po = CmsDictConverter.toPO(dto);
        PageResult<CmsDictPO> pageResult = cmsDictReadManage.findCmsDictOfPage(po, page);
        
        List<CmsDictDTO> list = CmsDictConverter.toDTO(pageResult.getList());
        PageResult<CmsDictDTO> result = new PageResult<CmsDictDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CmsDictDTO> findCmsDictAll(CmsDictDTO dto) {
		CmsDictPO po = CmsDictConverter.toPO(dto);
		List<CmsDictPO> list = cmsDictReadManage.findCmsDictAll(po);		
		return CmsDictConverter.toDTO(list);
	}
}
	