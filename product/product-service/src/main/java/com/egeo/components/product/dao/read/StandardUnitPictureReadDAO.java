package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.StandardUnitPicturePO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

public interface StandardUnitPictureReadDAO extends BaseReadDAO<StandardUnitPicturePO>{
    String findPictureUrlBySUId(@Param("id")Long id);
}
	