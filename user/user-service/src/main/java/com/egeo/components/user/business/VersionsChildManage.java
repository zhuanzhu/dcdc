package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.VersionsChildDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface VersionsChildManage {

	public VersionsChildDTO findVersionsChildById(VersionsChildDTO dto);	

	public PageResult<VersionsChildDTO> findVersionsChildOfPage(VersionsChildDTO dto,Pagination page);

	public List<VersionsChildDTO> findVersionsChildAll(VersionsChildDTO dto);

	Long insertVersionsChildWithTx(VersionsChildDTO dto);

	int updateVersionsChildWithTx(VersionsChildDTO dto);

	int deleteVersionsChildWithTx(VersionsChildDTO dto);
	/**
	 * 根据条件分页查询子版本和及其渠道信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> versionsChildAndChannelOfPage(VersionsChildDTO dto, Pagination page);
}
	