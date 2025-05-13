package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.vo.DepartmentUserVO;
import com.egeo.components.user.vo.UserExtendVO;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;

/**
 * DTO和PO相互转换工具类
 *
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class UserExtendConverter {

	public static UserExtendDTO toDTO(UserExtendVO src) {
		UserExtendDTO tar = new UserExtendDTO();
		tar.setId(src.getId());
		tar.setSex(src.getSex());
		tar.setNickname(src.getNickname());
		tar.setHeadPicUrl(src.getHeadPicUrl());
		tar.setName(src.getName());
		tar.setRegtime(src.getRegtime());
		tar.setBirthday(src.getBirthday());
		tar.setMobile(src.getMobile());
		tar.setQq(src.getQq());
		tar.setMemberCode(src.getMemberCode());
		tar.setRemark(src.getRemark());
		tar.setLastlogin(src.getLastlogin());
		tar.setActivityInfo(src.getActivityInfo());
		tar.setIsComplete(src.getIsComplete());
		tar.setDeviceId(src.getDeviceId());
		tar.setDeviceType(src.getDeviceType());
		tar.setWeixin(src.getWeixin());
		tar.setWorkaddressId(src.getWorkaddressId());
		tar.setStatus(src.getStatus());
		tar.setAccountStatus(src.getAccountStatus());
		tar.setAddress(src.getAddress());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setIsDeleted(src.getIsDeleted());
		tar.setChannelId(src.getChannelId());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
        tar.setChannelSource(src.getChannelSource());
        if (EmptyUtil.isNotEmpty(src.getEntryFinishTime())){
            tar.setEntryFinishTime(DateUtils.parseDate(src.getEntryFinishTime()));
        }
        if (EmptyUtil.isNotEmpty(src.getEntryStartTime())){
            tar.setEntryStartTime(DateUtils.parseDate(src.getEntryStartTime()));
        }
		return tar;
	}

	public static UserExtendVO toVO(UserExtendDTO src) {
		UserExtendVO tar = new UserExtendVO();
		tar.setId(src.getId());
		tar.setSex(src.getSex());
		tar.setNickname(src.getNickname());
		tar.setHeadPicUrl(src.getHeadPicUrl());
		tar.setName(src.getName());
		tar.setRegtime(src.getRegtime());
		tar.setBirthday(src.getBirthday());
		tar.setMobile(src.getMobile());
		tar.setQq(src.getQq());
		tar.setMemberCode(src.getMemberCode());
		tar.setRemark(src.getRemark());
		tar.setLastlogin(src.getLastlogin());
		tar.setActivityInfo(src.getActivityInfo());
		tar.setIsComplete(src.getIsComplete());
		tar.setDeviceId(src.getDeviceId());
		tar.setDeviceType(src.getDeviceType());
		tar.setWeixin(src.getWeixin());
		tar.setWorkaddressId(src.getWorkaddressId());
		tar.setStatus(src.getStatus());
		tar.setAccountStatus(src.getAccountStatus());
		tar.setAddress(src.getAddress());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setChannelId(src.getChannelId());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
        tar.setChannelSource(src.getChannelSource());
		return tar;
	}


	public static List<UserExtendVO> toVO(List<UserExtendDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserExtendVO> list = new ArrayList<UserExtendVO>();
		for (UserExtendDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}

	/**
	 * ueDTO转部门用户列表vo
	 * @param src
	 * @return
	 */
	public static DepartmentUserVO toDepartmentUserVO(UserExtendDTO src){
		if(src==null)
			return null;
		DepartmentUserVO tar=new DepartmentUserVO();
		tar.setUserId(src.getId());
		tar.setNickName(src.getNickname());
		return tar;
	}

	public static List<DepartmentUserVO> toDepartmentUserVO(List<UserExtendDTO> src){
		List<DepartmentUserVO> tar=new ArrayList<>();
		for(UserExtendDTO ue:src){
			tar.add(toDepartmentUserVO(ue));
		}
		return tar;
	}
	public static UserExtendDTO toDTO(UserExtendPO src) {
		if(src==null)
			return null;
		UserExtendDTO tar = new UserExtendDTO();
		tar.setId(src.getId());
		tar.setSex(src.getSex());
		tar.setNickname(src.getNickname());
		tar.setHeadPicUrl(src.getHeadPicUrl());
		tar.setName(src.getName());
		tar.setNamePy(src.getNamePy());
		tar.setRegtime(src.getRegtime());
		tar.setBirthday(src.getBirthday());
		tar.setMobile(src.getMobile());
		tar.setQq(src.getQq());
		tar.setMemberCode(src.getMemberCode());
		tar.setRemark(src.getRemark());
		tar.setLastlogin(src.getLastlogin());
		tar.setActivityInfo(src.getActivityInfo());
		tar.setIsComplete(src.getIsComplete());
		tar.setDeviceId(src.getDeviceId());
		tar.setDeviceType(src.getDeviceType());
		tar.setWeixin(src.getWeixin());
		tar.setWorkaddressId(src.getWorkaddressId());
		tar.setStatus(src.getStatus());
		tar.setAccountStatus(src.getAccountStatus());
		tar.setAddress(src.getAddress());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setBirthdayStartTime(src.getBirthdayStartTime());
		tar.setBirthdayFinishTime(src.getBirthdayFinishTime());
		tar.setEntryStartTime(src.getEntryStartTime());
		tar.setEntryFinishTime(src.getEntryFinishTime());
		tar.setCompanyId(src.getCompanyId());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setCompanyId(src.getCompanyId());
		tar.setMail(src.getMail());
		tar.setIsDeleted(src.getIsDeleted());
		tar.setQuitTime(src.getQuitTime());
		tar.setInvalidTime(src.getInvalidTime());
		tar.setChannelId(src.getChannelId());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setChannelSource(src.getChannelSource());
		tar.setIdCardNo(src.getIdCardNo());
		return tar;
	}

	public static UserExtendPO toPO(UserExtendDTO src) {
		if(src==null)
			return null;
		UserExtendPO tar = new UserExtendPO();
		tar.setId(src.getId());
		tar.setSex(src.getSex());
		tar.setNickname(src.getNickname());
		tar.setHeadPicUrl(src.getHeadPicUrl());
		tar.setName(src.getName());
		tar.setNamePy(src.getNamePy());
		tar.setRegtime(src.getRegtime());
		tar.setBirthday(src.getBirthday());
		tar.setMobile(src.getMobile());
		tar.setQq(src.getQq());
		tar.setMemberCode(src.getMemberCode());
		tar.setRemark(src.getRemark());
		tar.setLastlogin(src.getLastlogin());
		tar.setActivityInfo(src.getActivityInfo());
		tar.setIsComplete(src.getIsComplete());
		tar.setDeviceId(src.getDeviceId());
		tar.setDeviceType(src.getDeviceType());
		tar.setWeixin(src.getWeixin());
		tar.setWorkaddressId(src.getWorkaddressId());
		tar.setStatus(src.getStatus());
		tar.setAccountStatus(src.getAccountStatus());
		tar.setAddress(src.getAddress());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setBirthdayStartTime(src.getBirthdayStartTime());
		tar.setBirthdayFinishTime(src.getBirthdayFinishTime());
		tar.setEntryStartTime(src.getEntryStartTime());
		tar.setEntryFinishTime(src.getEntryFinishTime());
		tar.setCompanyId(src.getCompanyId());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setIsAdministrator(src.getIsAdministrator());
		tar.setCompanyId(src.getCompanyId());
		tar.setMail(src.getMail());
		tar.setIsDeleted(src.getIsDeleted());
		tar.setQuitTime(src.getQuitTime());
		tar.setInvalidTime(src.getInvalidTime());
		tar.setChannelId(src.getChannelId());
		tar.setCampaignCode(src.getCampaignCode());
		tar.setRegisterStoreId(src.getRegisterStoreId());
		tar.setImportRecordsId(src.getImportRecordsId());
		tar.setChannelSource(src.getChannelSource());
		tar.setIdCardNo(src.getIdCardNo());
		return tar;
	}

	public static List<UserExtendDTO> toDTO(List<UserExtendPO> srcs) {
		if (srcs == null)
			return null;
		List<UserExtendDTO> list = new ArrayList<UserExtendDTO>();
		for (UserExtendPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserExtendPO> toPO(List<UserExtendDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserExtendPO> list = new ArrayList<UserExtendPO>();
		for (UserExtendDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
