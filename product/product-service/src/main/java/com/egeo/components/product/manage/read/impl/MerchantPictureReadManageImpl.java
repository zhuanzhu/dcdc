package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantPictureReadManage;
import com.egeo.components.product.dao.read.MerchantPictureReadDAO;
import com.egeo.components.product.po.MerchantPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantPictureReadManageImpl implements MerchantPictureReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantPictureReadDAO merchantPictureReadDAO;
	
	public MerchantPicturePO findMerchantPictureById(MerchantPicturePO po) {
		MerchantPicturePO merchantPicturepo = new MerchantPicturePO();
		merchantPicturepo.setId(po.getId());
		return merchantPictureReadDAO.findById(merchantPicturepo);
	}

	public PageResult<MerchantPicturePO> findMerchantPictureOfPage(MerchantPicturePO po, Pagination page) {
		
		PageResult<MerchantPicturePO> pageResult = new PageResult<MerchantPicturePO>();
		List<MerchantPicturePO> list = null;

		int cnt = merchantPictureReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantPictureReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantPicturePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantPicturePO> findMerchantPictureAll(MerchantPicturePO po) {

		return merchantPictureReadDAO.findAll(po,null);
	}

	@Override
	public Long findLastId() {
		return merchantPictureReadDAO.findLastId();
	}

}
	