package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.StoreStockChangeApplyPictureReadManage;
import com.egeo.components.stock.dao.read.StoreStockChangeApplyPictureReadDAO;
import com.egeo.components.stock.po.StoreStockChangeApplyPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreStockChangeApplyPictureReadManageImpl implements StoreStockChangeApplyPictureReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreStockChangeApplyPictureReadDAO storeStockChangeApplyPictureReadDAO;
	
	public StoreStockChangeApplyPicturePO findStoreStockChangeApplyPictureById(StoreStockChangeApplyPicturePO po) {
		StoreStockChangeApplyPicturePO storeStockChangeApplyPicturepo = new StoreStockChangeApplyPicturePO();
		storeStockChangeApplyPicturepo.setId(po.getId());
		return storeStockChangeApplyPictureReadDAO.findById(storeStockChangeApplyPicturepo);
	}

	public PageResult<StoreStockChangeApplyPicturePO> findStoreStockChangeApplyPictureOfPage(StoreStockChangeApplyPicturePO po, Pagination page) {
		
		PageResult<StoreStockChangeApplyPicturePO> pageResult = new PageResult<StoreStockChangeApplyPicturePO>();
		List<StoreStockChangeApplyPicturePO> list = null;

		int cnt = storeStockChangeApplyPictureReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeStockChangeApplyPictureReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreStockChangeApplyPicturePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreStockChangeApplyPicturePO> findStoreStockChangeApplyPictureAll(StoreStockChangeApplyPicturePO po) {

		return storeStockChangeApplyPictureReadDAO.findAll(po,null);
	}
	
}
	