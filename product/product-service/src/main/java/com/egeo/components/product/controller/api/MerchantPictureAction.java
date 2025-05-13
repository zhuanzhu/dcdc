package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantPictureManage;
import com.egeo.components.product.converter.MerchantPictureConverter;
import com.egeo.components.product.vo.MerchantPictureVO;
import com.egeo.components.product.dto.MerchantPictureDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/merchantPicture")
public class MerchantPictureAction extends BaseSpringController {
	
	@Resource(name="merchantPicture")
	private MerchantPictureManage merchantPictureManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantPictureById")
	@ResponseBody
	public JsonResult<MerchantPictureVO> findMerchantPictureById(Long id ) {
		
		MerchantPictureDTO dto = new MerchantPictureDTO();
		dto.setId(id);
		MerchantPictureDTO rt = merchantPictureManage.findMerchantPictureById(dto);		
		return JsonResult.success(MerchantPictureConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantPictureAll")
	@ResponseBody
	public JsonResult<List<MerchantPictureVO>> findMerchantPictureAll(MerchantPictureVO vo,HttpServletRequest req ) {
		MerchantPictureDTO dto = MerchantPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MerchantPictureDTO> rt = merchantPictureManage.findMerchantPictureAll(dto);	
		return JsonResult.success(MerchantPictureConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantPictureOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantPictureVO>> findMerchantPictureOfPage(MerchantPictureVO vo,Pagination page,HttpServletRequest req ) {
		MerchantPictureDTO dto = MerchantPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MerchantPictureDTO> rt = merchantPictureManage.findMerchantPictureOfPage(dto, page);
        List<MerchantPictureVO> list = MerchantPictureConverter.toVO(rt.getList());
        PageResult<MerchantPictureVO> result = new PageResult<MerchantPictureVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantPictureWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantPictureWithTx(MerchantPictureVO vo,HttpServletRequest req ) {
		MerchantPictureDTO dto = MerchantPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = merchantPictureManage.insertMerchantPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantPictureByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantPictureByIdWithTx(MerchantPictureVO vo,HttpServletRequest req ) {
		MerchantPictureDTO dto = MerchantPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantPictureManage.updateMerchantPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantPictureWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantPictureWithTx(MerchantPictureVO vo,HttpServletRequest req ) {
		MerchantPictureDTO dto = MerchantPictureConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = merchantPictureManage.deleteMerchantPictureWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	