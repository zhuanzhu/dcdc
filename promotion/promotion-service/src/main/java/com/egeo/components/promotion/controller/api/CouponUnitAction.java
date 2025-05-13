package com.egeo.components.promotion.controller.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.promotion.business.CouponUnitManage;
import com.egeo.components.promotion.converter.CouponUnitConverter;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.vo.CouponUnitReVO;
import com.egeo.components.promotion.vo.CouponUnitVO;
import com.egeo.components.utils.JsonUtils;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/promotion/couponUnit")
public class CouponUnitAction extends BaseSpringController {

	@Resource(name = "couponUnit")
	private CouponUnitManage couponUnitManage;

	@Autowired
	private JedisUtil jedisUtil;
	private static String COUPON_BATCH_LOCK_VALUE = "coupon_batch";


	/**
	 * 优惠卷unit列表
	 *
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCouponUnitOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findCouponUnitOfPage(CouponUnitReVO vo, Pagination page,
																			HttpServletRequest req) {
		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		return success(couponUnitManage.findCouponUnitOfPageByBlurry(vo, page));
	}

	/**
	 * 优惠券unit重置为有效
	 *
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/resetCouponUnitWithTx")
	@ResponseBody
	public JsonResult<String> resetCouponUnitWithTx(String couponUnitList, Long effectStartTime, Long effectEndTime,
													HttpServletRequest req) {
		logger.info("优惠券unit重置为有效");
		// 参数校验
		List<Long> couponUnitList_ = null;
		try {
			couponUnitList_ = JSONArray.parseArray(couponUnitList, Long.class);
		} catch (Exception e) {
			return fail("优惠券unit的id集合参数格式错误");
		}
		if (EmptyUtil.isEmpty(couponUnitList_))
			return fail("id集合参数不能为空");
		if (EmptyUtil.isEmpty(effectStartTime))
			return fail("有效期开始时间不能为空");
		if (EmptyUtil.isEmpty(effectEndTime))
			return fail("有效期结束时间不能为空");
		if (effectEndTime.compareTo(effectStartTime) <= 0)
			return fail("有效期结束时间需晚于有效期开始时间");
		if (effectEndTime.compareTo(System.currentTimeMillis()) <= 0)
			return fail("有效期结束时间需晚于系统当前日期");

		return couponUnitManage.resetCouponUnitWithTx(couponUnitList_, new Date(effectStartTime),
				new Date(effectEndTime));
	}

	// ******************************客户端接口****************************************

	/**
	 * 通过用户查询优惠卷unit列表
	 *
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCouponUnitByUserOfPage")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCouponUnitByUserOfPage(Integer couponUnitStatus,
																				  Integer couponType, Long storeId, Pagination page,
																				  HttpServletRequest req) {
		if (EmptyUtil.isEmpty(couponUnitStatus))
			return fail("优惠卷unit状态不能为空");
		if (EmptyUtil.isEmpty(couponType))
			return fail("优惠卷类型不能为空");

		logger.info("[用户查询优惠券unit],storeId="+storeId);
		CouponUnitDTO couponUnitDTO = new CouponUnitDTO();

		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			couponUnitDTO.setPlatformId(platformId);
		}

		couponUnitDTO.setUserId(getCacheUser().getId());
		couponUnitDTO.setCompanyId(getCacheUser().getCompanyId());
		couponUnitDTO.setCouponUnitStatus(couponUnitStatus);
		couponUnitDTO.setCouponType(couponType);
		couponUnitDTO.setStoreId(storeId);
		logger.info("用户查询优惠卷unit列表 clientStr:" + req.getHeader("clientId"));
		couponUnitDTO.setClientId(Long.valueOf(req.getHeader("clientId")));
		return success(couponUnitManage.findCouponUnitOfPageByUser(couponUnitDTO, page));
	}

	/**
	 * 客户端领卷中心
	 *
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCouponUnitCenterOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String, Object>>> findCouponUnitCenterOfPage(Long storeId, Pagination page,
																				  HttpServletRequest req) {

		CacheUser cacheUser = getCacheUser();
		if (EmptyUtil.isEmpty(cacheUser.getCompanyId()))
			return fail("用户公司id不能为空");
		CouponUnitDTO dto = new CouponUnitDTO();

		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}
		String clientStr = req.getHeader("clientId");
		logger.info("客户端领卷中心 clientStr:" + clientStr);
		dto.setClientId(Long.valueOf(clientStr));
		
		dto.setUserId(cacheUser.getId());
		dto.setCompanyId(cacheUser.getCompanyId());
		dto.setCouponRelType(0);
		dto.setStoreId(storeId);
		return success(couponUnitManage.findCouponUnitCenterOfPage(dto, page));
	}

	/**
	 * 发放优惠卷
	 */
	@RequestMapping("/sendCouponUnitWithTx")
	@ResponseBody
	public JsonResult<Map<String, Object>> sendCouponUnitWithTx(Long storeId, Long typeId, HttpServletRequest req) {
		logger.info("[发放优惠卷unit]参数:" + typeId);
		if (EmptyUtil.isEmpty(typeId)) {
			return fail("typeId不能为空");
		}
		CacheUser cacheUser = getCacheUser();
		if (EmptyUtil.isEmpty(cacheUser.getCompanyId()))
			return fail("用户的公司id不能为空");
		if (EmptyUtil.isEmpty(storeId)) {
			return fail("storeId不能为空");

		}
		String str = req.getHeader("platformId");
		if (EmptyUtil.isEmpty(str)) {
			return fail("platformId不能为空");
		}
		
		Long platformId = Long.valueOf(str);
		
		Long clientId = Long.valueOf(req.getHeader("clientId"));
		
		return couponUnitManage.sendCouponUnitWithTx(platformId, typeId, storeId, cacheUser.getCompanyId(), cacheUser.getId(),clientId);

	}


	/**
	 * 领取优惠卷
	 *
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertCouponUnitWithTx")
	@ResponseBody
	public JsonResult<Map<String, Object>> insertCouponUnitWithTx(Long id, HttpServletRequest req) {
		logger.info("领取优惠卷");

		boolean lock = true;
		//多人抢同一个批次
		try {
			lock=jedisUtil.lockWithParam(JedisUtil.COUPON_BATCH_LOCK_PRE+id,COUPON_BATCH_LOCK_VALUE,JedisUtil.COUPON_LOCK_EXPIRE_TIME);
		} catch (InterruptedException e) {
			logger.info("[领取优惠券获取锁异常]");
			jedisUtil.delLock(JedisUtil.COUPON_BATCH_LOCK_PRE+id);
			e.printStackTrace();
		}
		if(!lock){
			return fail("网络繁忙,请稍后重试");
		}
		try {
			if (EmptyUtil.isEmpty(id))
				return fail("优惠卷批次的id不能为空");
			CacheUser cacheUser = getCacheUser();

			if (EmptyUtil.isEmpty(cacheUser.getCompanyId()))
				return fail("用户的公司id不能为空");

			CouponUnitDTO dto = new CouponUnitDTO();

			String str = req.getHeader("platformId");
			if (EmptyUtil.isNotEmpty(str)) {
				Long platformId = Long.valueOf(str);
				dto.setPlatformId(platformId);
			}

			dto.setUserId(cacheUser.getId());
			dto.setCompanyId(cacheUser.getCompanyId());
			dto.setCouponRelType(0);
			dto.setCouponBatchId(id);
			return couponUnitManage.insertCouponUnitWithTx(dto);
		}catch (Exception e){
			logger.info("领取优惠券异常");
			throw e;
		}finally {
			jedisUtil.delLock(JedisUtil.COUPON_BATCH_LOCK_PRE+id);
		}
	}


	/**
	 * 查询商品详情页的优惠卷批次列表
	 *
	 * @param standardUnitId
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCouponBatchGoodsOfAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findCouponBatchGoodsOfAll(Long standardUnitId, Long storeId, Pagination page,
																		   HttpServletRequest req) {
		if (EmptyUtil.isEmpty(standardUnitId)) {
			return fail("商品id不能为空");
		}
		CacheUser cacheUser = getCacheUser(false);
		if (EmptyUtil.isEmpty(cacheUser.getCompanyId()))
			return fail("用户公司id不能为空");
		CouponUnitDTO dto = new CouponUnitDTO();

		String str = req.getHeader("platformId");
		if (EmptyUtil.isNotEmpty(str)) {
			Long platformId = Long.valueOf(str);
			dto.setPlatformId(platformId);
		}

		dto.setUserId(cacheUser.getId());
		dto.setCompanyId(cacheUser.getCompanyId());
		dto.setGoodsId(standardUnitId);
		dto.setStoreId(storeId);
		logger.info("商品详情页的优惠卷批次列表 clientStr:" + req.getHeader("clientId"));
		dto.setClientId(Long.valueOf(req.getHeader("clientId")));
		return success(couponUnitManage.findCouponBatchGoodsOfPage(dto, page));
	}

	// 业务方法：
	@RequestMapping(value = "/findCouponUnitById")
	@ResponseBody
	public JsonResult<CouponUnitVO> findCouponUnitById(Long id) {

		CouponUnitDTO dto = new CouponUnitDTO();
		dto.setId(id);
		CouponUnitDTO rt = couponUnitManage.findCouponUnitById(dto);
		return success(CouponUnitConverter.toVO(rt));

	}

	// 业务方法：
	@RequestMapping(value = "/findCouponUnitAll")
	@ResponseBody
	public JsonResult<List<CouponUnitVO>> findCouponUnitAll(CouponUnitVO vo, HttpServletRequest req) {
		CouponUnitDTO dto = CouponUnitConverter.toDTO(vo);
		List<CouponUnitDTO> rt = couponUnitManage.findCouponUnitAll(dto);
		return success(CouponUnitConverter.toVO(rt));

	}

/**
 * 根据活动couponUnitId查询有效的活动
 */
	@RequestMapping(value = "/findExchangeActivityByCouponUnitId")
	@ResponseBody
	public JsonResult findExchangeActivityByCouponUnitId(Long couponUnitId,Long storeId, HttpServletRequest req){
		logger.info("[根据活动couponUnitId查询有效的活动]参数:couponUnitId"+couponUnitId);
		logger.info("[根据活动couponUnitId查询有效的活动]参数:storeId"+storeId);

		CacheUser cacheUser = this.getCacheUser(false);
		String str = req.getHeader("platformId");
		if(EmptyUtil.isEmpty(str)){
			return fail("platformId缺失");
		}
		Long platformId = Long.valueOf(str);
		Long userId = cacheUser.getId();
		Long clientId = Long.valueOf(req.getHeader("clientId"));

		return couponUnitManage.findExchangeActivityByCouponUnitId(couponUnitId,userId,cacheUser.getCompanyId(),storeId,clientId,platformId);

	}

	@RequestMapping("/findSelectedBatchId")
	@ResponseBody
	public JsonResult<List<Long>> findSelectedBatchId(String batchIds){
		List<Long> batchIdList = JsonUtils.jsonToList(batchIds, Long.class);
		return success(batchIdList);
	}
	
	@RequestMapping("/updateCouponUnitReadStatus")
	@ResponseBody
	public JsonResult<String> updateCouponUnitReadStatus(String ids){
		logger.debug("ids:" + ids);
		List<Long> idList = JsonUtils.jsonToList(ids, Long.class);
		couponUnitManage.updateCouponUnitReadStatus(idList);
		return success("");
	}
}
