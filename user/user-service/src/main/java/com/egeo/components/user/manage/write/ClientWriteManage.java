package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.ClientPO;
import com.egeo.components.user.po.ClientPayTypeConfigPO;

import java.util.List;


public interface ClientWriteManage {

	Long insertClientWithTx(ClientPO po);

	int updateClientWithTx(ClientPO po);

	int deleteClientWithTx(ClientPO po);

    boolean updateClientPayTypeWithTx(ClientPO clientPO, List<ClientPayTypeConfigPO> clientPayTypeConfigPOList);
}
	