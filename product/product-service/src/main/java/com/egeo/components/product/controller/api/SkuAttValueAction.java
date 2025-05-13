package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SkuAttValueManage;
import com.egeo.components.product.converter.SkuAttValueConverter;
import com.egeo.components.product.dto.SkuAttValueDTO;
import com.egeo.components.product.vo.SkuAttValueVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/skuAttValue")
public class SkuAttValueAction extends BaseSpringController {
	
	@Resource(name="skuAttValue")
	private SkuAttValueManage skuAttValueManage;


	// 业务方法：
	@RequestMapping(value = "/findSkuAttValueById")
	@ResponseBody
	public JsonResult<SkuAttValueVO> findSkuAttValueById(Long id ) {
		
		SkuAttValueDTO dto = new SkuAttValueDTO();
		dto.setId(id);
		SkuAttValueDTO rt = skuAttValueManage.findSkuAttValueById(dto);		
		return JsonResult.success(SkuAttValueConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSkuAttValueAll")
	@ResponseBody
	public JsonResult<List<SkuAttValueVO>> findSkuAttValueAll(SkuAttValueVO vo,HttpServletRequest req ) {
		SkuAttValueDTO dto = SkuAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SkuAttValueDTO> rt = skuAttValueManage.findSkuAttValueAll(dto);	
		return JsonResult.success(SkuAttValueConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSkuAttValueOfPage")
	@ResponseBody
	public JsonResult<PageResult<SkuAttValueVO>> findSkuAttValueOfPage(SkuAttValueVO vo,Pagination page,HttpServletRequest req ) {
		SkuAttValueDTO dto = SkuAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SkuAttValueDTO> rt = skuAttValueManage.findSkuAttValueOfPage(dto, page);
        List<SkuAttValueVO> list = SkuAttValueConverter.toVO(rt.getList());
        PageResult<SkuAttValueVO> result = new PageResult<SkuAttValueVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSkuAttValueWithTx")
	@ResponseBody
	public JsonResult<Long> insertSkuAttValueWithTx(SkuAttValueVO vo,HttpServletRequest req ) {
		SkuAttValueDTO dto = SkuAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = skuAttValueManage.insertSkuAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSkuAttValueByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSkuAttValueByIdWithTx(SkuAttValueVO vo,HttpServletRequest req ) {
		SkuAttValueDTO dto = SkuAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = skuAttValueManage.updateSkuAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSkuAttValueWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSkuAttValueWithTx(SkuAttValueVO vo,HttpServletRequest req ) {
		SkuAttValueDTO dto = SkuAttValueConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = skuAttValueManage.deleteSkuAttValueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	