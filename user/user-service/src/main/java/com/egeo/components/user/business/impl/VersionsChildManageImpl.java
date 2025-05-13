package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.VersionsChildManage;
import com.egeo.components.user.facade.VersionsChildFacade;
import com.egeo.components.user.dto.VersionsChildDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("versionsChild")
public class VersionsChildManageImpl implements VersionsChildManage{

	
	@Resource(name="versionsChildFacade")
	private VersionsChildFacade versionsChildFacade;

	@Override
	public VersionsChildDTO findVersionsChildById(VersionsChildDTO dto) {
		return versionsChildFacade.findVersionsChildById(dto);
	}

	@Override
	public PageResult<VersionsChildDTO> findVersionsChildOfPage(VersionsChildDTO dto, Pagination page) {
		return versionsChildFacade.findVersionsChildOfPage(dto, page);
	}

	@Override
	public List<VersionsChildDTO> findVersionsChildAll(VersionsChildDTO dto) {
		return versionsChildFacade.findVersionsChildAll(dto);
	}

	@Override
	public Long insertVersionsChildWithTx(VersionsChildDTO dto) {
		return versionsChildFacade.insertVersionsChildWithTx(dto);
	}

	@Override
	public int updateVersionsChildWithTx(VersionsChildDTO dto) {
		return versionsChildFacade.updateVersionsChildWithTx(dto);
	}

	@Override
	public int deleteVersionsChildWithTx(VersionsChildDTO dto) {
		return versionsChildFacade.deleteVersionsChildWithTx(dto);
	}
	/**
	 * 根据条件分页查询子版本和及其渠道信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<Map<String, Object>> versionsChildAndChannelOfPage(VersionsChildDTO dto, Pagination page) {
		List<Map<String, Object>> list = new ArrayList<>();
		PageResult<VersionsChildDTO> pageResult = versionsChildFacade.versionsChildAndChannelOfPage(dto, page);
		List<VersionsChildDTO> versionsChildList = pageResult.getList();
		for (VersionsChildDTO versionsChildDTO : versionsChildList) {
			Map<String, Object> map = new HashMap<>();
			map.put("versionsChildId", versionsChildDTO.getId());
			map.put("channelName", versionsChildDTO.getChannelName());
			map.put("path", versionsChildDTO.getPath());
			map.put("updateTime", versionsChildDTO.getUpdateTime());
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}


}
	