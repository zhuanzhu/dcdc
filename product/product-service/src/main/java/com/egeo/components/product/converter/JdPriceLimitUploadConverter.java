package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.components.product.po.JdPriceLimitUploadPO;
import com.egeo.components.product.vo.JdPriceLimitUploadVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2019-04-23 17:39:28
 */
public class JdPriceLimitUploadConverter {

	public static JdPriceLimitUploadDTO toDTO(JdPriceLimitUploadVO src) {
		if (src == null)
		return null;	
		JdPriceLimitUploadDTO tar = new JdPriceLimitUploadDTO();
		tar.setId(src.getId());
		tar.setJdPriceLimitStart(src.getJdPriceLimitStart());	
		tar.setJdPriceLimitEnd(src.getJdPriceLimitEnd());	
		return tar;
	}

	public static JdPriceLimitUploadVO toVO(JdPriceLimitUploadDTO src) {
		if (src == null)
		return null;	
		JdPriceLimitUploadVO tar = new JdPriceLimitUploadVO();
		tar.setId(src.getId());
		tar.setJdPriceLimitStart(src.getJdPriceLimitStart());	
		tar.setJdPriceLimitEnd(src.getJdPriceLimitEnd());	
		return tar;
	}

	public static List<JdPriceLimitUploadDTO> toDTOs(List<JdPriceLimitUploadVO> srcs) {
		if (srcs == null)
			return null;
		List<JdPriceLimitUploadDTO> list = new ArrayList<JdPriceLimitUploadDTO>();
		for (JdPriceLimitUploadVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdPriceLimitUploadVO> toVO(List<JdPriceLimitUploadDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdPriceLimitUploadVO> list = new ArrayList<JdPriceLimitUploadVO>();
		for (JdPriceLimitUploadDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static JdPriceLimitUploadDTO toDTO(JdPriceLimitUploadPO src) {
		if (src == null)
		return null;	
		JdPriceLimitUploadDTO tar = new JdPriceLimitUploadDTO();
		tar.setId(src.getId());
		tar.setJdPriceLimitStart(src.getJdPriceLimitStart());
		tar.setJdPriceLimitEnd(src.getJdPriceLimitEnd());
		return tar;
	}

	public static JdPriceLimitUploadPO toPO(JdPriceLimitUploadDTO src) {
		if (src == null)
		return null;	
		JdPriceLimitUploadPO tar = new JdPriceLimitUploadPO();
		tar.setId(src.getId());
		tar.setJdPriceLimitStart(src.getJdPriceLimitStart());
		tar.setJdPriceLimitEnd(src.getJdPriceLimitEnd());
		return tar;
	}

	public static List<JdPriceLimitUploadDTO> toDTO(List<JdPriceLimitUploadPO> srcs) {
		if (srcs == null)
			return null;
		List<JdPriceLimitUploadDTO> list = new ArrayList<JdPriceLimitUploadDTO>();
		for (JdPriceLimitUploadPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<JdPriceLimitUploadPO> toPO(List<JdPriceLimitUploadDTO> srcs) {
		if (srcs == null)
			return null;
		List<JdPriceLimitUploadPO> list = new ArrayList<JdPriceLimitUploadPO>();
		for (JdPriceLimitUploadDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	