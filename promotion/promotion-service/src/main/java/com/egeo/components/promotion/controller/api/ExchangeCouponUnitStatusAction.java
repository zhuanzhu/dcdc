package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.ExchangeCouponUnitStatusManage;
import com.egeo.components.promotion.converter.ExchangeCouponUnitStatusConverter;
import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.components.promotion.vo.ExchangeCouponUnitStatusVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/exchangeCouponUnitStatus")
public class ExchangeCouponUnitStatusAction extends BaseSpringController {
	
	@Resource(name="exchangeCouponUnitStatus")
	private ExchangeCouponUnitStatusManage exchangeCouponUnitStatusManage;


	// 业务方法：
	@RequestMapping(value = "/findExchangeCouponUnitStatusById")
	@ResponseBody
	public JsonResult<ExchangeCouponUnitStatusVO> findExchangeCouponUnitStatusById(Long id ) {
		
		ExchangeCouponUnitStatusDTO dto = new ExchangeCouponUnitStatusDTO();
		dto.setId(id);
		ExchangeCouponUnitStatusDTO rt = exchangeCouponUnitStatusManage.findExchangeCouponUnitStatusById(dto);		
		return success(ExchangeCouponUnitStatusConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findExchangeCouponUnitStatusAll")
	@ResponseBody
	public JsonResult<List<ExchangeCouponUnitStatusVO>> findExchangeCouponUnitStatusAll(ExchangeCouponUnitStatusVO vo,HttpServletRequest req ) {
		ExchangeCouponUnitStatusDTO dto = ExchangeCouponUnitStatusConverter.toDTO(vo);
		List<ExchangeCouponUnitStatusDTO> rt = exchangeCouponUnitStatusManage.findExchangeCouponUnitStatusAll(dto);	
		return success(ExchangeCouponUnitStatusConverter.toVO(rt));
					 
	}	

	// 业务方法：
	@RequestMapping(value = "/findExchangeCouponUnitStatusOfPage")
	@ResponseBody
	public JsonResult<PageResult<ExchangeCouponUnitStatusVO>> findExchangeCouponUnitStatusOfPage(ExchangeCouponUnitStatusVO vo,Pagination page,HttpServletRequest req ) {
		ExchangeCouponUnitStatusDTO dto = ExchangeCouponUnitStatusConverter.toDTO(vo);
		PageResult<ExchangeCouponUnitStatusDTO> rt = exchangeCouponUnitStatusManage.findExchangeCouponUnitStatusOfPage(dto, page);
        List<ExchangeCouponUnitStatusVO> list = ExchangeCouponUnitStatusConverter.toVO(rt.getList());
        PageResult<ExchangeCouponUnitStatusVO> result = new PageResult<ExchangeCouponUnitStatusVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertExchangeCouponUnitStatusWithTx")
	@ResponseBody
	public JsonResult<Long> insertExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusVO vo,HttpServletRequest req ) {
		ExchangeCouponUnitStatusDTO dto = ExchangeCouponUnitStatusConverter.toDTO(vo);
		Long rt = exchangeCouponUnitStatusManage.insertExchangeCouponUnitStatusWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateExchangeCouponUnitStatusByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateExchangeCouponUnitStatusByIdWithTx(ExchangeCouponUnitStatusVO vo,HttpServletRequest req ) {
		ExchangeCouponUnitStatusDTO dto = ExchangeCouponUnitStatusConverter.toDTO(vo);
		int rt = exchangeCouponUnitStatusManage.updateExchangeCouponUnitStatusWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteExchangeCouponUnitStatusWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusVO vo,HttpServletRequest req ) {
		ExchangeCouponUnitStatusDTO dto = ExchangeCouponUnitStatusConverter.toDTO(vo);
		int rt = exchangeCouponUnitStatusManage.deleteExchangeCouponUnitStatusWithTx(dto);	
		return success(rt);					 
	}
		
}
	