package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.components.user.service.read.UserTempReadService;
import com.egeo.components.user.service.write.UserTempWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UserTempFacade {
	
	@Resource
	private UserTempReadService userTempReadService;
	
	@Resource
	private UserTempWriteService userTempWriteService;
	
	
	public UserTempDTO findUserTempById(UserTempDTO dto){
		
		return userTempReadService.findUserTempById(dto);
	}

	public PageResult<UserTempDTO> findUserTempOfPage(UserTempDTO dto,Pagination page){
		
		return userTempReadService.findUserTempOfPage(dto, page);
		
	}

	public List<UserTempDTO> findUserTempAll(UserTempDTO dto){
		
		return userTempReadService.findUserTempAll(dto);
		
	}

	public Long insertUserTempWithTx(UserTempDTO dto){
		
		return userTempWriteService.insertUserTempWithTx(dto);
	}

	public int updateUserTempWithTx(UserTempDTO dto){
		
		return userTempWriteService.updateUserTempWithTx(dto);
	}

	public int deleteUserTempWithTx(UserTempDTO dto){
		
		return userTempWriteService.deleteUserTempWithTx(dto);
		
	}

	/**
	 * 将用户临时数据插入
	 * @param userTempDTOList
	 * @return
	 */
	public int insertUserViewListWithTx(List<UserTempDTO> userTempDTOList) {
		
		return userTempWriteService.insertUserViewListWithTx(userTempDTOList);
	}


}
	