package com.egeo.components.stock.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.StockDictManage;
import com.egeo.components.stock.converter.StockDictConverter;
import com.egeo.components.stock.vo.StockDictVO;
import com.egeo.components.stock.dto.StockDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/stock/stockDict")
public class StockDictAction extends BaseSpringController {
	
	@Resource(name="stockDict")
	private StockDictManage stockDictManage;


	// 业务方法：
	@RequestMapping(value = "/findStockDictById")
	@ResponseBody
	public JsonResult<StockDictVO> findStockDictById(Long id ) {
		
		StockDictDTO dto = new StockDictDTO();
		dto.setId(id);
		StockDictDTO rt = stockDictManage.findStockDictById(dto);		
		return JsonResult.success(StockDictConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStockDictAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findStockDictAll(StockDictVO vo,HttpServletRequest req ) {
		StockDictDTO dto = StockDictConverter.toDTO(vo);
		List<Map<String, Object>> rt = stockDictManage.findStockDictAll(dto);	
		return JsonResult.success(rt);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStockDictOfPage")
	@ResponseBody
	public JsonResult<PageResult<StockDictVO>> findStockDictOfPage(StockDictVO vo,Pagination page,HttpServletRequest req ) {
		StockDictDTO dto = StockDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StockDictDTO> rt = stockDictManage.findStockDictOfPage(dto, page);
        List<StockDictVO> list = StockDictConverter.toVO(rt.getList());
        PageResult<StockDictVO> result = new PageResult<StockDictVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStockDictWithTx")
	@ResponseBody
	public JsonResult<Long> insertStockDictWithTx(StockDictVO vo,HttpServletRequest req ) {
		StockDictDTO dto = StockDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = stockDictManage.insertStockDictWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStockDictByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStockDictByIdWithTx(StockDictVO vo,HttpServletRequest req ) {
		StockDictDTO dto = StockDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = stockDictManage.updateStockDictWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStockDictWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStockDictWithTx(StockDictVO vo,HttpServletRequest req ) {
		StockDictDTO dto = StockDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = stockDictManage.deleteStockDictWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	