package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.UserInformationCondition;
import com.egeo.components.user.converter.UserInformationConverter;
import com.egeo.components.user.dto.UserInformationDTO;
import com.egeo.components.user.manage.read.UserInformationReadManage;
import com.egeo.components.user.manage.read.UserInformationReadReadManage;
import com.egeo.components.user.manage.write.UserInformationReadWriteManage;
import com.egeo.components.user.po.UserInformationPO;
import com.egeo.components.user.po.UserInformationReadPO;
import com.egeo.components.user.service.read.UserInformationReadService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("userInformationReadService")
public class UserInformationReadServiceImpl implements UserInformationReadService {
	@Autowired
	private UserInformationReadManage userInformationReadManage;
	
	@Autowired
	private UserInformationReadWriteManage userInformationReadWriteManage;
	
	@Autowired
	private UserInformationReadReadManage userInformationReadReadManage;

	@Override
	public UserInformationDTO findUserInformationById(UserInformationDTO dto) {
		UserInformationPO po = UserInformationConverter.toPO(dto);
		UserInformationPO list = userInformationReadManage.findUserInformationById(po);		
		//根据消息id查询是否已读
		UserInformationReadPO userInformationReadPO = new UserInformationReadPO();
		userInformationReadPO.setUserInformationId(dto.getId());
		List<UserInformationReadPO> userInformationReadList = userInformationReadReadManage.findUserInformationReadAll(userInformationReadPO);
		if(StringUtils.isEmpty(userInformationReadList)){
			UserInformationReadPO userInformationRead = new UserInformationReadPO();
			userInformationRead.setUserInformationId(dto.getId());
			userInformationRead.setUserId(dto.getUserId());
			userInformationReadWriteManage.insertUserInformationReadWithTx(userInformationRead);
		}
		
		return UserInformationConverter.toDTO(list);
	}

	@Override
	public PageResult<UserInformationDTO> findUserInformationOfPage(UserInformationDTO dto, Pagination page) {
		UserInformationPO po = UserInformationConverter.toPO(dto);
        PageResult<UserInformationPO> pageResult = userInformationReadManage.findUserInformationOfPage(po, page);
        
        List<UserInformationDTO> list = UserInformationConverter.toDTO(pageResult.getList());
        PageResult<UserInformationDTO> result = new PageResult<UserInformationDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UserInformationDTO> findUserInformationAll(UserInformationDTO dto) {
		UserInformationPO po = UserInformationConverter.toPO(dto);
		List<UserInformationPO> list = userInformationReadManage.findUserInformationAll(po);		
		return UserInformationConverter.toDTO(list);
	}
	/**
	 * 根据用户id查询用户消息
	 * @param dto
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<UserInformationDTO> findUserInformationOfByUserIdPage(UserInformationDTO dto, Pagination page) {
		if(StringUtils.isEmpty(dto.getCompanyId())){
			throw new BusinessException("公司不能为空");
		}
		List<UserInformationDTO> list = new ArrayList<>();
		PageResult<UserInformationCondition> pageResult = userInformationReadManage.findUserInformationOfByUserIdPage(UserInformationConverter.toPO(dto), page);
		List<UserInformationCondition> userInformationConditionList = pageResult.getList();
		for (UserInformationCondition userInformationCondition : userInformationConditionList) {
			UserInformationDTO userInformationDTO = UserInformationConverter.toDTO(userInformationCondition);
			if(StringUtils.isEmpty(userInformationCondition.getIsDeleted())){
				userInformationDTO.setIsUnread(0);
			}else{
				userInformationDTO.setIsUnread(1);
			}
			list.add(userInformationDTO);
		}
        PageResult<UserInformationDTO> result = new PageResult<UserInformationDTO>();
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
		return userInformationReadManage.findUnreadByUserId(UserInformationConverter.toPO(dto));
	}
}
	