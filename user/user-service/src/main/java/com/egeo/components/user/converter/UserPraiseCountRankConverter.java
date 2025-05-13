package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UserPraiseCountRankDTO;
import com.egeo.components.user.po.UserPraiseCountRankPO;

public class UserPraiseCountRankConverter {

	public static UserPraiseCountRankDTO toDTO(UserPraiseCountRankPO src) {
		if (src == null)
		return null;	
		UserPraiseCountRankDTO tar = new UserPraiseCountRankDTO();
		tar.setUserId(src.getUserId());
		tar.setPraiseCount(src.getPraiseCount());
		tar.setRank(src.getRank());
		return tar;
	}
	
	public static List<UserPraiseCountRankDTO> toDTO(List<UserPraiseCountRankPO> srcs) {
		if (srcs == null)
			return null;
		List<UserPraiseCountRankDTO> list = new ArrayList<>();
		for (UserPraiseCountRankPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
}
