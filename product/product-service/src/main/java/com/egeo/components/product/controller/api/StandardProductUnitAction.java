package com.egeo.components.product.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardProductUnitManage;
import com.egeo.components.product.converter.StandardProductUnitConverter;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.vo.StandardProductUnitVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardProductUnit")
public class StandardProductUnitAction extends BaseSpringController {
	
	@Resource(name="standardProductUnit")
	private StandardProductUnitManage standardProductUnitManage;


	/**
	 * 新增su初始数据
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findStandardProductUnitById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findStandardProductUnitById(Long id,HttpServletRequest req ) {
		
		StandardProductUnitDTO dto = new StandardProductUnitDTO();
		dto.setId(id);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Map<String, Object> map = standardProductUnitManage.findStandardProductUnitById(dto,false);		
		return JsonResult.success(map);
	}
	
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	@RequestMapping(value = "/queryStandardProductUnitById")
	@ResponseBody
	public JsonResult<Map<String, Object>> queryStandardProductUnitById(Long standardProductUnitId ) {
		Map<String, Object> map = standardProductUnitManage.queryStandardProductUnitById(standardProductUnitId);		
		return JsonResult.success(map);
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitVO>> findStandardProductUnitAll(StandardProductUnitVO vo,HttpServletRequest req ) {
		StandardProductUnitDTO dto = StandardProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitDTO> rt = standardProductUnitManage.findStandardProductUnitAll(dto);	
		return JsonResult.success(StandardProductUnitConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardProductUnitOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardProductUnitVO>> findStandardProductUnitOfPage(StandardProductUnitVO vo,Pagination page,HttpServletRequest req ) {
		StandardProductUnitDTO dto = StandardProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardProductUnitDTO> rt = standardProductUnitManage.findStandardProductUnitOfPage(dto, page);
        List<StandardProductUnitVO> list = StandardProductUnitConverter.toVO(rt.getList());
        PageResult<StandardProductUnitVO> result = new PageResult<StandardProductUnitVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardProductUnitWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardProductUnitWithTx(StandardProductUnitVO vo,HttpServletRequest req ) {
		StandardProductUnitDTO dto = StandardProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardProductUnitManage.insertStandardProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardProductUnitByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardProductUnitByIdWithTx(StandardProductUnitVO vo,HttpServletRequest req ) {
		StandardProductUnitDTO dto = StandardProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitManage.updateStandardProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardProductUnitWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardProductUnitWithTx(StandardProductUnitVO vo,HttpServletRequest req ) {
		StandardProductUnitDTO dto = StandardProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardProductUnitManage.deleteStandardProductUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据suId查询spu模版id
	 * @param standardUnitId
	 * @return
	 */
	@RequestMapping(value = "/findCommodityTemplateIdByStandardUnitId")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCommodityTemplateIdByStandardUnitId(Long standardUnitId) {
		Map<String, Object> map = standardProductUnitManage.findCommodityTemplateIdByStandardUnitId(standardUnitId);		
		return JsonResult.success(map);
	}

	/**
	 * 所有基本属性“是否为电子卡券”为是，且“是否存在 unit 库存”为“是”的产品均不能被选择
	 */
	@RequestMapping(value = "/conditionStandardProductUnitAll")
	@ResponseBody
	public JsonResult<List<StandardProductUnitVO>> conditionStandardProductUnitAll(StandardProductUnitVO vo,HttpServletRequest req) {
		StandardProductUnitDTO dto = StandardProductUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardProductUnitDTO> dtoList = standardProductUnitManage.conditionStandardProductUnitAll(dto);
		
		return JsonResult.success(StandardProductUnitConverter.toVO(dtoList));
	}

}
	