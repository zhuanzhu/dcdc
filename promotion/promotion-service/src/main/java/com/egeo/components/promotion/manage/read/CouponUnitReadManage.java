package com.egeo.components.promotion.manage.read;

import java.util.List;

import com.egeo.components.promotion.condition.CouponUnitCondition;
import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CouponUnitReadManage {

	public CouponUnitPO findCouponUnitById(CouponUnitPO po);

	public PageResult<CouponUnitPO> findCouponUnitOfPage(CouponUnitPO po,Pagination page);

	public List<CouponUnitPO> findCouponUnitAll(CouponUnitPO po);

	/**
	 * 查询优惠卷unit列表
	 * @param po
	 * @param userList
	 * @param page
	 * @return
	 */
	public PageResult<CouponUnitCondition> findCouponUnitOfPageByBlurry(CouponUnitCondition po, List<Long> userList,
			Pagination page);

	/**
	 * 优惠卷unit列表(客户端)
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<CouponUnitCondition> findCouponUnitOfPageByUser(CouponUnitCondition po, Pagination page);
	
	/**
	 * 客户端领卷中心
	 * @param userId
	 * @param page
	 * @return
	 */
	public PageResult<CouponUnitCondition> findCouponUnitCenterOfPage(CouponUnitCondition po, Long companyIdByType, Pagination page);

	/**
	 * 根据用户和su的id查询优惠卷列表
	 * @param dtoToCondition
	 * @param page
	 * @return
	 */
	public List<CouponUnitCondition> findSUCouponBatchOfPage(CouponUnitCondition dtoToCondition, List<Long> goodIdList, Long companyIdByType, Pagination page);

	/**
	 * 查询用户所有可用满减卷
	 * @param couponUnitDTO
	 * @return
	 */
	public List<CouponUnitCondition> findCouponUnitOfAllByUser(CouponUnitCondition po);

    List<CouponUnitCondition> findCouponUnitListByBatchIdList(List<Long> couponBatchList);

	Long findCouponUnitAllCount(CouponUnitPO couponUnitPO);

    List<CouponUnitCondition> findCouponUnitAndBatchExchange(CouponUnitCondition couponUnitCondition, Long companyIdByType);

    Long findCouponUnitCountOfFreezeByParams(Long couponBatchId, Long startNum, Long endNum);
    
    Integer countUnreadCouponUnit(Long userId, Integer couponType);
}
	