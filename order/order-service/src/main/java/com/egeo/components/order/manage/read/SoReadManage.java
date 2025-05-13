package com.egeo.components.order.manage.read;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.egeo.components.order.condition.SoExtendsCondition;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.po.SoPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoReadManage {

	PageResult<SoPO> findOrderListByUserAndStatus(SoPO soPO, Pagination page);

	List<SoPO> findAllunpayOrders();

	/**
	 * 条件查询订单列表
	 * @param orderCodeList
	 * @param page
	 * @param startTime
	 * @param endTime
	 * @param orderStatus
	 * @param platformId
	 * @return
	 */
	List<SoPO> querySoListByCondition(List<String> orderCodeList, Pagination page, Date startTime, Date endTime,
			Integer orderStatus,String exactOrderCode, Long platformId);

	/**
	 * 条件查询订单列表总量
	 * @param orderCodeList
	 * @param page
	 * @param startTime
	 * @param endTime
	 * @param orderStatus
	 * @param exactOrderCode
	 * @param platformId
	 * @return
	 */
	int querySoCountByCondition(List<String> orderCodeList, Date startTime, Date endTime,
			Integer orderStatus, String exactOrderCode, Long platformId);

	/**
	 * 根据id查询订单
	 * @param orderId
	 * @return
	 */
	SoPO querySoById(Long orderId);

	/**
	 * 根据订单编号查询订单
	 * @param orderCode
	 * @return
	 */
	SoPO querySoByOrderCode(String orderCode);

	/**
	 * 查询未删除的订单
	 * @param orderCode
	 * @return
	 */
	SoPO queryUndeleteSoByOrderCode(String orderCode);

	/**
	 * 分页查询订单列表2.0
	 * @param po
	 * @param page
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	PageResult<SoPO> findSoOfPage(SoPO po, Integer cashPayType, Integer useFubi, Date startDateTime, Date endDateTime,
			Pagination page);

	/**
	 * 后台查询订单分页列表
     * @param email
     * @param puName
     * @param orderCode
     * @param startDateTime
     * @param endDateTime
     * @param status
     * @param confirmStatus
     * @param payStatus
     * @param paymentType
     * @param showTest
     * @param platformId
     * @param page
     * @param refundFlag            @return
     * */
	PageResult<SoPO> queryBackStageSoPage(
            Long storeId, String orderCode, List<Long> userIds, List<Long> puIds, Date startDateTime,
            Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus, Integer paymentType,
            Boolean showTest, Long platformId, Pagination page, boolean refundFlag, List<Long> testCompanyIds,
			List<Long> companyIds,Integer auditStatus,List<Long>  soIds);
	/**
	 * 查询所有状态为已发货、已收货，订单自动完成时间为空的订单
	 */
	List<SoPO> findByOrderStatusNoOrderAutoCompleteDate();
	/**
	 * 查询订单自动完成时间不为空的订单
	 * @return
	 */
	List<SoPO> findByOrderAutoCompleteDateNoNull();

	/**
	 * 根据用户和订单状态查询订单数量
	 * @param id
	 * @param orderStatusUnpay
	 * @return
	 */
	int querySoCountByUserAndStatus(Long userId, Integer orderStatus,Long platformId);
	/**
	 * 当月订单：所属平台门店为该门店的创建于当月（自然月）订单数量，包括已取消、已完成等所有订单状态。
	 * @param platformId
	 * @return
	 */
	Integer findCurrentMonthOrder(Long storeId, Long platformId);


	public List<SoPO> findMonthOrder(Date month);
	/**
	 * 今日订单：所属平台门店为该门店的创建于当日（自然日）订单数量，包括已取消、已完成等所有订单状态。
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	Integer findCurrentDayOrder(Long storeId, Long platformId);
	/**
	 * 待签收订单(待签收:确认状态为已确认，支付状态为已付款)
	 * @param orderConfirmStatus 订单确认状态  0:未确认，1:已确认，2:已取消 3:已完成
	 * @param orderPayStatus 订单支付状态 0:未支付、1:已支付、2:已退款
	 * @param storeId 门店id
	 * @param platformId 平台id
	 * @return
	 */
	Integer findNoSignOrderSumByStatus(Integer orderConfirmStatus, Integer orderPayStatus, Long storeId,
			Long platformId);
	/**
	 * 根据门店id查询当月订单总额
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	BigDecimal findCurrentMonthOrderAmount(Long storeId, Long platformId);
	/**
	 * 根据门店id查询今日订单总额
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	BigDecimal findcurrentDayOrderAmount(Long storeId, Long platformId);


	List<SoPO> findSoByCode(String orderCode);

	SoPO findSoById(Long orderId);


	Long findSoSum(SoPO soPO);

	List<SoPO> findSoByPuId(Long puId, Integer orderConfirmStatus);

	List<SoPO> findSoByPuIds(List<Long> puIds, Integer orderConfirmStatus);

    SoPO findSoByThirdpartyId(String jdOrderId);

	PageResult<SoExtendsCondition> getOrderListByUserAndStatus(SoPO soPO, Pagination page);

	List<SoPO> getSoByIds(List<Long> ids);

	List<SoPO> queryBackStageSoListPage(
			Long storeId, String orderCode, List<Long> userIds, List<Long> puIds, Date startDateTime,
			Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus, Integer paymentType,
			Boolean showTest, Long platformId, Pagination page, boolean refundFlag, List<Long> testCompanyIds,
			List<Long> companyIds,Integer auditStatus,List<Long>  soIds);
}
