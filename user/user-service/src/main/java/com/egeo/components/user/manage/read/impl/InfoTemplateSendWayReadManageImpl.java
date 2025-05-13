package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.InfoTemplateSendWayReadManage;
import com.egeo.components.user.dao.read.InfoTemplateSendWayReadDAO;
import com.egeo.components.user.po.InfoTemplateSendWayPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InfoTemplateSendWayReadManageImpl implements InfoTemplateSendWayReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoTemplateSendWayReadDAO infoTemplateSendWayReadDAO;
	
	public InfoTemplateSendWayPO findInfoTemplateSendWayById(InfoTemplateSendWayPO po) {
		InfoTemplateSendWayPO infoTemplateSendWaypo = new InfoTemplateSendWayPO();
		infoTemplateSendWaypo.setId(po.getId());
		return infoTemplateSendWayReadDAO.findById(infoTemplateSendWaypo);
	}

	public PageResult<InfoTemplateSendWayPO> findInfoTemplateSendWayOfPage(InfoTemplateSendWayPO po, Pagination page) {
		
		PageResult<InfoTemplateSendWayPO> pageResult = new PageResult<InfoTemplateSendWayPO>();
		List<InfoTemplateSendWayPO> list = null;

		int cnt = infoTemplateSendWayReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = infoTemplateSendWayReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InfoTemplateSendWayPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InfoTemplateSendWayPO> findInfoTemplateSendWayAll(InfoTemplateSendWayPO po) {

		return infoTemplateSendWayReadDAO.findAll(po,null);
	}
	/**
	 * 根据消息模版id查询发送方式
	 */
	@Override
	public List<String> sendWayNameByInfoTemplateId(Long infoTemplateId) {
		return infoTemplateSendWayReadDAO.sendWayNameByInfoTemplateId(infoTemplateId);
	}
	
}
	