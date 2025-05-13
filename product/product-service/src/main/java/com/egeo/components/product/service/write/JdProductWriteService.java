package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.JdProductDTO;

import java.math.BigDecimal;
import java.util.List;


public interface JdProductWriteService {

	public Long insertJdProductWithTx(JdProductDTO dto);

	public int updateJdProductWithTx(JdProductDTO dto);

	public int deleteJdProductWithTx(JdProductDTO dto);
	
	void updateSyncStatus(JdProductDTO dto);

    void saveJdProductListFirst(List<JdProductDTO> jdProductDTOList) ;

    void setAllSyncStatus(int status);

    void updateJdProductList(List<JdProductDTO> updateList);

    void updateJdProductPrice(List<Integer> profitList, List<Long> skuIdList, List<BigDecimal> priceList, List<BigDecimal> marketPrices);

    void updateProductCreateTime(List<Long> jdId);



	/*void saveProductList(List<Long> productIdList, List<String> spuSerialNumberList, List<String> productCategoryList, List<Long> catgoryTreeNodeIdList, List<String> nameList);

	void savePicture(List<Long> pictureIdList, List<String> pictureUrlList);

	void saveAttValue(List<Long> attValueIdList, List<String> attValueList);

	void saveProductAttName(List<Long> productAttNameIdList, List<Long> productIdList);

	void saveProductAttValue(List<Long> productAttNameIdList, List<Long> attValueIdList);

	void saveProductDescription(List<Long> productIdList);

	void saveProductPicture(List<Long> productIdList, List<Long> pictureIdList);

	void saveSPU(List<Long> spuIdList, List<String> spuSerialNumberList, List<String> productCategoryList, List<Long> catgoryTreeNodeIdList, List<String> nameList);

	void saveStandardProductUnitAttName(List<Long> spuIdList, List<Long> spuAttNameIdList);

	void saveSPUValue(List<Long> spuAttNameIdList, List<Long> attValueIdList);

	void saveStandardProductUnitDescription(List<Long> spuIdList);

	void saveStandardProductUnitPicture(List<Long> spuIdList, List<Long> pictureIdList);

	void saveSku(List<Long> skuIdList, List<Long> spuIdList, List<String> skuSerialNumberList, List<String> nameList, List<String> jdSkuIdList);

	void saveSkuAttValuePO(List<Long> skuAttNameIdList, List<Long> attValueIdList);

	void saveSkuAttName(List<Long> skuAttNameIdList, List<Long> skuIdList);

	void saveMerchantProduct(List<Long> merchantProductIdList, List<Long> spuIdList, List<String> merchantProductSerialNumberList, List<String> productCategoryList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> marketPriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

	void saveMerchantProdClient(List<Long> merchantProductIdList);

	void saveMerchantProdDescribe(List<Long> merchantProductIdList);

	void saveMerchantProdPicture(List<Long> merchantProductIdList, List<Long> merchantPictureIdList);

	void saveMerchantPicture(List<Long> merchantPictureIdList, List<Long> pictureIdList);

	void saveMerchantProductCompany(List<Long> merchantProductIdList);

	void saveMerchantProductStore(List<Long> merchantProductIdList);

	void saveStandardUnit(List<Long> suIdList, List<Long> spuIdList, List<String> merchantProductSerialNumberList, List<String> productCategoryList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> marketPriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

	void saveStandardUnitDescribe(List<Long> suIdList);

	void saveStandardUnitPicture(List<Long> suIdList, List<Long> merchantPictureIdList);

	void saveStandardUnitStore(List<Long> suIdList);

	void saveStandardUnitCompany(List<Long> suIdList);

	void saveStandardUnitClient(List<Long> suIdList);

	void saveProductUnit(List<Long> productUnitIdList, List<String> productUnitSerialNumberList, List<Long> skuIdList, List<Long> merchantProductIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

	void saveCommodityProductUnit(List<Long> puIdList, List<String> productUnitSerialNumberList, List<Long> productUnitIdList, List<Long> skuIdList, List<Long> suIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);
	*/
}
	