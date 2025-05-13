package com.egeo.components.user.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.vo.ChannelActivityVO;
import com.egeo.components.user.business.ChannelActivityManage;
import com.egeo.components.user.converter.ChannelActivityConverter;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/channelActivity")
public class ChannelActivityAction extends BaseSpringController {
	
	@Resource(name="channelActivity")
	private ChannelActivityManage channelActivityManage;


	// 业务方法：
	@RequestMapping(value = "/findChannelActivityById")
	@ResponseBody
	public JsonResult<Map<String, Object>> findChannelActivityById(Long channelActivityId ) {
		
		Map<String, Object> rt = channelActivityManage.findChannelActivityById(channelActivityId);		
		return JsonResult.success(rt);
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findChannelActivityAll")
	@ResponseBody
	public JsonResult<List<ChannelActivityVO>> findChannelActivityAll(ChannelActivityVO vo,HttpServletRequest req ) {
		ChannelActivityDTO dto = ChannelActivityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<ChannelActivityDTO> rt = channelActivityManage.findChannelActivityAll(dto);	
		return JsonResult.success(ChannelActivityConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findChannelActivityOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findChannelActivityOfPage(ChannelActivityVO vo,Pagination page,HttpServletRequest req ) {
		ChannelActivityDTO dto = ChannelActivityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = channelActivityManage.findChannelActivityOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertChannelActivityWithTx")
	@ResponseBody
	public JsonResult<Long> insertChannelActivityWithTx(ChannelActivityVO vo,HttpServletRequest req ) {
		ChannelActivityDTO dto = ChannelActivityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = channelActivityManage.insertChannelActivityWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateChannelActivityByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateChannelActivityByIdWithTx(ChannelActivityVO vo,HttpServletRequest req ) {
		ChannelActivityDTO dto = ChannelActivityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = channelActivityManage.updateChannelActivityWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteChannelActivityWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteChannelActivityWithTx(ChannelActivityVO vo,HttpServletRequest req ) {
		ChannelActivityDTO dto = ChannelActivityConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = channelActivityManage.deleteChannelActivityWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	/**
	 * 根据渠道id查询活动列表
	 * @param channelId
	 * @param req
	 * @return
	 */
	@RequestMapping("/findChannelActivityByChannelId")
	@ResponseBody
	public JsonResult<List<ChannelActivityVO>> findChannelActivityByChannelId(Long channelId,HttpServletRequest req){
		logger.info("[根据渠道id查询活动列表]参数channelId:"+channelId);
		String str = req.getHeader("platformId");
		if(StringUtils.isEmpty(str)){
			return JsonResult.fail("platform不能为空");
		}
		if(StringUtils.isEmpty(channelId)){
			return JsonResult.fail("channelId缺失");
		}
		Long platformId = Long.valueOf(str);
		List<ChannelActivityVO> result=channelActivityManage.findChannelActivityByChannelId(channelId,platformId);
		return JsonResult.success(result);
	}
		
}
	