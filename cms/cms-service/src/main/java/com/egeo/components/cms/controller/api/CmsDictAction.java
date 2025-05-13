package com.egeo.components.cms.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.CmsDictManage;
import com.egeo.components.cms.converter.CmsDictConverter;
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.components.cms.vo.CmsDictVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/cmsDict")
public class CmsDictAction extends BaseSpringController {
	
	@Resource(name="cmsDict")
	private CmsDictManage cmsDictManage;

	/**
	 * 通过parentId查询所有字典信息
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "/findCmsDictByParentId")
	@ResponseBody
	public JsonResult<List<CmsDictVO>> findCmsDictParentById(Long parentId) {
		
		CmsDictDTO dto = new CmsDictDTO();
		dto.setParentId(parentId);
		List<CmsDictDTO> rt = cmsDictManage.findCmsDictAll(dto);		
		return JsonResult.success(CmsDictConverter.toVO(rt));
					 
	}
	
	// 业务方法：
	@RequestMapping(value = "/findCmsDictById")
	@ResponseBody
	public JsonResult<CmsDictVO> findCmsDictById(Long id ) {
		
		CmsDictDTO dto = new CmsDictDTO();
		dto.setId(id);
		CmsDictDTO rt = cmsDictManage.findCmsDictById(dto);		
		return JsonResult.success(CmsDictConverter.toVO(rt));
		
	}



	// 业务方法：
	@RequestMapping(value = "/findCmsDictAll")
	@ResponseBody
	public JsonResult<List<CmsDictVO>> findCmsDictAll(CmsDictVO vo,HttpServletRequest req ) {
		CmsDictDTO dto = CmsDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<CmsDictDTO> rt = cmsDictManage.findCmsDictAll(dto);	
		return JsonResult.success(CmsDictConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findCmsDictOfPage")
	@ResponseBody
	public JsonResult<PageResult<CmsDictVO>> findCmsDictOfPage(CmsDictVO vo,Pagination page,HttpServletRequest req ) {
		CmsDictDTO dto = CmsDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<CmsDictDTO> rt = cmsDictManage.findCmsDictOfPage(dto, page);
        List<CmsDictVO> list = CmsDictConverter.toVO(rt.getList());
        PageResult<CmsDictVO> result = new PageResult<CmsDictVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCmsDictWithTx")
	@ResponseBody
	public JsonResult<Long> insertCmsDictWithTx(CmsDictVO vo,HttpServletRequest req ) {
		CmsDictDTO dto = CmsDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = cmsDictManage.insertCmsDictWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCmsDictByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCmsDictByIdWithTx(CmsDictVO vo,HttpServletRequest req ) {
		CmsDictDTO dto = CmsDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cmsDictManage.updateCmsDictWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteCmsDictWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCmsDictWithTx(CmsDictVO vo,HttpServletRequest req ) {
		CmsDictDTO dto = CmsDictConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = cmsDictManage.deleteCmsDictWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	