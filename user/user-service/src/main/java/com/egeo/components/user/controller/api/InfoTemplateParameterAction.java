package com.egeo.components.user.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.vo.InfoTemplateParameterVO;
import com.egeo.components.user.business.InfoTemplateParameterManage;
import com.egeo.components.user.converter.InfoTemplateParameterConverter;
import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/infoTemplateParameter")
public class InfoTemplateParameterAction extends BaseSpringController {
	
	@Resource(name="infoTemplateParameter")
	private InfoTemplateParameterManage infoTemplateParameterManage;


	// 业务方法：
	@RequestMapping(value = "/findInfoTemplateParameterById")
	@ResponseBody
	public JsonResult<InfoTemplateParameterVO> findInfoTemplateParameterById(Long id ) {
		
		InfoTemplateParameterDTO dto = new InfoTemplateParameterDTO();
		dto.setId(id);
		InfoTemplateParameterDTO rt = infoTemplateParameterManage.findInfoTemplateParameterById(dto);		
		return JsonResult.success(InfoTemplateParameterConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findInfoTemplateParameterAll")
	@ResponseBody
	public JsonResult<List<InfoTemplateParameterVO>> findInfoTemplateParameterAll(InfoTemplateParameterVO vo,HttpServletRequest req ) {
		InfoTemplateParameterDTO dto = InfoTemplateParameterConverter.toDTO(vo);
		List<InfoTemplateParameterDTO> rt = infoTemplateParameterManage.findInfoTemplateParameterAll(dto);	
		return JsonResult.success(InfoTemplateParameterConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findInfoTemplateParameterOfPage")
	@ResponseBody
	public JsonResult<PageResult<InfoTemplateParameterVO>> findInfoTemplateParameterOfPage(InfoTemplateParameterVO vo,Pagination page,HttpServletRequest req ) {
		InfoTemplateParameterDTO dto = InfoTemplateParameterConverter.toDTO(vo);
		PageResult<InfoTemplateParameterDTO> rt = infoTemplateParameterManage.findInfoTemplateParameterOfPage(dto, page);
        List<InfoTemplateParameterVO> list = InfoTemplateParameterConverter.toVO(rt.getList());
        PageResult<InfoTemplateParameterVO> result = new PageResult<InfoTemplateParameterVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertInfoTemplateParameterWithTx")
	@ResponseBody
	public JsonResult<Long> insertInfoTemplateParameterWithTx(InfoTemplateParameterVO vo,HttpServletRequest req ) {
		InfoTemplateParameterDTO dto = InfoTemplateParameterConverter.toDTO(vo);
		Long rt = infoTemplateParameterManage.insertInfoTemplateParameterWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateInfoTemplateParameterByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateInfoTemplateParameterByIdWithTx(InfoTemplateParameterVO vo,HttpServletRequest req ) {
		InfoTemplateParameterDTO dto = InfoTemplateParameterConverter.toDTO(vo);
		int rt = infoTemplateParameterManage.updateInfoTemplateParameterWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteInfoTemplateParameterWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteInfoTemplateParameterWithTx(InfoTemplateParameterVO vo,HttpServletRequest req ) {
		InfoTemplateParameterDTO dto = InfoTemplateParameterConverter.toDTO(vo);
		int rt = infoTemplateParameterManage.deleteInfoTemplateParameterWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	