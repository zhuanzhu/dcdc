package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SellPlatformMerchantProdManage;
import com.egeo.components.product.converter.SellPlatformMerchantProdConverter;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.vo.SellPlatformMerchantProdVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/sellPlatformMerchantProd")
public class SellPlatformMerchantProdAction extends BaseSpringController {
	
	@Resource(name="sellPlatformMerchantProd")
	private SellPlatformMerchantProdManage sellPlatformMerchantProdManage;


	// 业务方法：
	@RequestMapping(value = "/findSellPlatformMerchantProdById")
	@ResponseBody
	public JsonResult<SellPlatformMerchantProdVO> findSellPlatformMerchantProdById(Long id ) {
		
		SellPlatformMerchantProdDTO dto = new SellPlatformMerchantProdDTO();
		dto.setId(id);
		SellPlatformMerchantProdDTO rt = sellPlatformMerchantProdManage.findSellPlatformMerchantProdById(dto);		
		return JsonResult.success(SellPlatformMerchantProdConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSellPlatformMerchantProdAll")
	@ResponseBody
	public JsonResult<List<SellPlatformMerchantProdVO>> findSellPlatformMerchantProdAll(SellPlatformMerchantProdVO vo,HttpServletRequest req ) {
		SellPlatformMerchantProdDTO dto = SellPlatformMerchantProdConverter.toDTO(vo);
		List<SellPlatformMerchantProdDTO> rt = sellPlatformMerchantProdManage.findSellPlatformMerchantProdAll(dto);	
		return JsonResult.success(SellPlatformMerchantProdConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSellPlatformMerchantProdOfPage")
	@ResponseBody
	public JsonResult<PageResult<SellPlatformMerchantProdVO>> findSellPlatformMerchantProdOfPage(SellPlatformMerchantProdVO vo,Pagination page,HttpServletRequest req ) {
		SellPlatformMerchantProdDTO dto = SellPlatformMerchantProdConverter.toDTO(vo);
		PageResult<SellPlatformMerchantProdDTO> rt = sellPlatformMerchantProdManage.findSellPlatformMerchantProdOfPage(dto, page);
        List<SellPlatformMerchantProdVO> list = SellPlatformMerchantProdConverter.toVO(rt.getList());
        PageResult<SellPlatformMerchantProdVO> result = new PageResult<SellPlatformMerchantProdVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSellPlatformMerchantProdWithTx")
	@ResponseBody
	public JsonResult<Long> insertSellPlatformMerchantProdWithTx(SellPlatformMerchantProdVO vo,HttpServletRequest req ) {
		SellPlatformMerchantProdDTO dto = SellPlatformMerchantProdConverter.toDTO(vo);
		Long rt = sellPlatformMerchantProdManage.insertSellPlatformMerchantProdWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSellPlatformMerchantProdByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSellPlatformMerchantProdByIdWithTx(SellPlatformMerchantProdVO vo,HttpServletRequest req ) {
		SellPlatformMerchantProdDTO dto = SellPlatformMerchantProdConverter.toDTO(vo);
		int rt = sellPlatformMerchantProdManage.updateSellPlatformMerchantProdWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSellPlatformMerchantProdWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSellPlatformMerchantProdWithTx(SellPlatformMerchantProdVO vo,HttpServletRequest req ) {
		SellPlatformMerchantProdDTO dto = SellPlatformMerchantProdConverter.toDTO(vo);
		int rt = sellPlatformMerchantProdManage.deleteSellPlatformMerchantProdWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	