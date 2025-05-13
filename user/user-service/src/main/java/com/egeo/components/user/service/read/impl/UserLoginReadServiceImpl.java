package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.UserLoginCondition;
import com.egeo.components.user.converter.UserLoginConverter;
import com.egeo.components.user.dto.UserLoginDTO;
import com.egeo.components.user.manage.read.UserLoginReadManage;
import com.egeo.components.user.po.UserLoginPO;
import com.egeo.components.user.service.read.UserLoginReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userLoginReadService")
public class UserLoginReadServiceImpl implements UserLoginReadService {
	@Autowired
	private UserLoginReadManage userLoginReadManage;

	@Override
	public PageResult<UserLoginDTO> findOfPage(UserLoginDTO dto, Pagination page) {
		UserLoginCondition condition = UserLoginConverter.toCondition(dto);
		PageResult<UserLoginPO> pageResult = userLoginReadManage.findOfPage(condition,page);
		
		List<UserLoginDTO> list = UserLoginConverter.toDTO(pageResult.getList());
        PageResult<UserLoginDTO> result = new PageResult<UserLoginDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}


	@Override
	public List<UserLoginDTO> findByUserIds(List<Long> userIds, Long startTime, Long endTime) {
		List<UserLoginPO> list = userLoginReadManage.findByUserIds(userIds,startTime,endTime);
		return UserLoginConverter.toDTO(list);
	}
	
}
	