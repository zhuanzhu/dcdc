package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.CartDTO;
import com.egeo.components.order.po.CartPO;
import com.egeo.components.order.vo.CartVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-17 17:35:12
 */
public class CartConverter {

	public static CartDTO toDTO(CartVO src) {
		if (src == null)
		return null;	
		CartDTO tar = new CartDTO();
		tar.setId(src.getId());
		tar.setUserid(src.getUserid());	
		tar.setSessionid(src.getSessionid());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setClientId(src.getClientId());	
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setSaleWay(src.getSaleWay());
		return tar;
	}

	public static CartVO toVO(CartDTO src) {
		if (src == null)
		return null;	
		CartVO tar = new CartVO();
		tar.setId(src.getId());
		tar.setUserid(src.getUserid());	
		tar.setSessionid(src.getSessionid());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setClientId(src.getClientId());	
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setSaleWay(src.getSaleWay());
		return tar;
	}

	public static List<CartDTO> toDTOs(List<CartVO> srcs) {
		if (srcs == null)
			return null;
		List<CartDTO> list = new ArrayList<CartDTO>();
		for (CartVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CartVO> toVO(List<CartDTO> srcs) {
		if (srcs == null)
			return null;
		List<CartVO> list = new ArrayList<CartVO>();
		for (CartDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CartDTO toDTO(CartPO src) {
		if (src == null)
		return null;	
		CartDTO tar = new CartDTO();
		tar.setId(src.getId());
		tar.setUserid(src.getUserid());
		tar.setSessionid(src.getSessionid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setClientId(src.getClientId());
		tar.setPlatformId(src.getPlatformId());
		tar.setSaleWay(src.getSaleWay());
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static CartPO toPO(CartDTO src) {
		if (src == null)
		return null;	
		CartPO tar = new CartPO();
		tar.setId(src.getId());
		tar.setUserid(src.getUserid());
		tar.setSessionid(src.getSessionid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setClientId(src.getClientId());
		tar.setPlatformId(src.getPlatformId());
		tar.setSaleWay(src.getSaleWay());
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static List<CartDTO> toDTO(List<CartPO> srcs) {
		if (srcs == null)
			return null;
		List<CartDTO> list = new ArrayList<CartDTO>();
		for (CartPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CartPO> toPO(List<CartDTO> srcs) {
		if (srcs == null)
			return null;
		List<CartPO> list = new ArrayList<CartPO>();
		for (CartDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	