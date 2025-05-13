package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.VersionsPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface VersionsReadManage {

	public VersionsPO findVersionsById(VersionsPO po);

	public PageResult<VersionsPO> findVersionsOfPage(VersionsPO po,Pagination page);

	public List<VersionsPO> findVersionsAll(VersionsPO po);

	/**
	 * 根据版本号查询对应渠道的版本信息
	 * @param versionCode
	 * @param type
	 * @param platformId
     * @return
	 */
	public VersionsPO queryVerisonByVersionCode(Integer vCode, Integer type, Integer user, Long platformId);

	/**
	 * 查询平台最新版本
	 * @param type
	 * @return
	 */
	public VersionsPO queryLatestVersion(Integer type,Integer user);

	/**
	 * 查询对应平台晚于制定发布日期的所有版本
	 * @param releaseDate
	 * @param type
	 * @return
	 */
	public List<VersionsPO> queryLaterVersionsByVersionCode(Integer vCode, Integer type);

	/**
	 * 查询最大的版本水平
	 * @return
	 */
	public Integer queryMaxVersionCode(Integer type,Integer user,Long platformId);

	/**
	 * 查询最大的官方版本的版本水平
	 * @return
	 */
	public VersionsPO queryLatestOfficialVersion(VersionsPO po);

	/**
	 * 模糊查询版本列表
	 * @param po
	 * @param page
	 * @return
	 */
	public PageResult<VersionsPO> findVersionsOfPageByBlurry(VersionsPO po, Pagination page);

    public PageResult<VersionsPO> getVersionsOfPage(VersionsPO po, Pagination page);
}
	