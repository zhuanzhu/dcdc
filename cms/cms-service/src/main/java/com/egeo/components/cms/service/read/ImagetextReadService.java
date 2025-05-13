package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ImagetextReadService {

	public ImagetextDTO findImagetextById(ImagetextDTO dto);

	public PageResult<ImagetextDTO> findImagetextOfPage(ImagetextDTO dto,Pagination page);

	public List<ImagetextDTO> findImagetextAll(ImagetextDTO dto);

	/**
	 * 根据组id查询图文组件
	 * @param groupId
	 * @return
	 */
	public List<ImagetextDTO> queryImagetextByGroupId(Long groupId);
}
	