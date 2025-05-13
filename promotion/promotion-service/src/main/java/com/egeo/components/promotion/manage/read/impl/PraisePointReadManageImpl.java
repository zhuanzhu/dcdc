package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.PraisePointReadManage;
import com.egeo.components.promotion.dao.read.PraisePointReadDAO;
import com.egeo.components.promotion.po.PraisePointPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class PraisePointReadManageImpl implements PraisePointReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PraisePointReadDAO praisePointReadDAO;
	
	@Override
	public PraisePointPO queryPraisePointByUserId(Long userId) {
		return praisePointReadDAO.queryPraisePointByUserId(userId);
	}
	


	
}
	