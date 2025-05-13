package com.egeo.components.user.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.business.ChannelManage;
import com.egeo.components.user.converter.ChannelConverter;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.vo.ChannelVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.StringUtils;



@Controller
@RequestMapping("/api/user/channel")
public class ChannelAction extends BaseSpringController {
	
	@Resource(name="channel")
	private ChannelManage channelManage;


	// 业务方法：
	@RequestMapping(value = "/findChannelById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findChannelById(Long id ) {
		
		ChannelDTO dto = new ChannelDTO();
		dto.setId(id);
		Map<String, Object> rt = channelManage.findChannelById(dto);		
		return JsonResult.success(rt);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findChannelAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findChannelAll(ChannelVO vo,HttpServletRequest req ) {
		ChannelDTO dto = ChannelConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Map<String, Object>> rt = channelManage.findChannelAll(dto);	
		return JsonResult.success(rt);
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findChannelOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findChannelOfPage(ChannelVO vo,Pagination page,HttpServletRequest req ) {
		ChannelDTO dto = ChannelConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = channelManage.findChannelOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertChannelWithTx")
	@ResponseBody
	public JsonResult<Long> insertChannelWithTx(ChannelVO vo,HttpServletRequest req ) {
		ChannelDTO dto = ChannelConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = channelManage.insertChannelWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateChannelByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateChannelByIdWithTx(ChannelVO vo,HttpServletRequest req ) {
		ChannelDTO dto = ChannelConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = channelManage.updateChannelWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteChannelWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteChannelWithTx(ChannelVO vo,HttpServletRequest req ) {
		ChannelDTO dto = ChannelConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = channelManage.deleteChannelWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据版本类型：1、安卓 2、ios查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findChannelByType")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findChannelByType(int type) {
		logger.info("根据版本类型：1、安卓 2、ios查询渠道信息");
		List<Map<String, Object>> rt = channelManage.findChannelByType(type);	
		return JsonResult.success(rt);
					 
	}	
	
	/**
	 * 根据版本id查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findChannelByVersionsId")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findChannelByVersionsId(Long versionsId) {
		logger.info("根据版本id查询渠道信息,版本id：" + versionsId);
		List<Map<String, Object>> rt = channelManage.findChannelByVersionsId(versionsId);	
		return JsonResult.success(rt);
					 
	}

	/**
	 * 根据platformId查询渠道列表(不使用)
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findChannelByPlatformId")
	@ResponseBody
	public JsonResult<List<ChannelVO>> findChannelByPlatformId(HttpServletRequest req) {
		logger.info("[根据platformId查询渠道列表]");
		String str = req.getHeader("platformId");
		if(StringUtils.isEmpty(str)){
			return JsonResult.fail("platform不能为空");
		}
		Long platformId = Long.valueOf(str);
		List<ChannelVO> result=channelManage.findChannelByPlatformId(platformId);
		return JsonResult.success(result);

	}


}
	