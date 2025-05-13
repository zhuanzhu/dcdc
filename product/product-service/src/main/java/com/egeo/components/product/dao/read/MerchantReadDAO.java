package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.MerchantPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantReadDAO extends BaseReadDAO<MerchantPO>{
    List<MerchantPO> findMerchantList();

    List<MerchantPO> findMerchantListByType(@Param("type")Integer type);
}
	