package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantPictureReadService;
import com.egeo.components.product.manage.read.MerchantPictureReadManage;
import com.egeo.components.product.converter.MerchantPictureConverter;
import com.egeo.components.product.dto.MerchantPictureDTO;
import com.egeo.components.product.po.MerchantPicturePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantPictureReadService")
public class MerchantPictureReadServiceImpl  implements MerchantPictureReadService {
	@Autowired
	private MerchantPictureReadManage merchantPictureReadManage;

	@Override
	public MerchantPictureDTO findMerchantPictureById(MerchantPictureDTO dto) {
		MerchantPicturePO po = MerchantPictureConverter.toPO(dto);
		MerchantPicturePO list = merchantPictureReadManage.findMerchantPictureById(po);		
		return MerchantPictureConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantPictureDTO> findMerchantPictureOfPage(MerchantPictureDTO dto, Pagination page) {
		MerchantPicturePO po = MerchantPictureConverter.toPO(dto);
        PageResult<MerchantPicturePO> pageResult = merchantPictureReadManage.findMerchantPictureOfPage(po, page);
        
        List<MerchantPictureDTO> list = MerchantPictureConverter.toDTO(pageResult.getList());
        PageResult<MerchantPictureDTO> result = new PageResult<MerchantPictureDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantPictureDTO> findMerchantPictureAll(MerchantPictureDTO dto) {
		MerchantPicturePO po = MerchantPictureConverter.toPO(dto);
		List<MerchantPicturePO> list = merchantPictureReadManage.findMerchantPictureAll(po);		
		return MerchantPictureConverter.toDTO(list);
	}

	@Override
	public Long findLastId() {
		return merchantPictureReadManage.findLastId();
	}
}
	