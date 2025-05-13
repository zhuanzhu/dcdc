package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CommodityProductUnitWriteManage;
import com.egeo.components.product.dao.write.CommodityProductUnitWriteDAO;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.exception.BusinessException;

@Service
public class CommodityProductUnitWriteManageImpl implements CommodityProductUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitWriteDAO commodityProductUnitWriteDAO;

	@Override
	public Long insertCommodityProductUnitWithTx(CommodityProductUnitPO po) {
		
		int i ;
		try {
				i = commodityProductUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCommodityProductUnitWithTx(CommodityProductUnitPO po) {
		int i;
		i = commodityProductUnitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCommodityProductUnitWithTx(CommodityProductUnitPO po) {
		int i;
		i = commodityProductUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据pu草稿id修改pu信息
	 * @param commodityProductUnitDTO
	 * @return
	 */
	@Override
	public int updateCommodityProductUnitByProductUnitIdWithTx(CommodityProductUnitPO po) {
		// TODO Auto-generated method stub
		return commodityProductUnitWriteDAO.updateCommodityProductUnitByProductUnitIdWithTx(po);
	}
	/**
	 * 根据suid修改PU状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public int updateProductUnitStatusByStandardUnitIdWithTx(Long standardUnitId, Integer status) {
		// TODO Auto-generated method stub
		return commodityProductUnitWriteDAO.updateProductUnitStatusByStandardUnitIdWithTx(standardUnitId, status);
	}
	/**
	 * 根据skuid批量下架pu
	 * @param skuId
	 * @return
	 */
	@Override
	public int updateStatusBySkuId(Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitWriteDAO.updateStatusBySkuId(skuId);
	}
	/**
	 * 根据skuid同步失效sku下面的所有pu
	 * @param id
	 * @return
	 */
	@Override
	public int updatePUIsVendibleBySkuIdWithTx(Long skuId) {
		// TODO Auto-generated method stub
		return commodityProductUnitWriteDAO.updatePUIsVendibleBySkuIdWithTx(skuId);
	}
	/**
	 * 批量同步pu数据
	 * @param commodityProductUnitPOs
	 * @return
	 */
	@Override
	public int insertCommodityProductUnitAllWithTx(List<CommodityProductUnitPO> commodityProductUnitPOs) {
		// TODO Auto-generated method stub
		return commodityProductUnitWriteDAO.insertCommodityProductUnitAllWithTx(commodityProductUnitPOs);
	}

	@Override
	public void saveCommodityProductUnit(List<CommodityProductUnitPO> commodityProductUnitPOList) {
		try{
		commodityProductUnitWriteDAO.saveCommodityProductUnit(commodityProductUnitPOList);
		}catch (Exception e){
		logger.error("CommodityProductUnit保存失败"+e.getMessage());
		}
	}

	@Override
	public void updateCommodityProductUnitPrice(List<CommodityProductUnitPO> commodityProductUnitPricePOList) {
		commodityProductUnitWriteDAO.updateCommodityProductUnitPrice(commodityProductUnitPricePOList);
	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		commodityProductUnitWriteDAO.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
	}

	@Override
	public void updateCommodityProductUnitList(List<CommodityProductUnitPO> commodityProductUnitPOList) {
		try{
			commodityProductUnitWriteDAO.updateCommodityProductUnitList(commodityProductUnitPOList);
		}catch (Exception e){
			logger.error("updateCommodityProductUnitList保存失败"+e.getMessage());
		}
	}
}
	