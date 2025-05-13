package com.egeo.components.product.manage.read;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.egeo.components.product.condition.MerchantProductCondition;
import com.egeo.components.product.condition.MerchantProductUnitCondition;
import com.egeo.components.product.po.CommodityDetailsPO;
import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductReadManage {

	public MerchantProductCondition findMerchantProductById(MerchantProductPO po);

	public PageResult<MerchantProductCondition> findMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date starTime, Date endTime, MerchantProductPO po, Pagination page, List<String> nameList);
	public PageResult<MerchantProductCondition> findPlatformMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date starTime, Date endTime, MerchantProductPO po, Pagination page, List<String> nameList);

	public List<MerchantProductPO> findMerchantProductAll(MerchantProductPO po);
	/**
	 * 根据su草稿id查询基本信息（app预览）
	 * @param merchantProductId
	 * @return
	 */
	public MerchantProductCondition findMerchantProductAppById(Long merchantProductId);

	/**
	 * 标签页显示商品列表
	 * @param po
	 * @param page
	 * @return
	 */
    PageResult<MerchantProductCondition> findCommodityDetailsOfPage(CommodityDetailsPO po, Pagination page);

    Long findLastId();
    long findMaxfrontSerialNumber();

	PageResult<MerchantProductUnitCondition> exportMerchantProduct(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit,
																   List<Long> storeIds, Date starTime, Date endTime,
																   MerchantProductPO po, Pagination page,
																   List<String> nameList);
}
	