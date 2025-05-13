package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProductMembershipManage;
import com.egeo.components.product.converter.MerchantProductMembershipConverter;
import com.egeo.components.product.vo.MerchantProductMembershipVO;
import com.egeo.components.product.dto.MerchantProductMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/merchantProductMembership")
public class MerchantProductMembershipAction extends BaseSpringController {
	
	@Resource(name="merchantProductMembership")
	private MerchantProductMembershipManage merchantProductMembershipManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProductMembershipById")
	@ResponseBody
	public JsonResult<MerchantProductMembershipVO> findMerchantProductMembershipById(Long id ) {
		
		MerchantProductMembershipDTO dto = new MerchantProductMembershipDTO();
		dto.setId(id);
		MerchantProductMembershipDTO rt = merchantProductMembershipManage.findMerchantProductMembershipById(dto);		
		return JsonResult.success(MerchantProductMembershipConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProductMembershipAll")
	@ResponseBody
	public JsonResult<List<MerchantProductMembershipVO>> findMerchantProductMembershipAll(MerchantProductMembershipVO vo,HttpServletRequest req ) {
		MerchantProductMembershipDTO dto = MerchantProductMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantProductMembershipDTO> rt = merchantProductMembershipManage.findMerchantProductMembershipAll(dto);	
		return JsonResult.success(MerchantProductMembershipConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProductMembershipOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProductMembershipVO>> findMerchantProductMembershipOfPage(MerchantProductMembershipVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProductMembershipDTO dto = MerchantProductMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantProductMembershipDTO> rt = merchantProductMembershipManage.findMerchantProductMembershipOfPage(dto, page);
        List<MerchantProductMembershipVO> list = MerchantProductMembershipConverter.toVO(rt.getList());
        PageResult<MerchantProductMembershipVO> result = new PageResult<MerchantProductMembershipVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProductMembershipWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProductMembershipWithTx(MerchantProductMembershipVO vo,HttpServletRequest req ) {
		MerchantProductMembershipDTO dto = MerchantProductMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantProductMembershipManage.insertMerchantProductMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProductMembershipByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProductMembershipByIdWithTx(MerchantProductMembershipVO vo,HttpServletRequest req ) {
		MerchantProductMembershipDTO dto = MerchantProductMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProductMembershipManage.updateMerchantProductMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProductMembershipWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProductMembershipWithTx(MerchantProductMembershipVO vo,HttpServletRequest req ) {
		MerchantProductMembershipDTO dto = MerchantProductMembershipConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProductMembershipManage.deleteMerchantProductMembershipWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	