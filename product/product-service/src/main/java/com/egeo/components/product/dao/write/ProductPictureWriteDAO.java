package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.ProductPicturePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductPictureWriteDAO extends BaseWriteDAO<ProductPicturePO> {
    void saveProductPicture(@Param("poList")List<ProductPicturePO> productPicturePOList);
}
	