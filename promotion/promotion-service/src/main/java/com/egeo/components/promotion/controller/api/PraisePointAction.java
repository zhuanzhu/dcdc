package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.PraisePointManage;
import com.egeo.components.promotion.converter.PraisePointConverter;
import com.egeo.components.promotion.dto.PraisePointDTO;
import com.egeo.components.promotion.vo.PraisePointVO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/praisePoint")
public class PraisePointAction extends BaseSpringController {
	
	@Resource(name="praisePoint")
	private PraisePointManage praisePointManage;








	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertPraisePointWithTx")
	@ResponseBody
	public JsonResult<Long> insertPraisePointWithTx(PraisePointVO vo,HttpServletRequest req ) {
		PraisePointDTO dto = PraisePointConverter.toDTO(vo);
		Long rt = praisePointManage.insertPraisePointWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updatePraisePointByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updatePraisePointByIdWithTx(PraisePointVO vo,HttpServletRequest req ) {
		PraisePointDTO dto = PraisePointConverter.toDTO(vo);
		int rt = praisePointManage.updatePraisePointWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deletePraisePointWithTx")
	@ResponseBody
	public JsonResult<Integer> deletePraisePointWithTx(PraisePointVO vo,HttpServletRequest req ) {
		PraisePointDTO dto = PraisePointConverter.toDTO(vo);
		int rt = praisePointManage.deletePraisePointWithTx(dto);	
		return success(rt);					 
	}
		
}
	