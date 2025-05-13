package com.egeo.components.promotion.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.ExchangeBatchManage;
import com.egeo.components.promotion.converter.ExchangeBatchConverter;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.vo.ExchangeBatchVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/promotion/exchangeBatch")
public class ExchangeBatchAction extends BaseSpringController {
	
	@Resource(name="exchangeBatch")
	private ExchangeBatchManage exchangeBatchManage;


	// 业务方法：
	@RequestMapping(value = "/findExchangeBatchById")
	@ResponseBody
	public JsonResult<ExchangeBatchVO> findExchangeBatchById(Long id ) {
		
		ExchangeBatchDTO dto = new ExchangeBatchDTO();
		dto.setId(id);
		ExchangeBatchDTO rt = exchangeBatchManage.findExchangeBatchById(dto);		
		return success(ExchangeBatchConverter.toVO(rt));
					 
	}



	// 业务方法：
	@RequestMapping(value = "/findExchangeBatchAll")
	@ResponseBody
	public JsonResult<List<ExchangeBatchVO>> findExchangeBatchAll(ExchangeBatchVO vo,HttpServletRequest req ) {
		ExchangeBatchDTO dto = ExchangeBatchConverter.toDTO(vo);
		List<ExchangeBatchDTO> rt = exchangeBatchManage.findExchangeBatchAll(dto);	
		return success(ExchangeBatchConverter.toVO(rt));
					 
	}	

	// 业务方法：查看新/旧批次
	@RequestMapping(value = "/findExchangeBatchOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findExchangeBatchOfPage(ExchangeBatchVO vo,Pagination page,HttpServletRequest req ) {
		logger.info("开始查看新/旧批次");
		String str = req.getHeader("platformId");
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		ExchangeBatchDTO dto = ExchangeBatchConverter.toDTO(vo);
		return success(exchangeBatchManage.findExchangeBatchOfPage(dto, page));
					 
	}


	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertExchangeBatchWithTx")
	@ResponseBody
	public JsonResult<Long> insertExchangeBatchWithTx(ExchangeBatchVO vo,HttpServletRequest req ) {
		ExchangeBatchDTO dto = ExchangeBatchConverter.toDTO(vo);
		Long rt = exchangeBatchManage.insertExchangeBatchWithTx(dto);	
		return success(rt);					 
	}
	
	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateExchangeBatchByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateExchangeBatchByIdWithTx(ExchangeBatchVO vo,HttpServletRequest req ) {
		ExchangeBatchDTO dto = ExchangeBatchConverter.toDTO(vo);
		int rt = exchangeBatchManage.updateExchangeBatchWithTx(dto);	
		return success(rt);					 
	}

	// 业务方法：
	@RequestMapping(value = "/deleteExchangeBatchWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteExchangeBatchWithTx(ExchangeBatchVO vo,HttpServletRequest req ) {
		ExchangeBatchDTO dto = ExchangeBatchConverter.toDTO(vo);
		int rt = exchangeBatchManage.deleteExchangeBatchWithTx(dto);	
		return success(rt);					 
	}

	/**
	 * 校验当前unit与目标批次是否有效
	 */
	@RequestMapping(value = "checkExchangeValid")
	@ResponseBody
	public JsonResult checkExchangeValid(Long couponUnitId){
		logger.info("[校验当前unit与目标批次是否有效]参数:couponUnitId="+couponUnitId);
		return exchangeBatchManage.checkExchangeValid(couponUnitId);

	}

	/**
	 * 兑换流程中的优惠券校验
	 * @return
	 */
	@RequestMapping("checkExchangeAndCouponBatch")
	@ResponseBody
	public JsonResult<Map<String, Object>> checkExchangeAndCouponBatch(Integer type,Long exchangeCouponBatchId,Long exchangeCouponUnitId,Long exchangeId,HttpServletRequest req){
		logger.info("[兑换流程中的优惠券校验]参数.type="+type);
		logger.info("[兑换流程中的优惠券校验]参数.exchangeCouponBatchId="+exchangeCouponBatchId);
		logger.info("[兑换流程中的优惠券校验]参数.exchangeCouponUnitId="+exchangeCouponUnitId);
		logger.info("[兑换流程中的优惠券校验]参数.exchangeId="+exchangeId);
		if(EmptyUtil.isEmpty(type)){
			return fail("type参数缺失");
		}
		if(EmptyUtil.isEmpty(exchangeCouponBatchId)){
			return fail("exchangeCouponBatchId参数缺失");
		}
		if(EmptyUtil.isEmpty(exchangeCouponUnitId)){
			return fail("exchangeCouponUnitId参数缺失");
		}
		if(EmptyUtil.isEmpty(exchangeId)){
			return fail("exchangeId参数缺失");
		}
		CacheUser cacheUser = this.getCacheUser();
		String str = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(str)){
			return fail("platformId不能为空");
		}
		Long platformId = Long.valueOf(str);
		return exchangeBatchManage.checkAndExchange(platformId,type,cacheUser.getId(),cacheUser.getName(),exchangeCouponBatchId,exchangeCouponUnitId,exchangeId);
	}
	/**
	 * 以旧换新立即兑换接口
	 */
	@RequestMapping("exchangeRightNow")
	@ResponseBody
	public JsonResult exchangeRightNow(Long exchangeCouponBatchId,Long exchangeCouponUnitId,Long exchangeId,HttpServletRequest req){
		CacheUser cacheUser = this.getCacheUser();
		return exchangeBatchManage.exchangeRightNow(cacheUser.getId(),cacheUser.getName(),exchangeCouponBatchId,exchangeCouponUnitId,exchangeId);

	}

}
	