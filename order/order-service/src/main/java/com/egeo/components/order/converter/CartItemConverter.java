package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.CartItemDTO;
import com.egeo.components.order.po.CartItemPO;
import com.egeo.components.order.vo.CartItemVO;
import com.egeo.utils.EmptyUtil;

/**
 * DTO和PO相互转换工具类
 *
 * @author min
 * @date 2018-01-17 17:03:10
 */
public class CartItemConverter {


	public static CartItemDTO toDTO(CartItemVO src) {
		if (src == null)
		return null;
		CartItemDTO tar = new CartItemDTO();
		tar.setId(src.getId());
		tar.setCartId(src.getCartId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setNum(src.getNum());
		tar.setIsGift(src.getIsGift());
		tar.setSalePrice(src.getSalePrice());
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setCashAmount(src.getCashAmount());
		tar.setBlessingCoinAmount(src.getBlessingCoinAmount());
		tar.setPromotionId(src.getPromotionId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setChannelProductId(src.getChannelProductId());
		return tar;
	}

	public static CartItemVO toVO(CartItemDTO src) {
		if (src == null)
		return null;
		CartItemVO tar = new CartItemVO();
		tar.setId(src.getId());
		tar.setCartId(src.getCartId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setNum(src.getNum());
		tar.setIsGift(src.getIsGift());
		tar.setSalePrice(src.getSalePrice());
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setCashAmount(src.getCashAmount());
		tar.setBlessingCoinAmount(src.getBlessingCoinAmount());
		tar.setPromotionId(src.getPromotionId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setProductUnitId(src.getProductUnitId());
		if(EmptyUtil.isNotEmpty(src.getChannelProductId())){
			tar.setChannelProductId(src.getChannelProductId());
		}
		return tar;
	}

	public static List<CartItemDTO> toDTOs(List<CartItemVO> srcs) {
		if (srcs == null)
			return null;
		List<CartItemDTO> list = new ArrayList<CartItemDTO>();
		for (CartItemVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CartItemVO> toVO(List<CartItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<CartItemVO> list = new ArrayList<CartItemVO>();
		for (CartItemDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CartItemDTO toDTO(CartItemPO src) {
		if (src == null)
		return null;
		CartItemDTO tar = new CartItemDTO();
		tar.setId(src.getId());
		tar.setCartId(src.getCartId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setNum(src.getNum());
		tar.setIsGift(src.getIsGift());
		tar.setSalePrice(src.getSalePrice());
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setCashAmount(src.getCashAmount());
		tar.setBlessingCoinAmount(src.getBlessingCoinAmount());
		tar.setPromotionId(src.getPromotionId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setSnapshot(src.getSnapshot());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		if(EmptyUtil.isNotEmpty(src.getChannelProductId())){
			tar.setChannelProductId(src.getChannelProductId());
		}
		return tar;
	}

	public static CartItemPO toPO(CartItemDTO src) {
		if (src == null)
		return null;
		CartItemPO tar = new CartItemPO();
		tar.setId(src.getId());
		tar.setCartId(src.getCartId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setNum(src.getNum());
		tar.setIsGift(src.getIsGift());
		tar.setSalePrice(src.getSalePrice());
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setCashAmount(src.getCashAmount());
		tar.setBlessingCoinAmount(src.getBlessingCoinAmount());
		tar.setPromotionId(src.getPromotionId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setSnapshot(src.getSnapshot());
		if(src.getSource()!=null) {
			tar.setSource(src.getSource());
		}
		if(EmptyUtil.isNotEmpty(src.getChannelProductId())){
			tar.setChannelProductId(src.getChannelProductId());
		}

		return tar;
	}

	public static List<CartItemDTO> toDTO(List<CartItemPO> srcs) {
		if (srcs == null)
			return null;
		List<CartItemDTO> list = new ArrayList<CartItemDTO>();
		for (CartItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CartItemPO> toPO(List<CartItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<CartItemPO> list = new ArrayList<CartItemPO>();
		for (CartItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
