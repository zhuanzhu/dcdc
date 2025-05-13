package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProductStoreReadService;
import com.egeo.components.product.manage.read.MerchantProductStoreReadManage;
import com.egeo.components.product.converter.MerchantProductStoreConverter;
import com.egeo.components.product.dto.MerchantProductStoreDTO;
import com.egeo.components.product.po.MerchantProductStorePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductStoreReadService")
public class MerchantProductStoreReadServiceImpl  implements MerchantProductStoreReadService {
	@Autowired
	private MerchantProductStoreReadManage merchantProductStoreReadManage;

	@Override
	public MerchantProductStoreDTO findMerchantProductStoreById(MerchantProductStoreDTO dto) {
		MerchantProductStorePO po = MerchantProductStoreConverter.toPO(dto);
		MerchantProductStorePO list = merchantProductStoreReadManage.findMerchantProductStoreById(po);		
		return MerchantProductStoreConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProductStoreDTO> findMerchantProductStoreOfPage(MerchantProductStoreDTO dto, Pagination page) {
		MerchantProductStorePO po = MerchantProductStoreConverter.toPO(dto);
        PageResult<MerchantProductStorePO> pageResult = merchantProductStoreReadManage.findMerchantProductStoreOfPage(po, page);
        
        List<MerchantProductStoreDTO> list = MerchantProductStoreConverter.toDTO(pageResult.getList());
        PageResult<MerchantProductStoreDTO> result = new PageResult<MerchantProductStoreDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProductStoreDTO> findMerchantProductStoreAll(MerchantProductStoreDTO dto) {
		MerchantProductStorePO po = MerchantProductStoreConverter.toPO(dto);
		List<MerchantProductStorePO> list = merchantProductStoreReadManage.findMerchantProductStoreAll(po);		
		return MerchantProductStoreConverter.toDTO(list);
	}
}
	