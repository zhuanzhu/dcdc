package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.egeo.components.product.facade.SellPlatformFacade;
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProductFeedbackManage;
import com.egeo.components.product.facade.MerchantProductFeedbackFacade;
import com.egeo.components.product.dto.MerchantProductFeedbackDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductFeedback")
public class MerchantProductFeedbackManageImpl implements MerchantProductFeedbackManage{

	
	@Resource(name="merchantProductFeedbackFacade")
	private MerchantProductFeedbackFacade merchantProductFeedbackFacade;

	@Resource(name = "sellPlatformFacade")
	private SellPlatformFacade sellPlatformFacade;

	@Override
	public MerchantProductFeedbackDTO findMerchantProductFeedbackById(MerchantProductFeedbackDTO dto) {
		return merchantProductFeedbackFacade.findMerchantProductFeedbackById(dto);
	}

	@Override
	public PageResult<MerchantProductFeedbackDTO> findMerchantProductFeedbackOfPage(MerchantProductFeedbackDTO dto, Pagination page) {
		return merchantProductFeedbackFacade.findMerchantProductFeedbackOfPage(dto, page);
	}

	@Override
	public List<MerchantProductFeedbackDTO> findMerchantProductFeedbackAll(MerchantProductFeedbackDTO dto) {
		return merchantProductFeedbackFacade.findMerchantProductFeedbackAll(dto);
	}

	@Override
	public Long insertMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto) {
		return merchantProductFeedbackFacade.insertMerchantProductFeedbackWithTx(dto);
	}

	@Override
	public int updateMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto) {
		return merchantProductFeedbackFacade.updateMerchantProductFeedbackWithTx(dto);
	}

	@Override
	public int deleteMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto) {
		return merchantProductFeedbackFacade.deleteMerchantProductFeedbackWithTx(dto);
	}

	@Override
	public Map<String, Object> findComparePrice(SellPlatformDTO dto) {
		Map<String, Object> res = new HashMap<>();
		List<Map> dataList = new ArrayList<>();
		String feedBackDoc = null;
		List<SellPlatformDTO> ls = sellPlatformFacade.findSellPlatformAll(dto);
		for (SellPlatformDTO obj:ls) {
			Map<String, Object> sellPlatform = new HashMap<>();
			sellPlatform.put("id", obj.getId());
			sellPlatform.put("platformName", obj.getName());
			dataList.add(sellPlatform);
		}
		CommodityProductUnitCompareDTO cpuc = sellPlatformFacade.findCommodityProductUnitCompareAll(
				new CommodityProductUnitCompareDTO());
		if (EmptyUtil.isNotEmpty(cpuc)) {
			feedBackDoc = cpuc.getPlatformName();
		}
		res.put("comparePlatform", dataList);
		res.put("feedBackDoc", feedBackDoc);
		return res;
	}

	@Override
	public boolean doFeedbackOperator(MerchantProductFeedbackDTO dto) {
		int i = merchantProductFeedbackFacade.updateMerchantProductFeedbackWithTx(dto);
		return i > 0;
	}

	@Override
	public boolean insertComparePriceInfo(MerchantProductFeedbackDTO dto) {
		Long i = merchantProductFeedbackFacade.insertMerchantProductFeedbackWithTx(
				merchantProductFeedbackFacade.findPuAndSuInfo(dto));
		return i > 0;
	}


}
	