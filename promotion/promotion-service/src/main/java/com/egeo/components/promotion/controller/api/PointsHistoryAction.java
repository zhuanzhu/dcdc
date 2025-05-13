package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.PointsHistoryManage;
import com.egeo.components.promotion.converter.PointsHistoryConverter;
import com.egeo.components.promotion.dto.PointsHistoryDTO;
import com.egeo.components.promotion.vo.PointsHistoryVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/pointsHistory")
public class PointsHistoryAction extends BaseSpringController {
	
	@Resource(name="pointsHistory")
	private PointsHistoryManage pointsHistoryManage;


	// 业务方法：
	@RequestMapping(value = "/findPointsHistoryById")
	@ResponseBody
	public JsonResult<PointsHistoryVO> findPointsHistoryById(Long id ) {
		
		PointsHistoryDTO dto = new PointsHistoryDTO();
		dto.setId(id);
		PointsHistoryDTO rt = pointsHistoryManage.findPointsHistoryById(dto);		
		return success(PointsHistoryConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findPointsHistoryAll")
	@ResponseBody
	public JsonResult<List<PointsHistoryVO>> findPointsHistoryAll(PointsHistoryVO vo,HttpServletRequest req ) {
		PointsHistoryDTO dto = PointsHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<PointsHistoryDTO> rt = pointsHistoryManage.findPointsHistoryAll(dto);	
		return success(PointsHistoryConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findPointsHistoryOfPage")
	@ResponseBody
	public JsonResult<PageResult<PointsHistoryVO>> findPointsHistoryOfPage(PointsHistoryVO vo,Pagination page,HttpServletRequest req ) {
		PointsHistoryDTO dto = PointsHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<PointsHistoryDTO> rt = pointsHistoryManage.findPointsHistoryOfPage(dto, page);
        List<PointsHistoryVO> list = PointsHistoryConverter.toVO(rt.getList());
        PageResult<PointsHistoryVO> result = new PageResult<PointsHistoryVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertPointsHistoryWithTx")
	@ResponseBody
	public JsonResult<Long> insertPointsHistoryWithTx(PointsHistoryVO vo,HttpServletRequest req ) {
		PointsHistoryDTO dto = PointsHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = pointsHistoryManage.insertPointsHistoryWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updatePointsHistoryByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updatePointsHistoryByIdWithTx(PointsHistoryVO vo,HttpServletRequest req ) {
		PointsHistoryDTO dto = PointsHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = pointsHistoryManage.updatePointsHistoryWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deletePointsHistoryWithTx")
	@ResponseBody
	public JsonResult<Integer> deletePointsHistoryWithTx(PointsHistoryVO vo,HttpServletRequest req ) {
		PointsHistoryDTO dto = PointsHistoryConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = pointsHistoryManage.deletePointsHistoryWithTx(dto);	
		return success(rt);					 
	}
		
}
	