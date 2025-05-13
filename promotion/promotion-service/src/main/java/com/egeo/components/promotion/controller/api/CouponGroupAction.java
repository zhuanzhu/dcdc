package com.egeo.components.promotion.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.promotion.business.CouponGroupManage;
import com.egeo.components.promotion.converter.CouponGroupConverter;
import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.components.promotion.vo.CouponGroupVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/promotion/couponGroup")
public class CouponGroupAction extends BaseSpringController {

	@Resource(name = "couponGroup")
	private CouponGroupManage couponGroupManage;

	/**
	 * 创建或更新优惠卷分组
	 * 
	 * @param vo
	 * @param couponList
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateCouponGroupWithTx")
	@ResponseBody
	public JsonResult<Map<String, Object>> insertOrUpdateCouponGroupWithTx(CouponGroupVO vo, String coupons,
			String stores, HttpServletRequest req) {
		logger.info("创建或更新优惠卷分组");
		String str = req.getHeader("platformId");
		String flag = null;
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		//TODO
		/*CacheUser cacheUser = getCacheUser();
		vo.setUpdateUser(cacheUser.getId());*/
		vo.setUpdateUser(268l);
		try {
			vo.setCouponList(JSONArray.parseArray(coupons, Long.class)); 
		} catch (Exception e) {
			return fail("优惠卷参数错误");
		}
		if (EmptyUtil.isEmpty(vo.getCouponList()))
			return fail("请选择优惠卷");
		if (EmptyUtil.isNotEmpty(stores)) {
			List storeIds = JSONArray.parseArray(stores, Long.class);
			if (EmptyUtil.isNotEmpty(storeIds)) {
				if (StringUtils.equalsIgnoreCase("-1", storeIds.get(0).toString())) {
					flag = "-1";
				} else {
					vo.setStoreList(storeIds);
				}
			}
		}
		return couponGroupManage.insertOrUpdateCouponGroupWithTx(flag, CouponGroupConverter.toDTO(vo));
	}

	/**
	 * 删除优惠卷分组
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/deleteCouponGroupWithTx")
	@ResponseBody
	public JsonResult<Integer> deleteCouponGroupWithTx(CouponGroupVO vo, HttpServletRequest req) {
		logger.info("删除优惠卷分组");
		if (EmptyUtil.isEmpty(vo.getId()))
			return fail("优惠卷分组id不能为空");
		CouponGroupDTO dto = new CouponGroupDTO();
		dto.setId(vo.getId());

		int rt = couponGroupManage.deleteCouponGroupWithTx(dto);
		return success(rt);
	}

	/**
	 * 优惠卷分组列表
	 * 
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findCouponGroupOfPage")
	@ResponseBody
	public JsonResult<PageResult<Map<String,Object>>> findCouponGroupOfPage(CouponGroupVO vo, Pagination page, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		return success(couponGroupManage.findCouponGroupOfPageByBlurry(CouponGroupConverter.toDTO(vo), page));

	}

	/**
	 * 通过id查询优惠卷分组详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findCouponGroupById")
	@ResponseBody
	public JsonResult<Map<String,Object>> findCouponGroupById(Long id) {
		if (EmptyUtil.isEmpty(id))
			return fail("id不能为空");
		
		CouponGroupDTO dto = new CouponGroupDTO();
		dto.setId(id);
		Map<String,Object> rt = couponGroupManage.findCouponGroupById(dto);
		if (EmptyUtil.isEmpty(rt))
			return fail("优惠卷分组不存在");
		return success(rt);

	}

	// 业务方法：
	@RequestMapping(value = "/findCouponGroupAll")
	@ResponseBody
	public JsonResult<List<CouponGroupVO>> findCouponGroupAll(CouponGroupVO vo, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		CouponGroupDTO dto = CouponGroupConverter.toDTO(vo);
		List<CouponGroupDTO> rt = couponGroupManage.findCouponGroupAll(dto);
		return success(CouponGroupConverter.toVO(rt));

	}

	// 业务方法：返回是插入行的id
	@RequestMapping(value = "/insertCouponGroupWithTx")
	@ResponseBody
	public JsonResult<Long> insertCouponGroupWithTx(CouponGroupVO vo, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		CouponGroupDTO dto = CouponGroupConverter.toDTO(vo);
		Long rt = couponGroupManage.insertCouponGroupWithTx(dto);
		return success(rt);
	}

	// 业务方法：根据id更新数据
	@RequestMapping(value = "/updateCouponGroupByIdWithTx")
	@ResponseBody
	public JsonResult<Integer> updateCouponGroupByIdWithTx(CouponGroupVO vo, HttpServletRequest req) {
		String str = req.getHeader("platformId");		
		if(EmptyUtil.isNotEmpty(str)){
			Long platformId = Long.valueOf(str);
			vo.setPlatformId(platformId);
		}
		CouponGroupDTO dto = CouponGroupConverter.toDTO(vo);
		int rt = couponGroupManage.updateCouponGroupWithTx(dto);
		return success(rt);
	}

}
