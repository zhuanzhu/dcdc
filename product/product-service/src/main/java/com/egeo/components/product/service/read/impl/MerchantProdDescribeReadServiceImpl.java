package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProdDescribeReadService;
import com.egeo.components.product.manage.read.MerchantProdDescribeReadManage;
import com.egeo.components.product.converter.MerchantProdDescribeConverter;
import com.egeo.components.product.dto.MerchantProdDescribeDTO;
import com.egeo.components.product.po.MerchantProdDescribePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdDescribeReadService")
public class MerchantProdDescribeReadServiceImpl  implements MerchantProdDescribeReadService {
	@Autowired
	private MerchantProdDescribeReadManage merchantProdDescribeReadManage;

	@Override
	public MerchantProdDescribeDTO findMerchantProdDescribeById(MerchantProdDescribeDTO dto) {
		MerchantProdDescribePO po = MerchantProdDescribeConverter.toPO(dto);
		MerchantProdDescribePO list = merchantProdDescribeReadManage.findMerchantProdDescribeById(po);		
		return MerchantProdDescribeConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProdDescribeDTO> findMerchantProdDescribeOfPage(MerchantProdDescribeDTO dto, Pagination page) {
		MerchantProdDescribePO po = MerchantProdDescribeConverter.toPO(dto);
        PageResult<MerchantProdDescribePO> pageResult = merchantProdDescribeReadManage.findMerchantProdDescribeOfPage(po, page);
        
        List<MerchantProdDescribeDTO> list = MerchantProdDescribeConverter.toDTO(pageResult.getList());
        PageResult<MerchantProdDescribeDTO> result = new PageResult<MerchantProdDescribeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProdDescribeDTO> findMerchantProdDescribeAll(MerchantProdDescribeDTO dto) {
		MerchantProdDescribePO po = MerchantProdDescribeConverter.toPO(dto);
		List<MerchantProdDescribePO> list = merchantProdDescribeReadManage.findMerchantProdDescribeAll(po);		
		return MerchantProdDescribeConverter.toDTO(list);
	}
}
	