package com.egeo.components.stock.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.AdCodeManage;
import com.egeo.components.stock.converter.AdCodeConverter;
import com.egeo.components.stock.dto.AdCodeDTO;
import com.egeo.components.stock.vo.AdCodeVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/adCode")
public class AdCodeAction extends BaseSpringController {
	
	@Resource(name="adCode")
	private AdCodeManage adCodeManage;


	// 业务方法：
	@RequestMapping(value = "/findAdCodeById")
	@ResponseBody
	public JsonResult<AdCodeVO> findAdCodeById(Long id ) {
		
		AdCodeDTO dto = new AdCodeDTO();
		dto.setId(id);
		AdCodeDTO rt = adCodeManage.findAdCodeById(dto);		
		return success(AdCodeConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findAdCodeAll")
	@ResponseBody
	public JsonResult<List<AdCodeVO>> findAdCodeAll(AdCodeVO vo,HttpServletRequest req ) {
		AdCodeDTO dto = AdCodeConverter.toDTO(vo);
		List<AdCodeDTO> rt = adCodeManage.findAdCodeAll(dto);	
		return success(AdCodeConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findAdCodeOfPage")
	@ResponseBody
	public JsonResult<PageResult<AdCodeVO>> findAdCodeOfPage(AdCodeVO vo,Pagination page,HttpServletRequest req ) {
		AdCodeDTO dto = AdCodeConverter.toDTO(vo);
		PageResult<AdCodeDTO> rt = adCodeManage.findAdCodeOfPage(dto, page);
        List<AdCodeVO> list = AdCodeConverter.toVO(rt.getList());
        PageResult<AdCodeVO> result = new PageResult<AdCodeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertAdCodeWithTx")
	@ResponseBody
	public JsonResult<Long> insertAdCodeWithTx(AdCodeVO vo,HttpServletRequest req ) {
		AdCodeDTO dto = AdCodeConverter.toDTO(vo);
		Long rt = adCodeManage.insertAdCodeWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateAdCodeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateAdCodeByIdWithTx(AdCodeVO vo,HttpServletRequest req ) {
		AdCodeDTO dto = AdCodeConverter.toDTO(vo);
		int rt = adCodeManage.updateAdCodeWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteAdCodeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteAdCodeWithTx(AdCodeVO vo,HttpServletRequest req ) {
		AdCodeDTO dto = AdCodeConverter.toDTO(vo);
		int rt = adCodeManage.deleteAdCodeWithTx(dto);	
		return success(rt);					 
	}
		
}
	