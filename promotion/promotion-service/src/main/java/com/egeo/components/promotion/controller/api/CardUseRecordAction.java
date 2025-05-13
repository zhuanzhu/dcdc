package com.egeo.components.promotion.controller.api;


import com.egeo.components.promotion.business.CardUseRecordManage;
import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.vo.GrantUserBuyCardVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/api/promotion/cardUseRecord")
public class CardUseRecordAction extends BaseSpringController {

	@Resource(name="cardUseRecord")
	private CardUseRecordManage cardUseRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findCardUseRecordById")
	@ResponseBody
	public JsonResult<CardUseRecordDTO> findCardUseRecordById(Long id ) {
		CardUseRecordDTO dto = new CardUseRecordDTO();
		dto.setId(id);
		CardUseRecordDTO rt = cardUseRecordManage.findCardUseRecordById(dto);
		return success(rt);

	}



	// 业务方法：
	@RequestMapping(value = "/findCardUseRecordAll")
	@ResponseBody
	public JsonResult<List<CardUseRecordDTO>> findCardUseRecordAll(CardUseRecordDTO dto,HttpServletRequest req ) {
		List<CardUseRecordDTO> rt = cardUseRecordManage.findCardUseRecordAll(dto);
		return success(rt);

	}

	// 业务方法：
	@RequestMapping(value = "/findCardUseRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<CardUseRecordDTO>> findCardUseRecordOfPage(CardUseRecordDTO dto,Pagination page,HttpServletRequest req ) {

		PageResult<CardUseRecordDTO> rt = cardUseRecordManage.findCardUseRecordOfPage(dto, page);
		return success(rt);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCardUseRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertCardUseRecordWithTx(CardUseRecordDTO dto,HttpServletRequest req ) {
		Long rt = cardUseRecordManage.insertCardUseRecordWithTx(dto);
		return success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCardUseRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCardUseRecordByIdWithTx(CardUseRecordDTO dto,HttpServletRequest req ) {
		int rt = cardUseRecordManage.updateCardUseRecordWithTx(dto);
		return success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCardUseRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCardUseRecordWithTx(CardUseRecordDTO dto,HttpServletRequest req ) {
		int rt = cardUseRecordManage.deleteCardUseRecordWithTx(dto);
		return success(rt);
	}


	// 业务方法：
	@RequestMapping(value = "/findClientCardUseRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<CardUseRecordDTO>> findClientCardUseRecordOfPage(CardUseRecordDTO dto,Pagination page,HttpServletRequest req ) {
		if(dto ==null){
			dto = new CardUseRecordDTO();
		}
		if(dto.getUserId() ==null){
			dto.setUserId(getCacheUser().getId());
		}
		PageResult<CardUseRecordDTO> rt = cardUseRecordManage.findCardUseRecordOfPage(dto, page);
		return success(rt);

	}
}
