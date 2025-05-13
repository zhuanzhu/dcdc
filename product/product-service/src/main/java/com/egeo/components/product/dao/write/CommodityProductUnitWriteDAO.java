package com.egeo.components.product.dao.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.orm.BaseWriteDAO;

public interface CommodityProductUnitWriteDAO extends BaseWriteDAO<CommodityProductUnitPO> {
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
	int updateProductUnitStatusByStandardUnitIdWithTx(@Param("standardUnitId")Long standardUnitId, @Param("status")Integer status);
	/**
	 * 根据skuid批量下架pu
	 * @param skuId
	 * @return
	 */
	int updateStatusBySkuId(@Param("skuId")Long skuId);
	/**
	 * 根据skuid同步失效sku下面的所有pu
	 * @param id
	 * @return
	 */
	int updatePUIsVendibleBySkuIdWithTx(@Param("skuId")Long skuId);
	/**
	 * 批量同步pu数据
	 * @param commodityProductUnitPOs
	 * @return
	 */
	int insertCommodityProductUnitAllWithTx(@Param("poList")List<CommodityProductUnitPO> commodityProductUnitPOs);

    void saveCommodityProductUnit(@Param("poList")List<CommodityProductUnitPO> commodityProductUnitPOList);

    void updateCommodityProductUnitPrice(@Param("poList")List<CommodityProductUnitPO> commodityProductUnitPricePOList);

    void updateJdProductPriceByRate(@Param("competingCompanyRate")Integer competingCompanyRate,
									@Param("democompanysCompanyRate")Integer democompanysCompanyRate,
									@Param("standardCompanyRate")Integer standardCompanyRate);

    void updateCommodityProductUnitList(@Param("poList")List<CommodityProductUnitPO> commodityProductUnitPOList);
}
	