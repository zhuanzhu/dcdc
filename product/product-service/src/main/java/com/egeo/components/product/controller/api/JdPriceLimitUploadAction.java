package com.egeo.components.product.controller.api;


import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.JdPriceLimitUploadManage;
import com.egeo.components.product.converter.JdPriceLimitUploadConverter;
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.components.product.vo.JdPriceLimitUploadVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/jdPriceLimitUpload")
public class JdPriceLimitUploadAction extends BaseSpringController {
	
	@Resource(name="jdPriceLimitUpload")
	private JdPriceLimitUploadManage jdPriceLimitUploadManage;


	// 业务方法：
	@RequestMapping(value = "/findJdPriceLimitUploadById")
	@ResponseBody
	public JsonResult<JdPriceLimitUploadVO> findJdPriceLimitUploadById() {
		
		JdPriceLimitUploadDTO dto = new JdPriceLimitUploadDTO();
		dto.setId(1L);
		JdPriceLimitUploadDTO rt = jdPriceLimitUploadManage.findJdPriceLimitUploadById(dto);		
		return JsonResult.success(JdPriceLimitUploadConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findJdPriceLimitUploadAll")
	@ResponseBody
	public JsonResult<List<JdPriceLimitUploadVO>> findJdPriceLimitUploadAll(JdPriceLimitUploadVO vo,HttpServletRequest req ) {
		JdPriceLimitUploadDTO dto = JdPriceLimitUploadConverter.toDTO(vo);
		List<JdPriceLimitUploadDTO> rt = jdPriceLimitUploadManage.findJdPriceLimitUploadAll(dto);	
		return JsonResult.success(JdPriceLimitUploadConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findJdPriceLimitUploadOfPage")
	@ResponseBody
	public JsonResult<PageResult<JdPriceLimitUploadVO>> findJdPriceLimitUploadOfPage(JdPriceLimitUploadVO vo,Pagination page,HttpServletRequest req ) {
		JdPriceLimitUploadDTO dto = JdPriceLimitUploadConverter.toDTO(vo);
		PageResult<JdPriceLimitUploadDTO> rt = jdPriceLimitUploadManage.findJdPriceLimitUploadOfPage(dto, page);
        List<JdPriceLimitUploadVO> list = JdPriceLimitUploadConverter.toVO(rt.getList());
        PageResult<JdPriceLimitUploadVO> result = new PageResult<JdPriceLimitUploadVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertJdPriceLimitUploadWithTx")
	@ResponseBody
	public JsonResult<Long> insertJdPriceLimitUploadWithTx(JdPriceLimitUploadVO vo,HttpServletRequest req ) {
		JdPriceLimitUploadDTO dto = JdPriceLimitUploadConverter.toDTO(vo);
		Long rt = jdPriceLimitUploadManage.insertJdPriceLimitUploadWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateJdPriceLimitUploadByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateJdPriceLimitUploadByIdWithTx(BigDecimal jdPriceLimitStart, BigDecimal jdPriceLimitEnd, HttpServletRequest req ) {
		JdPriceLimitUploadDTO dto = new JdPriceLimitUploadDTO();
		if(EmptyUtil.isEmpty(jdPriceLimitEnd)||EmptyUtil.isEmpty(jdPriceLimitStart)){
			return JsonResult.fail("设置的值不能为空");

		}
		dto.setJdPriceLimitEnd(jdPriceLimitEnd);
		dto.setJdPriceLimitStart(jdPriceLimitStart);
		dto.setId(1L);
		int rt = jdPriceLimitUploadManage.updateJdPriceLimitUploadWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteJdPriceLimitUploadWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteJdPriceLimitUploadWithTx(JdPriceLimitUploadVO vo,HttpServletRequest req ) {
		JdPriceLimitUploadDTO dto = JdPriceLimitUploadConverter.toDTO(vo);
		int rt = jdPriceLimitUploadManage.deleteJdPriceLimitUploadWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	