package com.egeo.components.user.controller.api;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.egeo.components.user.business.VersionsManage;
import com.egeo.components.user.converter.VersionsConverter;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.components.user.vo.VersionsVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/versions")
public class VersionsAction extends BaseSpringController {
	
	public Logger logger = LoggerFactory.getLogger(VersionsAction.class);
	
	@Resource(name="versions")
	private VersionsManage versionsManage;
	
	
	/**
	 * 客户端版本更新校验
	 * @param req
	 * @param user 使用方：1、c端 2、b端
	 * @return
	 */
	@RequestMapping(value = "/validate")
	@ResponseBody
	public JsonResult<Map<String,Object>> validate(Integer user,HttpServletRequest req){
		logger.info("客户端版本更新校验-1，请求参数：user={}", user);
		// 使用方为空赋默认值
		if(StringUtils.isEmpty(user))
			user = 1;
		//从请求头中取得参数
		String f=req.getHeader("f");//来源 0:安卓  1:ios
		if(StringUtils.isBlank(f))
			return JsonResult.fail("渠道参数缺失");
		String vCode=req.getHeader("vCode");//版本号 
		Integer vCode_=null;
		if(!StringUtils.isBlank(vCode)) {
			vCode_=Integer.parseInt(vCode);
		}
		Integer f_=Integer.parseInt(f);
		String str = req.getHeader("platformId");
		if(StringUtils.isEmpty(str)){
			return JsonResult.fail("platformId不能为空");
		}
		Long platformId = Long.valueOf(str);
		logger.info("客户端版本更新校验-2，请求参数：user={},vCode={},f={},platformId={}", new Object[]{user,vCode_,f_,platformId});
		JsonResult<Map<String,Object>> result = versionsManage.validate(vCode_,f_,user,platformId);
		logger.info("客户端版本更新校验，返回参数:{}", JSON.toJSONString(result));
		return result;
	}


	/**
	 * 根据版本id查询版本详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findVersionsById")
	@ResponseBody
	public JsonResult<VersionsVO> findVersionsById(Long id ) {
		
		VersionsDTO dto = new VersionsDTO();
		dto.setId(id);
		VersionsDTO rt = versionsManage.findVersionsById(dto);		
		return JsonResult.success(VersionsConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findVersionsAll")
	@ResponseBody
	public JsonResult<List<VersionsVO>> findVersionsAll(VersionsVO vo,HttpServletRequest req ) {
		VersionsDTO dto = VersionsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<VersionsDTO> rt = versionsManage.findVersionsAll(dto);	
		return JsonResult.success(VersionsConverter.toVO(rt));
	}	

	/**
	 * 分页显示版本列表接口
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findVersionsOfPage")
	@ResponseBody
	public JsonResult<PageResult<VersionsVO>> findVersionsOfPage(VersionsVO vo,Pagination page,HttpServletRequest req ) {
		VersionsDTO dto = VersionsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
	//	PageResult<VersionsDTO> rt = versionsManage.findVersionsOfPageByBlurry(dto, page);
		PageResult<VersionsDTO> rt = versionsManage.getVersionsOfPage(dto, page);
        List<VersionsVO> list = VersionsConverter.toVO(rt.getList());
        PageResult<VersionsVO> result = new PageResult<VersionsVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	/**
	 * 新增版本
	 * @param vo
	 * @param release
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertVersionsWithTx")
	@ResponseBody
	public JsonResult<Map<String, Object>> insertVersionsWithTx(VersionsVO vo, HttpServletRequest req ){
		VersionsDTO dto = VersionsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		return versionsManage.insertVersionsWithTx(dto);				 
	}
	
	/**
	 * 更新版本
	 * @param vo
	 * @param release
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/updateVersionsByIdWithTx")
	@ResponseBody
	public JsonResult<Map<String, Object>> updateVersionsByIdWithTx(VersionsVO vo, HttpServletRequest req ) {
		VersionsDTO dto = VersionsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}		
		return versionsManage.updateVersionsWithTx(dto);
	}
	
	/**
	 * 更新版本的部分信息
	 * @param vo
	 * @param updateTarget 更新目标: 0:版本状态  1:官网版本
	 * @return
	 */
	@RequestMapping(value = "/updateVersionStatus")
	@ResponseBody
	public JsonResult<Map<String, Object>> updateVersionStatus(VersionsVO vo, Integer updateTarget, HttpServletRequest req) {
		VersionsDTO dto = VersionsConverter.toDTO(vo);
		
		return versionsManage.updatePartialVersionsWithTx(dto,updateTarget);				 
	}
	
	// 业务方法：
	@RequestMapping(value = "/deleteVersionsWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteVersionsWithTx(VersionsVO vo,HttpServletRequest req ) {
		VersionsDTO dto = VersionsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = versionsManage.deleteVersionsWithTx(dto);	
		return JsonResult.success(rt);
	}
	
}
	