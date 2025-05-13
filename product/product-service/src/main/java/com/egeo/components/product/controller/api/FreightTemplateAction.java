package com.egeo.components.product.controller.api;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.business.FreightTemplateManage;
import com.egeo.components.product.converter.FreightTemplateConverter;
import com.egeo.components.product.vo.FreightTemplateVO;
import com.egeo.components.product.dto.FreightRegulationDTO;
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/freightTemplate")
public class FreightTemplateAction extends BaseSpringController {
	
	@Resource(name="freightTemplate")
	private FreightTemplateManage freightTemplateManage;


	/**
	 * 根据运费模版Id查询运费模版信息
	 * @param freightTemplateId
	 * @return
	 */
	@RequestMapping(value = "/findFreightTemplateById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findFreightTemplateById(Long freightTemplateId ) {
		logger.info("根据运费模版Id查询运费模版信息，运费模版Id：" + freightTemplateId);
		Map<String, Object> rt = freightTemplateManage.findFreightTemplateById(freightTemplateId);
		return JsonResult.success(rt);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findFreightTemplateAll")
	@ResponseBody
	public JsonResult<List<FreightTemplateVO>> findFreightTemplateAll(FreightTemplateVO vo,HttpServletRequest req ) {
		FreightTemplateDTO dto = FreightTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<FreightTemplateDTO> rt = freightTemplateManage.findFreightTemplateAll(dto);	
		return JsonResult.success(FreightTemplateConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findFreightTemplateOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findFreightTemplateOfPage(FreightTemplateVO vo,Pagination page,HttpServletRequest req ) {
		FreightTemplateDTO dto = FreightTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		return JsonResult.success(freightTemplateManage.findFreightTemplateOfPage(dto, page));
					 
	}


	/**
	 * 新增运费模版
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertFreightTemplateWithTx")
	@ResponseBody
	public JsonResult<Long> insertFreightTemplateWithTx(FreightTemplateVO vo,String freightRegulations,HttpServletRequest req ) {
		logger.info("新增运费模版");
		FreightTemplateDTO dto = FreightTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<FreightRegulationDTO> freightRegulationList = new ArrayList<>(JSONArray.parseArray(freightRegulations, FreightRegulationDTO.class));
		Long rt = freightTemplateManage.insertFreightTemplateWithTx(dto,freightRegulationList);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据运费模版id修改运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateFreightTemplateByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFreightTemplateByIdWithTx(FreightTemplateVO vo,String freightRegulations,HttpServletRequest req ) {
		logger.info("根据运费模版id修改运费模版，运费模版Id:"+vo.getId());
		FreightTemplateDTO dto = FreightTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<FreightRegulationDTO> freightRegulationList = new ArrayList<>(JSONArray.parseArray(freightRegulations, FreightRegulationDTO.class));
		int rt = freightTemplateManage.updateFreightTemplateWithTx(dto,freightRegulationList);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteFreightTemplateWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteFreightTemplateWithTx(FreightTemplateVO vo,HttpServletRequest req ) {
		FreightTemplateDTO dto = FreightTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = freightTemplateManage.deleteFreightTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据运费模版id启用运费模版
	 * @param vo
	 * @param freightRegulations
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/startFreightTemplateWithTx")
	@ResponseBody
	public JsonResult<Integer> startFreightTemplateWithTx(Long freightTemplateId ) {
		logger.info("根据运费模版id启用运费模版，运费模版Id:"+ freightTemplateId);
		int rt = freightTemplateManage.startFreightTemplateWithTx(freightTemplateId);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据商家Id查询运费模版信息
	 * @param freightTemplateId
	 * @return
	 */
	@RequestMapping(value = "/freightTemplateByMerchantId")
	@ResponseBody
	public JsonResult<Map<String, Object>> freightTemplateByMerchantId(Long merchantId,Long storeId,HttpServletRequest req) {
		logger.info("根据商家Id查询运费模版信息，商家Id：" + merchantId);
		Long platformId = null;
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		Map<String, Object> rt = freightTemplateManage.freightTemplateByMerchantId(merchantId,storeId,platformId);
		return JsonResult.success(rt);
					 
	}
	
		
}
	