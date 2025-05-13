package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.PointsRuleManage;
import com.egeo.components.promotion.converter.PointsRuleConverter;
import com.egeo.components.promotion.dto.PointsRuleDTO;
import com.egeo.components.promotion.vo.PointsRuleVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/pointsRule")
public class PointsRuleAction extends BaseSpringController {
	
	@Resource(name="pointsRule")
	private PointsRuleManage pointsRuleManage;


	// 业务方法：
	@RequestMapping(value = "/findPointsRuleById")
	@ResponseBody
	public JsonResult<PointsRuleVO> findPointsRuleById(Long id ) {
		
		PointsRuleDTO dto = new PointsRuleDTO();
		dto.setId(id);
		PointsRuleDTO rt = pointsRuleManage.findPointsRuleById(dto);		
		return success(PointsRuleConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findPointsRuleAll")
	@ResponseBody
	public JsonResult<List<PointsRuleVO>> findPointsRuleAll(PointsRuleVO vo,HttpServletRequest req ) {
		PointsRuleDTO dto = PointsRuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		List<PointsRuleDTO> rt = pointsRuleManage.findPointsRuleAll(dto);	
		return success(PointsRuleConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findPointsRuleOfPage")
	@ResponseBody
	public JsonResult<PageResult<PointsRuleVO>> findPointsRuleOfPage(PointsRuleVO vo,Pagination page,HttpServletRequest req ) {
		PointsRuleDTO dto = PointsRuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		PageResult<PointsRuleDTO> rt = pointsRuleManage.findPointsRuleOfPage(dto, page);
        List<PointsRuleVO> list = PointsRuleConverter.toVO(rt.getList());
        PageResult<PointsRuleVO> result = new PageResult<PointsRuleVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertPointsRuleWithTx")
	@ResponseBody
	public JsonResult<Long> insertPointsRuleWithTx(PointsRuleVO vo,HttpServletRequest req ) {
		PointsRuleDTO dto = PointsRuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		Long rt = pointsRuleManage.insertPointsRuleWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updatePointsRuleByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updatePointsRuleByIdWithTx(PointsRuleVO vo,HttpServletRequest req ) {
		PointsRuleDTO dto = PointsRuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = pointsRuleManage.updatePointsRuleWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deletePointsRuleWithTx")
	@ResponseBody
	public JsonResult<Integer> deletePointsRuleWithTx(PointsRuleVO vo,HttpServletRequest req ) {
		PointsRuleDTO dto = PointsRuleConverter.toDTO(vo);
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		int rt = pointsRuleManage.deletePointsRuleWithTx(dto);	
		return success(rt);					 
	}
		
}
	