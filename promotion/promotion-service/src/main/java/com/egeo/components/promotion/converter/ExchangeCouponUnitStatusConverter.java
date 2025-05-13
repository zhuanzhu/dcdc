package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;
import com.egeo.components.promotion.vo.ExchangeCouponUnitStatusVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author feng
 * @date 2018-12-14 10:57:18
 */
public class ExchangeCouponUnitStatusConverter {

	public static ExchangeCouponUnitStatusDTO toDTO(ExchangeCouponUnitStatusVO src) {
		if (src == null)
		return null;	
		ExchangeCouponUnitStatusDTO tar = new ExchangeCouponUnitStatusDTO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());	
		tar.setAllowExchangeUnitStatus(src.getAllowExchangeUnitStatus());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ExchangeCouponUnitStatusVO toVO(ExchangeCouponUnitStatusDTO src) {
		if (src == null)
		return null;	
		ExchangeCouponUnitStatusVO tar = new ExchangeCouponUnitStatusVO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());	
		tar.setAllowExchangeUnitStatus(src.getAllowExchangeUnitStatus());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ExchangeCouponUnitStatusDTO> toDTOs(List<ExchangeCouponUnitStatusVO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeCouponUnitStatusDTO> list = new ArrayList<ExchangeCouponUnitStatusDTO>();
		for (ExchangeCouponUnitStatusVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExchangeCouponUnitStatusVO> toVO(List<ExchangeCouponUnitStatusDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeCouponUnitStatusVO> list = new ArrayList<ExchangeCouponUnitStatusVO>();
		for (ExchangeCouponUnitStatusDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ExchangeCouponUnitStatusDTO toDTO(ExchangeCouponUnitStatusPO src) {
		if (src == null)
		return null;	
		ExchangeCouponUnitStatusDTO tar = new ExchangeCouponUnitStatusDTO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());
		tar.setAllowExchangeUnitStatus(src.getAllowExchangeUnitStatus());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ExchangeCouponUnitStatusPO toPO(ExchangeCouponUnitStatusDTO src) {
		if (src == null)
		return null;	
		ExchangeCouponUnitStatusPO tar = new ExchangeCouponUnitStatusPO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());
		tar.setAllowExchangeUnitStatus(src.getAllowExchangeUnitStatus());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ExchangeCouponUnitStatusDTO> toDTO(List<ExchangeCouponUnitStatusPO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeCouponUnitStatusDTO> list = new ArrayList<ExchangeCouponUnitStatusDTO>();
		for (ExchangeCouponUnitStatusPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExchangeCouponUnitStatusPO> toPO(List<ExchangeCouponUnitStatusDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeCouponUnitStatusPO> list = new ArrayList<ExchangeCouponUnitStatusPO>();
		for (ExchangeCouponUnitStatusDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	