package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.LimitRuleStoreReadService;
import com.egeo.components.order.manage.read.LimitRuleStoreReadManage;
import com.egeo.components.order.converter.LimitRuleStoreConverter;
import com.egeo.components.order.dto.LimitRuleStoreDTO;
import com.egeo.components.order.po.LimitRuleStorePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("limitRuleStoreReadService")
public class LimitRuleStoreReadServiceImpl  implements LimitRuleStoreReadService {
	@Autowired
	private LimitRuleStoreReadManage limitRuleStoreReadManage;

	@Override
	public LimitRuleStoreDTO findLimitRuleStoreById(LimitRuleStoreDTO dto) {
		LimitRuleStorePO po = LimitRuleStoreConverter.toPO(dto);
		LimitRuleStorePO list = limitRuleStoreReadManage.findLimitRuleStoreById(po);		
		return LimitRuleStoreConverter.toDTO(list);
	}

	@Override
	public PageResult<LimitRuleStoreDTO> findLimitRuleStoreOfPage(LimitRuleStoreDTO dto, Pagination page) {
		LimitRuleStorePO po = LimitRuleStoreConverter.toPO(dto);
        PageResult<LimitRuleStorePO> pageResult = limitRuleStoreReadManage.findLimitRuleStoreOfPage(po, page);
        
        List<LimitRuleStoreDTO> list = LimitRuleStoreConverter.toDTO(pageResult.getList());
        PageResult<LimitRuleStoreDTO> result = new PageResult<LimitRuleStoreDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LimitRuleStoreDTO> findLimitRuleStoreAll(LimitRuleStoreDTO dto) {
		LimitRuleStorePO po = LimitRuleStoreConverter.toPO(dto);
		List<LimitRuleStorePO> list = limitRuleStoreReadManage.findLimitRuleStoreAll(po);		
		return LimitRuleStoreConverter.toDTO(list);
	}

	@Override
	public Integer findLimitRuleStoreCount(LimitRuleStoreDTO limitRuleStoreDTO) {
		return limitRuleStoreReadManage.findLimitRuleStoreCount(LimitRuleStoreConverter.toPO(limitRuleStoreDTO));
	}
}
	