package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ImagetextReadService;
import com.egeo.components.cms.manage.read.ImagetextReadManage;
import com.egeo.components.cms.converter.ImagetextConverter;
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.components.cms.po.ImagetextPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("imagetextReadService")
public class ImagetextReadServiceImpl  implements ImagetextReadService {
	@Autowired
	private ImagetextReadManage imagetextReadManage;

	@Override
	public ImagetextDTO findImagetextById(ImagetextDTO dto) {
		ImagetextPO po = ImagetextConverter.toPO(dto);
		ImagetextPO list = imagetextReadManage.findImagetextById(po);		
		return ImagetextConverter.toDTO(list);
	}

	@Override
	public PageResult<ImagetextDTO> findImagetextOfPage(ImagetextDTO dto, Pagination page) {
		ImagetextPO po = ImagetextConverter.toPO(dto);
        PageResult<ImagetextPO> pageResult = imagetextReadManage.findImagetextOfPage(po, page);
        
        List<ImagetextDTO> list = ImagetextConverter.toDTO(pageResult.getList());
        PageResult<ImagetextDTO> result = new PageResult<ImagetextDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ImagetextDTO> findImagetextAll(ImagetextDTO dto) {
		ImagetextPO po = ImagetextConverter.toPO(dto);
		List<ImagetextPO> list = imagetextReadManage.findImagetextAll(po);		
		return ImagetextConverter.toDTO(list);
	}

	@Override
	public List<ImagetextDTO> queryImagetextByGroupId(Long groupId) {
		
		return ImagetextConverter.toDTO(imagetextReadManage.queryImagetextByGroupId(groupId));
	}

}
	