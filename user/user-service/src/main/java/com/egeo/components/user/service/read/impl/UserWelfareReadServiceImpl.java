package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.UserPraiseCountRankConverter;
import com.egeo.components.user.converter.UserWelfareConverter;
import com.egeo.components.user.dto.UserPraiseCountRankDTO;
import com.egeo.components.user.dto.UserWelfareDTO;
import com.egeo.components.user.manage.read.UserWelfareReadManage;
import com.egeo.components.user.po.UserPraiseCountRankPO;
import com.egeo.components.user.po.UserWelfarePO;
import com.egeo.components.user.service.read.UserWelfareReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("userWelfareReadService")
public class UserWelfareReadServiceImpl implements UserWelfareReadService {
	@Autowired
	private UserWelfareReadManage userWelfareReadManage;

	@Override
	public UserWelfareDTO findUserWelfareById(UserWelfareDTO dto) {
		UserWelfarePO po = UserWelfareConverter.toPO(dto);
		UserWelfarePO list = userWelfareReadManage.findUserWelfareById(po);		
		return UserWelfareConverter.toDTO(list);
	}

	@Override
	public PageResult<UserWelfareDTO> findUserWelfareOfPage(UserWelfareDTO dto, Pagination page) {
		UserWelfarePO po = UserWelfareConverter.toPO(dto);
        PageResult<UserWelfarePO> pageResult = userWelfareReadManage.findUserWelfareOfPage(po, page);
        
        List<UserWelfareDTO> list = UserWelfareConverter.toDTO(pageResult.getList());
        PageResult<UserWelfareDTO> result = new PageResult<UserWelfareDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<UserWelfareDTO> findUserWelfareAll(UserWelfareDTO dto) {
		UserWelfarePO po = UserWelfareConverter.toPO(dto);
		List<UserWelfarePO> list = userWelfareReadManage.findUserWelfareAll(po);		
		return UserWelfareConverter.toDTO(list);
	}

	@Override
	public List<UserWelfareDTO> queryUserWelfaresByUserIds(List<Long> userIdList) {
		return UserWelfareConverter.toDTO(userWelfareReadManage.queryUserWelfaresByUserIds(userIdList));
	}

	@Override
	public PageResult<UserPraiseCountRankDTO> queryPraiseCountRankPage(Long companyId, Integer type, Pagination page) {
		PageResult<UserPraiseCountRankPO> poPage=userWelfareReadManage.queryPraiseCountRankPage(companyId,type,page);
		List<UserPraiseCountRankDTO> dtoList=UserPraiseCountRankConverter.toDTO(poPage.getList());
		PageResult<UserPraiseCountRankDTO> dtoPage=new PageResult<>();
		dtoPage.setList(dtoList);
		dtoPage.copy(poPage);
		return dtoPage;
	}

	@Override
	public UserPraiseCountRankDTO queryPraiseCountRankByUserId(Long userId, Integer type,Long companyId) {
		return UserPraiseCountRankConverter.toDTO(userWelfareReadManage.queryPraiseCountRankByUserId(userId,type,companyId));
	}

	@Override
	public UserWelfareDTO queryUserWelfareByUserId(Long userId) {
		return UserWelfareConverter.toDTO(userWelfareReadManage.queryUserWelfareByUserId(userId));
	}
}
	