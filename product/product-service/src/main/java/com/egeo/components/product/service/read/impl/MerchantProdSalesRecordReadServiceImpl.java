package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MerchantProdSalesRecordReadService;
import com.egeo.components.product.manage.read.MerchantProdSalesRecordReadManage;
import com.egeo.components.product.converter.MerchantProdSalesRecordConverter;
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.components.product.po.MerchantProdSalesRecordPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("merchantProdSalesRecordReadService")
public class MerchantProdSalesRecordReadServiceImpl  implements MerchantProdSalesRecordReadService {
	@Autowired
	private MerchantProdSalesRecordReadManage merchantProdSalesRecordReadManage;

	@Override
	public MerchantProdSalesRecordDTO findMerchantProdSalesRecordById(MerchantProdSalesRecordDTO dto) {
		MerchantProdSalesRecordPO po = MerchantProdSalesRecordConverter.toPO(dto);
		MerchantProdSalesRecordPO list = merchantProdSalesRecordReadManage.findMerchantProdSalesRecordById(po);		
		return MerchantProdSalesRecordConverter.toDTO(list);
	}

	@Override
	public PageResult<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordOfPage(MerchantProdSalesRecordDTO dto, Pagination page) {
		MerchantProdSalesRecordPO po = MerchantProdSalesRecordConverter.toPO(dto);
        PageResult<MerchantProdSalesRecordPO> pageResult = merchantProdSalesRecordReadManage.findMerchantProdSalesRecordOfPage(po, page);
        
        List<MerchantProdSalesRecordDTO> list = MerchantProdSalesRecordConverter.toDTO(pageResult.getList());
        PageResult<MerchantProdSalesRecordDTO> result = new PageResult<MerchantProdSalesRecordDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordAll(MerchantProdSalesRecordDTO dto) {
		MerchantProdSalesRecordPO po = MerchantProdSalesRecordConverter.toPO(dto);
		List<MerchantProdSalesRecordPO> list = merchantProdSalesRecordReadManage.findMerchantProdSalesRecordAll(po);		
		return MerchantProdSalesRecordConverter.toDTO(list);
	}
	/**
	 * 根据suid查询su销售量
	 * @param standardUnitId
	 * @return
	 */
	@Override
	public Long findSalesRecordByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return merchantProdSalesRecordReadManage.findSalesRecordByStandardUnitId(standardUnitId);
	}
	/**
	 * 根据puid查询pu销售信息
	 * @param puId
	 * @return
	 */
	@Override
	public MerchantProdSalesRecordDTO findByPUId(Long puId) {
		MerchantProdSalesRecordPO merchantProdSalesRecordPO = merchantProdSalesRecordReadManage.findByPUId(puId);
		if(EmptyUtil.isEmpty(merchantProdSalesRecordPO)){
			return null;
		}
		return MerchantProdSalesRecordConverter.toDTO(merchantProdSalesRecordPO);
	}
}
	