package com.egeo.components.promotion.business;

import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.vo.GrantUserBuyCardVO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountReqVO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.components.promotion.vo.UseCardReqVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UserCardRecordManage {

	public UserCardRecordDTO findUserCardRecordById(UserCardRecordDTO dto);

	public PageResult<UserCardRecordDTO> findUserCardRecordOfPage(UserCardRecordDTO dto,Pagination page);

	public List<UserCardRecordDTO> findUserCardRecordAll(UserCardRecordDTO dto);

	Long insertUserCardRecordWithTx(UserCardRecordDTO dto);

	int updateUserCardRecordWithTx(UserCardRecordDTO dto);

	int deleteUserCardRecordWithTx(UserCardRecordDTO dto);

	JsonResult<String> grantUserBuyCard(List<GrantUserBuyCardVO> list, Long operator);

	Integer countUserCardRecord(UserCardRecordDTO dto);

	public PageResult<UserCardRecordDTO> findUserCardRecordOfPagePlat(UserCardRecordDTO dto, Pagination page);

	List<SumUserCardTypeAmountVO> sumUserCardTypeAmount(SumUserCardTypeAmountReqVO vo);

	public PageResult<UserCardRecordDTO> findClientUserCardRecordOfPage(UserCardRecordDTO dto, Pagination page);

	public List<UserCardRecordDTO> findClientUserCardRecordAll(UserCardRecordDTO dto);

	public BigDecimal sumUserCardCash(UserCardRecordDTO dto);

	public Boolean useCard(UseCardReqVO vo);

	public Boolean cancelUserCard();

	public Boolean buyCardRefund(List<BuyCardItemRefundDTO> dtos);

	public JsonResult<Map<String,Object>> exportUserCardRecordSearch(UserCardRecordDTO dto);
}

