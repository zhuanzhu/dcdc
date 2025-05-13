package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.DeliveryFlowManage;
import com.egeo.components.order.converter.DeliveryFlowConverter;
import com.egeo.components.order.dto.DeliveryFlowDTO;
import com.egeo.components.order.vo.DeliveryFlowVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/deliveryFlow")
public class DeliveryFlowAction extends BaseSpringController {
	
	@Resource(name="deliveryFlow")
	private DeliveryFlowManage deliveryFlowManage;


	// 业务方法：
	@RequestMapping(value = "/findDeliveryFlowById")
	@ResponseBody
	public JsonResult<DeliveryFlowVO> findDeliveryFlowById(Long id ) {
		
		DeliveryFlowDTO dto = new DeliveryFlowDTO();
		dto.setId(id);
		DeliveryFlowDTO rt = deliveryFlowManage.findDeliveryFlowById(dto);		
		return JsonResult.success(DeliveryFlowConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findDeliveryFlowAll")
	@ResponseBody
	public JsonResult<List<DeliveryFlowVO>> findDeliveryFlowAll(DeliveryFlowVO vo,HttpServletRequest req ) {
		DeliveryFlowDTO dto = DeliveryFlowConverter.toDTO(vo);
		List<DeliveryFlowDTO> rt = deliveryFlowManage.findDeliveryFlowAll(dto);	
		return JsonResult.success(DeliveryFlowConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findDeliveryFlowOfPage")
	@ResponseBody
	public JsonResult<PageResult<DeliveryFlowVO>> findDeliveryFlowOfPage(DeliveryFlowVO vo,Pagination page,HttpServletRequest req ) {
		DeliveryFlowDTO dto = DeliveryFlowConverter.toDTO(vo);
		PageResult<DeliveryFlowDTO> rt = deliveryFlowManage.findDeliveryFlowOfPage(dto, page);
        List<DeliveryFlowVO> list = DeliveryFlowConverter.toVO(rt.getList());
        PageResult<DeliveryFlowVO> result = new PageResult<DeliveryFlowVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertDeliveryFlowWithTx")
	@ResponseBody
	public JsonResult<Long> insertDeliveryFlowWithTx(DeliveryFlowVO vo,HttpServletRequest req ) {
		DeliveryFlowDTO dto = DeliveryFlowConverter.toDTO(vo);
		Long rt = deliveryFlowManage.insertDeliveryFlowWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateDeliveryFlowByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateDeliveryFlowByIdWithTx(DeliveryFlowVO vo,HttpServletRequest req ) {
		DeliveryFlowDTO dto = DeliveryFlowConverter.toDTO(vo);
		int rt = deliveryFlowManage.updateDeliveryFlowWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteDeliveryFlowWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteDeliveryFlowWithTx(DeliveryFlowVO vo,HttpServletRequest req ) {
		DeliveryFlowDTO dto = DeliveryFlowConverter.toDTO(vo);
		int rt = deliveryFlowManage.deleteDeliveryFlowWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	