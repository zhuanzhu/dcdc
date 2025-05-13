package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProductFeedbackReadService;
import com.egeo.components.product.manage.read.MerchantProductFeedbackReadManage;
import com.egeo.components.product.converter.MerchantProductFeedbackConverter;
import com.egeo.components.product.dto.MerchantProductFeedbackDTO;
import com.egeo.components.product.po.MerchantProductFeedbackPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductFeedbackReadService")
public class MerchantProductFeedbackReadServiceImpl  implements MerchantProductFeedbackReadService {
	@Autowired
	private MerchantProductFeedbackReadManage merchantProductFeedbackReadManage;

	@Override
	public MerchantProductFeedbackDTO findMerchantProductFeedbackById(MerchantProductFeedbackDTO dto) {
		MerchantProductFeedbackPO po = MerchantProductFeedbackConverter.toPO(dto);
		MerchantProductFeedbackPO list = merchantProductFeedbackReadManage.findMerchantProductFeedbackById(po);		
		return MerchantProductFeedbackConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProductFeedbackDTO> findMerchantProductFeedbackOfPage(MerchantProductFeedbackDTO dto, Pagination page) {
		MerchantProductFeedbackPO po = MerchantProductFeedbackConverter.toPO(dto);
        PageResult<MerchantProductFeedbackPO> pageResult = merchantProductFeedbackReadManage.findMerchantProductFeedbackOfPage(po, page);
        
        List<MerchantProductFeedbackDTO> list = MerchantProductFeedbackConverter.toDTO(pageResult.getList());
        PageResult<MerchantProductFeedbackDTO> result = new PageResult<MerchantProductFeedbackDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProductFeedbackDTO> findMerchantProductFeedbackAll(MerchantProductFeedbackDTO dto) {
		MerchantProductFeedbackPO po = MerchantProductFeedbackConverter.toPO(dto);
		List<MerchantProductFeedbackPO> list = merchantProductFeedbackReadManage.findMerchantProductFeedbackAll(po);		
		return MerchantProductFeedbackConverter.toDTO(list);
	}
}
	