package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.components.product.po.ProductUnitPO;


public interface MerchantProductWriteManage {
	/**
	 * 新增su草稿信息
	 * @param po su信息
	 * @param picture 列表图片
	 * @param pictures 轮播图片集合
	 * @param webBannerPictures web轮播图集合
	 * @param sellPlatformMerchantProdVOList su商品平台关系（此功能去除）
	 * @param clientList 客户端id集合
	 * @param content su商品详情
	 * @param productUnitList pu集合
	 * @param tagList 标签id集合
	 * @param keywords 关键词集合
	 * @param companys 正式公司集合
	 * @param demoCompanys 演示公司
	 * @param competingCompanys 竞品公司集合
	 * @return
	 */
	Long insertMerchantProductWithTx(
			MerchantProductPO po,
			String picture, 
			List<String> pictures,
			List<String> webBannerPictures,
			List<Long> clients, 
			String content, 
			List<ProductUnitPO> productUnitList,
			List<Long> tagList,
			List<String> keywords,
			List<Long> companys,
			List<Long> demoCompanys,
			List<Long> competingCompanys,
			List<Long> storeIds,
			List<Long> membershipIds);
	/**
	 * 根据su商品草稿id更新su商品草稿信息
	 * @param po su草稿信息
	 * @param keywords 关键词集合
	 * @param companys 正式公司集合
	 * @param demoCompanys 演示公司
	 * @param competingCompanys 竞品公司集合
	 * @return
	 */
	int updateMerchantProductWithTx(
			MerchantProductPO po,
			List<String> keywords,
			List<Long> clients, 
			List<Long> tagList,
			List<Long> companys,
			List<Long> demoCompanys,
			List<Long> competingCompanys,
			List<String> webBannerPictures,
			List<Long> storeIdList,
			List<Long> membershipIdList);

	int deleteMerchantProductWithTx(MerchantProductPO po);
	/**
	 * 提交审核
	 * @param dto
	 * @return
	 */
	int submitAuditWithTx(MerchantProductPO po);
	/**
	 * 修改su草稿状态为已上架
	 * @param merchantProductDTO
	 * @return
	 */
	int updateStatus(MerchantProductPO po);
	/**
	 * 根据id通过
	 * @param vo
	 * @param req
	 * @return
	 */
	int merchantProductPassWithTx(MerchantProductPO merchantProductPO);
	/**
	 * 根据su商品id通过审核
	 * @param merchantProductId
	 * @return
	 */
	List<Long> merchantProductPassWithTx(Long merchantProductId);
	/**
	 * 根据su草稿id更新su草稿图片信息
	 * @param merchantProductId suId
	 * @param picture su草稿封面图片
	 * @param pictures su草稿轮播图
	 * @param webBannerPictures suWeb端轮播图
	 * @param po su商品信息
	 * @return
	 */
	int updateMerchantProductPictureByIdWithTx(Long merchantProductId, String picture, List<String> pictures,
			List<String> webBannerPictures, MerchantProductPO po);

    void saveMerchantProduct(List<MerchantProductPO> merchantProductPOList);

    void updateMerchantProductPrice(List<MerchantProductPO> merchantProductPricePOList);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);

    int updateMerchantProductVisible(Long standardUnitId, Integer status);

    void updateMerchantProductList(List<MerchantProductPO> merchantProductPOList);

    void updateSuAndPuStatusByJd();

    void updateJdProductStatusByProfit(Integer productLimitProfit);

}
	