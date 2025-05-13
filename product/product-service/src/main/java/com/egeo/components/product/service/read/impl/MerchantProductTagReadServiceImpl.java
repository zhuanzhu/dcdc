package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProductTagReadService;
import com.egeo.components.product.manage.read.MerchantProductTagReadManage;
import com.egeo.components.product.condition.MerchantProductTagCondition;
import com.egeo.components.product.converter.MerchantProductTagConverter;
import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.components.product.po.MerchantProductTagPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductTagReadService")
public class MerchantProductTagReadServiceImpl  implements MerchantProductTagReadService {
	@Autowired
	private MerchantProductTagReadManage merchantProductTagReadManage;

	@Override
	public MerchantProductTagDTO findMerchantProductTagById(MerchantProductTagDTO dto) {
		MerchantProductTagPO po = MerchantProductTagConverter.toPO(dto);
		MerchantProductTagPO list = merchantProductTagReadManage.findMerchantProductTagById(po);		
		return MerchantProductTagConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProductTagDTO> findMerchantProductTagOfPage(MerchantProductTagDTO dto, Pagination page) {
		MerchantProductTagPO po = MerchantProductTagConverter.toPO(dto);
        PageResult<MerchantProductTagPO> pageResult = merchantProductTagReadManage.findMerchantProductTagOfPage(po, page);
        
        List<MerchantProductTagDTO> list = MerchantProductTagConverter.toDTO(pageResult.getList());
        PageResult<MerchantProductTagDTO> result = new PageResult<MerchantProductTagDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProductTagDTO> findMerchantProductTagAll(MerchantProductTagDTO dto) {
		MerchantProductTagPO po = MerchantProductTagConverter.toPO(dto);
		List<MerchantProductTagPO> list = merchantProductTagReadManage.findMerchantProductTagAll(po);		
		return MerchantProductTagConverter.toDTO(list);
	}
	/**
	 * 根据su草稿id查询标签信息集合
	 * @param id
	 * @return
	 */
	@Override
	public List<MerchantProductTagDTO> findTagAllByMerchantProductId(Long merchantProductId) {
		List<MerchantProductTagDTO> list = new ArrayList<>();
		List<MerchantProductTagCondition> merchantProductTagConditionList = merchantProductTagReadManage.findTagAllByMerchantProductId(merchantProductId);
		for (MerchantProductTagCondition merchantProductTagCondition : merchantProductTagConditionList) {
			MerchantProductTagDTO merchantProductTagDTO = MerchantProductTagConverter.toDTO(merchantProductTagCondition);
			merchantProductTagDTO.setTagName(merchantProductTagCondition.getTagName());
			list.add(merchantProductTagDTO);
		}
		return list;
	}
}
	