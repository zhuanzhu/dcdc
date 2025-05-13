package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.InfoSendWayReadManage;
import com.egeo.components.user.dao.read.InfoSendWayReadDAO;
import com.egeo.components.user.po.InfoSendWayPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InfoSendWayReadManageImpl implements InfoSendWayReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoSendWayReadDAO infoSendWayReadDAO;
	
	public InfoSendWayPO findInfoSendWayById(InfoSendWayPO po) {
		InfoSendWayPO infoSendWaypo = new InfoSendWayPO();
		infoSendWaypo.setId(po.getId());
		return infoSendWayReadDAO.findById(infoSendWaypo);
	}

	public PageResult<InfoSendWayPO> findInfoSendWayOfPage(InfoSendWayPO po, Pagination page) {
		
		PageResult<InfoSendWayPO> pageResult = new PageResult<InfoSendWayPO>();
		List<InfoSendWayPO> list = null;

		int cnt = infoSendWayReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = infoSendWayReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InfoSendWayPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InfoSendWayPO> findInfoSendWayAll(InfoSendWayPO po) {

		return infoSendWayReadDAO.findAll(po,null);
	}
	
}
	