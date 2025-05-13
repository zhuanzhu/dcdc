package com.egeo.components.config.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.dto.PictureDTO;
import com.egeo.components.config.service.read.PictureReadService;
import com.egeo.components.config.service.write.PictureWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class PictureFacade {
	
	@Autowired
	private PictureReadService pictureReadService;
	
	@Autowired
	private PictureWriteService pictureWriteService;
	
	
	public PictureDTO findPictureById(PictureDTO dto){
		
		return pictureReadService.findPictureById(dto);
	}

	public PageResult<PictureDTO> findPictureOfPage(PictureDTO dto,Pagination page){
		
		return pictureReadService.findPictureOfPage(dto, page);
		
	}

	public List<PictureDTO> findPictureAll(PictureDTO dto){
		
		return pictureReadService.findPictureAll(dto);
		
	}

	public Long insertPictureWithTx(PictureDTO dto){
		
		return pictureWriteService.insertPictureWithTx(dto);
	}

	public int updatePictureWithTx(PictureDTO dto){
		
		return pictureWriteService.updatePictureWithTx(dto);
	}

	public int deletePictureWithTx(PictureDTO dto){
		
		return pictureWriteService.deletePictureWithTx(dto);
		
	}

}
	