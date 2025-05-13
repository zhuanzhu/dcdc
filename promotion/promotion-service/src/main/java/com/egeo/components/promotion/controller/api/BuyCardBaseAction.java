package com.egeo.components.promotion.controller.api;


import com.alibaba.fastjson.JSON;
import com.egeo.components.promotion.dto.BuyCardBaseDTO;
import com.egeo.components.promotion.facade.BuyCardBaseFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/api/promotion/buyCardBase")
public class BuyCardBaseAction extends BaseSpringController {

	@Resource
	private BuyCardBaseFacade buyCardBaseFacade;


	// 业务方法：
	@RequestMapping(value = "/findBuyCardBaseById")
	@ResponseBody
	public JsonResult<BuyCardBaseDTO> findBuyCardBaseById(Long id ) {
		BuyCardBaseDTO dto = new BuyCardBaseDTO();
		dto.setId(id);
		BuyCardBaseDTO rt = buyCardBaseFacade.findBuyCardBaseById(dto);
		return success(rt);
	}



	// 业务方法：
	@RequestMapping(value = "/findBuyCardBaseAll")
	@ResponseBody
	public JsonResult<List<BuyCardBaseDTO>> findBuyCardBaseAll(BuyCardBaseDTO dto,HttpServletRequest req ) {
		List<BuyCardBaseDTO> rt = buyCardBaseFacade.findBuyCardBaseAll(dto);
		return success(rt);

	}

	// 业务方法：
	@RequestMapping(value = "/findBuyCardBaseOfPage")
	@ResponseBody
	public JsonResult<PageResult<BuyCardBaseDTO>> findBuyCardBaseOfPage(BuyCardBaseDTO dto,Pagination page,HttpServletRequest req ) {
		logger.info("分页查询参数:{}", JSON.toJSONString(page));
		logger.info("分页查询业务参数:{}",JSON.toJSONString(dto));

		PageResult<BuyCardBaseDTO> rt = buyCardBaseFacade.findBuyCardBaseOfPage(dto, page);
		return success(rt);

	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertBuyCardBaseWithTx")
	@ResponseBody
	public JsonResult<Long> insertBuyCardBaseWithTx(BuyCardBaseDTO dto,HttpServletRequest req ) {
		/*boolean checkRT = buyCardBaseFacade.checkCardNameIsExist(dto.getCardName(),null);
		if(checkRT){
			return JsonResult.fail("卡名称不能重复");
		}*/
		Long rt = buyCardBaseFacade.insertBuyCardBaseWithTx(dto);
		return success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateBuyCardBaseByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateBuyCardBaseByIdWithTx(BuyCardBaseDTO dto,HttpServletRequest req ) {
		if(dto==null ||EmptyUtil.isEmpty(dto.getId())){
			return JsonResult.fail("参数缺失");
		}
		/*boolean checkRT = buyCardBaseFacade.checkCardNameIsExist(dto.getCardName(),dto.getId());
		if(checkRT){
			return JsonResult.fail("卡名称不能重复");
		}*/
		int rt = buyCardBaseFacade.updateBuyCardBaseWithTx(dto);
		return success(rt);
	}

	// 业务方法：
	@RequestMapping(value = "/deleteBuyCardBaseWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteBuyCardBaseWithTx(BuyCardBaseDTO dto,HttpServletRequest req ) {
		int rt = buyCardBaseFacade.deleteBuyCardBaseWithTx(dto);
		return success(rt);
	}

}
