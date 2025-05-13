package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.po.BuyCardBasePO;
import com.egeo.orm.BaseReadDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BuyCardBaseReadDAO extends BaseReadDAO<BuyCardBasePO>{

    public int findMaxSortValue();
}
