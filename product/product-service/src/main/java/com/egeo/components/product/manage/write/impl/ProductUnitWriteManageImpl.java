package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.ProductUnitWriteManage;
import com.egeo.components.product.dao.write.ProductUnitWriteDAO;
import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class ProductUnitWriteManageImpl implements ProductUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProductUnitWriteDAO productUnitWriteDAO;

	@Override
	public Long insertProductUnitWithTx(ProductUnitPO po) {
		
		int i ;
		try {
				i = productUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateProductUnitWithTx(ProductUnitPO po) {
		int i;
		i = productUnitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteProductUnitWithTx(ProductUnitPO po) {
		int i;
		i = productUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据suid修改草稿PU状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public int updateProductUnitStatusByStandardUnitIdWithTx(Long standardUnitId, Integer status) {
		return productUnitWriteDAO.updateProductUnitStatusByStandardUnitIdWithTx(standardUnitId, status);
	}
	/**
	 * 根据skuid同步下架pu草稿信息
	 * @param skuId
	 * @return
	 */
	@Override
	public int updateStatusBySkuId(Long skuId) {
		// TODO Auto-generated method stub
		return productUnitWriteDAO.updateStatusBySkuId(skuId);
	}
	/**
	 * 根据skuid同步失效sku下面的所有草稿pu
	 * @param id
	 * @return
	 */
	@Override
	public int updatePUIsVendibleBySkuIdWithTx(Long skuId) {
		// TODO Auto-generated method stub
		return productUnitWriteDAO.updatePUIsVendibleBySkuIdWithTx(skuId);
	}

	@Override
	public void saveProductUnit(List<ProductUnitPO> productUnitPOList) {
		try {
			productUnitWriteDAO.saveProductUnit(productUnitPOList);
		}catch (Exception e){
			logger.error("productUnit保存失败"+e.getMessage());

		}
	}

	@Override
	public void updateProductUnitPrice(List<ProductUnitPO> productUnitPricePOList) {
		productUnitWriteDAO.updateProductUnitPrice(productUnitPricePOList);
	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		productUnitWriteDAO.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
	}

	@Override
	public void updateProductUnitList(List<ProductUnitPO> productUnitPOList) {
		try {
			productUnitWriteDAO.updateProductUnitList(productUnitPOList);
		}catch (Exception e){
			logger.error("updateProductUnitList保存失败"+e.getMessage());

		}
	}
}
	