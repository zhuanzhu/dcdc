package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProductCompanyManage;
import com.egeo.components.product.converter.MerchantProductCompanyConverter;
import com.egeo.components.product.vo.MerchantProductCompanyVO;
import com.egeo.components.product.dto.MerchantProductCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/merchantProductCompany")
public class MerchantProductCompanyAction extends BaseSpringController {
	
	@Resource(name="merchantProductCompany")
	private MerchantProductCompanyManage merchantProductCompanyManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProductCompanyById")
	@ResponseBody
	public JsonResult<MerchantProductCompanyVO> findMerchantProductCompanyById(Long id ) {
		
		MerchantProductCompanyDTO dto = new MerchantProductCompanyDTO();
		dto.setId(id);
		MerchantProductCompanyDTO rt = merchantProductCompanyManage.findMerchantProductCompanyById(dto);		
		return JsonResult.success(MerchantProductCompanyConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProductCompanyAll")
	@ResponseBody
	public JsonResult<List<MerchantProductCompanyVO>> findMerchantProductCompanyAll(MerchantProductCompanyVO vo,HttpServletRequest req ) {
		MerchantProductCompanyDTO dto = MerchantProductCompanyConverter.toDTO(vo);
		List<MerchantProductCompanyDTO> rt = merchantProductCompanyManage.findMerchantProductCompanyAll(dto);	
		return JsonResult.success(MerchantProductCompanyConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProductCompanyOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProductCompanyVO>> findMerchantProductCompanyOfPage(MerchantProductCompanyVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProductCompanyDTO dto = MerchantProductCompanyConverter.toDTO(vo);
		PageResult<MerchantProductCompanyDTO> rt = merchantProductCompanyManage.findMerchantProductCompanyOfPage(dto, page);
        List<MerchantProductCompanyVO> list = MerchantProductCompanyConverter.toVO(rt.getList());
        PageResult<MerchantProductCompanyVO> result = new PageResult<MerchantProductCompanyVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProductCompanyWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProductCompanyWithTx(MerchantProductCompanyVO vo,HttpServletRequest req ) {
		MerchantProductCompanyDTO dto = MerchantProductCompanyConverter.toDTO(vo);
		Long rt = merchantProductCompanyManage.insertMerchantProductCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProductCompanyByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProductCompanyByIdWithTx(MerchantProductCompanyVO vo,HttpServletRequest req ) {
		MerchantProductCompanyDTO dto = MerchantProductCompanyConverter.toDTO(vo);
		int rt = merchantProductCompanyManage.updateMerchantProductCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProductCompanyWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProductCompanyWithTx(MerchantProductCompanyVO vo,HttpServletRequest req ) {
		MerchantProductCompanyDTO dto = MerchantProductCompanyConverter.toDTO(vo);
		int rt = merchantProductCompanyManage.deleteMerchantProductCompanyWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	