package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.ActivityDTO;
import com.egeo.components.promotion.po.ActivityPO;
import com.egeo.components.promotion.vo.ActivityVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:24
 */
public class ActivityConverter {

	
	public static ActivityDTO toDTO(ActivityVO src) {
		ActivityDTO tar = new ActivityDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setSortValue(src.getSortValue());
		tar.setStartTime(src.getStartTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ActivityVO toVO(ActivityDTO src) {
		ActivityVO tar = new ActivityVO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSortValue(src.getSortValue());
		tar.setStartTime(src.getStartTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}



	public static List<ActivityVO> toVO(List<ActivityDTO> srcs) {
		if (srcs == null)
			return null;
		List<ActivityVO> list = new ArrayList<ActivityVO>();
		for (ActivityDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ActivityDTO toDTO(ActivityPO src) {
		ActivityDTO tar = new ActivityDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSortValue(src.getSortValue());
		tar.setStartTime(src.getStartTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setTime(src.getTime());
		return tar;
	}

	public static ActivityPO toPO(ActivityDTO src) {
		ActivityPO tar = new ActivityPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSortValue(src.getSortValue());
		tar.setStartTime(src.getStartTime());
		tar.setFinishTime(src.getFinishTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setTime(src.getTime());
		return tar;
	}

	public static List<ActivityDTO> toDTO(List<ActivityPO> srcs) {
		if (srcs == null)
			return null;
		List<ActivityDTO> list = new ArrayList<ActivityDTO>();
		for (ActivityPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ActivityPO> toPO(List<ActivityDTO> srcs) {
		if (srcs == null)
			return null;
		List<ActivityPO> list = new ArrayList<ActivityPO>();
		for (ActivityDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	