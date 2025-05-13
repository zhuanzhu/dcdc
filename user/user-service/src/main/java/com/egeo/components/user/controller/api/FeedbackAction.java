package com.egeo.components.user.controller.api;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.vo.FeedbackVO;
import com.egeo.config.RuntimeContext;
import com.egeo.components.user.business.FeedbackManage;
import com.egeo.components.user.business.UserExtendManage;
import com.egeo.components.user.converter.FeedbackConverter;
import com.egeo.components.user.dto.FeedbackDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.orm.PageResult;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.HostUtils;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/feedback")
public class FeedbackAction extends BaseSpringController {
	
	@Resource(name="feedback")
	private FeedbackManage feedbackManage;

	@Resource(name = "userExtend")
	private UserExtendManage userExtendManage;

	// 业务方法：
	@RequestMapping(value = "/findFeedbackById")
	@ResponseBody
	public JsonResult<FeedbackVO> findFeedbackById(Long id ) {
		
		FeedbackDTO dto = new FeedbackDTO();
		dto.setId(id);
		FeedbackDTO rt = feedbackManage.findFeedbackById(dto);		
		return JsonResult.success(FeedbackConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findFeedbackAll")
	@ResponseBody
	public JsonResult<List<FeedbackVO>> findFeedbackAll(FeedbackVO vo,HttpServletRequest req ) {
		FeedbackDTO dto = FeedbackConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<FeedbackDTO> rt = feedbackManage.findFeedbackAll(dto);	
		return JsonResult.success(FeedbackConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findFeedbackOfPage")
	@ResponseBody
	public JsonResult<PageResult<FeedbackVO>> findFeedbackOfPage(FeedbackVO vo,Pagination page,HttpServletRequest req ) {
		FeedbackDTO dto = FeedbackConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<FeedbackDTO> rt = feedbackManage.findFeedbackOfPage(dto, page);
        List<FeedbackVO> list = FeedbackConverter.toVO(rt.getList());
        PageResult<FeedbackVO> result = new PageResult<FeedbackVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	/**
	 * 意见反馈添加
	 * @param vo
	 * @param v
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertFeedbackWithTx")
	@ResponseBody
	public JsonResult<String> insertFeedbackWithTx(FeedbackVO vo,String v,HttpServletRequest req ) {
		logger.info("意见反馈添加");
		FeedbackDTO dto = FeedbackConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser userCache = this.getCacheUser();
		//获得客户端的ip地址 
		String ip = req.getRemoteAddr();
		//根据ip获取mac地址
		String mac;
		try {
			mac = HostUtils.getLocalMac(ip);
		} catch (Exception e) {
			throw new BusinessException("获取mac地址异常" + e.getMessage());
			
		}
		Long userId = userCache.getId();
		UserExtendDTO userData = userExtendManage.findById(userId);
		String userName = userData.getName();
		dto.setCreateUserid(userId);
		dto.setCreateUsername(userName);
		dto.setCreateUserip(ip);
		dto.setCreateUsermac(mac);
		dto.setClientVersionno(v);
		Long rt = feedbackManage.insertFeedbackWithTx(dto);	
		if(StringUtils.isNotEmpty(rt)){
			return JsonResult.success("意见反馈添加成功");	
		}else{
			return JsonResult.success("意见反馈添加失败");
		}
						 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateFeedbackByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFeedbackByIdWithTx(FeedbackVO vo,HttpServletRequest req ) {
		FeedbackDTO dto = FeedbackConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser user = RuntimeContext.cacheUser();
		dto.setUpdateUsername(user.getLoginName());
		dto.setUpdateUserid(user.getId());
		dto.setUpdateTime(new Date());
		int rt = feedbackManage.updateFeedbackWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteFeedbackWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteFeedbackWithTx(FeedbackVO vo,HttpServletRequest req ) {
		FeedbackDTO dto = FeedbackConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = feedbackManage.deleteFeedbackWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	