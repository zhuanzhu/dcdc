package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.po.ErCardRecordPO;
import com.egeo.components.promotion.vo.ErCardRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-11 07:18:27
 */
public class ErCardRecordConverter {

	public static ErCardRecordDTO toDTO(ErCardRecordVO src) {
		if (src == null)
		return null;	
		ErCardRecordDTO tar = new ErCardRecordDTO();
		tar.setId(src.getId());
		tar.setImportRecordsId(src.getImportRecordsId());	
		tar.setBatch(src.getBatch());	
		tar.setSkuId(src.getSkuId());	
		tar.setSkuName(src.getSkuName());	
		tar.setSkuSerialNumber(src.getSkuSerialNumber());	
		tar.setType(src.getType());	
		tar.setCardNumber(src.getCardNumber());	
		tar.setCardThick(src.getCardThick());	
		tar.setUuid(src.getUuid());	
		tar.setStartTime(src.getStartTime());	
		tar.setEndTime(src.getEndTime());	
		tar.setSource(src.getSource());	
		tar.setFaceValue(src.getFaceValue());	
		tar.setRemark(src.getRemark());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateUserip(src.getUpdateUserip());	
		tar.setUpdateUsermac(src.getUpdateUsermac());	
		tar.setClientVersionno(src.getClientVersionno());	
		tar.setIsValid(src.getIsValid());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setUserId(src.getUserId());
		tar.setUserLoginName(src.getUserLoginName());	
		tar.setIsAllocation(src.getIsAllocation());	
		tar.setAllocationTime(src.getAllocationTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setUserId(src.getUserId());
		return tar;
	}

	public static ErCardRecordVO toVO(ErCardRecordDTO src) {
		if (src == null)
		return null;	
		ErCardRecordVO tar = new ErCardRecordVO();
		tar.setId(src.getId());
		tar.setImportRecordsId(src.getImportRecordsId());	
		tar.setBatch(src.getBatch());	
		tar.setSkuId(src.getSkuId());	
		tar.setSkuName(src.getSkuName());	
		tar.setSkuSerialNumber(src.getSkuSerialNumber());	
		tar.setType(src.getType());	
		tar.setCardNumber(src.getCardNumber());	
		tar.setCardThick(src.getCardThick());	
		tar.setUuid(src.getUuid());	
		tar.setStartTime(src.getStartTime());	
		tar.setEndTime(src.getEndTime());	
		tar.setSource(src.getSource());	
		tar.setFaceValue(src.getFaceValue());	
		tar.setRemark(src.getRemark());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateUserip(src.getUpdateUserip());	
		tar.setUpdateUsermac(src.getUpdateUsermac());	
		tar.setClientVersionno(src.getClientVersionno());	
		tar.setIsValid(src.getIsValid());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setUserId(src.getUserId());
		tar.setUserLoginName(src.getUserLoginName());	
		tar.setIsAllocation(src.getIsAllocation());	
		tar.setAllocationTime(src.getAllocationTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setUserId(src.getUserId());
		return tar;
	}

	public static List<ErCardRecordDTO> toDTOs(List<ErCardRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<ErCardRecordDTO> list = new ArrayList<ErCardRecordDTO>();
		for (ErCardRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ErCardRecordVO> toVO(List<ErCardRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<ErCardRecordVO> list = new ArrayList<ErCardRecordVO>();
		for (ErCardRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ErCardRecordDTO toDTO(ErCardRecordPO src) {
		if (src == null)
		return null;	
		ErCardRecordDTO tar = new ErCardRecordDTO();
		tar.setId(src.getId());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setBatch(src.getBatch());
		tar.setSkuId(src.getSkuId());
		tar.setSkuName(src.getSkuName());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());
		tar.setType(src.getType());
		tar.setCardNumber(src.getCardNumber());
		tar.setCardThick(src.getCardThick());
		tar.setUuid(src.getUuid());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setSource(src.getSource());
		tar.setFaceValue(src.getFaceValue());
		tar.setRemark(src.getRemark());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setClientVersionno(src.getClientVersionno());
		tar.setIsValid(src.getIsValid());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setUserLoginName(src.getUserLoginName());
		tar.setIsAllocation(src.getIsAllocation());
		tar.setAllocationTime(src.getAllocationTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setUserId(src.getUserId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		return tar;
	}

	public static ErCardRecordPO toPO(ErCardRecordDTO src) {
		if (src == null)
		return null;	
		ErCardRecordPO tar = new ErCardRecordPO();
		tar.setId(src.getId());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setBatch(src.getBatch());
		tar.setSkuId(src.getSkuId());
		tar.setSkuName(src.getSkuName());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());
		tar.setType(src.getType());
		tar.setCardNumber(src.getCardNumber());
		tar.setCardThick(src.getCardThick());
		tar.setUuid(src.getUuid());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setSource(src.getSource());
		tar.setFaceValue(src.getFaceValue());
		tar.setRemark(src.getRemark());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setClientVersionno(src.getClientVersionno());
		tar.setIsValid(src.getIsValid());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setUserLoginName(src.getUserLoginName());
		tar.setIsAllocation(src.getIsAllocation());
		tar.setAllocationTime(src.getAllocationTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setUserId(src.getUserId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		return tar;
	}

	public static List<ErCardRecordDTO> toDTO(List<ErCardRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<ErCardRecordDTO> list = new ArrayList<ErCardRecordDTO>();
		for (ErCardRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ErCardRecordPO> toPO(List<ErCardRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<ErCardRecordPO> list = new ArrayList<ErCardRecordPO>();
		for (ErCardRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	