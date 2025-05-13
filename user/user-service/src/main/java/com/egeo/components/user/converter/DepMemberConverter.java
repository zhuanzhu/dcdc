package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.DepMemberDTO;
import com.egeo.components.user.po.DepMemberPO;
import com.egeo.components.user.vo.DepMemberVO;

public class DepMemberConverter {

	public static DepMemberDTO toDTO(DepMemberPO src){
		if(src==null)
			return null;
		DepMemberDTO tar=new DepMemberDTO();
		tar.setFace(src.getFace());
		tar.setId(src.getId());
		tar.setMail(src.getMail());
		tar.setName(src.getName());
		return tar;
	}
	
	public static List<DepMemberDTO> toDTO(List<DepMemberPO> srcs) {
		List<DepMemberDTO> list = new ArrayList<>();
		for (DepMemberPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static DepMemberVO toVO(DepMemberDTO src){
		if(src==null)
			return null;
		DepMemberVO tar=new DepMemberVO();
		tar.setFace(src.getFace());
		tar.setId(src.getId());
		tar.setMail(src.getMail());
		tar.setName(src.getName());
		return tar;
	}
	
	public static List<DepMemberVO> toVO(List<DepMemberDTO> srcs) {
		List<DepMemberVO> list = new ArrayList<>();
		for (DepMemberDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
