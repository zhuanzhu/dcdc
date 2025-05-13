package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.AddressDTO;
import com.egeo.components.order.po.AddressPO;
import com.egeo.components.order.vo.AddressVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-09-14 15:19:51
 */
public class AddressConverter {

	public static AddressDTO toDTO(AddressVO src) {
		AddressDTO tar = new AddressDTO();
		tar.setId(src.getId());
		tar.setAddressName(src.getAddressName());	
		tar.setPid(src.getPid());	
		return tar;
	}

	public static AddressVO toVO(AddressDTO src) {
		AddressVO tar = new AddressVO();
		tar.setId(src.getId());
		tar.setAddressName(src.getAddressName());	
		tar.setPid(src.getPid());	
		return tar;
	}

	public static List<AddressDTO> toDTOs(List<AddressVO> srcs) {
		if (srcs == null)
			return null;
		List<AddressDTO> list = new ArrayList<AddressDTO>();
		for (AddressVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AddressVO> toVO(List<AddressDTO> srcs) {
		if (srcs == null)
			return null;
		List<AddressVO> list = new ArrayList<AddressVO>();
		for (AddressDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static AddressDTO toDTO(AddressPO src) {
		AddressDTO tar = new AddressDTO();
		tar.setId(src.getId());
		tar.setAddressName(src.getAddressName());
		tar.setPid(src.getPid());
		tar.setSortValue(src.getSortValue());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static AddressPO toPO(AddressDTO src) {
		AddressPO tar = new AddressPO();
		tar.setId(src.getId());
		tar.setAddressName(src.getAddressName());
		tar.setPid(src.getPid());
		tar.setSortValue(src.getSortValue());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<AddressDTO> toDTO(List<AddressPO> srcs) {
		if (srcs == null)
			return null;
		List<AddressDTO> list = new ArrayList<AddressDTO>();
		for (AddressPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AddressPO> toPO(List<AddressDTO> srcs) {
		if (srcs == null)
			return null;
		List<AddressPO> list = new ArrayList<AddressPO>();
		for (AddressDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	