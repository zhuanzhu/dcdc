package com.egeo.components.product.business;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.egeo.components.product.vo.PictureVO;
import com.egeo.components.product.dto.PictureDTO;

public interface PictureManage {

    String uploadPicture(MultipartFile uploadFile);

    Long saveProduct(PictureVO pictureVO);

    PictureDTO findById(PictureVO pictureVO);

    List<PictureDTO> findAll(PictureVO pictureVO);

    String delete(PictureVO pictureVO);

    String deleteByIds(String ids);

    String deletePicture(String picture);
    /**
	 * 根据spuid查询su轮播图信息
	 * @param standardProductUnitId
	 * @param req
	 * @return
	 */
	List<String> findByStandardUnitId(Long standardUnitId);
	

}
	