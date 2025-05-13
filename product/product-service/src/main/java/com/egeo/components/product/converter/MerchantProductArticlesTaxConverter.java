package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProductArticlesTaxDTO;
import com.egeo.components.product.po.MerchantProductArticlesTaxPO;
import com.egeo.components.product.vo.MerchantProductArticlesTaxVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantProductArticlesTaxConverter {
	
	public static MerchantProductArticlesTaxDTO toDTO(MerchantProductArticlesTaxVO src) {
		MerchantProductArticlesTaxDTO tar = new MerchantProductArticlesTaxDTO();
		tar.setId(src.getId());
		tar.setTaxNumber(src.getTaxNumber());	
		tar.setMerchantExplain(src.getMerchantExplain());	
		tar.setMerchantRange(src.getMerchantRange());	
		tar.setTaxRale(src.getTaxRale());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantProductArticlesTaxVO toVO(MerchantProductArticlesTaxDTO src) {
		MerchantProductArticlesTaxVO tar = new MerchantProductArticlesTaxVO();
		tar.setId(src.getId());
		tar.setTaxNumber(src.getTaxNumber());		
		tar.setMerchantExplain(src.getMerchantExplain());		
		tar.setMerchantRange(src.getMerchantRange());		
		tar.setTaxRale(src.getTaxRale());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantProductArticlesTaxDTO> toDTOs(List<MerchantProductArticlesTaxVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductArticlesTaxDTO> list = new ArrayList<MerchantProductArticlesTaxDTO>();
		for (MerchantProductArticlesTaxVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductArticlesTaxVO> toVO(List<MerchantProductArticlesTaxDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductArticlesTaxVO> list = new ArrayList<MerchantProductArticlesTaxVO>();
		for (MerchantProductArticlesTaxDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProductArticlesTaxDTO toDTO(MerchantProductArticlesTaxPO src) {
		MerchantProductArticlesTaxDTO tar = new MerchantProductArticlesTaxDTO();
		tar.setId(src.getId());
		tar.setTaxNumber(src.getTaxNumber());
		tar.setMerchantExplain(src.getMerchantExplain());
		tar.setMerchantRange(src.getMerchantRange());
		tar.setTaxRale(src.getTaxRale());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantProductArticlesTaxPO toPO(MerchantProductArticlesTaxDTO src) {
		MerchantProductArticlesTaxPO tar = new MerchantProductArticlesTaxPO();
		tar.setId(src.getId());
		tar.setTaxNumber(src.getTaxNumber());
		tar.setMerchantExplain(src.getMerchantExplain());
		tar.setMerchantRange(src.getMerchantRange());
		tar.setTaxRale(src.getTaxRale());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantProductArticlesTaxDTO> toDTO(List<MerchantProductArticlesTaxPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductArticlesTaxDTO> list = new ArrayList<MerchantProductArticlesTaxDTO>();
		for (MerchantProductArticlesTaxPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductArticlesTaxPO> toPO(List<MerchantProductArticlesTaxDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductArticlesTaxPO> list = new ArrayList<MerchantProductArticlesTaxPO>();
		for (MerchantProductArticlesTaxDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	