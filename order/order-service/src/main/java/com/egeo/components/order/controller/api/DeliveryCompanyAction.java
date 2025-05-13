package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.DeliveryCompanyManage;
import com.egeo.components.order.converter.DeliveryCompanyConverter;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.vo.DeliveryCompanyVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/deliveryCompany")
public class DeliveryCompanyAction extends BaseSpringController {
	
	@Resource(name="deliveryCompany")
	private DeliveryCompanyManage deliveryCompanyManage;


	// 业务方法：
	@RequestMapping(value = "/findDeliveryCompanyById")
	@ResponseBody
	public JsonResult<DeliveryCompanyVO> findDeliveryCompanyById(Long id ) {
		DeliveryCompanyDTO rt = deliveryCompanyManage.findDeliveryCompanyById(id);
		return JsonResult.success(DeliveryCompanyConverter.toVO(rt));
	}

	// 业务方法：
	@RequestMapping(value = "/findDeliveryCompanyAll")
	@ResponseBody
	public JsonResult<List<DeliveryCompanyVO>> findDeliveryCompanyAll(DeliveryCompanyVO vo,HttpServletRequest req ) {
		DeliveryCompanyDTO dto = DeliveryCompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<DeliveryCompanyDTO> rt = deliveryCompanyManage.findDeliveryCompanyAll(dto);	
		return JsonResult.success(DeliveryCompanyConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findDeliveryCompanyOfPage")
	@ResponseBody
	public JsonResult<PageResult<DeliveryCompanyVO>> findDeliveryCompanyOfPage(DeliveryCompanyVO vo,Pagination page,HttpServletRequest req ) {
		DeliveryCompanyDTO dto = DeliveryCompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<DeliveryCompanyDTO> rt = deliveryCompanyManage.findDeliveryCompanyOfPage(dto, page);
        List<DeliveryCompanyVO> list = DeliveryCompanyConverter.toVO(rt.getList());
        PageResult<DeliveryCompanyVO> result = new PageResult<DeliveryCompanyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：
	@RequestMapping(value = "/insertDeliveryCompanyWithTx")
	@ResponseBody
	public JsonResult<Integer> insertDeliveryCompanyWithTx(DeliveryCompanyVO vo,HttpServletRequest req ) {
		DeliveryCompanyDTO dto = DeliveryCompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = deliveryCompanyManage.insertDeliveryCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateDeliveryCompanyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateDeliveryCompanyByIdWithTx(DeliveryCompanyVO vo,HttpServletRequest req ) {
		DeliveryCompanyDTO dto = DeliveryCompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = deliveryCompanyManage.updateDeliveryCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteDeliveryCompanyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteDeliveryCompanyWithTx(DeliveryCompanyVO vo,HttpServletRequest req ) {
		DeliveryCompanyDTO dto = DeliveryCompanyConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = deliveryCompanyManage.deleteDeliveryCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	