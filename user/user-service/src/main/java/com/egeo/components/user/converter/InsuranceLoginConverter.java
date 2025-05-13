package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.InsuranceLoginDTO;
import com.egeo.components.user.po.InsuranceLoginPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-05-11 14:46:11
 */
public class InsuranceLoginConverter {
	
	public static InsuranceLoginDTO toDTO(InsuranceLoginPO src) {
		if (src == null)
		return null;	
		InsuranceLoginDTO tar = new InsuranceLoginDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setLoginName(src.getLoginName());
		tar.setPassword(src.getPassword());
		tar.setLoginTimes(src.getLoginTimes());
		tar.setLastLogin(src.getLastLogin());
		tar.setCardno(src.getCardno());
		tar.setNickname(src.getNickname());
		tar.setShopname(src.getShopname());
		tar.setHeaderurl(src.getHeaderurl());
		tar.setDccuid(src.getDccuid());
		tar.setMemberid(src.getMemberid());
		tar.setOpenid(src.getOpenid());
		tar.setUserno(src.getUserno());
		tar.setMid(src.getMid());
		tar.setVsId(src.getVsId());
		tar.setMembercode(src.getMembercode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setDccucode(src.getDccucode());
		return tar;
	}

	public static InsuranceLoginPO toPO(InsuranceLoginDTO src) {
		if (src == null)
		return null;	
		InsuranceLoginPO tar = new InsuranceLoginPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setLoginName(src.getLoginName());
		tar.setPassword(src.getPassword());
		tar.setLoginTimes(src.getLoginTimes());
		tar.setLastLogin(src.getLastLogin());
		tar.setCardno(src.getCardno());
		tar.setNickname(src.getNickname());
		tar.setShopname(src.getShopname());
		tar.setHeaderurl(src.getHeaderurl());
		tar.setDccuid(src.getDccuid());
		tar.setMemberid(src.getMemberid());
		tar.setOpenid(src.getOpenid());
		tar.setUserno(src.getUserno());
		tar.setMid(src.getMid());
		tar.setVsId(src.getVsId());
		tar.setMembercode(src.getMembercode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setDccucode(src.getDccucode());
		return tar;
	}

	public static List<InsuranceLoginDTO> toDTO(List<InsuranceLoginPO> srcs) {
		if (srcs == null)
			return null;
		List<InsuranceLoginDTO> list = new ArrayList<InsuranceLoginDTO>();
		for (InsuranceLoginPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<InsuranceLoginPO> toPO(List<InsuranceLoginDTO> srcs) {
		if (srcs == null)
			return null;
		List<InsuranceLoginPO> list = new ArrayList<InsuranceLoginPO>();
		for (InsuranceLoginDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	