package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.ImagetextGroupCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ImagetextGroupCompanyReadService {

	public ImagetextGroupCompanyDTO findImagetextGroupCompanyById(ImagetextGroupCompanyDTO dto);

	public PageResult<ImagetextGroupCompanyDTO> findImagetextGroupCompanyOfPage(ImagetextGroupCompanyDTO dto,Pagination page);

	public List<ImagetextGroupCompanyDTO> findImagetextGroupCompanyAll(ImagetextGroupCompanyDTO dto);
}
	