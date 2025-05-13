package com.egeo.components.promotion.business;

import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.vo.GrantUserBuyCardVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CardUseRecordManage {

	public CardUseRecordDTO findCardUseRecordById(CardUseRecordDTO dto);

	public PageResult<CardUseRecordDTO> findCardUseRecordOfPage(CardUseRecordDTO dto,Pagination page);

	public List<CardUseRecordDTO> findCardUseRecordAll(CardUseRecordDTO dto);

	Long insertCardUseRecordWithTx(CardUseRecordDTO dto);

	int updateCardUseRecordWithTx(CardUseRecordDTO dto);

	int deleteCardUseRecordWithTx(CardUseRecordDTO dto);


}

