package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.vo.MerchantListVO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantManage {

	public MerchantDTO findMerchantById(MerchantDTO dto);	

	public PageResult<MerchantDTO> findMerchantOfPage(MerchantDTO dto,Pagination page);

	public List<MerchantDTO> findMerchantAll(MerchantDTO dto);

	Long insertMerchantWithTx(MerchantDTO dto);

	int updateMerchantWithTx(MerchantDTO dto);

	int deleteMerchantWithTx(MerchantDTO dto);

    List<MerchantListVO> findStartedMerchantList();
}
	