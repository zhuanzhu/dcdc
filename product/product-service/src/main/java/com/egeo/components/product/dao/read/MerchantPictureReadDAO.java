package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.MerchantPicturePO;
import com.egeo.orm.BaseReadDAO;

public interface MerchantPictureReadDAO extends BaseReadDAO<MerchantPicturePO>{
    Long findLastId();
}
	