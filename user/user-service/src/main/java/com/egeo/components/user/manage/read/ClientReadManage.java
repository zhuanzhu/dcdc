package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.ClientPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ClientReadManage {

	public ClientPO findClientById(ClientPO po);

	public PageResult<ClientPO> findClientOfPage(ClientPO po,Pagination page);

	public List<ClientPO> findClientAll(ClientPO po);
	/**
	 * 根据所属平台id集合查询所属平台信息
	 * @param clientIds
	 * @return
	 */
	public List<ClientPO> findClientByClientIds(List<Long> clientIds);
	/**
	 * 根据运营方Id集合查询运营方名称
	 * @param clientIdList
	 * @return
	 */
	public List<String> clientNameByClientIds(List<Long> clientIdList);
}
	