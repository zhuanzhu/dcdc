package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdDescribeManage;
import com.egeo.components.product.converter.MerchantProdDescribeConverter;
import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.components.product.vo.MerchantProdDescribeVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantProdDescribe")
public class MerchantProdDescribeAction extends BaseSpringController {
	
	@Resource(name="merchantProdDescribe")
	private MerchantProdDescribeManage merchantProdDescribeManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProdDescribeById")
	@ResponseBody
	public JsonResult<MerchantProdDescribeVO> findMerchantProdDescribeById(Long id ) {
		
		MerchantProdDescribeDTO dto = new MerchantProdDescribeDTO();
		dto.setId(id);
		MerchantProdDescribeDTO rt = merchantProdDescribeManage.findMerchantProdDescribeById(dto);		
		return JsonResult.success(MerchantProdDescribeConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProdDescribeAll")
	@ResponseBody
	public JsonResult<List<MerchantProdDescribeVO>> findMerchantProdDescribeAll(MerchantProdDescribeVO vo,HttpServletRequest req ) {
		MerchantProdDescribeDTO dto = MerchantProdDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantProdDescribeDTO> rt = merchantProdDescribeManage.findMerchantProdDescribeAll(dto);	
		return JsonResult.success(MerchantProdDescribeConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProdDescribeOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProdDescribeVO>> findMerchantProdDescribeOfPage(MerchantProdDescribeVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProdDescribeDTO dto = MerchantProdDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantProdDescribeDTO> rt = merchantProdDescribeManage.findMerchantProdDescribeOfPage(dto, page);
        List<MerchantProdDescribeVO> list = MerchantProdDescribeConverter.toVO(rt.getList());
        PageResult<MerchantProdDescribeVO> result = new PageResult<MerchantProdDescribeVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProdDescribeWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProdDescribeWithTx(MerchantProdDescribeVO vo,HttpServletRequest req ) {
		MerchantProdDescribeDTO dto = MerchantProdDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantProdDescribeManage.insertMerchantProdDescribeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProdDescribeByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProdDescribeByIdWithTx(MerchantProdDescribeVO vo,HttpServletRequest req ) {
		MerchantProdDescribeDTO dto = MerchantProdDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdDescribeManage.updateMerchantProdDescribeWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProdDescribeWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProdDescribeWithTx(MerchantProdDescribeVO vo,HttpServletRequest req ) {
		MerchantProdDescribeDTO dto = MerchantProdDescribeConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdDescribeManage.deleteMerchantProdDescribeWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	