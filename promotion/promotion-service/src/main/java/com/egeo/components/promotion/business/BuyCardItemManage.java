package com.egeo.components.promotion.business;

import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.components.promotion.vo.GrantUserBuyCardVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BuyCardItemManage {

	public BuyCardItemDTO findBuyCardItemById(BuyCardItemDTO dto);

	public PageResult<BuyCardItemDTO> findBuyCardItemOfPage(BuyCardItemDTO dto,Pagination page);

	public List<BuyCardItemDTO> findBuyCardItemAll(BuyCardItemDTO dto);

	Long insertBuyCardItemWithTx(BuyCardItemDTO dto);

	int updateBuyCardItemWithTx(BuyCardItemDTO dto);

	int deleteBuyCardItemWithTx(BuyCardItemDTO dto);


}

