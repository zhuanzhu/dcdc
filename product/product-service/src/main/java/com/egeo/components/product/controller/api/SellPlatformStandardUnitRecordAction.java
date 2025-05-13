package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.SellPlatformStandardUnitRecordManage;
import com.egeo.components.product.converter.SellPlatformStandardUnitRecordConverter;
import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.components.product.vo.SellPlatformStandardUnitRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/sellPlatformStandardUnitRecord")
public class SellPlatformStandardUnitRecordAction extends BaseSpringController {
	
	@Resource(name="sellPlatformStandardUnitRecord")
	private SellPlatformStandardUnitRecordManage sellPlatformStandardUnitRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findSellPlatformStandardUnitRecordById")
	@ResponseBody
	public JsonResult<SellPlatformStandardUnitRecordVO> findSellPlatformStandardUnitRecordById(Long id ) {
		
		SellPlatformStandardUnitRecordDTO dto = new SellPlatformStandardUnitRecordDTO();
		dto.setId(id);
		SellPlatformStandardUnitRecordDTO rt = sellPlatformStandardUnitRecordManage.findSellPlatformStandardUnitRecordById(dto);		
		return JsonResult.success(SellPlatformStandardUnitRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSellPlatformStandardUnitRecordAll")
	@ResponseBody
	public JsonResult<List<SellPlatformStandardUnitRecordVO>> findSellPlatformStandardUnitRecordAll(SellPlatformStandardUnitRecordVO vo,HttpServletRequest req ) {
		SellPlatformStandardUnitRecordDTO dto = SellPlatformStandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SellPlatformStandardUnitRecordDTO> rt = sellPlatformStandardUnitRecordManage.findSellPlatformStandardUnitRecordAll(dto);	
		return JsonResult.success(SellPlatformStandardUnitRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSellPlatformStandardUnitRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<SellPlatformStandardUnitRecordVO>> findSellPlatformStandardUnitRecordOfPage(SellPlatformStandardUnitRecordVO vo,Pagination page,HttpServletRequest req ) {
		SellPlatformStandardUnitRecordDTO dto = SellPlatformStandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SellPlatformStandardUnitRecordDTO> rt = sellPlatformStandardUnitRecordManage.findSellPlatformStandardUnitRecordOfPage(dto, page);
        List<SellPlatformStandardUnitRecordVO> list = SellPlatformStandardUnitRecordConverter.toVO(rt.getList());
        PageResult<SellPlatformStandardUnitRecordVO> result = new PageResult<SellPlatformStandardUnitRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSellPlatformStandardUnitRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordVO vo,HttpServletRequest req ) {
		SellPlatformStandardUnitRecordDTO dto = SellPlatformStandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = sellPlatformStandardUnitRecordManage.insertSellPlatformStandardUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSellPlatformStandardUnitRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSellPlatformStandardUnitRecordByIdWithTx(SellPlatformStandardUnitRecordVO vo,HttpServletRequest req ) {
		SellPlatformStandardUnitRecordDTO dto = SellPlatformStandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = sellPlatformStandardUnitRecordManage.updateSellPlatformStandardUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSellPlatformStandardUnitRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordVO vo,HttpServletRequest req ) {
		SellPlatformStandardUnitRecordDTO dto = SellPlatformStandardUnitRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = sellPlatformStandardUnitRecordManage.deleteSellPlatformStandardUnitRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	