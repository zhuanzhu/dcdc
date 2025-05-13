package com.egeo.components.product.service.write.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.StandardUnitCombinationSuWriteService;
import com.egeo.components.product.manage.write.StandardUnitCombinationSuWriteManage;
import com.egeo.components.product.converter.StandardUnitCombinationSuConverter;
import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;

@Service("standardUnitCombinationSuWriteService")
public class StandardUnitCombinationSuWriteServiceImpl  implements StandardUnitCombinationSuWriteService {
	@Autowired
	private StandardUnitCombinationSuWriteManage standardUnitCombinationSuWriteManage;

	@Override
	public Long insertStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto) {
		StandardUnitCombinationSuPO po = StandardUnitCombinationSuConverter.toPO(dto);
		Long rt = standardUnitCombinationSuWriteManage.insertStandardUnitCombinationSuWithTx(po);
		return rt;
	}

	@Override
	public int updateStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto) {
		StandardUnitCombinationSuPO po = StandardUnitCombinationSuConverter.toPO(dto);
		int rt = standardUnitCombinationSuWriteManage.updateStandardUnitCombinationSuWithTx(po);
		return rt;
	}

	@Override
	public int deleteStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto) {
		StandardUnitCombinationSuPO po = StandardUnitCombinationSuConverter.toPO(dto);
		int rt = standardUnitCombinationSuWriteManage.deleteStandardUnitCombinationSuWithTx(po);
		return rt;
	}
	/**
	 * 批量保存su组合和su商品关系
	 * @param standardUnitCombinationId
	 * @param standardUnitList
	 * @return
	 */
	@Override
	public boolean saveStandardUnitCombinationSuAllWithTx(Long standardUnitCombinationId, List<Long> standardUnits,Integer source,List<Map<String,String>> productAndSkuMap) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuWriteManage.saveStandardUnitCombinationSuAllWithTx(standardUnitCombinationId, standardUnits,source,productAndSkuMap);
	}
	/**
	 * 根据su组合与su关系id修改排序
	 * @param standardUnitCombinationSuId
	 * @param sortValue
	 * @return
	 */
	@Override
	public boolean sortValueByStandardUnitCombinationIdWithTx(Long standardUnitCombinationSuId, Integer sortValue) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuWriteManage.sortValueByStandardUnitCombinationIdWithTx(standardUnitCombinationSuId, sortValue);
	}
}
