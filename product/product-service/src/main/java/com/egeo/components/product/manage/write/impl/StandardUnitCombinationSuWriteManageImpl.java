package com.egeo.components.product.manage.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.egeo.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitCombinationSuWriteManage;
import com.egeo.components.product.dao.read.StandardUnitCombinationSuReadDAO;
import com.egeo.components.product.dao.write.StandardUnitCombinationSuWriteDAO;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class StandardUnitCombinationSuWriteManageImpl implements StandardUnitCombinationSuWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCombinationSuWriteDAO standardUnitCombinationSuWriteDAO;

	@Autowired
	private StandardUnitCombinationSuReadDAO standardUnitCombinationSuReadDAO;

	@Override
	public Long insertStandardUnitCombinationSuWithTx(StandardUnitCombinationSuPO po) {

		int i ;
		try {
				i = standardUnitCombinationSuWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitCombinationSuWithTx(StandardUnitCombinationSuPO po) {
		int i;
		i = standardUnitCombinationSuWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitCombinationSuWithTx(StandardUnitCombinationSuPO po) {
		int i;
		i = standardUnitCombinationSuWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 批量保存su组合和su商品关系
	 * @param standardUnitCombinationId
	 * @param standardUnits
	 * @return
	 */
	@Override
	public boolean saveStandardUnitCombinationSuAllWithTx(Long standardUnitCombinationId, List<Long> standardUnits,Integer source,List<Map<String,String>> productAndSkuMap) {
		boolean isTrue = false;
		int sortValue = 0;
		/**
		 * 根据su组合id查询su商品排序最大值
		 */
		StandardUnitCombinationSuPO standardUnitCombinationSuPO = new StandardUnitCombinationSuPO();
		standardUnitCombinationSuPO.setStandardUnitCombinationId(standardUnitCombinationId);
		List<StandardUnitCombinationSuPO> standardUnitCombinationSuList = standardUnitCombinationSuReadDAO.findAll(standardUnitCombinationSuPO,null);
		if(EmptyUtil.isNotEmpty(standardUnitCombinationSuList)){
			sortValue = standardUnitCombinationSuReadDAO.findStandardUnitNumberMax(standardUnitCombinationId);
		}
		if(source !=null && (source.intValue() ==4 || source.intValue()==5)){
			if(EmptyUtil.isEmpty(productAndSkuMap)){
				return isTrue;
			}
			for (Map<String, String> stringStringMap : productAndSkuMap) {
				String productId = stringStringMap.get("productId");
				String skuId = stringStringMap.get("thirdSkuId");
				StandardUnitCombinationSuPO standardUnitCombinationSu = new StandardUnitCombinationSuPO();
				standardUnitCombinationSu.setStandardUnitCombinationId(standardUnitCombinationId);
				standardUnitCombinationSu.setStandardUnitId(Long.valueOf(skuId));
				standardUnitCombinationSu.setThirdSkuId(productId);
				List<StandardUnitCombinationSuPO> findExist = standardUnitCombinationSuReadDAO.findAll(standardUnitCombinationSu,null);
				sortValue = sortValue + 1;
				standardUnitCombinationSu.setSortValue(sortValue);
				standardUnitCombinationSu.setSource(source);
				if(findExist==null || findExist.size()==0) {
					standardUnitCombinationSuWriteDAO.insert(standardUnitCombinationSu);
					isTrue = true;
				}
			}

		}else{
			for (Long standardUnitId : standardUnits) {
				StandardUnitCombinationSuPO standardUnitCombinationSu = new StandardUnitCombinationSuPO();
				standardUnitCombinationSu.setStandardUnitCombinationId(standardUnitCombinationId);
				standardUnitCombinationSu.setStandardUnitId(standardUnitId);

				List<StandardUnitCombinationSuPO> findExist = standardUnitCombinationSuReadDAO.findAll(standardUnitCombinationSu,null);
				sortValue = sortValue + 1;
				standardUnitCombinationSu.setSortValue(sortValue);
				if(source!=null) {
					standardUnitCombinationSu.setSource(source);
				}

				if(findExist==null || findExist.size()==0) {
					standardUnitCombinationSuWriteDAO.insert(standardUnitCombinationSu);
					isTrue = true;
				}
			}
		}

		return isTrue;
	}
	/**
	 * 根据su组合与su关系id修改排序
	 * @param standardUnitCombinationId
	 * @param sortValue
	 * @return
	 */
	@Override
	public boolean sortValueByStandardUnitCombinationIdWithTx(Long standardUnitCombinationSuId, Integer sortValue) {
		StandardUnitCombinationSuPO standardUnitCombinationSuPO = new StandardUnitCombinationSuPO();
		standardUnitCombinationSuPO.setId(standardUnitCombinationSuId);
		standardUnitCombinationSuPO.setSortValue(sortValue);
		int i = standardUnitCombinationSuWriteDAO.update(standardUnitCombinationSuPO);
		if(i != 0){
			return true;
		}
		return false;
	}
}
