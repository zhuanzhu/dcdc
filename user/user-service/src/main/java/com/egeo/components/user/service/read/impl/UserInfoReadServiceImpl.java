package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.UserExtendCondition;
import com.egeo.components.user.converter.UserExtendConverter;
import com.egeo.components.user.converter.UserInfoConverter;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.components.user.manage.read.UserInfoReadManage;
import com.egeo.components.user.po.UserInfoPO;
import com.egeo.components.user.service.read.UserInfoReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userInfoReadService")
public class UserInfoReadServiceImpl implements UserInfoReadService {
	@Autowired
	private UserInfoReadManage userInfoReadManage;

	@Override
	public UserInfoDTO findUserInfoById(UserInfoDTO dto) {
		UserInfoPO po = UserInfoConverter.toPO(dto);
		UserInfoPO list = userInfoReadManage.findUserInfoById(po);		
		return UserInfoConverter.toDTO(list);
	}

	@Override
	public PageResult<UserInfoDTO> findUserInfoOfPage(UserInfoDTO dto, Pagination page) {
		UserInfoPO po = UserInfoConverter.toPO(dto);
        PageResult<UserInfoPO> pageResult = userInfoReadManage.findUserInfoOfPage(po, page);
        
        List<UserInfoDTO> list = UserInfoConverter.toDTO(pageResult.getList());
        PageResult<UserInfoDTO> result = new PageResult<UserInfoDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UserInfoDTO> findUserInfoAll(UserInfoDTO dto) {
		UserInfoPO po = UserInfoConverter.toPO(dto);
		List<UserInfoPO> list = userInfoReadManage.findUserInfoAll(po);		
		return UserInfoConverter.toDTO(list);
	}

	@Override
	public int findUserSumByInfoId(Long infoId, Long platformId) {
		// TODO Auto-generated method stub
		return userInfoReadManage.findUserSumByInfoId(infoId, platformId);
	}

	@Override
	public PageResult<UserExtendDTO> findUserByInfoIdOfPage(UserInfoDTO dto, Pagination page) {
		UserInfoPO po = UserInfoConverter.toPO(dto);
        PageResult<UserExtendCondition> pageResult = userInfoReadManage.findUserByInfoIdOfPage(po,page);
        List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
        
        for (UserExtendCondition tmp : pageResult.getList()) {
            UserExtendDTO userExtendDTO = UserExtendConverter.toDTO(tmp);
            
    		userExtendDTO.setCompanyName(tmp.getCompanyName());
    		userExtendDTO.setDepartmentName(tmp.getDepartmentName());
    		userExtendDTO.setMail(tmp.getMail());
    		userExtendDTO.setIsAvailable(tmp.getIsAvailable());
    		userExtendDTO.setChannelId(tmp.getChannelId());
            list.add(userExtendDTO);
        }
        PageResult<UserExtendDTO> result = new PageResult<UserExtendDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public int findUserInfoSumByUserId(UserInfoDTO userInfoDTO) {
		return userInfoReadManage.findUserInfoSumByUserId(UserInfoConverter.toPO(userInfoDTO));
	}
}
	