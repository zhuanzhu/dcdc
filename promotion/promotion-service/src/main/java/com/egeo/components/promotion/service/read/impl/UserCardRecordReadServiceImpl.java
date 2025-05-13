package com.egeo.components.promotion.service.read.impl;

import com.egeo.components.promotion.converter.UserCardRecordConverter;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.manage.read.UserCardRecordReadManage;
import com.egeo.components.promotion.po.UserCardRecordPO;
import com.egeo.components.promotion.service.read.UserCardRecordReadService;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userCardRecordReadService")
public class UserCardRecordReadServiceImpl implements UserCardRecordReadService {
	@Autowired
	private UserCardRecordReadManage userCardRecordReadManage;

	@Override
	public UserCardRecordDTO findUserCardRecordById(UserCardRecordDTO dto) {
		UserCardRecordPO po = UserCardRecordConverter.toPO(dto);
		UserCardRecordPO list = userCardRecordReadManage.findUserCardRecordById(po);
		return UserCardRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<UserCardRecordDTO> findUserCardRecordOfPage(UserCardRecordDTO dto, Pagination page) {
		UserCardRecordPO po = UserCardRecordConverter.toPO(dto);
		PageResult<UserCardRecordPO> pageResult = userCardRecordReadManage.findUserCardRecordOfPage(po, page);

		List<UserCardRecordDTO> list = UserCardRecordConverter.toDTO(pageResult.getList());
		PageResult<UserCardRecordDTO> result = new PageResult<>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<UserCardRecordDTO> findUserCardRecordAll(UserCardRecordDTO dto) {
		UserCardRecordPO po = UserCardRecordConverter.toPO(dto);
		List<UserCardRecordPO> list = userCardRecordReadManage.findUserCardRecordAll(po);
		return UserCardRecordConverter.toDTO(list);
	}

	@Override
	public Integer countUserCardRecord(UserCardRecordDTO dto){
		UserCardRecordPO po = UserCardRecordConverter.toPO(dto);
		return userCardRecordReadManage.countUserCardRecord(po);
	}

	@Override
	public List<UserCardRecordDTO> sumUserCardTypeAmount(UserCardRecordDTO dto){
		UserCardRecordPO po = UserCardRecordConverter.toPO(dto);
		List<UserCardRecordPO> list =  userCardRecordReadManage.sumUserCardTypeAmount(po);
		return UserCardRecordConverter.toDTO(list);
	}
}
