package com.egeo.components.stock.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.ContactGroupStockManage;
import com.egeo.components.stock.converter.ContactGroupStockConverter;
import com.egeo.components.stock.vo.ContactGroupStockVO;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/stock/contactGroupStock")
public class ContactGroupStockAction extends BaseSpringController {
	
	@Resource(name="contactGroupStock")
	private ContactGroupStockManage contactGroupStockManage;


	// 业务方法：
	@RequestMapping(value = "/findContactGroupStockById")
	@ResponseBody
	public JsonResult<ContactGroupStockVO> findContactGroupStockById(Long id ) {
		
		ContactGroupStockDTO dto = new ContactGroupStockDTO();
		dto.setId(id);
		ContactGroupStockDTO rt = contactGroupStockManage.findContactGroupStockById(dto);		
		return JsonResult.success(ContactGroupStockConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findContactGroupStockAll")
	@ResponseBody
	public JsonResult<List<ContactGroupStockVO>> findContactGroupStockAll(ContactGroupStockVO vo,HttpServletRequest req ) {
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		List<ContactGroupStockDTO> rt = contactGroupStockManage.findContactGroupStockAll(dto);	
		return JsonResult.success(ContactGroupStockConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findContactGroupStockOfPage")
	@ResponseBody
	public JsonResult<PageResult<ContactGroupStockVO>> findContactGroupStockOfPage(ContactGroupStockVO vo,Pagination page,HttpServletRequest req ) {
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		PageResult<ContactGroupStockDTO> rt = contactGroupStockManage.findContactGroupStockOfPage(dto, page);
        List<ContactGroupStockVO> list = ContactGroupStockConverter.toVO(rt.getList());
        PageResult<ContactGroupStockVO> result = new PageResult<ContactGroupStockVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateContactGroupStockByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateContactGroupStockByIdWithTx(ContactGroupStockVO vo,HttpServletRequest req ) {
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		int rt = contactGroupStockManage.updateContactGroupStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteContactGroupStockWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteContactGroupStockWithTx(ContactGroupStockVO vo,HttpServletRequest req ) {
		ContactGroupStockDTO dto = ContactGroupStockConverter.toDTO(vo);
		int rt = contactGroupStockManage.deleteContactGroupStockWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	