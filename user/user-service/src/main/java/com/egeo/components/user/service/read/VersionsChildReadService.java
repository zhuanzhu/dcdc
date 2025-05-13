package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.VersionsChildDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface VersionsChildReadService {

	public VersionsChildDTO findVersionsChildById(VersionsChildDTO dto);

	public PageResult<VersionsChildDTO> findVersionsChildOfPage(VersionsChildDTO dto,Pagination page);

	public List<VersionsChildDTO> findVersionsChildAll(VersionsChildDTO dto);
	/**
	 * 根据条件分页查询子版本和及其渠道信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<VersionsChildDTO> versionsChildAndChannelOfPage(VersionsChildDTO dto, Pagination page);
}
	