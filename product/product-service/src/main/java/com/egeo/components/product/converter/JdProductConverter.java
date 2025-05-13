package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.JdSearchHitResultDTO;
import com.egeo.components.product.po.JdProductPO;
import com.egeo.components.product.vo.JdProductEnterpriseVO;
import com.egeo.components.product.vo.JdProductVO;
import com.egeo.components.product.vo.JdProductVO2;

/**
 * DTO和PO相互转换工具类
 *
 * @author tan
 * @date 2019-03-26 10:43:54
 */
public class JdProductConverter {


	public static JdProductDTO toDTO(JdProductVO src) {
		if (src == null)
		return null;
		JdProductDTO tar = new JdProductDTO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());
		tar.setName(src.getName());
		tar.setImagePath(src.getImagePath());
		tar.setIntroduction(src.getIntroduction());
		tar.setParam(src.getParam());
		tar.setLowestBuy(src.getLowestBuy());
		tar.setSpuId(src.getSpuId());
		tar.setSpuName(src.getSpuName());
		tar.setPrice(src.getPrice());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setIsSelf(src.getIsSelf());
		tar.setIsJdLogistics(src.getIsJdLogistics());
		tar.setSaleState(src.getSaleState());
		tar.setState(src.getState());
		tar.setIs7ToReturn(src.getIs7ToReturn());
		tar.setNoReasonToReturn(src.getNoReasonToReturn());
		tar.setThwa(src.getThwa());
		tar.setSkuJson(src.getSkuJson());
		tar.setSyncStatus(src.getSyncStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSyncTime(src.getSyncTime());
		tar.setProductCreateTime(src.getProductCreateTime());
		tar.setPicture(src.getPicture());

		return tar;
	}

	public static JdProductDTO toDTO(JdProductVO2 src) {
		if (src == null)
		return null;
		JdProductDTO tar = new JdProductDTO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());
		tar.setName(src.getName());
		tar.setImagePath(src.getImagePath());
		tar.setIntroduction(src.getIntroduction());
		tar.setParam(src.getParam());
		tar.setLowestBuy(src.getLowestBuy());
		tar.setSpuId(src.getSpuId());
		tar.setSpuName(src.getSpuName());
		tar.setPrice(src.getPrice());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setIsSelf(src.getIsSelf());
		tar.setIsJdLogistics(src.getIsJdLogistics());
		tar.setSaleState(src.getSaleState());
		tar.setState(src.getState());
		tar.setIs7ToReturn(src.getIs7ToReturn());
		tar.setNoReasonToReturn(src.getNoReasonToReturn());
		tar.setThwa(src.getThwa());
		tar.setSkuJson(src.getSkuJson());
		tar.setSyncStatus(src.getSyncStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSyncTime(src.getSyncTime());
		tar.setProductCreateTime(src.getProductCreateTime());
		tar.setPicture(src.getPicture());
		tar.setEnterpriseId(src.getEnterpriseId());
		return tar;
	}

	public static JdProductDTO toDTO(JdSearchHitResultDTO src) {
		if (src == null)
		return null;
		JdProductDTO tar = new JdProductDTO();
		tar.setId(src.getWareIdLong());
		tar.setCategoryId(src.getCatIdLong());
		tar.setName(src.getWareName());
		tar.setImagePath(src.getImageUrl());
		tar.setState(src.getWstateInt());
		tar.setSaleState(1);
		tar.setSpuId(src.getWarePIdLong());
		tar.setIsShow(1);
		tar.setIsDetail(1);
		return tar;
	}

	public static JdProductEnterpriseVO toEnterpriseVO(JdSearchHitResultDTO src) {
		if (src == null)
		return null;
		JdProductEnterpriseVO tar = new JdProductEnterpriseVO();
		tar.setId(src.getWareIdLong());
		tar.setCategoryId(src.getCatIdLong());
		tar.setName(src.getWareName());
		tar.setImagePath(src.getImageUrl());
		tar.setState(src.getWstateInt());
		tar.setSaleState(1);
		tar.setSpuId(src.getWarePIdLong());
		return tar;
	}

	/*
	public static JdProductDTO toDTO(JSONObject src) {
		if (src == null)
		return null;
		JdProductDTO tar = new JdProductDTO();
		if(src.containsKey(""))
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());
		tar.setName(src.getName());
		tar.setImagePath(src.getImagePath());
		tar.setIntroduction(src.getIntroduction());
		tar.setParam(src.getParam());
		tar.setLowestBuy(src.getLowestBuy());
		tar.setSpuId(src.getSpuId());
		tar.setSpuName(src.getSpuName());
		tar.setPrice(src.getPrice());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setIsSelf(src.getIsSelf());
		tar.setIsJdLogistics(src.getIsJdLogistics());
		tar.setSaleState(src.getSaleState());
		tar.setState(src.getState());
		tar.setIs7ToReturn(src.getIs7ToReturn());
		tar.setNoReasonToReturn(src.getNoReasonToReturn());
		tar.setThwa(src.getThwa());
		tar.setSkuJson(src.getSkuJson());
		tar.setSyncStatus(src.getSyncStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSyncTime(src.getSyncTime());
		tar.setProductCreateTime(src.getProductCreateTime());
		tar.setPicture(src.getPicture());

		return tar;
	}*/
	public static JdProductVO toVO(JdProductDTO src) {
		if (src == null)
		return null;
		JdProductVO tar = new JdProductVO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());
		tar.setName(src.getName());
		tar.setImagePath(src.getImagePath());
		tar.setIntroduction(src.getIntroduction());
		tar.setParam(src.getParam());
		tar.setLowestBuy(src.getLowestBuy());
		tar.setSpuId(src.getSpuId());
		tar.setSpuName(src.getSpuName());
		tar.setPrice(src.getPrice());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setIsSelf(src.getIsSelf());
		tar.setIsJdLogistics(src.getIsJdLogistics());
		tar.setSaleState(src.getSaleState());
		tar.setState(src.getState());
		tar.setIs7ToReturn(src.getIs7ToReturn());
		tar.setNoReasonToReturn(src.getNoReasonToReturn());
		tar.setThwa(src.getThwa());
		tar.setSkuJson(src.getSkuJson());
		tar.setSyncStatus(src.getSyncStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSyncTime(src.getSyncTime());
		tar.setProductCreateTime(src.getProductCreateTime());
		tar.setPicture(src.getPicture());

		return tar;
	}

	public static List<JdProductDTO> toDTOs(List<JdProductVO> srcs) {
		if (srcs == null)
			return null;
		List<JdProductDTO> list = new ArrayList<JdProductDTO>();
		for (JdProductVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdProductDTO> toDTO2s(List<JdProductVO2> srcs) {
		if (srcs == null)
			return null;
		List<JdProductDTO> list = new ArrayList<JdProductDTO>();
		for (JdProductVO2 src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static List<JdProductVO> toVO(List<JdProductDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdProductVO> list = new ArrayList<JdProductVO>();
		for (JdProductDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static JdProductDTO toDTO(JdProductPO src) {
		if (src == null)
		return null;
		JdProductDTO tar = new JdProductDTO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());
		tar.setName(src.getName());
		tar.setImagePath(src.getImagePath());
		tar.setIntroduction(src.getIntroduction());
		tar.setParam(src.getParam());
		tar.setLowestBuy(src.getLowestBuy());
		tar.setSpuId(src.getSpuId());
		tar.setSpuName(src.getSpuName());
		tar.setPrice(src.getPrice());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setIsSelf(src.getIsSelf());
		tar.setIsJdLogistics(src.getIsJdLogistics());
		tar.setSaleState(src.getSaleState());
		tar.setState(src.getState());
		tar.setIs7ToReturn(src.getIs7ToReturn());
		tar.setNoReasonToReturn(src.getNoReasonToReturn());
		tar.setThwa(src.getThwa());
		tar.setSkuJson(src.getSkuJson());
		tar.setSyncStatus(src.getSyncStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setProfit(src.getProfit());
		tar.setIsShow(src.getIsShow());
		tar.setIsDetail(src.getIsDetail());
		tar.setSyncTime(src.getSyncTime());
		tar.setProductCreateTime(src.getProductCreateTime());
		tar.setPicture(src.getPicture());
		return tar;
	}

	public static JdProductPO toPO(JdProductDTO src) {
		if (src == null)
		return null;
		JdProductPO tar = new JdProductPO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());
		tar.setName(src.getName());
		tar.setImagePath(src.getImagePath());
		tar.setIntroduction(src.getIntroduction());
		tar.setParam(src.getParam());
		tar.setLowestBuy(src.getLowestBuy());
		tar.setSpuId(src.getSpuId());
		tar.setSpuName(src.getSpuName());
		tar.setPrice(src.getPrice());
		tar.setMarketPrice(src.getMarketPrice());
		tar.setIsSelf(src.getIsSelf());
		tar.setIsJdLogistics(src.getIsJdLogistics());
		tar.setSaleState(src.getSaleState());
		tar.setState(src.getState());
		tar.setIs7ToReturn(src.getIs7ToReturn());
		tar.setNoReasonToReturn(src.getNoReasonToReturn());
		tar.setThwa(src.getThwa());
		tar.setSkuJson(src.getSkuJson());
		tar.setSyncStatus(src.getSyncStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setProfit(src.getProfit());
		tar.setIsShow(src.getIsShow());
		tar.setIsDetail(src.getIsDetail());
		tar.setSyncTime(src.getSyncTime());
		tar.setProductCreateTime(src.getProductCreateTime());
		tar.setPicture(src.getPicture());
		return tar;
	}

	public static List<JdProductDTO> toDTO(List<JdProductPO> srcs) {
		if (srcs == null)
			return null;
		List<JdProductDTO> list = new ArrayList<JdProductDTO>();
		for (JdProductPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdProductPO> toPO(List<JdProductDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdProductPO> list = new ArrayList<JdProductPO>();
		for (JdProductDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
