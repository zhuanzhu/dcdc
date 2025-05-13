package com.egeo.components.config.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.business.LogManage;
import com.egeo.components.config.converter.LogConverter;
import com.egeo.components.config.converter.LogInfoConverter;
import com.egeo.components.config.dto.LogDTO;
import com.egeo.components.config.dto.LogInfoDTO;
import com.egeo.components.config.vo.LogInfoVO;
import com.egeo.components.config.vo.LogVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/config/log")
public class LogAction extends BaseSpringController {
	
	@Resource(name="log")
	private LogManage logManage;

	@Autowired
	private JedisUtil redis;
	// 业务方法：
	@RequestMapping(value = "/findLogById")
	@ResponseBody
	public JsonResult<LogVO> findLogById(Long id ) {
		
		LogDTO dto = new LogDTO();
		dto.setId(id);
		LogDTO rt = logManage.findLogById(dto);		
		return success(LogConverter.toVO(rt));
					 
	}

	// 业务方法：
		@RequestMapping(value = "/test",method=RequestMethod.GET)
		@ResponseBody
		public JsonResult<String> test(Long id ) {
			return success(redis.getString("123"));
						 
		}

	// 业务方法：
	@RequestMapping(value = "/findLogAll")
	@ResponseBody
	public JsonResult<List<LogVO>> findLogAll(LogVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		LogDTO dto = LogConverter.toDTO(vo);
		List<LogDTO> rt = logManage.findLogAll(dto);	
		return success(LogConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findLogOfPage")
	@ResponseBody
	public JsonResult<PageResult<LogVO>> findLogOfPage(LogVO vo,Pagination page,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		LogDTO dto = LogConverter.toDTO(vo);
		PageResult<LogDTO> rt = logManage.findLogOfPage(dto, page);
        List<LogVO> list = LogConverter.toVO(rt.getList());
        PageResult<LogVO> result = new PageResult<LogVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertLogWithTx")
	@ResponseBody
	public JsonResult<Long> insertLogWithTx(LogVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		LogDTO dto = LogConverter.toDTO(vo);
		Long rt = logManage.insertLogWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateLogByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateLogByIdWithTx(LogVO vo,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		LogDTO dto = LogConverter.toDTO(vo);
		int rt = logManage.updateLogWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteLogWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteLogWithTx(LogVO vo,HttpServletRequest req ) {
		LogDTO dto = LogConverter.toDTO(vo);
		int rt = logManage.deleteLogWithTx(dto);	
		return success(rt);					 
	}
		
	/**
	 * 查询日志列表
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findLogInfoOfPage")
	@ResponseBody
	public JsonResult<PageResult<LogInfoVO>> findLogInfoOfPage(LogInfoVO vo,Pagination page,HttpServletRequest req ) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		PageResult<LogInfoDTO> rt = logManage.findLogInfoOfPage(LogInfoConverter.toDTO(vo), page);
		List<LogInfoDTO> list = rt.getList();
		PageResult<LogInfoVO> result = new PageResult<LogInfoVO>();
		result.setList(LogInfoConverter.toVO(list));
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}
}
	