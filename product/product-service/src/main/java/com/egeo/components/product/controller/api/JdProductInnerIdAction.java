package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.JdProductInnerIdManage;
import com.egeo.components.product.converter.JdProductInnerIdConverter;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.vo.JdProductInnerIdVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/jdProductInnerId")
public class JdProductInnerIdAction extends BaseSpringController {
	
	@Resource(name="jdProductInnerId")
	private JdProductInnerIdManage jdProductInnerIdManage;


	// 业务方法：
	@RequestMapping(value = "/updateJdProductStatus")
	@ResponseBody
	public JsonResult<Boolean> updateJdProductStatus(@RequestParam("jdSkuId") Long jdSkuId) {
		jdProductInnerIdManage.updateJdProductStatus(jdSkuId);
		return JsonResult.success(true);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findJdProductInnerIdAll")
	@ResponseBody
	public JsonResult<List<JdProductInnerIdVO>> findJdProductInnerIdAll(JdProductInnerIdVO vo,HttpServletRequest req ) {
		JdProductInnerIdDTO dto = JdProductInnerIdConverter.toDTO(vo);
		List<JdProductInnerIdDTO> rt = jdProductInnerIdManage.findJdProductInnerIdAll(dto);	
		return JsonResult.success(JdProductInnerIdConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findJdProductInnerIdOfPage")
	@ResponseBody
	public JsonResult<PageResult<JdProductInnerIdVO>> findJdProductInnerIdOfPage(JdProductInnerIdVO vo,Pagination page,HttpServletRequest req ) {
		JdProductInnerIdDTO dto = JdProductInnerIdConverter.toDTO(vo);
		PageResult<JdProductInnerIdDTO> rt = jdProductInnerIdManage.findJdProductInnerIdOfPage(dto, page);
        List<JdProductInnerIdVO> list = JdProductInnerIdConverter.toVO(rt.getList());
        PageResult<JdProductInnerIdVO> result = new PageResult<JdProductInnerIdVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertJdProductInnerIdWithTx")
	@ResponseBody
	public JsonResult<Long> insertJdProductInnerIdWithTx(JdProductInnerIdVO vo,HttpServletRequest req ) {
		JdProductInnerIdDTO dto = JdProductInnerIdConverter.toDTO(vo);
		Long rt = jdProductInnerIdManage.insertJdProductInnerIdWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateJdProductInnerIdByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateJdProductInnerIdByIdWithTx(JdProductInnerIdVO vo,HttpServletRequest req ) {
		JdProductInnerIdDTO dto = JdProductInnerIdConverter.toDTO(vo);
		int rt = jdProductInnerIdManage.updateJdProductInnerIdWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteJdProductInnerIdWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteJdProductInnerIdWithTx(JdProductInnerIdVO vo,HttpServletRequest req ) {
		JdProductInnerIdDTO dto = JdProductInnerIdConverter.toDTO(vo);
		int rt = jdProductInnerIdManage.deleteJdProductInnerIdWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	