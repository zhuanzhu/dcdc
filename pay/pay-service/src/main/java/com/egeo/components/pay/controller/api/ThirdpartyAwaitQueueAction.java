package com.egeo.components.pay.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.pay.business.ThirdpartyAwaitQueueManage;
import com.egeo.components.pay.converter.ThirdpartyAwaitQueueConverter;
import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.vo.ThirdpartyAwaitQueueVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/pay/thirdpartyAwaitQueue")
public class ThirdpartyAwaitQueueAction extends BaseSpringController {
	
	@Resource(name="thirdpartyAwaitQueue")
	private ThirdpartyAwaitQueueManage thirdpartyAwaitQueueManage;


	// 业务方法：
	@RequestMapping(value = "/findThirdpartyAwaitQueueById")
	@ResponseBody
	public JsonResult<ThirdpartyAwaitQueueVO> findThirdpartyAwaitQueueById(Long id ) {
		
		ThirdpartyAwaitQueueDTO dto = new ThirdpartyAwaitQueueDTO();
		dto.setId(id);
		ThirdpartyAwaitQueueDTO rt = thirdpartyAwaitQueueManage.findThirdpartyAwaitQueueById(dto);		
		return JsonResult.success(ThirdpartyAwaitQueueConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findThirdpartyAwaitQueueAll")
	@ResponseBody
	public JsonResult<List<ThirdpartyAwaitQueueVO>> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueueVO vo,HttpServletRequest req ) {
		ThirdpartyAwaitQueueDTO dto = ThirdpartyAwaitQueueConverter.toDTO(vo);
		List<ThirdpartyAwaitQueueDTO> rt = thirdpartyAwaitQueueManage.findThirdpartyAwaitQueueAll(dto);	
		return JsonResult.success(ThirdpartyAwaitQueueConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findThirdpartyAwaitQueueOfPage")
	@ResponseBody
	public JsonResult<PageResult<ThirdpartyAwaitQueueVO>> findThirdpartyAwaitQueueOfPage(ThirdpartyAwaitQueueVO vo,Pagination page,HttpServletRequest req ) {
		ThirdpartyAwaitQueueDTO dto = ThirdpartyAwaitQueueConverter.toDTO(vo);
		PageResult<ThirdpartyAwaitQueueDTO> rt = thirdpartyAwaitQueueManage.findThirdpartyAwaitQueueOfPage(dto, page);
        List<ThirdpartyAwaitQueueVO> list = ThirdpartyAwaitQueueConverter.toVO(rt.getList());
        PageResult<ThirdpartyAwaitQueueVO> result = new PageResult<ThirdpartyAwaitQueueVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertThirdpartyAwaitQueueWithTx")
	@ResponseBody
	public JsonResult<Long> insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueVO vo,HttpServletRequest req ) {
		ThirdpartyAwaitQueueDTO dto = ThirdpartyAwaitQueueConverter.toDTO(vo);
		Long rt = thirdpartyAwaitQueueManage.insertThirdpartyAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateThirdpartyAwaitQueueByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateThirdpartyAwaitQueueByIdWithTx(ThirdpartyAwaitQueueVO vo,HttpServletRequest req ) {
		ThirdpartyAwaitQueueDTO dto = ThirdpartyAwaitQueueConverter.toDTO(vo);
		int rt = thirdpartyAwaitQueueManage.updateThirdpartyAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteThirdpartyAwaitQueueWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueVO vo,HttpServletRequest req ) {
		ThirdpartyAwaitQueueDTO dto = ThirdpartyAwaitQueueConverter.toDTO(vo);
		int rt = thirdpartyAwaitQueueManage.deleteThirdpartyAwaitQueueWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	