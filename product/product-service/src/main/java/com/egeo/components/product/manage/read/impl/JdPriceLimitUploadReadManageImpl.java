package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.JdPriceLimitUploadReadManage;
import com.egeo.components.product.dao.read.JdPriceLimitUploadReadDAO;
import com.egeo.components.product.po.JdPriceLimitUploadPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class JdPriceLimitUploadReadManageImpl implements JdPriceLimitUploadReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdPriceLimitUploadReadDAO jdPriceLimitUploadReadDAO;
	
	public JdPriceLimitUploadPO findJdPriceLimitUploadById(JdPriceLimitUploadPO po) {
		JdPriceLimitUploadPO jdPriceLimitUploadpo = new JdPriceLimitUploadPO();
		jdPriceLimitUploadpo.setId(po.getId());
		return jdPriceLimitUploadReadDAO.findById(jdPriceLimitUploadpo);
	}

	public PageResult<JdPriceLimitUploadPO> findJdPriceLimitUploadOfPage(JdPriceLimitUploadPO po, Pagination page) {
		
		PageResult<JdPriceLimitUploadPO> pageResult = new PageResult<JdPriceLimitUploadPO>();
		List<JdPriceLimitUploadPO> list = null;

		int cnt = jdPriceLimitUploadReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = jdPriceLimitUploadReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<JdPriceLimitUploadPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<JdPriceLimitUploadPO> findJdPriceLimitUploadAll(JdPriceLimitUploadPO po) {

		return jdPriceLimitUploadReadDAO.findAll(po,null);
	}
	
}
	