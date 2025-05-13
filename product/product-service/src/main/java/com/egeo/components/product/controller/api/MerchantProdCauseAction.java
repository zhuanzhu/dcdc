package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdCauseManage;
import com.egeo.components.product.converter.MerchantProdCauseConverter;
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.components.product.vo.MerchantProdCauseVO;
import com.egeo.core.Constant.BusinessExceptionConstant;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantProdCause")
public class MerchantProdCauseAction extends BaseSpringController {
	
	@Resource(name="merchantProdCause")
	private MerchantProdCauseManage merchantProdCauseManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProdCauseById")
	@ResponseBody
	public JsonResult<MerchantProdCauseVO> findMerchantProdCauseById(Long id ) {
		
		MerchantProdCauseDTO dto = new MerchantProdCauseDTO();
		dto.setId(id);
		MerchantProdCauseDTO rt = merchantProdCauseManage.findMerchantProdCauseById(dto);		
		return JsonResult.success(MerchantProdCauseConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProdCauseAll")
	@ResponseBody
	public JsonResult<List<MerchantProdCauseVO>> findMerchantProdCauseAll(MerchantProdCauseVO vo,HttpServletRequest req ) {
		MerchantProdCauseDTO dto = MerchantProdCauseConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantProdCauseDTO> rt = merchantProdCauseManage.findMerchantProdCauseAll(dto);	
		return JsonResult.success(MerchantProdCauseConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProdCauseOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProdCauseVO>> findMerchantProdCauseOfPage(MerchantProdCauseVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProdCauseDTO dto = MerchantProdCauseConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantProdCauseDTO> rt = merchantProdCauseManage.findMerchantProdCauseOfPage(dto, page);
        List<MerchantProdCauseVO> list = MerchantProdCauseConverter.toVO(rt.getList());
        PageResult<MerchantProdCauseVO> result = new PageResult<MerchantProdCauseVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProdCauseWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProdCauseWithTx(MerchantProdCauseVO vo,HttpServletRequest req ) {
		MerchantProdCauseDTO dto = MerchantProdCauseConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantProdCauseManage.insertMerchantProdCauseWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProdCauseByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProdCauseByIdWithTx(MerchantProdCauseVO vo,HttpServletRequest req ) {
		MerchantProdCauseDTO dto = MerchantProdCauseConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdCauseManage.updateMerchantProdCauseWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProdCauseWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProdCauseWithTx(MerchantProdCauseVO vo,HttpServletRequest req ) {
		MerchantProdCauseDTO dto = MerchantProdCauseConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdCauseManage.deleteMerchantProdCauseWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 审核是否通过
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/isPass")
	@ResponseBody
	public JsonResult<Integer> isPass(MerchantProdCauseVO vo,HttpServletRequest req ) {
		MerchantProdCauseDTO dto = MerchantProdCauseConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(EmptyUtil.isEmpty(vo.getType())){
			throw new BusinessException(BusinessExceptionConstant.IS_PASS_TYPE_NO__EMPTY,"请选择是否通过");
		}
		int rt = merchantProdCauseManage.isPass(dto);	
		return JsonResult.success(rt);						 
	}
		
}
	