package com.egeo.components.stock.controller.api;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.CommodityProductUnitStockRunningWaterManage;
import com.egeo.components.stock.converter.CommodityProductUnitStockRunningWaterConverter;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.vo.CommodityProductUnitStockRunningWaterVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/commodityProductUnitStockRunningWater")
public class CommodityProductUnitStockRunningWaterAction extends BaseSpringController {
	
	@Resource(name="commodityProductUnitStockRunningWater")
	private CommodityProductUnitStockRunningWaterManage commodityProductUnitStockRunningWaterManage;


	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitStockRunningWaterById")
	@ResponseBody
	public JsonResult<CommodityProductUnitStockRunningWaterVO> findCommodityProductUnitStockRunningWaterById(Long id ) {
		
		CommodityProductUnitStockRunningWaterDTO dto = new CommodityProductUnitStockRunningWaterDTO();
		dto.setId(id);
		CommodityProductUnitStockRunningWaterDTO rt = commodityProductUnitStockRunningWaterManage.findCommodityProductUnitStockRunningWaterById(dto);		
		return JsonResult.success(CommodityProductUnitStockRunningWaterConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitStockRunningWaterAll")
	@ResponseBody
	public JsonResult<List<CommodityProductUnitStockRunningWaterVO>> findCommodityProductUnitStockRunningWaterAll(CommodityProductUnitStockRunningWaterVO vo,HttpServletRequest req ) {
		CommodityProductUnitStockRunningWaterDTO dto = CommodityProductUnitStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CommodityProductUnitStockRunningWaterDTO> rt = commodityProductUnitStockRunningWaterManage.findCommodityProductUnitStockRunningWaterAll(dto);	
		return JsonResult.success(CommodityProductUnitStockRunningWaterConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitStockRunningWaterOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findCommodityProductUnitStockRunningWaterOfPage(CommodityProductUnitStockRunningWaterVO vo,Long createTimeFront,Long createTimeBack,Pagination page,HttpServletRequest req ) {
		CommodityProductUnitStockRunningWaterDTO dto = CommodityProductUnitStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(createTimeFront != null){
			dto.setCreateTimeFront(new Date(createTimeFront));
		}
		if(createTimeBack != null){
			dto.setCreateTimeBack(new Date(createTimeBack));
		}
		
		PageResult<Map<String, Object>> rt = commodityProductUnitStockRunningWaterManage.findCommodityProductUnitStockRunningWaterOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCommodityProductUnitStockRunningWaterWithTx")
	@ResponseBody
	public JsonResult<Long> insertCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterVO vo,HttpServletRequest req ) {
		CommodityProductUnitStockRunningWaterDTO dto = CommodityProductUnitStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = commodityProductUnitStockRunningWaterManage.insertCommodityProductUnitStockRunningWaterWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCommodityProductUnitStockRunningWaterByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCommodityProductUnitStockRunningWaterByIdWithTx(CommodityProductUnitStockRunningWaterVO vo,HttpServletRequest req ) {
		CommodityProductUnitStockRunningWaterDTO dto = CommodityProductUnitStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityProductUnitStockRunningWaterManage.updateCommodityProductUnitStockRunningWaterWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCommodityProductUnitStockRunningWaterWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterVO vo,HttpServletRequest req ) {
		CommodityProductUnitStockRunningWaterDTO dto = CommodityProductUnitStockRunningWaterConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityProductUnitStockRunningWaterManage.deleteCommodityProductUnitStockRunningWaterWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	