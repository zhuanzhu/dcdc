package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserTempConverter;
import com.egeo.components.user.dto.UserTempDTO;
import com.egeo.components.user.manage.read.UserTempReadManage;
import com.egeo.components.user.po.UserTempPO;
import com.egeo.components.user.service.read.UserTempReadService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("userTempReadService")
public class UserTempReadServiceImpl implements UserTempReadService {
	@Autowired
	private UserTempReadManage userTempReadManage;

	@Override
	public UserTempDTO findUserTempById(UserTempDTO dto) {
		UserTempPO po = UserTempConverter.toPO(dto);
		UserTempPO list = userTempReadManage.findUserTempById(po);		
		return UserTempConverter.toDTO(list);
	}

	@Override
	public PageResult<UserTempDTO> findUserTempOfPage(UserTempDTO dto, Pagination page) {
		UserTempPO po = UserTempConverter.toPO(dto);
        PageResult<UserTempPO> pageResult = userTempReadManage.findUserTempOfPage(po, page);
        
        List<UserTempDTO> list = UserTempConverter.toDTO(pageResult.getList());
        PageResult<UserTempDTO> result = new PageResult<UserTempDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UserTempDTO> findUserTempAll(UserTempDTO dto) {
		UserTempPO po = UserTempConverter.toPO(dto);
		List<UserTempPO> list = userTempReadManage.findUserTempAll(po);		
		return UserTempConverter.toDTO(list);
	}
	/**
	 * 根据用户id查询预导入数据id
	 * @param createUserid 用户id
	 * @param platformId 平台id
	 * @return
	 */
	@Override
	public List<Long> findIdsByCreateUserid(Long createUserid, Long platformId) {
		if(StringUtils.isEmpty(createUserid))
			throw new BusinessException("用户id不能为空");
		if(StringUtils.isEmpty(platformId))
			throw new BusinessException("平台id不能为空");
		return userTempReadManage.findIdsByCreateUserid(createUserid, platformId);
	}
}
	