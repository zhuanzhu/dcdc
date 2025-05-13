package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantCateTreeNodeDTO;
import com.egeo.components.product.po.MerchantCateTreeNodePO;
import com.egeo.components.product.vo.MerchantCateTreeNodeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantCateTreeNodeConverter {
	
	
	public static MerchantCateTreeNodeDTO toDTO(MerchantCateTreeNodeVO src) {
		MerchantCateTreeNodeDTO tar = new MerchantCateTreeNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setMerchantCatTreeId(src.getMerchantCatTreeId());	
		tar.setCategoryId(src.getCategoryId());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantCateTreeNodeVO toVO(MerchantCateTreeNodeDTO src) {
		MerchantCateTreeNodeVO tar = new MerchantCateTreeNodeVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());		
		tar.setMerchantCatTreeId(src.getMerchantCatTreeId());		
		tar.setCategoryId(src.getCategoryId());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantCateTreeNodeDTO> toDTOs(List<MerchantCateTreeNodeVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantCateTreeNodeDTO> list = new ArrayList<MerchantCateTreeNodeDTO>();
		for (MerchantCateTreeNodeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantCateTreeNodeVO> toVO(List<MerchantCateTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantCateTreeNodeVO> list = new ArrayList<MerchantCateTreeNodeVO>();
		for (MerchantCateTreeNodeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantCateTreeNodeDTO toDTO(MerchantCateTreeNodePO src) {
		MerchantCateTreeNodeDTO tar = new MerchantCateTreeNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setMerchantCatTreeId(src.getMerchantCatTreeId());
		tar.setCategoryId(src.getCategoryId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantCateTreeNodePO toPO(MerchantCateTreeNodeDTO src) {
		MerchantCateTreeNodePO tar = new MerchantCateTreeNodePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setMerchantCatTreeId(src.getMerchantCatTreeId());
		tar.setCategoryId(src.getCategoryId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantCateTreeNodeDTO> toDTO(List<MerchantCateTreeNodePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantCateTreeNodeDTO> list = new ArrayList<MerchantCateTreeNodeDTO>();
		for (MerchantCateTreeNodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantCateTreeNodePO> toPO(List<MerchantCateTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantCateTreeNodePO> list = new ArrayList<MerchantCateTreeNodePO>();
		for (MerchantCateTreeNodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	