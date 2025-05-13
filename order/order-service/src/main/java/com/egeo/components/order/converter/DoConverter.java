package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.DoDTO;
import com.egeo.components.order.po.DoPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class DoConverter {
	
	public static DoDTO toDTO(DoPO src) {
		DoDTO tar = new DoDTO();
		tar.setId(src.getId());
		tar.setDoCode(src.getDoCode());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setProductAmount(src.getProductAmount());
		tar.setOrderDeliveryFee(src.getOrderDeliveryFee());
		tar.setOrderDeliveryFeeInsuranceType(src.getOrderDeliveryFeeInsuranceType());
		tar.setOrderDeliveryFeeInsuranceAmount(src.getOrderDeliveryFeeInsuranceAmount());
		tar.setOrderPromotionDiscount(src.getOrderPromotionDiscount());
		tar.setOrderGivePoints(src.getOrderGivePoints());
		tar.setOrderDeliveryFeeAccounting(src.getOrderDeliveryFeeAccounting());
		tar.setOrderDeliveryServiceType(src.getOrderDeliveryServiceType());
		tar.setOrderDeliveryMethodId(src.getOrderDeliveryMethodId());
		tar.setExpectReceiveDateStart(src.getExpectReceiveDateStart());
		tar.setExpectReceiveDateEnd(src.getExpectReceiveDateEnd());
		tar.setExpectReceiveTimeStart(src.getExpectReceiveTimeStart());
		tar.setExpectReceiveTimeEnd(src.getExpectReceiveTimeEnd());
		tar.setOrderRemarkUser(src.getOrderRemarkUser());
		tar.setOrderRemarkMerchant2user(src.getOrderRemarkMerchant2user());
		tar.setOrderRemarkMerchant(src.getOrderRemarkMerchant());
		tar.setOrderBusinessType(src.getOrderBusinessType());
		tar.setOrderMessagePhone(src.getOrderMessagePhone());
		tar.setGoodReceiverId(src.getGoodReceiverId());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setGoodReceiverPostcode(src.getGoodReceiverPostcode());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setGoodReceiverCountryId(src.getGoodReceiverCountryId());
		tar.setGoodReceiverCountry(src.getGoodReceiverCountry());
		tar.setGoodReceiverProvinceId(src.getGoodReceiverProvinceId());
		tar.setGoodReceiverProvince(src.getGoodReceiverProvince());
		tar.setGoodReceiverCityId(src.getGoodReceiverCityId());
		tar.setGoodReceiverCity(src.getGoodReceiverCity());
		tar.setGoodReceiverCountyId(src.getGoodReceiverCountyId());
		tar.setGoodReceiverCounty(src.getGoodReceiverCounty());
		tar.setGoodReceiverAreaId(src.getGoodReceiverAreaId());
		tar.setGoodReceiverArea(src.getGoodReceiverArea());
		tar.setOrderInvoiceType(src.getOrderInvoiceType());
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static DoPO toPO(DoDTO src) {
		DoPO tar = new DoPO();
		tar.setId(src.getId());
		tar.setDoCode(src.getDoCode());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setOrderAmount(src.getOrderAmount());
		tar.setProductAmount(src.getProductAmount());
		tar.setOrderDeliveryFee(src.getOrderDeliveryFee());
		tar.setOrderDeliveryFeeInsuranceType(src.getOrderDeliveryFeeInsuranceType());
		tar.setOrderDeliveryFeeInsuranceAmount(src.getOrderDeliveryFeeInsuranceAmount());
		tar.setOrderPromotionDiscount(src.getOrderPromotionDiscount());
		tar.setOrderGivePoints(src.getOrderGivePoints());
		tar.setOrderDeliveryFeeAccounting(src.getOrderDeliveryFeeAccounting());
		tar.setOrderDeliveryServiceType(src.getOrderDeliveryServiceType());
		tar.setOrderDeliveryMethodId(src.getOrderDeliveryMethodId());
		tar.setExpectReceiveDateStart(src.getExpectReceiveDateStart());
		tar.setExpectReceiveDateEnd(src.getExpectReceiveDateEnd());
		tar.setExpectReceiveTimeStart(src.getExpectReceiveTimeStart());
		tar.setExpectReceiveTimeEnd(src.getExpectReceiveTimeEnd());
		tar.setOrderRemarkUser(src.getOrderRemarkUser());
		tar.setOrderRemarkMerchant2user(src.getOrderRemarkMerchant2user());
		tar.setOrderRemarkMerchant(src.getOrderRemarkMerchant());
		tar.setOrderBusinessType(src.getOrderBusinessType());
		tar.setOrderMessagePhone(src.getOrderMessagePhone());
		tar.setGoodReceiverId(src.getGoodReceiverId());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setGoodReceiverPostcode(src.getGoodReceiverPostcode());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setGoodReceiverCountryId(src.getGoodReceiverCountryId());
		tar.setGoodReceiverCountry(src.getGoodReceiverCountry());
		tar.setGoodReceiverProvinceId(src.getGoodReceiverProvinceId());
		tar.setGoodReceiverProvince(src.getGoodReceiverProvince());
		tar.setGoodReceiverCityId(src.getGoodReceiverCityId());
		tar.setGoodReceiverCity(src.getGoodReceiverCity());
		tar.setGoodReceiverCountyId(src.getGoodReceiverCountyId());
		tar.setGoodReceiverCounty(src.getGoodReceiverCounty());
		tar.setGoodReceiverAreaId(src.getGoodReceiverAreaId());
		tar.setGoodReceiverArea(src.getGoodReceiverArea());
		tar.setOrderInvoiceType(src.getOrderInvoiceType());
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<DoDTO> toDTO(List<DoPO> srcs) {
		if (srcs == null)
			return null;
		List<DoDTO> list = new ArrayList<DoDTO>();
		for (DoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DoPO> toPO(List<DoDTO> srcs) {
		if (srcs == null)
			return null;
		List<DoPO> list = new ArrayList<DoPO>();
		for (DoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	