package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserQuitConverter;
import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.components.user.manage.read.UserQuitReadManage;
import com.egeo.components.user.po.UserQuitPO;
import com.egeo.components.user.service.read.UserQuitReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userQuitReadService")
public class UserQuitReadServiceImpl implements UserQuitReadService {
	@Autowired
	private UserQuitReadManage userQuitReadManage;

	@Override
	public UserQuitDTO findUserQuitById(UserQuitDTO dto) {
		UserQuitPO po = UserQuitConverter.toPO(dto);
		UserQuitPO list = userQuitReadManage.findUserQuitById(po);		
		return UserQuitConverter.toDTO(list);
	}

	@Override
	public PageResult<UserQuitDTO> findUserQuitOfPage(UserQuitDTO dto, Pagination page) {
		UserQuitPO po = UserQuitConverter.toPO(dto);
        PageResult<UserQuitPO> pageResult = userQuitReadManage.findUserQuitOfPage(po, page);
        
        List<UserQuitDTO> list = UserQuitConverter.toDTO(pageResult.getList());
        PageResult<UserQuitDTO> result = new PageResult<UserQuitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UserQuitDTO> findUserQuitAll(UserQuitDTO dto) {
		UserQuitPO po = UserQuitConverter.toPO(dto);
		List<UserQuitPO> list = userQuitReadManage.findUserQuitAll(po);		
		return UserQuitConverter.toDTO(list);
	}
}
	