package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantSeriesDTO;
import com.egeo.components.product.po.MerchantSeriesPO;
import com.egeo.components.product.vo.MerchantSeriesVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantSeriesConverter {
	
	public static MerchantSeriesDTO toDTO(MerchantSeriesVO src) {
		MerchantSeriesDTO tar = new MerchantSeriesDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());	
		tar.setMainMerchantProductId(src.getMainMerchantProductId());	
		tar.setName(src.getName());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCode(src.getCode());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MerchantSeriesVO toVO(MerchantSeriesDTO src) {
		MerchantSeriesVO tar = new MerchantSeriesVO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());		
		tar.setMainMerchantProductId(src.getMainMerchantProductId());		
		tar.setName(src.getName());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCode(src.getCode());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<MerchantSeriesDTO> toDTOs(List<MerchantSeriesVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantSeriesDTO> list = new ArrayList<MerchantSeriesDTO>();
		for (MerchantSeriesVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantSeriesVO> toVO(List<MerchantSeriesDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantSeriesVO> list = new ArrayList<MerchantSeriesVO>();
		for (MerchantSeriesDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantSeriesDTO toDTO(MerchantSeriesPO src) {
		MerchantSeriesDTO tar = new MerchantSeriesDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setMainMerchantProductId(src.getMainMerchantProductId());
		tar.setName(src.getName());
		tar.setPlatformId(src.getPlatformId());
		tar.setCode(src.getCode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantSeriesPO toPO(MerchantSeriesDTO src) {
		MerchantSeriesPO tar = new MerchantSeriesPO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setMainMerchantProductId(src.getMainMerchantProductId());
		tar.setName(src.getName());
		tar.setPlatformId(src.getPlatformId());
		tar.setCode(src.getCode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantSeriesDTO> toDTO(List<MerchantSeriesPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantSeriesDTO> list = new ArrayList<MerchantSeriesDTO>();
		for (MerchantSeriesPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantSeriesPO> toPO(List<MerchantSeriesDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantSeriesPO> list = new ArrayList<MerchantSeriesPO>();
		for (MerchantSeriesDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	