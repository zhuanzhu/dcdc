package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.components.user.service.read.UserWelfareReadService;
import com.egeo.components.user.service.write.UserWelfareWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UserWelfareFacade {
	
	@Resource
	private UserWelfareReadService userWelfareReadService;
	
	@Resource
	private UserWelfareWriteService userWelfareWriteService;
	
	
	public UserWelfareDTO findUserWelfareById(UserWelfareDTO dto){
		
		return userWelfareReadService.findUserWelfareById(dto);
	}

	public PageResult<UserWelfareDTO> findUserWelfareOfPage(UserWelfareDTO dto,Pagination page){
		
		return userWelfareReadService.findUserWelfareOfPage(dto, page);
		
	}

	public List<UserWelfareDTO> findUserWelfareAll(UserWelfareDTO dto){
		
		return userWelfareReadService.findUserWelfareAll(dto);
		
	}

	public Long insertUserWelfareWithTx(UserWelfareDTO dto){
		
		return userWelfareWriteService.insertUserWelfareWithTx(dto);
	}

	public int updateUserWelfareWithTx(UserWelfareDTO dto){
		
		return userWelfareWriteService.updateUserWelfareWithTx(dto);
	}

	public int deleteUserWelfareWithTx(UserWelfareDTO dto){
		
		return userWelfareWriteService.deleteUserWelfareWithTx(dto);
		
	}
	/**
	 * 根据用户id修改用户扩展表信息(部门、入职时间)
	 * @param dto
	 * @return
	 */
	public int updateUserWelfareByUserIdWithTx(UserWelfareDTO dto) {
		
		return userWelfareWriteService.updateUserWelfareByUserIdWithTx(dto);
	}

}
	