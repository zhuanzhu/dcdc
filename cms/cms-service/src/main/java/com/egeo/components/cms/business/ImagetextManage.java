package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ImagetextManage {

	public ImagetextDTO findImagetextById(ImagetextDTO dto);	

	public PageResult<ImagetextDTO> findImagetextOfPage(ImagetextDTO dto,Pagination page);

	public List<ImagetextDTO> findImagetextAll(ImagetextDTO dto);

	Long insertImagetextWithTx(ImagetextDTO dto);

	int updateImagetextWithTx(ImagetextDTO dto);

	int deleteImagetextWithTx(ImagetextDTO dto);
}
	