package com.egeo.components.order.service.read;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.egeo.components.order.condition.SoExtendsCondition;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoDetailDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoReadService {

	/**
	 * 分页查询用户某个状态的订单
	 * @param userId
	 * @param orderStatus
	 * @param platformId
	 * @param page
	 * @return
	 */
	PageResult<SoDetailDTO> findOrderByUserAndStatus(Long userId, Integer orderStatus,Long platformId,Pagination page);
	public List<SoDetailDTO> findOrderByMonth(String month);
	List<SoDTO> findAllunpayOrders();

	List<SoItemDTO> findSoItemListByOrderCode(Long soId);

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
	List<SoDTO> querySoListByCondition(List<String> orderCodeList, Pagination page, Date startTime, Date endTime,
			Integer orderStatus,String exactOrderCode, Long platformId);

	/**
	 * 条件查询订单列表总数
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
	SoDTO querySoById(Long orderId);

	/**
	 * 根据订单编号查询订单
	 * @param orderCode
	 * @return
	 */
	SoDTO querySoByOrderCode(String orderCode);

	/**
	 * 查询未删除的订单
	 * @param orderCode
	 * @return
	 */
	SoDTO queryUndeleteSoByOrderCode(String orderCode);


	/**
	 * 查询订单列表2.0
	 * @param soDTO
	 * @param page
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	PageResult<SoDTO> findSoOfPage(SoDTO soDTO, Integer cashPayType, Integer useFubi, Date startDateTime,
			Date endDateTime, Pagination page);

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
	PageResult<SoDTO> queryBackStageSoPage(
            Long storeId, String orderCode, List<Long> userIds, List<Long> puIds, Date startDateTime,
            Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus, Integer paymentType,
            Boolean showTest, Long platformId, Pagination page, boolean refundFlag, List<Long> testCompanyIds,
			List<Long> companyIds,Integer auditStatus,List<Long>  soIds);
	/**
	 * 查询所有状态为已发货、已收货，订单自动完成时间为空的订单
	 * @return
	 */
	List<SoDTO> findByOrderStatusNoOrderAutoCompleteDate();
	/**
	 * 查询订单自动完成时间不为空的订单
	 * @return
	 */
	List<SoDTO> findByOrderAutoCompleteDateNoNull();

	/**
	 * 合并被拆分的订单项
	 * @param soItemDTOList
	 * @return
	 */
	List<SoItemDTO> mergeSoItems(List<SoItemDTO> soItemDTOList);

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
	Integer findCurrentMonthOrder(Long storeId,Long platformId);
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

    List<SoDTO> findSoByCode(String orderCode);

    SoDTO findSoById(Long orderId);

    Long findSoSum(SoDTO soDTO);

    /**
     * 根据puId支付状态 查询订单
     * @param puId
     * @return
     */
    List<SoDTO> findSoDTOByPuId(Long puId,Integer status);

	List<SoDTO> findSoDTOByPuId(List<Long> puIds, Integer status);

    SoDTO findSoByThirdpartyId(String jdOrderId);

	PageResult<SoExtendsCondition> getOrderByUserAndStatus(Long userId, Integer orderStatus, Long platformId, Pagination page);

	List<SoDTO> getSoByIds(List<Long> ids);

	List<SoDTO> queryBackStageSoList(
			Long storeId, String orderCode, List<Long> userIds, List<Long> puIds, Date startDateTime,
			Date endDateTime, Integer status, Integer confirmStatus, Integer payStatus, Integer paymentType,
			Boolean showTest, Long platformId, Pagination page, boolean refundFlag, List<Long> testCompanyIds,
			List<Long> companyIds,Integer auditStatus,List<Long>  soIds);
}
