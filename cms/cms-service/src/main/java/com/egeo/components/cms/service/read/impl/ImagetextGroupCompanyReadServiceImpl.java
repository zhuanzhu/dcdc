package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ImagetextGroupCompanyReadService;
import com.egeo.components.cms.manage.read.ImagetextGroupCompanyReadManage;
import com.egeo.components.cms.converter.ImagetextGroupCompanyConverter;
import com.egeo.components.cms.dto.ImagetextGroupCompanyDTO;
import com.egeo.components.cms.po.ImagetextGroupCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("imagetextGroupCompanyReadService")
public class ImagetextGroupCompanyReadServiceImpl  implements ImagetextGroupCompanyReadService {
	@Autowired
	private ImagetextGroupCompanyReadManage imagetextGroupCompanyReadManage;

	@Override
	public ImagetextGroupCompanyDTO findImagetextGroupCompanyById(ImagetextGroupCompanyDTO dto) {
		ImagetextGroupCompanyPO po = ImagetextGroupCompanyConverter.toPO(dto);
		ImagetextGroupCompanyPO list = imagetextGroupCompanyReadManage.findImagetextGroupCompanyById(po);		
		return ImagetextGroupCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<ImagetextGroupCompanyDTO> findImagetextGroupCompanyOfPage(ImagetextGroupCompanyDTO dto, Pagination page) {
		ImagetextGroupCompanyPO po = ImagetextGroupCompanyConverter.toPO(dto);
        PageResult<ImagetextGroupCompanyPO> pageResult = imagetextGroupCompanyReadManage.findImagetextGroupCompanyOfPage(po, page);
        
        List<ImagetextGroupCompanyDTO> list = ImagetextGroupCompanyConverter.toDTO(pageResult.getList());
        PageResult<ImagetextGroupCompanyDTO> result = new PageResult<ImagetextGroupCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ImagetextGroupCompanyDTO> findImagetextGroupCompanyAll(ImagetextGroupCompanyDTO dto) {
		ImagetextGroupCompanyPO po = ImagetextGroupCompanyConverter.toPO(dto);
		List<ImagetextGroupCompanyPO> list = imagetextGroupCompanyReadManage.findImagetextGroupCompanyAll(po);		
		return ImagetextGroupCompanyConverter.toDTO(list);
	}
}
	