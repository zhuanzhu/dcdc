package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserInformationReadConverter;
import com.egeo.components.user.dto.UserInformationReadDTO;
import com.egeo.components.user.manage.read.UserInformationReadReadManage;
import com.egeo.components.user.po.UserInformationReadPO;
import com.egeo.components.user.service.read.UserInformationReadReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userInformationReadReadService")
public class UserInformationReadReadServiceImpl implements UserInformationReadReadService {
	@Autowired
	private UserInformationReadReadManage userInformationReadReadManage;

	@Override
	public UserInformationReadDTO findUserInformationReadById(UserInformationReadDTO dto) {
		UserInformationReadPO po = UserInformationReadConverter.toPO(dto);
		UserInformationReadPO list = userInformationReadReadManage.findUserInformationReadById(po);		
		return UserInformationReadConverter.toDTO(list);
	}

	@Override
	public PageResult<UserInformationReadDTO> findUserInformationReadOfPage(UserInformationReadDTO dto, Pagination page) {
		UserInformationReadPO po = UserInformationReadConverter.toPO(dto);
        PageResult<UserInformationReadPO> pageResult = userInformationReadReadManage.findUserInformationReadOfPage(po, page);
        
        List<UserInformationReadDTO> list = UserInformationReadConverter.toDTO(pageResult.getList());
        PageResult<UserInformationReadDTO> result = new PageResult<UserInformationReadDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UserInformationReadDTO> findUserInformationReadAll(UserInformationReadDTO dto) {
		UserInformationReadPO po = UserInformationReadConverter.toPO(dto);
		List<UserInformationReadPO> list = userInformationReadReadManage.findUserInformationReadAll(po);		
		return UserInformationReadConverter.toDTO(list);
	}
}
	