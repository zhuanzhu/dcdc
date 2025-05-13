package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MpSerachKeywordReadManage;
import com.egeo.components.product.dao.read.MpSerachKeywordReadDAO;
import com.egeo.components.product.po.MpSerachKeywordPO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;


@Service
public class MpSerachKeywordReadManageImpl implements MpSerachKeywordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MpSerachKeywordReadDAO mpSerachKeywordReadDAO;
	
	public MpSerachKeywordPO findMpSerachKeywordById(MpSerachKeywordPO po) {
		MpSerachKeywordPO mpSerachKeywordpo = new MpSerachKeywordPO();
		mpSerachKeywordpo.setId(po.getId());
		return mpSerachKeywordReadDAO.findById(mpSerachKeywordpo);
	}

	public PageResult<MpSerachKeywordPO> findMpSerachKeywordOfPage(MpSerachKeywordPO po, Pagination page) {
		
		PageResult<MpSerachKeywordPO> pageResult = new PageResult<MpSerachKeywordPO>();
		List<MpSerachKeywordPO> list = null;

		int cnt = mpSerachKeywordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = mpSerachKeywordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MpSerachKeywordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MpSerachKeywordPO> findMpSerachKeywordAll(MpSerachKeywordPO po) {

		return mpSerachKeywordReadDAO.findAll(po,null);
	}
	/**
	 * 根据suId查询su草稿关键词
	 * @param id
	 * @param platformId
	 * @return
	 */
	@Override
	public List<String> keyWordByMerchantProductId(Long merchantProductId, Long platformId) {
		if(merchantProductId == null)
			throw new BusinessException("suid不能为空");
		if(platformId == null)
			throw new BusinessException("平台id不能为空");
		return mpSerachKeywordReadDAO.keyWordByMerchantProductId(merchantProductId, platformId);
	}
	
}
	