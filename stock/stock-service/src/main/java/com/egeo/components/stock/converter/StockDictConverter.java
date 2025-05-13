package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.StockDictDTO;
import com.egeo.components.stock.po.StockDictPO;
import com.egeo.components.stock.vo.StockDictVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-13 16:49:27
 */
public class StockDictConverter {

	public static StockDictDTO toDTO(StockDictVO src) {
		if (src == null)
		return null;	
		StockDictDTO tar = new StockDictDTO();
		tar.setId(src.getId());
		tar.setType(src.getType());	
		tar.setCustomId(src.getCustomId());	
		tar.setName(src.getName());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StockDictVO toVO(StockDictDTO src) {
		if (src == null)
		return null;	
		StockDictVO tar = new StockDictVO();
		tar.setId(src.getId());
		tar.setType(src.getType());	
		tar.setCustomId(src.getCustomId());	
		tar.setName(src.getName());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StockDictDTO> toDTOs(List<StockDictVO> srcs) {
		if (srcs == null)
			return null;
		List<StockDictDTO> list = new ArrayList<StockDictDTO>();
		for (StockDictVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StockDictVO> toVO(List<StockDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<StockDictVO> list = new ArrayList<StockDictVO>();
		for (StockDictDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StockDictDTO toDTO(StockDictPO src) {
		if (src == null)
		return null;	
		StockDictDTO tar = new StockDictDTO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setCustomId(src.getCustomId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StockDictPO toPO(StockDictDTO src) {
		if (src == null)
		return null;	
		StockDictPO tar = new StockDictPO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setCustomId(src.getCustomId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StockDictDTO> toDTO(List<StockDictPO> srcs) {
		if (srcs == null)
			return null;
		List<StockDictDTO> list = new ArrayList<StockDictDTO>();
		for (StockDictPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StockDictPO> toPO(List<StockDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<StockDictPO> list = new ArrayList<StockDictPO>();
		for (StockDictDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	