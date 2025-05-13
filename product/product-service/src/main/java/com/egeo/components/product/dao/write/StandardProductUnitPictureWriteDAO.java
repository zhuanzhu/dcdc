package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.StandardProductUnitPicturePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardProductUnitPictureWriteDAO extends BaseWriteDAO<StandardProductUnitPicturePO> {
    void saveStandardProductUnitPicture(@Param("poList")List<StandardProductUnitPicturePO> standardProductUnitPicturePOList);
}
	