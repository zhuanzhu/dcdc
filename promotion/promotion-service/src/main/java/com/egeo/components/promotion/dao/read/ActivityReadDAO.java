package com.egeo.components.promotion.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.promotion.po.ActivityPO;
import com.egeo.orm.BaseReadDAO;

public interface ActivityReadDAO extends BaseReadDAO<ActivityPO>{

	List<ActivityPO> activityByMerchantProdIdAndDate(@Param("date")Date date, @Param("merchantProdId")Long merchantProdId);
}
	