package com.egeo.components.user.service.read;


import java.util.List;

import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface VersionsReadService {

	public VersionsDTO findVersionsById(VersionsDTO dto);

	public PageResult<VersionsDTO> findVersionsOfPage(VersionsDTO dto,Pagination page);

	public List<VersionsDTO> findVersionsAll(VersionsDTO dto);

	/**
	 * 根据版本号查询对应渠道的版本信息
	 * @param versionCode
	 * @param type
	 * @param platformId
	 * @return
	 */
	public VersionsDTO queryVerisonByVersionCode(Integer vCode, Integer type, Integer user, Long platformId);

	/**
	 * 查询平台最新版本
	 * @param type
	 * @return
	 */
	public VersionsDTO queryLatestVersion(Integer type,Integer user);

	/**
	 * 查询对应平台晚于制定发布日期的所有版本
	 * @param releaseDate
	 * @param type
	 * @return
	 */
	public List<VersionsDTO> queryLaterVersionsByVersionCode(Integer vCode, Integer type);

	/**
	 * 查询最大的版本水平
	 * @return
	 */
	public Integer queryMaxVersionCode(Integer type,Integer user,Long platformId);

	/**
	 * 查询最大的官方版本的版本水平
	 * @param dto
	 * @return
	 */
	public VersionsDTO queryLatestOfficialVersion(VersionsDTO dto);

	/**
	 * 模糊搜索版本列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<VersionsDTO> findVersionsOfPageByBlurry(VersionsDTO dto, Pagination page);

    public PageResult<VersionsDTO> getVersionsOfPage(VersionsDTO dto, Pagination page);
}
	