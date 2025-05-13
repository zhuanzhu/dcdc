package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.FunctionTreeNodeDTO;
import com.egeo.components.user.po.FunctionTreeNodePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-11-13 10:32:46
 */
public class FunctionTreeNodeConverter {
	
	public static FunctionTreeNodeDTO toDTO(FunctionTreeNodePO src) {
		if (src == null)
		return null;	
		FunctionTreeNodeDTO tar = new FunctionTreeNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setName(src.getName());
		tar.setSortValue(src.getSortValue());
		tar.setIsLeaf(src.getIsLeaf());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static FunctionTreeNodePO toPO(FunctionTreeNodeDTO src) {
		if (src == null)
		return null;	
		FunctionTreeNodePO tar = new FunctionTreeNodePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setName(src.getName());
		tar.setSortValue(src.getSortValue());
		tar.setIsLeaf(src.getIsLeaf());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<FunctionTreeNodeDTO> toDTO(List<FunctionTreeNodePO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionTreeNodeDTO> list = new ArrayList<FunctionTreeNodeDTO>();
		for (FunctionTreeNodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FunctionTreeNodePO> toPO(List<FunctionTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionTreeNodePO> list = new ArrayList<FunctionTreeNodePO>();
		for (FunctionTreeNodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	