package com.egeo.components.finance.service.read.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.UserAccountConverter;
import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.finance.manage.read.UserAccountReadManage;
import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.components.finance.service.read.UserAccountReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userAccountReadService")
public class UserAccountReadServiceImpl  implements UserAccountReadService {
	@Autowired
	private UserAccountReadManage userAccountReadManage;

	@Override
	public UserAccountDTO findUserAccountById(UserAccountDTO dto) {
		UserAccountPO po = UserAccountConverter.toPO(dto);
		UserAccountPO list = userAccountReadManage.findUserAccountById(po);		
		return UserAccountConverter.toDTO(list);
	}

	@Override
	public PageResult<UserAccountDTO> findUserAccountOfPage(UserAccountDTO dto, Pagination page) {
		UserAccountPO po = UserAccountConverter.toPO(dto);
        PageResult<UserAccountPO> pageResult = userAccountReadManage.findUserAccountOfPage(po, page);
        
        List<UserAccountDTO> list = UserAccountConverter.toDTO(pageResult.getList());
        PageResult<UserAccountDTO> result = new PageResult<UserAccountDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UserAccountDTO> findUserAccountAll(UserAccountDTO dto) {
		UserAccountPO po = UserAccountConverter.toPO(dto);
		List<UserAccountPO> list = userAccountReadManage.findUserAccountAll(po);		
		return UserAccountConverter.toDTO(list);
	}

	@Override
	public List<UserAccountDTO> queryUserAccountByUserId(Long userId) {
		return UserAccountConverter.toDTO(userAccountReadManage.queryUserAccountByUserId(userId));
	}

	@Override
	public UserAccountDTO queryUserAccountByUserIdAndType(Long id, Integer type) {
		return UserAccountConverter.toDTO(userAccountReadManage.queryUserAccountByUserIdAndType(id,type));
	}

	@Override
	public List<UserAccountDTO> queryUserAccountByUserIds(List<Long> userIdList) {
		return UserAccountConverter.toDTO(userAccountReadManage.queryUserAccountByUserIds(userIdList));
	}

	@Override
	public List<UserAccountDTO> queryUserAccountByParam(UserAccountDTO dto){
		UserAccountPO po = UserAccountConverter.toPO(dto);
		List<UserAccountPO> list = userAccountReadManage.queryUserAccountByParam(po);
		return UserAccountConverter.toDTO(list);
	}

	@Override
	public BigDecimal findBeforeDisabledBalance(Long userId){
		BigDecimal sum = userAccountReadManage.findBeforeDisabledBalance(userId);
		return sum;
	}


}
	