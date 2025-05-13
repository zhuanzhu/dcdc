package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.ImagetextManage;
import com.egeo.components.cms.facade.ImagetextFacade;
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("imagetext")
public class ImagetextManageImpl implements ImagetextManage{

	
	@Resource(name="imagetextFacade")
	private ImagetextFacade imagetextFacade;

	@Override
	public ImagetextDTO findImagetextById(ImagetextDTO dto) {
		return imagetextFacade.findImagetextById(dto);
	}

	@Override
	public PageResult<ImagetextDTO> findImagetextOfPage(ImagetextDTO dto, Pagination page) {
		return imagetextFacade.findImagetextOfPage(dto, page);
	}

	@Override
	public List<ImagetextDTO> findImagetextAll(ImagetextDTO dto) {
		return imagetextFacade.findImagetextAll(dto);
	}

	@Override
	public Long insertImagetextWithTx(ImagetextDTO dto) {
		return imagetextFacade.insertImagetextWithTx(dto);
	}

	@Override
	public int updateImagetextWithTx(ImagetextDTO dto) {
		return imagetextFacade.updateImagetextWithTx(dto);
	}

	@Override
	public int deleteImagetextWithTx(ImagetextDTO dto) {
		return imagetextFacade.deleteImagetextWithTx(dto);
	}


}
	