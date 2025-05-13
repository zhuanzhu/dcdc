package com.egeo.components.order.manage.write.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.egeo.components.utils.FHCollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.client.AccountFlowClient;
import com.egeo.components.order.business.impl.SoThirdpartyManageImpl;
import com.egeo.components.order.condition.NewSoItemCondition;
import com.egeo.components.order.dao.read.LimitRuleReadDAO;
import com.egeo.components.order.dao.read.SoChildReadDAO;
import com.egeo.components.order.dao.read.SoInvoiceReadDAO;
import com.egeo.components.order.dao.read.SoItemReadDAO;
import com.egeo.components.order.dao.read.SoReadDAO;
import com.egeo.components.order.dao.write.CartItemWriteDAO;
import com.egeo.components.order.dao.write.LimitRuleRecordWriteDAO;
import com.egeo.components.order.dao.write.SoChildWriteDAO;
import com.egeo.components.order.dao.write.SoDeviceWriteDAO;
import com.egeo.components.order.dao.write.SoInvoiceWriteDAO;
import com.egeo.components.order.dao.write.SoItemWriteDAO;
import com.egeo.components.order.dao.write.SoThirdpartyWriteDAO;
import com.egeo.components.order.dao.write.SoWriteDAO;
import com.egeo.components.order.manage.write.SoWriteManage;
import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.components.order.po.SoDevicePO;
import com.egeo.components.order.po.SoInvoicePO;
import com.egeo.components.order.po.SoItemPO;
import com.egeo.components.order.po.SoPO;
import com.egeo.components.order.po.SoThirdpartyPO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.JsonResult;
import org.springframework.util.CollectionUtils;

@Service
public class SoWriteManageImpl implements SoWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoWriteDAO soWriteDAO;
	@Autowired
	private SoChildWriteDAO soChildWriteDAO;

	@Autowired
	private SoDeviceWriteDAO soDeviceWriteDAO;

	@Autowired
	private SoItemWriteDAO soItemWriteDAO;

	@Autowired
	private CartItemWriteDAO cartItemWriteDao;

	@Autowired
	private SoInvoiceWriteDAO soInvoiceWriteDAO;

	@Autowired
	private SoInvoiceReadDAO soInvoiceReadDAO;

	@Autowired
	private LimitRuleReadDAO limitRuleReadDAO;

	@Autowired
	private LimitRuleRecordWriteDAO limitRuleRecordWriteDAO;

	@Autowired
	private SoThirdpartyWriteDAO soThirdpartyWriteDAO;

	@Autowired
	private SoReadDAO soReadDAO;
	@Autowired
	private SoChildReadDAO soChildReadDAO;
	@Autowired
	private SoItemReadDAO soItemReadDAO;
	@Autowired
	private AccountFlowClient accountFlowClient;
	@Override
	public Long createOrderWithTx(
			SoPO soPo, List<NewSoItemCondition> newSoItemConditions,
			List<Long> cartItemIds, List<SoChildPO> soChildPOList,
			SoDevicePO soDevicePO,
			List<LimitRuleRecordPO> limitRuleRecordList,
			SoThirdpartyPO soThirdpartyPO,
			Long companyAllId) {
		// 插入订单
		soWriteDAO.insert(soPo);
		Long soId = soPo.getId();
		// 插入子订单
		for(SoChildPO soChildPO:soChildPOList){
			soChildPO.setSoId(soId);
			soChildWriteDAO.insert(soChildPO);


		}

//		soChildPO.setSoId(soId);
//		soChildWriteDAO.insert(soChildPO);
//		Long soChildId = soChildPO.getId();
		// 插入第三方订单
		for(SoChildPO po:soChildPOList){
			if (!po.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_NONE)) {
				if(po.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_CAKE) || po.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_WORLD)){
					soThirdpartyPO.setSoChildCode(po.getChildCode());
				}
				// 第三方订单类型为话费充值或者京东
				soThirdpartyPO.setSoChildId(po.getId());
				soThirdpartyWriteDAO.insert(soThirdpartyPO);
			}
		}
		// 插入订单项
		for (NewSoItemCondition newSoItemCondition : newSoItemConditions) {
			SoItemPO soItemPO = new SoItemPO();
			BeanUtils.copyProperties(newSoItemCondition,soItemPO);
			soItemPO.setSoId(soId);
			//为soitem设置sochildId
			boolean flag = false;
			for(SoChildPO soChildPO:soChildPOList){
				if(soChildPO.getChildCode().equals(newSoItemCondition.getSoChildCode())){
					flag = true;
					soItemPO.setSoChildId(soChildPO.getId());
				}
			}
			if(!flag){
				throw new BusinessException("为soitem分配的childcode有误");
			}


			soItemWriteDAO.insert(soItemPO);
		}
		// 生成限购记录
		if(EmptyUtil.isNotEmpty(limitRuleRecordList))
			saveLimitRuleRecord(limitRuleRecordList,soPo.getOrderCode());

		// 更新发票
		Long invoiceId = soPo.getInvoiceId();
		if (invoiceId != null) {
			SoInvoicePO updInv = new SoInvoicePO();
			updInv.setId(soPo.getInvoiceId());
			updInv.setSoId(soId);
			updInv.setSoChildId(soChildPOList.get(0).getId());
			soInvoiceWriteDAO.update(updInv);

			// 插入母订单的发票快照
			SoInvoicePO soInvoicePO = soInvoiceReadDAO.findById(updInv);
			soInvoicePO.setSoChildId(-1L);
			soInvoicePO.setSoId(soId);
			soInvoiceWriteDAO.insert(soInvoicePO);
		}
		// 插入订单设备
		soDevicePO.setOrderId(soId);
		soDeviceWriteDAO.insert(soDevicePO);
		//批量删除购物车项
		if (cartItemIds!=null && cartItemIds.size() > 0)
			cartItemWriteDao.batchDeleteCartItemsByIds(cartItemIds);
		return soId;
	}

	@Override
	public Long createOrderNewWithTx(SoPO soPo, List<NewSoItemCondition> newSoItemConditions, List<Long> cartItemIds, List<SoChildPO> soChildPOList, SoDevicePO soDevicePO,
							  List<LimitRuleRecordPO> limitRuleRecordList, List<SoThirdpartyPO> soThirdpartyPOList, Long companyAllId){

// 插入订单
		soWriteDAO.insert(soPo);
		Long soId = soPo.getId();
		// 插入子订单
		for(SoChildPO soChildPO:soChildPOList){
			soChildPO.setSoId(soId);
			soChildWriteDAO.insert(soChildPO);


		}

//		soChildPO.setSoId(soId);
//		soChildWriteDAO.insert(soChildPO);
//		Long soChildId = soChildPO.getId();
		Map<String,SoThirdpartyPO> stringSoThirdpartyPOMap = FHCollectionUtils.listToMap(soThirdpartyPOList,SoThirdpartyPO::getSoChildCode, e->e);
		// 插入第三方订单
		for(SoChildPO po:soChildPOList){
			if (!po.getThirdpartyType().equals(SoThirdpartyManageImpl.THIRDPARTY_TYPE_NONE)) {
				SoThirdpartyPO soThirdpartyPO =CollectionUtils.isEmpty(stringSoThirdpartyPOMap)?null:stringSoThirdpartyPOMap.get(po.getChildCode());
				if(Objects.isNull(soThirdpartyPO)){
					continue;
				}
				// 第三方订单类型为话费充值或者京东
				soThirdpartyPO.setSoChildId(po.getId());
				soThirdpartyWriteDAO.insert(soThirdpartyPO);
			}
		}
		// 插入订单项
		for (NewSoItemCondition newSoItemCondition : newSoItemConditions) {
			SoItemPO soItemPO = new SoItemPO();
			BeanUtils.copyProperties(newSoItemCondition,soItemPO);
			soItemPO.setSoId(soId);
			//为soitem设置sochildId
			boolean flag = false;
			for(SoChildPO soChildPO:soChildPOList){
				if(soChildPO.getChildCode().equals(newSoItemCondition.getSoChildCode())){
					flag = true;
					soItemPO.setSoChildId(soChildPO.getId());
				}
			}
			if(!flag){
				throw new BusinessException("为soitem分配的childcode有误");
			}


			soItemWriteDAO.insert(soItemPO);
		}
		// 生成限购记录
		if(EmptyUtil.isNotEmpty(limitRuleRecordList))
			saveLimitRuleRecord(limitRuleRecordList,soPo.getOrderCode());

		// 更新发票
		Long invoiceId = soPo.getInvoiceId();
		if (invoiceId != null) {
			SoInvoicePO updInv = new SoInvoicePO();
			updInv.setId(soPo.getInvoiceId());
			updInv.setSoId(soId);
			updInv.setSoChildId(soChildPOList.get(0).getId());
			soInvoiceWriteDAO.update(updInv);

			// 插入母订单的发票快照
			SoInvoicePO soInvoicePO = soInvoiceReadDAO.findById(updInv);
			soInvoicePO.setSoChildId(-1L);
			soInvoicePO.setSoId(soId);
			soInvoiceWriteDAO.insert(soInvoicePO);
		}
		// 插入订单设备
		soDevicePO.setOrderId(soId);
		soDeviceWriteDAO.insert(soDevicePO);
		//批量删除购物车项
		if (cartItemIds!=null && cartItemIds.size() > 0)
			cartItemWriteDao.batchDeleteCartItemsByIds(cartItemIds);
		return soId;

	}

	/**
	 * 批量保存su限购记录
	 * @param goodsList
	 * @return
	 */
	public boolean saveLimitRuleRecord(List<LimitRuleRecordPO> goodsList,String orderCode) {

		for(LimitRuleRecordPO po:goodsList){
			po.setOrderCode(orderCode);
			//限购商品处于冻结状态
			po.setOrderStatus(Integer.valueOf(1));
		}
		// 批量添加限购记录信息
		if(EmptyUtil.isNotEmpty(goodsList))
			limitRuleRecordWriteDAO.saveLimitRuleRecords(goodsList);
		return true;
	}

	@Override
	public boolean changeOrderStatusByOrderCodeWithTx(String orderCode, Integer status,Integer confirmStatus,
			Integer payStatus,Integer deliveryStatus) {
		// 取消订单
		soWriteDAO.updateOrderStatus(orderCode, status,confirmStatus,payStatus,deliveryStatus,null);
		if(EmptyUtil.isNotEmpty(orderCode)){
			limitRuleRecordWriteDAO.updateOrderStatus(orderCode,Integer.valueOf(0));
		}
		return true;

	}

	@Override
	public boolean changeOrderStatusByOrderIdWithTx(String orderCode, Long id, Integer status, Integer confirmStatus,
													Integer payStatus, Integer deliveryStatus) {
		// 取消订单
		soWriteDAO.updateOrderStatusById(id, status,confirmStatus,payStatus,deliveryStatus,null);
		//修改限购记录
		if(EmptyUtil.isNotEmpty(orderCode)){
			limitRuleRecordWriteDAO.updateOrderStatus(orderCode,Integer.valueOf(0));
		}
		return true;

	}

	@Override
	public int updateOrderMoneyInfoWithTx(Long orderId, BigDecimal orderAmount, BigDecimal deliveryAmount,
			BigDecimal payMoney) {
		SoPO po = new SoPO();
		po.setId(orderId);
		po.setOrderAmount(orderAmount);
		po.setDeliveryFee(deliveryAmount);
		// po.setOrderPaidByAccount(payMoney);
		return soWriteDAO.update(po);
	}

	@Override
	public int updateDeliveryInfoWithTx(Long orderId, String receiverName, String receiverPhone,
			String receiverAddress) {
		SoPO po = new SoPO();
		po.setId(orderId);
		// po.setReceiverAddress(receiverAddress);
		// po.setReceiverName(receiverName);
		// po.setReceiverPhone(receiverPhone);
		return soWriteDAO.update(po);
	}

	@Override
	public int updateOrderPaymentInfoWithTx(SoPO po) {
		logger.info("更新订单信息:{}",JSON.toJSONString(po));
		int i=soWriteDAO.update(po);
		logger.info("订单号:{}订单id:{},订单状态:",po.getOrderCode(),po.getId(),po.getOrderStatus());
		if(EmptyUtil.isNotEmpty(po.getOrderCode())&&po.getOrderStatus()==OrderConstant.ORDER_STATUS_UNPAY.getStatus()){
			//代付款时,冻结
			limitRuleRecordWriteDAO.updateOrderStatus(po.getOrderCode(),Integer.valueOf(1));
		}else if(EmptyUtil.isNotEmpty(po.getOrderCode())&&po.getOrderStatus()==OrderConstant.ORDER_STATUS_CANCELED.getStatus()){
			//代付款时,冻结
			limitRuleRecordWriteDAO.updateOrderStatus(po.getOrderCode(),Integer.valueOf(0));
		}else if(EmptyUtil.isNotEmpty(po.getOrderCode())){
			//已付款的
			logger.info("订单号:{}订单id:{},付款成功:",po.getOrderCode(),po.getId(),po.getOrderStatus());
			limitRuleRecordWriteDAO.updateOrderStatus(po.getOrderCode(),Integer.valueOf(2));

		}

		if(EmptyUtil.isNotEmpty(po.getOrderCode())&&(po.getOrderStatus()==OrderConstant.ORDER_STATUS_PAYED.getStatus()||
				po.getOrderStatus()==OrderConstant.ORDER_STATUS_DELIVERED.getStatus()||
				po.getOrderStatus()==OrderConstant.ORDER_STATUS_RECEIVED_GOODS.getStatus()|
						po.getOrderStatus()==OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus())) {
			accountFlowClient.orderConfirm(po.getOrderCode());
		}
		if(EmptyUtil.isNotEmpty(po.getOrderCode())&&(po.getOrderStatus()==OrderConstant.ORDER_STATUS_RETURN_CASH_FINISHED.getStatus())) {
			accountFlowClient.orderRefund(po.getOrderCode());
		}
		return i;
	}

	@Override
	public int deleteByOrderCode(String orderCode, Long userId) {
		// 此处设置为2，2、为不能复原订单 1、为可以复原订单
		return soWriteDAO.deleteByOrderCode(orderCode, userId, 2);

	}

	@Override
	public String affirmOrderBySoId(Long orderId, Long platformId) {
		logger.info("affirmOrderBySoId:"+orderId);
		SoPO po = new SoPO();
		po.setId(orderId);
		// 状态4为已完成
		po.setOrderStatus(OrderConstant.ORDER_STATUS_RECEIVED_FINISHED.getStatus());
		po.setOrderConfirmStatus(3);
		po.setPlatformId(platformId);
		int i = soWriteDAO.update(po);
		if (i != 0) {
			return "确认订单成功";
		} else {
			throw new BusinessException("确认订单失败");
		}
	}

	/**
	 * 根据订单id修改订单
	 *
	 * @param soVO
	 * @param req
	 * @return
	 */
	@Override
	public JsonResult<String> updateOrderByOrderId(SoPO po) {
		int update = soWriteDAO.update(po);
		if (update != 0) {
			return JsonResult.success("根据订单id修改订单成功");
		}
		return JsonResult.fail("根据订单id修改订单失败");
	}
	@Override
	public JsonResult<String> updateOrderByOrderIdWithTx(SoPO po) {
		int update = soWriteDAO.update(po);
		if (update != 0) {
			return JsonResult.success("根据订单id修改订单成功");
		}
		return JsonResult.fail("根据订单id修改订单失败");
	}
	@Override
	public int SoSort(List<Long> soIdArr, Long platformId) {

		//更改子订单物流状态
		//等一下  这个方法好像已经废弃了  先注释掉 吧

//		int soSort = soWriteDAO.soSort(soIdArr,platformId);
//
//		Map<Long ,List<SoChildPO>> map = new HashMap<>();
//		for (int i = 0; i < soIdArr.length; i++) {
//			SoChildPO soChildPO = new SoChildPO();
//			soChildPO.setSoId(soIdArr[i]);
//			List<SoChildPO> soChildPOList = soChildReadDAO.findAll(soChildPO);
//			map.put(soIdArr[i], soChildPOList);
//		}
//
//		Set<Long> keySet = map.keySet();
//
//		List<Long> soIdList = new ArrayList<>(keySet);
//
//		for (Long long1 : soIdList) {
//			for (SoChildPO soChildPO : map.get(long1)) {
//				SoPackagePO soPackagePO2 = new SoPackagePO();
//				soPackagePO2.setSoId(long1);
//				soPackagePO2.setSoChildId(soChildPO.getId());
//				List<SoPackagePO> soPackagePOList = soPackageReadDAO.findAll(soPackagePO2);
//				if(EmptyUtil.isEmpty(soPackagePOList)){
//					//插入一条运单记录
//					soPackagePO2.setDeliveryStatus(1);
//					soPackageWriteDAO.insert(soPackagePO2);
//				}
//
//				//记录流水1
//				DeliveryFlowPO deliveryFlowPO = new DeliveryFlowPO();
//				deliveryFlowPO.setSoId(long1);
//				deliveryFlowPO.setSoChildId(soChildPO.getId());
//				deliveryFlowPO.setOperate(0);
//				deliveryFlowWriteDAO.insert(deliveryFlowPO);
//				//记录流水2
//				SoChildFlowPO soChildFlowPO = new SoChildFlowPO();
//				soChildFlowPO.setSoId(long1);
//				soChildFlowPO.setSoChildId(soChildPO.getId());
//				soChildFlowWriteDAO.insert(soChildFlowPO);
//			}
//		}
//		return soSort;
		return 0;
	}

	@Override
	public int orderRefund(SoPO po) {
		return soWriteDAO.orderRefund(po);
	}
	/**
	 * 根据订单id保存订单自动完成时间
	 * @param soDTO2
	 * @return
	 */
	@Override
	public int saveOrderAutoCompleteDate(SoPO po) {
		// TODO Auto-generated method stub
		return soWriteDAO.update(po);
	}

	@Override
	public void updateSoPayByFuBi(Long orderId, BigDecimal soFreezeBalance) {
		soWriteDAO.updateSoPayByFuBi(orderId,soFreezeBalance);
	}

	@Override
	public void repairOrderDataWithTx() {
		List<SoPO> soList = soReadDAO.findAll(new SoPO(),null);
		for (SoPO so : soList) {
			SoChildPO sc = new SoChildPO();
			sc.setSoId(so.getId());
			List<SoChildPO> soChildList = soChildReadDAO.findAll(sc,null);
			SoItemPO sip = new SoItemPO();
			sip.setSoId(so.getId());
			List<SoItemPO> allSoItemList = soItemReadDAO.findAll(sip,null);
			repairSo(so, soChildList, allSoItemList);

			for (SoChildPO soChild : soChildList) {
				SoItemPO si = new SoItemPO();
				si.setSoChildId(soChild.getId());
				List<SoItemPO> soItemList = soItemReadDAO.findAll(si,null);
				repairSoChild(so, soChild, soItemList);
				for (SoItemPO s : soItemList) {
					repairSoItem(soChild, s);
				}
			}
		}
	}

	@Override
	public int updateOrderCancelStatus(String orderCode) {
		return soWriteDAO.updateOrderCancelStatus(orderCode);
	}

	@Override
	public int updateOrderCancelOverStatusByOrderCode(String orderCode) {
		return soWriteDAO.updateOrderCancelOverStatusByOrderCode(orderCode);
	}

	private void repairSoItem(SoChildPO soChild, SoItemPO soItem) {
		SoItemPO updateSoItem = new SoItemPO();
		updateSoItem.setId(soItem.getId());
		BigDecimal childStoreDiscountAmount = new BigDecimal(0);
		BigDecimal childProductAmount = new BigDecimal(0);
		BigDecimal childCouponDiscountAmount = new BigDecimal(0);
		BigDecimal childDeliverFee = new BigDecimal(0);
		BigDecimal childAmount = new BigDecimal(0);
		if (soChild.getStoreDiscount() != null) {
			childStoreDiscountAmount = soChild.getStoreDiscount();
		}
		if (soChild.getProductAmount() != null) {
			childProductAmount = soChild.getProductAmount();
		}
		if (soChild.getCouponDiscount() != null) {
			childCouponDiscountAmount = soChild.getCouponDiscount();
		}
		if (soChild.getDeliveryFee() != null) {
			childDeliverFee = soChild.getDeliveryFee();
		}
		if (soChild.getAmount() != null) {
			childAmount = soChild.getAmount();
		}

		updateSoItem.setDeliveryFeeDiscountAver(new BigDecimal(0));
		BigDecimal price = new BigDecimal(0);
		if (soItem.getPrice() != null) {
			price = soItem.getPrice();
		}
		BigDecimal promotionAver = new BigDecimal(0);
		if (soItem.getPromotionAver() != null) {
			promotionAver = soItem.getPromotionAver();
		}
		if (childProductAmount.subtract(childCouponDiscountAmount).compareTo(new BigDecimal(0)) == 0) {
			updateSoItem.setStoreDiscountAver(new BigDecimal(0));
		} else {
			updateSoItem.setStoreDiscountAver(childStoreDiscountAmount.multiply(price.subtract(promotionAver)).divide(childProductAmount.subtract(childCouponDiscountAmount), 2, BigDecimal.ROUND_DOWN));
		}
		BigDecimal storeDiscountAver = new BigDecimal(0);
		if (soItem.getStoreDiscountAver() != null) {
			storeDiscountAver = soItem.getStoreDiscountAver();
		}
		updateSoItem.setAfterDiscountPriceAver(price.subtract(promotionAver).subtract(storeDiscountAver));
		BigDecimal afterDiscountPriceAver = new BigDecimal(0);
		if (soItem.getAfterDiscountPriceAver() != null) {
			afterDiscountPriceAver = soItem.getAfterDiscountPriceAver();
		}
		if (childAmount.compareTo(new BigDecimal(0)) == 0) {
			updateSoItem.setDeliveryFeeAver(new BigDecimal(0));
		} else {
			updateSoItem.setDeliveryFeeAver(childDeliverFee.multiply(afterDiscountPriceAver).divide(childAmount, 2, BigDecimal.ROUND_DOWN));
		}
		soItemWriteDAO.update(updateSoItem);
	}

	private void repairSoChild(SoPO so, SoChildPO soChild, List<SoItemPO> soItemList) {
		SoChildPO updateSoChild = new SoChildPO();
		updateSoChild.setId(soChild.getId());
		BigDecimal childProductAmount = new BigDecimal(0);
		BigDecimal childCouponDiscount = new BigDecimal(0);
		for (SoItemPO si : soItemList) {
			BigDecimal siPrice = new BigDecimal(0);
			BigDecimal puCount = new BigDecimal(0);
			BigDecimal finalPromotionAver = new BigDecimal(0);
			if (si.getPrice() != null) {
				siPrice = si.getPrice();
			}
			if (si.getPuCount() != null) {
				puCount = BigDecimal.valueOf(si.getPuCount());
			}
			if (si.getFinalPromotionAver() != null) {
				finalPromotionAver = si.getFinalPromotionAver();
			}
			childProductAmount = childProductAmount.add(siPrice.multiply(puCount));
			childCouponDiscount = childCouponDiscount.add(finalPromotionAver);
		}
		updateSoChild.setProductAmount(childProductAmount);
		updateSoChild.setDeliveryFeeDiscount(new BigDecimal(0));
		updateSoChild.setCouponDiscount(childCouponDiscount);
		BigDecimal soProductAmount = new BigDecimal(0);
		if (so.getProductAmount() != null) {
			soProductAmount = so.getProductAmount();
		}
		BigDecimal soCouponDiscount = new BigDecimal(0);
		if (so.getCouponDiscount() != null) {
			soCouponDiscount = so.getCouponDiscount();
		}
		if (soProductAmount.subtract(soCouponDiscount).compareTo(new BigDecimal(0)) == 0) {
			updateSoChild.setStoreDiscount(new BigDecimal(0));
		} else {
			updateSoChild.setStoreDiscount(so.getStoreDiscount().multiply(childProductAmount.subtract(childCouponDiscount)).divide(soProductAmount.subtract(soCouponDiscount), 2, BigDecimal.ROUND_DOWN));
		}
		if (updateSoChild.getCouponDiscount().compareTo(new BigDecimal(0)) == 1) {
			updateSoChild.setCouponId(so.getCouponUnitId());
		}
		soChildWriteDAO.update(updateSoChild);
	}

	private void repairSo(SoPO so, List<SoChildPO> soChildList, List<SoItemPO> allSoItemList) {
		SoPO updateSo = new SoPO();
		updateSo.setId(so.getId());
		BigDecimal soOrderAmountPay = new BigDecimal(0);
		BigDecimal soCouponDiscount = new BigDecimal(0);
		BigDecimal soDeliveryFee = new BigDecimal(0);
		BigDecimal soDeliveryFeeDiscount = new BigDecimal(0);
		for (SoItemPO si : allSoItemList) {
			BigDecimal finalPromotionAver = new BigDecimal(0);
			if (si.getFinalPromotionAver() != null) {
				finalPromotionAver = si.getFinalPromotionAver();
			}
			soCouponDiscount = soCouponDiscount.add(finalPromotionAver);
		}
		for (SoChildPO sc : soChildList) {
			BigDecimal amount = new BigDecimal(0);
			BigDecimal deliveryFee = new BigDecimal(0);
			if (sc.getAmount() != null) {
				amount = sc.getAmount();
			}
			if (sc.getDeliveryFee() != null) {
				deliveryFee = sc.getDeliveryFee();
			}
			soOrderAmountPay = soOrderAmountPay.add(amount).add(deliveryFee);
			soDeliveryFee = soDeliveryFee.add(deliveryFee);
		}
		BigDecimal soProductAmount = new BigDecimal(0);
		if (so.getProductAmount() != null) {
			soProductAmount = so.getProductAmount();
		}
		updateSo.setOrderAmountPay(soOrderAmountPay);
		updateSo.setCouponDiscount(soCouponDiscount);
		updateSo.setDeliveryFee(soDeliveryFee);
		updateSo.setDeliveryFeeDiscount(soDeliveryFeeDiscount);
		updateSo.setStoreDiscount(soProductAmount.subtract(soOrderAmountPay.subtract(soDeliveryFee)).subtract(soCouponDiscount));
		soWriteDAO.update(updateSo);
	}

}
