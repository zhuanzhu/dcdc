package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.UserInformationManage;
import com.egeo.components.user.facade.UserInformationFacade;
import com.egeo.components.user.dto.UserInformationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userInformation")
public class UserInformationManageImpl implements UserInformationManage{

	
	@Resource(name="userInformationFacade")
	private UserInformationFacade userInformationFacade;

	@Override
	public Map<String, Object> findUserInformationById(UserInformationDTO dto) {
		UserInformationDTO userInformationDTO = userInformationFacade.findUserInformationById(dto);
		Map<String, Object> map = new HashMap<>();
		map.put("userInformationId", userInformationDTO.getId());
		map.put("headline", userInformationDTO.getHeadline());
		map.put("headlineContent", userInformationDTO.getHeadlineContent());
		map.put("createTime", userInformationDTO.getCreateTime());
		return map;
	}

	@Override
	public PageResult<UserInformationDTO> findUserInformationOfPage(UserInformationDTO dto, Pagination page) {
		return userInformationFacade.findUserInformationOfPage(dto, page);
	}

	@Override
	public List<UserInformationDTO> findUserInformationAll(UserInformationDTO dto) {
		return userInformationFacade.findUserInformationAll(dto);
	}

	@Override
	public Long insertUserInformationWithTx(UserInformationDTO dto) {
		return userInformationFacade.insertUserInformationWithTx(dto);
	}

	@Override
	public int updateUserInformationWithTx(UserInformationDTO dto) {
		return userInformationFacade.updateUserInformationWithTx(dto);
	}

	@Override
	public int deleteUserInformationWithTx(UserInformationDTO dto) {
		return userInformationFacade.deleteUserInformationWithTx(dto);
	}
	/**
	 * 根据用户id查询用户消息
	 * @param dto
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> findUserInformationOfByUserIdPage(UserInformationDTO dto, Pagination page) {
		List<Map<String, Object>> list = new ArrayList<>();
		PageResult<UserInformationDTO> pageResult = userInformationFacade.findUserInformationOfByUserIdPage(dto, page);
		List<UserInformationDTO> userInformationList = pageResult.getList();
		for (UserInformationDTO userInformationDTO : userInformationList) {
			Map<String, Object> map = new HashMap<>();
			map.put("userInformationId", userInformationDTO.getId());
			map.put("headline", userInformationDTO.getHeadline());
			map.put("isUnread", userInformationDTO.getIsUnread());
			map.put("createTime", userInformationDTO.getCreateTime());
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}
	/**
	 * 根据用户id查询用户消息未读信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public int findUnreadByUserId(UserInformationDTO dto) {
		// TODO Auto-generated method stub
		return userInformationFacade.findUnreadByUserId(dto);
	}


}
	