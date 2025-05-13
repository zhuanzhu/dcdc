package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UserInformationReadDTO;
import com.egeo.components.user.service.read.UserInformationReadReadService;
import com.egeo.components.user.service.write.UserInformationReadWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UserInformationReadFacade {
	
	@Resource
	private UserInformationReadReadService userInformationReadReadService;
	
	@Resource
	private UserInformationReadWriteService userInformationReadWriteService;
	
	
	public UserInformationReadDTO findUserInformationReadById(UserInformationReadDTO dto){
		
		return userInformationReadReadService.findUserInformationReadById(dto);
	}

	public PageResult<UserInformationReadDTO> findUserInformationReadOfPage(UserInformationReadDTO dto,Pagination page){
		
		return userInformationReadReadService.findUserInformationReadOfPage(dto, page);
		
	}

	public List<UserInformationReadDTO> findUserInformationReadAll(UserInformationReadDTO dto){
		
		return userInformationReadReadService.findUserInformationReadAll(dto);
		
	}

	public Long insertUserInformationReadWithTx(UserInformationReadDTO dto){
		
		return userInformationReadWriteService.insertUserInformationReadWithTx(dto);
	}

	public int updateUserInformationReadWithTx(UserInformationReadDTO dto){
		
		return userInformationReadWriteService.updateUserInformationReadWithTx(dto);
	}

	public int deleteUserInformationReadWithTx(UserInformationReadDTO dto){
		
		return userInformationReadWriteService.deleteUserInformationReadWithTx(dto);
		
	}
	/**
	 * 根据消息id逻辑删除
	 * @param userInformationId
	 * @return
	 */
	public int deleteByUserInformationIdWithTx(Long userInformationId) {
		// TODO Auto-generated method stub
		return userInformationReadWriteService.deleteByUserInformationIdWithTx(userInformationId);
	}

}
	