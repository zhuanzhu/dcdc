package com.egeo.components.user.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.vo.SendWayTypeVO;
import com.egeo.components.user.business.SendWayTypeManage;
import com.egeo.components.user.converter.SendWayTypeConverter;
import com.egeo.components.user.dto.SendWayTypeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/sendWayType")
public class SendWayTypeAction extends BaseSpringController {
	
	@Resource(name="sendWayType")
	private SendWayTypeManage sendWayTypeManage;


	// 业务方法：
	@RequestMapping(value = "/findSendWayTypeById")
	@ResponseBody
	public JsonResult<SendWayTypeVO> findSendWayTypeById(Long id ) {
		
		SendWayTypeDTO dto = new SendWayTypeDTO();
		dto.setId(id);
		SendWayTypeDTO rt = sendWayTypeManage.findSendWayTypeById(dto);		
		return JsonResult.success(SendWayTypeConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findSendWayTypeAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findSendWayTypeAll(SendWayTypeVO vo,HttpServletRequest req ) {
		SendWayTypeDTO dto = SendWayTypeConverter.toDTO(vo);
		List<Map<String, Object>> rt = sendWayTypeManage.findSendWayTypeAll(dto);	
		return JsonResult.success(rt);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findSendWayTypeOfPage")
	@ResponseBody
	public JsonResult<PageResult<SendWayTypeVO>> findSendWayTypeOfPage(SendWayTypeVO vo,Pagination page,HttpServletRequest req ) {
		SendWayTypeDTO dto = SendWayTypeConverter.toDTO(vo);
		PageResult<SendWayTypeDTO> rt = sendWayTypeManage.findSendWayTypeOfPage(dto, page);
        List<SendWayTypeVO> list = SendWayTypeConverter.toVO(rt.getList());
        PageResult<SendWayTypeVO> result = new PageResult<SendWayTypeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertSendWayTypeWithTx")
	@ResponseBody
	public JsonResult<Long> insertSendWayTypeWithTx(SendWayTypeVO vo,HttpServletRequest req ) {
		SendWayTypeDTO dto = SendWayTypeConverter.toDTO(vo);
		Long rt = sendWayTypeManage.insertSendWayTypeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateSendWayTypeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateSendWayTypeByIdWithTx(SendWayTypeVO vo,HttpServletRequest req ) {
		SendWayTypeDTO dto = SendWayTypeConverter.toDTO(vo);
		int rt = sendWayTypeManage.updateSendWayTypeWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteSendWayTypeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteSendWayTypeWithTx(SendWayTypeVO vo,HttpServletRequest req ) {
		SendWayTypeDTO dto = SendWayTypeConverter.toDTO(vo);
		int rt = sendWayTypeManage.deleteSendWayTypeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	