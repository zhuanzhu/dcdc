package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.KeywordDTO;
import com.egeo.components.config.po.KeywordPO;
import com.egeo.components.config.vo.KeywordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-12-04 13:14:15
 */
public class KeywordConverter {

	public static KeywordDTO toDTO(KeywordVO src) {
		if (src == null)
		return null;	
		KeywordDTO tar = new KeywordDTO();
		tar.setId(src.getId());
		tar.setKeywordContent(src.getKeywordContent());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static KeywordVO toVO(KeywordDTO src) {
		if (src == null)
		return null;	
		KeywordVO tar = new KeywordVO();
		tar.setId(src.getId());
		tar.setKeywordContent(src.getKeywordContent());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	

	public static List<KeywordVO> toVO(List<KeywordDTO> srcs) {
		if (srcs == null)
			return null;
		List<KeywordVO> list = new ArrayList<KeywordVO>();
		for (KeywordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static KeywordDTO toDTO(KeywordPO src) {
		if (src == null)
		return null;	
		KeywordDTO tar = new KeywordDTO();
		tar.setId(src.getId());
		tar.setKeywordContent(src.getKeywordContent());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static KeywordPO toPO(KeywordDTO src) {
		if (src == null)
		return null;	
		KeywordPO tar = new KeywordPO();
		tar.setId(src.getId());
		tar.setKeywordContent(src.getKeywordContent());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<KeywordDTO> toDTO(List<KeywordPO> srcs) {
		if (srcs == null)
			return null;
		List<KeywordDTO> list = new ArrayList<KeywordDTO>();
		for (KeywordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<KeywordPO> toPO(List<KeywordDTO> srcs) {
		if (srcs == null)
			return null;
		List<KeywordPO> list = new ArrayList<KeywordPO>();
		for (KeywordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	