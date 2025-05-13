package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.ProductPictureReadManage;
import com.egeo.components.product.dao.read.ProductPictureReadDAO;
import com.egeo.components.product.po.ProductPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ProductPictureReadManageImpl implements ProductPictureReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductPictureReadDAO productPictureReadDAO;
	
	public ProductPicturePO findProductPictureById(ProductPicturePO po) {
		ProductPicturePO productPicturepo = new ProductPicturePO();
		productPicturepo.setId(po.getId());
		return productPictureReadDAO.findById(productPicturepo);
	}

	public PageResult<ProductPicturePO> findProductPictureOfPage(ProductPicturePO po, Pagination page) {
		
		PageResult<ProductPicturePO> pageResult = new PageResult<ProductPicturePO>();
		List<ProductPicturePO> list = null;

		int cnt = productPictureReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = productPictureReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ProductPicturePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ProductPicturePO> findProductPictureAll(ProductPicturePO po) {

		return productPictureReadDAO.findAll(po,null);
	}
	
}
	