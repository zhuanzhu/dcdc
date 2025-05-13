package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.DownloadDTO;
import com.egeo.components.user.po.DownloadPO;
import com.egeo.components.user.vo.DownloadVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-05-15 15:14:50
 */
public class DownloadConverter {
	
	public static DownloadDTO toDTO(DownloadPO src) {
		if (src == null)
		return null;	
		DownloadDTO tar = new DownloadDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSession(src.getSession());
		tar.setUrl(src.getUrl());
		tar.setDailyDownloads(src.getDailyDownloads());
		tar.setDownloads(src.getDownloads());
		tar.setCreateTime(src.getCreateTime());
		tar.setUodateTime(src.getUodateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static DownloadPO toPO(DownloadDTO src) {
		if (src == null)
		return null;	
		DownloadPO tar = new DownloadPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSession(src.getSession());
		tar.setUrl(src.getUrl());
		tar.setDailyDownloads(src.getDailyDownloads());
		tar.setDownloads(src.getDownloads());
		tar.setCreateTime(src.getCreateTime());
		tar.setUodateTime(src.getUodateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<DownloadDTO> toDTO(List<DownloadPO> srcs) {
		if (srcs == null)
			return null;
		List<DownloadDTO> list = new ArrayList<DownloadDTO>();
		for (DownloadPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DownloadPO> toPO(List<DownloadDTO> srcs) {
		if (srcs == null)
			return null;
		List<DownloadPO> list = new ArrayList<DownloadPO>();
		for (DownloadDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	
	public static DownloadDTO toDTO(DownloadVO src) {
		if (src == null)
		return null;	
		DownloadDTO tar = new DownloadDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setSession(src.getSession());	
		tar.setUrl(src.getUrl());	
		tar.setDailyDownloads(src.getDailyDownloads());	
		tar.setDownloads(src.getDownloads());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUodateTime(src.getUodateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static DownloadVO toVO(DownloadDTO src) {
		if (src == null)
		return null;	
		DownloadVO tar = new DownloadVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setSession(src.getSession());	
		tar.setUrl(src.getUrl());	
		tar.setDailyDownloads(src.getDailyDownloads());	
		tar.setDownloads(src.getDownloads());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUodateTime(src.getUodateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}


	public static List<DownloadVO> toVO(List<DownloadDTO> srcs) {
		if (srcs == null)
			return null;
		List<DownloadVO> list = new ArrayList<DownloadVO>();
		for (DownloadDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	