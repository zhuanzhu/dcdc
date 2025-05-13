package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.components.product.po.ProductLimitProfitPO;
import com.egeo.components.product.vo.ProductLimitProfitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2019-04-16 10:17:35
 */
public class ProductLimitProfitConverter {
	
	public static ProductLimitProfitDTO toDTO(ProductLimitProfitVO src) {
		if (src == null)
		return null;	
		ProductLimitProfitDTO tar = new ProductLimitProfitDTO();
		tar.setId(src.getId());
		tar.setProductLimitProfit(src.getProductLimitProfit());	
		return tar;
	}

	public static ProductLimitProfitVO toVO(ProductLimitProfitDTO src) {
		if (src == null)
		return null;	
		ProductLimitProfitVO tar = new ProductLimitProfitVO();
		tar.setId(src.getId());
		tar.setProductLimitProfit(src.getProductLimitProfit());	
		return tar;
	}

	public static List<ProductLimitProfitDTO> toDTOs(List<ProductLimitProfitVO> srcs) {
		if (srcs == null)
			return null;
		List<ProductLimitProfitDTO> list = new ArrayList<ProductLimitProfitDTO>();
		for (ProductLimitProfitVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductLimitProfitVO> toVO(List<ProductLimitProfitDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductLimitProfitVO> list = new ArrayList<ProductLimitProfitVO>();
		for (ProductLimitProfitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ProductLimitProfitDTO toDTO(ProductLimitProfitPO src) {
		if (src == null)
		return null;	
		ProductLimitProfitDTO tar = new ProductLimitProfitDTO();
		tar.setId(src.getId());
		tar.setProductLimitProfit(src.getProductLimitProfit());
		return tar;
	}

	public static ProductLimitProfitPO toPO(ProductLimitProfitDTO src) {
		if (src == null)
		return null;	
		ProductLimitProfitPO tar = new ProductLimitProfitPO();
		tar.setId(src.getId());
		tar.setProductLimitProfit(src.getProductLimitProfit());
		return tar;
	}

	public static List<ProductLimitProfitDTO> toDTO(List<ProductLimitProfitPO> srcs) {
		if (srcs == null)
			return null;
		List<ProductLimitProfitDTO> list = new ArrayList<ProductLimitProfitDTO>();
		for (ProductLimitProfitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ProductLimitProfitPO> toPO(List<ProductLimitProfitDTO> srcs) {
		if (srcs == null)
			return null;
		List<ProductLimitProfitPO> list = new ArrayList<ProductLimitProfitPO>();
		for (ProductLimitProfitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	