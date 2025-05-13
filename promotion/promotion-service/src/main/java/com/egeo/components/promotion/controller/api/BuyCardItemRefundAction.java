package com.egeo.components.promotion.controller.api;


import com.egeo.components.promotion.business.BuyCardItemRefundManage;
import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
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
@RequestMapping("/api/promotion/buyCardItemRefund")
public class BuyCardItemRefundAction extends BaseSpringController {

	@Resource(name="buyCardItemRefund")
	private BuyCardItemRefundManage buyCardItemRefundManage;


	// 业务方法：
	@RequestMapping(value = "/findBuyCardItemRefundById")
	@ResponseBody
	public JsonResult<BuyCardItemRefundDTO> findBuyCardItemRefundById(Long id ) {
		BuyCardItemRefundDTO dto = new BuyCardItemRefundDTO();
		dto.setId(id);
		BuyCardItemRefundDTO rt = buyCardItemRefundManage.findBuyCardItemRefundById(dto);
		return success(rt);

	}



	// 业务方法：
	@RequestMapping(value = "/findBuyCardItemRefundAll")
	@ResponseBody
	public JsonResult<List<BuyCardItemRefundDTO>> findBuyCardItemRefundAll(BuyCardItemRefundDTO dto,HttpServletRequest req ) {
		List<BuyCardItemRefundDTO> rt = buyCardItemRefundManage.findBuyCardItemRefundAll(dto);
		return success(rt);

	}

	// 业务方法：
	@RequestMapping(value = "/findBuyCardItemRefundOfPage")
	@ResponseBody
	public JsonResult<PageResult<BuyCardItemRefundDTO>> findBuyCardItemRefundOfPage(BuyCardItemRefundDTO dto,Pagination page,HttpServletRequest req ) {

		PageResult<BuyCardItemRefundDTO> rt = buyCardItemRefundManage.findBuyCardItemRefundOfPage(dto, page);
		return success(rt);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertBuyCardItemRefundWithTx")
	@ResponseBody
	public JsonResult<Long> insertBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto,HttpServletRequest req ) {
		Long rt = buyCardItemRefundManage.insertBuyCardItemRefundWithTx(dto);
		return success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateBuyCardItemRefundByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateBuyCardItemRefundByIdWithTx(BuyCardItemRefundDTO dto,HttpServletRequest req ) {
		int rt = buyCardItemRefundManage.updateBuyCardItemRefundWithTx(dto);
		return success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteBuyCardItemRefundWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto,HttpServletRequest req ) {
		int rt = buyCardItemRefundManage.deleteBuyCardItemRefundWithTx(dto);
		return success(rt);
	}



}
