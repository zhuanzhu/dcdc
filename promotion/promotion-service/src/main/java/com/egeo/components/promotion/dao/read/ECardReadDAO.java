package com.egeo.components.promotion.dao.read;

import com.egeo.components.promotion.po.ECardPO;
import com.egeo.orm.BaseReadDAO;

import java.util.List;
import java.util.Map;

public interface ECardReadDAO extends BaseReadDAO<ECardPO>{

    /**
     * 根据keys查询卡表
      * @param keys
     * @return
     */
    public List<ECardPO> queryECardListByKey(Map<String, Object> keys);

}
	