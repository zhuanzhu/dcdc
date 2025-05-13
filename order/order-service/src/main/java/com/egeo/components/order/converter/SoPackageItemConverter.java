package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.components.order.po.SoPackageItemPO;
import com.egeo.components.order.vo.SoPackageItemVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoPackageItemConverter {

	public static SoPackageItemDTO toDTO(SoPackageItemVO src) {
		SoPackageItemDTO tar = new SoPackageItemDTO();
		tar.setId(src.getId());
		tar.setSoPackageId(src.getSoPackageId());		
		tar.setOrderCode(src.getOrderCode());			
		tar.setParentOrderCode(src.getParentOrderCode());			
		tar.setUserId(src.getUserId());			
		tar.setMerchantId(src.getMerchantId());			
		tar.setProductId(src.getProductId());			
		tar.setMpId(src.getMpId());			
		tar.setWarehouseId(src.getWarehouseId());			
		tar.setProductItemAmount(src.getProductItemAmount());			
		tar.setProductPriceFinal(src.getProductPriceFinal());			
		tar.setProductItemNum(src.getProductItemNum());			
		tar.setProductItemOutNum(src.getProductItemOutNum());			
		tar.setProductCname(src.getProductCname());			
		tar.setProductEname(src.getProductEname());			
		tar.setProductPicPath(src.getProductPicPath());			
		tar.setProductVersionNo(src.getProductVersionNo());			
		tar.setProductSaleType(src.getProductSaleType());			
		tar.setProductPriceOriginal(src.getProductPriceOriginal());			
		tar.setProductPriceMarket(src.getProductPriceMarket());			
		tar.setProductPriceSale(src.getProductPriceSale());			
		tar.setProductTaxAmount(src.getProductTaxAmount());			
		tar.setBuyType(src.getBuyType());			
		tar.setProductType(src.getProductType());			
		tar.setAmountShareCoupon(src.getAmountShareCoupon());			
		tar.setAmountSharePromotion(src.getAmountSharePromotion());			
		tar.setAmountShareDeliveryFee(src.getAmountShareDeliveryFee());			
		tar.setAmountShareDeliveryFeeAccounting(src.getAmountShareDeliveryFeeAccounting());			
		tar.setProductGrossWeight(src.getProductGrossWeight());			
		tar.setVehicleWarranty(src.getVehicleWarranty());			
		tar.setDeliveryTime(src.getDeliveryTime());			
		tar.setDeliveryMethod(src.getDeliveryMethod());			
		tar.setViolationResponsibility(src.getViolationResponsibility());			
		tar.setSourceId(src.getSourceId());			
		tar.setCode(src.getCode());			
		tar.setUnit(src.getUnit());			
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());			
		tar.setExciseTaxAmount(src.getExciseTaxAmount());			
		tar.setIncrementTaxAmount(src.getIncrementTaxAmount());			
		tar.setCustomsDutiesAmount(src.getCustomsDutiesAmount());			
		tar.setExtInfo(src.getExtInfo());			
		tar.setSoItemId(src.getSoItemId());			
		tar.setUpdateTime(src.getUpdateTime());			
		tar.setCreateTime(src.getCreateTime());			
		tar.setPlatformId(src.getPlatformId());			
		tar.setSkuId(src.getSkuId());			
		return tar;
	}

	public static SoPackageItemVO toVO(SoPackageItemDTO src) {
		SoPackageItemVO tar = new SoPackageItemVO();
		tar.setId(src.getId());
		tar.setSoPackageId(src.getSoPackageId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setProductId(src.getProductId());
		tar.setMpId(src.getMpId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setProductItemAmount(src.getProductItemAmount());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setProductItemOutNum(src.getProductItemOutNum());
		tar.setProductCname(src.getProductCname());
		tar.setProductEname(src.getProductEname());
		tar.setProductPicPath(src.getProductPicPath());
		tar.setProductVersionNo(src.getProductVersionNo());
		tar.setProductSaleType(src.getProductSaleType());
		tar.setProductPriceOriginal(src.getProductPriceOriginal());
		tar.setProductPriceMarket(src.getProductPriceMarket());
		tar.setProductPriceSale(src.getProductPriceSale());
		tar.setProductTaxAmount(src.getProductTaxAmount());
		tar.setBuyType(src.getBuyType());
		tar.setProductType(src.getProductType());
		tar.setAmountShareCoupon(src.getAmountShareCoupon());
		tar.setAmountSharePromotion(src.getAmountSharePromotion());
		tar.setAmountShareDeliveryFee(src.getAmountShareDeliveryFee());
		tar.setAmountShareDeliveryFeeAccounting(src.getAmountShareDeliveryFeeAccounting());
		tar.setProductGrossWeight(src.getProductGrossWeight());
		tar.setVehicleWarranty(src.getVehicleWarranty());
		tar.setDeliveryTime(src.getDeliveryTime());
		tar.setDeliveryMethod(src.getDeliveryMethod());
		tar.setViolationResponsibility(src.getViolationResponsibility());
		tar.setSourceId(src.getSourceId());
		tar.setCode(src.getCode());
		tar.setUnit(src.getUnit());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setExciseTaxAmount(src.getExciseTaxAmount());
		tar.setIncrementTaxAmount(src.getIncrementTaxAmount());
		tar.setCustomsDutiesAmount(src.getCustomsDutiesAmount());
		tar.setExtInfo(src.getExtInfo());
		tar.setSoItemId(src.getSoItemId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<SoPackageItemDTO> toDTOs(List<SoPackageItemVO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageItemDTO> list = new ArrayList<SoPackageItemDTO>();
		for (SoPackageItemVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoPackageItemVO> toVO(List<SoPackageItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageItemVO> list = new ArrayList<SoPackageItemVO>();
		for (SoPackageItemDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoPackageItemDTO toDTO(SoPackageItemPO src) {
		SoPackageItemDTO tar = new SoPackageItemDTO();
		tar.setId(src.getId());
		tar.setSoPackageId(src.getSoPackageId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setProductId(src.getProductId());
		tar.setMpId(src.getMpId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setProductItemAmount(src.getProductItemAmount());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setProductItemOutNum(src.getProductItemOutNum());
		tar.setProductCname(src.getProductCname());
		tar.setProductEname(src.getProductEname());
		tar.setProductPicPath(src.getProductPicPath());
		tar.setProductVersionNo(src.getProductVersionNo());
		tar.setProductSaleType(src.getProductSaleType());
		tar.setProductPriceOriginal(src.getProductPriceOriginal());
		tar.setProductPriceMarket(src.getProductPriceMarket());
		tar.setProductPriceSale(src.getProductPriceSale());
		tar.setProductTaxAmount(src.getProductTaxAmount());
		tar.setBuyType(src.getBuyType());
		tar.setProductType(src.getProductType());
		tar.setAmountShareCoupon(src.getAmountShareCoupon());
		tar.setAmountSharePromotion(src.getAmountSharePromotion());
		tar.setAmountShareDeliveryFee(src.getAmountShareDeliveryFee());
		tar.setAmountShareDeliveryFeeAccounting(src.getAmountShareDeliveryFeeAccounting());
		tar.setProductGrossWeight(src.getProductGrossWeight());
		tar.setVehicleWarranty(src.getVehicleWarranty());
		tar.setDeliveryTime(src.getDeliveryTime());
		tar.setDeliveryMethod(src.getDeliveryMethod());
		tar.setViolationResponsibility(src.getViolationResponsibility());
		tar.setSourceId(src.getSourceId());
		tar.setCode(src.getCode());
		tar.setUnit(src.getUnit());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setExciseTaxAmount(src.getExciseTaxAmount());
		tar.setIncrementTaxAmount(src.getIncrementTaxAmount());
		tar.setCustomsDutiesAmount(src.getCustomsDutiesAmount());
		tar.setExtInfo(src.getExtInfo());
		tar.setSoItemId(src.getSoItemId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static SoPackageItemPO toPO(SoPackageItemDTO src) {
		SoPackageItemPO tar = new SoPackageItemPO();
		tar.setId(src.getId());
		tar.setSoPackageId(src.getSoPackageId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setUserId(src.getUserId());
		tar.setMerchantId(src.getMerchantId());
		tar.setProductId(src.getProductId());
		tar.setMpId(src.getMpId());
		tar.setWarehouseId(src.getWarehouseId());
		tar.setProductItemAmount(src.getProductItemAmount());
		tar.setProductPriceFinal(src.getProductPriceFinal());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setProductItemOutNum(src.getProductItemOutNum());
		tar.setProductCname(src.getProductCname());
		tar.setProductEname(src.getProductEname());
		tar.setProductPicPath(src.getProductPicPath());
		tar.setProductVersionNo(src.getProductVersionNo());
		tar.setProductSaleType(src.getProductSaleType());
		tar.setProductPriceOriginal(src.getProductPriceOriginal());
		tar.setProductPriceMarket(src.getProductPriceMarket());
		tar.setProductPriceSale(src.getProductPriceSale());
		tar.setProductTaxAmount(src.getProductTaxAmount());
		tar.setBuyType(src.getBuyType());
		tar.setProductType(src.getProductType());
		tar.setAmountShareCoupon(src.getAmountShareCoupon());
		tar.setAmountSharePromotion(src.getAmountSharePromotion());
		tar.setAmountShareDeliveryFee(src.getAmountShareDeliveryFee());
		tar.setAmountShareDeliveryFeeAccounting(src.getAmountShareDeliveryFeeAccounting());
		tar.setProductGrossWeight(src.getProductGrossWeight());
		tar.setVehicleWarranty(src.getVehicleWarranty());
		tar.setDeliveryTime(src.getDeliveryTime());
		tar.setDeliveryMethod(src.getDeliveryMethod());
		tar.setViolationResponsibility(src.getViolationResponsibility());
		tar.setSourceId(src.getSourceId());
		tar.setCode(src.getCode());
		tar.setUnit(src.getUnit());
		tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
		tar.setExciseTaxAmount(src.getExciseTaxAmount());
		tar.setIncrementTaxAmount(src.getIncrementTaxAmount());
		tar.setCustomsDutiesAmount(src.getCustomsDutiesAmount());
		tar.setExtInfo(src.getExtInfo());
		tar.setSoItemId(src.getSoItemId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<SoPackageItemDTO> toDTO(List<SoPackageItemPO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageItemDTO> list = new ArrayList<SoPackageItemDTO>();
		for (SoPackageItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoPackageItemPO> toPO(List<SoPackageItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageItemPO> list = new ArrayList<SoPackageItemPO>();
		for (SoPackageItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	