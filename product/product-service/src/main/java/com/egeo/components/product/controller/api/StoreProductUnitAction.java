package com.egeo.components.product.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StoreProductUnitManage;
import com.egeo.components.product.converter.StoreProductUnitConverter;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.vo.StoreProductUnitVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

import io.swagger.annotations.ApiOperation;



@Controller
@RequestMapping("/api/product/storeProductUnit")
public class StoreProductUnitAction extends BaseSpringController {
	
	@Resource(name="storeProductUnit")
	private StoreProductUnitManage storeProductUnitManage;


	// 业务方法：
	@RequestMapping(value = "/findStoreProductUnitById")
	@ResponseBody
	public JsonResult<StoreProductUnitVO> findStoreProductUnitById(Long id ) {
		
		StoreProductUnitDTO dto = new StoreProductUnitDTO();
		dto.setId(id);
		StoreProductUnitDTO rt = storeProductUnitManage.findStoreProductUnitById(dto);		
		return JsonResult.success(StoreProductUnitConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStoreProductUnitAll")
	@ResponseBody
	public JsonResult<List<StoreProductUnitVO>> findStoreProductUnitAll(StoreProductUnitVO vo,HttpServletRequest req ) {
		StoreProductUnitDTO dto = StoreProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StoreProductUnitDTO> rt = storeProductUnitManage.findStoreProductUnitAll(dto);	
		return JsonResult.success(StoreProductUnitConverter.toVO(rt));
					 
	}	

	/**
	 * 分页查询门店pu库存信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "根据门店id查询商品接口")
	@RequestMapping(value = "/findStoreProductUnitOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findStoreProductUnitOfPage(StoreProductUnitVO vo,Pagination page,HttpServletRequest req ) {
		StoreProductUnitDTO dto = StoreProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = storeProductUnitManage.findStoreProductUnitOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStoreProductUnitWithTx")
	@ResponseBody
	public JsonResult<Long> insertStoreProductUnitWithTx(StoreProductUnitVO vo,HttpServletRequest req ) {
		StoreProductUnitDTO dto = StoreProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = storeProductUnitManage.insertStoreProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStoreProductUnitByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStoreProductUnitByIdWithTx(StoreProductUnitVO vo,HttpServletRequest req ) {
		StoreProductUnitDTO dto = StoreProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeProductUnitManage.updateStoreProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStoreProductUnitWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStoreProductUnitWithTx(StoreProductUnitVO vo,HttpServletRequest req ) {
		StoreProductUnitDTO dto = StoreProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = storeProductUnitManage.deleteStoreProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	