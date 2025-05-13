package com.egeo.components.product.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.dto.CommodityDetailsDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.vo.MerchantProductVO;
import com.egeo.components.product.vo.ProductUnitVO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface MerchantProductManage {

	public Map<String, Object> findMerchantProductById(MerchantProductDTO dto,CacheUser user,String ip);	

	public PageResult<MerchantProductVO> findMerchantProductOfPage(Integer platformFlag,BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, Date starTime, Date endTime, MerchantProductDTO dto, Pagination page, List<String> nameList);

	public JsonResult<Map<String, Object>> exportMerchantProductOfPage(Integer platformFlag, BigDecimal priceStart,
																	 BigDecimal priceEnd, Integer startProfit,
																	 Integer endProfit, Date starTime, Date endTime,
																	 MerchantProductDTO dto, List<String> nameList);

	public List<MerchantProductDTO> findMerchantProductAll(MerchantProductDTO dto);
	/**
	 * 新增su草稿信息
	 * @param dto su信息
	 * @param picture 列表图片
	 * @param pictureList 轮播图片集合
	 * @param webBannerPictureList web轮播图图片地址
	 * @param sellPlatformMerchantProdVOList su商品平台关系（此功能去除）
	 * @param clientList 客户端id集合
	 * @param companyList 公司id集合
	 * @param content su商品详情
	 * @param productUnitList pu集合
	 * @param tagList 标签id集合
	 * @param keywords 关键词集合
	 * @param demoCompanyList 演示公司集合
	 * @param competingCompanyList 竞品公司集合
	 * @return
	 */
	JsonResult<Long> insertMerchantProductWithTx(MerchantProductDTO dto,
			String picture,
			String pictureList,
			String webBannerPictureList,
			List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList,
			String clientList,
			String companyList,
			String content,
			List<ProductUnitDTO> productUnitList,
			List<Long> tagList,
			List<String> keywords,
			String demoCompanyList,
			String competingCompanyList,
			List<Long> storeIds,
			List<Long> membershipIds);
	/**
	 * 根据su商品草稿id更新su商品草稿信息
	 * @param dto su商品信息
	 * @param sellPlatformMerchantProdList su商品平台关系（此功能去除）
	 * @param clientList 客户端id集合
	 * @param companyList 公司id集合
	 * @param content su商品详情
	 * @param tagList 标签id集合
	 * @param keywords 关键词id集合
	 * @param demoCompanyList 演示公司集合
	 * @param competingCompanyList 竞品公司集合
	 * @param req
	 * @return
	 */
	int updateMerchantProductWithTx(MerchantProductDTO dto,
			List<SellPlatformMerchantProdDTO> sellPlatformMerchantProdVOList,
			String clientList,
			String companyList,
			String content,
			List<Long> tagList,
			List<String> keywords,
			String demoCompanyList,
			String competingCompanyList,
			String webBannerPictureList,
			List<Long> storeIdList,
			List<Long> membershipIdList);

	int deleteMerchantProductWithTx(MerchantProductDTO dto);
	/**
	 * 提交审核
	 * @param dto
	 * @return
	 */
	public int submitAuditWithTx(MerchantProductDTO dto);
	/**
	 * 根据su草稿id更新su草稿图片信息
	 * @param merchantProductId suId
	 * @param picture su草稿封面图片
	 * @param pictures su草稿轮播图
	 * @param webBannerPictures suWeb端轮播图
	 * @param merchantProductDTO su商品信息
	 * @return
	 */
	public int updateMerchantProductPictureByIdWithTx(
			Long merchantProductId,
			String picture, 
			List<String> pictures,
			List<String> webBannerPictures,
			MerchantProductDTO merchantProductDTO);

	public int updateProductUnitByIdWithTx(List<ProductUnitVO> productUnitList,MerchantProductDTO merchantProductDTO,
										   List<Long> companyIds,List<Long> demoCompanyIds,List<Long> competingCompanyIds);
	/**
	 * 批量通过
	 * @param vo
	 * @param req
	 * @return
	 */
	public int passAllAuditWithTx(String ids,Long platformId,HttpServletRequest req);
	/**
	 * 根据id通过
	 * @param vo
	 * @param req
	 * @return
	 */
	public int merchantProductPassWithTx(Long merchantProductId,String cause,int type,Long platformId,HttpServletRequest req);
	/**
	 * 根据su草稿id查询基本信息（app预览）
	 * @param merchantProductId
	 * @return
	 */
	public Map<String, Object> findMerchantProductAppById(Long merchantProductId);

	/**
	 * 查询商品列表详情
	 * @param dto
	 * @param page
	 * @return
	 */
    PageResult<MerchantProductDTO> findCommodityDetailsOfPage(CommodityDetailsDTO dto, Pagination page);

    JsonResult<List<Map<String,Object>>> findPuNameBySuIdBackStage(Long suId);

	public Map<String, Object> findMerchantProductById(MerchantProductDTO dto);
	

	public long findMaxfrontSerialNumber();
}
	