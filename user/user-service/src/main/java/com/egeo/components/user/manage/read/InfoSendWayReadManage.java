package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.InfoSendWayPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InfoSendWayReadManage {

	public InfoSendWayPO findInfoSendWayById(InfoSendWayPO po);

	public PageResult<InfoSendWayPO> findInfoSendWayOfPage(InfoSendWayPO po,Pagination page);

	public List<InfoSendWayPO> findInfoSendWayAll(InfoSendWayPO po);
}
	