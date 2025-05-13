package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.UrlWriteManage;
import com.egeo.components.user.dao.write.UrlWriteDAO;
import com.egeo.components.user.po.UrlPO;

@Service
public class UrlWriteManageImpl implements UrlWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UrlWriteDAO urlWriteDAO;
	
	@Override
	public int saveWithTx(UrlPO po) {
		int i = urlWriteDAO.insert(po);
		return i;
	}
	public int UpdateWithTx(UrlPO po) {
		return urlWriteDAO.update(po);
	}
	@Override
	public void delete(UrlPO po) {
		urlWriteDAO.delete(po);
	}
}
	