package com.egeo.components.product.service.read.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantReadService;
import com.egeo.components.product.manage.read.MerchantReadManage;
import com.egeo.components.product.converter.MerchantConverter;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.po.MerchantPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantReadService")
public class MerchantReadServiceImpl  implements MerchantReadService {
	@Autowired
	private MerchantReadManage merchantReadManage;

	@Override
	public MerchantDTO findMerchantById(MerchantDTO dto) {
		MerchantPO po = MerchantConverter.toPO(dto);
		MerchantPO list = merchantReadManage.findMerchantById(po);		
		return MerchantConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantDTO> findMerchantOfPage(MerchantDTO dto, Pagination page) {
		MerchantPO po = MerchantConverter.toPO(dto);
        PageResult<MerchantPO> pageResult = merchantReadManage.findMerchantOfPage(po, page);
        
        List<MerchantDTO> list = MerchantConverter.toDTO(pageResult.getList());
        PageResult<MerchantDTO> result = new PageResult<MerchantDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantDTO> findMerchantAll(MerchantDTO dto) {
		MerchantPO po = MerchantConverter.toPO(dto);
		List<MerchantPO> list = merchantReadManage.findMerchantAll(po);		
		return MerchantConverter.toDTO(list);
	}

	/**
	 * 查询id为1和大于等于1000的所有运营方
	 * @return
	 */
	@Override
	public List<MerchantDTO> findMerchantList() {
		return MerchantConverter.toDTO(merchantReadManage.findMerchantList());
	}

	/**
	 * 根据运营方类型查询运营方
	 * @return
	 */
	@Override
	public List<MerchantDTO> findMerchantListByType(Integer type) {
		return MerchantConverter.toDTO(merchantReadManage.findMerchantListByType(type));

	}
}
	