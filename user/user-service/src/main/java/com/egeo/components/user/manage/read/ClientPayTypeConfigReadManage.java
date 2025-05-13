package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.ClientPayTypeConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ClientPayTypeConfigReadManage {

	public ClientPayTypeConfigPO findClientPayTypeConfigById(ClientPayTypeConfigPO po);

	public PageResult<ClientPayTypeConfigPO> findClientPayTypeConfigOfPage(ClientPayTypeConfigPO po,Pagination page);

	public List<ClientPayTypeConfigPO> findClientPayTypeConfigAll(ClientPayTypeConfigPO po);
}
	