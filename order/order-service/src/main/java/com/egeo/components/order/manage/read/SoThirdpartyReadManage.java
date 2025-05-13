package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.SoThirdpartyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoThirdpartyReadManage {

	public SoThirdpartyPO findSoThirdpartyById(SoThirdpartyPO po);

	public PageResult<SoThirdpartyPO> findSoThirdpartyOfPage(SoThirdpartyPO po,Pagination page);

	public List<SoThirdpartyPO> findSoThirdpartyAll(SoThirdpartyPO po);

    List<Long> getThirdpartyIdListByStatus();

    Long findSoChildIdByThirdpartyId(Long jdOrderId);
}
	