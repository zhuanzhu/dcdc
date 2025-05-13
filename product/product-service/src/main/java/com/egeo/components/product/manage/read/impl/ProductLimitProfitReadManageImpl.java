package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductLimitProfitReadManage;
import com.egeo.components.product.dao.read.ProductLimitProfitReadDAO;
import com.egeo.components.product.po.ProductLimitProfitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ProductLimitProfitReadManageImpl implements ProductLimitProfitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductLimitProfitReadDAO productLimitProfitReadDAO;
	
	public ProductLimitProfitPO findProductLimitProfitById(ProductLimitProfitPO po) {
		ProductLimitProfitPO productLimitProfitpo = new ProductLimitProfitPO();
		productLimitProfitpo.setId(po.getId());
		return productLimitProfitReadDAO.findById(productLimitProfitpo);
	}

	public PageResult<ProductLimitProfitPO> findProductLimitProfitOfPage(ProductLimitProfitPO po, Pagination page) {
		
		PageResult<ProductLimitProfitPO> pageResult = new PageResult<ProductLimitProfitPO>();
		List<ProductLimitProfitPO> list = null;

		int cnt = productLimitProfitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = productLimitProfitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ProductLimitProfitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ProductLimitProfitPO> findProductLimitProfitAll(ProductLimitProfitPO po) {

		return productLimitProfitReadDAO.findAll(po,null);
	}
	
}
	