package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.ClientPayTypeConfigPO;


public interface ClientPayTypeConfigWriteManage {

	Long insertClientPayTypeConfigWithTx(ClientPayTypeConfigPO po);

	int updateClientPayTypeConfigWithTx(ClientPayTypeConfigPO po);

	int deleteClientPayTypeConfigWithTx(ClientPayTypeConfigPO po);
}
	