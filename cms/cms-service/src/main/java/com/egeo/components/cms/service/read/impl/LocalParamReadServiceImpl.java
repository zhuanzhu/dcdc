package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.LocalParamReadService;
import com.egeo.components.cms.manage.read.LocalParamReadManage;
import com.egeo.components.cms.converter.LocalParamConverter;
import com.egeo.components.cms.dto.LocalParamDTO;
import com.egeo.components.cms.po.LocalParamPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("localParamReadService")
public class LocalParamReadServiceImpl  implements LocalParamReadService {
	@Autowired
	private LocalParamReadManage localParamReadManage;

	@Override
	public LocalParamDTO findLocalParamById(LocalParamDTO dto) {
		LocalParamPO po = LocalParamConverter.toPO(dto);
		LocalParamPO list = localParamReadManage.findLocalParamById(po);		
		return LocalParamConverter.toDTO(list);
	}

	@Override
	public PageResult<LocalParamDTO> findLocalParamOfPage(LocalParamDTO dto, Pagination page) {
		LocalParamPO po = LocalParamConverter.toPO(dto);
        PageResult<LocalParamPO> pageResult = localParamReadManage.findLocalParamOfPage(po, page);
        
        List<LocalParamDTO> list = LocalParamConverter.toDTO(pageResult.getList());
        PageResult<LocalParamDTO> result = new PageResult<LocalParamDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LocalParamDTO> findLocalParamAll(LocalParamDTO dto) {
		LocalParamPO po = LocalParamConverter.toPO(dto);
		List<LocalParamPO> list = localParamReadManage.findLocalParamAll(po);		
		return LocalParamConverter.toDTO(list);
	}
}
	