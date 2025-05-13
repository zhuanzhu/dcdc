package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProdClientReadService;
import com.egeo.components.product.manage.read.MerchantProdClientReadManage;
import com.egeo.components.product.converter.MerchantProdClientConverter;
import com.egeo.components.product.dto.MerchantProdClientDTO;
import com.egeo.components.product.po.MerchantProdClientPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdClientReadService")
public class MerchantProdClientReadServiceImpl  implements MerchantProdClientReadService {
	@Autowired
	private MerchantProdClientReadManage merchantProdClientReadManage;

	@Override
	public MerchantProdClientDTO findMerchantProdClientById(MerchantProdClientDTO dto) {
		MerchantProdClientPO po = MerchantProdClientConverter.toPO(dto);
		MerchantProdClientPO list = merchantProdClientReadManage.findMerchantProdClientById(po);		
		return MerchantProdClientConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProdClientDTO> findMerchantProdClientOfPage(MerchantProdClientDTO dto, Pagination page) {
		MerchantProdClientPO po = MerchantProdClientConverter.toPO(dto);
        PageResult<MerchantProdClientPO> pageResult = merchantProdClientReadManage.findMerchantProdClientOfPage(po, page);
        
        List<MerchantProdClientDTO> list = MerchantProdClientConverter.toDTO(pageResult.getList());
        PageResult<MerchantProdClientDTO> result = new PageResult<MerchantProdClientDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProdClientDTO> findMerchantProdClientAll(MerchantProdClientDTO dto) {
		MerchantProdClientPO po = MerchantProdClientConverter.toPO(dto);
		List<MerchantProdClientPO> list = merchantProdClientReadManage.findMerchantProdClientAll(po);		
		return MerchantProdClientConverter.toDTO(list);
	}
}
	