package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdSalesRecordManage;
import com.egeo.components.product.converter.MerchantProdSalesRecordConverter;
import com.egeo.components.product.vo.MerchantProdSalesRecordVO;
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/merchantProdSalesRecord")
public class MerchantProdSalesRecordAction extends BaseSpringController {
	
	@Resource(name="merchantProdSalesRecord")
	private MerchantProdSalesRecordManage merchantProdSalesRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProdSalesRecordById")
	@ResponseBody
	public JsonResult<MerchantProdSalesRecordVO> findMerchantProdSalesRecordById(Long id ) {
		
		MerchantProdSalesRecordDTO dto = new MerchantProdSalesRecordDTO();
		dto.setId(id);
		MerchantProdSalesRecordDTO rt = merchantProdSalesRecordManage.findMerchantProdSalesRecordById(dto);		
		return JsonResult.success(MerchantProdSalesRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProdSalesRecordAll")
	@ResponseBody
	public JsonResult<List<MerchantProdSalesRecordVO>> findMerchantProdSalesRecordAll(MerchantProdSalesRecordVO vo,HttpServletRequest req ) {
		MerchantProdSalesRecordDTO dto = MerchantProdSalesRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantProdSalesRecordDTO> rt = merchantProdSalesRecordManage.findMerchantProdSalesRecordAll(dto);	
		return JsonResult.success(MerchantProdSalesRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProdSalesRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProdSalesRecordVO>> findMerchantProdSalesRecordOfPage(MerchantProdSalesRecordVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProdSalesRecordDTO dto = MerchantProdSalesRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantProdSalesRecordDTO> rt = merchantProdSalesRecordManage.findMerchantProdSalesRecordOfPage(dto, page);
        List<MerchantProdSalesRecordVO> list = MerchantProdSalesRecordConverter.toVO(rt.getList());
        PageResult<MerchantProdSalesRecordVO> result = new PageResult<MerchantProdSalesRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProdSalesRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordVO vo,HttpServletRequest req ) {
		MerchantProdSalesRecordDTO dto = MerchantProdSalesRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantProdSalesRecordManage.insertMerchantProdSalesRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProdSalesRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProdSalesRecordByIdWithTx(MerchantProdSalesRecordVO vo,HttpServletRequest req ) {
		MerchantProdSalesRecordDTO dto = MerchantProdSalesRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdSalesRecordManage.updateMerchantProdSalesRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProdSalesRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProdSalesRecordWithTx(MerchantProdSalesRecordVO vo,HttpServletRequest req ) {
		MerchantProdSalesRecordDTO dto = MerchantProdSalesRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdSalesRecordManage.deleteMerchantProdSalesRecordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	