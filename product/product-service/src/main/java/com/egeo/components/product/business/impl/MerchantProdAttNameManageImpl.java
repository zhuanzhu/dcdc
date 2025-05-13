package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdAttNameManage;
import com.egeo.components.product.facade.MerchantProdAttNameFacade;
import com.egeo.components.product.dto.MerchantProdAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdAttName")
public class MerchantProdAttNameManageImpl implements MerchantProdAttNameManage{

	
	@Resource(name="merchantProdAttNameFacade")
	private MerchantProdAttNameFacade merchantProdAttNameFacade;

	@Override
	public MerchantProdAttNameDTO findMerchantProdAttNameById(MerchantProdAttNameDTO dto) {
		return merchantProdAttNameFacade.findMerchantProdAttNameById(dto);
	}

	@Override
	public PageResult<MerchantProdAttNameDTO> findMerchantProdAttNameOfPage(MerchantProdAttNameDTO dto, Pagination page) {
		return merchantProdAttNameFacade.findMerchantProdAttNameOfPage(dto, page);
	}

	@Override
	public List<MerchantProdAttNameDTO> findMerchantProdAttNameAll(MerchantProdAttNameDTO dto) {
		return merchantProdAttNameFacade.findMerchantProdAttNameAll(dto);
	}

	@Override
	public Long insertMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto) {
		return merchantProdAttNameFacade.insertMerchantProdAttNameWithTx(dto);
	}

	@Override
	public int updateMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto) {
		return merchantProdAttNameFacade.updateMerchantProdAttNameWithTx(dto);
	}

	@Override
	public int deleteMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto) {
		return merchantProdAttNameFacade.deleteMerchantProdAttNameWithTx(dto);
	}


}
	