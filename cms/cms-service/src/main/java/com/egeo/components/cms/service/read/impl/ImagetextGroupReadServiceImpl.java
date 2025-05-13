package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ImagetextGroupReadService;
import com.egeo.components.cms.manage.read.ImagetextGroupReadManage;
import com.egeo.components.cms.converter.ImagetextGroupConverter;
import com.egeo.components.cms.dto.ImagetextGroupDTO;
import com.egeo.components.cms.po.ImagetextGroupPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("imagetextGroupReadService")
public class ImagetextGroupReadServiceImpl  implements ImagetextGroupReadService {
	@Autowired
	private ImagetextGroupReadManage imagetextGroupReadManage;

	@Override
	public ImagetextGroupDTO findImagetextGroupById(ImagetextGroupDTO dto) {
		ImagetextGroupPO po = ImagetextGroupConverter.toPO(dto);
		ImagetextGroupPO list = imagetextGroupReadManage.findImagetextGroupById(po);		
		return ImagetextGroupConverter.toDTO(list);
	}

	@Override
	public PageResult<ImagetextGroupDTO> findImagetextGroupOfPage(ImagetextGroupDTO dto, Pagination page) {
		ImagetextGroupPO po = ImagetextGroupConverter.toPO(dto);
        PageResult<ImagetextGroupPO> pageResult = imagetextGroupReadManage.findImagetextGroupOfPage(po, page);
        
        List<ImagetextGroupDTO> list = ImagetextGroupConverter.toDTO(pageResult.getList());
        PageResult<ImagetextGroupDTO> result = new PageResult<ImagetextGroupDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ImagetextGroupDTO> findImagetextGroupAll(ImagetextGroupDTO dto) {
		ImagetextGroupPO po = ImagetextGroupConverter.toPO(dto);
		List<ImagetextGroupPO> list = imagetextGroupReadManage.findImagetextGroupAll(po);		
		return ImagetextGroupConverter.toDTO(list);
	}

	@Override
	public ImagetextGroupDTO queryImagetextGroupByInstId(Long instId, Integer groupType) {
		
		return ImagetextGroupConverter.toDTO(imagetextGroupReadManage.queryImagetextGroupByInstId(instId, groupType));
	}
}
	