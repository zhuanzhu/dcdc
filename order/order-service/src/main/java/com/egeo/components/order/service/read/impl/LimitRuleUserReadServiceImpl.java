package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.LimitRuleUserReadService;
import com.egeo.components.order.manage.read.LimitRuleUserReadManage;
import com.egeo.components.order.converter.LimitRuleUserConverter;
import com.egeo.components.order.dto.LimitRuleUserDTO;
import com.egeo.components.order.po.LimitRuleUserPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("limitRuleUserReadService")
public class LimitRuleUserReadServiceImpl  implements LimitRuleUserReadService {
	@Autowired
	private LimitRuleUserReadManage limitRuleUserReadManage;

	@Override
	public LimitRuleUserDTO findLimitRuleUserById(LimitRuleUserDTO dto) {
		LimitRuleUserPO po = LimitRuleUserConverter.toPO(dto);
		LimitRuleUserPO list = limitRuleUserReadManage.findLimitRuleUserById(po);		
		return LimitRuleUserConverter.toDTO(list);
	}

	@Override
	public PageResult<LimitRuleUserDTO> findLimitRuleUserOfPage(LimitRuleUserDTO dto, Pagination page) {
		LimitRuleUserPO po = LimitRuleUserConverter.toPO(dto);
        PageResult<LimitRuleUserPO> pageResult = limitRuleUserReadManage.findLimitRuleUserOfPage(po, page);
        
        List<LimitRuleUserDTO> list = LimitRuleUserConverter.toDTO(pageResult.getList());
        PageResult<LimitRuleUserDTO> result = new PageResult<LimitRuleUserDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<LimitRuleUserDTO> findLimitRuleUserAll(LimitRuleUserDTO dto) {
		LimitRuleUserPO po = LimitRuleUserConverter.toPO(dto);
		List<LimitRuleUserPO> list = limitRuleUserReadManage.findLimitRuleUserAll(po);		
		return LimitRuleUserConverter.toDTO(list);
	}

	@Override
	public Integer findLimitRuleAllByParam(LimitRuleUserDTO limitRuleUserDTO) {
		return limitRuleUserReadManage.findLimitRuleAllByParam(LimitRuleUserConverter.toPO(limitRuleUserDTO));
	}
}
	