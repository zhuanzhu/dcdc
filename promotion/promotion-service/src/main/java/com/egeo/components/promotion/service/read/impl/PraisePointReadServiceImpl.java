package com.egeo.components.promotion.service.read.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.service.read.PraisePointReadService;
import com.egeo.components.promotion.manage.read.PraisePointReadManage;
import com.egeo.components.promotion.converter.PraisePointConverter;
import com.egeo.components.promotion.dto.PraisePointDTO;
 

@Service("praisePointReadService")
public class PraisePointReadServiceImpl implements PraisePointReadService {
	@Autowired
	private PraisePointReadManage praisePointReadManage;

	@Override
	public PraisePointDTO queryPraisePointByUserId(Long userId) {
		return PraisePointConverter.toDTO(praisePointReadManage.queryPraisePointByUserId(userId));
	}



}
	