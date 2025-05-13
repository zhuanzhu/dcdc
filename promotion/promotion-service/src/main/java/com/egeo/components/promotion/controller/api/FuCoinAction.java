package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.FuCoinManage;
import com.egeo.components.promotion.converter.FuCoinConverter;
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.components.promotion.vo.FuCoinVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/fuCoin")
public class FuCoinAction extends BaseSpringController {
	
	@Resource(name="fuCoin")
	private FuCoinManage fuCoinManage;


	// 业务方法：
	@RequestMapping(value = "/findFuCoinById")
	@ResponseBody
	public JsonResult<FuCoinVO> findFuCoinById(Long id ) {
		
		FuCoinDTO dto = new FuCoinDTO();
		dto.setId(id);
		FuCoinDTO rt = fuCoinManage.findFuCoinById(dto);		
		return success(FuCoinConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findFuCoinAll")
	@ResponseBody
	public JsonResult<List<FuCoinVO>> findFuCoinAll(FuCoinVO vo,HttpServletRequest req ) {
		FuCoinDTO dto = FuCoinConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<FuCoinDTO> rt = fuCoinManage.findFuCoinAll(dto);	
		return success(FuCoinConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findFuCoinOfPage")
	@ResponseBody
	public JsonResult<PageResult<FuCoinVO>> findFuCoinOfPage(FuCoinVO vo,Pagination page,HttpServletRequest req ) {
		FuCoinDTO dto = FuCoinConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<FuCoinDTO> rt = fuCoinManage.findFuCoinOfPage(dto, page);
        List<FuCoinVO> list = FuCoinConverter.toVO(rt.getList());
        PageResult<FuCoinVO> result = new PageResult<FuCoinVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertFuCoinWithTx")
	@ResponseBody
	public JsonResult<Long> insertFuCoinWithTx(FuCoinVO vo,HttpServletRequest req ) {
		FuCoinDTO dto = FuCoinConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = fuCoinManage.insertFuCoinWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateFuCoinByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateFuCoinByIdWithTx(FuCoinVO vo,HttpServletRequest req ) {
		FuCoinDTO dto = FuCoinConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = fuCoinManage.updateFuCoinWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteFuCoinWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteFuCoinWithTx(FuCoinVO vo,HttpServletRequest req ) {
		FuCoinDTO dto = FuCoinConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = fuCoinManage.deleteFuCoinWithTx(dto);	
		return success(rt);					 
	}
		
}
	