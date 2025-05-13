package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.BannerReadManage;
import com.egeo.components.cms.dao.read.BannerReadDAO;
import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.BannerPickPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class BannerReadManageImpl implements BannerReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BannerReadDAO bannerReadDAO;
	
	public BannerPO findBannerById(BannerPO po) {
		BannerPO bannerpo = new BannerPO();
		bannerpo.setId(po.getId());
		return bannerReadDAO.findById(bannerpo);
	}

	public PageResult<BannerPO> findBannerOfPage(BannerPO po, Pagination page) {
		
		PageResult<BannerPO> pageResult = new PageResult<BannerPO>();
		List<BannerPO> list = null;

		int cnt = bannerReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = bannerReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<BannerPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<BannerPO> findBannerAll(BannerPO po) {

		return bannerReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<Map<String, Object>> findPickBannerOfPage(BannerPickPO po, Pagination page) {
		PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
		List<Map<String, Object>> list = null;
		
		int cnt = bannerReadDAO.countPickBannerOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = bannerReadDAO.findPickBannerOfPage(po, page);
			
		} else {
			list = new ArrayList<Map<String, Object>>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public List<BannerPO> queryBannerListByInstIdAndCompanyId(Long instId, Long companyId,Long companyAllId) {
		return bannerReadDAO.queryBannerListByInstIdAndCompanyId(instId,companyId,companyAllId);
	}

	@Override
	public PageResult<BannerPO> queryBannerPage(String name, Integer linkType, List<Long> companyIdList,List<Long> bannerIdList,
			Integer belongPage,Pagination page, Integer enabled, Integer belongPageType,Long platformId,Integer companyType) {
		List<BannerPO> poList=bannerReadDAO.queryBannerPage(
				name,linkType,companyIdList,bannerIdList,belongPage,page,enabled, belongPageType,platformId,companyType);
		Integer totalCount=bannerReadDAO.queryBannerPageTotalSize(
				name,linkType,companyIdList, bannerIdList,belongPage,enabled, belongPageType,platformId,companyType);
		PageResult<BannerPO> poPage=new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}
	/**
	 * 查询我的体检轮播图列表(暂不做平台区分)
	 * @param belongPage 所属页面
	 * @param companyId 公司id
	 * @param platformId 平台id
	 * @return
	 */
	@Override
	public List<BannerPO> queryBannerListByBelongPageAndCompanyId(Integer belongPage, Long companyId, Long platformId, Long companyAllId) {
		// TODO Auto-generated method stub
		return bannerReadDAO.queryBannerListByBelongPageAndCompanyId(belongPage,companyId,platformId, companyAllId);
	}

	@Override
	public BannerPO findBannerByIdAndCompanyId(Long bannerId, Long companyId, Long companyAllId) {
		return bannerReadDAO.findBannerByIdAndCompanyId(bannerId, companyId, companyAllId);
	}
	
}
	