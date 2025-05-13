package com.egeo.components.promotion.service.read;


import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface UserCardRecordReadService {

	public UserCardRecordDTO findUserCardRecordById(UserCardRecordDTO dto);

	public PageResult<UserCardRecordDTO> findUserCardRecordOfPage(UserCardRecordDTO dto,Pagination page);

	public List<UserCardRecordDTO> findUserCardRecordAll(UserCardRecordDTO dto);

	public Integer countUserCardRecord(UserCardRecordDTO dto);

	public List<UserCardRecordDTO> sumUserCardTypeAmount(UserCardRecordDTO dto);
}
