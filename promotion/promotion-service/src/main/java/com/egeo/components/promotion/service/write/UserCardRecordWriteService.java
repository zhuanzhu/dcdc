package com.egeo.components.promotion.service.write;

import com.egeo.components.promotion.dto.UserCardRecordDTO;

import java.math.BigDecimal;
import java.util.List;


public interface UserCardRecordWriteService {

	public Long insertUserCardRecordWithTx(UserCardRecordDTO dto);

	public int updateUserCardRecordWithTx(UserCardRecordDTO dto);

	public int deleteUserCardRecordWithTx(UserCardRecordDTO dto);

	public Long insertUserCardRecordBatchWithTx(List<UserCardRecordDTO> dtos);

	public Boolean useCardWithTx(Long id, BigDecimal amount,Integer useState);
	public Boolean refundUserCardRecordWithTx(Long id, BigDecimal amount,Integer useState);
}
