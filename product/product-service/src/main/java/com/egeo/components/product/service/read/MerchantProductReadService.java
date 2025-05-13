package com.egeo.components.product.service.read;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.egeo.components.product.condition.MerchantProductUnitCondition;
import com.egeo.components.product.dto.CommodityDetailsDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface MerchantProductReadService {

	public MerchantProductDTO findMerchantProductById(MerchantProductDTO dto);

	public PageResult<MerchantProductDTO> findMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date starTime, Date endTime, MerchantProductDTO dto, Pagination page, List<String> nameList);
	public PageResult<MerchantProductDTO> findPlatformMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, List<Long> storeIds, Date starTime, Date endTime, MerchantProductDTO dto, Pagination page, List<String> nameList);

	public List<MerchantProductDTO> findMerchantProductAll(MerchantProductDTO dto);
	/**
	 * 根据su草稿id查询基本信息（app预览）
	 * @param merchantProductId
	 * @return
	 */
	public MerchantProductDTO findMerchantProductAppById(Long merchantProductId);

	/**
	 * 标签-商品草稿详情展示
	 * @param dto
	 * @param page
	 * @return
	 */
    PageResult<MerchantProductDTO> findCommodityDetailsOfPage(CommodityDetailsDTO dto, Pagination page);

    Long findLastId();

	public long findMaxfrontSerialNumber();

	List<MerchantProductUnitCondition> exportMerchantProduct(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit,
															 List<Long> storeIds, Date starTime, Date endTime,
															 MerchantProductDTO dto,
															 List<String> nameList);

}
	