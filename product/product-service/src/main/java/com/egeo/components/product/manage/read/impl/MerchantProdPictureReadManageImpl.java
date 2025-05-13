package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProdPictureReadManage;
import com.egeo.components.product.condition.MerchantProdPictureCondition;
import com.egeo.components.product.dao.read.MerchantProdPictureReadDAO;
import com.egeo.components.product.po.MerchantProdPicturePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProdPictureReadManageImpl implements MerchantProdPictureReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdPictureReadDAO merchantProdPictureReadDAO;
	
	public MerchantProdPicturePO findMerchantProdPictureById(MerchantProdPicturePO po) {
		MerchantProdPicturePO merchantProdPicturepo = new MerchantProdPicturePO();
		merchantProdPicturepo.setId(po.getId());
		return merchantProdPictureReadDAO.findById(merchantProdPicturepo);
	}

	public PageResult<MerchantProdPicturePO> findMerchantProdPictureOfPage(MerchantProdPicturePO po, Pagination page) {
		
		PageResult<MerchantProdPicturePO> pageResult = new PageResult<MerchantProdPicturePO>();
		List<MerchantProdPicturePO> list = null;

		int cnt = merchantProdPictureReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProdPictureReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProdPicturePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProdPicturePO> findMerchantProdPictureAll(MerchantProdPicturePO po) {

		return merchantProdPictureReadDAO.findAll(po,null);
	}
	/**
	 * 根据su草稿id查询su草稿图片信息
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public List<MerchantProdPictureCondition> findByMerchantProductId(Long merchantProductId) {
		// TODO Auto-generated method stub
		return merchantProdPictureReadDAO.findByMerchantProductId(merchantProductId);
	}
	
}
	