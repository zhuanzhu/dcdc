package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProdAttValueReadService;
import com.egeo.components.product.manage.read.MerchantProdAttValueReadManage;
import com.egeo.components.product.converter.MerchantProdAttValueConverter;
import com.egeo.components.product.dto.MerchantProdAttValueDTO;
import com.egeo.components.product.po.MerchantProdAttValuePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdAttValueReadService")
public class MerchantProdAttValueReadServiceImpl  implements MerchantProdAttValueReadService {
	@Autowired
	private MerchantProdAttValueReadManage merchantProdAttValueReadManage;

	@Override
	public MerchantProdAttValueDTO findMerchantProdAttValueById(MerchantProdAttValueDTO dto) {
		MerchantProdAttValuePO po = MerchantProdAttValueConverter.toPO(dto);
		MerchantProdAttValuePO list = merchantProdAttValueReadManage.findMerchantProdAttValueById(po);		
		return MerchantProdAttValueConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProdAttValueDTO> findMerchantProdAttValueOfPage(MerchantProdAttValueDTO dto, Pagination page) {
		MerchantProdAttValuePO po = MerchantProdAttValueConverter.toPO(dto);
        PageResult<MerchantProdAttValuePO> pageResult = merchantProdAttValueReadManage.findMerchantProdAttValueOfPage(po, page);
        
        List<MerchantProdAttValueDTO> list = MerchantProdAttValueConverter.toDTO(pageResult.getList());
        PageResult<MerchantProdAttValueDTO> result = new PageResult<MerchantProdAttValueDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProdAttValueDTO> findMerchantProdAttValueAll(MerchantProdAttValueDTO dto) {
		MerchantProdAttValuePO po = MerchantProdAttValueConverter.toPO(dto);
		List<MerchantProdAttValuePO> list = merchantProdAttValueReadManage.findMerchantProdAttValueAll(po);		
		return MerchantProdAttValueConverter.toDTO(list);
	}
}
	