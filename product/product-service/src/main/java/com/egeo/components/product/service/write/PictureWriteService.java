package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.PictureDTO;

public interface PictureWriteService {

    Long savePictureWithTx(PictureDTO dto);

    String deleteWithTx(PictureDTO dto);

    String deleteByIdsWithTx(String ids);

    String deletePictureWithTx(String picture);
    /**
     * 根据suid和图片id集合删除所有没有查询出来的su图片关系
     * @param merchantProductId
     * @param pictureIds
     * @return
     */
	int delByStandardUnitIdPictureIdWithTx(Long merchantProductId, List<Long> pictureIds);

    void savePicture(List<Long> pictureIdList, List<String> pictureUrlList);

    void updatePictureList(List<Long> pictureIdList, List<String> pictureUrlList);
}
	