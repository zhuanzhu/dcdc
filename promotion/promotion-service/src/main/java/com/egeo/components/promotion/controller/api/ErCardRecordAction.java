package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.ErCardRecordManage;
import com.egeo.components.promotion.converter.ErCardRecordConverter;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.vo.ErCardRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/erCardRecord")
public class ErCardRecordAction extends BaseSpringController {
	
	@Resource(name="erCardRecord")
	private ErCardRecordManage erCardRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findErCardRecordById")
	@ResponseBody
	public JsonResult<ErCardRecordVO> findErCardRecordById(Long id ) {
		
		ErCardRecordDTO dto = new ErCardRecordDTO();
		dto.setId(id);
		ErCardRecordDTO rt = erCardRecordManage.findErCardRecordById(dto);		
		return success(ErCardRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findErCardRecordAll")
	@ResponseBody
	public JsonResult<List<ErCardRecordVO>> findErCardRecordAll(ErCardRecordVO vo,HttpServletRequest req ) {
		ErCardRecordDTO dto = ErCardRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<ErCardRecordDTO> rt = erCardRecordManage.findErCardRecordAll(dto);	
		return success(ErCardRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findErCardRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<ErCardRecordVO>> findErCardRecordOfPage(ErCardRecordVO vo,Pagination page,HttpServletRequest req ) {
		ErCardRecordDTO dto = ErCardRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<ErCardRecordDTO> rt = erCardRecordManage.findErCardRecordOfPage(dto, page);
        List<ErCardRecordVO> list = ErCardRecordConverter.toVO(rt.getList());
        PageResult<ErCardRecordVO> result = new PageResult<ErCardRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertErCardRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertErCardRecordWithTx(ErCardRecordVO vo,HttpServletRequest req ) {
		ErCardRecordDTO dto = ErCardRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = erCardRecordManage.insertErCardRecordWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateErCardRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateErCardRecordByIdWithTx(ErCardRecordVO vo,HttpServletRequest req ) {
		ErCardRecordDTO dto = ErCardRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = erCardRecordManage.updateErCardRecordWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteErCardRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteErCardRecordWithTx(ErCardRecordVO vo,HttpServletRequest req ) {
		ErCardRecordDTO dto = ErCardRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = erCardRecordManage.deleteErCardRecordWithTx(dto);	
		return success(rt);					 
	}
	
	/**
	 * 确认导入
	 * @return
	 */
	@RequestMapping(value = "/confirmTheImport")
	@ResponseBody
	public JsonResult<Integer> confirmTheImport(ErCardRecordVO vo,HttpServletRequest req) {
		ErCardRecordDTO dto = ErCardRecordConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = erCardRecordManage.confirmTheImport(dto,null);	
		return success(rt);					 
	}
		
}
	