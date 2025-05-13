package com.egeo.components.product.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.TopSearchManage;
import com.egeo.components.product.converter.TopSearchConverter;
import com.egeo.components.product.dto.TopSearchDTO;
import com.egeo.components.product.vo.TopSearchVO;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/topSearch")
public class TopSearchAction extends BaseSpringController {
	
	@Resource(name="topSearch")
	private TopSearchManage topSearchManage;


	/**
	 * 根据热搜id查询热搜信息
	 * @param topSearchId
	 * @return
	 */
	@RequestMapping(value = "/findTopSearchById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findTopSearchById(Long topSearchId ) {
		
		TopSearchDTO dto = new TopSearchDTO();
		dto.setId(topSearchId);
		Map<String, Object> rt = topSearchManage.findTopSearchById(dto);		
		return JsonResult.success(rt);
					 
	}



	/**
	 * 显示启用的热搜
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findStartTopSearchAll")
	@ResponseBody
	public JsonResult<Map<String, Object>> findStartTopSearchAll(TopSearchVO vo,HttpServletRequest req ) {
		TopSearchDTO dto = TopSearchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Map<String, Object> rt = topSearchManage.findStartTopSearchAll(dto);	
		return JsonResult.success(rt);
					 
	}	

	/**
	 * 分页查询热搜
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findTopSearchOfPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> findTopSearchOfPage(TopSearchVO vo,Pagination page,HttpServletRequest req ) {
		TopSearchDTO dto = TopSearchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Map<String, Object> rt = topSearchManage.findTopSearchOfPage(dto, page);
		return JsonResult.success(rt);
	}


	/**
	 * 新增热搜
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertTopSearchWithTx")
	@ResponseBody
	public JsonResult<Long> insertTopSearchWithTx(TopSearchVO vo,HttpServletRequest req ) {
		TopSearchDTO dto = TopSearchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(EmptyUtil.isEmpty(dto.getName()))
			return JsonResult.fail("热搜名称不能为空");
		if(dto.getName().length() > 8 )
			return JsonResult.fail("热搜名称最多可输入8位");
		Long rt = topSearchManage.insertTopSearchWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateTopSearchByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateTopSearchByIdWithTx(TopSearchVO vo,HttpServletRequest req ) {
		TopSearchDTO dto = TopSearchConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		if(EmptyUtil.isEmpty(dto.getName()))
			return JsonResult.fail("热搜名称不能为空");
		if(dto.getName().length() > 8 )
			return JsonResult.fail("热搜名称最多可输入8位");
		if(EmptyUtil.isEmpty(dto.getSortValue()))
			return JsonResult.fail("请输入热搜编号");
		TopSearchDTO topSearchDTO = new TopSearchDTO();
		topSearchDTO.setSortValue(dto.getSortValue());
		List<TopSearchDTO> list = topSearchManage.findTopSearchAll(topSearchDTO);
		if(EmptyUtil.isNotEmpty(list) && !dto.getId().equals(list.get(0).getId()))
			return JsonResult.fail("热搜编号重复");
		int rt = topSearchManage.updateTopSearchWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	/**
	 * 根据id启用停用热搜
	 * @param topSearchId
	 * @return
	 */
	@RequestMapping(value = "/startStopTopSearch")
	@ResponseBody
	public JsonResult<Integer> startStopTopSearchWithTx(Long topSearchId) {
		int rt = topSearchManage.startStopTopSearchWithTx(topSearchId);	
		return JsonResult.success(rt);					 
	}
	
		
}
	