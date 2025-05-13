package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.components.promotion.po.FuCoinPO;
import com.egeo.components.promotion.vo.FuCoinVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-11-10 15:43:53
 */
public class FuCoinConverter {

	public static FuCoinDTO toDTO(FuCoinVO src) {
		FuCoinDTO tar = new FuCoinDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setCoin(src.getCoin());	
		tar.setDescs(src.getDescs());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static FuCoinVO toVO(FuCoinDTO src) {
		FuCoinVO tar = new FuCoinVO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setCoin(src.getCoin());	
		tar.setDescs(src.getDescs());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<FuCoinDTO> toDTOs(List<FuCoinVO> srcs) {
		if (srcs == null)
			return null;
		List<FuCoinDTO> list = new ArrayList<FuCoinDTO>();
		for (FuCoinVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FuCoinVO> toVO(List<FuCoinDTO> srcs) {
		if (srcs == null)
			return null;
		List<FuCoinVO> list = new ArrayList<FuCoinVO>();
		for (FuCoinDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static FuCoinDTO toDTO(FuCoinPO src) {
		FuCoinDTO tar = new FuCoinDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setCoin(src.getCoin());
		tar.setDescs(src.getDescs());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static FuCoinPO toPO(FuCoinDTO src) {
		FuCoinPO tar = new FuCoinPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setCoin(src.getCoin());
		tar.setDescs(src.getDescs());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<FuCoinDTO> toDTO(List<FuCoinPO> srcs) {
		if (srcs == null)
			return null;
		List<FuCoinDTO> list = new ArrayList<FuCoinDTO>();
		for (FuCoinPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FuCoinPO> toPO(List<FuCoinDTO> srcs) {
		if (srcs == null)
			return null;
		List<FuCoinPO> list = new ArrayList<FuCoinPO>();
		for (FuCoinDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	