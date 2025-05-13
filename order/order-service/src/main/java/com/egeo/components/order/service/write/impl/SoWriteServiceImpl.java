package com.egeo.components.order.service.write.impl;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.order.manage.read.SoItemReadManage;
import com.egeo.utils.log.XLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.components.order.manage.write.SoWriteManage;
import com.egeo.components.order.converter.LimitRuleRecordConverter;
import com.egeo.components.order.converter.SoChildConverter;
import com.egeo.components.order.converter.SoConverter;
import com.egeo.components.order.converter.SoDeviceConverter;
import com.egeo.components.order.converter.SoItemConverter;
import com.egeo.components.order.converter.SoThirdpartyConverter;
import com.egeo.components.order.dto.NewSoOrderDTO;
import com.egeo.components.order.dto.SoDTO;

import com.egeo.exception.BusinessException;
import com.egeo.web.JsonResult;

@Service("soWriteService")
public class SoWriteServiceImpl  implements SoWriteService {
	XLogger logger = XLogger.getLogger(this.getClass().getName());

	@Autowired
	private SoWriteManage soWriteManage;
	@Autowired
	private SoItemReadManage soItemReadManage;

	@Override
	public Long createOrder(NewSoOrderDTO nsoDTO) {

		logger.info("createOrder:"+nsoDTO.getSoDTO().getOrderCode()+",status:"+nsoDTO.getSoDTO().getOrderStatus());

	/*	return soWriteManage.createOrderWithTx(
				SoConverter.toPO(nsoDTO.getSoDTO()),
				SoItemConverter.toNewCondition(nsoDTO.getItemList()),
				nsoDTO.getCartItemIds(),
//				SoChildConverter.toNewCondition(nsoDTO.getSoChild()),
				SoChildConverter.toPO(nsoDTO.getSoChild()),
				SoDeviceConverter.toPO(nsoDTO.getSoDevice()),
				LimitRuleRecordConverter.toPO(nsoDTO.getLimitRuleRecordList()),
				SoThirdpartyConverter.toPO(nsoDTO.getSoThirdpartyDTO()),nsoDTO.getCompanyAllId());
	}
*/
		return soWriteManage.createOrderNewWithTx(
				SoConverter.toPO(nsoDTO.getSoDTO()),
				SoItemConverter.toNewCondition(nsoDTO.getItemList()),
				nsoDTO.getCartItemIds(),
				SoChildConverter.toPO(nsoDTO.getSoChild()),
				SoDeviceConverter.toPO(nsoDTO.getSoDevice()),
				LimitRuleRecordConverter.toPO(nsoDTO.getLimitRuleRecordList()),
				SoThirdpartyConverter.toPO(nsoDTO.getSoThirdpartyDTOList()),nsoDTO.getCompanyAllId());
	}

	@Override
	public boolean changeOrderStatusByOrderCode(
			String orderCode, Integer status,Integer confirmStatus,
			Integer payStatus,Integer deliveryStatus) {
		return soWriteManage.changeOrderStatusByOrderCodeWithTx(orderCode,status,confirmStatus,payStatus,deliveryStatus);
	}

	@Override
	public boolean changeOrderStatusByOrderId(
			String orderCode, Long id, Integer status, Integer confirmStatus,
			Integer payStatus, Integer deliveryStatus) {
		logger.info("changeOrderStatusByOrderId:"+orderCode+",status:"+status+",deliveryStatus:"+deliveryStatus);
		return soWriteManage.changeOrderStatusByOrderIdWithTx(orderCode,id,status,confirmStatus,payStatus,deliveryStatus);
	}

	@Override
	public int updateOrderMoneyInfo(Long orderId, BigDecimal orderAmount, BigDecimal deliveryAmount, BigDecimal payMoney) {
		return soWriteManage.updateOrderMoneyInfoWithTx(orderId, orderAmount, deliveryAmount,payMoney);
	}

	@Override
	public int updateDeliveryInfo(Long orderId, String receiverName, String receiverPhone, String receiverAddress) {
		return soWriteManage.updateDeliveryInfoWithTx(orderId,receiverName,receiverPhone,receiverAddress);
	}

	@Override
	public int updateOrderPaymentInfo(SoDTO orderUpdateCondition) {
		logger.info("updateOrderPaymentInfo:"+orderUpdateCondition.getOrderCode()+",status:"+orderUpdateCondition.getOrderStatus());
		return soWriteManage.updateOrderPaymentInfoWithTx(
				SoConverter.toPO(orderUpdateCondition));
	}

	@Override
	public int deleteByOrderCode(String orderCode, Long userId) {
		return soWriteManage.deleteByOrderCode(orderCode, userId);
	}

	@Override
	public String affirmOrderBySoId(Long orderId, Long platformId) {
		if(orderId == null){
			throw new BusinessException("确认订单，订单编号不能为空");
		}
		return soWriteManage.affirmOrderBySoId(orderId, platformId);
	}
	/**
	 * 根据订单id修改订单收货人信息
	 * @param soVO
	 * @param req
	 * @return
	 */
	@Override
	public JsonResult<String> updateOrderByOrderId(SoDTO dto) {
		logger.info("updateOrderByOrderId:"+dto.getOrderCode()+",status:"+dto.getOrderStatus());
		return soWriteManage.updateOrderByOrderId(SoConverter.toPO(dto));
	}

	@Override
	public int SoSort(List<Long> soIdArr, Long platformId) {
		return soWriteManage.SoSort(soIdArr,platformId);
	}

	@Override
	public int orderRefund(SoDTO soDTO) {

		return soWriteManage.orderRefund(SoConverter.toPO(soDTO));
	}
	/**
	 * 根据订单id保存订单自动完成时间
	 * @param soDTO2
	 * @return
	 */
	@Override
	public int saveOrderAutoCompleteDate(SoDTO soDTO2) {
		// TODO Auto-generated method stub
		logger.info("saveOrderAutoCompleteDate:"+soDTO2.getOrderCode()+",status:"+soDTO2.getOrderStatus());
		return soWriteManage.saveOrderAutoCompleteDate(SoConverter.toPO(soDTO2));
	}

	@Override
	public void updateSoPayByFuBi(Long orderId, BigDecimal soFreezeBalance) {
		soWriteManage.updateSoPayByFuBi(orderId,soFreezeBalance);
	}

	@Override
	public void repairOrderDataWithTx() {
		soWriteManage.repairOrderDataWithTx();
	}


	//更新订单中的订单取消状态为1(取消中)
	@Override
	public int updateOrderCancelStatus(String orderCode) {
		return soWriteManage.updateOrderCancelStatus(orderCode);
	}

	@Override
	public int updateOrderCancelOverStatusByOrderCode(String orderCode) {
		return soWriteManage.updateOrderCancelOverStatusByOrderCode(orderCode);
	}

	@Override
	public void updateOrderWithTX(SoDTO sodto) {
		logger.info("updateOrderWithTX:"+sodto.getOrderCode()+",status:"+sodto.getOrderStatus());
		soWriteManage.updateOrderByOrderIdWithTx(SoConverter.toPO(sodto));
	}

}
