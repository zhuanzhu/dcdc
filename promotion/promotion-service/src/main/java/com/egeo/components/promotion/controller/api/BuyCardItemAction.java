package com.egeo.components.promotion.controller.api;


import com.egeo.components.promotion.business.BuyCardItemManage;
import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/api/promotion/buyCardItem")
public class BuyCardItemAction extends BaseSpringController {

	@Resource(name="buyCardItem")
	private BuyCardItemManage buyCardItemManage;


	// 业务方法：
	@RequestMapping(value = "/findBuyCardItemById")
	@ResponseBody
	public JsonResult<BuyCardItemDTO> findBuyCardItemById(Long id ) {
		BuyCardItemDTO dto = new BuyCardItemDTO();
		dto.setId(id);
		BuyCardItemDTO rt = buyCardItemManage.findBuyCardItemById(dto);
		return success(rt);

	}



	// 业务方法：
	@RequestMapping(value = "/findBuyCardItemAll")
	@ResponseBody
	public JsonResult<List<BuyCardItemDTO>> findBuyCardItemAll(BuyCardItemDTO dto,HttpServletRequest req ) {
		List<BuyCardItemDTO> rt = buyCardItemManage.findBuyCardItemAll(dto);
		return success(rt);

	}

	// 业务方法：
	@RequestMapping(value = "/findBuyCardItemOfPage")
	@ResponseBody
	public JsonResult<PageResult<BuyCardItemDTO>> findBuyCardItemOfPage(BuyCardItemDTO dto,Pagination page,HttpServletRequest req ) {
		PageResult<BuyCardItemDTO> rt = buyCardItemManage.findBuyCardItemOfPage(dto, page);
		return success(rt);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertBuyCardItemWithTx")
	@ResponseBody
	public JsonResult<Long> insertBuyCardItemWithTx(BuyCardItemDTO dto,HttpServletRequest req ) {
		Long rt = buyCardItemManage.insertBuyCardItemWithTx(dto);
		return success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateBuyCardItemByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateBuyCardItemByIdWithTx(BuyCardItemDTO dto,HttpServletRequest req ) {
		int rt = buyCardItemManage.updateBuyCardItemWithTx(dto);
		return success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteBuyCardItemWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteBuyCardItemWithTx(BuyCardItemDTO dto,HttpServletRequest req ) {
		int rt = buyCardItemManage.deleteBuyCardItemWithTx(dto);
		return success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/findClientBuyCardItemOfPage")
	@ResponseBody
	public JsonResult<PageResult<BuyCardItemDTO>> findClientBuyCardItemOfPage(BuyCardItemDTO dto,Pagination page,HttpServletRequest req ) {
		if(dto ==null){
			dto = new BuyCardItemDTO();
		}
		if(dto.getUserId()==null){
			CacheUser cacheUser = getCacheUser();
			dto.setUserId(cacheUser.getId());
		}
		PageResult<BuyCardItemDTO> rt = buyCardItemManage.findBuyCardItemOfPage(dto, page);
		return success(rt);

	}
}
