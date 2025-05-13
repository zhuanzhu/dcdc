package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.po.ContactGroupPuStockPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author mingqiang.luo
 * @date 2018-12-19 10:05:34
 */
public class ContactGroupPuStockConverter {
	
	public static ContactGroupPuStockDTO toDTO(ContactGroupPuStockPO src) {
		if (src == null)
		return null;	
		ContactGroupPuStockDTO tar = new ContactGroupPuStockDTO();
		tar.setId(src.getId());
		tar.setSuId(src.getSuId());
		tar.setPuId(src.getPuId());
		tar.setContactGroupSkuId(src.getContactGroupSkuId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ContactGroupPuStockPO toPO(ContactGroupPuStockDTO src) {
		if (src == null)
		return null;	
		ContactGroupPuStockPO tar = new ContactGroupPuStockPO();
		tar.setId(src.getId());
		tar.setSuId(src.getSuId());
		tar.setPuId(src.getPuId());
		tar.setContactGroupSkuId(src.getContactGroupSkuId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ContactGroupPuStockDTO> toDTO(List<ContactGroupPuStockPO> srcs) {
		if (srcs == null)
			return null;
		List<ContactGroupPuStockDTO> list = new ArrayList<ContactGroupPuStockDTO>();
		for (ContactGroupPuStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ContactGroupPuStockPO> toPO(List<ContactGroupPuStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<ContactGroupPuStockPO> list = new ArrayList<ContactGroupPuStockPO>();
		for (ContactGroupPuStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	