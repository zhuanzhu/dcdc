package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.StandardUnitDescribeManage;
import com.egeo.components.product.converter.StandardUnitDescribeConverter;
import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.components.product.vo.StandardUnitDescribeVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/standardUnitDescribe")
public class StandardUnitDescribeAction extends BaseSpringController {
	
	@Resource(name="standardUnitDescribe")
	private StandardUnitDescribeManage standardUnitDescribeManage;


	// 业务方法：
	@RequestMapping(value = "/findStandardUnitDescribeById")
	@ResponseBody
	public JsonResult<StandardUnitDescribeVO> findStandardUnitDescribeById(Long id ) {
		
		StandardUnitDescribeDTO dto = new StandardUnitDescribeDTO();
		dto.setId(id);
		StandardUnitDescribeDTO rt = standardUnitDescribeManage.findStandardUnitDescribeById(dto);		
		return JsonResult.success(StandardUnitDescribeConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findStandardUnitDescribeAll")
	@ResponseBody
	public JsonResult<List<StandardUnitDescribeVO>> findStandardUnitDescribeAll(StandardUnitDescribeVO vo,HttpServletRequest req ) {
		StandardUnitDescribeDTO dto = StandardUnitDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<StandardUnitDescribeDTO> rt = standardUnitDescribeManage.findStandardUnitDescribeAll(dto);	
		return JsonResult.success(StandardUnitDescribeConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findStandardUnitDescribeOfPage")
	@ResponseBody
	public JsonResult<PageResult<StandardUnitDescribeVO>> findStandardUnitDescribeOfPage(StandardUnitDescribeVO vo,Pagination page,HttpServletRequest req ) {
		StandardUnitDescribeDTO dto = StandardUnitDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<StandardUnitDescribeDTO> rt = standardUnitDescribeManage.findStandardUnitDescribeOfPage(dto, page);
        List<StandardUnitDescribeVO> list = StandardUnitDescribeConverter.toVO(rt.getList());
        PageResult<StandardUnitDescribeVO> result = new PageResult<StandardUnitDescribeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertStandardUnitDescribeWithTx")
	@ResponseBody
	public JsonResult<Long> insertStandardUnitDescribeWithTx(StandardUnitDescribeVO vo,HttpServletRequest req ) {
		StandardUnitDescribeDTO dto = StandardUnitDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = standardUnitDescribeManage.insertStandardUnitDescribeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateStandardUnitDescribeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateStandardUnitDescribeByIdWithTx(StandardUnitDescribeVO vo,HttpServletRequest req ) {
		StandardUnitDescribeDTO dto = StandardUnitDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitDescribeManage.updateStandardUnitDescribeWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteStandardUnitDescribeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteStandardUnitDescribeWithTx(StandardUnitDescribeVO vo,HttpServletRequest req ) {
		StandardUnitDescribeDTO dto = StandardUnitDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = standardUnitDescribeManage.deleteStandardUnitDescribeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据su商品id查询su商品星详情
	 * @param standardUnitId
	 * @return
	 */
	@RequestMapping(value = "/findContentByStandardUnitId")
	@ResponseBody
	public JsonResult<String> findContentByStandardUnitId(Long standardUnitId ) {
		
		String rt = standardUnitDescribeManage.findContentByStandardUnitId(standardUnitId);		
		return JsonResult.success(rt);
					 
	}
		
}
	