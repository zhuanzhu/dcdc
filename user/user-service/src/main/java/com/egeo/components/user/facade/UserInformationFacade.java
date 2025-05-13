package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UserInformationDTO;
import com.egeo.components.user.service.read.UserInformationReadService;
import com.egeo.components.user.service.write.UserInformationWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UserInformationFacade {
	
	@Resource
	private UserInformationReadService userInformationReadService;
	
	@Resource
	private UserInformationWriteService userInformationWriteService;
	
	
	public UserInformationDTO findUserInformationById(UserInformationDTO dto){
		
		return userInformationReadService.findUserInformationById(dto);
	}

	public PageResult<UserInformationDTO> findUserInformationOfPage(UserInformationDTO dto,Pagination page){
		
		return userInformationReadService.findUserInformationOfPage(dto, page);
		
	}

	public List<UserInformationDTO> findUserInformationAll(UserInformationDTO dto){
		
		return userInformationReadService.findUserInformationAll(dto);
		
	}

	public Long insertUserInformationWithTx(UserInformationDTO dto){
		
		return userInformationWriteService.insertUserInformationWithTx(dto);
	}

	public int updateUserInformationWithTx(UserInformationDTO dto){
		
		return userInformationWriteService.updateUserInformationWithTx(dto);
	}

	public int deleteUserInformationWithTx(UserInformationDTO dto){
		
		return userInformationWriteService.deleteUserInformationWithTx(dto);
		
	}
	/**
	 * 根据用户id查询用户消息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<UserInformationDTO> findUserInformationOfByUserIdPage(UserInformationDTO dto, Pagination page) {
		// TODO Auto-generated method stub
		return userInformationReadService.findUserInformationOfByUserIdPage(dto, page);
	}
	/**
	 * 根据用户id查询用户消息未读信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public int findUnreadByUserId(UserInformationDTO dto) {
		// TODO Auto-generated method stub
		return userInformationReadService.findUnreadByUserId(dto);
	}

}
	