package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.po.ImportRecordsPO;
import com.egeo.components.config.vo.ImportRecordsVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-10 06:24:53
 */
public class ImportRecordsConverter {

	
	public static ImportRecordsDTO toDTO(ImportRecordsVO src) {
		if (src == null)
		return null;	
		ImportRecordsDTO tar = new ImportRecordsDTO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());	
		tar.setTemplateType(src.getTemplateType());	
		tar.setGeneratedTime(src.getGeneratedTime());	
		tar.setFileSequenceNumber(src.getFileSequenceNumber());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static ImportRecordsVO toVO(ImportRecordsDTO src) {
		if (src == null)
		return null;	
		ImportRecordsVO tar = new ImportRecordsVO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());	
		tar.setTemplateType(src.getTemplateType());	
		tar.setGeneratedTime(src.getGeneratedTime());	
		tar.setFileSequenceNumber(src.getFileSequenceNumber());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}



	public static List<ImportRecordsVO> toVO(List<ImportRecordsDTO> srcs) {
		if (srcs == null)
			return null;
		List<ImportRecordsVO> list = new ArrayList<ImportRecordsVO>();
		for (ImportRecordsDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ImportRecordsDTO toDTO(ImportRecordsPO src) {
		if (src == null)
		return null;	
		ImportRecordsDTO tar = new ImportRecordsDTO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());
		tar.setTemplateType(src.getTemplateType());
		tar.setGeneratedTime(src.getGeneratedTime());
		tar.setFileSequenceNumber(src.getFileSequenceNumber());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static ImportRecordsPO toPO(ImportRecordsDTO src) {
		if (src == null)
		return null;	
		ImportRecordsPO tar = new ImportRecordsPO();
		tar.setId(src.getId());
		tar.setCompanyName(src.getCompanyName());
		tar.setTemplateType(src.getTemplateType());
		tar.setGeneratedTime(src.getGeneratedTime());
		tar.setFileSequenceNumber(src.getFileSequenceNumber());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<ImportRecordsDTO> toDTO(List<ImportRecordsPO> srcs) {
		if (srcs == null)
			return null;
		List<ImportRecordsDTO> list = new ArrayList<ImportRecordsDTO>();
		for (ImportRecordsPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ImportRecordsPO> toPO(List<ImportRecordsDTO> srcs) {
		if (srcs == null)
			return null;
		List<ImportRecordsPO> list = new ArrayList<ImportRecordsPO>();
		for (ImportRecordsDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

}
	