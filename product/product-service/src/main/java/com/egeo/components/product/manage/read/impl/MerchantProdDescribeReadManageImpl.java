package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProdDescribeReadManage;
import com.egeo.components.product.dao.read.MerchantProdDescribeReadDAO;
import com.egeo.components.product.po.MerchantProdDescribePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProdDescribeReadManageImpl implements MerchantProdDescribeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProdDescribeReadDAO merchantProdDescribeReadDAO;
	
	public MerchantProdDescribePO findMerchantProdDescribeById(MerchantProdDescribePO po) {
		MerchantProdDescribePO merchantProdDescribepo = new MerchantProdDescribePO();
		merchantProdDescribepo.setId(po.getId());
		return merchantProdDescribeReadDAO.findById(merchantProdDescribepo);
	}

	public PageResult<MerchantProdDescribePO> findMerchantProdDescribeOfPage(MerchantProdDescribePO po, Pagination page) {
		
		PageResult<MerchantProdDescribePO> pageResult = new PageResult<MerchantProdDescribePO>();
		List<MerchantProdDescribePO> list = null;

		int cnt = merchantProdDescribeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProdDescribeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProdDescribePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProdDescribePO> findMerchantProdDescribeAll(MerchantProdDescribePO po) {

		return merchantProdDescribeReadDAO.findAll(po,null);
	}
	/**
	 * 根据su草稿id查询su草稿详情
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public MerchantProdDescribePO findByMerchantProdId(Long merchantProductId) {
		// TODO Auto-generated method stub
		return merchantProdDescribeReadDAO.findByMerchantProdId(merchantProductId);
	}
	
}
	