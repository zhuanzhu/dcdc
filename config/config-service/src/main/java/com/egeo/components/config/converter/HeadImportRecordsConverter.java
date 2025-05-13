package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.po.HeadImportRecordsPO;
import com.egeo.components.config.vo.HeadImportRecordsVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-12 05:09:12
 */
public class HeadImportRecordsConverter {

	public static HeadImportRecordsDTO toDTO(HeadImportRecordsVO src) {
		if (src == null)
		return null;	
		HeadImportRecordsDTO tar = new HeadImportRecordsDTO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());	
		tar.setTemplateType(src.getTemplateType());	
		tar.setGeneratedTime(src.getGeneratedTime());	
		tar.setFileSequenceNumber(src.getFileSequenceNumber());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static HeadImportRecordsVO toVO(HeadImportRecordsDTO src) {
		if (src == null)
		return null;	
		HeadImportRecordsVO tar = new HeadImportRecordsVO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());	
		tar.setTemplateType(src.getTemplateType());	
		tar.setGeneratedTime(src.getGeneratedTime());	
		tar.setFileSequenceNumber(src.getFileSequenceNumber());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	

	public static List<HeadImportRecordsVO> toVO(List<HeadImportRecordsDTO> srcs) {
		if (srcs == null)
			return null;
		List<HeadImportRecordsVO> list = new ArrayList<HeadImportRecordsVO>();
		for (HeadImportRecordsDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static HeadImportRecordsDTO toDTO(HeadImportRecordsPO src) {
		if (src == null)
		return null;	
		HeadImportRecordsDTO tar = new HeadImportRecordsDTO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());
		tar.setTemplateType(src.getTemplateType());
		tar.setGeneratedTime(src.getGeneratedTime());
		tar.setFileSequenceNumber(src.getFileSequenceNumber());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static HeadImportRecordsPO toPO(HeadImportRecordsDTO src) {
		if (src == null)
		return null;	
		HeadImportRecordsPO tar = new HeadImportRecordsPO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());
		tar.setTemplateType(src.getTemplateType());
		tar.setGeneratedTime(src.getGeneratedTime());
		tar.setFileSequenceNumber(src.getFileSequenceNumber());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<HeadImportRecordsDTO> toDTO(List<HeadImportRecordsPO> srcs) {
		if (srcs == null)
			return null;
		List<HeadImportRecordsDTO> list = new ArrayList<HeadImportRecordsDTO>();
		for (HeadImportRecordsPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<HeadImportRecordsPO> toPO(List<HeadImportRecordsDTO> srcs) {
		if (srcs == null)
			return null;
		List<HeadImportRecordsPO> list = new ArrayList<HeadImportRecordsPO>();
		for (HeadImportRecordsDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	