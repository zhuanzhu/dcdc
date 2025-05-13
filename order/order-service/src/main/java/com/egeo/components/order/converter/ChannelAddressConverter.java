package com.egeo.components.order.converter;

import com.egeo.components.order.dto.CakeAddResultDTO;
import com.egeo.components.order.dto.ChannelAddressDTO;
import com.egeo.components.order.po.ChannelAddressPO;
import com.egeo.utils.EmptyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DTO和PO相互转换工具类
 *
 * @author min
 * @date 2017-09-14 15:19:51
 */
public class ChannelAddressConverter {


	public static ChannelAddressDTO toDTO(ChannelAddressPO src) {
		ChannelAddressDTO tar = new ChannelAddressDTO();
		tar.setId(src.getId());
		tar.setChannelCode(src.getChannelCode());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setUserId(src.getUserId());
		tar.setProvince(src.getProvince());
		tar.setCity(src.getCity());
		tar.setArea(src.getArea());
		tar.setAddr(src.getAddr());
		tar.setProvinceCode(src.getProvinceCode());
		tar.setCityId(src.getCityId());
		tar.setAreaCode(src.getAreaCode());
		tar.setChannelAddressId(src.getChannelAddressId());
		tar.setChannelUserId(src.getChannelUserId());
		tar.setChannelUserName(src.getChannelUserName());
		tar.setPhone(src.getPhone());
		tar.setIsDefault(src.getIsDefault());
		tar.setDeleted(src.getDeleted());
		tar.setLng(src.getLng());
		tar.setLat(src.getLat());
		tar.setLandmark(src.getLandmark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static ChannelAddressPO toPO(ChannelAddressDTO src) {
		ChannelAddressPO tar = new ChannelAddressPO();
		tar.setId(src.getId());
		tar.setChannelCode(src.getChannelCode());
		tar.setReceiverAddressId(src.getReceiverAddressId());
		tar.setUserId(src.getUserId());
		tar.setProvince(src.getProvince());
		tar.setCity(src.getCity());
		tar.setArea(src.getArea());
		tar.setAddr(src.getAddr());
		tar.setProvinceCode(src.getProvinceCode());
		tar.setCityId(src.getCityId());
		tar.setAreaCode(src.getAreaCode());
		tar.setChannelAddressId(src.getChannelAddressId());
		tar.setChannelUserId(src.getChannelUserId());
		tar.setChannelUserName(src.getChannelUserName());
		tar.setPhone(src.getPhone());
		tar.setIsDefault(src.getIsDefault());
		tar.setDeleted(src.getDeleted());
		tar.setLng(src.getLng());
		tar.setLat(src.getLat());
		tar.setLandmark(src.getLandmark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<ChannelAddressDTO> toDTO(List<ChannelAddressPO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelAddressDTO> list = new ArrayList<>();
		for (ChannelAddressPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ChannelAddressPO> toPO(List<ChannelAddressDTO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelAddressPO> list = new ArrayList<>();
		for (ChannelAddressDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static CakeAddResultDTO toCakeAddResultDTO(ChannelAddressPO src){
		if(Objects.isNull(src)){
			return null;
		}
		CakeAddResultDTO tar = new CakeAddResultDTO();
		tar.setId(src.getChannelAddressId());
		tar.setUser_id(src.getChannelUserId());
		tar.setName(src.getChannelUserName());
		tar.setProvince(src.getProvince());
		tar.setCity(src.getCity());
		tar.setCity_id(src.getCityId());
		tar.setArea(src.getArea());
		tar.setAddr(src.getAddr());
		tar.setPhone(src.getPhone());
		tar.setShip_time("0");
		tar.setIs_default(EmptyUtil.isNotEmpty(src.getIsDefault())?src.getIsDefault().toString():"0");
		tar.setLat(src.getLat());
		tar.setLng(src.getLng());
		tar.setLandmark(src.getLandmark());
		return tar;
	}

	public static ChannelAddressPO cakeAddResultToPO(CakeAddResultDTO src,String channelCode,Long userId,Long receiverAddressId){
		if(Objects.isNull(src)){
			return null;
		}
		ChannelAddressPO tar = new ChannelAddressPO();
		tar.setChannelCode(channelCode);
		tar.setReceiverAddressId(receiverAddressId);
		tar.setUserId(userId);
		tar.setProvince(src.getProvince());
		tar.setCity(src.getCity());
		tar.setArea(src.getArea());
		tar.setAddr(src.getAddr());
		tar.setCityId(src.getCity_id());
		tar.setChannelAddressId(src.getId());
		tar.setChannelUserName(src.getName());
		tar.setChannelUserId(src.getUser_id());
		tar.setPhone(src.getPhone());
		tar.setDeleted(0);
		tar.setIsDefault(EmptyUtil.isNotEmpty(src.getIs_default())?Integer.valueOf(src.getIs_default()):0);
		tar.setLat(src.getLat());
		tar.setLng(src.getLng());
		tar.setLandmark(src.getLandmark());
		return tar;
	}
}
