package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.AttributeNameDecimalReadManage;
import com.egeo.components.product.dao.read.AttributeNameDecimalReadDAO;
import com.egeo.components.product.po.AttributeNameDecimalPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AttributeNameDecimalReadManageImpl implements AttributeNameDecimalReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AttributeNameDecimalReadDAO attributeNameDecimalReadDAO;
	
	public AttributeNameDecimalPO findAttributeNameDecimalById(AttributeNameDecimalPO po) {
		AttributeNameDecimalPO attributeNameDecimalpo = new AttributeNameDecimalPO();
		attributeNameDecimalpo.setId(po.getId());
		return attributeNameDecimalReadDAO.findById(attributeNameDecimalpo);
	}

	public PageResult<AttributeNameDecimalPO> findAttributeNameDecimalOfPage(AttributeNameDecimalPO po, Pagination page) {
		
		PageResult<AttributeNameDecimalPO> pageResult = new PageResult<AttributeNameDecimalPO>();
		List<AttributeNameDecimalPO> list = null;

		int cnt = attributeNameDecimalReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = attributeNameDecimalReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AttributeNameDecimalPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AttributeNameDecimalPO> findAttributeNameDecimalAll(AttributeNameDecimalPO po) {

		return attributeNameDecimalReadDAO.findAll(po,null);
	}
	
}
	