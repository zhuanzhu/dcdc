package com.egeo.components.product.service.write;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;


public interface MerchantProductWriteService {
	/**
	 * 新增su草稿信息
	 * @param dto su信息
	 * @param picture 列表图片
	 * @param pictureList 轮播图片集合
	 * @param webBannerPictureList web轮播图图片地址集合
	 * @param sellPlatformMerchantProdVOList su商品平台关系（此功能去除）
	 * @param clientList 客户端id集合
	 * @param content su商品详情
	 * @param productUnitList pu集合
	 * @param tagList 标签id集合
	 * @param keywords 关键词集合
	 * @param companys 正式公司集合
	 * @param demoCompanys 演示公司集合
	 * @param competingCompanys 竞品公司集合
	 * @return
	 */
	public Long insertMerchantProductWithTx(
			MerchantProductDTO dto,
			String picture,
			String pictureList,
			String webBannerPictureList,
			List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList,
			String clientList,
			String content,
			List<ProductUnitDTO> productUnitList,
			List<Long> tagList,
			List<String> keywords,
			List<Long> companys,
			List<Long> demoCompanys,
			List<Long> competingCompanys,
			List<Long> storeIds,
			List<Long> membershipIds);
	/**
	 * 根据su商品草稿id更新su商品草稿信息
	 * @param dto su商品信息
	 * @param sellPlatformMerchantProdList su商品平台关系（此功能去除）
	 * @param clientList 客户端id集合
	 * @param content su商品详情
	 * @param tagList 标签id集合
	 * @param keywords 关键词id集合
	 * @param companys 正式公司集合
	 * @param demoCompanys 演示公司集合
	 * @param competingCompanys 竞品公司集合
	 * @param req
	 * @return
	 */
	public int updateMerchantProductWithTx(
			MerchantProductDTO dto,
			List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList,
			String clientList,
			List<Long> tagList,
			List<String> keywords,
			List<Long> companys,
			List<Long> demoCompanys,
			List<Long> competingCompanys,
			String webBannerPictureList,
			List<Long> storeIdList,
			List<Long> membershipIdList);

	public int deleteMerchantProductWithTx(MerchantProductDTO dto);
	/**
	 * 提交审核
	 * @param dto
	 * @return
	 */
	public int submitAuditWithTx(MerchantProductDTO dto);
	/**
	 * 修改su草稿状态为已上架
	 * @param merchantProductDTO
	 * @return
	 */
	public int updateStatusWithTx(MerchantProductDTO merchantProductDTO);
	/**
	 * 根据su草稿id更新su草稿图片信息
	 * @param merchantProductId suId
	 * @param picture su草稿封面图片
	 * @param pictures suapp轮播图
	 * @param webBannerPictures su草稿商品web端轮播图
	 * @param merchantProductDTO su草稿信息
	 * @return
	 */
	public int updateMerchantProductPictureByIdWithTx(
			Long merchantProductId, 
			String picture,
			List<String> pictures,
			List<String> webBannerPictures,
			MerchantProductDTO merchantProductDTO);
	/**
	 * 根据su草稿id更新supu信息
	 * @param merchantProductId
	 * @param picture
	 * @param pictureList
	 * @return
	 */
	public int updateProductUnitByIdWithTx(List<ProductUnitDTO> dto,MerchantProductDTO merchantProductDTO,
										   List<Long> companyIds,List<Long> demoCompanyIds,List<Long> competingCompanyIds);
	/**
	 * 根据id通过
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Long> merchantProductPassWithTx(Long merchantProductId,Long platformId);

    void saveMerchantProduct(List<Integer> profitList, List<Long> merchantProductIdList, List<Long> spuIdList, List<String> merchantProductSerialNumberList, List<String> productCategoryList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> marketPriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

    void updateMerchantProductPrice(List<Integer> profits, List<Integer> statusList, List<Integer> isVisibleList, List<Long> merchantProductIdList, List<BigDecimal> salePriceList, List<BigDecimal> demoPriceList, List<BigDecimal> competingPriceList, List<BigDecimal> marketPriceList);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);

    int updateMerchantProductVisible(Long standardUnitId, Integer status);

    void updateMerchantProductList(List<Integer> profitList, List<Long> merchantProductIdList, List<String> nameList, List<BigDecimal> marketPriceList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

    void updateSuAndPuStatusByJd();

    void updateJdProductStatusByProfit(Integer productLimitProfit);

}
	