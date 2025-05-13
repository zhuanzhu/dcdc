package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.po.MerchantPO;
import com.egeo.components.product.vo.MerchantVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-09 15:46:51
 */
public class MerchantConverter {

	public static MerchantDTO toDTO(MerchantVO src) {
		if (src == null)
		return null;	
		MerchantDTO tar = new MerchantDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setOperateUserId(src.getOperateUserId());
		tar.setOperateUserName(src.getOperateUserName());
		tar.setIsStop(src.getIsStop());
		return tar;
	}

	public static MerchantVO toVO(MerchantDTO src) {
		if (src == null)
		return null;	
		MerchantVO tar = new MerchantVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());
		tar.setOperateUserId(src.getOperateUserId());
		tar.setOperateUserName(src.getOperateUserName());
		tar.setIsStop(src.getIsStop());
		return tar;
	}

	public static List<MerchantDTO> toDTOs(List<MerchantVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantDTO> list = new ArrayList<MerchantDTO>();
		for (MerchantVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantVO> toVO(List<MerchantDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantVO> list = new ArrayList<MerchantVO>();
		for (MerchantDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantDTO toDTO(MerchantPO src) {
		if (src == null)
		return null;	
		MerchantDTO tar = new MerchantDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setOperateUserId(src.getOperateUserId());
		tar.setOperateUserName(src.getOperateUserName());
		tar.setIsStop(src.getIsStop());
		tar.setType(src.getType());
		return tar;
	}

	public static MerchantPO toPO(MerchantDTO src) {
		if (src == null)
		return null;	
		MerchantPO tar = new MerchantPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setOperateUserId(src.getOperateUserId());
		tar.setOperateUserName(src.getOperateUserName());
		tar.setIsStop(src.getIsStop());
		tar.setType(src.getType());

		return tar;
	}

	public static List<MerchantDTO> toDTO(List<MerchantPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantDTO> list = new ArrayList<MerchantDTO>();
		for (MerchantPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantPO> toPO(List<MerchantDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantPO> list = new ArrayList<MerchantPO>();
		for (MerchantDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	