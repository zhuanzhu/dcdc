package com.egeo.components.config.controller.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.business.LogDictManage;
import com.egeo.components.config.converter.LogDictConverter;
import com.egeo.components.config.dto.LogDictDTO;
import com.egeo.components.config.vo.LogDictSimpleVO;
import com.egeo.components.config.vo.LogDictVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/config/logDict")
public class LogDictAction extends BaseSpringController {
	
	@Resource(name="logDict")
	private LogDictManage logDictManage;


	// 业务方法：
	@RequestMapping(value = "/findLogDictById")
	@ResponseBody
	public JsonResult<LogDictVO> findLogDictById(Long id ) {
		
		LogDictDTO dto = new LogDictDTO();
		dto.setId(id);
		LogDictDTO rt = logDictManage.findLogDictById(dto);		
		return success(LogDictConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findLogDictAll")
	@ResponseBody
	public JsonResult<List<LogDictVO>> findLogDictAll(LogDictVO vo,HttpServletRequest req ) {
		LogDictDTO dto = LogDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<LogDictDTO> rt = logDictManage.findLogDictAll(dto);	
		return success(LogDictConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findLogDictOfPage")
	@ResponseBody
	public JsonResult<PageResult<LogDictVO>> findLogDictOfPage(LogDictVO vo,Pagination page,HttpServletRequest req ) {
		LogDictDTO dto = LogDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<LogDictDTO> rt = logDictManage.findLogDictOfPage(dto, page);
        List<LogDictVO> list = LogDictConverter.toVO(rt.getList());
        PageResult<LogDictVO> result = new PageResult<LogDictVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertLogDictWithTx")
	@ResponseBody
	public JsonResult<Long> insertLogDictWithTx(LogDictVO vo,HttpServletRequest req ) {
		LogDictDTO dto = LogDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = logDictManage.insertLogDictWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateLogDictByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateLogDictByIdWithTx(LogDictVO vo,HttpServletRequest req ) {
		LogDictDTO dto = LogDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = logDictManage.updateLogDictWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteLogDictWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteLogDictWithTx(LogDictVO vo,HttpServletRequest req ) {
		LogDictDTO dto = LogDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = logDictManage.deleteLogDictWithTx(dto);	
		return success(rt);					 
	}
	
	/**
	 * 查询某条日志记录所在模块的所有操作
	 * @param logId
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findLogDictByLogId")
	@ResponseBody
	public JsonResult<List<LogDictSimpleVO>> findLogDictByLogId(Long logId,HttpServletRequest req) {
		List<LogDictDTO> rt = logDictManage.findLogDictByLogId(logId);	
		return success(LogDictConverter.toSimpleVO(rt));
	}
	
	// 业务方法：
	@RequestMapping(value = "/findAllLogDict")
	@ResponseBody
	public JsonResult<Map<String,Object>> findAllLogDict(Long moduleId, Long pageId, HttpServletRequest req ) {
		LogDictDTO moduleDTO = new LogDictDTO();
		LogDictDTO pageDTO = new LogDictDTO();
		LogDictDTO operDTO = new LogDictDTO();
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			moduleDTO.setPlatformId(platformId);
			pageDTO.setPlatformId(platformId);
			operDTO.setPlatformId(platformId);
		}
		
		moduleDTO.setArealevel(1);
		List<LogDictDTO> modules = logDictManage.findLogDictAll(moduleDTO);	
		
		pageDTO.setArealevel(2);
		pageDTO.setParentId(moduleId != null ? moduleId : null);
		List<LogDictDTO> pages = logDictManage.findLogDictAll(pageDTO);	
		
	
		List<LogDictDTO> opers = new ArrayList<LogDictDTO>();
		if (moduleId != null && pageId == null) {
			LogDictDTO module = new LogDictDTO();
			module.setParentId(moduleId);
			List<LogDictDTO> pageList = logDictManage.findLogDictAll(module);
			for (LogDictDTO logDictDTO_ : pageList) {
				operDTO.setParentId(logDictDTO_.getId());
				opers.addAll(logDictManage.findLogDictAll(operDTO));	
			}
		} else {
			operDTO.setArealevel(3);
			operDTO.setParentId(pageId != null ? pageId : null);
			opers = logDictManage.findLogDictAll(operDTO);	
		}
		
		Map<String,Object> rt = new HashMap<>();
		rt.put("modules", LogDictConverter.toSimpleVO(modules));
		rt.put("pages", LogDictConverter.toSimpleVO(pages));
		rt.put("opers", LogDictConverter.toSimpleVO(opers));
		return success(rt);
					 
	}	
	
	
	
		
}
	