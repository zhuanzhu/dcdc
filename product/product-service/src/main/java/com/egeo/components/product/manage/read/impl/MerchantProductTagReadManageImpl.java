package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProductTagReadManage;
import com.egeo.components.product.condition.MerchantProductTagCondition;
import com.egeo.components.product.dao.read.MerchantProductTagReadDAO;
import com.egeo.components.product.po.MerchantProductTagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProductTagReadManageImpl implements MerchantProductTagReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductTagReadDAO merchantProductTagReadDAO;
	
	public MerchantProductTagPO findMerchantProductTagById(MerchantProductTagPO po) {
		MerchantProductTagPO merchantProductTagpo = new MerchantProductTagPO();
		merchantProductTagpo.setId(po.getId());
		return merchantProductTagReadDAO.findById(merchantProductTagpo);
	}

	public PageResult<MerchantProductTagPO> findMerchantProductTagOfPage(MerchantProductTagPO po, Pagination page) {
		
		PageResult<MerchantProductTagPO> pageResult = new PageResult<MerchantProductTagPO>();
		List<MerchantProductTagPO> list = null;

		int cnt = merchantProductTagReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = merchantProductTagReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MerchantProductTagPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MerchantProductTagPO> findMerchantProductTagAll(MerchantProductTagPO po) {

		return merchantProductTagReadDAO.findAll(po,null);
	}
	/**
	 * 根据su草稿id查询标签信息集合
	 * @param id
	 * @return
	 */
	@Override
	public List<MerchantProductTagCondition> findTagAllByMerchantProductId(Long merchantProductId) {
		// TODO Auto-generated method stub
		return merchantProductTagReadDAO.findTagAllByMerchantProductId(merchantProductId);
	}
	
}
	