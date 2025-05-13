package com.egeo.components.product.converter;

import com.egeo.components.product.dto.ChannelPriceLimitUploadDTO;
import com.egeo.components.product.po.ChannelPriceLimitUploadPO;
import com.egeo.components.product.vo.ChannelPriceLimitUploadVO;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO和PO相互转换工具类
 *
 * @author xia
 * @date 2019-04-23 17:39:28
 */
public class ChannelPriceLimitUploadConverter {

	public static ChannelPriceLimitUploadDTO toDTO(ChannelPriceLimitUploadVO src) {
		if (src == null)
		return null;
		ChannelPriceLimitUploadDTO tar = new ChannelPriceLimitUploadDTO();
		tar.setId(src.getId());
		tar.setJdPriceLimitStart(src.getJdPriceLimitStart());
		tar.setJdPriceLimitEnd(src.getJdPriceLimitEnd());
		tar.setChannelCode(src.getChannelCode());
		return tar;
	}

	public static ChannelPriceLimitUploadVO toVO(ChannelPriceLimitUploadDTO src) {
		if (src == null)
		return null;
		ChannelPriceLimitUploadVO tar = new ChannelPriceLimitUploadVO();
		tar.setId(src.getId());
		tar.setJdPriceLimitStart(src.getJdPriceLimitStart());
		tar.setJdPriceLimitEnd(src.getJdPriceLimitEnd());
		tar.setChannelCode(src.getChannelCode());
		return tar;
	}

	public static List<ChannelPriceLimitUploadDTO> toDTOs(List<ChannelPriceLimitUploadVO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelPriceLimitUploadDTO> list = new ArrayList<>();
		for (ChannelPriceLimitUploadVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ChannelPriceLimitUploadVO> toVO(List<ChannelPriceLimitUploadDTO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelPriceLimitUploadVO> list = new ArrayList<>();
		for (ChannelPriceLimitUploadDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ChannelPriceLimitUploadDTO toDTO(ChannelPriceLimitUploadPO src) {
		if (src == null)
		return null;
		ChannelPriceLimitUploadDTO tar = new ChannelPriceLimitUploadDTO();
		tar.setId(src.getId());
		tar.setJdPriceLimitStart(src.getJdPriceLimitStart());
		tar.setJdPriceLimitEnd(src.getJdPriceLimitEnd());
		tar.setChannelCode(src.getChannelCode());
		return tar;
	}

	public static ChannelPriceLimitUploadPO toPO(ChannelPriceLimitUploadDTO src) {
		if (src == null)
		return null;
		ChannelPriceLimitUploadPO tar = new ChannelPriceLimitUploadPO();
		tar.setId(src.getId());
		tar.setJdPriceLimitStart(src.getJdPriceLimitStart());
		tar.setJdPriceLimitEnd(src.getJdPriceLimitEnd());
		tar.setChannelCode(src.getChannelCode());
		return tar;
	}

	public static List<ChannelPriceLimitUploadDTO> toDTO(List<ChannelPriceLimitUploadPO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelPriceLimitUploadDTO> list = new ArrayList<>();
		for (ChannelPriceLimitUploadPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ChannelPriceLimitUploadPO> toPO(List<ChannelPriceLimitUploadDTO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelPriceLimitUploadPO> list = new ArrayList<ChannelPriceLimitUploadPO>();
		for (ChannelPriceLimitUploadDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
