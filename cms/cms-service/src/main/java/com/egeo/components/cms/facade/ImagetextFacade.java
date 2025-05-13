package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.ImagetextGroupReadService;
import com.egeo.components.cms.service.read.ImagetextReadService;
import com.egeo.components.cms.service.write.ImagetextGroupWriteService;
import com.egeo.components.cms.service.write.ImagetextWriteService;
import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.components.cms.dto.ImagetextGroupDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ImagetextFacade {
	
	@Resource
	private ImagetextReadService imagetextReadService;
	
	@Resource
	private ImagetextWriteService imagetextWriteService;
	
	@Resource
	private ImagetextGroupReadService imagetextGroupReadService;
	
	@Resource
	private ImagetextGroupWriteService imagetextGroupWriteService;
	
	
	public ImagetextDTO findImagetextById(ImagetextDTO dto){
		
		return imagetextReadService.findImagetextById(dto);
	}

	public PageResult<ImagetextDTO> findImagetextOfPage(ImagetextDTO dto,Pagination page){
		
		return imagetextReadService.findImagetextOfPage(dto, page);
		
	}

	public List<ImagetextDTO> findImagetextAll(ImagetextDTO dto){
		
		return imagetextReadService.findImagetextAll(dto);
		
	}

	public Long insertImagetextWithTx(ImagetextDTO dto){
		
		return imagetextWriteService.insertImagetextWithTx(dto);
	}

	public int updateImagetextWithTx(ImagetextDTO dto){
		
		return imagetextWriteService.updateImagetextWithTx(dto);
	}

	public int deleteImagetextWithTx(ImagetextDTO dto){
		
		return imagetextWriteService.deleteImagetextWithTx(dto);
		
	}

	/**
	 * 根据实例id查询图文组件组
	 * @param instId
	 * @return
	 */
	public ImagetextGroupDTO queryImagetextGroupByInstId(Long instId, Integer groupType) {
		return imagetextGroupReadService.queryImagetextGroupByInstId(instId, groupType);
	}

	/**
	 * 根据组id查询图文组件
	 * @param id
	 * @return
	 */
	public List<ImagetextDTO> queryImagetextByGroupId(Long groupId) {
		return imagetextReadService.queryImagetextByGroupId(groupId);
	}

}
	