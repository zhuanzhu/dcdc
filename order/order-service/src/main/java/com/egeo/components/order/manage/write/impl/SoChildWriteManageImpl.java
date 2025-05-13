package com.egeo.components.order.manage.write.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.dao.read.ReceiverAddressReadDAO;
import com.egeo.components.order.dao.read.SoChildReadDAO;
import com.egeo.components.order.dao.read.SoItemReadDAO;
import com.egeo.components.order.dao.read.SoPackageReadDAO;
import com.egeo.components.order.dao.read.SoReadDAO;
import com.egeo.components.order.dao.write.ReceiverAddressWriteDAO;
import com.egeo.components.order.dao.write.SoChildFlowWriteDAO;
import com.egeo.components.order.dao.write.SoChildWriteDAO;
import com.egeo.components.order.dao.write.SoFlowWriteDAO;
import com.egeo.components.order.dao.write.SoItemWriteDAO;
import com.egeo.components.order.dao.write.SoPackageWriteDAO;
import com.egeo.components.order.dao.write.SoWriteDAO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.manage.write.SoChildWriteManage;
import com.egeo.components.order.po.ReceiverAddressPO;
import com.egeo.components.order.po.SoChildFlowPO;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.components.order.po.SoFlowPO;
import com.egeo.components.order.po.SoItemPO;
import com.egeo.components.order.po.SoPO;
import com.egeo.components.order.po.SoPackagePO;
import com.egeo.components.order.po.SoPackageTempPO;
import com.egeo.components.pay.enums.OrderConstant;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class SoChildWriteManageImpl implements SoChildWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoChildReadDAO soChildReadDAO;
	@Autowired
	private SoChildWriteDAO soChildWriteDAO;
	@Autowired
	private SoPackageWriteDAO soPackageWriteDAO;
	@Autowired
	private SoPackageReadDAO soPackageReadDAO;
	@Autowired
	private ReceiverAddressReadDAO receiverAddressReadDAO;
	@Autowired
	private ReceiverAddressWriteDAO receiverAddressWriteDAO;
	@Autowired
	private SoItemReadDAO soItemReadDAO;
	@Autowired
	private SoItemWriteDAO soItemWriteDAO;
	@Autowired
	private SoChildFlowWriteDAO soChildFlowWriteDAO;
	@Autowired
	private SoFlowWriteDAO soFlowWriteDAO;

	@Autowired
	private SoWriteDAO soWriteDAO;
	@Autowired
	private SoReadDAO soReadDAO;

	@Override
	public Long insertSoChildWithTx(SoChildPO po) {

		int i;
		try {
			i = soChildWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateSoChildWithTx(SoChildPO po) {
		int i;
		i = soChildWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoChildWithTx(SoChildPO po) {
		int i;
		i = soChildWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	// 跟新子订单的状态/将数据同步到soPackage这张表里面
	@Override
	public void updateSochildStatusAndSynchrosoPackageTemp(List<Long> soChildIdList, List<SoPackageTempPO> po,
			Integer deliveryStatus) {
		for (Long soChildId : soChildIdList) {
			SoChildPO soChildPO = new SoChildPO();
			soChildPO.setId(soChildId);
			soChildPO.setDeliveryStatus(deliveryStatus);
			int i;
			i = soChildWriteDAO.update(soChildPO);
			if (i == 0)
				throw new BusinessException("未能成功更新数据!");
		}

		for (SoPackageTempPO soPackageTempPO : po) {
			SoPackagePO soPackagePO2 = new SoPackagePO();
			soPackagePO2.setSoChildId(soPackageTempPO.getSoChildId());
			List<SoPackagePO> findAll = soPackageReadDAO.findAll(soPackagePO2,null);
			SoPackagePO soPackagePO = null;
			if (EmptyUtil.isNotEmpty(findAll)) {
				soPackagePO = findAll.get(0);
			}

			if (deliveryStatus == 2) {
				soPackagePO.setGoodReceiverName(soPackageTempPO.getGoodReceiverName());
				soPackagePO.setGoodReceiverMobile(soPackageTempPO.getGoodReceiverMobile());
				soPackagePO.setProCityArea(soPackageTempPO.getProCityArea());
				soPackagePO.setGoodReceiverAddress(soPackageTempPO.getGoodReceiverAddress());
				soPackagePO.setDeliveryDate(soPackageTempPO.getDeliveryDate());
				soPackagePO.setDeliveryName(soPackageTempPO.getDeliveryName());
				soPackagePO.setDeliveryNameMobile(soPackageTempPO.getDeliveryNameMobile());
			}
			if (deliveryStatus == 3) {
				soPackagePO.setSignName(soPackageTempPO.getSignName());
				soPackagePO.setSignDate(soPackageTempPO.getSignDate());
				soPackagePO.setSignRemark(soPackageTempPO.getSignRemark());
			}
			soPackageWriteDAO.update(soPackagePO);
		}
	}

	@Deprecated
	@Override
	public boolean openOrder(Long sochildId, Map<Long, Integer> puIdAndCountMap, BigDecimal orderPrice,
			Map<Long, BigDecimal> puIdAndPriceMap, Integer orChangeReceiveInfo, ReceiverAddressPO po) {
		// 根据子单的id，查询子单
		SoChildPO soChildPO2 = new SoChildPO();
		soChildPO2.setId(sochildId);
		// 母 子单的信息
		SoChildPO soChildById = soChildReadDAO.findById(soChildPO2);

		SoItemPO soItemPO = new SoItemPO();
		soItemPO.setSoChildId(sochildId);

		List<SoItemPO> soItemPOList = soItemReadDAO.findAll(soItemPO,null);

		po.setUserId(soItemPOList.get(0).getUserId());
		List<ReceiverAddressPO> receiverAddressPOList = receiverAddressReadDAO.findAll(po,null);

		// 新建一个子单对象
		SoChildPO soChildPO3 = new SoChildPO();
		if (orChangeReceiveInfo == 0) {
			// 没有新增数据库地址，用的是其上子单的收获信息
			soChildPO3.setDeliveryId(soChildById.getDeliveryId());
		} else {
			// 新增了地址
			if (EmptyUtil.isNotEmpty(receiverAddressPOList)) {
				// 说明新增了数据库已经存在的地址
				soChildPO3.setDeliveryId(receiverAddressPOList.get(0).getId());
			} else {
				// 说明新增了一个全新的地址,要保存这个地址 ，且拆完单的一定不是默认收获地址
				po.setUserId(soItemPOList.get(0).getUserId());
				po.setType(2);
				po.setIsDefault(0);
				Long reveiveAddressId = (long) receiverAddressWriteDAO.insert(po);

				soChildPO3.setDeliveryId(reveiveAddressId);
			}
		}

		soChildPO3.setAmount(orderPrice);
		soChildPO3.setSoId(soChildById.getSoId());
		soChildPO3.setDeliveryStatus(soChildById.getDeliveryStatus());
		// 插入新的sochild
		Long soChildIdNew = (long) soChildWriteDAO.insert(soChildPO3);

		// 新生成soItem

		Set<Long> puIdSet = puIdAndCountMap.keySet();
		List<Long> puIdList = new ArrayList<>(puIdSet);
		for (int i = 0; i < puIdAndCountMap.size(); i++) {
			// 对原来的soItem进行更新
			// 根据子订单的id 和puId 查询唯一的 soItem
			SoItemPO soItemPO2 = new SoItemPO();
			soItemPO2.setSoChildId(sochildId);
			soItemPO2.setPuId(puIdList.get(i));

			List<SoItemPO> soItemList = soItemReadDAO.findAll(soItemPO2,null);
			SoItemPO soItemPO3 = soItemList.get(0);
			// 改变pu数量
			soItemPO3.setPuCount(soItemPO3.getPuCount() - puIdAndCountMap.get(puIdList.get(i)));
			// 跟新
			soItemWriteDAO.update(soItemPO3);

			// 新增soitem
			SoItemPO soItemPO4 = soItemPO3;
			soItemPO4.setId(null);
			soItemPO4.setPuCount(puIdAndCountMap.get(puIdList.get(i)));
			soItemPO4.setCreateTime(null);
			soItemPO4.setUpdateTime(null);

			soItemWriteDAO.insert(soItemPO4);
		}
		// 生成运单数据

		SoPackagePO soPackagePO = new SoPackagePO();
		soPackagePO.setSoChildId(soChildById.getId());
		soPackagePO.setSoId(soChildById.getSoId());
		List<SoPackagePO> findAll = soPackageReadDAO.findAll(soPackagePO,null);
		if (EmptyUtil.isEmpty(findAll)) {
			Long insertSoPackagePOId = (long) soPackageWriteDAO.insert(soPackagePO);
		}

		SoPackagePO soPackagePO2 = new SoPackagePO();
		soPackagePO.setSoChildId(soChildIdNew);
		soPackagePO.setSoId(soChildById.getSoId());
		List<SoPackagePO> findAll2 = soPackageReadDAO.findAll(soPackagePO2,null);
		if (EmptyUtil.isEmpty(findAll2)) {
			Long insertSoPackagePOId2 = (long) soPackageWriteDAO.insert(soPackagePO2);
		}

		// 记录流水
		SoChildFlowPO soChildFlowPO = new SoChildFlowPO();
		soChildFlowPO.setSoChildId(sochildId);
		soChildFlowPO.setOperate(0);
		soChildFlowWriteDAO.insert(soChildFlowPO);

		return true;
	}

	@Override
	public Long openOrderWithTx(SoChildPO insertSoChild, List<SoItemPO> insertItems,
			List<SoItemPO> updateItems, SoChildPO updateSoChild) {
		// 插入新的子订单,获取新id
		soChildWriteDAO.insert(insertSoChild);
		Long idNew = insertSoChild.getId();
		// 更新原子订单
		soChildWriteDAO.update(updateSoChild);
		// 插入新订单项,填写新子订单id
		for (SoItemPO it : insertItems) {
			it.setSoChildId(idNew);
			soItemWriteDAO.insert(it);
		}
		// 更新原订单项
		for (SoItemPO it : updateItems) {
			// System.out.println(it.toString());
			// 分两种情况
			if (it.getPuCount() == null) {
				// 如果puCount为0,则表示将子订单直接转移到新的子订单下
				it.setSoChildId(idNew);
			}
			// 如果puCount不为0,则表示在原订单项基础上减少数量
			soItemWriteDAO.update(it);
		}
		// 插入子订单操作流水
		// SoChildFlowPO soChildFlowPO = new SoChildFlowPO();
		// soChildFlowPO.setOperatorId(insertSoChild.getLastOperatorId());
		// soChildFlowPO.setSoId(insertSoChild.getSoId());
		// soChildFlowPO.setSoChildId(soChildId);
		// soChildFlowPO.setOperate(0);
		// soChildFlowWriteDAO.insert(soChildFlowPO);
		// 插入母订单操作流水
		SoFlowPO soFlowPO = new SoFlowPO();
		soFlowPO.setOperate(0);
		soFlowPO.setOperatorId(insertSoChild.getLastOperatorId());
		soFlowPO.setSoId(insertSoChild.getSoId());
		soFlowPO.setOperatorId(updateSoChild.getLastOperatorId());
		soFlowWriteDAO.insert(soFlowPO);
		return idNew;
	}

	@Override
	public void orderSortWithTx(List<Long> orderIds,Long operatorId) {
		if(orderIds!=null && orderIds.size()>0) {
			for (Long orderId : orderIds) {
				// 更新子订单物流状态
//				SoChildPO cond=new SoChildPO();
//				cond.setSoId(orderId);
//				cond.setDeliveryStatus(1);
				soChildWriteDAO.updateDeliveryStatusBySoId(orderId,1);
				// 插入订单分拣记录
				SoFlowPO flow=new SoFlowPO();
				flow.setOperate(1);
				flow.setOperatorId(operatorId);
				flow.setSoId(orderId);
				soFlowWriteDAO.insert(flow);
				// 插入子订单分拣记录
				soChildFlowWriteDAO.insertSoChildFlowByOrderId(orderId,operatorId,1);
				//更改母订单总状态
				soWriteDAO.updateOrderStatusById(orderId, OrderConstant.ORDER_STATUS_DELIVERED.getStatus(), null, null, OrderConstant.ORDER_DELIVERY_STATUS_DELIVERED.getStatus(),null);
			}
		}
	}


	@Override
	public void orderChildSortWithTx(List<SoChildDTO> orderChildren,Long operatorId) {
		if(orderChildren!=null && orderChildren.size()>0) {
			for (SoChildDTO orderChild : orderChildren) {
				// 更新子订单物流状态
//				SoChildPO cond=new SoChildPO();
//				cond.setSoId(orderId);
//				cond.setDeliveryStatus(1);
				if(orderChild.getDeliveryStatus()!=null && orderChild.getDeliveryStatus().intValue()<1) {
					soChildWriteDAO.updateDeliveryStatusBySoChildId(orderChild.getId(), 1);

					// 插入订单分拣记录
					SoFlowPO flow=new SoFlowPO();
					flow.setOperate(1);
					flow.setOperatorId(operatorId);
					flow.setSoId(orderChild.getSoId());
					soFlowWriteDAO.insert(flow);
					// 插入子订单分拣记录
					soChildFlowWriteDAO.insertSoChildFlowByOrderId(orderChild.getSoId(),operatorId,1);
					//更改母订单总状态
					soWriteDAO.updateOrderStatusById(orderChild.getSoId(), OrderConstant.ORDER_STATUS_DELIVERED.getStatus(), null, null, OrderConstant.ORDER_DELIVERY_STATUS_DELIVERED.getStatus(),null);
				}
			}
		}
	}


	@Override
	public void writeSendInfo(Long orderId, Long operatorId, Long platformId) {
		SoChildPO po = new SoChildPO();
		po.setSoId(orderId);
		List<SoChildPO> all = soChildReadDAO.findAll(po,null);
		for(SoChildPO soChildPO:all){
			//更改子订单的状态为已发货,
			soChildPO.setDeliveryStatus(2);
			soChildWriteDAO.update(soChildPO);
			//插入操作记录
			SoChildFlowPO scFlow=new SoChildFlowPO();
			scFlow.setOperate(3);
			scFlow.setOperatorId(operatorId);
			scFlow.setSoChildId(soChildPO.getId());
			scFlow.setSoId(orderId);
			soChildFlowWriteDAO.insert(scFlow);
		}
		SoPO soPO = new SoPO();
		soPO.setId(orderId);
		soPO.setOrderStatus(4);
		soPO.setOrderConfirmStatus(3);
		soWriteDAO.update(soPO);
		SoFlowPO flow=new SoFlowPO();
		flow.setOperate(3);
		flow.setOperatorId(operatorId);
		flow.setSoId(orderId);
		soFlowWriteDAO.insert(flow);
	}

	@Override
	public void deliverOrderWithTx(SoChildPO soChild, SoPackagePO soPackage, Map<String, Integer> deliverMap) {
		SoChildPO dbSoChild = soChildReadDAO.findById(soChild);
		SoPO findSo = new SoPO();
		findSo.setId(soChild.getSoId());
		SoPO dbSo = soReadDAO.findById(findSo);
		if (dbSoChild.getDeliveryStatus() != 0 || dbSo.getOrderConfirmStatus() != 1) {
			//发货之前校验订单状态：母订单状态是已确认，子订单物流状态状态必须是待发货
			//母订单确认状态  0:未确认，1:已确认，2:已取消 3:已完成
			//子订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
			throw new BusinessException("订单状态错误");
		}

		boolean deliverWhole = true;
		List<SoItemPO> newSoItemList = new ArrayList<>();
		List<SoItemPO> updateSoItemList = new ArrayList<>();
		BigDecimal amountNew = new BigDecimal(0);
        BigDecimal deliveryFeeNew = new BigDecimal(0);
        BigDecimal productAmountNew = new BigDecimal(0);
        BigDecimal couponDiscountNew = new BigDecimal(0);
        BigDecimal storeDiscountNew = new BigDecimal(0);
		if (deliverMap != null && !deliverMap.isEmpty()) {
			SoItemPO findSoItem = new SoItemPO();
			findSoItem.setSoChildId(soChild.getId());
			List<SoItemPO> soItemList = soItemReadDAO.findAll(findSoItem,null);
			for (SoItemPO po : soItemList) {
				String puId = po.getPuId().toString();
				if (deliverMap.containsKey(puId)) {
					if (deliverMap.get(puId) > po.getPuCount()) {
						throw new BusinessException("发货数量错误");
					}
					int deliverCount = deliverMap.get(puId);
					BigDecimal deliverCountBD = new BigDecimal(deliverCount);
					amountNew = amountNew.add(po.getAfterDiscountPriceAver().multiply(deliverCountBD));
					deliveryFeeNew = deliveryFeeNew.add(po.getDeliveryFeeAver().multiply(deliverCountBD));
					productAmountNew = productAmountNew.add(po.getPrice().multiply(deliverCountBD));
					couponDiscountNew = couponDiscountNew.add(po.getPromotionAver().multiply(deliverCountBD));
					storeDiscountNew = storeDiscountNew.add(po.getStoreDiscountAver().multiply(deliverCountBD));

					SoItemPO updatePO = new SoItemPO();
					updatePO.setId(po.getId());
					if (deliverCount < po.getPuCount()) {
						deliverWhole = false;
						updatePO.setPuCount(po.getPuCount() - deliverCount);

						SoItemPO insertPO = po.copyThis();
						insertPO.setPuCount(deliverCount);
						newSoItemList.add(insertPO);
					}
					updateSoItemList.add(updatePO);
				}
			}
		}

		if (!deliverWhole) {
			SoChildPO newSoChild = soChild.copyThis();
			newSoChild.setDeliveryStatus(2);//设置状态为已发货
			newSoChild.setChildCode(generateNewChildCode(soChild.getSoId()));

			//更新原来子订单金额
			SoChildPO updateOldSoChild = new SoChildPO();
			updateOldSoChild.setId(soChild.getId());
			updateOldSoChild.setAmount(soChild.getAmount().subtract(amountNew));
			updateOldSoChild.setDeliveryFee(soChild.getDeliveryFee().subtract(deliveryFeeNew));
			updateOldSoChild.setProductAmount(soChild.getProductAmount().subtract(productAmountNew));
			updateOldSoChild.setCouponDiscount(soChild.getCouponDiscount().subtract(couponDiscountNew));
			updateOldSoChild.setStoreDiscount(soChild.getStoreDiscount().subtract(storeDiscountNew));
			soChildWriteDAO.update(updateOldSoChild);
			//插入新的子订单
			newSoChild.setAmount(amountNew);
			newSoChild.setDeliveryFee(deliveryFeeNew);
			newSoChild.setProductAmount(productAmountNew);
			newSoChild.setCouponDiscount(couponDiscountNew);
			newSoChild.setStoreDiscount(storeDiscountNew);
			soChildWriteDAO.insert(newSoChild);
			//更新原来子订单下的PU项
			for (SoItemPO updateItem : updateSoItemList) {
				if (updateItem.getPuCount() == null) {
					//单项拆分发货，更新数量；单项整体发货，更新子订单号；为空说明是整体发货
					updateItem.setSoChildId(newSoChild.getId());
				}
				soItemWriteDAO.update(updateItem);
			}
			//新增发货的订单项目
			for (SoItemPO insertItem : newSoItemList) {
				insertItem.setSoChildId(newSoChild.getId());
				soItemWriteDAO.insert(insertItem);
			}
			soPackage.setSoChildId(newSoChild.getId());
		} else {
			//不拆单直接发货
			SoChildPO updateOldSoChild = new SoChildPO();
			updateOldSoChild.setId(soChild.getId());
			updateOldSoChild.setDeliveryStatus(2);//设置状态为已发货
			soChildWriteDAO.update(updateOldSoChild);
			soPackage.setSoChildId(soChild.getId());
		}
		//保存物流信息
		soPackageWriteDAO.insert(soPackage);
	}

	@Override
	public void updateSoChildByCodeWithTx(SoChildPO soChildPO) {
		soChildWriteDAO.updateSoChildByCodeWithTx(soChildPO);
	}

	private String generateNewChildCode(Long soId) {
		SoChildPO findSoChild = new SoChildPO();
		findSoChild.setSoId(soId);
		List<SoChildPO> soChildList = soChildReadDAO.findAll(findSoChild,null);
		String childCodePreffix = null;
		Integer maxChildCodeIndex = 1;
		for (SoChildPO soChild : soChildList) {
			String childCode = soChild.getChildCode();
			Integer codeIndex = Integer.parseInt(childCode.substring(childCode.lastIndexOf("-") + 1));
			if (EmptyUtil.isEmpty(childCodePreffix)) {
				childCodePreffix = childCode.substring(0, childCode.lastIndexOf("-") + 1);
			}
			if (maxChildCodeIndex <= codeIndex) {
				maxChildCodeIndex = codeIndex + 1;
			}
		}
		return childCodePreffix + maxChildCodeIndex;
	}

	@Override
	public int updateSoChildRefundWithTx(SoChildPO po){
		return soChildWriteDAO.updateSoChildRefundWithTx(po);
	}

	@Override
	public int deleteByChildCodeWithTx(SoChildPO po){
		return soChildWriteDAO.deleteByChildCodeWithTx(po.getChildCode(),po.getChildDeleteStatus());
	}
}
