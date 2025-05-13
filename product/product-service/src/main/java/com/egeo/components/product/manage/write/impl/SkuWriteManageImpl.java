package com.egeo.components.product.manage.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SkuWriteManage;
import com.egeo.components.product.dao.read.ProductUnitReadDAO;
import com.egeo.components.product.dao.read.SkuReadDAO;
import com.egeo.components.product.dao.read.StandardUnitReadDAO;
import com.egeo.components.product.dao.read.StoreTreeNodeReadDAO;
import com.egeo.components.product.dao.write.CommodityProductUnitWriteDAO;
import com.egeo.components.product.dao.write.ProductUnitWriteDAO;
import com.egeo.components.product.dao.write.SkuWriteDAO;
import com.egeo.components.product.dao.write.StoreProductUnitWriteDAO;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.components.product.po.SkuPO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.components.product.po.StoreProductUnitPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class SkuWriteManageImpl implements SkuWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SkuWriteDAO skuWriteDAO;
	
	@Autowired
	private SkuReadDAO skuReadDAO;
	
	@Autowired
	private ProductUnitReadDAO productUnitReadDAO;
	
	@Autowired
	private ProductUnitWriteDAO productUnitWriteDAO;
	
	@Autowired
	private CommodityProductUnitWriteDAO commodityProductUnitWriteDAO;
	
	@Autowired
	private StandardUnitReadDAO standardUnitReadDAO;
	
	@Autowired
	private StoreTreeNodeReadDAO storeTreeNodeReadDAO;
	
	@Autowired
	private StoreProductUnitWriteDAO storeProductUnitWriteDAO;
	
	@Override
	public Long insertSkuWithTx(SkuPO po) {
		
		int i ;
		try {
				i = skuWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSkuWithTx(SkuPO po) {
		int i;
		i = skuWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSkuWithTx(SkuPO po) {
		int i;
		i = skuWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据spuid更新supu信息
	 * @param standardProductUnitId
	 * @param id
	 */
	@Override
	public List<CommodityProductUnitPO> updateSuPuWithTx(Long standardProductUnitId, Long merchantProductId) {
		List<Long> puIds = new ArrayList<>();
		List<CommodityProductUnitPO> pos = new ArrayList<>();
		//根据su草稿id查询pu信息
		ProductUnitPO productUnit = new ProductUnitPO();
		productUnit.setMerchantProductId(merchantProductId);
		List<ProductUnitPO> productUnitList = productUnitReadDAO.findAll(productUnit,null);
		List<Long> skuIds = new ArrayList<>();
		for (ProductUnitPO pu : productUnitList) {
			skuIds.add(pu.getSkuId());
		}
		
		//根据su草稿id查询su信息
		StandardUnitPO standardUnitPO = new StandardUnitPO();
		standardUnitPO.setId(merchantProductId);
		StandardUnitPO standardUnitPO2 = standardUnitReadDAO.findById(standardUnitPO);
		//根据spuid查询sku信息
		SkuPO skuPO = new SkuPO();
		skuPO.setStandardProductUnitId(standardProductUnitId);
		List<SkuPO> skuList = skuReadDAO.findAll(skuPO,null);
		for (SkuPO skuPO2 : skuList) {
			//如果当前的supu商品sku集合不包含那么新增草稿pu和正式pu
			if(!skuIds.contains(skuPO2.getId())){
				ProductUnitPO productUnitPO = new ProductUnitPO();
				//查询pu序列号
				String productUnitSerialNumber = skuReadDAO.skuSerialNumberBySkuId(skuPO2.getId());
				productUnitPO.setProductUnitSerialNumber(productUnitSerialNumber);
				productUnitPO.setSkuId(skuPO2.getId());
				productUnitPO.setMerchantProductId(merchantProductId);
				productUnitPO.setName(skuPO2.getSkuName());
				productUnitPO.setRemark(skuPO2.getRemark());
				//默认待上架
				productUnitPO.setStatus(1);
				productUnitPO.setCode(skuPO2.getCode());
				productUnitPO.setPuPicUrl(skuPO2.getSkuPicUrl());
				productUnitPO.setPlatformId(skuPO2.getPlatformId());
				productUnitWriteDAO.insert(productUnitPO);
				
				//如果su不为空则新增pu信息及返回puid新增库存信息
				if(EmptyUtil.isNotEmpty(standardUnitPO2)){
					//同步pu信息
					CommodityProductUnitPO commodityProductUnit = new CommodityProductUnitPO();
					//查询pu序列号
					commodityProductUnit.setProductUnitSerialNumber(productUnitSerialNumber);
					commodityProductUnit.setProductUnitId(productUnitPO.getId());
					commodityProductUnit.setSkuId(skuPO2.getId());
					commodityProductUnit.setStandardUnitId(merchantProductId);
					commodityProductUnit.setName(skuPO2.getSkuName());
					commodityProductUnit.setRemark(skuPO2.getRemark());
					//默认待上架
					commodityProductUnit.setStatus(1);
					commodityProductUnit.setCode(skuPO2.getCode());
					commodityProductUnit.setPuPicUrl(skuPO2.getSkuPicUrl());
					commodityProductUnit.setPlatformId(skuPO2.getPlatformId());
					commodityProductUnitWriteDAO.insert(commodityProductUnit);
					
					puIds.add(commodityProductUnit.getId());
					
					//新建共用库存关联
					pos.add(commodityProductUnit);
					
					List<Long> storeIds = storeTreeNodeReadDAO.findStoreTreeNodeAllByPlatformId(standardUnitPO2.getPlatformId());
					// 门店关系不为空则生成门店pu
					if(EmptyUtil.isNotEmpty(storeIds)){
						List<StoreProductUnitPO> list = new ArrayList<>();
						for (Long storeId : storeIds) {
							for (Long puId : puIds) {
								StoreProductUnitPO storeProductUnitPO = new StoreProductUnitPO();
								storeProductUnitPO.setStoreId(storeId);
								storeProductUnitPO.setStandardUnitId(merchantProductId);
								storeProductUnitPO.setCommodityProductUnitId(puId);
								// 默认上架（本版本废弃，后续版本开发）
								storeProductUnitPO.setStatus(3);
								storeProductUnitPO.setPlatformId(standardUnitPO2.getPlatformId());
								list.add(storeProductUnitPO);
							}
						}
						// 批量生产门店pu
						if(EmptyUtil.isNotEmpty(list))
							storeProductUnitWriteDAO.insertAllWithTx(list);
					}
				}
				
			}
		}
		return pos;
		
		
	}

	@Override
	public int updateSkuParamsWithTx(SkuPO po) {
		return skuWriteDAO.updateSkuParamsWithTx(po);
	}

	@Override
	public void saveSku(List<SkuPO> skuPOList) {
		try{
		skuWriteDAO.saveSku(skuPOList);
		}catch (Exception e){
			logger.error("saveSku失败,e:"+e.getMessage());
		}
	}

	@Override
	public void updateSkuList(List<SkuPO> skuPOList) {
		try{
			skuWriteDAO.updateSkuList(skuPOList);
		}catch (Exception e){
			logger.error("updateSkuList失败,e:"+e.getMessage());
		}
	}
}
	