package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.JdCategoryDTO;
import com.egeo.components.product.po.JdCategoryPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2019-03-26 10:43:15
 */
public class JdCategoryConverter {
	
	public static JdCategoryDTO toDTO(JdCategoryPO src) {
		if (src == null)
		return null;	
		JdCategoryDTO tar = new JdCategoryDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setParentId(src.getParentId());
		tar.setInnerCategoryId(src.getInnerCategoryId());
		tar.setCatClass(src.getCatClass());
		return tar;
	}

	public static JdCategoryPO toPO(JdCategoryDTO src) {
		if (src == null)
		return null;	
		JdCategoryPO tar = new JdCategoryPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setParentId(src.getParentId());
		tar.setInnerCategoryId(src.getInnerCategoryId());
		tar.setCatClass(src.getCatClass());

		return tar;
	}

	public static List<JdCategoryDTO> toDTO(List<JdCategoryPO> srcs) {
		if (srcs == null)
			return null;
		List<JdCategoryDTO> list = new ArrayList<JdCategoryDTO>();
		for (JdCategoryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdCategoryPO> toPO(List<JdCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdCategoryPO> list = new ArrayList<JdCategoryPO>();
		for (JdCategoryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	