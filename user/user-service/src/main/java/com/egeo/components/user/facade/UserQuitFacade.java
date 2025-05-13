package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.components.user.service.read.UserQuitReadService;
import com.egeo.components.user.service.write.UserQuitWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UserQuitFacade {
	
	@Resource
	private UserQuitReadService userQuitReadService;
	
	@Resource
	private UserQuitWriteService userQuitWriteService;
	
	
	public UserQuitDTO findUserQuitById(UserQuitDTO dto){
		
		return userQuitReadService.findUserQuitById(dto);
	}

	public PageResult<UserQuitDTO> findUserQuitOfPage(UserQuitDTO dto,Pagination page){
		
		return userQuitReadService.findUserQuitOfPage(dto, page);
		
	}

	public List<UserQuitDTO> findUserQuitAll(UserQuitDTO dto){
		
		return userQuitReadService.findUserQuitAll(dto);
		
	}

	public Long insertUserQuitWithTx(UserQuitDTO dto){
		
		return userQuitWriteService.insertUserQuitWithTx(dto);
	}

	public int updateUserQuitWithTx(UserQuitDTO dto){
		
		return userQuitWriteService.updateUserQuitWithTx(dto);
	}

	public int deleteUserQuitWithTx(UserQuitDTO dto){
		
		return userQuitWriteService.deleteUserQuitWithTx(dto);
		
	}

}
	