package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitPictureReadManage;
import com.egeo.components.product.dao.read.StandardProductUnitPictureReadDAO;
import com.egeo.components.product.po.StandardProductUnitPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardProductUnitPictureReadManageImpl implements StandardProductUnitPictureReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitPictureReadDAO standardProductUnitPictureReadDAO;
	
	public StandardProductUnitPicturePO findStandardProductUnitPictureById(StandardProductUnitPicturePO po) {
		StandardProductUnitPicturePO standardProductUnitPicturepo = new StandardProductUnitPicturePO();
		standardProductUnitPicturepo.setId(po.getId());
		return standardProductUnitPictureReadDAO.findById(standardProductUnitPicturepo);
	}

	public PageResult<StandardProductUnitPicturePO> findStandardProductUnitPictureOfPage(StandardProductUnitPicturePO po, Pagination page) {
		
		PageResult<StandardProductUnitPicturePO> pageResult = new PageResult<StandardProductUnitPicturePO>();
		List<StandardProductUnitPicturePO> list = null;

		int cnt = standardProductUnitPictureReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitPictureReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitPicturePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitPicturePO> findStandardProductUnitPictureAll(StandardProductUnitPicturePO po) {

		return standardProductUnitPictureReadDAO.findAll(po,null);
	}
	
}
	