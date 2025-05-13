package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserQuitTempConverter;
import com.egeo.components.user.dto.UserQuitTempDTO;
import com.egeo.components.user.manage.read.UserQuitTempReadManage;
import com.egeo.components.user.po.UserQuitTempPO;
import com.egeo.components.user.service.read.UserQuitTempReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userQuitTempReadService")
public class UserQuitTempReadServiceImpl implements UserQuitTempReadService {
	@Autowired
	private UserQuitTempReadManage userQuitTempReadManage;

	@Override
	public UserQuitTempDTO findUserQuitTempById(UserQuitTempDTO dto) {
		UserQuitTempPO po = UserQuitTempConverter.toPO(dto);
		UserQuitTempPO list = userQuitTempReadManage.findUserQuitTempById(po);		
		return UserQuitTempConverter.toDTO(list);
	}

	@Override
	public PageResult<UserQuitTempDTO> findUserQuitTempOfPage(UserQuitTempDTO dto, Pagination page) {
		UserQuitTempPO po = UserQuitTempConverter.toPO(dto);
        PageResult<UserQuitTempPO> pageResult = userQuitTempReadManage.findUserQuitTempOfPage(po, page);
        
        List<UserQuitTempDTO> list = UserQuitTempConverter.toDTO(pageResult.getList());
        PageResult<UserQuitTempDTO> result = new PageResult<UserQuitTempDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UserQuitTempDTO> findUserQuitTempAll(UserQuitTempDTO dto) {
		UserQuitTempPO po = UserQuitTempConverter.toPO(dto);
		List<UserQuitTempPO> list = userQuitTempReadManage.findUserQuitTempAll(po);		
		return UserQuitTempConverter.toDTO(list);
	}
}
	