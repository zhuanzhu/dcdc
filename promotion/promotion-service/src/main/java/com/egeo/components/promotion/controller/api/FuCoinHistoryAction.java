package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.FuCoinHistoryManage;
import com.egeo.components.promotion.converter.FuCoinHistoryConverter;
import com.egeo.components.promotion.dto.FuCoinHistoryDTO;
import com.egeo.components.promotion.vo.FuCoinHistoryVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/fuCoinHistory")
public class FuCoinHistoryAction extends BaseSpringController {
	
	@Resource(name="fuCoinHistory")
	private FuCoinHistoryManage fuCoinHistoryManage;


	// 业务方法：
	@RequestMapping(value = "/findFuCoinHistoryById")
	@ResponseBody
	public JsonResult<FuCoinHistoryVO> findFuCoinHistoryById(Long id ) {
		
		FuCoinHistoryDTO dto = new FuCoinHistoryDTO();
		dto.setId(id);
		FuCoinHistoryDTO rt = fuCoinHistoryManage.findFuCoinHistoryById(dto);		
		return success(FuCoinHistoryConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findFuCoinHistoryAll")
	@ResponseBody
	public JsonResult<List<FuCoinHistoryVO>> findFuCoinHistoryAll(FuCoinHistoryVO vo,HttpServletRequest req ) {
		FuCoinHistoryDTO dto = FuCoinHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<FuCoinHistoryDTO> rt = fuCoinHistoryManage.findFuCoinHistoryAll(dto);	
		return success(FuCoinHistoryConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findFuCoinHistoryOfPage")
	@ResponseBody
	public JsonResult<PageResult<FuCoinHistoryVO>> findFuCoinHistoryOfPage(FuCoinHistoryVO vo,Pagination page,HttpServletRequest req ) {
		FuCoinHistoryDTO dto = FuCoinHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<FuCoinHistoryDTO> rt = fuCoinHistoryManage.findFuCoinHistoryOfPage(dto, page);
        List<FuCoinHistoryVO> list = FuCoinHistoryConverter.toVO(rt.getList());
        PageResult<FuCoinHistoryVO> result = new PageResult<FuCoinHistoryVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertFuCoinHistoryWithTx")
	@ResponseBody
	public JsonResult<Long> insertFuCoinHistoryWithTx(FuCoinHistoryVO vo,HttpServletRequest req ) {
		FuCoinHistoryDTO dto = FuCoinHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = fuCoinHistoryManage.insertFuCoinHistoryWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateFuCoinHistoryByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFuCoinHistoryByIdWithTx(FuCoinHistoryVO vo,HttpServletRequest req ) {
		FuCoinHistoryDTO dto = FuCoinHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = fuCoinHistoryManage.updateFuCoinHistoryWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteFuCoinHistoryWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteFuCoinHistoryWithTx(FuCoinHistoryVO vo,HttpServletRequest req ) {
		FuCoinHistoryDTO dto = FuCoinHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = fuCoinHistoryManage.deleteFuCoinHistoryWithTx(dto);	
		return success(rt);					 
	}
		
}
	