package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.CategoryAttNamePO;
import com.egeo.orm.BaseWriteDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryAttNameWriteDAO extends BaseWriteDAO<CategoryAttNamePO> {
    void addCategoryAttNameListWithTx(@Param("poList") List<CategoryAttNamePO> categoryAttNamePOS);
}
	