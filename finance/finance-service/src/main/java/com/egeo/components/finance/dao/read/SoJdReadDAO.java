package com.egeo.components.finance.dao.read;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.SoJdPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface SoJdReadDAO extends BaseReadDAO<SoJdPO>{

	List<SoJdPO> findAllunpayOrders(@Param("po") SoJdPO po);

	/**
	 * 条件查询订单列表
	 * @param orderCodeList
	 * @param page
	 * @param startTime
	 * @param endTime
	 * @param orderStatus
	 * @param exactOrderCode 
	 * @param platformId 
	 * @return
	 */
	List<SoJdPO> querySoListByCondition(
			@Param("ids")List<String> orderCodeList, @Param("page")Pagination page, 
			@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("orderStatus")Integer orderStatus, @Param("exactOrderCode")String exactOrderCode, 
			@Param("platformId")Long platformId);

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
	int querySoCountByCondition(@Param("ids")List<String> orderCodeList, 
			@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("orderStatus")Integer orderStatus, @Param("exactOrderCode")String exactOrderCode, 
			@Param("platformId")Long platformId);

	/**
	 * 根据订单编号查询订单
	 * @param orderCode
	 * @return
	 */
	SoJdPO querySoByOrderCode(@Param("orderCode")String orderCode);
	SoJdPO querySoByRefundOrderCode(@Param("orderCode")String orderCode);

	/**
	 * 查询未删除的订单
	 * @param orderCode
	 * @return
	 */
	SoJdPO queryUndeleteSoByOrderCode(@Param("orderCode")String orderCode);


	int countOrderOfPage(@Param("po")SoJdPO po, @Param("cashPayType")Integer cashPayType, @Param("useFubi")Integer useFubi, @Param("startDateTime")Date startDateTime, @Param("endDateTime")Date endDateTime);

	List<SoJdPO> findOrderOfPage(@Param("po")SoJdPO po, @Param("cashPayType")Integer cashPayType, @Param("useFubi")Integer useFubi, @Param("startDateTime")Date startDateTime, @Param("endDateTime")Date endDateTime,@Param("page")Pagination page);

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
     * @param refundFlag 只显示退款            @return
     * */
	List<SoJdPO> queryBackStageSoPage(
            @Param("storeId") Long storeId,
            @Param("orderCode") String orderCode,
            @Param("userIds") List<Long> userIds,
            @Param("puIds") List<Long> puIds,
            @Param("startDateTime") Date startDateTime,
            @Param("endDateTime") Date endDateTime,
            @Param("status") Integer status,
            @Param("confirmStatus") Integer confirmStatus,
            @Param("payStatus") Integer payStatus,
            @Param("paymentType") Integer paymentType,
            @Param("showTest") Boolean showTest,
            @Param("platformId") Long platformId,
            @Param("page") Pagination page,
            @Param("refundFlag") boolean refundFlag,
            @Param("testCompanyIds") List<Long> testCompanyIds,
            @Param("companyIds") List<Long> companyIds);
	
	/**
	 * 后台查询订单分页列表总记录数
	 * @param email
	 * @param puName
	 * @param page
	 * @param orderCode
	 * @param startDateTime
	 * @param endDateTime
	 * @param status
	 * @param confirmStatus
	 * @param payStatus
	 * @param paymentType
	 * @param showTest
	 * @param platformId          @return
	 * */
	Integer queryBackStageSoPageTotalSize(
			@Param("storeId") Long storeId,
			@Param("orderCode") String orderCode,
			@Param("userIds") List<Long> userIds,
			@Param("puIds") List<Long> puIds,
			@Param("startDateTime") Date startDateTime,
			@Param("endDateTime") Date endDateTime,
			@Param("status") Integer status,
			@Param("confirmStatus") Integer confirmStatus,
			@Param("payStatus") Integer payStatus,
			@Param("paymentType") Integer paymentType,
			@Param("showTest") Boolean showTest,
			@Param("platformId") Long platformId,
			@Param("refundFlag") boolean refundFlag,
			@Param("testCompanyIds") List<Long> testCompanyIds,
            @Param("companyIds") List<Long> companyIds);
	/**
	 * 查询所有状态为已发货、已收货，订单自动完成时间为空的订单
	 */
	List<SoJdPO> findByOrderStatusNoOrderAutoCompleteDate();
	/**
	 * 查询订单自动完成时间不为空的订单
	 * @return
	 */
	List<SoJdPO> findByOrderAutoCompleteDateNoNull();

	/**
	 * 根据用户和订单状态查询订单数量
	 * @param id
	 * @param orderStatusUnpay
	 * @return
	 */
	int querySoCountByUserAndStatus(@Param("userId")Long userId, 
			@Param("orderStatus")Integer orderStatus,
			@Param("orderDeleteStatus")Integer orderDeleteStatus,
			@Param("platformId")Long platformId);
	/**
	 * 当月订单：所属平台门店为该门店的创建于当月（自然月）订单数量，包括已取消、已完成等所有订单状态。
	 * @param date 
	 * @param platformId
	 * @return
	 */
	Integer findCurrentMonthOrder(
			@Param("storeId")Long storeId,
			@Param("date")Date date, 
			@Param("platformId")Long platformId);
	List<SoJdPO> findMonthOrder(
			@Param("date")Date date);
	/**
	 * 今日订单：所属平台门店为该门店的创建于当日（自然日）订单数量，包括已取消、已完成等所有订单状态。
	 * @param storeId
	 * @param date
	 * @param platformId
	 * @return
	 */
	Integer findCurrentDayOrder(
			@Param("storeId")Long storeId,
			@Param("date")Date date, 
			@Param("platformId")Long platformId);
	/**
	 * 待签收订单(待签收:确认状态为已确认，支付状态为已付款)
	 * @param orderConfirmStatus 订单确认状态  0:未确认，1:已确认，2:已取消 3:已完成
	 * @param orderPayStatus 订单支付状态 0:未支付、1:已支付、2:已退款
	 * @param storeId 门店id
	 * @param platformId 平台id
	 * @return
	 */
	Integer findNoSignOrderSumByStatus(
			@Param("orderConfirmStatus")Integer orderConfirmStatus, 
			@Param("orderPayStatus")Integer orderPayStatus, 
			@Param("storeId")Long storeId,
			@Param("platformId")Long platformId);
	/**
	 * 根据门店id查询当月订单总额
	 * @param storeId
	 * @param date
	 * @param platformId
	 * @return
	 */
	BigDecimal findCurrentMonthOrderAmount(
			@Param("storeId")Long storeId,
			@Param("date")Date date, 
			@Param("platformId")Long platformId);
	/**
	 * 根据门店id查询今日订单总额
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	BigDecimal findcurrentDayOrderAmount(
			@Param("storeId")Long storeId,
			@Param("date")Date date, 
			@Param("platformId")Long platformId);

    Long findSoSum(@Param("SoJdPO") SoJdPO SoJdPO);

	List<SoJdPO> findSoByPuId(@Param("puId") Long puId,@Param("orderConfirmStatus") Integer orderConfirmStatus);

	List<SoJdPO> findSoByPuIds(@Param("puIds") List<Long> puIds,@Param("orderConfirmStatus") Integer orderConfirmStatus);

    SoJdPO findSoByThirdpartyId(@Param("jdOrderId")String jdOrderId);

   /* List<SoJdPO> queryBackStageSoPageByStoreIdNull(@Param("storeId")Long storeId,@Param("orderCode") String orderCode,
												 @Param("userIds")List<Long> userIds, @Param("puIds") List<Long> puIds,
												 @Param("startDateTime")Date startDateTime, @Param("endDateTime") Date endDateTime,
												 @Param("status") Integer status, @Param("confirmStatus") Integer confirmStatus,
												 @Param("payStatus") Integer payStatus, @Param("paymentType") Integer paymentType,
												 @Param("showTest") Boolean showTest, @Param("platformId") Long platformId,
												 @Param("page") Pagination page, @Param("refundFlag") boolean refundFlag,
												 @Param("testCompanyIds") List<Long> testCompanyIds);

	Integer queryBackStageSoPageByStoreIdNullTotalSize(@Param("storeId") Long storeId, @Param("orderCode") String orderCode,
													   @Param("userIds") List<Long> userIds,@Param("puIds")  List<Long> puIds,
													   @Param("startDateTime") Date startDateTime, @Param("endDateTime") Date endDateTime,
													   @Param("status") Integer status, @Param("confirmStatus") Integer confirmStatus,
													   @Param("payStatus") Integer payStatus, @Param("paymentType") Integer paymentType,
													   @Param("showTest") Boolean showTest, @Param("platformId") Long platformId,
													   @Param("refundFlag") boolean refundFlag, @Param("testCompanyIds") List<Long> testCompanyIds);*/
}
