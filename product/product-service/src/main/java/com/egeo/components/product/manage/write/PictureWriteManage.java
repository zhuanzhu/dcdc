package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.PicturePO;

public interface PictureWriteManage {

    Long savePictureWithTx(PicturePO po);

    String deleteWithTx(PicturePO po);

    String deleteByIds(String ids);
    /**
     * 根据suid和图片id集合删除所有没有查询出来的su图片关系
     * @param merchantProductId
     * @param pictureIds
     * @return
     */
	int delByStandardUnitIdPictureId(Long merchantProductId, List<Long> pictureIds);

    void savePicture(List<PicturePO> picturePOList);

    void updatePictureList(List<PicturePO> picturePOList);
}
	