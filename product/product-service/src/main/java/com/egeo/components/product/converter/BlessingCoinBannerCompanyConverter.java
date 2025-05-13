package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.BlessingCoinBannerCompanyDTO;
import com.egeo.components.product.po.BlessingCoinBannerCompanyPO;
import com.egeo.components.product.vo.BlessingCoinBannerCompanyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-25 14:11:58
 */
public class BlessingCoinBannerCompanyConverter {
	
	public static BlessingCoinBannerCompanyDTO toDTO(BlessingCoinBannerCompanyVO src) {
		if (src == null)
		return null;	
		BlessingCoinBannerCompanyDTO tar = new BlessingCoinBannerCompanyDTO();
		tar.setId(src.getId());
		tar.setBlessingCoinBannerId(src.getBlessingCoinBannerId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static BlessingCoinBannerCompanyVO toVO(BlessingCoinBannerCompanyDTO src) {
		if (src == null)
		return null;	
		BlessingCoinBannerCompanyVO tar = new BlessingCoinBannerCompanyVO();
		tar.setId(src.getId());
		tar.setBlessingCoinBannerId(src.getBlessingCoinBannerId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static List<BlessingCoinBannerCompanyDTO> toDTOs(List<BlessingCoinBannerCompanyVO> srcs) {
		if (srcs == null)
			return null;
		List<BlessingCoinBannerCompanyDTO> list = new ArrayList<BlessingCoinBannerCompanyDTO>();
		for (BlessingCoinBannerCompanyVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BlessingCoinBannerCompanyVO> toVO(List<BlessingCoinBannerCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<BlessingCoinBannerCompanyVO> list = new ArrayList<BlessingCoinBannerCompanyVO>();
		for (BlessingCoinBannerCompanyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static BlessingCoinBannerCompanyDTO toDTO(BlessingCoinBannerCompanyPO src) {
		if (src == null)
		return null;	
		BlessingCoinBannerCompanyDTO tar = new BlessingCoinBannerCompanyDTO();
		tar.setId(src.getId());
		tar.setBlessingCoinBannerId(src.getBlessingCoinBannerId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static BlessingCoinBannerCompanyPO toPO(BlessingCoinBannerCompanyDTO src) {
		if (src == null)
		return null;	
		BlessingCoinBannerCompanyPO tar = new BlessingCoinBannerCompanyPO();
		tar.setId(src.getId());
		tar.setBlessingCoinBannerId(src.getBlessingCoinBannerId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<BlessingCoinBannerCompanyDTO> toDTO(List<BlessingCoinBannerCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<BlessingCoinBannerCompanyDTO> list = new ArrayList<BlessingCoinBannerCompanyDTO>();
		for (BlessingCoinBannerCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BlessingCoinBannerCompanyPO> toPO(List<BlessingCoinBannerCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<BlessingCoinBannerCompanyPO> list = new ArrayList<BlessingCoinBannerCompanyPO>();
		for (BlessingCoinBannerCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	