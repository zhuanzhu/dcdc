package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitPicturePO;
import com.egeo.orm.BaseWriteDAO;

public interface StandardUnitPictureWriteDAO extends BaseWriteDAO<StandardUnitPicturePO> {
	/**
	 * 批量保存su商品图片信息
	 * @param standardUnitPictures
	 */
	void insertAll(@Param("poList")List<StandardUnitPicturePO> standardUnitPictures);

    void saveStandardUnitPicture(@Param("poList")List<StandardUnitPicturePO> standardUnitPicturePOList);
}
	