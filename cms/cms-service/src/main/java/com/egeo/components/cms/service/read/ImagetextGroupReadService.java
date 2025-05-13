package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.ImagetextGroupDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ImagetextGroupReadService {

	public ImagetextGroupDTO findImagetextGroupById(ImagetextGroupDTO dto);

	public PageResult<ImagetextGroupDTO> findImagetextGroupOfPage(ImagetextGroupDTO dto,Pagination page);

	public List<ImagetextGroupDTO> findImagetextGroupAll(ImagetextGroupDTO dto);

	/**
	 * 根据实例id查询图文组件组
	 * @param instId
	 * @return
	 */
	public ImagetextGroupDTO queryImagetextGroupByInstId(Long instId, Integer groupType);
}
	