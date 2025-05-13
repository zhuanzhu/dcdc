package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.components.stock.po.ContactGroupStockPO;
import com.egeo.components.stock.vo.ContactGroupStockVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author mingqiang.luo
 * @date 2018-12-19 10:05:33
 */
public class ContactGroupStockConverter {

	public static ContactGroupStockDTO toDTO(ContactGroupStockVO src) {
		if (src == null)
		return null;	
		ContactGroupStockDTO tar = new ContactGroupStockDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setSpuId(src.getSpuId());	
		tar.setCount(src.getCount());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMerchantId(src.getMerchantId());
		tar.setCreateUserId(src.getCreateUserId());
		tar.setCreateUserName(src.getCreateUserName());
		return tar;
	}

	public static ContactGroupStockVO toVO(ContactGroupStockDTO src) {
		if (src == null)
		return null;	
		ContactGroupStockVO tar = new ContactGroupStockVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setSpuId(src.getSpuId());	
		tar.setCount(src.getCount());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMerchantId(src.getMerchantId());
		tar.setCreateUserId(src.getCreateUserId());
		tar.setCreateUserName(src.getCreateUserName());
		return tar;
	}

	public static List<ContactGroupStockDTO> toDTOs(List<ContactGroupStockVO> srcs) {
		if (srcs == null)
			return null;
		List<ContactGroupStockDTO> list = new ArrayList<ContactGroupStockDTO>();
		for (ContactGroupStockVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ContactGroupStockVO> toVO(List<ContactGroupStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<ContactGroupStockVO> list = new ArrayList<ContactGroupStockVO>();
		for (ContactGroupStockDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ContactGroupStockDTO toDTO(ContactGroupStockPO src) {
		if (src == null)
		return null;	
		ContactGroupStockDTO tar = new ContactGroupStockDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSpuId(src.getSpuId());
		tar.setCount(src.getCount());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMerchantId(src.getMerchantId());
		tar.setCreateUserId(src.getCreateUserId());
		tar.setCreateUserName(src.getCreateUserName());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ContactGroupStockPO toPO(ContactGroupStockDTO src) {
		if (src == null)
		return null;	
		ContactGroupStockPO tar = new ContactGroupStockPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSpuId(src.getSpuId());
		tar.setCount(src.getCount());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMerchantId(src.getMerchantId());
		tar.setCreateUserId(src.getCreateUserId());
		tar.setCreateUserName(src.getCreateUserName());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ContactGroupStockDTO> toDTO(List<ContactGroupStockPO> srcs) {
		if (srcs == null)
			return null;
		List<ContactGroupStockDTO> list = new ArrayList<ContactGroupStockDTO>();
		for (ContactGroupStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ContactGroupStockPO> toPO(List<ContactGroupStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<ContactGroupStockPO> list = new ArrayList<ContactGroupStockPO>();
		for (ContactGroupStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	