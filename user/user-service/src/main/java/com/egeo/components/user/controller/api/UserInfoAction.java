package com.egeo.components.user.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.business.UserInfoManage;
import com.egeo.components.user.converter.UserInfoConverter;
import com.egeo.components.user.dto.UserInfoDTO;
import com.egeo.components.user.vo.UserInfoVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/userInfo")
public class UserInfoAction extends BaseSpringController {
	
	@Resource(name="userInfo")
	private UserInfoManage userInfoManage;


	// 业务方法：
	@RequestMapping(value = "/findUserInfoById")
	@ResponseBody
	public JsonResult<UserInfoVO> findUserInfoById(Long id ) {
		
		UserInfoDTO dto = new UserInfoDTO();
		dto.setId(id);
		UserInfoDTO rt = userInfoManage.findUserInfoById(dto);		
		return JsonResult.success(UserInfoConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findUserInfoAll")
	@ResponseBody
	public JsonResult<List<UserInfoVO>> findUserInfoAll(UserInfoVO vo,HttpServletRequest req ) {
		UserInfoDTO dto = UserInfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<UserInfoDTO> rt = userInfoManage.findUserInfoAll(dto);	
		return JsonResult.success(UserInfoConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findUserInfoOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findUserInfoOfPage(UserInfoVO vo,Pagination page,HttpServletRequest req ) {
		UserInfoDTO dto = UserInfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = userInfoManage.findUserInfoOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertUserInfoWithTx")
	@ResponseBody
	public JsonResult<Long> insertUserInfoWithTx(UserInfoVO vo,HttpServletRequest req ) {
		UserInfoDTO dto = UserInfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = userInfoManage.insertUserInfoWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateUserInfoByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateUserInfoByIdWithTx(UserInfoVO vo,HttpServletRequest req ) {
		UserInfoDTO dto = UserInfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = userInfoManage.updateUserInfoWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	/**
	 * 根据当前用户id查询当前用户消息数量
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findUserInfoSumByUserId")
	@ResponseBody
	public JsonResult<Integer> findUserInfoSumByUserId(HttpServletRequest req ) {
		String str = req.getHeader("platformId");	
		Long platformId = null;
		if(StringUtils.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}
		CacheUser cacheUser = this.getCacheUser();
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setUserId(cacheUser.getId());
		userInfoDTO.setPlatformId(platformId);
		int rt = userInfoManage.findUserInfoSumByUserId(userInfoDTO);
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据用户消息id更新用户消息是否已读状态
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateIsReadByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateIsReadByIdWithTx(Long userInfoId ) {
		int rt = userInfoManage.updateIsReadByIdWithTx(userInfoId);
		return JsonResult.success(rt);					 
	}
	
	/**
	 * 根据id删除用户消息
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/delUserInfoByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> delUserInfoByIdWithTx(UserInfoVO vo,HttpServletRequest req ) {
		UserInfoDTO dto = UserInfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = userInfoManage.deleteUserInfoWithTx(dto);
		return JsonResult.success(rt);					 
	}
		
}
	