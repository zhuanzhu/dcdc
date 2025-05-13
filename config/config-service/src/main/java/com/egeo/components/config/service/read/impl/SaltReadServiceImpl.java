package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.SaltConverter;
import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.config.manage.read.SaltReadManage;
import com.egeo.components.config.po.SaltPO;
import com.egeo.components.config.service.read.SaltReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("saltReadService")
public class SaltReadServiceImpl  implements SaltReadService {
	@Autowired
	private SaltReadManage saltReadManage;

	@Override
	public SaltDTO findSaltById(SaltDTO dto) {
		SaltPO po = SaltConverter.toPO(dto);
		SaltPO list = saltReadManage.findSaltById(po);		
		return SaltConverter.toDTO(list);
	}

	@Override
	public PageResult<SaltDTO> findSaltOfPage(SaltDTO dto, Pagination page) {
		SaltPO po = SaltConverter.toPO(dto);
        PageResult<SaltPO> pageResult = saltReadManage.findSaltOfPage(po, page);
        
        List<SaltDTO> list = SaltConverter.toDTO(pageResult.getList());
        PageResult<SaltDTO> result = new PageResult<SaltDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SaltDTO> findSaltAll(SaltDTO dto) {
		SaltPO po = SaltConverter.toPO(dto);
		List<SaltPO> list = saltReadManage.findSaltAll(po);		
		return SaltConverter.toDTO(list);
	}

	@Override
	public SaltDTO querySaltByUUID(String uuid) {
		return SaltConverter.toDTO(saltReadManage.querySaltByUUID(uuid));
	}
}
	