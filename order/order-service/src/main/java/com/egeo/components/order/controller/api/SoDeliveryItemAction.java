package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.SoDeliveryItemManage;
import com.egeo.components.order.converter.SoDeliveryItemConverter;
import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.components.order.vo.SoDeliveryItemVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/soDeliveryItem")
public class SoDeliveryItemAction extends BaseSpringController {
	
	@Resource(name="soDeliveryItem")
	private SoDeliveryItemManage soDeliveryItemManage;


	// 业务方法：
	@RequestMapping(value = "/findSoDeliveryItemById")
	@ResponseBody
	public JsonResult<SoDeliveryItemVO> findSoDeliveryItemById(Long id ) {
		
		SoDeliveryItemDTO dto = new SoDeliveryItemDTO();
		dto.setId(id);
		SoDeliveryItemDTO rt = soDeliveryItemManage.findSoDeliveryItemById(dto);		
		return JsonResult.success(SoDeliveryItemConverter.toVO(rt));
					 
	}

	// 业务方法：
	@RequestMapping(value = "/findSoDeliveryItemAll")
	@ResponseBody
	public JsonResult<List<SoDeliveryItemVO>> findSoDeliveryItemAll(SoDeliveryItemVO vo,HttpServletRequest req ) {
		SoDeliveryItemDTO dto = SoDeliveryItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<SoDeliveryItemDTO> rt = soDeliveryItemManage.findSoDeliveryItemAll(dto);	
		return JsonResult.success(SoDeliveryItemConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSoDeliveryItemOfPage")
	@ResponseBody
	public JsonResult<PageResult<SoDeliveryItemVO>> findSoDeliveryItemOfPage(SoDeliveryItemVO vo,Pagination page,HttpServletRequest req ) {
		SoDeliveryItemDTO dto = SoDeliveryItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<SoDeliveryItemDTO> rt = soDeliveryItemManage.findSoDeliveryItemOfPage(dto, page);
        List<SoDeliveryItemVO> list = SoDeliveryItemConverter.toVO(rt.getList());
        PageResult<SoDeliveryItemVO> result = new PageResult<SoDeliveryItemVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：
	@RequestMapping(value = "/insertSoDeliveryItemWithTx")
	@ResponseBody
	public JsonResult<Integer> insertSoDeliveryItemWithTx(SoDeliveryItemVO vo,HttpServletRequest req ) {
		SoDeliveryItemDTO dto = SoDeliveryItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soDeliveryItemManage.insertSoDeliveryItemWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSoDeliveryItemByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSoDeliveryItemByIdWithTx(SoDeliveryItemVO vo,HttpServletRequest req ) {
		SoDeliveryItemDTO dto = SoDeliveryItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soDeliveryItemManage.updateSoDeliveryItemWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSoDeliveryItemWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSoDeliveryItemWithTx(SoDeliveryItemVO vo,HttpServletRequest req ) {
		SoDeliveryItemDTO dto = SoDeliveryItemConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = soDeliveryItemManage.deleteSoDeliveryItemWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	