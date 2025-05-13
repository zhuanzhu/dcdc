package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.product.po.StoreTreeNodePO;
import com.egeo.components.product.vo.StoreTreeNodeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-09-10 09:50:02
 */
public class StoreTreeNodeConverter {

	
	public static StoreTreeNodeDTO toDTO(StoreTreeNodeVO src) {
		if (src == null)
		return null;	
		StoreTreeNodeDTO tar = new StoreTreeNodeDTO();
		tar.setId(src.getNodeId());
		tar.setParentId(src.getParentId());	
		tar.setPids(src.getPids());	
		tar.setStoreTreeId(src.getStoreTreeId());	
		tar.setStoreId(src.getStoreId());	
		tar.setListSort(src.getListSort());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StoreTreeNodeVO toVO(StoreTreeNodeDTO src) {
		if (src == null)
		return null;	
		StoreTreeNodeVO tar = new StoreTreeNodeVO();
		tar.setNodeId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setPids(src.getPids());	
		tar.setStoreTreeId(src.getStoreTreeId());	
		tar.setStoreId(src.getStoreId());	
		tar.setListSort(src.getListSort());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StoreTreeNodeDTO> toDTOs(List<StoreTreeNodeVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreTreeNodeDTO> list = new ArrayList<StoreTreeNodeDTO>();
		for (StoreTreeNodeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreTreeNodeVO> toVO(List<StoreTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreTreeNodeVO> list = new ArrayList<StoreTreeNodeVO>();
		for (StoreTreeNodeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StoreTreeNodeDTO toDTO(StoreTreeNodePO src) {
		if (src == null)
		return null;	
		StoreTreeNodeDTO tar = new StoreTreeNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setPids(src.getPids());
		tar.setStoreTreeId(src.getStoreTreeId());
		tar.setStoreId(src.getStoreId());
		tar.setListSort(src.getListSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StoreTreeNodePO toPO(StoreTreeNodeDTO src) {
		if (src == null)
		return null;	
		StoreTreeNodePO tar = new StoreTreeNodePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setPids(src.getPids());
		tar.setStoreTreeId(src.getStoreTreeId());
		tar.setStoreId(src.getStoreId());
		tar.setListSort(src.getListSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StoreTreeNodeDTO> toDTO(List<StoreTreeNodePO> srcs) {
		if (srcs == null)
			return null;
		List<StoreTreeNodeDTO> list = new ArrayList<StoreTreeNodeDTO>();
		for (StoreTreeNodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreTreeNodePO> toPO(List<StoreTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreTreeNodePO> list = new ArrayList<StoreTreeNodePO>();
		for (StoreTreeNodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	