package com.egeo.components.pay.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.pay.business.AwaitQueueManage;
import com.egeo.components.pay.converter.AwaitQueueConverter;
import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.vo.AwaitQueueVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/pay/awaitQueue")
public class AwaitQueueAction extends BaseSpringController {
	
	@Resource(name="awaitQueue")
	private AwaitQueueManage awaitQueueManage;


	// 业务方法：
	@RequestMapping(value = "/findAwaitQueueById")
	@ResponseBody
	public JsonResult<AwaitQueueVO> findAwaitQueueById(Long id ) {
		
		AwaitQueueDTO dto = new AwaitQueueDTO();
		dto.setId(id);
		AwaitQueueDTO rt = awaitQueueManage.findAwaitQueueById(dto);		
		return JsonResult.success(AwaitQueueConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findAwaitQueueAll")
	@ResponseBody
	public JsonResult<List<AwaitQueueVO>> findAwaitQueueAll(AwaitQueueVO vo,HttpServletRequest req ) {
		AwaitQueueDTO dto = AwaitQueueConverter.toDTO(vo);
		List<AwaitQueueDTO> rt = awaitQueueManage.findAwaitQueueAll(dto);	
		return JsonResult.success(AwaitQueueConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findAwaitQueueOfPage")
	@ResponseBody
	public JsonResult<PageResult<AwaitQueueVO>> findAwaitQueueOfPage(AwaitQueueVO vo,Pagination page,HttpServletRequest req ) {
		AwaitQueueDTO dto = AwaitQueueConverter.toDTO(vo);
		PageResult<AwaitQueueDTO> rt = awaitQueueManage.findAwaitQueueOfPage(dto, page);
        List<AwaitQueueVO> list = AwaitQueueConverter.toVO(rt.getList());
        PageResult<AwaitQueueVO> result = new PageResult<AwaitQueueVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertAwaitQueueWithTx")
	@ResponseBody
	public JsonResult<Long> insertAwaitQueueWithTx(AwaitQueueVO vo,HttpServletRequest req ) {
		AwaitQueueDTO dto = AwaitQueueConverter.toDTO(vo);
		Long rt = awaitQueueManage.insertAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateAwaitQueueByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateAwaitQueueByIdWithTx(AwaitQueueVO vo,HttpServletRequest req ) {
		AwaitQueueDTO dto = AwaitQueueConverter.toDTO(vo);
		int rt = awaitQueueManage.updateAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteAwaitQueueWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteAwaitQueueWithTx(AwaitQueueVO vo,HttpServletRequest req ) {
		AwaitQueueDTO dto = AwaitQueueConverter.toDTO(vo);
		int rt = awaitQueueManage.deleteAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：
	@RequestMapping(value = "/findAwaitQueueByOrderCode")
	@ResponseBody
	public JsonResult<Integer> findAwaitQueueByOrderCode(String orderCode ) {
		AwaitQueueDTO awaitQueueDTO = new AwaitQueueDTO();
		awaitQueueDTO.setOrderCode(orderCode);
		List<AwaitQueueDTO> rt = awaitQueueManage.findAwaitQueueAll(awaitQueueDTO);
		if(EmptyUtil.isNotEmpty(rt)){
			return JsonResult.success(1);		
		}else{
			return JsonResult.success(0);		
		}
					 
	}
		
}
	