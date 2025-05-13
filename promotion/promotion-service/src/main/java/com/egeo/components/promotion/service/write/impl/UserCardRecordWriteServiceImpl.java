package com.egeo.components.promotion.service.write.impl;

import com.egeo.components.promotion.converter.UserCardRecordConverter;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.manage.write.UserCardRecordWriteManage;
import com.egeo.components.promotion.po.UserCardRecordPO;
import com.egeo.components.promotion.service.write.UserCardRecordWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("userCardRecordWriteService")
public class UserCardRecordWriteServiceImpl implements UserCardRecordWriteService {
	@Autowired
	private UserCardRecordWriteManage userCardRecordWriteManage;

	@Override
	public Long insertUserCardRecordWithTx(UserCardRecordDTO dto) {
		UserCardRecordPO po = UserCardRecordConverter.toPO(dto);
		Long rt = userCardRecordWriteManage.insertUserCardRecordWithTx(po);
		return rt;
	}

	@Override
	public int updateUserCardRecordWithTx(UserCardRecordDTO dto) {
		UserCardRecordPO po = UserCardRecordConverter.toPO(dto);
		int rt = userCardRecordWriteManage.updateUserCardRecordWithTx(po);
		return rt;
	}

	@Override
	public int deleteUserCardRecordWithTx(UserCardRecordDTO dto) {
		UserCardRecordPO po = UserCardRecordConverter.toPO(dto);
		int rt = userCardRecordWriteManage.deleteUserCardRecordWithTx(po);
		return rt;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Long insertUserCardRecordBatchWithTx(List<UserCardRecordDTO> dtos){
		for (UserCardRecordDTO dto : dtos) {
			insertUserCardRecordWithTx(dto);
		}
		return 1L;
	}

	@Override
	public Boolean useCardWithTx(Long id, BigDecimal amount,Integer useState){
		return userCardRecordWriteManage.useCardWithTx(id,amount,useState);
	}

	@Override
	public Boolean refundUserCardRecordWithTx(Long id, BigDecimal amount,Integer useState){
		return userCardRecordWriteManage.refundUserCardRecordWithTx(id,amount,useState);
	}
}
