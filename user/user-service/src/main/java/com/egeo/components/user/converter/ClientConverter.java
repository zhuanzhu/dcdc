package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.po.ClientPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 16:04:43
 */
public class ClientConverter {
	
	public static ClientDTO toDTO(ClientPO src) {
		if (src == null)
		return null;	
		ClientDTO tar = new ClientDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setClientPayTypeRemarks(src.getClientPayTypeRemarks());
		return tar;
	}

	public static ClientPO toPO(ClientDTO src) {
		if (src == null)
		return null;	
		ClientPO tar = new ClientPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setClientPayTypeRemarks(src.getClientPayTypeRemarks());
		return tar;
	}

	public static List<ClientDTO> toDTO(List<ClientPO> srcs) {
		if (srcs == null)
			return null;
		List<ClientDTO> list = new ArrayList<ClientDTO>();
		for (ClientPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ClientPO> toPO(List<ClientDTO> srcs) {
		if (srcs == null)
			return null;
		List<ClientPO> list = new ArrayList<ClientPO>();
		for (ClientDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	