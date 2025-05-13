package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.VersionsPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface VersionsReadDAO extends BaseReadDAO<VersionsPO>{

	/**
	 * 根据版本号查询对应渠道的版本信息
	 * @param versionCode
	 * @param type
	 * @param platformId
     * @return
	 */
	VersionsPO queryVerisonByVersionCode(
            @Param("vCode") Integer vCode,
            @Param("type") Integer type,
            @Param("user") Integer user, @Param("platformId")Long platformId);

	/**
	 * 查询平台最新版本
	 * @param type
	 * @return
	 */
	VersionsPO queryLatestVersion(
			@Param("type")Integer type, 
			@Param("user")Integer user);

	/**
	 * 查询对应平台晚于制定发布日期的所有版本
	 * @param releaseDate
	 * @param type
	 * @return
	 */
	List<VersionsPO> queryLaterVersionsByVersionCode(
			@Param("vCode")Integer vCode, 
			@Param("type")Integer type);

	/**
	 * 查询最大的版本水平
	 * @return
	 */
	Integer queryMaxVersionCode(@Param("type")Integer type,@Param("user")Integer user,@Param("platformId")Long platformId);

	/**
	 * 查询官方版本的最大的版本水平
	 * @return
	 */
	VersionsPO queryLatestOfficialVersion(@Param("po")VersionsPO po);

	/**
	 * 模糊搜索版本列表
	 * @param po
	 * @return
	 */
	int countOfPageByBlurry(@Param("po")VersionsPO po);

	List<VersionsPO> findOfPageByBlurry(@Param("po")VersionsPO po, @Param("page")Pagination page);

	List<VersionsPO> getVersionsOfPage(@Param("po") VersionsPO po, @Param("page") Pagination page);
}
	