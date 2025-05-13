package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProdPictureReadService;
import com.egeo.components.product.manage.read.MerchantProdPictureReadManage;
import com.egeo.components.product.condition.MerchantProdPictureCondition;
import com.egeo.components.product.converter.MerchantProdPictureConverter;
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.components.product.po.MerchantProdPicturePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProdPictureReadService")
public class MerchantProdPictureReadServiceImpl  implements MerchantProdPictureReadService {
	@Autowired
	private MerchantProdPictureReadManage merchantProdPictureReadManage;

	@Override
	public MerchantProdPictureDTO findMerchantProdPictureById(MerchantProdPictureDTO dto) {
		MerchantProdPicturePO po = MerchantProdPictureConverter.toPO(dto);
		MerchantProdPicturePO list = merchantProdPictureReadManage.findMerchantProdPictureById(po);		
		return MerchantProdPictureConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProdPictureDTO> findMerchantProdPictureOfPage(MerchantProdPictureDTO dto, Pagination page) {
		MerchantProdPicturePO po = MerchantProdPictureConverter.toPO(dto);
        PageResult<MerchantProdPicturePO> pageResult = merchantProdPictureReadManage.findMerchantProdPictureOfPage(po, page);
        
        List<MerchantProdPictureDTO> list = MerchantProdPictureConverter.toDTO(pageResult.getList());
        PageResult<MerchantProdPictureDTO> result = new PageResult<MerchantProdPictureDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProdPictureDTO> findMerchantProdPictureAll(MerchantProdPictureDTO dto) {
		MerchantProdPicturePO po = MerchantProdPictureConverter.toPO(dto);
		List<MerchantProdPicturePO> list = merchantProdPictureReadManage.findMerchantProdPictureAll(po);		
		return MerchantProdPictureConverter.toDTO(list);
	}
	/**
	 * 根据su草稿id查询su草稿图片信息
	 * @param merchantProductId
	 * @return
	 */
	@Override
	public List<MerchantProdPictureDTO> findByMerchantProductId(Long merchantProductId) {
		List<MerchantProdPictureDTO> list = new ArrayList<>();
		List<MerchantProdPictureCondition> merchantProdPictureConditionList = merchantProdPictureReadManage.findByMerchantProductId(merchantProductId);
		for (MerchantProdPictureCondition merchantProdPictureCondition2 : merchantProdPictureConditionList) {
			MerchantProdPictureDTO merchantProdPictureDTO = MerchantProdPictureConverter.toDTO(merchantProdPictureCondition2);
			merchantProdPictureDTO.setPictureUrl(merchantProdPictureCondition2.getPictureUrl());
			list.add(merchantProdPictureDTO);
		}
		return list;
	}
}
	