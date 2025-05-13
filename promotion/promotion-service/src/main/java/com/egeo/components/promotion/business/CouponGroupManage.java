package com.egeo.components.promotion.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.CouponGroupDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface CouponGroupManage {

	public Map<String,Object> findCouponGroupById(CouponGroupDTO dto);	

	public PageResult<CouponGroupDTO> findCouponGroupOfPage(CouponGroupDTO dto,Pagination page);

	public List<CouponGroupDTO> findCouponGroupAll(CouponGroupDTO dto);

	Long insertCouponGroupWithTx(CouponGroupDTO dto);

	int updateCouponGroupWithTx(CouponGroupDTO dto);

	int deleteCouponGroupWithTx(CouponGroupDTO dto);

	/**
	 * 创建或更新优惠卷分组
	 * @param dto
	 * @return
	 */
	public JsonResult<Map<String, Object>> insertOrUpdateCouponGroupWithTx(String flag, CouponGroupDTO dto);

	/**
	 * 查询优惠卷列表(模糊查询)
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<Map<String,Object>> findCouponGroupOfPageByBlurry(CouponGroupDTO dto, Pagination page);
}
	