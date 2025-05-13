package com.egeo.components.promotion.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.vo.CouponUnitReVO;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface CouponUnitManage {

	public CouponUnitDTO findCouponUnitById(CouponUnitDTO dto);	

	public PageResult<CouponUnitDTO> findCouponUnitOfPage(CouponUnitDTO dto,Pagination page);

	public List<CouponUnitDTO> findCouponUnitAll(CouponUnitDTO dto);


	int updateCouponUnitWithTx(CouponUnitDTO dto);

	int deleteCouponUnitWithTx(CouponUnitDTO dto);
	
	/**
	 * 领取优惠卷
	 * @param dto
	 * @param companyId
	 * @return
	 */
	JsonResult<Map<String, Object>> insertCouponUnitWithTx(CouponUnitDTO dto);

	/**
	 * 优惠卷unit列表
	 * @param vo
	 * @param page
	 * @return
	 */
	public PageResult<Map<String, Object>> findCouponUnitOfPageByBlurry(CouponUnitReVO vo, Pagination page);

	/**
	 * 优惠券unit重置为有效
	 * @param dto
	 * @return
	 */
	public JsonResult<String> resetCouponUnitWithTx(List<Long> couponUnitList, Date effectStartTime, Date effectEndTime);

	/**
	 * 优惠卷unit列表(客户端)
	 * @param vo
	 * @param page
	 * @return
	 */
	public Map<String, Object> findCouponUnitOfPageByUser(CouponUnitDTO dto, Pagination page);

	/**
	 * 客户端领卷中心
	 * @return
	 */
	public PageResult<Map<String, Object>> findCouponUnitCenterOfPage(CouponUnitDTO dto, Pagination page);

	/**
	 * 查询商品详情页的优惠卷批次列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<Map<String, Object>> findCouponBatchGoodsOfPage(CouponUnitDTO dto, Pagination page);

    JsonResult<Map<String,Object>> sendCouponUnitWithTx(Long platformId, Long typeId, Long storeId, Long companyId, Long userId,Long clientId);

	JsonResult findExchangeActivityByCouponUnitId(Long couponUnitId, Long userId, Long companyId, Long storeId,Long clientId,Long platformId);

	void updateCouponUnitReadStatus(List<Long> ids);
	
}
	