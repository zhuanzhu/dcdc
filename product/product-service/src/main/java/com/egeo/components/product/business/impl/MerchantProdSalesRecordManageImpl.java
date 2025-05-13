package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdSalesRecordManage;
import com.egeo.components.product.facade.MerchantProdSalesRecordFacade;
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdSalesRecord")
public class MerchantProdSalesRecordManageImpl implements MerchantProdSalesRecordManage{

	
	@Resource(name="merchantProdSalesRecordFacade")
	private MerchantProdSalesRecordFacade merchantProdSalesRecordFacade;

	@Override
	public MerchantProdSalesRecordDTO findMerchantProdSalesRecordById(MerchantProdSalesRecordDTO dto) {
		return merchantProdSalesRecordFacade.findMerchantProdSalesRecordById(dto);
	}

	@Override
	public PageResult<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordOfPage(MerchantProdSalesRecordDTO dto, Pagination page) {
		return merchantProdSalesRecordFacade.findMerchantProdSalesRecordOfPage(dto, page);
	}

	@Override
	public List<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordAll(MerchantProdSalesRecordDTO dto) {
		return merchantProdSalesRecordFacade.findMerchantProdSalesRecordAll(dto);
	}

	@Override
	public Long insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto) {
		return merchantProdSalesRecordFacade.insertMerchantProdSalesRecordWithTx(dto);
	}

	@Override
	public int updateMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto) {
		return merchantProdSalesRecordFacade.updateMerchantProdSalesRecordWithTx(dto);
	}

	@Override
	public int deleteMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto) {
		return merchantProdSalesRecordFacade.deleteMerchantProdSalesRecordWithTx(dto);
	}


}
	