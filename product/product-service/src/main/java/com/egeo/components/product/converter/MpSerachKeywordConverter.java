package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MpSerachKeywordDTO;
import com.egeo.components.product.po.MpSerachKeywordPO;
import com.egeo.components.product.vo.MpSerachKeywordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-06-29 18:16:47
 */
public class MpSerachKeywordConverter {
	
	public static MpSerachKeywordDTO toDTO(MpSerachKeywordVO src) {
		if (src == null)
		return null;	
		MpSerachKeywordDTO tar = new MpSerachKeywordDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setKeyword(src.getKeyword());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static MpSerachKeywordVO toVO(MpSerachKeywordDTO src) {
		if (src == null)
		return null;	
		MpSerachKeywordVO tar = new MpSerachKeywordVO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setKeyword(src.getKeyword());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<MpSerachKeywordDTO> toDTOs(List<MpSerachKeywordVO> srcs) {
		if (srcs == null)
			return null;
		List<MpSerachKeywordDTO> list = new ArrayList<MpSerachKeywordDTO>();
		for (MpSerachKeywordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MpSerachKeywordVO> toVO(List<MpSerachKeywordDTO> srcs) {
		if (srcs == null)
			return null;
		List<MpSerachKeywordVO> list = new ArrayList<MpSerachKeywordVO>();
		for (MpSerachKeywordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MpSerachKeywordDTO toDTO(MpSerachKeywordPO src) {
		if (src == null)
		return null;	
		MpSerachKeywordDTO tar = new MpSerachKeywordDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setKeyword(src.getKeyword());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MpSerachKeywordPO toPO(MpSerachKeywordDTO src) {
		if (src == null)
		return null;	
		MpSerachKeywordPO tar = new MpSerachKeywordPO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setKeyword(src.getKeyword());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MpSerachKeywordDTO> toDTO(List<MpSerachKeywordPO> srcs) {
		if (srcs == null)
			return null;
		List<MpSerachKeywordDTO> list = new ArrayList<MpSerachKeywordDTO>();
		for (MpSerachKeywordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MpSerachKeywordPO> toPO(List<MpSerachKeywordDTO> srcs) {
		if (srcs == null)
			return null;
		List<MpSerachKeywordPO> list = new ArrayList<MpSerachKeywordPO>();
		for (MpSerachKeywordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	