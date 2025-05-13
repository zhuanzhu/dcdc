package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SellPlatformStandardUnitManage;
import com.egeo.components.product.converter.SellPlatformStandardUnitConverter;
import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.components.product.vo.SellPlatformStandardUnitVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/sellPlatformStandardUnit")
public class SellPlatformStandardUnitAction extends BaseSpringController {
	
	@Resource(name="sellPlatformStandardUnit")
	private SellPlatformStandardUnitManage sellPlatformStandardUnitManage;


	// 业务方法：
	@RequestMapping(value = "/findSellPlatformStandardUnitById")
	@ResponseBody
	public JsonResult<SellPlatformStandardUnitVO> findSellPlatformStandardUnitById(Long id ) {
		
		SellPlatformStandardUnitDTO dto = new SellPlatformStandardUnitDTO();
		dto.setId(id);
		SellPlatformStandardUnitDTO rt = sellPlatformStandardUnitManage.findSellPlatformStandardUnitById(dto);		
		return JsonResult.success(SellPlatformStandardUnitConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSellPlatformStandardUnitAll")
	@ResponseBody
	public JsonResult<List<SellPlatformStandardUnitVO>> findSellPlatformStandardUnitAll(SellPlatformStandardUnitVO vo,HttpServletRequest req ) {
		SellPlatformStandardUnitDTO dto = SellPlatformStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SellPlatformStandardUnitDTO> rt = sellPlatformStandardUnitManage.findSellPlatformStandardUnitAll(dto);	
		return JsonResult.success(SellPlatformStandardUnitConverter.toVOs(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSellPlatformStandardUnitOfPage")
	@ResponseBody
	public JsonResult<PageResult<SellPlatformStandardUnitVO>> findSellPlatformStandardUnitOfPage(SellPlatformStandardUnitVO vo,Pagination page,HttpServletRequest req ) {
		SellPlatformStandardUnitDTO dto = SellPlatformStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SellPlatformStandardUnitDTO> rt = sellPlatformStandardUnitManage.findSellPlatformStandardUnitOfPage(dto, page);
        List<SellPlatformStandardUnitVO> list = SellPlatformStandardUnitConverter.toVOs(rt.getList());
        PageResult<SellPlatformStandardUnitVO> result = new PageResult<SellPlatformStandardUnitVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSellPlatformStandardUnitWithTx")
	@ResponseBody
	public JsonResult<Long> insertSellPlatformStandardUnitWithTx(SellPlatformStandardUnitVO vo,HttpServletRequest req ) {
		SellPlatformStandardUnitDTO dto = SellPlatformStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = sellPlatformStandardUnitManage.insertSellPlatformStandardUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSellPlatformStandardUnitByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSellPlatformStandardUnitByIdWithTx(SellPlatformStandardUnitVO vo,HttpServletRequest req ) {
		SellPlatformStandardUnitDTO dto = SellPlatformStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = sellPlatformStandardUnitManage.updateSellPlatformStandardUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSellPlatformStandardUnitWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSellPlatformStandardUnitWithTx(SellPlatformStandardUnitVO vo,HttpServletRequest req ) {
		SellPlatformStandardUnitDTO dto = SellPlatformStandardUnitConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = sellPlatformStandardUnitManage.deleteSellPlatformStandardUnitWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	