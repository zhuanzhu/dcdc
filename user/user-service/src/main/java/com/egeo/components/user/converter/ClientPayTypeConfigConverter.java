package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.ClientPayTypeConfigDTO;
import com.egeo.components.user.po.ClientPayTypeConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-08-30 17:15:02
 */
public class ClientPayTypeConfigConverter {
	
	public static ClientPayTypeConfigDTO toDTO(ClientPayTypeConfigPO src) {
		if (src == null)
		return null;	
		ClientPayTypeConfigDTO tar = new ClientPayTypeConfigDTO();
		tar.setId(src.getId());
		tar.setClientId(src.getClientId());
		tar.setPayTypeCode(src.getPayTypeCode());
		tar.setIndexCode(src.getIndexCode());
		tar.setIsStop(src.getIsStop());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ClientPayTypeConfigPO toPO(ClientPayTypeConfigDTO src) {
		if (src == null)
		return null;	
		ClientPayTypeConfigPO tar = new ClientPayTypeConfigPO();
		tar.setId(src.getId());
		tar.setClientId(src.getClientId());
		tar.setPayTypeCode(src.getPayTypeCode());
		tar.setIndexCode(src.getIndexCode());
		tar.setIsStop(src.getIsStop());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());

		return tar;
	}

	public static List<ClientPayTypeConfigDTO> toDTO(List<ClientPayTypeConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<ClientPayTypeConfigDTO> list = new ArrayList<ClientPayTypeConfigDTO>();
		for (ClientPayTypeConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ClientPayTypeConfigPO> toPO(List<ClientPayTypeConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<ClientPayTypeConfigPO> list = new ArrayList<ClientPayTypeConfigPO>();
		for (ClientPayTypeConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	