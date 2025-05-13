package com.egeo.components.product.dao.read;

import com.egeo.components.product.po.JdCategoryPO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JdCategoryReadDAO extends BaseReadDAO<JdCategoryPO>{
    List<Long> findJdCategoryIdByCatClass(@Param("catClass")int catClass);
}
	