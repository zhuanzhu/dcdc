package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.RoleFunctionTreeNodeDTO;
import com.egeo.components.user.po.RoleFunctionTreeNodePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-11-13 17:08:59
 */
public class RoleFunctionTreeNodeConverter {
	
	public static RoleFunctionTreeNodeDTO toDTO(RoleFunctionTreeNodePO src) {
		if (src == null)
		return null;	
		RoleFunctionTreeNodeDTO tar = new RoleFunctionTreeNodeDTO();
		tar.setId(src.getId());
		tar.setRoleId(src.getRoleId());
		tar.setFunctionTreeNodeId(src.getFunctionTreeNodeId());
		tar.setFullCheck(src.getFullCheck());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setUpdateTime(src.getUpdateTime());
		//tar.setFunctionModuleId(src.getFunctionModuleId());
		return tar;
	}

	public static RoleFunctionTreeNodePO toPO(RoleFunctionTreeNodeDTO src) {
		if (src == null)
		return null;	
		RoleFunctionTreeNodePO tar = new RoleFunctionTreeNodePO();
		tar.setId(src.getId());
		tar.setRoleId(src.getRoleId());
		tar.setFunctionTreeNodeId(src.getFunctionTreeNodeId());
		tar.setFullCheck(src.getFullCheck());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setUpdateTime(src.getUpdateTime());
		//tar.setFunctionModuleId(src.getFunctionModuleId());
		return tar;
	}

	public static List<RoleFunctionTreeNodeDTO> toDTO(List<RoleFunctionTreeNodePO> srcs) {
		if (srcs == null)
			return null;
		List<RoleFunctionTreeNodeDTO> list = new ArrayList<RoleFunctionTreeNodeDTO>();
		for (RoleFunctionTreeNodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<RoleFunctionTreeNodePO> toPO(List<RoleFunctionTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<RoleFunctionTreeNodePO> list = new ArrayList<RoleFunctionTreeNodePO>();
		for (RoleFunctionTreeNodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	