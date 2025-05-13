package com.egeo.components.order.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.business.AddressManage;
import com.egeo.components.order.converter.AddressConverter;
import com.egeo.components.order.dto.AddressDTO;
import com.egeo.components.order.vo.AddressVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/order/address")
public class AddressAction extends BaseSpringController {
	
	@Resource(name="address")
	private AddressManage addressManage;


	// 业务方法：
	@RequestMapping(value = "/findAddressById")
	@ResponseBody
	public JsonResult<AddressVO> findAddressById(Long id ) {
		
		AddressDTO dto = new AddressDTO();
		dto.setId(id);
		AddressDTO rt = addressManage.findAddressById(dto);		
		return JsonResult.success(AddressConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findAddressAll")
	@ResponseBody
	public JsonResult<List<AddressVO>> findAddressAll(AddressVO vo,HttpServletRequest req ) {
		AddressDTO dto = AddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<AddressDTO> rt = addressManage.findAddressAll(dto);	
		return JsonResult.success(AddressConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findAddressOfPage")
	@ResponseBody
	public JsonResult<PageResult<AddressVO>> findAddressOfPage(AddressVO vo,Pagination page,HttpServletRequest req ) {
		AddressDTO dto = AddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<AddressDTO> rt = addressManage.findAddressOfPage(dto, page);
        List<AddressVO> list = AddressConverter.toVO(rt.getList());
        PageResult<AddressVO> result = new PageResult<AddressVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertAddressWithTx")
	@ResponseBody
	public JsonResult<Long> insertAddressWithTx(AddressVO vo,HttpServletRequest req ) {
		AddressDTO dto = AddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = addressManage.insertAddressWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateAddressByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateAddressByIdWithTx(AddressVO vo,HttpServletRequest req ) {
		AddressDTO dto = AddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = addressManage.updateAddressWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteAddressWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteAddressWithTx(AddressVO vo,HttpServletRequest req ) {
		AddressDTO dto = AddressConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = addressManage.deleteAddressWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	