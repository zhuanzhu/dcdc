package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.read.SaltReadDAO;
import com.egeo.components.config.manage.read.SaltReadManage;
import com.egeo.components.config.po.SaltPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SaltReadManageImpl implements SaltReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SaltReadDAO saltReadDAO;
	
	public SaltPO findSaltById(SaltPO po) {
		SaltPO saltpo = new SaltPO();
		saltpo.setId(po.getId());
		return saltReadDAO.findById(saltpo);
	}

	public PageResult<SaltPO> findSaltOfPage(SaltPO po, Pagination page) {
		
		PageResult<SaltPO> pageResult = new PageResult<SaltPO>();
		List<SaltPO> list = null;

		int cnt = saltReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = saltReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SaltPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SaltPO> findSaltAll(SaltPO po) {
		return saltReadDAO.findAll(po,null);
	}

	@Override
	public SaltPO querySaltByUUID(String uuid) {
		return saltReadDAO.querySaltByUUID(uuid);
	}
	
}
	