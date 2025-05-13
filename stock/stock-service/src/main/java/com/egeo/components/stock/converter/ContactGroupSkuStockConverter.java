package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.components.stock.po.ContactGroupSkuStockPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author mingqiang.luo
 * @date 2018-12-19 10:05:33
 */
public class ContactGroupSkuStockConverter {
	
	public static ContactGroupSkuStockDTO toDTO(ContactGroupSkuStockPO src) {
		if (src == null)
		return null;	
		ContactGroupSkuStockDTO tar = new ContactGroupSkuStockDTO();
		tar.setId(src.getId());
		tar.setSkuId(src.getSkuId());
		tar.setContactGroupStockId(src.getContactGroupStockId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ContactGroupSkuStockPO toPO(ContactGroupSkuStockDTO src) {
		if (src == null)
		return null;	
		ContactGroupSkuStockPO tar = new ContactGroupSkuStockPO();
		tar.setId(src.getId());
		tar.setSkuId(src.getSkuId());
		tar.setContactGroupStockId(src.getContactGroupStockId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ContactGroupSkuStockDTO> toDTO(List<ContactGroupSkuStockPO> srcs) {
		if (srcs == null)
			return null;
		List<ContactGroupSkuStockDTO> list = new ArrayList<ContactGroupSkuStockDTO>();
		for (ContactGroupSkuStockPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ContactGroupSkuStockPO> toPO(List<ContactGroupSkuStockDTO> srcs) {
		if (srcs == null)
			return null;
		List<ContactGroupSkuStockPO> list = new ArrayList<ContactGroupSkuStockPO>();
		for (ContactGroupSkuStockDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	