package com.egeo.components.promotion.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.CardBatchManage;
import com.egeo.components.promotion.converter.CardBatchConverter;
import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.components.promotion.vo.CardBatchVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/cardBatch")
public class CardBatchAction extends BaseSpringController {
	
	@Resource(name="cardBatch")
	private CardBatchManage cardBatchManage;


	// 业务方法：
	@RequestMapping(value = "/findCardBatchById")
	@ResponseBody
	public JsonResult<CardBatchVO> findCardBatchById(Long id ) {
		
		CardBatchDTO dto = new CardBatchDTO();
		dto.setId(id);
		CardBatchDTO rt = cardBatchManage.findCardBatchById(dto);		
		return success(CardBatchConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCardBatchAll")
	@ResponseBody
	public JsonResult<List<CardBatchVO>> findCardBatchAll(CardBatchVO vo,HttpServletRequest req ) {
		CardBatchDTO dto = CardBatchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CardBatchDTO> rt = cardBatchManage.findCardBatchAll(dto);	
		return success(CardBatchConverter.toVO(rt));
					 
	}	

	/**
	 * 分页查询批次信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCardBatchOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findCardBatchOfPage(CardBatchVO vo,Pagination page,HttpServletRequest req ) {
		CardBatchDTO dto = CardBatchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = cardBatchManage.findCardBatchOfPage(dto, page);
		return success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCardBatchWithTx")
	@ResponseBody
	public JsonResult<Long> insertCardBatchWithTx(CardBatchVO vo,HttpServletRequest req ) {
		CardBatchDTO dto = CardBatchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = cardBatchManage.insertCardBatchWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCardBatchByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCardBatchByIdWithTx(CardBatchVO vo,HttpServletRequest req ) {
		CardBatchDTO dto = CardBatchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cardBatchManage.updateCardBatchWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCardBatchWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCardBatchWithTx(CardBatchVO vo,HttpServletRequest req ) {
		CardBatchDTO dto = CardBatchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cardBatchManage.deleteCardBatchWithTx(dto);	
		return success(rt);					 
	}
		
}
	