package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SkuAttNameManage;
import com.egeo.components.product.converter.SkuAttNameConverter;
import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.components.product.vo.SkuAttNameVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/skuAttName")
public class SkuAttNameAction extends BaseSpringController {
	
	@Resource(name="skuAttName")
	private SkuAttNameManage skuAttNameManage;


	// 业务方法：
	@RequestMapping(value = "/findSkuAttNameById")
	@ResponseBody
	public JsonResult<SkuAttNameVO> findSkuAttNameById(Long id ) {
		
		SkuAttNameDTO dto = new SkuAttNameDTO();
		dto.setId(id);
		SkuAttNameDTO rt = skuAttNameManage.findSkuAttNameById(dto);		
		return JsonResult.success(SkuAttNameConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSkuAttNameAll")
	@ResponseBody
	public JsonResult<List<SkuAttNameVO>> findSkuAttNameAll(SkuAttNameVO vo,HttpServletRequest req ) {
		SkuAttNameDTO dto = SkuAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SkuAttNameDTO> rt = skuAttNameManage.findSkuAttNameAll(dto);	
		return JsonResult.success(SkuAttNameConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSkuAttNameOfPage")
	@ResponseBody
	public JsonResult<PageResult<SkuAttNameVO>> findSkuAttNameOfPage(SkuAttNameVO vo,Pagination page,HttpServletRequest req ) {
		SkuAttNameDTO dto = SkuAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SkuAttNameDTO> rt = skuAttNameManage.findSkuAttNameOfPage(dto, page);
        List<SkuAttNameVO> list = SkuAttNameConverter.toVO(rt.getList());
        PageResult<SkuAttNameVO> result = new PageResult<SkuAttNameVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSkuAttNameWithTx")
	@ResponseBody
	public JsonResult<Long> insertSkuAttNameWithTx(SkuAttNameVO vo,HttpServletRequest req ) {
		SkuAttNameDTO dto = SkuAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = skuAttNameManage.insertSkuAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSkuAttNameByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSkuAttNameByIdWithTx(SkuAttNameVO vo,HttpServletRequest req ) {
		SkuAttNameDTO dto = SkuAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = skuAttNameManage.updateSkuAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSkuAttNameWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSkuAttNameWithTx(SkuAttNameVO vo,HttpServletRequest req ) {
		SkuAttNameDTO dto = SkuAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = skuAttNameManage.deleteSkuAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	