package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.condition.ReceiverAddressCondition;
import com.egeo.components.order.dto.ReceiverAddressDTO;
import com.egeo.components.order.dto.ReceiverAddressDetailDTO;
import com.egeo.components.order.po.ReceiverAddressPO;
import com.egeo.components.order.vo.ReceiverAddressDetailVO;
import com.egeo.components.order.vo.ReceiverAddressVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-09-14 11:26:49
 */
public class ReceiverAddressConverter {

	
	public static ReceiverAddressDTO toDTO(ReceiverAddressVO src) {
		if(src==null)
			return null;
		ReceiverAddressDTO tar = new ReceiverAddressDTO();
		tar.setId(src.getId());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());	
		tar.setGoodReceiverPostcode(src.getGoodReceiverPostcode());	
		tar.setGoodReceiverName(src.getGoodReceiverName());	
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());	
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());	
		tar.setGoodReceiverCountryId(src.getGoodReceiverCountryId());	
		tar.setGoodReceiverCountry(src.getGoodReceiverCountry());	
		tar.setGoodReceiverProvinceId(src.getGoodReceiverProvinceId());	
		tar.setGoodReceiverProvince(src.getGoodReceiverProvince());	
		tar.setGoodReceiverCityId(src.getGoodReceiverCityId());	
		tar.setGoodReceiverCity(src.getGoodReceiverCity());	
		tar.setGoodReceiverCountyId(src.getGoodReceiverCountyId());	
		tar.setGoodReceiverCounty(src.getGoodReceiverCounty());	
		tar.setGoodReceiverAreaId(src.getGoodReceiverAreaId());	
		tar.setGoodReceiverArea(src.getGoodReceiverArea());	
		tar.setUserId(src.getUserId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setIsDefault(src.getIsDefault());	
		tar.setType(src.getType());
		tar.setModifyMail(src.getModifyMail());
		tar.setSoChildCode(src.getSoChildCode());
		return tar;
	}

	public static ReceiverAddressVO toVO(ReceiverAddressDTO src) {
		if(src==null)
			return null;
		ReceiverAddressVO tar = new ReceiverAddressVO();
		tar.setId(src.getId());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());	
		tar.setGoodReceiverPostcode(src.getGoodReceiverPostcode());	
		tar.setGoodReceiverName(src.getGoodReceiverName());	
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());	
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());	
		tar.setGoodReceiverCountryId(src.getGoodReceiverCountryId());	
		tar.setGoodReceiverCountry(src.getGoodReceiverCountry());	
		tar.setGoodReceiverProvinceId(src.getGoodReceiverProvinceId());	
		tar.setGoodReceiverProvince(src.getGoodReceiverProvince());	
		tar.setGoodReceiverCityId(src.getGoodReceiverCityId());	
		tar.setGoodReceiverCity(src.getGoodReceiverCity());	
		tar.setGoodReceiverCountyId(src.getGoodReceiverCountyId());	
		tar.setGoodReceiverCounty(src.getGoodReceiverCounty());	
		tar.setGoodReceiverAreaId(src.getGoodReceiverAreaId());	
		tar.setGoodReceiverArea(src.getGoodReceiverArea());	
		tar.setUserId(src.getUserId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setIsDefault(src.getIsDefault());	
		tar.setType(src.getType());
		tar.setModifyMail(src.getModifyMail());
		tar.setSoChildCode(src.getSoChildCode());
		return tar;
	}
	public static ReceiverAddressDetailVO toDetailVO(ReceiverAddressDetailDTO src) {
		if(src==null)
			return null;
		ReceiverAddressDetailVO tar = new ReceiverAddressDetailVO();
		
		tar.setId(src.getId());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());	
		tar.setGoodReceiverPostcode(src.getGoodReceiverPostcode());	
		tar.setGoodReceiverName(src.getGoodReceiverName());	
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());	
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());	
		tar.setGoodReceiverCountryId(src.getGoodReceiverCountryId());	
		tar.setGoodReceiverCountry(src.getGoodReceiverCountry());	
		tar.setGoodReceiverProvinceId(src.getGoodReceiverProvinceId());	
		tar.setGoodReceiverProvince(src.getGoodReceiverProvince());	
		tar.setGoodReceiverCityId(src.getGoodReceiverCityId());	
		tar.setGoodReceiverCity(src.getGoodReceiverCity());	
		tar.setGoodReceiverCountyId(src.getGoodReceiverCountyId());	
		tar.setGoodReceiverCounty(src.getGoodReceiverCounty());	
		tar.setGoodReceiverAreaId(src.getGoodReceiverAreaId());	
		tar.setGoodReceiverArea(src.getGoodReceiverArea());	
		tar.setUserId(src.getUserId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setIsDefault(src.getIsDefault());	
		tar.setType(src.getType());
		tar.setModifyMail(src.getModifyMail());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setModifyMail(src.getModifyMail());
		tar.setSoChildId(src.getSoChildId());
		
		return tar;
	}

	public static List<ReceiverAddressDTO> toDTOs(List<ReceiverAddressVO> srcs) {
		if (srcs == null)
			return null;
		List<ReceiverAddressDTO> list = new ArrayList<ReceiverAddressDTO>();
		for (ReceiverAddressVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ReceiverAddressVO> toVO(List<ReceiverAddressDTO> srcs) {
		if (srcs == null)
			return null;
		List<ReceiverAddressVO> list = new ArrayList<ReceiverAddressVO>();
		for (ReceiverAddressDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static List<ReceiverAddressDetailVO> toDetailVO(List<ReceiverAddressDetailDTO> srcs) {
		if (srcs == null)
			return null;
		List<ReceiverAddressDetailVO> list = new ArrayList<ReceiverAddressDetailVO>();
		for (ReceiverAddressDetailDTO src : srcs) {
			list.add(toDetailVO(src));
		}
		return list;
	}
	public static ReceiverAddressDTO toDTO(ReceiverAddressPO src) {
		if(src==null)
			return null;
		ReceiverAddressDTO tar = new ReceiverAddressDTO();
		tar.setId(src.getId());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setGoodReceiverPostcode(src.getGoodReceiverPostcode());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setGoodReceiverCountryId(src.getGoodReceiverCountryId());
		tar.setGoodReceiverCountry(src.getGoodReceiverCountry());
		tar.setGoodReceiverProvinceId(src.getGoodReceiverProvinceId());
		tar.setGoodReceiverProvince(src.getGoodReceiverProvince());
		tar.setGoodReceiverCityId(src.getGoodReceiverCityId());
		tar.setGoodReceiverCity(src.getGoodReceiverCity());
		tar.setGoodReceiverCountyId(src.getGoodReceiverCountyId());
		tar.setGoodReceiverCounty(src.getGoodReceiverCounty());
		tar.setGoodReceiverAreaId(src.getGoodReceiverAreaId());
		tar.setGoodReceiverArea(src.getGoodReceiverArea());
		tar.setUserId(src.getUserId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setIsDefault(src.getIsDefault());
		tar.setType(src.getType());
		tar.setModifyMail(src.getModifyMail());
		tar.setSoChildCode(src.getSoChildCode());

		return tar;
	}
	public static ReceiverAddressDetailDTO toDetailDTO(ReceiverAddressCondition src) {
		if(src==null)
			return null;
		ReceiverAddressDetailDTO tar = new ReceiverAddressDetailDTO();
		tar.setId(src.getId());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setGoodReceiverPostcode(src.getGoodReceiverPostcode());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setGoodReceiverCountryId(src.getGoodReceiverCountryId());
		tar.setGoodReceiverCountry(src.getGoodReceiverCountry());
		tar.setGoodReceiverProvinceId(src.getGoodReceiverProvinceId());
		tar.setGoodReceiverProvince(src.getGoodReceiverProvince());
		tar.setGoodReceiverCityId(src.getGoodReceiverCityId());
		tar.setGoodReceiverCity(src.getGoodReceiverCity());
		tar.setGoodReceiverCountyId(src.getGoodReceiverCountyId());
		tar.setGoodReceiverCounty(src.getGoodReceiverCounty());
		tar.setGoodReceiverAreaId(src.getGoodReceiverAreaId());
		tar.setGoodReceiverArea(src.getGoodReceiverArea());
		tar.setUserId(src.getUserId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setIsDefault(src.getIsDefault());
		tar.setType(src.getType());
		tar.setModifyMail(src.getModifyMail());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setModifyMail(src.getModifyMail());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setSoChildCode(src.getSoChildCode());
		tar.setSoChildId(src.getSoChildId());
		return tar;
	}

	public static ReceiverAddressPO toPO(ReceiverAddressDTO src) {
		if(src==null)
			return null;
		ReceiverAddressPO tar = new ReceiverAddressPO();
		tar.setId(src.getId());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setGoodReceiverPostcode(src.getGoodReceiverPostcode());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setGoodReceiverPhone(src.getGoodReceiverPhone());
		tar.setGoodReceiverCountryId(src.getGoodReceiverCountryId());
		tar.setGoodReceiverCountry(src.getGoodReceiverCountry());
		tar.setGoodReceiverProvinceId(src.getGoodReceiverProvinceId());
		tar.setGoodReceiverProvince(src.getGoodReceiverProvince());
		tar.setGoodReceiverCityId(src.getGoodReceiverCityId());
		tar.setGoodReceiverCity(src.getGoodReceiverCity());
		tar.setGoodReceiverCountyId(src.getGoodReceiverCountyId());
		tar.setGoodReceiverCounty(src.getGoodReceiverCounty());
		tar.setGoodReceiverAreaId(src.getGoodReceiverAreaId());
		tar.setGoodReceiverArea(src.getGoodReceiverArea());
		tar.setUserId(src.getUserId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setIsDefault(src.getIsDefault());
		tar.setType(src.getType());
		tar.setModifyMail(src.getModifyMail());
		tar.setSoChildCode(src.getSoChildCode());
		return tar;
	}

	public static List<ReceiverAddressDTO> toDTO(List<ReceiverAddressPO> srcs) {
		if (srcs == null)
			return null;
		List<ReceiverAddressDTO> list = new ArrayList<ReceiverAddressDTO>();
		for (ReceiverAddressPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static List<ReceiverAddressDetailDTO> toDetailDTO(List<ReceiverAddressCondition> srcs) {
		if (srcs == null)
			return null;
		List<ReceiverAddressDetailDTO> list = new ArrayList<ReceiverAddressDetailDTO>();
		for (ReceiverAddressCondition src : srcs) {
			list.add(toDetailDTO(src));
		}
		return list;
	}

	public static List<ReceiverAddressPO> toPO(List<ReceiverAddressDTO> srcs) {
		if (srcs == null)
			return null;
		List<ReceiverAddressPO> list = new ArrayList<ReceiverAddressPO>();
		for (ReceiverAddressDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	