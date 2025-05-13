package com.egeo.components.product.controller.api;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitStoreManage;
import com.egeo.components.product.converter.StandardUnitStoreConverter;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.vo.StandardUnitStoreVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitStore")
public class StandardUnitStoreAction extends BaseSpringController {
	
	@Resource(name="standardUnitStore")
	private StandardUnitStoreManage standardUnitStoreManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitStoreById")
	@ResponseBody
	public JsonResult<StandardUnitStoreVO> findStandardUnitStoreById(Long id ) {
		
		StandardUnitStoreDTO dto = new StandardUnitStoreDTO();
		dto.setId(id);
		StandardUnitStoreDTO rt = standardUnitStoreManage.findStandardUnitStoreById(dto);		
		return JsonResult.success(StandardUnitStoreConverter.toVO(rt));
					 
	}



	/**
	 * 根据门店id查询所有门店商品（包含id和名称）
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findStandardUnitStoreAll")
	@ResponseBody
	public JsonResult<Map<String, Object>> findStandardUnitStoreAll(StandardUnitStoreVO vo,HttpServletRequest req ) {
		StandardUnitStoreDTO dto = StandardUnitStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Map<String, Object> rt = standardUnitStoreManage.findStandardUnitStoreAll(dto);	
		return JsonResult.success(rt);
					 
	}	
	/**
	 * 根据商品id查询所有门店（包含id和名称）
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/standardUnitStoreByStandardUnitId")
	@ResponseBody
	public JsonResult<Map<String, Object>> standardUnitStoreByStandardUnitId(StandardUnitStoreVO vo,HttpServletRequest req ) {
		StandardUnitStoreDTO dto = StandardUnitStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Map<String, Object> rt = standardUnitStoreManage.standardUnitStoreByStandardUnitId(dto);	
		return JsonResult.success(rt);
	}

	/**
	 * 根据门店id分页查询商品
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findStandardUnitStoreOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findStandardUnitStoreOfPage(StandardUnitStoreVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitStoreDTO dto = StandardUnitStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(EmptyUtil.isEmpty(vo.getStoreId()))
			return JsonResult.fail("请选择门店");
		PageResult<Map<String, Object>> rt = standardUnitStoreManage.findStandardUnitStoreOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitStoreWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitStoreWithTx(StandardUnitStoreVO vo,HttpServletRequest req ) {
		StandardUnitStoreDTO dto = StandardUnitStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitStoreManage.insertStandardUnitStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitStoreByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitStoreByIdWithTx(StandardUnitStoreVO vo,HttpServletRequest req ) {
		StandardUnitStoreDTO dto = StandardUnitStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitStoreManage.updateStandardUnitStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitStoreWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitStoreWithTx(StandardUnitStoreVO vo,HttpServletRequest req ) {
		StandardUnitStoreDTO dto = StandardUnitStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitStoreManage.deleteStandardUnitStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	