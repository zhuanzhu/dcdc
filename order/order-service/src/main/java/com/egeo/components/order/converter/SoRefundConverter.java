package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoRefundDTO;
import com.egeo.components.order.po.SoRefundPO;
import com.egeo.components.order.vo.SoRefundVO;

/**
 * DTO和PO相互转换工具类
 *
 * @author wyy
 * @date 2018-07-19 17:21:52
 */
public class SoRefundConverter {

	public static SoRefundDTO toDTO(SoRefundVO src) {
		if (src == null)
		return null;
		SoRefundDTO tar = new SoRefundDTO();
		tar.setId(src.getId());
		tar.setSoRefundCode(src.getSoRefundCode());
		tar.setSoId(src.getSoId());
		tar.setCreatorId(src.getCreatorId());
		tar.setFubiAccountBatchId(src.getFubiAccountBatchId());
		tar.setCashAccountBatchId(src.getCashAccountBatchId());
		tar.setSoRefundByFubi(src.getSoRefundByFubi());
		tar.setSoRefundByCash(src.getSoRefundByCash());
		tar.setSoRefundReason(src.getSoRefundReason());
		tar.setRemark(src.getRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUserId(src.getUserId());
		tar.setPlatformId(src.getPlatformId());
		tar.setThirdRefundCode(src.getThirdRefundCode());
		tar.setBizId(src.getBizId());
		tar.setRefundState(src.getRefundState());
		return tar;
	}

	public static SoRefundVO toVO(SoRefundDTO src) {
		if (src == null)
		return null;
		SoRefundVO tar = new SoRefundVO();
		tar.setId(src.getId());
		tar.setSoRefundCode(src.getSoRefundCode());
		tar.setSoId(src.getSoId());
		tar.setCreatorId(src.getCreatorId());
		tar.setFubiAccountBatchId(src.getFubiAccountBatchId());
		tar.setCashAccountBatchId(src.getCashAccountBatchId());
		tar.setSoRefundByFubi(src.getSoRefundByFubi());
		tar.setSoRefundByCash(src.getSoRefundByCash());
		tar.setSoRefundReason(src.getSoRefundReason());
		tar.setRemark(src.getRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUserId(src.getUserId());
		tar.setPlatformId(src.getPlatformId());
		tar.setThirdRefundCode(src.getThirdRefundCode());
		tar.setBizId(src.getBizId());
		tar.setRefundState(src.getRefundState());
		return tar;
	}

	public static List<SoRefundDTO> toDTOs(List<SoRefundVO> srcs) {
		if (srcs == null)
			return null;
		List<SoRefundDTO> list = new ArrayList<SoRefundDTO>();
		for (SoRefundVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoRefundVO> toVO(List<SoRefundDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoRefundVO> list = new ArrayList<SoRefundVO>();
		for (SoRefundDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SoRefundDTO toDTO(SoRefundPO src) {
		if (src == null)
		return null;
		SoRefundDTO tar = new SoRefundDTO();
		tar.setId(src.getId());
		tar.setSoRefundCode(src.getSoRefundCode());
		tar.setSoId(src.getSoId());
		tar.setCreatorId(src.getCreatorId());
		tar.setFubiAccountBatchId(src.getFubiAccountBatchId());
		tar.setCashAccountBatchId(src.getCashAccountBatchId());
		tar.setSoRefundByFubi(src.getSoRefundByFubi());
		tar.setSoRefundByCash(src.getSoRefundByCash());
		tar.setSoRefundReason(src.getSoRefundReason());
		tar.setRemark(src.getRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUserId(src.getUserId());
		tar.setPlatformId(src.getPlatformId());
		tar.setThirdRefundCode(src.getThirdRefundCode());
		tar.setBizId(src.getBizId());
		tar.setSoRefundByJidian(src.getSoRefundByJidian());
		tar.setJidianAccountBatchId(src.getJidianAccountBatchId());
		tar.setRefundState(src.getRefundState());
		tar.setBuyCardAccountBatchId(src.getBuyCardAccountBatchId());
		tar.setSoRefundByBuyCard(src.getSoRefundByBuyCard());
		return tar;
	}

	public static SoRefundPO toPO(SoRefundDTO src) {
		if (src == null)
		return null;
		SoRefundPO tar = new SoRefundPO();
		tar.setId(src.getId());
		tar.setSoRefundCode(src.getSoRefundCode());
		tar.setSoId(src.getSoId());
		tar.setCreatorId(src.getCreatorId());
		tar.setFubiAccountBatchId(src.getFubiAccountBatchId());
		tar.setCashAccountBatchId(src.getCashAccountBatchId());
		tar.setSoRefundByFubi(src.getSoRefundByFubi());
		tar.setSoRefundByCash(src.getSoRefundByCash());
		tar.setSoRefundReason(src.getSoRefundReason());
		tar.setRemark(src.getRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUserId(src.getUserId());
		tar.setPlatformId(src.getPlatformId());
		tar.setThirdRefundCode(src.getThirdRefundCode());
		tar.setBizId(src.getBizId());
		tar.setJidianAccountBatchId(src.getJidianAccountBatchId());
		tar.setSoRefundByJidian(src.getSoRefundByJidian());
		tar.setRefundState(src.getRefundState());
		tar.setBuyCardAccountBatchId(src.getBuyCardAccountBatchId());
		tar.setSoRefundByBuyCard(src.getSoRefundByBuyCard());
		return tar;
	}

	public static List<SoRefundDTO> toDTO(List<SoRefundPO> srcs) {
		if (srcs == null)
			return null;
		List<SoRefundDTO> list = new ArrayList<SoRefundDTO>();
		for (SoRefundPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoRefundPO> toPO(List<SoRefundDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoRefundPO> list = new ArrayList<SoRefundPO>();
		for (SoRefundDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
