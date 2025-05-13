package com.egeo.components.promotion.controller.api;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.ExchangeOrderRecordManage;
import com.egeo.components.promotion.converter.ExchangeOrderRecordConverter;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.vo.ExchangeOrderRecordVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/exchangeOrderRecord")
public class ExchangeOrderRecordAction extends BaseSpringController {
	
	@Resource(name="exchangeOrderRecord")
	private ExchangeOrderRecordManage exchangeOrderRecordManage;


	// 业务方法：
	@RequestMapping(value = "/findExchangeOrderRecordById")
	@ResponseBody
	public JsonResult<ExchangeOrderRecordVO> findExchangeOrderRecordById(Long id ) {
		
		ExchangeOrderRecordDTO dto = new ExchangeOrderRecordDTO();
		dto.setId(id);
		ExchangeOrderRecordDTO rt = exchangeOrderRecordManage.findExchangeOrderRecordById(dto);		
		return success(ExchangeOrderRecordConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findExchangeOrderRecordAll")
	@ResponseBody
	public JsonResult<List<ExchangeOrderRecordVO>> findExchangeOrderRecordAll(ExchangeOrderRecordVO vo,HttpServletRequest req ) {
		ExchangeOrderRecordDTO dto = ExchangeOrderRecordConverter.toDTO(vo);
		List<ExchangeOrderRecordDTO> rt = exchangeOrderRecordManage.findExchangeOrderRecordAll(dto);	
		return success(ExchangeOrderRecordConverter.toVO(rt));
					 
	}	

	// 业务方法：分页查询以旧换新活动兑换记录
	@RequestMapping(value = "/findExchangeOrderRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<ExchangeOrderRecordVO>> findExchangeOrderRecordOfPage(ExchangeOrderRecordVO vo,Pagination page,HttpServletRequest req ) {
		logger.info("开始分页查询以旧换新活动兑换记录");
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		ExchangeOrderRecordDTO dto = ExchangeOrderRecordConverter.toDTO(vo);
		PageResult<ExchangeOrderRecordDTO> rt = exchangeOrderRecordManage.findExchangeOrderRecordOfPage(dto, page);
        List<ExchangeOrderRecordVO> list = ExchangeOrderRecordConverter.toVO(rt.getList());
        PageResult<ExchangeOrderRecordVO> result = new PageResult<ExchangeOrderRecordVO>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		
		return success(result);
					 
	}

	/**
	 * 模糊查询以旧换新活动兑换记录
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/fuzzyQueryExchangeOrderRecordOfPage")
	@ResponseBody
	public JsonResult<PageResult<ExchangeOrderRecordVO>> fuzzyQueryExchangeOrderRecordOfPage(ExchangeOrderRecordVO vo,String startTime,String endTime,
																							 Pagination page,HttpServletRequest req ) {
		logger.info("开始模糊查询以旧换新活动兑换记录");
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		ExchangeOrderRecordDTO dto = ExchangeOrderRecordConverter.toDTO(vo);
		PageResult<ExchangeOrderRecordDTO> rt = exchangeOrderRecordManage.fuzzyQueryExchangeOrderRecordOfPage(dto,startTime,endTime, page);
		List<ExchangeOrderRecordVO> list = ExchangeOrderRecordConverter.toVO(rt.getList());
		PageResult<ExchangeOrderRecordVO> result = new PageResult<ExchangeOrderRecordVO>();
		result.setList(list);
		result.setPageNo(rt.getPageNo());
		result.setPageSize(rt.getPageSize());
		result.setTotalSize(rt.getTotalSize());

		return success(result);

	}

	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertExchangeOrderRecordWithTx")
	@ResponseBody
	public JsonResult<Long> insertExchangeOrderRecordWithTx(ExchangeOrderRecordVO vo,HttpServletRequest req ) {
		ExchangeOrderRecordDTO dto = ExchangeOrderRecordConverter.toDTO(vo);
		Long rt = exchangeOrderRecordManage.insertExchangeOrderRecordWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateExchangeOrderRecordByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateExchangeOrderRecordByIdWithTx(ExchangeOrderRecordVO vo,HttpServletRequest req ) {
		ExchangeOrderRecordDTO dto = ExchangeOrderRecordConverter.toDTO(vo);
		int rt = exchangeOrderRecordManage.updateExchangeOrderRecordWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteExchangeOrderRecordWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteExchangeOrderRecordWithTx(ExchangeOrderRecordVO vo,HttpServletRequest req ) {
		ExchangeOrderRecordDTO dto = ExchangeOrderRecordConverter.toDTO(vo);
		int rt = exchangeOrderRecordManage.deleteExchangeOrderRecordWithTx(dto);	
		return success(rt);					 
	}
		
}
	