package com.egeo.components.user.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.components.user.service.read.UserInfoReadService;
import com.egeo.components.user.service.write.UserInfoWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class UserInfoFacade {
	
	@Resource
	private UserInfoReadService userInfoReadService;
	
	@Resource
	private UserInfoWriteService userInfoWriteService;
	
	@Resource
	private UserExtendReadService userExtendReadService;
	
	@Resource(name="userExtendFacade")
	private UserExtendFacade userExtendFacade;
	
	
	public UserInfoDTO findUserInfoById(UserInfoDTO dto){
		
		return userInfoReadService.findUserInfoById(dto);
	}

	public PageResult<Map<String, Object>> findUserInfoOfPage(UserInfoDTO dto,Pagination page){
		// 根据消息id分页查询用户信息
		PageResult<UserExtendDTO> pageResult = userInfoReadService.findUserByInfoIdOfPage(dto, page);
		PageResult<Map<String, Object>> result = new PageResult<>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<UserExtendDTO> userExtendList = pageResult.getList();
		
		for (UserExtendDTO userExtendDTO2 : userExtendList) {
			Map<String, Object> map = new HashMap<>();
			map.put("userId", userExtendDTO2.getId());
			map.put("name", userExtendDTO2.getName());
			map.put("isDeleted", userExtendDTO2.getIsDeleted());
			map.put("accountStatus", userExtendDTO2.getAccountStatus());
			map.put("sex", userExtendDTO2.getSex());
			map.put("mail", userExtendDTO2.getMail());
			map.put("mobile", userExtendDTO2.getMobile());
			map.put("memberCode", userExtendDTO2.getMemberCode());
			map.put("birthday", userExtendDTO2.getBirthday());
			map.put("companyName", userExtendDTO2.getCompanyName());
			map.put("departmentName", userExtendDTO2.getDepartmentName());
			map.put("status", userExtendDTO2.getStatus());
			map.put("isavailable", userExtendDTO2.getIsAvailable());
			map.put("quitTime", userExtendDTO2.getQuitTime());
			map.put("invalidTime", userExtendDTO2.getInvalidTime());
			if(EmptyUtil.isNotEmpty(userExtendDTO2.getChannelId())){
				//根据渠道id查询渠道信息
				ChannelDTO channelDTO = userExtendFacade.findChannelByChannelId(userExtendDTO2.getChannelId());
				map.put("shortCode", channelDTO.getShortCode());
			}else{
				map.put("shortCode", null);
			}
			
			
			list.add(map);
		}
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
		
	}

	public List<UserInfoDTO> findUserInfoAll(UserInfoDTO dto){
		
		return userInfoReadService.findUserInfoAll(dto);
		
	}

	public Long insertUserInfoWithTx(UserInfoDTO dto){
		
		return userInfoWriteService.insertUserInfoWithTx(dto);
	}

	public int updateUserInfoWithTx(UserInfoDTO dto){
		
		return userInfoWriteService.updateUserInfoWithTx(dto);
	}

	public int deleteUserInfoWithTx(UserInfoDTO dto){
		
		return userInfoWriteService.deleteUserInfoWithTx(dto);
		
	}

	public int findUserInfoSumByUserId(UserInfoDTO userInfoDTO) {
		return userInfoReadService.findUserInfoSumByUserId(userInfoDTO);
	}
	
	public int updateIsReadByIdWithTx(Long userInfoId) {
		return userInfoWriteService.updateIsReadByIdWithTx(userInfoId);
	}

}
	