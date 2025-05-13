package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.CommodityProductUnitPO;


public interface CommodityProductUnitWriteManage {

	Long insertCommodityProductUnitWithTx(CommodityProductUnitPO po);

	int updateCommodityProductUnitWithTx(CommodityProductUnitPO po);

	int deleteCommodityProductUnitWithTx(CommodityProductUnitPO po);
	/**
	 * 根据pu草稿id修改pu信息
	 * @param commodityProductUnitDTO
	 * @return
	 */
	int updateCommodityProductUnitByProductUnitIdWithTx(CommodityProductUnitPO po);
	/**
	 * 根据suid修改PU状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updateProductUnitStatusByStandardUnitIdWithTx(Long id, Integer status);
	/**
	 * 根据skuid批量下架pu
	 * @param skuId
	 * @return
	 */
	int updateStatusBySkuId(Long skuId);
	/**
	 * 根据skuid同步失效sku下面的所有pu
	 * @param id
	 * @return
	 */
	int updatePUIsVendibleBySkuIdWithTx(Long id);
	/**
	 * 批量同步pu数据
	 * @param commodityProductUnitPOs
	 * @return
	 */
	int insertCommodityProductUnitAllWithTx(List<CommodityProductUnitPO> commodityProductUnitPOs);

    void saveCommodityProductUnit(List<CommodityProductUnitPO> commodityProductUnitPOList);

    void updateCommodityProductUnitPrice(List<CommodityProductUnitPO> commodityProductUnitPricePOList);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);

    void updateCommodityProductUnitList(List<CommodityProductUnitPO> commodityProductUnitPOList);
}
	