package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitPictureReadManage;
import com.egeo.components.product.dao.read.StandardUnitPictureReadDAO;
import com.egeo.components.product.po.StandardUnitPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitPictureReadManageImpl implements StandardUnitPictureReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitPictureReadDAO standardUnitPictureReadDAO;
	
	public StandardUnitPicturePO findStandardUnitPictureById(StandardUnitPicturePO po) {
		StandardUnitPicturePO standardUnitPicturepo = new StandardUnitPicturePO();
		standardUnitPicturepo.setId(po.getId());
		return standardUnitPictureReadDAO.findById(standardUnitPicturepo);
	}

	public PageResult<StandardUnitPicturePO> findStandardUnitPictureOfPage(StandardUnitPicturePO po, Pagination page) {
		
		PageResult<StandardUnitPicturePO> pageResult = new PageResult<StandardUnitPicturePO>();
		List<StandardUnitPicturePO> list = null;

		int cnt = standardUnitPictureReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitPictureReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitPicturePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitPicturePO> findStandardUnitPictureAll(StandardUnitPicturePO po) {

		return standardUnitPictureReadDAO.findAll(po,null);
	}

	@Override
	public String findPictureUrlBySUId(Long id) {
		return standardUnitPictureReadDAO.findPictureUrlBySUId(id);
	}

}
	