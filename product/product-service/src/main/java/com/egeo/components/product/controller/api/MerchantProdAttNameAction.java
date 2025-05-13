package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdAttNameManage;
import com.egeo.components.product.converter.MerchantProdAttNameConverter;
import com.egeo.components.product.vo.MerchantProdAttNameVO;
import com.egeo.components.product.dto.MerchantProdAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/merchantProdAttName")
public class MerchantProdAttNameAction extends BaseSpringController {
	
	@Resource(name="merchantProdAttName")
	private MerchantProdAttNameManage merchantProdAttNameManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProdAttNameById")
	@ResponseBody
	public JsonResult<MerchantProdAttNameVO> findMerchantProdAttNameById(Long id ) {
		
		MerchantProdAttNameDTO dto = new MerchantProdAttNameDTO();
		dto.setId(id);
		MerchantProdAttNameDTO rt = merchantProdAttNameManage.findMerchantProdAttNameById(dto);		
		return JsonResult.success(MerchantProdAttNameConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProdAttNameAll")
	@ResponseBody
	public JsonResult<List<MerchantProdAttNameVO>> findMerchantProdAttNameAll(MerchantProdAttNameVO vo,HttpServletRequest req ) {
		MerchantProdAttNameDTO dto = MerchantProdAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantProdAttNameDTO> rt = merchantProdAttNameManage.findMerchantProdAttNameAll(dto);	
		return JsonResult.success(MerchantProdAttNameConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProdAttNameOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProdAttNameVO>> findMerchantProdAttNameOfPage(MerchantProdAttNameVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProdAttNameDTO dto = MerchantProdAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantProdAttNameDTO> rt = merchantProdAttNameManage.findMerchantProdAttNameOfPage(dto, page);
        List<MerchantProdAttNameVO> list = MerchantProdAttNameConverter.toVO(rt.getList());
        PageResult<MerchantProdAttNameVO> result = new PageResult<MerchantProdAttNameVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProdAttNameWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProdAttNameWithTx(MerchantProdAttNameVO vo,HttpServletRequest req ) {
		MerchantProdAttNameDTO dto = MerchantProdAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantProdAttNameManage.insertMerchantProdAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProdAttNameByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProdAttNameByIdWithTx(MerchantProdAttNameVO vo,HttpServletRequest req ) {
		MerchantProdAttNameDTO dto = MerchantProdAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdAttNameManage.updateMerchantProdAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProdAttNameWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProdAttNameWithTx(MerchantProdAttNameVO vo,HttpServletRequest req ) {
		MerchantProdAttNameDTO dto = MerchantProdAttNameConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdAttNameManage.deleteMerchantProdAttNameWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	