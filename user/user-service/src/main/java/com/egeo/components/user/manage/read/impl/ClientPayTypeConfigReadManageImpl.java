package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.ClientPayTypeConfigReadDAO;
import com.egeo.components.user.manage.read.ClientPayTypeConfigReadManage;
import com.egeo.components.user.po.ClientPayTypeConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ClientPayTypeConfigReadManageImpl implements ClientPayTypeConfigReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ClientPayTypeConfigReadDAO clientPayTypeConfigReadDAO;
	
	public ClientPayTypeConfigPO findClientPayTypeConfigById(ClientPayTypeConfigPO po) {
		ClientPayTypeConfigPO clientPayTypeConfigpo = new ClientPayTypeConfigPO();
		clientPayTypeConfigpo.setId(po.getId());
		return clientPayTypeConfigReadDAO.findById(clientPayTypeConfigpo);
	}

	public PageResult<ClientPayTypeConfigPO> findClientPayTypeConfigOfPage(ClientPayTypeConfigPO po, Pagination page) {
		
		PageResult<ClientPayTypeConfigPO> pageResult = new PageResult<ClientPayTypeConfigPO>();
		List<ClientPayTypeConfigPO> list = null;

		int cnt = clientPayTypeConfigReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = clientPayTypeConfigReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ClientPayTypeConfigPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ClientPayTypeConfigPO> findClientPayTypeConfigAll(ClientPayTypeConfigPO po) {

		return clientPayTypeConfigReadDAO.findAll(po,null);
	}
	
}
	