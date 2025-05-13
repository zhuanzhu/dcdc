package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface VersionsManage {

	public VersionsDTO findVersionsById(VersionsDTO dto);	

	public PageResult<VersionsDTO> findVersionsOfPage(VersionsDTO dto,Pagination page);

	public List<VersionsDTO> findVersionsAll(VersionsDTO dto);

	JsonResult<Map<String, Object>> insertVersionsWithTx(VersionsDTO dto);

	JsonResult<Map<String, Object>> updateVersionsWithTx(VersionsDTO dto);

	int deleteVersionsWithTx(VersionsDTO dto);

	/**
	 * 客户端版本校验
	 * @param versionLevel
	 * @param versionCode 版本号
	 * @param type 来源 0:安卓 1:ios;
	 * @param platformId
     * @return
	 */
	public JsonResult<Map<String, Object>> validate(Integer vCode, Integer type, Integer user, Long platformId);

	public JsonResult<Map<String, Object>> updatePartialVersionsWithTx(VersionsDTO dto, Integer updateTarget);

	/**
	 * 模糊搜索版本列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<VersionsDTO> findVersionsOfPageByBlurry(VersionsDTO dto, Pagination page);

    public PageResult<VersionsDTO> getVersionsOfPage(VersionsDTO dto, Pagination page);
}
	