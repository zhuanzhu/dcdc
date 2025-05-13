package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.components.user.service.read.VersionsReadService;
import com.egeo.components.user.service.write.VersionsWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class VersionsFacade {
	
	@Resource
	private VersionsReadService versionsReadService;
	
	@Resource
	private VersionsWriteService versionsWriteService;
	
	
	public VersionsDTO findVersionsById(VersionsDTO dto){
		
		return versionsReadService.findVersionsById(dto);
	}

	public PageResult<VersionsDTO> findVersionsOfPage(VersionsDTO dto,Pagination page){
		
		return versionsReadService.findVersionsOfPage(dto, page);
		
	}

	public List<VersionsDTO> findVersionsAll(VersionsDTO dto){
		
		return versionsReadService.findVersionsAll(dto);
		
	}

	public Long insertVersionsWithTx(VersionsDTO dto){
		
		return versionsWriteService.insertVersionsWithTx(dto);
	}

	public int updateVersionsWithTx(VersionsDTO dto){
		
		return versionsWriteService.updateVersionsWithTx(dto);
	}

	public int deleteVersionsWithTx(VersionsDTO dto){
		
		return versionsWriteService.deleteVersionsWithTx(dto);
		
	}

	/**
	 * 根据版本号查询对应渠道的版本信息
	 * @param versionCode
	 * @param type
	 * @param platformId
	 * @return
	 */
	public VersionsDTO queryVerisonByVersionCode(Integer vCode, Integer type, Integer user, Long platformId) {
		
		return versionsReadService.queryVerisonByVersionCode(vCode,type,user,platformId);
	}

	/**
	 * 查询平台最新版本
	 * @param type
	 * @return
	 */
	public VersionsDTO queryLatestVersion(Integer type,Integer user) {
		return versionsReadService.queryLatestVersion(type,user);
	}

	/**
	 * 查询对应平台版本水平高于给出的版本水平的所有版本
	 * @param releaseDate
	 * @param type
	 * @return
	 */
	public List<VersionsDTO> queryLaterVersionsByVersionCode(Integer vCode, Integer type) {
		
		return versionsReadService.queryLaterVersionsByVersionCode(vCode,type);
	}

	/**
	 * 查询最大的版本水平
	 * @return
	 */
	public Integer queryMaxVersionCode(Integer type,Integer user,Long platformId) {
		return versionsReadService.queryMaxVersionCode(type,user,platformId);
	}

	public int updateVersionsOfficialByTypeWithTx(Integer user) {
		return versionsWriteService.updateVersionsOfficialByTypeWithTx(user);
	}

	/**
	 * 查询最新官方版本的版本信息
	 * @param versionsDto
	 * @return
	 */
	public VersionsDTO queryLatestOfficialVersion(VersionsDTO dto) {
		return versionsReadService.queryLatestOfficialVersion(dto);
	}

	/**
	 * 模糊搜索版本列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<VersionsDTO> findVersionsOfPageByBlurry(VersionsDTO dto, Pagination page) {
		return versionsReadService.findVersionsOfPageByBlurry(dto, page);
	}

	public PageResult<VersionsDTO> getVersionsOfPage(VersionsDTO dto, Pagination page) {
		return  versionsReadService.getVersionsOfPage(dto,page);
	}
}
	