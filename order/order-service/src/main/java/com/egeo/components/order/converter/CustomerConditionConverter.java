package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.CustomerConditionDTO;
import com.egeo.components.order.po.CustomerConditionPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class CustomerConditionConverter {
	
	public static CustomerConditionDTO toDTO(CustomerConditionPO src) {
		CustomerConditionDTO tar = new CustomerConditionDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setConditionPoolId(src.getConditionPoolId());
		tar.setPlatfromConditionId(src.getPlatfromConditionId());
		tar.setMenuCode(src.getMenuCode());
		tar.setMenuName(src.getMenuName());
		tar.setPageName(src.getPageName());
		tar.setConditionValue(src.getConditionValue());
		tar.setConditionName(src.getConditionName());
		tar.setDefualtCondition(src.getDefualtCondition());
		tar.setViewAliasName(src.getViewAliasName());
		tar.setLayoutSort(src.getLayoutSort());
		tar.setActive(src.getActive());
		tar.setPlatfromActive(src.getPlatfromActive());
		tar.setIsDft(src.getIsDft());
		tar.setConditionType(src.getConditionType());
		tar.setPlatformId(src.getPlatformId());
		tar.setDataType(src.getDataType());
		tar.setGroupName(src.getGroupName());
		tar.setTbodyValue(src.getTbodyValue());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static CustomerConditionPO toPO(CustomerConditionDTO src) {
		CustomerConditionPO tar = new CustomerConditionPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setConditionPoolId(src.getConditionPoolId());
		tar.setPlatfromConditionId(src.getPlatfromConditionId());
		tar.setMenuCode(src.getMenuCode());
		tar.setMenuName(src.getMenuName());
		tar.setPageName(src.getPageName());
		tar.setConditionValue(src.getConditionValue());
		tar.setConditionName(src.getConditionName());
		tar.setDefualtCondition(src.getDefualtCondition());
		tar.setViewAliasName(src.getViewAliasName());
		tar.setLayoutSort(src.getLayoutSort());
		tar.setActive(src.getActive());
		tar.setPlatfromActive(src.getPlatfromActive());
		tar.setIsDft(src.getIsDft());
		tar.setConditionType(src.getConditionType());
		tar.setPlatformId(src.getPlatformId());
		tar.setDataType(src.getDataType());
		tar.setGroupName(src.getGroupName());
		tar.setTbodyValue(src.getTbodyValue());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<CustomerConditionDTO> toDTO(List<CustomerConditionPO> srcs) {
		if (srcs == null)
			return null;
		List<CustomerConditionDTO> list = new ArrayList<CustomerConditionDTO>();
		for (CustomerConditionPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CustomerConditionPO> toPO(List<CustomerConditionDTO> srcs) {
		if (srcs == null)
			return null;
		List<CustomerConditionPO> list = new ArrayList<CustomerConditionPO>();
		for (CustomerConditionDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	