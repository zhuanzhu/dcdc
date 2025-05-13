package com.egeo.components.product.manage.read.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.product.condition.MerchantProductUnitCondition;
import com.egeo.components.product.po.CommodityDetailsPO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MerchantProductReadManage;
import com.egeo.components.product.condition.MerchantProductCondition;
import com.egeo.components.product.dao.read.MerchantProductReadDAO;
import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.config.RuntimeContext;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MerchantProductReadManageImpl implements MerchantProductReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductReadDAO merchantProductReadDAO;

	public MerchantProductCondition findMerchantProductById(MerchantProductPO po) {
		MerchantProductPO merchantProductpo = new MerchantProductPO();
		merchantProductpo.setId(po.getId());
		return merchantProductReadDAO.findMerchantProductById(merchantProductpo);
	}

	public PageResult<MerchantProductCondition> findMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date startTime, Date endTime, MerchantProductPO po, Pagination page, List<String> nameList) {
		
		PageResult<MerchantProductCondition> pageResult = new PageResult<MerchantProductCondition>();
		List<MerchantProductCondition> list = null;
		int cnt=0;
		if(storeIds.size()==0){
			list= null;
		}else{
			Long enterpriseId = null;
			if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getEnterpriseId()!=null) {
				enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
			}
			cnt= merchantProductReadDAO.countOfPages(priceStart,priceEnd,startProfit,endProfit,storeIds,startTime,endTime,enterpriseId,po,nameList,null);

			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				RuntimeContext.cacheUser();
				
				list = merchantProductReadDAO.findMerchantProductConditionOfPage(priceStart,priceEnd,startProfit,endProfit,storeIds,startTime,endTime,enterpriseId,po, page,nameList,null);
			} else {
				list = new ArrayList<MerchantProductCondition>();
			}
		}

		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public PageResult<MerchantProductCondition> findPlatformMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date startTime, Date endTime, MerchantProductPO po, Pagination page, List<String> nameList) {
		
		PageResult<MerchantProductCondition> pageResult = new PageResult<MerchantProductCondition>();
		List<MerchantProductCondition> list = null;
		int cnt=0;
		if(storeIds.size()==0){
			list= null;
		}else{
			cnt= merchantProductReadDAO.countOfPlatformPages(priceStart,priceEnd,startProfit,endProfit,storeIds,startTime,endTime,po,nameList,null);

			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				RuntimeContext.cacheUser();
				
				list = merchantProductReadDAO.findPlatformMerchantProductConditionOfPage(priceStart,priceEnd,startProfit,endProfit,storeIds,startTime,endTime,po, page,nameList,null);
			} else {
				list = new ArrayList<MerchantProductCondition>();
			}
		}

		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}
	public List<MerchantProductPO> findMerchantProductAll(MerchantProductPO po) {

		return merchantProductReadDAO.findAll(po,null);
	}
	/**
	 * 根据su草稿id查询基本信息（app预览）
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public MerchantProductCondition findMerchantProductAppById(Long merchantProductId) {
		// TODO Auto-generated method stub
		return merchantProductReadDAO.findMerchantProductAppById(merchantProductId);
	}

	@Override
	public PageResult<MerchantProductCondition> findCommodityDetailsOfPage(CommodityDetailsPO po, Pagination page) {
			PageResult<MerchantProductCondition> pageResult = new PageResult<MerchantProductCondition>();
			List<MerchantProductCondition> list = null;
			int cnt = merchantProductReadDAO.countCdOfPage(po);
			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				list = merchantProductReadDAO.findCommodityDetailsConditionOfPage(po, page);
			} else {
				list = new ArrayList<MerchantProductCondition>();
			}
			pageResult.setList(list);
			pageResult.setTotalSize(cnt);
			pageResult.setPageNo(page.getPageNo());
			pageResult.setPageSize(page.getPageSize());
			return pageResult;
	}

	@Override
	public Long findLastId() {
		return merchantProductReadDAO.findLastId();
	}

	@Override
	public long findMaxfrontSerialNumber() {
		// TODO Auto-generated method stub
		return merchantProductReadDAO.findMaxfrontSerialNumber();
	}


	@Override
	public PageResult<MerchantProductUnitCondition> exportMerchantProduct(BigDecimal priceStart, BigDecimal priceEnd,
																		  Integer startProfit, Integer endProfit,
																		  List<Long> storeIds, Date starTime, Date endTime,
																		  MerchantProductPO po, Pagination page,
																		  List<String> nameList) {
		PageResult<MerchantProductUnitCondition> pageResult = new PageResult<MerchantProductUnitCondition>();
		List<MerchantProductUnitCondition> list = null;
        Long enterpriseId = null;
        if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getEnterpriseId()!=null) {
            enterpriseId = RuntimeContext.cacheUser().getEnterpriseId();
        }
        page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());

        list = merchantProductReadDAO.exportMerchantProductConditionOfPage(priceStart,priceEnd,startProfit,endProfit,storeIds,starTime,endTime,enterpriseId,po, page,nameList,null);

        pageResult.setList(list);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
}
	