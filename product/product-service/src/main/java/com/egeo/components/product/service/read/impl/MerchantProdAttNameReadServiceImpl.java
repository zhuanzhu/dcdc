package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProdAttNameReadService;
import com.egeo.components.product.manage.read.MerchantProdAttNameReadManage;
import com.egeo.components.product.converter.MerchantProdAttNameConverter;
import com.egeo.components.product.dto.MerchantProdAttNameDTO;
import com.egeo.components.product.po.MerchantProdAttNamePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdAttNameReadService")
public class MerchantProdAttNameReadServiceImpl  implements MerchantProdAttNameReadService {
	@Autowired
	private MerchantProdAttNameReadManage merchantProdAttNameReadManage;

	@Override
	public MerchantProdAttNameDTO findMerchantProdAttNameById(MerchantProdAttNameDTO dto) {
		MerchantProdAttNamePO po = MerchantProdAttNameConverter.toPO(dto);
		MerchantProdAttNamePO list = merchantProdAttNameReadManage.findMerchantProdAttNameById(po);		
		return MerchantProdAttNameConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProdAttNameDTO> findMerchantProdAttNameOfPage(MerchantProdAttNameDTO dto, Pagination page) {
		MerchantProdAttNamePO po = MerchantProdAttNameConverter.toPO(dto);
        PageResult<MerchantProdAttNamePO> pageResult = merchantProdAttNameReadManage.findMerchantProdAttNameOfPage(po, page);
        
        List<MerchantProdAttNameDTO> list = MerchantProdAttNameConverter.toDTO(pageResult.getList());
        PageResult<MerchantProdAttNameDTO> result = new PageResult<MerchantProdAttNameDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProdAttNameDTO> findMerchantProdAttNameAll(MerchantProdAttNameDTO dto) {
		MerchantProdAttNamePO po = MerchantProdAttNameConverter.toPO(dto);
		List<MerchantProdAttNamePO> list = merchantProdAttNameReadManage.findMerchantProdAttNameAll(po);		
		return MerchantProdAttNameConverter.toDTO(list);
	}
}
	