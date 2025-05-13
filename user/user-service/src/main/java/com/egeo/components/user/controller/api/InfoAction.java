package com.egeo.components.user.controller.api;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.user.vo.InfoVO;
import com.egeo.components.user.business.InfoManage;
import com.egeo.components.user.converter.InfoConverter;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.orm.PageResult;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.excel2.ExcelUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/user/info")
public class InfoAction extends BaseSpringController {
	
	@Resource(name="info")
	private InfoManage infoManage;


	// 业务方法：
	@RequestMapping(value = "/findInfoById")
	@ResponseBody
	public JsonResult<InfoVO> findInfoById(Long id ) {
		
		InfoDTO dto = new InfoDTO();
		dto.setId(id);
		InfoDTO rt = infoManage.findInfoById(dto);		
		return JsonResult.success(InfoConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findInfoAll")
	@ResponseBody
	public JsonResult<List<InfoVO>> findInfoAll(InfoVO vo,HttpServletRequest req ) {
		InfoDTO dto = InfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<InfoDTO> rt = infoManage.findInfoAll(dto);	
		return JsonResult.success(InfoConverter.toVO(rt));
					 
	}	

	/**
	 * 分页显示消息列表
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findInfoOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findInfoOfPage(InfoVO vo,Pagination page,HttpServletRequest req ) {
		InfoDTO dto = InfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<Map<String, Object>> rt = infoManage.findInfoOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}


	/**
	 * 添加消息
	 * @param vo
	 * @param sendWayIdList
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertInfoWithTx")
	@ResponseBody
	public JsonResult<Long> insertInfoWithTx(InfoVO vo,String sendWayIdList,Integer isUserAll,String userAll, String serialNum,HttpServletRequest req ) {
		InfoDTO dto = InfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<Long> sendWayIds = null;
		if(com.egeo.utils.StringUtils.isNotEmpty(sendWayIdList))
			sendWayIds = JSONArray.parseArray(sendWayIdList, Long.class);
		if(com.egeo.utils.StringUtils.isEmpty(vo.getIsAdmin()))
			throw new BusinessException("是否是管理员不能为空");
		List<Long> userIds = null;
		if(com.egeo.utils.StringUtils.isNotEmpty(userAll))
			userIds = JSONArray.parseArray(userAll, Long.class);
		CacheUser user = getCacheUser();
		
		Long rt = infoManage.insertInfoWithTx(dto,sendWayIds,isUserAll,userIds, user.getId(), serialNum);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateInfoByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateInfoByIdWithTx(InfoVO vo,HttpServletRequest req ) {
		InfoDTO dto = InfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = infoManage.updateInfoWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	/**
	 * 根据当前用户id分页显示当前不同消息列表
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findUserInfoOfPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> findUserInfoOfPage(InfoVO vo,Pagination page,HttpServletRequest req ) {
		InfoDTO dto = InfoConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(com.egeo.utils.StringUtils.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		CacheUser cacheUser = this.getCacheUser();
		dto.setUserId(cacheUser.getId());
		Map<String, Object> rt = infoManage.findUserInfoOfPage(dto, page);
		return JsonResult.success(rt);
					 
	}
		
	@RequestMapping(value = "/parseSendUser")
	@ResponseBody
	public JsonResult<Map<String,Object>> parseSendUser(HttpServletRequest req) {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
    	Iterator<String> iter = multiRequest.getFileNames();
    	if(!iter.hasNext()) {
    		return JsonResult.fail("请上传文件");
    	}
    	MultipartFile file = multiRequest.getFile(iter.next());
    	if (file == null)
    		return JsonResult.fail("未发现Excel文件");
    	
    	String originalFilename = file.getOriginalFilename();
    	// 获取文件后缀
    	String suffix = "";
    	
    	suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
    	
    	if(! StringUtils.equals(suffix, ".xls") && ! StringUtils.equals(suffix, ".xlsx")){
    		return JsonResult.fail("导入的文件类型错误，请选择后重新选择文件导入");
    	};
    	
    	List<Map<String, Object>> valueList = null;
    	try {
    		valueList = ExcelUtil.readExcelData(0, 0, file.getInputStream());
    	} catch (IOException e) {
    		e.printStackTrace();
    		return JsonResult.fail("Excel文件读取发生异常");
    	}
    	CacheUser user = getCacheUser();
		return infoManage.parseSendUser(valueList, user.getId());					 
	}
	
	@RequestMapping(value = "/clearImportInfoCache")
	@ResponseBody
	public JsonResult<String> clearImportInfoCache(HttpServletRequest req) {
    	CacheUser user = getCacheUser();
		infoManage.clearImportInfoCache(user.getId());			
		return JsonResult.success("清除缓存成功");
	}
	
}
	