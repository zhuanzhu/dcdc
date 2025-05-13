package com.egeo.components.stock.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.CommodityProductUnitVirtualStockManage;
import com.egeo.components.stock.converter.CommodityProductUnitVirtualStockConverter;
import com.egeo.components.stock.vo.CommodityProductUnitVirtualStockVO;
import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/stock/commodityProductUnitVirtualStock")
public class CommodityProductUnitVirtualStockAction extends BaseSpringController {
	
	@Resource(name="commodityProductUnitVirtualStock")
	private CommodityProductUnitVirtualStockManage commodityProductUnitVirtualStockManage;


	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitVirtualStockById")
	@ResponseBody
	public JsonResult<CommodityProductUnitVirtualStockVO> findCommodityProductUnitVirtualStockById(Long id ) {
		
		CommodityProductUnitVirtualStockDTO dto = new CommodityProductUnitVirtualStockDTO();
		dto.setId(id);
		CommodityProductUnitVirtualStockDTO rt = commodityProductUnitVirtualStockManage.findCommodityProductUnitVirtualStockById(dto);		
		return JsonResult.success(CommodityProductUnitVirtualStockConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitVirtualStockAll")
	@ResponseBody
	public JsonResult<List<CommodityProductUnitVirtualStockVO>> findCommodityProductUnitVirtualStockAll(CommodityProductUnitVirtualStockVO vo,HttpServletRequest req ) {
		CommodityProductUnitVirtualStockDTO dto = CommodityProductUnitVirtualStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CommodityProductUnitVirtualStockDTO> rt = commodityProductUnitVirtualStockManage.findCommodityProductUnitVirtualStockAll(dto);	
		return JsonResult.success(CommodityProductUnitVirtualStockConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCommodityProductUnitVirtualStockOfPage")
	@ResponseBody
	public JsonResult<PageResult<CommodityProductUnitVirtualStockVO>> findCommodityProductUnitVirtualStockOfPage(CommodityProductUnitVirtualStockVO vo,Pagination page,HttpServletRequest req ) {
		CommodityProductUnitVirtualStockDTO dto = CommodityProductUnitVirtualStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CommodityProductUnitVirtualStockDTO> rt = commodityProductUnitVirtualStockManage.findCommodityProductUnitVirtualStockOfPage(dto, page);
        List<CommodityProductUnitVirtualStockVO> list = CommodityProductUnitVirtualStockConverter.toVO(rt.getList());
        PageResult<CommodityProductUnitVirtualStockVO> result = new PageResult<CommodityProductUnitVirtualStockVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCommodityProductUnitVirtualStockWithTx")
	@ResponseBody
	public JsonResult<Long> insertCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockVO vo,HttpServletRequest req ) {
		CommodityProductUnitVirtualStockDTO dto = CommodityProductUnitVirtualStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = commodityProductUnitVirtualStockManage.insertCommodityProductUnitVirtualStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCommodityProductUnitVirtualStockByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCommodityProductUnitVirtualStockByIdWithTx(CommodityProductUnitVirtualStockVO vo,HttpServletRequest req ) {
		CommodityProductUnitVirtualStockDTO dto = CommodityProductUnitVirtualStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityProductUnitVirtualStockManage.updateCommodityProductUnitVirtualStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCommodityProductUnitVirtualStockWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockVO vo,HttpServletRequest req ) {
		CommodityProductUnitVirtualStockDTO dto = CommodityProductUnitVirtualStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityProductUnitVirtualStockManage.deleteCommodityProductUnitVirtualStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	