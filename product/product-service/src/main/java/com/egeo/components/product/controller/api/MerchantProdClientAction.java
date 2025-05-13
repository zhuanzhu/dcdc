package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdClientManage;
import com.egeo.components.product.converter.MerchantProdClientConverter;
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.components.product.vo.MerchantProdClientVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantProdClient")
public class MerchantProdClientAction extends BaseSpringController {
	
	@Resource(name="merchantProdClient")
	private MerchantProdClientManage merchantProdClientManage;


	// 业务方法：
	@RequestMapping(value = "/findMerchantProdClientById")
	@ResponseBody
	public JsonResult<MerchantProdClientVO> findMerchantProdClientById(Long id ) {
		
		MerchantProdClientDTO dto = new MerchantProdClientDTO();
		dto.setId(id);
		MerchantProdClientDTO rt = merchantProdClientManage.findMerchantProdClientById(dto);		
		return JsonResult.success(MerchantProdClientConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMerchantProdClientAll")
	@ResponseBody
	public JsonResult<List<MerchantProdClientVO>> findMerchantProdClientAll(MerchantProdClientVO vo,HttpServletRequest req ) {
		MerchantProdClientDTO dto = MerchantProdClientConverter.toDTO(vo);
		List<MerchantProdClientDTO> rt = merchantProdClientManage.findMerchantProdClientAll(dto);	
		return JsonResult.success(MerchantProdClientConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMerchantProdClientOfPage")
	@ResponseBody
	public JsonResult<PageResult<MerchantProdClientVO>> findMerchantProdClientOfPage(MerchantProdClientVO vo,Pagination page,HttpServletRequest req ) {
		MerchantProdClientDTO dto = MerchantProdClientConverter.toDTO(vo);
		PageResult<MerchantProdClientDTO> rt = merchantProdClientManage.findMerchantProdClientOfPage(dto, page);
        List<MerchantProdClientVO> list = MerchantProdClientConverter.toVO(rt.getList());
        PageResult<MerchantProdClientVO> result = new PageResult<MerchantProdClientVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMerchantProdClientWithTx")
	@ResponseBody
	public JsonResult<Long> insertMerchantProdClientWithTx(MerchantProdClientVO vo,HttpServletRequest req ) {
		MerchantProdClientDTO dto = MerchantProdClientConverter.toDTO(vo);
		Long rt = merchantProdClientManage.insertMerchantProdClientWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMerchantProdClientByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMerchantProdClientByIdWithTx(MerchantProdClientVO vo,HttpServletRequest req ) {
		MerchantProdClientDTO dto = MerchantProdClientConverter.toDTO(vo);
		int rt = merchantProdClientManage.updateMerchantProdClientWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMerchantProdClientWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMerchantProdClientWithTx(MerchantProdClientVO vo,HttpServletRequest req ) {
		MerchantProdClientDTO dto = MerchantProdClientConverter.toDTO(vo);
		int rt = merchantProdClientManage.deleteMerchantProdClientWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	