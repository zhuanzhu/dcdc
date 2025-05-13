package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductUnitReadManage;
import com.egeo.components.product.dao.read.ProductUnitReadDAO;
import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ProductUnitReadManageImpl implements ProductUnitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductUnitReadDAO productUnitReadDAO;
	
	public ProductUnitPO findProductUnitById(ProductUnitPO po) {
		ProductUnitPO productUnitpo = new ProductUnitPO();
		productUnitpo.setId(po.getId());
		return productUnitReadDAO.findById(productUnitpo);
	}

	public PageResult<ProductUnitPO> findProductUnitOfPage(ProductUnitPO po, Pagination page) {
		
		PageResult<ProductUnitPO> pageResult = new PageResult<ProductUnitPO>();
		List<ProductUnitPO> list = null;

		int cnt = productUnitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = productUnitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ProductUnitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ProductUnitPO> findProductUnitAll(ProductUnitPO po) {

		return productUnitReadDAO.findAll(po,null);
	}
	/**
	 * 根据puid查询pu属性值id集合
	 * @param id
	 * @return
	 */
	@Override
	public List<Long> attValueByProductUnitId(Long productUnitId) {
		// TODO Auto-generated method stub
		return productUnitReadDAO.attValueByProductUnitId(productUnitId);
	}

	@Override
	public Long findLastId() {
		return productUnitReadDAO.findLastId();
	}

}
	