package com.egeo.components.product.dao.read;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.egeo.components.product.condition.MerchantProductUnitCondition;
import com.egeo.components.product.po.CommodityDetailsPO;
import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.MerchantProductCondition;
import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface MerchantProductReadDAO extends BaseReadDAO<MerchantProductPO>{

	List<MerchantProductCondition> findMerchantProductConditionOfPage(@Param("priceStart")BigDecimal priceStart, @Param("priceEnd")BigDecimal priceEnd, @Param("startProfit") Integer startProfit, @Param("endProfit") Integer endProfit, @Param("storeIds") List<Long> storeIds,
                                                                      @Param("startTime") Date starTime, @Param("endTime") Date endTime,@Param("enterpriseId") Long enterpriseId,
                                                                      @Param("po") MerchantProductPO po, @Param("page") Pagination page, @Param("nameList") List<String> nameList, @Param("supplierId") Long supplierId);

	List<MerchantProductCondition> findPlatformMerchantProductConditionOfPage(@Param("priceStart")BigDecimal priceStart, @Param("priceEnd")BigDecimal priceEnd, @Param("startProfit") Integer startProfit, @Param("endProfit") Integer endProfit, @Param("storeIds") List<Long> storeIds,
                                                                      @Param("startTime") Date starTime, @Param("endTime") Date endTime,
                                                                      @Param("po") MerchantProductPO po, @Param("page") Pagination page, @Param("nameList") List<String> nameList, @Param("supplierId") Long supplierId);
	MerchantProductCondition findMerchantProductById(@Param("po")MerchantProductPO merchantProductpo);
	/**
	 * 根据su草稿id查询基本信息（app预览）
	 * @param merchantProductId
	 * @return
	 */
	MerchantProductCondition findMerchantProductAppById(@Param("merchantProductId")Long merchantProductId);

    int countCdOfPage(@Param("po")CommodityDetailsPO po);

	List<MerchantProductCondition> findCommodityDetailsConditionOfPage(@Param("po")CommodityDetailsPO po,@Param("page") Pagination page);

    int countOfPages(@Param("priceStart")BigDecimal priceStart,@Param("priceEnd") BigDecimal priceEnd, @Param("startProfit") Integer startProfit, @Param("endProfit") Integer endProfit, @Param("storeIds") List<Long> storeIds, @Param("startTime") Date starTime, @Param("endTime") Date endTime,@Param("enterpriseId") Long enterpriseId, @Param("po") MerchantProductPO po, @Param("nameList") List<String> nameList, @Param("supplierId") Long supplierId);
    int countOfPlatformPages(@Param("priceStart")BigDecimal priceStart,@Param("priceEnd") BigDecimal priceEnd, @Param("startProfit") Integer startProfit, @Param("endProfit") Integer endProfit, @Param("storeIds") List<Long> storeIds, @Param("startTime") Date starTime, @Param("endTime") Date endTime, @Param("po") MerchantProductPO po, @Param("nameList") List<String> nameList, @Param("supplierId") Long supplierId);

    Long findLastId();
    
    
    long findMaxfrontSerialNumber();


	List<MerchantProductUnitCondition> exportMerchantProductConditionOfPage(@Param("priceStart")BigDecimal priceStart, @Param("priceEnd")BigDecimal priceEnd, @Param("startProfit") Integer startProfit, @Param("endProfit") Integer endProfit, @Param("storeIds") List<Long> storeIds,
																	  @Param("startTime") Date starTime, @Param("endTime") Date endTime,@Param("enterpriseId") Long enterpriseId,
																	  @Param("po") MerchantProductPO po, @Param("page") Pagination page, @Param("nameList") List<String> nameList, @Param("supplierId") Long supplierId);

}
	