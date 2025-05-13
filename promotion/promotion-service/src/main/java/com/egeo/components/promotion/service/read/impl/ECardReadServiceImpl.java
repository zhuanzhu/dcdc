package com.egeo.components.promotion.service.read.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.ECardReadService;
import com.egeo.components.promotion.manage.read.ECardReadManage;
import com.egeo.components.promotion.converter.ECardConverter;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.po.ECardPO;
 
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("eCardReadService")
public class ECardReadServiceImpl implements ECardReadService {
	@Autowired
	private ECardReadManage eCardReadManage;

	@Override
	public ECardDTO findECardById(ECardDTO dto) {
		ECardPO po = ECardConverter.toPO(dto);
		ECardPO list = eCardReadManage.findECardById(po);		
		return ECardConverter.toDTO(list);
	}

	@Override
	public PageResult<ECardDTO> findECardOfPage(ECardDTO dto, Pagination page) {
		ECardPO po = ECardConverter.toPO(dto);
        PageResult<ECardPO> pageResult = eCardReadManage.findECardOfPage(po, page);
        
        List<ECardDTO> list = ECardConverter.toDTO(pageResult.getList());
        PageResult<ECardDTO> result = new PageResult<ECardDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ECardDTO> findECardAll(ECardDTO dto) {
		ECardPO po = ECardConverter.toPO(dto);
		List<ECardPO> list = eCardReadManage.findECardAll(po);		
		return ECardConverter.toDTO(list);
	}

	public List<ECardDTO> queryECardListByKey(Map<String, Object> keys) {
		List<ECardPO> list = eCardReadManage.queryECardListByKey(keys);
		return ECardConverter.toDTO(list);
	}
}
	