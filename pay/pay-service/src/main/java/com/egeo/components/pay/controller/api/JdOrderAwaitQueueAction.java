package com.egeo.components.pay.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.pay.business.JdOrderAwaitQueueManage;
import com.egeo.components.pay.converter.JdOrderAwaitQueueConverter;
import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.vo.JdOrderAwaitQueueVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/pay/jdOrderAwaitQueue")
public class JdOrderAwaitQueueAction extends BaseSpringController {
	
	@Resource(name="jdOrderAwaitQueue")
	private JdOrderAwaitQueueManage jdOrderAwaitQueueManage;


	// 业务方法：
	@RequestMapping(value = "/findJdOrderAwaitQueueById")
	@ResponseBody
	public JsonResult<JdOrderAwaitQueueVO> findJdOrderAwaitQueueById(Long id ) {
		
		JdOrderAwaitQueueDTO dto = new JdOrderAwaitQueueDTO();
		dto.setId(id);
		JdOrderAwaitQueueDTO rt = jdOrderAwaitQueueManage.findJdOrderAwaitQueueById(dto);		
		return JsonResult.success(JdOrderAwaitQueueConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findJdOrderAwaitQueueAll")
	@ResponseBody
	public JsonResult<List<JdOrderAwaitQueueVO>> findJdOrderAwaitQueueAll(JdOrderAwaitQueueVO vo,HttpServletRequest req ) {
		JdOrderAwaitQueueDTO dto = JdOrderAwaitQueueConverter.toDTO(vo);
		List<JdOrderAwaitQueueDTO> rt = jdOrderAwaitQueueManage.findJdOrderAwaitQueueAll(dto);	
		return JsonResult.success(JdOrderAwaitQueueConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findJdOrderAwaitQueueOfPage")
	@ResponseBody
	public JsonResult<PageResult<JdOrderAwaitQueueVO>> findJdOrderAwaitQueueOfPage(JdOrderAwaitQueueVO vo,Pagination page,HttpServletRequest req ) {
		JdOrderAwaitQueueDTO dto = JdOrderAwaitQueueConverter.toDTO(vo);
		PageResult<JdOrderAwaitQueueDTO> rt = jdOrderAwaitQueueManage.findJdOrderAwaitQueueOfPage(dto, page);
        List<JdOrderAwaitQueueVO> list = JdOrderAwaitQueueConverter.toVO(rt.getList());
        PageResult<JdOrderAwaitQueueVO> result = new PageResult<JdOrderAwaitQueueVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertJdOrderAwaitQueueWithTx")
	@ResponseBody
	public JsonResult<Long> insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueueVO vo,HttpServletRequest req ) {
		JdOrderAwaitQueueDTO dto = JdOrderAwaitQueueConverter.toDTO(vo);
		Long rt = jdOrderAwaitQueueManage.insertJdOrderAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateJdOrderAwaitQueueByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateJdOrderAwaitQueueByIdWithTx(JdOrderAwaitQueueVO vo,HttpServletRequest req ) {
		JdOrderAwaitQueueDTO dto = JdOrderAwaitQueueConverter.toDTO(vo);
		int rt = jdOrderAwaitQueueManage.updateJdOrderAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteJdOrderAwaitQueueWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueueVO vo,HttpServletRequest req ) {
		JdOrderAwaitQueueDTO dto = JdOrderAwaitQueueConverter.toDTO(vo);
		int rt = jdOrderAwaitQueueManage.deleteJdOrderAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	