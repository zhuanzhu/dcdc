package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoPackageTempReadService;
import com.egeo.components.order.manage.read.SoPackageTempReadManage;
import com.egeo.components.order.converter.SoPackageTempConverter;
import com.egeo.components.order.dto.SoPackageTempDTO;
import com.egeo.components.order.po.SoPackageTempPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soPackageTempReadService")
public class SoPackageTempReadServiceImpl  implements SoPackageTempReadService {
	@Autowired
	private SoPackageTempReadManage soPackageTempReadManage;

	@Override
	public SoPackageTempDTO findSoPackageTempById(SoPackageTempDTO dto) {
		SoPackageTempPO po = SoPackageTempConverter.toPO(dto);
		SoPackageTempPO list = soPackageTempReadManage.findSoPackageTempById(po);		
		return SoPackageTempConverter.toDTO(list);
	}

	@Override
	public PageResult<SoPackageTempDTO> findSoPackageTempOfPage(SoPackageTempDTO dto, Pagination page) {
		SoPackageTempPO po = SoPackageTempConverter.toPO(dto);
        PageResult<SoPackageTempPO> pageResult = soPackageTempReadManage.findSoPackageTempOfPage(po, page);
        
        List<SoPackageTempDTO> list = SoPackageTempConverter.toDTO(pageResult.getList());
        PageResult<SoPackageTempDTO> result = new PageResult<SoPackageTempDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoPackageTempDTO> findSoPackageTempAll(SoPackageTempDTO dto) {
		SoPackageTempPO po = SoPackageTempConverter.toPO(dto);
		List<SoPackageTempPO> list = soPackageTempReadManage.findSoPackageTempAll(po);		
		return SoPackageTempConverter.toDTO(list);
	}
}
	