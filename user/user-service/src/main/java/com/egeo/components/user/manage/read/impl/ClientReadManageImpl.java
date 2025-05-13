package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 

import com.egeo.components.user.dao.read.ClientReadDAO;
import com.egeo.components.user.manage.read.ClientReadManage;
import com.egeo.components.user.po.ClientPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ClientReadManageImpl implements ClientReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ClientReadDAO clientReadDAO;
	
	public ClientPO findClientById(ClientPO po) {
		ClientPO clientpo = new ClientPO();
		clientpo.setId(po.getId());
		return clientReadDAO.findById(clientpo);
	}

	public PageResult<ClientPO> findClientOfPage(ClientPO po, Pagination page) {
		
		PageResult<ClientPO> pageResult = new PageResult<ClientPO>();
		List<ClientPO> list = null;

		int cnt = clientReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = clientReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ClientPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ClientPO> findClientAll(ClientPO po) {

		return clientReadDAO.findAll(po,null);
	}
	/**
	 * 根据所属平台id集合查询所属平台信息
	 * @param clientIds
	 * @return
	 */
	@Override
	public List<ClientPO> findClientByClientIds(List<Long> clientIds) {
		// TODO Auto-generated method stub
		return clientReadDAO.findClientByClientIds(clientIds);
	}
	/**
	 * 根据运营方Id集合查询运营方名称
	 * @param clientIdList
	 * @return
	 */
	@Override
	public List<String> clientNameByClientIds(List<Long> clientIdList) {
		// TODO Auto-generated method stub
		return clientReadDAO.clientNameByClientIds(clientIdList);
	}
	
}
	