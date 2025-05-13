package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProductStoreManage;
import com.egeo.components.product.converter.MerchantProductStoreConverter;
import com.egeo.components.product.vo.MerchantProductStoreVO;
import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/merchantProductStore")
public class MerchantProductStoreAction extends BaseSpringController {
	
	@Resource(name="merchantProductStore")
	private MerchantProductStoreManage merchantProductStoreManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProductStoreById")
	@ResponseBody
	public JsonResult<MerchantProductStoreVO> findMerchantProductStoreById(Long id ) {
		
		MerchantProductStoreDTO dto = new MerchantProductStoreDTO();
		dto.setId(id);
		MerchantProductStoreDTO rt = merchantProductStoreManage.findMerchantProductStoreById(dto);		
		return JsonResult.success(MerchantProductStoreConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProductStoreAll")
	@ResponseBody
	public JsonResult<List<MerchantProductStoreVO>> findMerchantProductStoreAll(MerchantProductStoreVO vo,HttpServletRequest req ) {
		MerchantProductStoreDTO dto = MerchantProductStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantProductStoreDTO> rt = merchantProductStoreManage.findMerchantProductStoreAll(dto);	
		return JsonResult.success(MerchantProductStoreConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProductStoreOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProductStoreVO>> findMerchantProductStoreOfPage(MerchantProductStoreVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProductStoreDTO dto = MerchantProductStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantProductStoreDTO> rt = merchantProductStoreManage.findMerchantProductStoreOfPage(dto, page);
        List<MerchantProductStoreVO> list = MerchantProductStoreConverter.toVO(rt.getList());
        PageResult<MerchantProductStoreVO> result = new PageResult<MerchantProductStoreVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProductStoreWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProductStoreWithTx(MerchantProductStoreVO vo,HttpServletRequest req ) {
		MerchantProductStoreDTO dto = MerchantProductStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantProductStoreManage.insertMerchantProductStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProductStoreByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProductStoreByIdWithTx(MerchantProductStoreVO vo,HttpServletRequest req ) {
		MerchantProductStoreDTO dto = MerchantProductStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProductStoreManage.updateMerchantProductStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProductStoreWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProductStoreWithTx(MerchantProductStoreVO vo,HttpServletRequest req ) {
		MerchantProductStoreDTO dto = MerchantProductStoreConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProductStoreManage.deleteMerchantProductStoreWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	