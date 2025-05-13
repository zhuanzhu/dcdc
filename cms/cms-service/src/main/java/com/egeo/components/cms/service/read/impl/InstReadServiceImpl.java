package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.InstReadService;
import com.egeo.components.cms.manage.read.InstReadManage;
import com.egeo.components.cms.converter.InstConverter;
import com.egeo.components.cms.dto.InstDTO;
import com.egeo.components.cms.po.InstPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("instReadService")
public class InstReadServiceImpl  implements InstReadService {
	@Autowired
	private InstReadManage instReadManage;

	@Override
	public InstDTO findInstById(Long id) {
		InstPO po = new InstPO();
		po.setId(id);
		InstPO list = instReadManage.findInstById(po);		
		return InstConverter.toDTO(list);
	}

	@Override
	public PageResult<InstDTO> findInstOfPage(InstDTO dto, Pagination page) {
		InstPO po = InstConverter.toPO(dto);
        PageResult<InstPO> pageResult = instReadManage.findInstOfPage(po, page);
        
        List<InstDTO> list = InstConverter.toDTO(pageResult.getList());
        PageResult<InstDTO> result = new PageResult<InstDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InstDTO> findInstAll(InstDTO dto) {
		InstPO po = InstConverter.toPO(dto);
		List<InstPO> list = instReadManage.findInstAll(po);		
		return InstConverter.toDTO(list);
	}

	@Override
	public InstDTO queryInstByElementId(Long elementId) {
		return InstConverter.toDTO(instReadManage.queryInstByElementId(elementId));
	}
}
	