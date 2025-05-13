package com.egeo.components.order.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.order.dto.*;
import com.egeo.components.order.vo.*;
import com.egeo.components.order.vo.jd.SkuInfo;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface SoManage {

	JsonResult<Map<String,Object>> createOrder(HttpServletRequest req, CreateOrderDTO orderDTO);

	/**
	 * 创建订单
     * @param deliveryMethod
* @param skus
     * @param invoiceType
     * @param invoiceTitleType
     * @param invoiceTitle
     * @param taxNo
     * @param req
     * @param puIdList
     * @param exchangeId
     * @param exchangeCouponUnitId
     * @param exchangeCouponBatchId
     * @param orderType
     * @param receiveAddressId
     * @param remark
     * @param userId
     * @param platformId     @return                                                      */
	JsonResult<Map<String,Object>> createOrder(HttpServletRequest req, List<Long> puIdList, Long exchangeId, Long exchangeCouponUnitId, Long exchangeCouponBatchId, Integer orderType, Long storeId, Long receiveAddressId, Integer type,
                                               String cartItemIds, Long puId, Integer num, Integer useFubi, String remark, Long invoiceId, Long userId,
                                               Long platformId, String deviceId, Integer orderChannel, String ip, String os, String phoneModel,
                                               String versionCode, String userName, String mac, Long companyId, String phone, Integer couponType, Long couponUnitId, String deliveryPrice,String channelProductId,Integer source,String thirdOrderJsonStr);

	/**
	 * 取消订单
	 * @param orderId
	 * @param userId
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String,Object>> cancelOrder(String orderCode, Long userId,Long platformId,String ip,
			String userName,String mac, HttpServletRequest req);

	/**
	 * 查询用户不同状态的订单
	 * @param userId
	 * @param orderStatus
	 * @param platformId
	 * @param page
	 * @return
	 */
	PageResult<SoDetailVO> findOrderByUserAndStatus(Long userId, Integer orderStatus,Long platformId,
			Long f, Long clientId, Pagination page);

//	/**
//	 * 分页查询订单列表
//	 * @param pageNo
//	 * @param pageSize
//	 * @param orderCode
//	 * @param goodsName
//	 * @param startTime
//	 * @param endTime
//	 * @param orderStatus
//	 * @return
//	 */
//	@Deprecated
//	PageResult<SoListBackVo> backOrderList(Integer pageNo,Integer pageSize,String orderCode,
//			String merchantName,Long startTime,Long endTime,
//			Integer orderStatus,Long platformId);

	/**
	 * 商品确认
	 * @param type
	 * @param cartItemIds
	 * @param merchantId
	 * @param num
	 * @param clientId
	 * @param req
	 * @return
	 */
	JsonResult<Map<String, Object>> orderConfirm(Long storeId,Integer type, String cartItemIds, Long puId, Integer num,
			Long addrId,Long memberId,Long platformId,Long companyId,String phone, Integer couponType, Long couponUnitId, Long clientId,String channelProductId,Integer source);

	JsonResult<Map<String, Object>> queryOrderPayInfo(String soCode);
	/**
	 * 后台查询订单详情
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> backOrderDetail(Long orderId,Long platformId);

	/**
	 * 枚举订单状态
	 * @return
	 */
	JsonResult<List<Map<String, String>>> orderStatusList();

	/**
	 * 修改订单状态
	 * @param orderId
	 * @param status
	 * @return
	 */
	JsonResult<Map<String, Object>> updateOrderStatus(String orderCode, Integer status);

	/**
	 * 修改订单信息(主要是各项金额)
	 * @param orderId
	 * @param orderAmount
	 * @param deliveryAmount
	 * @param payMoney
	 * @return
	 */
	JsonResult<Map<String, Object>> updateOrderInfo(Long orderId, BigDecimal orderAmount, BigDecimal deliveryAmount,
			BigDecimal payMoney);

	/**
	 * 修改收货信息
	 * @param orderId
	 * @param receiverName
	 * @param receiverPhone
	 * @param receiverAddress
	 * @return
	 */
	JsonResult<Map<String, Object>> updateDeliveryInfo(Long orderId, String receiverName, String receiverPhone,
			String receiverAddress);
	/**
	 * 根据订单id查询订单项信息
	 * @param soId
	 * @return
	 */
	JsonResult<List<SoDetailItemVo>> soDetailItemVoBySoId(Long soId, Long packId, Long platformId);

	/**
	 * 删除订单
	 * 改变订单删除标志位
	 * @param soId
	 * @return
	 */
	JsonResult<Map<String, Object>> deleteByOrderCode(String orderCode,Long userId);
	/**
	 * 确认收货
	 * @param soId
	 * @param platformId
	 * @param userId
	 * @return
	 */
	String affirmOrderBySoId(String orderCode, Long platformId, Long userId);

	/**
	 * 订单支付确认
	 * @param orderCode
	 * @return
	 */
	JsonResult<Map<String, Object>> orderPayConfirm(String orderCode);

	/**
	 * 客户端查询订单详情
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> orderDetail(String orderCode,Long platformId, Long userId, Long f, Long clientId);

	/**
	 * 根据订单id修改订单收货人信息
	 * @param soVO
	 * @param req
	 * @return
	 */
	JsonResult<String> updateOrderByOrderId(SoVO soVO);

	/**
	 * 查询订单剩余支付时间
	 * @param orderId
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> orderPayEndTime(String orderId);

	/**
	 * 后台查询订单2.0
     * @param soDTO
     * @param endTime2
     * @param page
     * @param endTime
     * @param paymentType
     * @param platformId    @return
     * */
	JsonResult<Map<String,Object>> orderList(OrderSearchVO orderSearchVO);


	PageResult<SoDTO> queryBackStageSoPage(OrderSearchVO orderSearchVO);

	/**
	 * 查询订单详情2.0
	 * @param orderId
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> findOrderDetail(Long orderId, Long platformId);

	/**
	 * 查询所有子订单
	 * @param soChildDTO
	 * @param page
	 * @return
	 */
	JsonResult<Map<String, Object>> findAllSOChild(SoChildDTO soChildDTO, Pagination page);

	/**
	 * 导出子订单的接口
	 * @param soChildIdArr
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String,Object>> exportSoChild(Long orderId, Long platformId);

	/**
	 * 后台查询子单详情
	 * @param soChildDTO
	 * @return
	 */
	JsonResult<Map<String, Object>> findSochildById(Long id);

	/**
	 * 查询pu和收获地址的信息
	 * @param sochildId
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> puInfoForOpenOrder(Long soChildId, Long platformId);

	/**
	 * 查询该子订单的所属用户的收货地址列表
	 * @param sochildId
	 * @param platformId
	 * @return
	 */
//	JsonResult<Map<String, Object>> userReceiverInfos(Long sochildId, Long platformId);

	/**拆单
	 * @param sochildId
	 * @param puIdArr
	 * @param orChangeReceiveInfo
	 * @param receiverAddressDTO
	 * @return
	 */
	JsonResult<Map<String,Object>> openOrder(Long sochildId, String puIdArr,Long userId);

	/**
	 * 母订单分拣
	 * @param soIdArrStr
	 * @param platformId
	 * @return
	 */
	JsonResult<String> soSort(String soIdArrStr, Long platformId);

	/**
	 * 查询财务信息
	 * @param soDTO
	 * @return
	 */
	JsonResult<Map<String, Object>> findFinanceBySoId(SoDTO soDTO);

	/**
	 * 查询订单退款信息
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> soRefundInfo(Long orderId);

	/**
	 * 更新子订单收货信息
	 * @param soChildId
	 * @param receiverName
	 * @param receiverMobile
	 * @param provinceId
	 * @param province
	 * @param cityId
	 * @param city
	 * @param countyId
	 * @param county
	 * @param address
	 * @return
	 */
	JsonResult<Map<String, Object>> updateReceiverInfo(Long soChildId,
			Long receiverAddressId,
			String receiverName,
			String receiverMobile,
			Long provinceId,
			Long cityId,
			Long countyId,
			String address
			,Long platformId,Long operatorId);


	/**
	 * 获取订单收货信息
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> receiverInfos(Long orderId);

	/**
	 * 售后服务信息列表
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> customerServices(Long orderId);

	/**
	 * 保存运营备注
	 * @param soChildId
	 * @param operatorRemark
	 * @return
	 */
	JsonResult<Map<String, Object>> saveOperatorRemark(Long soChildId, String operatorRemark);

	/**
	 * 订单操作流水列表
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> soOpFlow(Long orderId);

	/**
	 * 子订单操作流水列表
	 * @param soChildId
	 * @return
	 */
	JsonResult<Map<String, Object>> soChildOpFlow(Long soChildId);

	/**
	 * 订单退款
	 * @param orderId
	 * @param refundCash
	 * @param refundFubi
	 * @param reason
	 * @param platformId
	 * @param operatorId
	 * @return
	 */
	JsonResult<Map<String, Object>> soRefund(Long orderId, Double refundCash, Double refundFubi, String reason,
			Long platformId, Long operatorId, HttpServletRequest req,Double refundJiDian);

	JsonResult<Map<String, Object>> soRefund(RefundVo refundVo, HttpServletRequest req);
	/**
	 * 查询订单退款详情
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> refundDetail(Long orderId);

	/**
	 * 订单分拣导出
	 * @param orderIds
	 * @param operatorId
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String, Object>> orderSortExport(String orderIds, Long operatorId, Long platformId);

	/**
	 * 订单信息导出
	 * @param orderIds
	 * @return
	 */
	JsonResult<Map<String, Object>> orderExport(String orderIds);

	/**
	 * 子订单导出
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> soChildExport(Long orderId);

	/**
	 * 导出订单操作流水
	 * @param orderId
	 * @return
	 */
	JsonResult<Map<String, Object>> operateFlowExport(Long orderId);

	/**
	 * 分拣导出日志查询
	 * @param parseArray
	 * @return
	 */
	Map<String, Object> getOrderInfoByOrderIds(List<Long> parseArray);

	/**
	 * 通过订单id查询订单信息
	 * @param orderId
	 * @return
	 */
	SoDTO findOrderById(Long orderId);

	/**
	 * 通过订单编号查询订单信息
	 * @param orderCode
	 * @return
	 */
	SoDTO querySoByOrderCode(String orderCode);

	/**
	 * 通过子订单id查询收货地址
	 * @param soChildId
	 * @return
	 */
	ReceiverAddressDTO queryReceiverAddressBySoChildId(Long soChildId);

	SoChildDTO querySoChildById(Long id);

	/**
	 * 通过收货地址查询收获地址信息
	 * @param receiverAddressId
	 * @return
	 */
	ReceiverAddressDTO queryReceiverAddressById(Long receiverAddressId);

	/**
	 * 查询用户订单/优惠卷的统计信息
	 * @param id
	 * @return
	 */
	JsonResult<Map<String, Object>> queryUserStatisticalInfo(Long id,Long platformId);

	JsonResult<Map<String,Object>> getSoChildAllReceiverInfoPage(Long soChildId, Long platformId,Long pageNum);


	JsonResult<Object> receiverAddressById(Long updateAddressId, Long platformId);

	JsonResult<Map<String,Object>> updateAddressCreateByBackStage(Long soChildId, Long updateAddressId, String receiverName, String receiverMobile, Long provinceId, Long cityId, Long countyId, String address, Long platformId, Long memberId);

    JsonResult<Map<String,Object>> soChildAllList(Integer merchantId, String soChildCode, String mail, String puName, Date soCreateTimeStart, Date soCreateTimeEnd, Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus, Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Pagination page, Long platformId,Long supplierId,Integer orderPayStatus,Integer auditStatus);

    void receviedConfirm(Long orderId, Long operatorId, Long platformId);

	void sendHostoryConfirm(Long orderId, Long operatorId, Long platformId);

    List<SoDTO> findSoByCode(String orderCode);

    void writeDeliveryDate(Long orderId,Long platformId,Date date);

    JsonResult<Long> getUnPayNum(Long storeId, Long platformId,Long userId);

	JsonResult<Long> getPaiedNum(Long storeId, Long platformId,Long userId);

	JsonResult<Long> getSendedNum(Long storeId, Long platformId,Long userId);

	void repairOrderDataWithTx();

    int updateCouponUnitLockedByCouponUnitId(Long exchangeCouponUnitId);

    CartItemDTO findCartItemByItemId(Long itemId);

    CouponUnitDTO findCouponUnitById(Long exchangeCouponUnitId);

	Map<String,String> getDeliveryPriceFromJd(String address, List<SkuInfo> skuInfoList);

    JsonResult<Map<String,Object>> thirdpartyOrderExport(List<Long> idList, Long merchantId);

	Map<String, Object> getOrderInfoByOrderChildIds(List<Long> orderIds);


	JsonResult<Map<String, Object>> orderChildSortExport(String orderChildIds, Long operatorId, Long platformId,
			Long supplierId);

	PageResult<SoDetailVO> getOrderByUserAndStatus(Long userId, Integer orderStatus, Long platformId,
												   Long f, Long clientId, Pagination page);

	JsonResult<Map<String, Object>> childOrderDetail(String orderCode, Long platformId, Long userId,
													 Long f, Long clientId);

	/**
	 * 取消子订单
	 * @param orderCode
	 * @param userId
	 * @param platformId
	 * @return
	 */
	JsonResult<Map<String,Object>> cancelChildOrder(String orderCode, Long userId,Long platformId,String ip,
											   String userName,String mac, HttpServletRequest req);

	/**
	 * 删除订子订单单
	 * 改变订单删除标志位
	 * @param orderCode
	 * @return
	 */
	JsonResult<Map<String, Object>> deleteByChildOrderCode(String orderCode,Long userId);

	String affirmOrderByChildCode(String orderCode, Long platformId, Long userId);

	JsonResult<Map<String,Object>> onlyCancelChildOrder(String orderCode, Long userId,Long platformId,String ip,
													String userName,String mac, HttpServletRequest req);

	/**
	 * 商品确认
	 * @param type
	 * @param cartItemIds
	 * @param num
	 * @param clientId
	 * @return
	 */
	JsonResult<Map<String, Object>> orderConfirmNew(Long storeId,Integer type, String cartItemIds, Long puId, Integer num,
												 Long addrId,Long memberId,Long platformId,Long companyId,String phone, Integer couponType, Long couponUnitId, Long clientId,String channelProductId,Integer source);


	/**
	 * 创建订单
	 * @param deliveryMethod
	 * @param skus
	 * @param invoiceType
	 * @param invoiceTitleType
	 * @param invoiceTitle
	 * @param taxNo
	 * @param req
	 * @param puIdList
	 * @param exchangeId
	 * @param exchangeCouponUnitId
	 * @param exchangeCouponBatchId
	 * @param orderType
	 * @param receiveAddressId
	 * @param remark
	 * @param userId
	 * @param platformId     @return                                                      */
	JsonResult<Map<String,Object>> createOrderNew(HttpServletRequest req, List<Long> puIdList, Long exchangeId, Long exchangeCouponUnitId, Long exchangeCouponBatchId, Integer orderType, Long storeId, Long receiveAddressId, Integer type,
											   String cartItemIds, Long puId, Integer num, Integer useFubi, String remark, Long invoiceId, Long userId,
											   Long platformId, String deviceId, Integer orderChannel, String ip, String os, String phoneModel,
											   String versionCode, String userName, String mac, Long companyId, String phone, Integer couponType, Long couponUnitId, String deliveryPrice,String channelProductId,Integer source,String thirdOrderJsonStr);


	public JsonResult<Map<String, Object>> soChildBatchExport(List<Long> ids);

	JsonResult<Map<String, Object>> orderSearchExport(OrderSearchVO searchVO);

	JsonResult<Map<String, Object>> soChildAllListToExport(Integer merchantId, String soChildCode, String mail,
														   String puName, Date soCreateTimeStart, Date soCreateTimeEnd,
														   Integer soType, Integer soChildDeliveryStatus, Integer soConfirmStatus,
														   Date sendTimeStart, Date sendTimeEnd, Boolean showTest, Pagination page,
														   Long platformId,Long supplierId,Integer orderPayStatus,Integer auditStatus);

	public JsonResult<BuyCardUseCheckRespVO> buyCardUseCheck(BuyCardUseCheckReqVO vo);

	public JsonResult<CardPayCanUseRespVO> findCanUserCards(CardPayCanUseReqVO vo);

	public BuyCardPayRespVO buyCardPay(BuyCardPayReqVO vo);

	public JsonResult<Integer> buyCardUseCheckNum(BuyCardUseCheckReqVO vo);
}
