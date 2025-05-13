package com.egeo.components.user.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.VersionsConverter;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.components.user.manage.read.VersionsReadManage;
import com.egeo.components.user.po.VersionsPO;
import com.egeo.components.user.service.read.VersionsReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("versionsReadService")
public class VersionsReadServiceImpl implements VersionsReadService {
	@Autowired
	private VersionsReadManage versionsReadManage;

	@Override
	public VersionsDTO findVersionsById(VersionsDTO dto) {
		VersionsPO po = VersionsConverter.toPO(dto);
		VersionsPO list = versionsReadManage.findVersionsById(po);		
		return VersionsConverter.toDTO(list);
	}

	@Override
	public PageResult<VersionsDTO> findVersionsOfPage(VersionsDTO dto, Pagination page) {
		VersionsPO po = VersionsConverter.toPO(dto);
        PageResult<VersionsPO> pageResult = versionsReadManage.findVersionsOfPage(po, page);
        
        List<VersionsDTO> list = VersionsConverter.toDTO(pageResult.getList());
        PageResult<VersionsDTO> result = new PageResult<VersionsDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<VersionsDTO> findVersionsAll(VersionsDTO dto) {
		VersionsPO po = VersionsConverter.toPO(dto);
		List<VersionsPO> list = versionsReadManage.findVersionsAll(po);		
		return VersionsConverter.toDTO(list);
	}

	@Override
	public VersionsDTO queryVerisonByVersionCode(Integer vCode, Integer type, Integer user, Long platformId) {
		return VersionsConverter.toDTO(versionsReadManage.queryVerisonByVersionCode(vCode,type,user,platformId));
	}

	@Override
	public VersionsDTO queryLatestVersion(Integer type,Integer user) {
		return VersionsConverter.toDTO(versionsReadManage.queryLatestVersion(type,user));
	}

	@Override
	public List<VersionsDTO> queryLaterVersionsByVersionCode(Integer vCode, Integer type) {
		
		return VersionsConverter.toDTO(versionsReadManage.queryLaterVersionsByVersionCode(vCode,type));
	}

	@Override
	public Integer queryMaxVersionCode(Integer type,Integer user,Long platformId) {
		return versionsReadManage.queryMaxVersionCode(type,user,platformId);
	}

	@Override
	public VersionsDTO queryLatestOfficialVersion(VersionsDTO dto) {
		return VersionsConverter.toDTO(versionsReadManage.queryLatestOfficialVersion(VersionsConverter.toPO(dto)));
	}

	@Override
	public PageResult<VersionsDTO> findVersionsOfPageByBlurry(VersionsDTO dto, Pagination page) {
		VersionsPO po = VersionsConverter.toPO(dto);
        PageResult<VersionsPO> pageResult = versionsReadManage.findVersionsOfPageByBlurry(po, page);
        
        List<VersionsDTO> list = VersionsConverter.toDTO(pageResult.getList());
        PageResult<VersionsDTO> result = new PageResult<VersionsDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public PageResult<VersionsDTO> getVersionsOfPage(VersionsDTO dto, Pagination page) {
		VersionsPO po = VersionsConverter.toPO(dto);
		PageResult<VersionsPO> pageResult =  versionsReadManage.getVersionsOfPage(po, page);
		List<VersionsDTO> list = VersionsConverter.toDTO(pageResult.getList());
		PageResult<VersionsDTO> result = new PageResult<>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

}
	