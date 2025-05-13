package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdPictureManage;
import com.egeo.components.product.converter.MerchantProdPictureConverter;
import com.egeo.components.product.vo.MerchantProdPictureVO;
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/merchantProdPicture")
public class MerchantProdPictureAction extends BaseSpringController {
	
	@Resource(name="merchantProdPicture")
	private MerchantProdPictureManage merchantProdPictureManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProdPictureById")
	@ResponseBody
	public JsonResult<MerchantProdPictureVO> findMerchantProdPictureById(Long id ) {
		
		MerchantProdPictureDTO dto = new MerchantProdPictureDTO();
		dto.setId(id);
		MerchantProdPictureDTO rt = merchantProdPictureManage.findMerchantProdPictureById(dto);		
		return JsonResult.success(MerchantProdPictureConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProdPictureAll")
	@ResponseBody
	public JsonResult<List<MerchantProdPictureVO>> findMerchantProdPictureAll(MerchantProdPictureVO vo,HttpServletRequest req ) {
		MerchantProdPictureDTO dto = MerchantProdPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantProdPictureDTO> rt = merchantProdPictureManage.findMerchantProdPictureAll(dto);	
		return JsonResult.success(MerchantProdPictureConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProdPictureOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProdPictureVO>> findMerchantProdPictureOfPage(MerchantProdPictureVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProdPictureDTO dto = MerchantProdPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantProdPictureDTO> rt = merchantProdPictureManage.findMerchantProdPictureOfPage(dto, page);
        List<MerchantProdPictureVO> list = MerchantProdPictureConverter.toVO(rt.getList());
        PageResult<MerchantProdPictureVO> result = new PageResult<MerchantProdPictureVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProdPictureWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProdPictureWithTx(MerchantProdPictureVO vo,HttpServletRequest req ) {
		MerchantProdPictureDTO dto = MerchantProdPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantProdPictureManage.insertMerchantProdPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProdPictureByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProdPictureByIdWithTx(MerchantProdPictureVO vo,HttpServletRequest req ) {
		MerchantProdPictureDTO dto = MerchantProdPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdPictureManage.updateMerchantProdPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProdPictureWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProdPictureWithTx(MerchantProdPictureVO vo,HttpServletRequest req ) {
		MerchantProdPictureDTO dto = MerchantProdPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantProdPictureManage.deleteMerchantProdPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	