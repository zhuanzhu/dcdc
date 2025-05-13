package com.egeo.components.product.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MpSerachKeywordManage;
import com.egeo.components.product.converter.MpSerachKeywordConverter;
import com.egeo.components.product.vo.MpSerachKeywordVO;
import com.egeo.components.product.dto.MpSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;
import com.egeo.utils.EmptyUtil;



@Controller
@RequestMapping("/api/product/mpSerachKeyword")
public class MpSerachKeywordAction extends BaseSpringController {
	
	@Resource(name="mpSerachKeyword")
	private MpSerachKeywordManage mpSerachKeywordManage;


	// 业务方法：
	@RequestMapping(value = "/findMpSerachKeywordById")
	@ResponseBody
	public JsonResult<MpSerachKeywordVO> findMpSerachKeywordById(Long id ) {
		
		MpSerachKeywordDTO dto = new MpSerachKeywordDTO();
		dto.setId(id);
		MpSerachKeywordDTO rt = mpSerachKeywordManage.findMpSerachKeywordById(dto);		
		return JsonResult.success(MpSerachKeywordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findMpSerachKeywordAll")
	@ResponseBody
	public JsonResult<List<MpSerachKeywordVO>> findMpSerachKeywordAll(MpSerachKeywordVO vo,HttpServletRequest req ) {
		MpSerachKeywordDTO dto = MpSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<MpSerachKeywordDTO> rt = mpSerachKeywordManage.findMpSerachKeywordAll(dto);	
		return JsonResult.success(MpSerachKeywordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findMpSerachKeywordOfPage")
	@ResponseBody
	public JsonResult<PageResult<MpSerachKeywordVO>> findMpSerachKeywordOfPage(MpSerachKeywordVO vo,Pagination page,HttpServletRequest req ) {
		MpSerachKeywordDTO dto = MpSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<MpSerachKeywordDTO> rt = mpSerachKeywordManage.findMpSerachKeywordOfPage(dto, page);
        List<MpSerachKeywordVO> list = MpSerachKeywordConverter.toVO(rt.getList());
        PageResult<MpSerachKeywordVO> result = new PageResult<MpSerachKeywordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return JsonResult.success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertMpSerachKeywordWithTx")
	@ResponseBody
	public JsonResult<Long> insertMpSerachKeywordWithTx(MpSerachKeywordVO vo,HttpServletRequest req ) {
		MpSerachKeywordDTO dto = MpSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = mpSerachKeywordManage.insertMpSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateMpSerachKeywordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateMpSerachKeywordByIdWithTx(MpSerachKeywordVO vo,HttpServletRequest req ) {
		MpSerachKeywordDTO dto = MpSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = mpSerachKeywordManage.updateMpSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteMpSerachKeywordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteMpSerachKeywordWithTx(MpSerachKeywordVO vo,HttpServletRequest req ) {
		MpSerachKeywordDTO dto = MpSerachKeywordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = mpSerachKeywordManage.deleteMpSerachKeywordWithTx(dto);	
		return JsonResult.success(rt);					 
	}
		
}
	