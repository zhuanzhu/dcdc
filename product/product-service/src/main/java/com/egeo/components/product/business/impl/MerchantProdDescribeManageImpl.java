package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantProdDescribeManage;
import com.egeo.components.product.facade.MerchantProdDescribeFacade;
import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdDescribe")
public class MerchantProdDescribeManageImpl implements MerchantProdDescribeManage{

	
	@Resource(name="merchantProdDescribeFacade")
	private MerchantProdDescribeFacade merchantProdDescribeFacade;

	@Override
	public MerchantProdDescribeDTO findMerchantProdDescribeById(MerchantProdDescribeDTO dto) {
		return merchantProdDescribeFacade.findMerchantProdDescribeById(dto);
	}

	@Override
	public PageResult<MerchantProdDescribeDTO> findMerchantProdDescribeOfPage(MerchantProdDescribeDTO dto, Pagination page) {
		return merchantProdDescribeFacade.findMerchantProdDescribeOfPage(dto, page);
	}

	@Override
	public List<MerchantProdDescribeDTO> findMerchantProdDescribeAll(MerchantProdDescribeDTO dto) {
		return merchantProdDescribeFacade.findMerchantProdDescribeAll(dto);
	}

	@Override
	public Long insertMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto) {
		return merchantProdDescribeFacade.insertMerchantProdDescribeWithTx(dto);
	}

	@Override
	public int updateMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto) {
		return merchantProdDescribeFacade.updateMerchantProdDescribeWithTx(dto);
	}

	@Override
	public int deleteMerchantProdDescribeWithTx(MerchantProdDescribeDTO dto) {
		return merchantProdDescribeFacade.deleteMerchantProdDescribeWithTx(dto);
	}


}
	