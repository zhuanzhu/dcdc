package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;
import com.egeo.components.product.vo.StandardUnitCombinationSuVO;

/**
 * DTO和PO相互转换工具类
 *
 * @author min
 * @date 2018-04-06 16:03:34
 */
public class StandardUnitCombinationSuConverter {

	public static StandardUnitCombinationSuDTO toDTO(StandardUnitCombinationSuPO src) {
		if (src == null)
		return null;
		StandardUnitCombinationSuDTO tar = new StandardUnitCombinationSuDTO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setSortValue(src.getSortValue());
		tar.setSortSalesNum(src.getSortSalesNum());
		tar.setSortPrice(src.getSortPrice());
		tar.setThirdSkuName(src.getThirdSkuName());
		tar.setSellState(src.getSellState());
		tar.setCheckTime(src.getCheckTime());
		return tar;
	}

	public static StandardUnitCombinationSuPO toPO(StandardUnitCombinationSuDTO src) {
		if (src == null)
		return null;
		StandardUnitCombinationSuPO tar = new StandardUnitCombinationSuPO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setSortValue(src.getSortValue());
		tar.setSellState(src.getSellState());
		tar.setCheckTime(src.getCheckTime());
		tar.setSortSalesNum(src.getSortSalesNum());
		tar.setSortPrice(src.getSortPrice());
		tar.setThirdSkuName(src.getThirdSkuName());
		return tar;
	}

	public static List<StandardUnitCombinationSuDTO> toDTO(List<StandardUnitCombinationSuPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationSuDTO> list = new ArrayList<StandardUnitCombinationSuDTO>();
		for (StandardUnitCombinationSuPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCombinationSuPO> toPO(List<StandardUnitCombinationSuDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationSuPO> list = new ArrayList<StandardUnitCombinationSuPO>();
		for (StandardUnitCombinationSuDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitCombinationSuDTO toDTO(StandardUnitCombinationSuVO src) {
		if (src == null)
		return null;
		StandardUnitCombinationSuDTO tar = new StandardUnitCombinationSuDTO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setSortValue(src.getSortValue());
		return tar;
	}

	public static StandardUnitCombinationSuVO toVO(StandardUnitCombinationSuDTO src) {
		if (src == null)
		return null;
		StandardUnitCombinationSuVO tar = new StandardUnitCombinationSuVO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setSortValue(src.getSortValue());
		return tar;
	}

	public static List<StandardUnitCombinationSuDTO> toDTOs(List<StandardUnitCombinationSuVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationSuDTO> list = new ArrayList<StandardUnitCombinationSuDTO>();
		for (StandardUnitCombinationSuVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCombinationSuVO> toVO(List<StandardUnitCombinationSuDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationSuVO> list = new ArrayList<StandardUnitCombinationSuVO>();
		for (StandardUnitCombinationSuDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
