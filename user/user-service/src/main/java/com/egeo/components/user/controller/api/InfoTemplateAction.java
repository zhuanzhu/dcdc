package com.egeo.components.user.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.user.vo.InfoTemplateVO;
import com.egeo.components.user.business.InfoTemplateManage;
import com.egeo.components.user.converter.InfoTemplateConverter;
import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/infoTemplate")
public class InfoTemplateAction extends BaseSpringController {
	
	@Resource(name="infoTemplate")
	private InfoTemplateManage infoTemplateManage;


	/**
	 * 根据消息模版id查询消息模版信息
	 * @param infoTemplateId
	 * @return
	 */
	@RequestMapping(value = "/findInfoTemplateById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findInfoTemplateById(Long infoTemplateId ) {
		
		Map<String, Object> rt = infoTemplateManage.findInfoTemplateById(infoTemplateId);
		return JsonResult.success(rt);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findInfoTemplateAll")
	@ResponseBody
	public JsonResult<List<InfoTemplateVO>> findInfoTemplateAll(InfoTemplateVO vo,HttpServletRequest req ) {
		InfoTemplateDTO dto = InfoTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<InfoTemplateDTO> rt = infoTemplateManage.findInfoTemplateAll(dto);	
		return JsonResult.success(InfoTemplateConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findInfoTemplateOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findInfoTemplateOfPage(InfoTemplateVO vo,Pagination page,HttpServletRequest req ) {
		InfoTemplateDTO dto = InfoTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = infoTemplateManage.findInfoTemplateOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertInfoTemplateWithTx")
	@ResponseBody
	public JsonResult<Long> insertInfoTemplateWithTx(InfoTemplateVO vo,HttpServletRequest req ) {
		InfoTemplateDTO dto = InfoTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = infoTemplateManage.insertInfoTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateInfoTemplateByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateInfoTemplateByIdWithTx(InfoTemplateVO vo,String sendWayIdList, HttpServletRequest req ) {
		InfoTemplateDTO dto = InfoTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Long> sendWayIds = null;
		if(StringUtils.isNotEmpty(sendWayIdList)){
			sendWayIds = JSONArray.parseArray(sendWayIdList, Long.class);
		}
		int rt = infoTemplateManage.updateInfoTemplateWithTx(dto,sendWayIds);	
		return JsonResult.success(rt);					 
	}
	
	//根据消息模版id启用停用消息模版
	@RequestMapping(value = "/isStartByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> isStartByIdWithTx(Long infoTemplateId) {
		int rt = infoTemplateManage.isStartByIdWithTx(infoTemplateId);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteInfoTemplateWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteInfoTemplateWithTx(InfoTemplateVO vo,HttpServletRequest req ) {
		InfoTemplateDTO dto = InfoTemplateConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = infoTemplateManage.deleteInfoTemplateWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	