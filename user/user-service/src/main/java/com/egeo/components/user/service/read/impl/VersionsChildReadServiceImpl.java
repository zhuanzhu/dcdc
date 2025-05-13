package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.VersionsChildCondition;
import com.egeo.components.user.converter.VersionsChildConverter;
import com.egeo.components.user.dto.VersionsChildDTO;
import com.egeo.components.user.manage.read.VersionsChildReadManage;
import com.egeo.components.user.po.VersionsChildPO;
import com.egeo.components.user.service.read.VersionsChildReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("versionsChildReadService")
public class VersionsChildReadServiceImpl implements VersionsChildReadService {
	@Autowired
	private VersionsChildReadManage versionsChildReadManage;

	@Override
	public VersionsChildDTO findVersionsChildById(VersionsChildDTO dto) {
		VersionsChildPO po = VersionsChildConverter.toPO(dto);
		VersionsChildPO list = versionsChildReadManage.findVersionsChildById(po);		
		return VersionsChildConverter.toDTO(list);
	}

	@Override
	public PageResult<VersionsChildDTO> findVersionsChildOfPage(VersionsChildDTO dto, Pagination page) {
		VersionsChildPO po = VersionsChildConverter.toPO(dto);
        PageResult<VersionsChildPO> pageResult = versionsChildReadManage.findVersionsChildOfPage(po, page);
        
        List<VersionsChildDTO> list = VersionsChildConverter.toDTO(pageResult.getList());
        PageResult<VersionsChildDTO> result = new PageResult<VersionsChildDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<VersionsChildDTO> findVersionsChildAll(VersionsChildDTO dto) {
		VersionsChildPO po = VersionsChildConverter.toPO(dto);
		List<VersionsChildPO> list = versionsChildReadManage.findVersionsChildAll(po);		
		return VersionsChildConverter.toDTO(list);
	}
	/**
	 * 根据条件分页查询子版本和及其渠道信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<VersionsChildDTO> versionsChildAndChannelOfPage(VersionsChildDTO dto, Pagination page) {
		VersionsChildPO po = VersionsChildConverter.toPO(dto);
		List<VersionsChildDTO> list = new ArrayList<>();
		PageResult<VersionsChildCondition> pageResult = versionsChildReadManage.versionsChildAndChannelOfPage(po, page);
		List<VersionsChildCondition> versionsChildConditionList = pageResult.getList();
		for (VersionsChildCondition versionsChildCondition : versionsChildConditionList) {
			VersionsChildDTO versionsChildDTO = VersionsChildConverter.toDTO(versionsChildCondition);
			versionsChildDTO.setChannelName(versionsChildCondition.getChannelName());
			versionsChildDTO.setPath(versionsChildCondition.getPath());
			versionsChildDTO.setUpdateTime(versionsChildCondition.getUpdateTime());
			list.add(versionsChildDTO);
		}
		
        PageResult<VersionsChildDTO> result = new PageResult<VersionsChildDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
}
	