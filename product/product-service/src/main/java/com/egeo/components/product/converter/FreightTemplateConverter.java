package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.components.product.po.FreightTemplatePO;
import com.egeo.components.product.vo.FreightTemplateVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-11 10:35:36
 */
public class FreightTemplateConverter {


	public static FreightTemplateDTO toDTO(FreightTemplateVO src) {
		if (src == null)
		return null;	
		FreightTemplateDTO tar = new FreightTemplateDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setShipmentsExplain(src.getShipmentsExplain());	
		tar.setIsExemption(src.getIsExemption());	
		tar.setIsValid(src.getIsValid());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static FreightTemplateVO toVO(FreightTemplateDTO src) {
		if (src == null)
		return null;	
		FreightTemplateVO tar = new FreightTemplateVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setShipmentsExplain(src.getShipmentsExplain());	
		tar.setIsExemption(src.getIsExemption());	
		tar.setIsValid(src.getIsValid());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static List<FreightTemplateDTO> toDTOs(List<FreightTemplateVO> srcs) {
		if (srcs == null)
			return null;
		List<FreightTemplateDTO> list = new ArrayList<FreightTemplateDTO>();
		for (FreightTemplateVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FreightTemplateVO> toVO(List<FreightTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<FreightTemplateVO> list = new ArrayList<FreightTemplateVO>();
		for (FreightTemplateDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static FreightTemplateDTO toDTO(FreightTemplatePO src) {
		if (src == null)
		return null;	
		FreightTemplateDTO tar = new FreightTemplateDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setMerchantId(src.getMerchantId());
		tar.setShipmentsExplain(src.getShipmentsExplain());
		tar.setIsExemption(src.getIsExemption());
		tar.setIsValid(src.getIsValid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static FreightTemplatePO toPO(FreightTemplateDTO src) {
		if (src == null)
		return null;	
		FreightTemplatePO tar = new FreightTemplatePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setMerchantId(src.getMerchantId());
		tar.setShipmentsExplain(src.getShipmentsExplain());
		tar.setIsExemption(src.getIsExemption());
		tar.setIsValid(src.getIsValid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static List<FreightTemplateDTO> toDTO(List<FreightTemplatePO> srcs) {
		if (srcs == null)
			return null;
		List<FreightTemplateDTO> list = new ArrayList<FreightTemplateDTO>();
		for (FreightTemplatePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FreightTemplatePO> toPO(List<FreightTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<FreightTemplatePO> list = new ArrayList<FreightTemplatePO>();
		for (FreightTemplateDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	