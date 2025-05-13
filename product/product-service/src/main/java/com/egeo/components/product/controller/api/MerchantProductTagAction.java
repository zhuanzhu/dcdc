package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProductTagManage;
import com.egeo.components.product.converter.MerchantProductTagConverter;
import com.egeo.components.product.vo.MerchantProductTagVO;
import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/merchantProductTag")
public class MerchantProductTagAction extends BaseSpringController {
	
	@Resource(name="merchantProductTag")
	private MerchantProductTagManage merchantProductTagManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProductTagById")
	@ResponseBody
	public JsonResult<MerchantProductTagVO> findMerchantProductTagById(Long id ) {
		
		MerchantProductTagDTO dto = new MerchantProductTagDTO();
		dto.setId(id);
		MerchantProductTagDTO rt = merchantProductTagManage.findMerchantProductTagById(dto);		
		return JsonResult.success(MerchantProductTagConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProductTagAll")
	@ResponseBody
	public JsonResult<List<MerchantProductTagVO>> findMerchantProductTagAll(MerchantProductTagVO vo,HttpServletRequest req ) {
		MerchantProductTagDTO dto = MerchantProductTagConverter.toDTO(vo);
		List<MerchantProductTagDTO> rt = merchantProductTagManage.findMerchantProductTagAll(dto);	
		return JsonResult.success(MerchantProductTagConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProductTagOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProductTagVO>> findMerchantProductTagOfPage(MerchantProductTagVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProductTagDTO dto = MerchantProductTagConverter.toDTO(vo);
		PageResult<MerchantProductTagDTO> rt = merchantProductTagManage.findMerchantProductTagOfPage(dto, page);
        List<MerchantProductTagVO> list = MerchantProductTagConverter.toVO(rt.getList());
        PageResult<MerchantProductTagVO> result = new PageResult<MerchantProductTagVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProductTagWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProductTagWithTx(MerchantProductTagVO vo,HttpServletRequest req ) {
		MerchantProductTagDTO dto = MerchantProductTagConverter.toDTO(vo);
		Long rt = merchantProductTagManage.insertMerchantProductTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProductTagByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProductTagByIdWithTx(MerchantProductTagVO vo,HttpServletRequest req ) {
		MerchantProductTagDTO dto = MerchantProductTagConverter.toDTO(vo);
		int rt = merchantProductTagManage.updateMerchantProductTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProductTagWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProductTagWithTx(MerchantProductTagVO vo,HttpServletRequest req ) {
		MerchantProductTagDTO dto = MerchantProductTagConverter.toDTO(vo);
		int rt = merchantProductTagManage.deleteMerchantProductTagWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	