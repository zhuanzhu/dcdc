package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.JdProductInnerIdPO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JdProductInnerIdWriteDAO extends BaseWriteDAO<JdProductInnerIdPO> {
    void saveJdProductInnerIdList(@Param("poList")List<JdProductInnerIdPO> jdProductInnerIdPOList);
}
	