package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.PicturePO;
import com.egeo.orm.BaseWriteDAO;

public interface PictureWriteDAO extends BaseWriteDAO<PicturePO> {

    int deleteByIds(@Param("ids")String ids);
    /**
     * 根据suid和图片id集合删除所有没有查询出来的su图片关系
     * @param merchantProductId
     * @param pictureIds
     * @return
     */
	int delByStandardUnitIdPictureId(@Param("merchantProductId")Long merchantProductId, @Param("ids")List<Long> pictureIds);

    void savePicture(@Param("poList")List<PicturePO> picturePOList);

    void updatePictureList(@Param("poList")List<PicturePO> picturePOList);
}
	