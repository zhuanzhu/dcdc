package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationDTO;
import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.components.product.vo.StandardUnitCombinationVO;

/**
 * DTO和PO相互转换工具类
 *
 * @author min
 * @date 2018-04-06 16:43:24
 */
public class StandardUnitCombinationConverter {

	public static StandardUnitCombinationDTO toDTO(StandardUnitCombinationPO src) {
		if (src == null)
		return null;
		StandardUnitCombinationDTO tar = new StandardUnitCombinationDTO();
		tar.setId(src.getId());
		tar.setCombinationName(src.getCombinationName());
		tar.setType(src.getType());
		tar.setContent(src.getContent());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCategoryTreeId(src.getCategoryTreeId());
		tar.setSortType(src.getSortType());
		tar.setType(src.getType());
		tar.setCombinationMinProfit(src.getCombinationMinProfit());
		return tar;
	}

	public static StandardUnitCombinationPO toPO(StandardUnitCombinationDTO src) {
		if (src == null)
		return null;
		StandardUnitCombinationPO tar = new StandardUnitCombinationPO();
		tar.setId(src.getId());
		tar.setCombinationName(src.getCombinationName());
		tar.setType(src.getType());
		tar.setContent(src.getContent());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCategoryTreeId(src.getCategoryTreeId());
		tar.setSortType(src.getSortType());
		tar.setType(src.getType());
		tar.setTypes(src.getTypes());
		tar.setCombinationMinProfit(src.getCombinationMinProfit());
		return tar;
	}

	public static List<StandardUnitCombinationDTO> toDTO(List<StandardUnitCombinationPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationDTO> list = new ArrayList<StandardUnitCombinationDTO>();
		for (StandardUnitCombinationPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCombinationPO> toPO(List<StandardUnitCombinationDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationPO> list = new ArrayList<StandardUnitCombinationPO>();
		for (StandardUnitCombinationDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitCombinationDTO toDTO(StandardUnitCombinationVO src) {
		if (src == null)
		return null;
		StandardUnitCombinationDTO tar = new StandardUnitCombinationDTO();
		tar.setId(src.getId());
		tar.setCombinationName(src.getCombinationName());
		tar.setType(src.getType());
		tar.setContent(src.getContent());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCombinationMinProfit(src.getCombinationMinProfit());
		return tar;
	}

	public static StandardUnitCombinationVO toVO(StandardUnitCombinationDTO src) {
		if (src == null)
		return null;
		StandardUnitCombinationVO tar = new StandardUnitCombinationVO();
		tar.setId(src.getId());
		tar.setCombinationName(src.getCombinationName());
		tar.setType(src.getType());
		tar.setContent(src.getContent());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCombinationMinProfit(src.getCombinationMinProfit());
		return tar;
	}

	public static List<StandardUnitCombinationDTO> toDTOs(List<StandardUnitCombinationVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationDTO> list = new ArrayList<StandardUnitCombinationDTO>();
		for (StandardUnitCombinationVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCombinationVO> toVO(List<StandardUnitCombinationDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationVO> list = new ArrayList<StandardUnitCombinationVO>();
		for (StandardUnitCombinationDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
