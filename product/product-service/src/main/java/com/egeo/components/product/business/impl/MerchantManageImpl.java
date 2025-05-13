package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.egeo.components.product.vo.MerchantListVO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.MerchantManage;
import com.egeo.components.product.facade.MerchantFacade;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchant")
public class MerchantManageImpl implements MerchantManage{

	
	@Resource(name="merchantFacade")
	private MerchantFacade merchantFacade;

	@Override
	public MerchantDTO findMerchantById(MerchantDTO dto) {
		return merchantFacade.findMerchantById(dto);
	}

	@Override
	public PageResult<MerchantDTO> findMerchantOfPage(MerchantDTO dto, Pagination page) {
		return merchantFacade.findMerchantOfPage(dto, page);
	}

	@Override
	public List<MerchantDTO> findMerchantAll(MerchantDTO dto) {
		return merchantFacade.findMerchantAll(dto);
	}

	@Override
	public Long insertMerchantWithTx(MerchantDTO dto) {
		return merchantFacade.insertMerchantWithTx(dto);
	}

	@Override
	public int updateMerchantWithTx(MerchantDTO dto) {
		return merchantFacade.updateMerchantWithTx(dto);
	}

	@Override
	public int deleteMerchantWithTx(MerchantDTO dto) {
		return merchantFacade.deleteMerchantWithTx(dto);
	}

	@Override
	public List<MerchantListVO> findStartedMerchantList() {
		MerchantDTO merchantDTO = new MerchantDTO();
		merchantDTO.setIsStop(0);
		List<MerchantDTO> merchantAll = merchantFacade.findMerchantAll(merchantDTO);
		if(EmptyUtil.isEmpty(merchantAll)){
			throw new BusinessException("没有已启用的运营方");
		}
		List<MerchantListVO> result = new ArrayList<>();
		for(MerchantDTO dto:merchantAll){
			MerchantListVO vo=new MerchantListVO();
			vo.setId(dto.getId());
			vo.setName(dto.getName());
			result.add(vo);
		}
		return result;
	}


}
	