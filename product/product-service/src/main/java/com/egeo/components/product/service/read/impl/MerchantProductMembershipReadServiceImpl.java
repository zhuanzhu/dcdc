package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProductMembershipReadService;
import com.egeo.components.product.manage.read.MerchantProductMembershipReadManage;
import com.egeo.components.product.converter.MerchantProductMembershipConverter;
import com.egeo.components.product.dto.MerchantProductMembershipDTO;
import com.egeo.components.product.po.MerchantProductMembershipPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductMembershipReadService")
public class MerchantProductMembershipReadServiceImpl  implements MerchantProductMembershipReadService {
	@Autowired
	private MerchantProductMembershipReadManage merchantProductMembershipReadManage;

	@Override
	public MerchantProductMembershipDTO findMerchantProductMembershipById(MerchantProductMembershipDTO dto) {
		MerchantProductMembershipPO po = MerchantProductMembershipConverter.toPO(dto);
		MerchantProductMembershipPO list = merchantProductMembershipReadManage.findMerchantProductMembershipById(po);		
		return MerchantProductMembershipConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProductMembershipDTO> findMerchantProductMembershipOfPage(MerchantProductMembershipDTO dto, Pagination page) {
		MerchantProductMembershipPO po = MerchantProductMembershipConverter.toPO(dto);
        PageResult<MerchantProductMembershipPO> pageResult = merchantProductMembershipReadManage.findMerchantProductMembershipOfPage(po, page);
        
        List<MerchantProductMembershipDTO> list = MerchantProductMembershipConverter.toDTO(pageResult.getList());
        PageResult<MerchantProductMembershipDTO> result = new PageResult<MerchantProductMembershipDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProductMembershipDTO> findMerchantProductMembershipAll(MerchantProductMembershipDTO dto) {
		MerchantProductMembershipPO po = MerchantProductMembershipConverter.toPO(dto);
		List<MerchantProductMembershipPO> list = merchantProductMembershipReadManage.findMerchantProductMembershipAll(po);		
		return MerchantProductMembershipConverter.toDTO(list);
	}
}
	