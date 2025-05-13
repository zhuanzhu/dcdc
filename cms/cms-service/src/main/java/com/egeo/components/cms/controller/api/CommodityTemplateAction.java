package com.egeo.components.cms.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.CommodityTemplateManage;
import com.egeo.components.cms.converter.CommodityTemplateConverter;
import com.egeo.components.cms.dto.CommodityTemplateDTO;
import com.egeo.components.cms.vo.CommodityTemplateVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/commodityTemplate")
public class CommodityTemplateAction extends BaseSpringController {
	
	@Resource(name="commodityTemplate")
	private CommodityTemplateManage commodityTemplateManage;


	/**
	 * 根据商品模版id查询商品模版信息
	 * @param commodityTemplateId
	 * @return
	 */
	@RequestMapping(value = "/findCommodityTemplateById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCommodityTemplateById(Long commodityTemplateId ) {
		
		Map<String, Object> rt = commodityTemplateManage.findCommodityTemplateById(commodityTemplateId);		
		return JsonResult.success(rt);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findCommodityTemplateAll")
	@ResponseBody
	public JsonResult<List<CommodityTemplateVO>> findCommodityTemplateAll(CommodityTemplateVO vo,HttpServletRequest req ) {
		CommodityTemplateDTO dto = CommodityTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CommodityTemplateDTO> rt = commodityTemplateManage.findCommodityTemplateAll(dto);	
		return JsonResult.success(CommodityTemplateConverter.toVO(rt));
					 
	}	

	
	/**
	 * // 商品模板列表
	 * @param vo
	 * @param page 分页信息
	 * @param req
	 * @return 
	 */
	@RequestMapping(value = "/findCommodityTemplateOfPage")
	@ResponseBody
	public JsonResult<PageResult<CommodityTemplateVO>> findCommodityTemplateOfPage(CommodityTemplateVO vo,Pagination page,HttpServletRequest req ) {
		CommodityTemplateDTO dto = CommodityTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CommodityTemplateDTO> rt = commodityTemplateManage.findCommodityTemplateOfPage(dto, page);
        List<CommodityTemplateVO> list = CommodityTemplateConverter.toVO(rt.getList());
        PageResult<CommodityTemplateVO> result = new PageResult<CommodityTemplateVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCommodityTemplateWithTx")
	@ResponseBody
	public JsonResult<Long> insertCommodityTemplateWithTx(CommodityTemplateVO vo,HttpServletRequest req ) {
		CommodityTemplateDTO dto = CommodityTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser userCache = this.getCacheUser();
		
		//用户id
		Long userId=userCache.getId();
		String name = userCache.getName();
		dto.setCreateUserid(userId);
		dto.setCreateUsername(name);
		dto.setUpdateUserid(userId);
		dto.setUpdateUsername(name);
		Long rt = commodityTemplateManage.insertCommodityTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCommodityTemplateByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCommodityTemplateByIdWithTx(CommodityTemplateVO vo,HttpServletRequest req ) {
		CommodityTemplateDTO dto = CommodityTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser userCache = this.getCacheUser();
		
		//用户id
		Long userId=userCache.getId();
		String name = userCache.getName();
		dto.setUpdateUserid(userId);
		dto.setUpdateUsername(name);
		int rt = commodityTemplateManage.updateCommodityTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCommodityTemplateWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCommodityTemplateWithTx(CommodityTemplateVO vo,HttpServletRequest req ) {
		CommodityTemplateDTO dto = CommodityTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = commodityTemplateManage.deleteCommodityTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 分页查询所有商品类型模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/queryCommodityTemplateOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> queryCommodityTemplateOfPage(
			CommodityTemplateVO vo,Pagination page,HttpServletRequest req ) {
		logger.info("分页查询所有商品类型模版信息");
		CommodityTemplateDTO dto = CommodityTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = commodityTemplateManage.queryCommodityTemplateOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}
	
	/**
	 * 根据商品类型模版id启用模版
	 * @param commodityTemplateId
	 * @return
	 */
	@RequestMapping(value = "/showCommodityTemplate")
	@ResponseBody
	public JsonResult<Boolean> showCommodityTemplateWithTx(Long commodityTemplateId) {
		logger.info("根据商品类型模版id启用模版,商品类型模版id:"+commodityTemplateId);
		boolean rt = commodityTemplateManage.showCommodityTemplateWithTx(commodityTemplateId);
		return JsonResult.success(rt);
					 
	}
	
	/**
	 * 根据商品类型查询商品模版
	 * @param commodityTemplateId
	 * @return
	 */
	@RequestMapping(value = "/findCommodityTemplateByType")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCommodityTemplateByType(Integer type,HttpServletRequest req) {
		logger.info("根据商品类型查询商品模版,商品模版类型:"+type);
		Long platformId = null;
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		Map<String, Object> rt = commodityTemplateManage.findCommodityTemplateByType(type,platformId);
		return JsonResult.success(rt);
					 
	}
	
		
}
	