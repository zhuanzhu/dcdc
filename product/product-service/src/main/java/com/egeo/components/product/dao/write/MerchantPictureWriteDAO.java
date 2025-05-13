package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.MerchantPicturePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantPictureWriteDAO extends BaseWriteDAO<MerchantPicturePO> {
    void saveMerchantPicture(@Param("poList")List<MerchantPicturePO> merchantPicturePOList);
}
	