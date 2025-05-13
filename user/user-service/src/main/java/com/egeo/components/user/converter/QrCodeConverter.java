package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.condition.QrCodeCondition;
import com.egeo.components.user.dto.QrCodeDTO;
import com.egeo.components.user.po.QrCodePO;
import com.egeo.components.user.vo.QrCodeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-09-13 17:09:31
 */
public class QrCodeConverter {
	
	public static QrCodeDTO toDTO(QrCodePO src) {
		if (src == null)
		return null;	
		QrCodeDTO tar = new QrCodeDTO();
		tar.setId(src.getId());
		tar.setBranchId(src.getBranchId());
		tar.setClientId(src.getClientId());
		tar.setChannelId(src.getChannelId());
		tar.setCampaignId(src.getCampaignId());
		tar.setTypeId(src.getTypeId());
		tar.setRdid(src.getRdid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static 	QrCodeDTO conditionToDTO(QrCodeCondition src){
		if (src == null)
			return null;
		QrCodeDTO tar = new QrCodeDTO();
		tar.setId(src.getId());
		tar.setBranchId(src.getBranchId());
		tar.setClientId(src.getClientId());
		tar.setChannelId(src.getChannelId());
		tar.setCampaignId(src.getCampaignId());
		tar.setTypeId(src.getTypeId());
		tar.setRdid(src.getRdid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCampaignName(src.getActivityName());
		tar.setChannelName(src.getChannelName());
		tar.setCampaignCode(src.getShortCode());
		return tar;
	}
	public static QrCodePO toPO(QrCodeDTO src) {
		if (src == null)
		return null;	
		QrCodePO tar = new QrCodePO();
		tar.setId(src.getId());
		tar.setBranchId(src.getBranchId());
		tar.setClientId(src.getClientId());
		tar.setChannelId(src.getChannelId());
		tar.setCampaignId(src.getCampaignId());
		tar.setTypeId(src.getTypeId());
		tar.setRdid(src.getRdid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<QrCodeDTO> toDTO(List<QrCodePO> srcs) {
		if (srcs == null)
			return null;
		List<QrCodeDTO> list = new ArrayList<QrCodeDTO>();
		for (QrCodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static List<QrCodeDTO> conditionToDTO(List<QrCodeCondition> srcs) {
		if (srcs == null)
			return null;
		List<QrCodeDTO> list = new ArrayList<QrCodeDTO>();
		for (QrCodeCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
	public static List<QrCodePO> toPO(List<QrCodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<QrCodePO> list = new ArrayList<QrCodePO>();
		for (QrCodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	
	public static QrCodeDTO toDTO(QrCodeVO src) {
		if (src == null)
		return null;	
		QrCodeDTO tar = new QrCodeDTO();
		tar.setId(src.getId());
		tar.setBranchId(src.getBranchId());	
		tar.setClientId(src.getClientId());	
		tar.setChannelId(src.getChannelId());	
		tar.setCampaignId(src.getCampaignId());	
		tar.setTypeId(src.getTypeId());	
		tar.setRdid(src.getRdid());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		tar.setCampaignCode(src.getCampaignCode());
		return tar;
	}

	public static QrCodeVO toVO(QrCodeDTO src) {
		if (src == null)
		return null;	
		QrCodeVO tar = new QrCodeVO();
		tar.setId(src.getId());
		tar.setBranchId(src.getBranchId());	
		tar.setClientId(src.getClientId());	
		tar.setChannelId(src.getChannelId());	
		tar.setCampaignId(src.getCampaignId());	
		tar.setTypeId(src.getTypeId());	
		tar.setRdid(src.getRdid());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setType(src.getType());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}


	public static List<QrCodeVO> toVO(List<QrCodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<QrCodeVO> list = new ArrayList<QrCodeVO>();
		for (QrCodeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	