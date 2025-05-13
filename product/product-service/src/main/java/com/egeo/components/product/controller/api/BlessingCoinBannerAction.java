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
import com.egeo.components.product.business.BlessingCoinBannerManage;
import com.egeo.components.product.converter.BlessingCoinBannerConverter;
import com.egeo.components.product.dto.BlessingCoinBannerDTO;
import com.egeo.components.product.vo.BlessingCoinBannerVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/blessingCoinBanner")
public class BlessingCoinBannerAction extends BaseSpringController {
	
	@Resource(name="blessingCoinBanner")
	private BlessingCoinBannerManage blessingCoinBannerManage;


	/**
	 * 根据积分轮播图id查询积分轮播图
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findBlessingCoinBannerById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findBlessingCoinBannerById(Long id ) {
		logger.info("根据积分轮播图id查询积分轮播图");
		BlessingCoinBannerDTO dto = new BlessingCoinBannerDTO();
		dto.setId(id);
		Map<String, Object> rt = blessingCoinBannerManage.findBlessingCoinBannerById(dto);		
		return JsonResult.success(rt);
	}



	/**
	 * 查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findBlessingCoinBannerAll")
	@ResponseBody
	public JsonResult<List<BlessingCoinBannerVO>> findBlessingCoinBannerAll(BlessingCoinBannerVO vo,HttpServletRequest req ) {
		logger.info("查询所有有效轮播图信息");
		BlessingCoinBannerDTO dto = BlessingCoinBannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<BlessingCoinBannerDTO> rt = blessingCoinBannerManage.findBlessingCoinBannerAll(dto);	
		return JsonResult.success(BlessingCoinBannerConverter.toVO(rt));
					 
	}	

	/**
	 * 分页查询所有轮播图
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findBlessingCoinBannerOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findBlessingCoinBannerOfPage(BlessingCoinBannerVO vo,Pagination page,HttpServletRequest req ) {
		logger.info("查询所有有效轮播图信息");
		BlessingCoinBannerDTO dto = BlessingCoinBannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = blessingCoinBannerManage.findBlessingCoinBannerOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	/**
	 * 添加轮播图
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertBlessingCoinBannerWithTx")
	@ResponseBody
	public JsonResult<Long> insertBlessingCoinBannerWithTx(BlessingCoinBannerVO vo, String companyList, HttpServletRequest req ) {
		BlessingCoinBannerDTO dto = BlessingCoinBannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Long> companyIds = new ArrayList<Long>(JSONArray.parseArray(companyList, Long.class));
		Long rt = blessingCoinBannerManage.insertBlessingCoinBannerWithTx(dto,companyIds);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据轮播图id修改轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateBlessingCoinBannerByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateBlessingCoinBannerByIdWithTx(BlessingCoinBannerVO vo,String companyList, HttpServletRequest req ) {
		BlessingCoinBannerDTO dto = BlessingCoinBannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Long> companyIds = new ArrayList<Long>(JSONArray.parseArray(companyList, Long.class));
		int rt = blessingCoinBannerManage.updateBlessingCoinBannerWithTx(dto,companyIds);	
		return JsonResult.success(rt);					 
	}

	/**
	 * 根据轮播图id删除轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteBlessingCoinBannerWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteBlessingCoinBannerWithTx(BlessingCoinBannerVO vo,HttpServletRequest req ) {
		BlessingCoinBannerDTO dto = BlessingCoinBannerConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = blessingCoinBannerManage.deleteBlessingCoinBannerWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findBlessingCoinBannerAllApp")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findBlessingCoinBannerAllApp(BlessingCoinBannerVO vo,HttpServletRequest req ) {
		logger.info("客户端查询所有有效轮播图信息");
		CacheUser userCache = this.getCacheUser();
		Long companyId=userCache.getCompanyId();
		BlessingCoinBannerDTO dto = BlessingCoinBannerConverter.toDTO(vo);
		dto.setCompanyId(companyId);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Map<String, Object>> rt = blessingCoinBannerManage.findBlessingCoinBannerAllApp(dto);	
		return JsonResult.success(rt);
					 
	}	
		
}
	