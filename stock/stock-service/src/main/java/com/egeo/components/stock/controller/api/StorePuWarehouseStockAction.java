package com.egeo.components.stock.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.StorePuWarehouseStockManage;
import com.egeo.components.stock.converter.StorePuWarehouseStockConverter;
import com.egeo.components.stock.vo.StorePuWarehouseStockVO;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/stock/storePuWarehouseStock")
public class StorePuWarehouseStockAction extends BaseSpringController {
	
	@Resource(name="storePuWarehouseStock")
	private StorePuWarehouseStockManage storePuWarehouseStockManage;


	// 业务方法：
	@RequestMapping(value = "/findStorePuWarehouseStockById")
	@ResponseBody
	public JsonResult<StorePuWarehouseStockVO> findStorePuWarehouseStockById(Long id ) {
		
		StorePuWarehouseStockDTO dto = new StorePuWarehouseStockDTO();
		dto.setId(id);
		StorePuWarehouseStockDTO rt = storePuWarehouseStockManage.findStorePuWarehouseStockById(dto);		
		return JsonResult.success(StorePuWarehouseStockConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStorePuWarehouseStockAll")
	@ResponseBody
	public JsonResult<List<StorePuWarehouseStockVO>> findStorePuWarehouseStockAll(StorePuWarehouseStockVO vo,HttpServletRequest req ) {
		StorePuWarehouseStockDTO dto = StorePuWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StorePuWarehouseStockDTO> rt = storePuWarehouseStockManage.findStorePuWarehouseStockAll(dto);	
		return JsonResult.success(StorePuWarehouseStockConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStorePuWarehouseStockOfPage")
	@ResponseBody
	public JsonResult<PageResult<StorePuWarehouseStockVO>> findStorePuWarehouseStockOfPage(StorePuWarehouseStockVO vo,Pagination page,HttpServletRequest req ) {
		StorePuWarehouseStockDTO dto = StorePuWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StorePuWarehouseStockDTO> rt = storePuWarehouseStockManage.findStorePuWarehouseStockOfPage(dto, page);
        List<StorePuWarehouseStockVO> list = StorePuWarehouseStockConverter.toVO(rt.getList());
        PageResult<StorePuWarehouseStockVO> result = new PageResult<StorePuWarehouseStockVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStorePuWarehouseStockWithTx")
	@ResponseBody
	public JsonResult<Long> insertStorePuWarehouseStockWithTx(StorePuWarehouseStockVO vo,HttpServletRequest req ) {
		StorePuWarehouseStockDTO dto = StorePuWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = storePuWarehouseStockManage.insertStorePuWarehouseStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStorePuWarehouseStockByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStorePuWarehouseStockByIdWithTx(StorePuWarehouseStockVO vo,HttpServletRequest req ) {
		StorePuWarehouseStockDTO dto = StorePuWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storePuWarehouseStockManage.updateStorePuWarehouseStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStorePuWarehouseStockWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStorePuWarehouseStockWithTx(StorePuWarehouseStockVO vo,HttpServletRequest req ) {
		StorePuWarehouseStockDTO dto = StorePuWarehouseStockConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storePuWarehouseStockManage.deleteStorePuWarehouseStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	