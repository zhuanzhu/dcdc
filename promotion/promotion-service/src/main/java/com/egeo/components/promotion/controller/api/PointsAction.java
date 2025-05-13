package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.PointsManage;
import com.egeo.components.promotion.converter.PointsConverter;
import com.egeo.components.promotion.dto.PointsDTO;
import com.egeo.components.promotion.vo.PointsVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/points")
public class PointsAction extends BaseSpringController {
	
	@Resource(name="points")
	private PointsManage pointsManage;


	// 业务方法：
	@RequestMapping(value = "/findPointsById")
	@ResponseBody
	public JsonResult<PointsVO> findPointsById(Long id ) {
		
		PointsDTO dto = new PointsDTO();
		dto.setId(id);
		PointsDTO rt = pointsManage.findPointsById(dto);		
		return success(PointsConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findPointsAll")
	@ResponseBody
	public JsonResult<List<PointsVO>> findPointsAll(PointsVO vo,HttpServletRequest req ) {
		PointsDTO dto = PointsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<PointsDTO> rt = pointsManage.findPointsAll(dto);	
		return success(PointsConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findPointsOfPage")
	@ResponseBody
	public JsonResult<PageResult<PointsVO>> findPointsOfPage(PointsVO vo,Pagination page,HttpServletRequest req ) {
		PointsDTO dto = PointsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<PointsDTO> rt = pointsManage.findPointsOfPage(dto, page);
        List<PointsVO> list = PointsConverter.toVO(rt.getList());
        PageResult<PointsVO> result = new PageResult<PointsVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertPointsWithTx")
	@ResponseBody
	public JsonResult<Long> insertPointsWithTx(PointsVO vo,HttpServletRequest req ) {
		PointsDTO dto = PointsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = pointsManage.insertPointsWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updatePointsByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updatePointsByIdWithTx(PointsVO vo,HttpServletRequest req ) {
		PointsDTO dto = PointsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = pointsManage.updatePointsWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deletePointsWithTx")
	@ResponseBody
	public JsonResult<Integer> deletePointsWithTx(PointsVO vo,HttpServletRequest req ) {
		PointsDTO dto = PointsConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = pointsManage.deletePointsWithTx(dto);	
		return success(rt);					 
	}
		
}
	