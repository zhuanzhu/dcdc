package com.egeo.components.promotion.service.read;


import java.util.List;

import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CouponUnitReadService {

	public CouponUnitDTO findCouponUnitById(CouponUnitDTO dto);

	public PageResult<CouponUnitDTO> findCouponUnitOfPage(CouponUnitDTO dto,Pagination page);

	public List<CouponUnitDTO> findCouponUnitAll(CouponUnitDTO dto);

	/**
	 * 查询优惠卷unit列表
	 * @param dto
	 * @param userList
	 * @param page
	 * @return
	 */
	public PageResult<CouponUnitDTO> findCouponUnitOfPageByBlurry(CouponUnitDTO dto, List<Long> userList,
			Pagination page);

	/**
	 * 优惠卷unit列表(客户端)
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<CouponUnitDTO> findCouponUnitOfPageByUser(CouponUnitDTO dto, Pagination page);

	/**
	 * 客户端领卷中心
	 * @param page
	 * @return
	 */
	public PageResult<CouponUnitDTO> findCouponUnitCenterOfPage(CouponUnitDTO dto, Long companyIdByType, Pagination page);

	/**
	 * 根据用户和su的id查询优惠卷列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<CouponUnitDTO> findSUCouponBatchOfPage(CouponUnitDTO dto, List<Long> goodIdList, Long companyIdByType, Pagination page);

	/**
	 * 查询用户所有可用满减卷
	 * @param couponUnitDTO
	 * @return
	 */
	public List<CouponUnitDTO> findCouponUnitOfAllByUser(CouponUnitDTO couponUnitDTO);

	/**
	 * 通过订单id查询其优惠卷信息
	 * @param orderId
	 * @return
	 */
	public Integer findCouponUnitByOrderId(Long orderId);

    List<CouponUnitDTO> findCouponUnitListByBatchIdList(List<Long> couponBatchList);

    Long findCouponUnitAllCount(CouponUnitDTO couponUnitDTO);

    List<CouponUnitDTO> findCouponUnitAllByCouponUnitCode(String oldUnitCode);

    List<CouponUnitDTO> findCouponUnitAndBatchExchange(CouponUnitDTO dto, Long companyIdByType);

    Long findCouponUnitCountOfFreezeByParams(Long couponBatchId, Long startNum, Long endNum);
    
    Integer countUnreadCouponUnit(Long userId, Integer couponType);
    
}
	