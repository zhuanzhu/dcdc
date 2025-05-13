package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.egeo.components.product.business.PictureManage;
import com.egeo.components.product.converter.PictureConverter;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.facade.PictureFacade;
import com.egeo.components.product.vo.PictureVO;

@Service("picture")
public class PictureManageImpl implements PictureManage{

	
	@Resource(name="pictureFacade")
	private PictureFacade pictureFacade;

    @Override
    public String uploadPicture(MultipartFile uploadFile) {
        return null;
    }

    @Override
    public Long saveProduct(PictureVO pictureVO) {
        
        return pictureFacade.savePicturect(PictureConverter.toDTO(pictureVO));
    }

    @Override
    public PictureDTO findById(PictureVO pictureVO) {
        
        return pictureFacade.findById(PictureConverter.toDTO(pictureVO));
    }

    @Override
    public List<PictureDTO> findAll(PictureVO pictureVO) {
        
        return pictureFacade.findAll(PictureConverter.toDTO(pictureVO));
    }

    @Override
    public String delete(PictureVO pictureVO) {
        
        return pictureFacade.delete(PictureConverter.toDTO(pictureVO));
    }

    @Override
    public String deleteByIds(String ids) {
        
        return pictureFacade.deleteByIds(ids);
    }

    @Override
    public String deletePicture(String picture) {
        
        return pictureFacade.deletePicture(picture);
    }
    /**
	 * 根据spuid查询su轮播图信息
	 * @param standardProductUnitId
	 * @param req
	 * @return
	 */
	@Override
	public List<String> findByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return pictureFacade.findByStandardUnitId(standardUnitId);
	}
	


}
	