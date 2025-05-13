package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.condition.UserCondition;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.po.UserPO;
import com.egeo.components.user.vo.UserVO;

/**
 * DTO和PO相互转换工具类
 *
 * @author xiaping
 * @date 2017-08-19 11:18:51
 */
public class UserConverter {

	public static UserDTO toDTO(UserVO src) {
		if(src==null)
			return null;
		UserDTO tar = new UserDTO();
		tar.setId(src.getId());
		tar.setLoginName(src.getLoginName());
		tar.setName(src.getName());
		tar.setPassword(src.getPassword());
		tar.setMobile(src.getMobile());
		tar.setMail(src.getMail());
		tar.setQrcode(src.getQrcode());
		tar.setSalt(src.getSalt());
		tar.setUpdateSalt(src.getUpdateSalt());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setType(src.getType());
		tar.setBeginTime(src.getBeginTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setRegtime(src.getRegtime());
		tar.setStatus(src.getStatus());
		tar.setDeviceType(src.getDeviceType());
		tar.setDeviceId(src.getDeviceId());
		tar.setVersion(src.getVersion());
		tar.setQuitTime(src.getQuitTime());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
		tar.setChannelId(src.getChannelId());
		return tar;
	}

	public static UserDTO toDTO2(UserCondition src) {

		if(src==null)
			return null;
		UserDTO tar = new UserDTO();
		tar.setId(src.getId());
		tar.setLoginName(src.getLoginName());
		tar.setName(src.getName());
		tar.setPassword(src.getPassword());
		tar.setMobile(src.getMobile());
		tar.setMail(src.getMail());
		tar.setQrcode(src.getQrcode());
		tar.setSalt(src.getSalt());
		tar.setUpdateSalt(src.getUpdateSalt());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setType(src.getType());
		tar.setBeginTime(src.getBeginTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPaymentCode(src.getPaymentCode());
		tar.setPaymentCodeUuid(src.getPaymentCodeUuid());
		tar.setIsDeleted(src.getIsDeleted());
		tar.setDeviceType(src.getDeviceType());
		tar.setDeviceId(src.getDeviceId());
		tar.setVersion(src.getVersion());
		tar.setQuitTime(src.getQuitTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setWechatUserId(src.getWechatUserId());

		if(src.getEnterpriseId()!=null && src.getEnterpriseId()>0) {
			tar.setEnterpriseId(src.getEnterpriseId());
		}
		if(src.getType()!=null && src.getType()>0) {
			tar.setType(src.getType());
		}
		return tar;

	}

	public static UserDTO toDTO(UserPO src) {
		if(src==null)
			return null;
		UserDTO tar = new UserDTO();
		tar.setId(src.getId());
		tar.setLoginName(src.getLoginName());
		tar.setName(src.getName());
		tar.setPassword(src.getPassword());
		tar.setMobile(src.getMobile());
		tar.setMail(src.getMail());
		tar.setQrcode(src.getQrcode());
		tar.setSalt(src.getSalt());
		tar.setUpdateSalt(src.getUpdateSalt());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setType(src.getType());
		tar.setBeginTime(src.getBeginTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPaymentCode(src.getPaymentCode());
		tar.setPaymentCodeUuid(src.getPaymentCodeUuid());
		tar.setIsDeleted(src.getIsDeleted());
		tar.setDeviceType(src.getDeviceType());
		tar.setDeviceId(src.getDeviceId());
		tar.setVersion(src.getVersion());
		tar.setQuitTime(src.getQuitTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setWechatUserId(src.getWechatUserId());
		tar.setChannelSource(src.getChannelSource());

		if(src.getEnterpriseId()!=null && src.getEnterpriseId()>0) {
			tar.setEnterpriseId(src.getEnterpriseId());
		}
		if(src.getType()!=null && src.getType()>0) {
			tar.setType(src.getType());
		}
		tar.setChannelUserUnique(src.getChannelUserUnique());
		return tar;
	}

	public static UserVO toVO(UserDTO src) {
		if(src==null)
			return null;
		UserVO tar = new UserVO();
		tar.setId(src.getId());
		if(src.getEnterpriseId()!=null && src.getEnterpriseId()>0) {
			tar.setEnterpriseId(src.getEnterpriseId());
		}
		if(src.getType()!=null && src.getType()>=0) {
			tar.setType(src.getType());
		}
		tar.setLoginName(src.getLoginName());
		tar.setName(src.getName());
		tar.setPassword(src.getPassword());
		tar.setMobile(src.getMobile());
		tar.setMail(src.getMail());
		tar.setQrcode(src.getQrcode());
		tar.setSalt(src.getSalt());
		tar.setUpdateSalt(src.getUpdateSalt());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setType(src.getType());
		tar.setCompanyType(src.getCompanyType());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setBeginTime(src.getBeginTime());
        tar.setFinishTime(src.getFinishTime());
        tar.setPlatformId(src.getPlatformId());
        tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setRegtime(src.getRegtime());
		tar.setStatus(src.getStatus());
		tar.setDeviceType(src.getDeviceType());
		tar.setDeviceId(src.getDeviceId());
		tar.setVersion(src.getVersion());
		tar.setQuitTime(src.getQuitTime());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
		tar.setChannelSource(src.getChannelSource());
		tar.setChannelUserUnique(src.getChannelUserUnique());
		return tar;
	}

	public static List<UserVO> toVO(List<UserDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserVO> list = new ArrayList<UserVO>();
		for (UserDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static UserPO toPO(UserDTO src) {
		if(src==null)
			return null;
		UserPO tar = new UserPO();
		tar.setId(src.getId());
		tar.setLoginName(src.getLoginName());
		tar.setName(src.getName());
		tar.setPassword(src.getPassword());
		tar.setMobile(src.getMobile());
		tar.setMail(src.getMail());
		tar.setQrcode(src.getQrcode());
		tar.setSalt(src.getSalt());
		tar.setUpdateSalt(src.getUpdateSalt());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setType(src.getType());
		tar.setBeginTime(src.getBeginTime());
        tar.setFinishTime(src.getFinishTime());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setCreateTime(src.getCreateTime());
		tar.setName(src.getName());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPaymentCode(src.getPaymentCode());
		tar.setPaymentCodeUuid(src.getPaymentCodeUuid());
		tar.setIsDeleted(src.getIsDeleted());
		tar.setDeviceType(src.getDeviceType());
		tar.setDeviceId(src.getDeviceId());
		tar.setVersion(src.getVersion());
		tar.setQuitTime(src.getQuitTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
		tar.setChannelId(src.getChannelId());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setWechatUserId(src.getWechatUserId());
		tar.setChannelUserUnique(src.getChannelUserUnique());
		return tar;
	}

	public static List<UserDTO> toDTO(List<UserPO> srcs) {
		if (srcs == null)
			return null;
		List<UserDTO> list = new ArrayList<UserDTO>();
		for (UserPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserDTO> toDTO2(List<UserCondition> srcs) {
		if (srcs == null)
			return null;
		List<UserDTO> list = new ArrayList<UserDTO>();
		for (UserPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static List<UserPO> toPO(List<UserDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserPO> list = new ArrayList<UserPO>();
		for (UserDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
