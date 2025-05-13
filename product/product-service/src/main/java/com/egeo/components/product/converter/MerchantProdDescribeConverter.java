package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.components.product.po.MerchantProdDescribePO;
import com.egeo.components.product.vo.MerchantProdDescribeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class MerchantProdDescribeConverter {

	public static MerchantProdDescribeDTO toDTO(MerchantProdDescribeVO src) {
		if (src == null)
		return null;	
		MerchantProdDescribeDTO tar = new MerchantProdDescribeDTO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		return tar;
	}

	public static MerchantProdDescribeVO toVO(MerchantProdDescribeDTO src) {
		if (src == null)
		return null;	
		MerchantProdDescribeVO tar = new MerchantProdDescribeVO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setMerchantProductId(src.getMerchantProductId());	
		return tar;
	}

	public static List<MerchantProdDescribeDTO> toDTOs(List<MerchantProdDescribeVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdDescribeDTO> list = new ArrayList<MerchantProdDescribeDTO>();
		for (MerchantProdDescribeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdDescribeVO> toVO(List<MerchantProdDescribeDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdDescribeVO> list = new ArrayList<MerchantProdDescribeVO>();
		for (MerchantProdDescribeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProdDescribeDTO toDTO(MerchantProdDescribePO src) {
		if (src == null)
		return null;	
		MerchantProdDescribeDTO tar = new MerchantProdDescribeDTO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProductId(src.getMerchantProductId());
		return tar;
	}

	public static MerchantProdDescribePO toPO(MerchantProdDescribeDTO src) {
		if (src == null)
		return null;	
		MerchantProdDescribePO tar = new MerchantProdDescribePO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setMerchantProductId(src.getMerchantProductId());
		return tar;
	}

	public static List<MerchantProdDescribeDTO> toDTO(List<MerchantProdDescribePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdDescribeDTO> list = new ArrayList<MerchantProdDescribeDTO>();
		for (MerchantProdDescribePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProdDescribePO> toPO(List<MerchantProdDescribeDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProdDescribePO> list = new ArrayList<MerchantProdDescribePO>();
		for (MerchantProdDescribeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	