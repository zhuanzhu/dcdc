package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdAttValueManage;
import com.egeo.components.product.facade.MerchantProdAttValueFacade;
import com.egeo.components.product.dto.MerchantProdAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdAttValue")
public class MerchantProdAttValueManageImpl implements MerchantProdAttValueManage{

	
	@Resource(name="merchantProdAttValueFacade")
	private MerchantProdAttValueFacade merchantProdAttValueFacade;

	@Override
	public MerchantProdAttValueDTO findMerchantProdAttValueById(MerchantProdAttValueDTO dto) {
		return merchantProdAttValueFacade.findMerchantProdAttValueById(dto);
	}

	@Override
	public PageResult<MerchantProdAttValueDTO> findMerchantProdAttValueOfPage(MerchantProdAttValueDTO dto, Pagination page) {
		return merchantProdAttValueFacade.findMerchantProdAttValueOfPage(dto, page);
	}

	@Override
	public List<MerchantProdAttValueDTO> findMerchantProdAttValueAll(MerchantProdAttValueDTO dto) {
		return merchantProdAttValueFacade.findMerchantProdAttValueAll(dto);
	}

	@Override
	public Long insertMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto) {
		return merchantProdAttValueFacade.insertMerchantProdAttValueWithTx(dto);
	}

	@Override
	public int updateMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto) {
		return merchantProdAttValueFacade.updateMerchantProdAttValueWithTx(dto);
	}

	@Override
	public int deleteMerchantProdAttValueWithTx(MerchantProdAttValueDTO dto) {
		return merchantProdAttValueFacade.deleteMerchantProdAttValueWithTx(dto);
	}

	@Override
    public void insertList(List<String> my) {
		merchantProdAttValueFacade.insertList(my);
	}
}
	