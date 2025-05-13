package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.condition.FunctionTreeNodeUrlCondition;
import com.egeo.components.user.dto.FunctionTreeNodeUrlDTO;
import com.egeo.components.user.po.FunctionTreeNodeUrlPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-11-13 11:50:21
 */
public class FunctionTreeNodeUrlConverter {
	
	public static FunctionTreeNodeUrlDTO toDTO(FunctionTreeNodeUrlPO src) {
		if (src == null)
		return null;	
		FunctionTreeNodeUrlDTO tar = new FunctionTreeNodeUrlDTO();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setFunctionTreeNodeId(src.getFunctionTreeNodeId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}
	
	public static FunctionTreeNodeUrlDTO conditionToDTO(FunctionTreeNodeUrlCondition src) {
		if (src == null)
			return null;	
		FunctionTreeNodeUrlDTO tar = new FunctionTreeNodeUrlDTO();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setFunctionTreeNodeId(src.getFunctionTreeNodeId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setUrl(src.getUrl());
		tar.setUrlName(src.getUrlName());
		tar.setFunctionName(src.getFunctionName());
		return tar;
	}

	public static FunctionTreeNodeUrlPO toPO(FunctionTreeNodeUrlDTO src) {
		if (src == null)
		return null;	
		FunctionTreeNodeUrlPO tar = new FunctionTreeNodeUrlPO();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setFunctionTreeNodeId(src.getFunctionTreeNodeId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}
	
	public static FunctionTreeNodeUrlCondition toCondition(FunctionTreeNodeUrlDTO src) {
		if (src == null)
			return null;	
		FunctionTreeNodeUrlCondition tar = new FunctionTreeNodeUrlCondition();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setFunctionTreeNodeId(src.getFunctionTreeNodeId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setUrl(src.getUrl());
		tar.setUrlName(src.getUrlName());
		tar.setFunctionName(src.getFunctionName());
		return tar;
	}

	public static List<FunctionTreeNodeUrlDTO> conditionToDTO(List<FunctionTreeNodeUrlCondition> srcs) {
		if (srcs == null)
			return null;
		List<FunctionTreeNodeUrlDTO> list = new ArrayList<FunctionTreeNodeUrlDTO>();
		for (FunctionTreeNodeUrlCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
	
	public static List<FunctionTreeNodeUrlDTO> toDTO(List<FunctionTreeNodeUrlPO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionTreeNodeUrlDTO> list = new ArrayList<FunctionTreeNodeUrlDTO>();
		for (FunctionTreeNodeUrlPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FunctionTreeNodeUrlPO> toPO(List<FunctionTreeNodeUrlDTO> srcs) {
		if (srcs == null)
			return null;
		List<FunctionTreeNodeUrlPO> list = new ArrayList<FunctionTreeNodeUrlPO>();
		for (FunctionTreeNodeUrlDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	