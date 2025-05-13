package com.egeo.components.promotion.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface CouponManage {

	/**
	 * 通过id查询优惠卷详情
	 * @param dto
	 * @return
	 */
	public Map<String,Object> findCouponById(CouponDTO dto);	

	public PageResult<CouponDTO> findCouponOfPage(CouponDTO dto,Pagination page);

	public List<CouponDTO> findCouponAll(CouponDTO dto);

	Long insertCouponWithTx(CouponDTO dto);

	int updateCouponWithTx(CouponDTO dto);

	int deleteCouponWithTx(CouponDTO dto);

	/**
	 * 创建或编辑优惠卷
	 * @param flag = -1,关联所有门店
	 * @param dto
	 * @return
	 */
	public JsonResult<Map<String,Object>> insertOrUpdateCouponWithTx(String flag, CouponDTO dto);

	/**
	 * 优惠卷列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<Map<String, Object>> findCouponOfPageByBlurry(CouponDTO dto, List<Long> couponIdList, Pagination page);

	/**
	 * 查询优惠卷的所属公司
	 * @param platformId
	 * @param id
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> findCouponCompanyOfPage(Long platformId, Long id, Integer type, Pagination page);

	/**
	 * 查询优惠卷的相关商品
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> findCouponGoodsAll(CouponDTO dto);
}
	