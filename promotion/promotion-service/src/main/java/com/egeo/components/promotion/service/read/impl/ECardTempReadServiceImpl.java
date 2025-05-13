package com.egeo.components.promotion.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.ECardTempReadService;
import com.egeo.components.promotion.manage.read.ECardTempReadManage;
import com.egeo.components.promotion.converter.ECardTempConverter;
import com.egeo.components.promotion.dto.ECardTempDTO;
import com.egeo.components.promotion.po.ECardTempPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("eCardTempReadService")
public class ECardTempReadServiceImpl implements ECardTempReadService {
	@Autowired
	private ECardTempReadManage eCardTempReadManage;

	@Override
	public ECardTempDTO findECardTempById(ECardTempDTO dto) {
		ECardTempPO po = ECardTempConverter.toPO(dto);
		ECardTempPO list = eCardTempReadManage.findECardTempById(po);		
		return ECardTempConverter.toDTO(list);
	}

	@Override
	public PageResult<ECardTempDTO> findECardTempOfPage(ECardTempDTO dto, Pagination page) {
		ECardTempPO po = ECardTempConverter.toPO(dto);
        PageResult<ECardTempPO> pageResult = eCardTempReadManage.findECardTempOfPage(po, page);
        
        List<ECardTempDTO> list = ECardTempConverter.toDTO(pageResult.getList());
        PageResult<ECardTempDTO> result = new PageResult<ECardTempDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ECardTempDTO> findECardTempAll(ECardTempDTO dto) {
		ECardTempPO po = ECardTempConverter.toPO(dto);
		List<ECardTempPO> list = eCardTempReadManage.findECardTempAll(po);		
		return ECardTempConverter.toDTO(list);
	}
}
	