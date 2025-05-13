package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.WmsWarehouseDTO;
import com.egeo.components.stock.po.WmsWarehousePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 11:44:55
 */
public class WmsWarehouseConverter {
	
	public static WmsWarehouseDTO toDTO(WmsWarehousePO src) {
		if (src == null)
			return null;	
		WmsWarehouseDTO tar = new WmsWarehouseDTO();
		tar.setId(src.getId());
		tar.setShortName(src.getShortName());
		tar.setWarehouseName(src.getWarehouseName());
		tar.setWarehouseDesc(src.getWarehouseDesc());
		tar.setWarehouseType(src.getWarehouseType());
		tar.setWarehousestatus(src.getWarehousestatus());
		tar.setIsRealWarehouse(src.getIsRealWarehouse());
		tar.setStorageType(src.getStorageType());
		tar.setFunctionType(src.getFunctionType());
		tar.setIsSupportTran(src.getIsSupportTran());
		tar.setIsSupportSby(src.getIsSupportSby());
		tar.setFlex(src.getFlex());
		tar.setStockType(src.getStockType());
		tar.setIsOem(src.getIsOem());
		tar.setBusinessHours(src.getBusinessHours());
		tar.setCutOffTime(src.getCutOffTime());
		tar.setCountryId(src.getCountryId());
		tar.setProvinceId(src.getProvinceId());
		tar.setCityId(src.getCityId());
		tar.setCountyId(src.getCountyId());
		tar.setAddressName(src.getAddressName());
		tar.setWarehouseLongitude(src.getWarehouseLongitude());
		tar.setWarehouseLatitude(src.getWarehouseLatitude());
		tar.setWarehouseGroupEmail(src.getWarehouseGroupEmail());
		tar.setWarehousePhone(src.getWarehousePhone());
		tar.setWarehouseFax(src.getWarehouseFax());
		tar.setWarehouseContactor(src.getWarehouseContactor());
		tar.setWarehouseContactorMobile(src.getWarehouseContactorMobile());
		tar.setRtvDefaultExpress(src.getRtvDefaultExpress());
		tar.setRtvSpReceiveAddress(src.getRtvSpReceiveAddress());
		tar.setExpressRemark(src.getExpressRemark());
		tar.setReturnDutyPerson(src.getReturnDutyPerson());
		tar.setReturnPhone(src.getReturnPhone());
		tar.setReturnMobile(src.getReturnMobile());
		tar.setDoReturnDutyPerson(src.getDoReturnDutyPerson());
		tar.setDoReturnPhone(src.getDoReturnPhone());
		tar.setDoReturnMobile(src.getDoReturnMobile());
		tar.setCustomerReturnAddress(src.getCustomerReturnAddress());
		tar.setWarehouseReamrk(src.getWarehouseReamrk());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static WmsWarehousePO toPO(WmsWarehouseDTO src) {
		if (src == null)
			return null;	
		WmsWarehousePO tar = new WmsWarehousePO();
		tar.setId(src.getId());
		tar.setShortName(src.getShortName());
		tar.setWarehouseName(src.getWarehouseName());
		tar.setWarehouseDesc(src.getWarehouseDesc());
		tar.setWarehouseType(src.getWarehouseType());
		tar.setWarehousestatus(src.getWarehousestatus());
		tar.setIsRealWarehouse(src.getIsRealWarehouse());
		tar.setStorageType(src.getStorageType());
		tar.setFunctionType(src.getFunctionType());
		tar.setIsSupportTran(src.getIsSupportTran());
		tar.setIsSupportSby(src.getIsSupportSby());
		tar.setFlex(src.getFlex());
		tar.setStockType(src.getStockType());
		tar.setIsOem(src.getIsOem());
		tar.setBusinessHours(src.getBusinessHours());
		tar.setCutOffTime(src.getCutOffTime());
		tar.setCountryId(src.getCountryId());
		tar.setProvinceId(src.getProvinceId());
		tar.setCityId(src.getCityId());
		tar.setCountyId(src.getCountyId());
		tar.setAddressName(src.getAddressName());
		tar.setWarehouseLongitude(src.getWarehouseLongitude());
		tar.setWarehouseLatitude(src.getWarehouseLatitude());
		tar.setWarehouseGroupEmail(src.getWarehouseGroupEmail());
		tar.setWarehousePhone(src.getWarehousePhone());
		tar.setWarehouseFax(src.getWarehouseFax());
		tar.setWarehouseContactor(src.getWarehouseContactor());
		tar.setWarehouseContactorMobile(src.getWarehouseContactorMobile());
		tar.setRtvDefaultExpress(src.getRtvDefaultExpress());
		tar.setRtvSpReceiveAddress(src.getRtvSpReceiveAddress());
		tar.setExpressRemark(src.getExpressRemark());
		tar.setReturnDutyPerson(src.getReturnDutyPerson());
		tar.setReturnPhone(src.getReturnPhone());
		tar.setReturnMobile(src.getReturnMobile());
		tar.setDoReturnDutyPerson(src.getDoReturnDutyPerson());
		tar.setDoReturnPhone(src.getDoReturnPhone());
		tar.setDoReturnMobile(src.getDoReturnMobile());
		tar.setCustomerReturnAddress(src.getCustomerReturnAddress());
		tar.setWarehouseReamrk(src.getWarehouseReamrk());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<WmsWarehouseDTO> toDTO(List<WmsWarehousePO> srcs) {
		if (srcs == null)
			return null;
		List<WmsWarehouseDTO> list = new ArrayList<WmsWarehouseDTO>();
		for (WmsWarehousePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<WmsWarehousePO> toPO(List<WmsWarehouseDTO> srcs) {
		if (srcs == null)
			return null;
		List<WmsWarehousePO> list = new ArrayList<WmsWarehousePO>();
		for (WmsWarehouseDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	