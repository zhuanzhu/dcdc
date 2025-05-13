package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CommodityProductUnitWriteManage;
import com.egeo.components.product.manage.write.ProductUnitWriteManage;
import com.egeo.components.product.manage.write.StandardUnitWriteManage;
import com.egeo.components.product.dao.write.MerchantProductWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitWriteDAO;
import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class StandardUnitWriteManageImpl implements StandardUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitWriteDAO standardUnitWriteDAO;
	
	@Autowired
	private CommodityProductUnitWriteManage commodityProductUnitWriteManage;
	
	@Autowired
	private ProductUnitWriteManage productUnitWriteManage;
	
	@Autowired
	private MerchantProductWriteDAO merchantProductWriteDAO;

	@Override
	public Long insertStandardUnitWithTx(StandardUnitPO po) {
		
		int i ;
		try {
				i = standardUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitWithTx(StandardUnitPO po) {
		int i;
		i = standardUnitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitWithTx(StandardUnitPO po) {
		int i;
		i = standardUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * su上下架
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int putawaySoldOutWithTx(StandardUnitPO po,int type) {
		int i = standardUnitWriteDAO.update(po);
		
		if(i != 0){
			MerchantProductPO merchantProductPO = new MerchantProductPO();
			merchantProductPO.setId(po.getId());
			merchantProductPO.setStatus(po.getStatus());
			merchantProductWriteDAO.update(merchantProductPO);
			//是否同步上/下架该SU下的所有PU 0否1是
			if(type == 1){
				//根据suid修改PU状态
				commodityProductUnitWriteManage.updateProductUnitStatusByStandardUnitIdWithTx(po.getId(),po.getStatus());
				//根据suid修改草稿PU状态
				productUnitWriteManage.updateProductUnitStatusByStandardUnitIdWithTx(po.getId(),po.getStatus());
			}
		}
		return i;
	}

	@Override
	public void saveStandardUnit(List<StandardUnitPO> standardUnitPOList) {
		try{
		standardUnitWriteDAO.saveStandardUnit(standardUnitPOList);
		}catch (Exception e){
			logger.error("StandardUnit保存失败"+e.getMessage());

		}

	}

	@Override
	public void updateStandardUnitPrice(List<StandardUnitPO> standardUnitPricePOList) {
		standardUnitWriteDAO.updateStandardUnitPrice(standardUnitPricePOList);
	}
	@Override
	public void updateStandardUnitPriceList(List<StandardUnitPO> standardUnitPricePOList) {
		standardUnitWriteDAO.updateStandardUnitPriceList(standardUnitPricePOList);
	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		standardUnitWriteDAO.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
	}

	@Override
	public void updateSUList(List<StandardUnitPO> standardUnitPOList) {
		try{
			standardUnitWriteDAO.updateSUList(standardUnitPOList);
		}catch (Exception e){
			logger.error("updateSUList保存失败"+e.getMessage());

		}
	}

	@Override
	public void updateSuAndPuStatusByJd() {
		standardUnitWriteDAO.updateSuAndPuStatusByJd();
	}

	@Override
	public void updateJdProductStatusByProfit(Integer productLimitProfit) {
		standardUnitWriteDAO.updateJdProductStatusByProfit(productLimitProfit);
	}
}
	