package com.egeo.components.product.facade;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.JdUtils2;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.delivery.JdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.JdCategoryDTO;
import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.service.read.AttributeNameReadService;
import com.egeo.components.product.service.read.AttributeValueReadService;
import com.egeo.components.product.service.read.CategoryAttNameReadService;
import com.egeo.components.product.service.read.CategoryReadService;
import com.egeo.components.product.service.read.CategoryTreeNodeReadService;
import com.egeo.components.product.service.read.CategoryTreeReadService;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.service.read.JdCategoryReadService;
import com.egeo.components.product.service.read.JdPriceConfigReadService;
import com.egeo.components.product.service.read.JdPriceLimitUploadReadService;
import com.egeo.components.product.service.read.JdProductReadService;
import com.egeo.components.product.service.read.MerchantPictureReadService;
import com.egeo.components.product.service.read.MerchantProdAttNameReadService;
import com.egeo.components.product.service.read.MerchantProdAttValueReadService;
import com.egeo.components.product.service.read.MerchantProductReadService;
import com.egeo.components.product.service.read.PictureReadService;
import com.egeo.components.product.service.read.ProductAttNameReadService;
import com.egeo.components.product.service.read.ProductAttValueReadService;
import com.egeo.components.product.service.read.ProductPictureReadService;
import com.egeo.components.product.service.read.ProductReadService;
import com.egeo.components.product.service.read.ProductUnitReadService;
import com.egeo.components.product.service.read.SkuAttNameReadService;
import com.egeo.components.product.service.read.SkuAttValueReadService;
import com.egeo.components.product.service.read.SkuReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.read.StandardProductUnitReadService;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.product.service.write.AttributeNameWriteService;
import com.egeo.components.product.service.write.AttributeValueWriteService;
import com.egeo.components.product.service.write.CategoryAttNameWriteService;
import com.egeo.components.product.service.write.CategoryTreeNodeWriteService;
import com.egeo.components.product.service.write.CategoryWriteService;
import com.egeo.components.product.service.write.CommodityProductUnitWriteService;
import com.egeo.components.product.service.write.JdCategoryWriteService;
import com.egeo.components.product.service.write.JdProductInnerIdWriteService;
import com.egeo.components.product.service.write.JdProductWriteService;
import com.egeo.components.product.service.write.MerchantPictureWriteService;
import com.egeo.components.product.service.write.MerchantProdClientWriteService;
import com.egeo.components.product.service.write.MerchantProdDescribeWriteService;
import com.egeo.components.product.service.write.MerchantProdPictureWriteService;
import com.egeo.components.product.service.write.MerchantProductCompanyWriteService;
import com.egeo.components.product.service.write.MerchantProductStoreWriteService;
import com.egeo.components.product.service.write.MerchantProductWriteService;
import com.egeo.components.product.service.write.PictureWriteService;
import com.egeo.components.product.service.write.ProductAttNameWriteService;
import com.egeo.components.product.service.write.ProductAttValueWriteService;
import com.egeo.components.product.service.write.ProductCauseWriteService;
import com.egeo.components.product.service.write.ProductDescriptionWriteService;
import com.egeo.components.product.service.write.ProductPictureWriteService;
import com.egeo.components.product.service.write.ProductUnitWriteService;
import com.egeo.components.product.service.write.ProductWriteService;
import com.egeo.components.product.service.write.SkuAttNameWriteService;
import com.egeo.components.product.service.write.SkuAttValueWriteService;
import com.egeo.components.product.service.write.SkuWriteService;
import com.egeo.components.product.service.write.StandardProductUnitAttNameRecordWriteService;
import com.egeo.components.product.service.write.StandardProductUnitAttNameWriteService;
import com.egeo.components.product.service.write.StandardProductUnitAttValueRecordWriteService;
import com.egeo.components.product.service.write.StandardProductUnitAttValueWriteService;
import com.egeo.components.product.service.write.StandardProductUnitDescriptionRecordWriteService;
import com.egeo.components.product.service.write.StandardProductUnitDescriptionWriteService;
import com.egeo.components.product.service.write.StandardProductUnitPictureRecordWriteService;
import com.egeo.components.product.service.write.StandardProductUnitPictureWriteService;
import com.egeo.components.product.service.write.StandardProductUnitRecordWriteService;
import com.egeo.components.product.service.write.StandardProductUnitWriteService;
import com.egeo.components.product.service.write.StandardUnitClientWriteService;
import com.egeo.components.product.service.write.StandardUnitCompanyWriteService;
import com.egeo.components.product.service.write.StandardUnitDescribeWriteService;
import com.egeo.components.product.service.write.StandardUnitPictureWriteService;
import com.egeo.components.product.service.write.StandardUnitStoreWriteService;
import com.egeo.components.product.service.write.StandardUnitWriteService;
import com.egeo.components.product.service.write.SuSerachRuleWriteService;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;


@Component
public class JdProductFacade {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private JdProductReadService jdProductReadService;

	@Resource
	private JdProductWriteService jdProductWriteService;

	@Resource
	private CategoryTreeNodeReadService categoryTreeNodeReadService;

	@Resource
	private CategoryTreeNodeWriteService categoryTreeNodeWriteService;

	@Resource
	private MerchantPictureReadService 	merchantPictureReadService;
	@Resource
	private CategoryTreeReadService categoryTreeReadService;
	@Resource
private ProductUnitReadService productUnitReadService;
	@Resource
	private CategoryWriteService categoryWriteService;

	@Resource
	private JdCategoryWriteService jdCategoryWriteService;

	@Resource
	private JdCategoryReadService jdCategoryReadService;

	@Resource
	private  AttributeNameWriteService attributeNameWriteService;

	@Resource
	private AttributeValueWriteService attributeValueWriteService;

	@Resource
private CommodityProductUnitWriteService commodityProductUnitWriteService;

	@Resource
	private ProductAttNameReadService productAttNameReadService;

	@Resource
	private ProductAttNameWriteService productAttNameWriteService;

	@Resource
	private ProductAttValueReadService productAttValueReadService;

	@Resource
	private ProductAttValueWriteService productAttValueWriteService;

	@Resource
	private CommodityProductUnitReadService commodityProductUnitReadService;
	@Resource
	private MerchantProductReadService merchantProductReadService;

	@Resource
	private SkuReadService skuReadService;

	@Resource
	private JdPriceConfigReadService jdPriceConfigReadService;
	@Resource
	private SkuWriteService skuWriteService;

	@Resource
	private SkuAttNameReadService skuAttNameReadService;

	@Resource
	private SkuAttValueReadService skuAttValueReadService;

	@Resource
	private MerchantProdAttNameReadService merchantProdAttNameReadService;

	@Resource
	private MerchantProdAttValueReadService merchantProdAttValueReadService;

	@Resource
	private PictureReadService pictureReadService;

	@Resource
	private StandardProductUnitReadService standardProductUnitReadService;

	@Resource
	private ProductCauseWriteService productCauseWriteService;

	@Resource
	private StandardProductUnitWriteService standardProductUnitWriteService;

	@Resource
	private StandardProductUnitDescriptionWriteService standardProductUnitDescriptionWriteService;

	@Resource
	private StandardProductUnitAttNameReadService standardProductUnitAttNameReadService;

	@Resource
	private StandardProductUnitAttNameWriteService standardProductUnitAttNameWriteService;

	@Resource
	private StandardProductUnitAttValueWriteService standardProductUnitAttValueWriteService;

	@Resource
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;

	@Resource
	private ProductPictureReadService productPictureReadService;

	@Resource
	private StandardProductUnitPictureWriteService standardProductUnitPictureWriteService;

	@Resource
	private StandardProductUnitRecordWriteService standardProductUnitRecordWriteService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;
	@Resource
	private StandardProductUnitDescriptionRecordWriteService standardProductUnitDescriptionRecordWriteService;

	@Resource
	private StandardProductUnitAttNameRecordWriteService standardProductUnitAttNameRecordWriteService;

	@Resource
	private StandardProductUnitAttValueRecordWriteService standardProductUnitAttValueRecordWriteService;

	@Resource
	private StandardProductUnitPictureRecordWriteService standardProductUnitPictureRecordWriteService;

	@Resource
	private CategoryReadService categoryReadService;

	@Resource
	private SkuAttNameWriteService skuAttNameWriteService;

	@Resource
	private SkuAttValueWriteService skuAttValueWriteService;

	@Resource
	private AttributeValueReadService attributeValueReadService;

	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockWriteService;

	@Resource
	private CategoryAttNameReadService categoryAttNameReadService;

	@Resource
	private AttributeNameReadService attributeNameReadService;

	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;

	@Resource
	private ProductReadService productReadService;
	@Resource

	private ProductWriteService productWriteService;
	@Resource
	private StandardUnitReadService standardUnitReadService;
	@Resource
	private StandardUnitWriteService standardUnitWriteService;
	@Resource
	private PictureWriteService pictureWriteService;
	@Resource
	private ProductDescriptionWriteService productDescriptionWriteService;
	@Resource
	private ProductPictureWriteService productPictureWriteService;
@Resource
private MerchantProductWriteService merchantProductWriteService;
	@Resource
	private MerchantProductStoreWriteService merchantProductStoreWriteService;
@Resource
private MerchantProductCompanyWriteService merchantProductCompanyWriteService;
@Resource
private MerchantPictureWriteService merchantPictureWriteService;
	@Resource
	private MerchantProdPictureWriteService merchantProdPictureWriteService;
	@Resource
	private MerchantProdDescribeWriteService merchantProdDescribeWriteService;
	@Resource
	private MerchantProdClientWriteService merchantProdClientWriteService;
	@Resource
	private StandardUnitDescribeWriteService standardUnitDescribeWriteService;
	@Resource
	private StandardUnitPictureWriteService standardUnitPictureWriteService;
	@Resource
	private StandardUnitStoreWriteService standardUnitStoreWriteService;
	@Resource
	private StandardUnitCompanyWriteService standardUnitCompanyWriteService;
	@Resource
	private StandardUnitClientWriteService standardUnitClientWriteService;
	@Resource
	private ProductUnitWriteService productUnitWriteService;
	@Resource
	private SuSerachRuleWriteService suSerachRuleWriteService;
	@Resource
	private JdPriceLimitUploadReadService jdPriceLimitUploadReadService;
	@Resource
	private CategoryAttNameWriteService categoryAttNameWriteService;

	@Resource
	private JdProductInnerIdWriteService jdProductInnerIdWriteService;

	@Autowired
	private JdUtils jdUtils;
	@Resource
	private JedisUtil cache;

	@Autowired
	private JdUtils2 jdUtils2;



	public JdProductDTO findJdProductById(JdProductDTO dto){

		return jdProductReadService.findJdProductById(dto);
	}

	public PageResult<JdProductDTO> findJdProductOfPage(JdProductDTO dto,Pagination page){

		return jdProductReadService.findJdProductOfPage(dto, page);

	}

	public List<JdProductDTO> findJdProductAll(JdProductDTO dto){

		return jdProductReadService.findJdProductAll(dto);

	}

	public Long insertJdProductWithTx(JdProductDTO dto){

		return jdProductWriteService.insertJdProductWithTx(dto);
	}

	public int updateJdProductWithTx(JdProductDTO dto){

		return jdProductWriteService.updateJdProductWithTx(dto);
	}

	public int deleteJdProductWithTx(JdProductDTO dto){

		return jdProductWriteService.deleteJdProductWithTx(dto);

	}

	public Long createJdCategoryToLocal(Long treeId, String catId, String name, String jdParentId, Long selfParentId, int catClass) {
		JdCategoryDTO jdCategoryDTO = new JdCategoryDTO();
		jdCategoryDTO.setId(Long.parseLong(catId));
		JdCategoryDTO dbDTO = jdCategoryReadService.findJdCategoryById(jdCategoryDTO);
		if (dbDTO != null) {
			return dbDTO.getId();
		} else {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setSerialNumber(generateCategorySerialNumber(treeId, selfParentId));
			categoryDTO.setName(name);
			categoryDTO.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
			Long categoryId = categoryWriteService.insertCategoryWithTx(categoryDTO);

			List<CategoryAttNameDTO> list = new ArrayList<>();
			CategoryAttNameDTO categoryAttNameDTO = new CategoryAttNameDTO();
			CategoryAttNameDTO categoryAttNameDTO1 = new CategoryAttNameDTO();
			CategoryAttNameDTO categoryAttNameDTO2 = new CategoryAttNameDTO();
			CategoryAttNameDTO categoryAttNameDTO3 = new CategoryAttNameDTO();
			CategoryAttNameDTO categoryAttNameDTO4 = new CategoryAttNameDTO();
			categoryAttNameDTO.setCategoryId(categoryId);
			categoryAttNameDTO.setType(1);
			categoryAttNameDTO.setAttNameId(1L);
			categoryAttNameDTO.setSortValue(1);
			categoryAttNameDTO1.setCategoryId(categoryId);
			categoryAttNameDTO1.setType(1);
			categoryAttNameDTO1.setAttNameId(2L);
			categoryAttNameDTO1.setSortValue(1);
			categoryAttNameDTO2.setCategoryId(categoryId);
			categoryAttNameDTO2.setType(1);
			categoryAttNameDTO2.setAttNameId(3L);
			categoryAttNameDTO2.setSortValue(1);
			categoryAttNameDTO3.setCategoryId(categoryId);
			categoryAttNameDTO3.setType(1);
			categoryAttNameDTO3.setAttNameId(4L);
			categoryAttNameDTO3.setSortValue(1);
			categoryAttNameDTO4.setCategoryId(categoryId);
			categoryAttNameDTO4.setType(2);
			categoryAttNameDTO4.setAttNameId(100L);
			categoryAttNameDTO4.setSortValue(1);
			list.add(categoryAttNameDTO);
			list.add(categoryAttNameDTO1);
			list.add(categoryAttNameDTO2);
			list.add(categoryAttNameDTO3);
			list.add(categoryAttNameDTO4);
			categoryAttNameWriteService.addCategoryAttNameListWithTx(list);





			CategoryTreeNodeDTO dto = new CategoryTreeNodeDTO();
			dto.setCategoryId(categoryId);
			dto.setCategoryTreeId(treeId);
			dto.setParentId(selfParentId);
			dto.setListSort(1);
			logger.info("错误检查catId="+catId);
			logger.info("错误检查JdparentId="+jdParentId);
			logger.info("错误检查selfParentId="+selfParentId);
			Long newParentId = categoryTreeNodeWriteService.insertCategoryTreeNodeWithTx(dto, name,catId);

			jdCategoryDTO.setName(name);
			jdCategoryDTO.setParentId(jdParentId == null ? null : Long.parseLong(jdParentId));
			jdCategoryDTO.setInnerCategoryId(categoryId);
			jdCategoryDTO.setCatClass(catClass+1);
			jdCategoryWriteService.insertJdCategoryWithTx(jdCategoryDTO);

			return newParentId;
		}
	}

	private String generateCategorySerialNumber(Long treeId, Long selfParentId) {
		String serialNum = "";
		if (selfParentId != null && selfParentId > 0) {
			CategoryTreeNodeDTO categoryTreeNode = categoryTreeNodeReadService.findByCategoryTreeNodeId(selfParentId);
			if (categoryTreeNode != null) {
				CategoryDTO category = categoryReadService.findCategoryById(categoryTreeNode.getCategoryId());
				serialNum += category.getSerialNumber();
			}
		}
		CategoryTreeNodeDTO dto = new CategoryTreeNodeDTO();
		dto.setParentId(selfParentId);
		dto.setCategoryTreeId(treeId);
		List<CategoryTreeNodeDTO> childList = categoryTreeNodeReadService.findAll(dto);
		if (childList == null || childList.size() == 0) {
			serialNum += "01";
		} else if (childList.size() < 9) {
			int index = childList.size() + 1;
			serialNum += "0" + index;
		} else {
			int index = childList.size() + 1;
			serialNum += index;
		}
		return serialNum;
	}

	public Long createJdTopCategoryIfNotExist(Long treeId) {
		String name = "京东商品分类";
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setName(name);
		List<CategoryDTO> categoryList = categoryReadService.findAllList(categoryDTO);
		if (EmptyUtil.isNotEmpty(categoryList)) {
			Long categoryId = categoryList.get(0).getId();
			CategoryTreeNodeDTO dto = new CategoryTreeNodeDTO();
			dto.setCategoryTreeId(treeId);
			dto.setCategoryId(categoryId);
			List<CategoryTreeNodeDTO> treeNodeList = categoryTreeNodeReadService.findAll(dto);
			return treeNodeList.get(0).getId();
		} else {
			categoryDTO.setSerialNumber(generateCategorySerialNumber(treeId, 0L));
			categoryDTO.setPlatformId(PlatformKeyConstant.FGJ_PLATFORM_ID);
			Long categoryId = categoryWriteService.insertCategoryWithTx(categoryDTO);
			CategoryTreeNodeDTO dto = new CategoryTreeNodeDTO();
			dto.setCategoryId(categoryId);
			dto.setCategoryTreeId(treeId);
			dto.setParentId(0L);
			dto.setName(name);
			dto.setListSort(1);
			return categoryTreeNodeWriteService.insertCategoryTreeNodeWithTx(dto, name, null);
		}
	}
	public CategoryDTO findCategoryById(Long innerCategoryId) {
		return  categoryReadService.findCategoryById(innerCategoryId);
	}
	public Long findBackendCategoryTreeId() {
		CategoryTreeDTO dto = new CategoryTreeDTO();
		dto.setType(1);
//		dto.setStartUsing(1);
		List<CategoryTreeDTO> treeList = categoryTreeReadService.findCategoryAll(dto);
		if (EmptyUtil.isNotEmpty(treeList)) {
			return treeList.get(0).getId();
		} else {
			return null;
		}
	}

	public JdCategoryDTO findJdCategoryDTOById(Long id) {
		JdCategoryDTO dto = new JdCategoryDTO();
		dto.setId(id);
		return jdCategoryReadService.findJdCategoryById(dto);
	}

	public void updateSyncStatus(JdProductDTO dto) {
		jdProductWriteService.updateSyncStatus(dto);
	}

    public void saveJdProductList(List<JdProductDTO> jdProductDTOList) {
		jdProductWriteService.saveJdProductListFirst(jdProductDTOList);
    }
    //京东商品拆解存储到本地
    public void createProduct(List<JdProductDTO> createProductList, Long productIdOld,
							  Long pictureIdOld, Long attValueIdOld, Long productAttNameIdOld,
							  Long spuIdOld, Long spuAttNameIdOld, Long skuAttNameIdOld,
							  Long skuIdOld, Long merchantProductIdOld, Long merchantPictureIdOld,
							  Long productUnitIdOld, Long puIdOld) {
		//同步商品

		logger.info("------------------------组织创建商品需要的所有参数------------------------------------");

		List<Long> productIdList=new ArrayList<>();
			List<String> spuSerialNumberList=new ArrayList<>();
			List<String> productCategoryList=new ArrayList<>();
			List<Long> catgoryTreeNodeIdList=new ArrayList<>();
			List<String> nameList=new ArrayList<>();
			List<Long> pictureIdList=new ArrayList<>();
			List<String> pictureUrlList=new ArrayList<>();
			List<Long> productAttNameIdList=new ArrayList<>();
			List<Long> attValueIdList=new ArrayList<>();
			List<Long> spuIdList=new ArrayList<>();
			List<Long> spuAttNameIdList=new ArrayList<>();
			List<Long> skuAttNameIdList=new ArrayList<>();
			List<Long> skuIdList=new ArrayList<>();
			List<String> skuSerialNumberList=new ArrayList<>();
			List<String> jdSkuIdList=new ArrayList<>();
			List<Long> merchantProductIdList=new ArrayList<>();
			List<String> merchantProductSerialNumberList=new ArrayList<>();
			List<BigDecimal> salePriceList=new ArrayList<>();
			List<BigDecimal> marketPriceList=new ArrayList<>();
			List<BigDecimal> demoSalePriceList=new ArrayList<>();
			List<BigDecimal> competingSalePriceList=new ArrayList<>();
			List<Long> suIdList=new ArrayList<>();
			List<Long> merchantPictureIdList=new ArrayList<>();
			List<Long> productUnitIdList=new ArrayList<>();
			List<String> productUnitSerialNumberList=new ArrayList<>();
			List<Long> puIdList=new ArrayList<>();
			List<String> attValueList=new ArrayList<>();
		List<Integer> profitList = new ArrayList<>();
		List<Long> jdSpuIdList=new ArrayList<>();



		List<JdPriceConfigDTO> jdPriceConfigDTOList = jdPriceConfigReadService.findJdPriceConfigAll(new JdPriceConfigDTO());
		if (EmptyUtil.isEmpty(jdPriceConfigDTOList)||jdPriceConfigDTOList.get(0)== null
				|| jdPriceConfigDTOList.get(0).getStandardCompanyRate() == null
				|| jdPriceConfigDTOList.get(0).getCompetingCompanyRate() == null
				|| jdPriceConfigDTOList.get(0).getDemocompanysCompanyRate() == null) {
			throw new BusinessException("请先设置京东商品定价配置");
		}




		Integer size = createProductList.size();

		for(int i=1;i<=createProductList.size();i++){
				JdProductDTO jdProductDTO=createProductList.get(i-1);

				Long productId = productIdOld + i;
				String spuSerialNumber= StringUtils.protectCategoryProductSerialNumber(jdProductDTO.getInnerCategorySerialNumber() + StringUtils.protectProductSerialNumber(productId));
				String productCategory = jdProductDTO.getInnerCategoryName();
				Long catgoryTreeNodeId = jdProductDTO.getInnerCategoryTreeNode();
				String name = jdProductDTO.getName();
				Long pictureId=pictureIdOld + i;
				String pictureUrl = jdProductDTO.getPicture();
				Long productAttNameId=productAttNameIdOld+((5 * (i-1))+1);
				Long attValueId = attValueIdOld + i;
				Long spuId = productIdOld + i;
				Long spuAttNameId = spuAttNameIdOld + ((5 * (i-1))+1);
				//Long skuAttNameId = skuAttNameIdOld + ((5 * (i-1))+1);
				Long skuAttNameId = skuAttNameIdOld +i;
				Long skuId = skuIdOld + i;
				String skuSerialNumber = spuSerialNumber + StringUtils.skuSerialNumber(1);
				Long jdSkuId = jdProductDTO.getId();
				Long merchantProductId = merchantProductIdOld + i;
				String merchantProductSerialNumber =spuSerialNumber+ StringUtils.skuSerialNumber(1);
				BigDecimal salePrice=jdProductDTO.getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTOList.get(0).getStandardCompanyRate())).divide(BigDecimal.valueOf(100L), 2);
				BigDecimal demoSalePrice=jdProductDTO.getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTOList.get(0).getDemocompanysCompanyRate())).divide(BigDecimal.valueOf(100L), 2);
				BigDecimal competingSalePrice=jdProductDTO.getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTOList.get(0).getCompetingCompanyRate())).divide(BigDecimal.valueOf(100L), 2);
				BigDecimal marketPrice = jdProductDTO.getMarketPrice();
				Long suId = merchantProductId;
				Long merchantPictureId = merchantPictureIdOld + i;
				Long productUnitId = productUnitIdOld + i;
				String productUnitSerialNumber =skuSerialNumber + StringUtils.skuSerialNumber(1) ;
				Long puId = puIdOld + i;
				String attValue = jdProductDTO.getName();


				attValueList.add(attValue);
				productIdList.add(productId);
				spuSerialNumberList.add(spuSerialNumber);
				productCategoryList.add(productCategory);
				catgoryTreeNodeIdList.add(catgoryTreeNodeId);
				nameList.add(name);
				pictureIdList.add(pictureId);
				pictureUrlList.add(pictureUrl);
				productAttNameIdList.add(productAttNameId);
				attValueIdList.add(attValueId);
				spuIdList.add(spuId);
				spuAttNameIdList.add(spuAttNameId);
				skuAttNameIdList.add(skuAttNameId);
				skuIdList.add(skuId);
				skuSerialNumberList.add(skuSerialNumber);
				jdSkuIdList.add(jdSkuId.toString());
				merchantProductIdList.add(merchantProductId);
				merchantProductSerialNumberList.add(merchantProductSerialNumber);
				salePriceList.add(salePrice);
				marketPriceList.add(marketPrice);
				demoSalePriceList.add(demoSalePrice);
				competingSalePriceList.add(competingSalePrice);
				suIdList.add(suId);
				merchantPictureIdList.add(merchantPictureId);
				productUnitIdList.add(productUnitId);
				productUnitSerialNumberList.add(productUnitSerialNumber);
				puIdList.add(puId);
			profitList.add(jdProductDTO.getProfit());
			jdSpuIdList.add(jdProductDTO.getSpuId());

			}
		//jdProductWriteService.updateJdProductList(createProductList);
		buildProductList(jdSpuIdList,profitList,productIdList, spuSerialNumberList, productCategoryList, catgoryTreeNodeIdList,
				nameList, pictureIdList, pictureUrlList, productAttNameIdList, attValueIdList,
				spuIdList, spuAttNameIdList, skuAttNameIdList, skuIdList, skuSerialNumberList,
				jdSkuIdList, merchantProductIdList, merchantProductSerialNumberList, salePriceList,
				marketPriceList, demoSalePriceList, competingSalePriceList, suIdList, merchantPictureIdList,
				productUnitIdList, productUnitSerialNumberList, puIdList,attValueList);





	}
    //处理京东商品数据主体
	private void buildProductList(List<Long> jdSpuIdList,List<Integer> profitList, List<Long> productIdList, List<String> spuSerialNumberList, List<String> productCategoryList, List<Long> catgoryTreeNodeIdList, List<String> nameList, List<Long> pictureIdList, List<String> pictureUrlList, List<Long> productAttNameIdList, List<Long> attValueIdList, List<Long> spuIdList, List<Long> spuAttNameIdList, List<Long> skuAttNameIdList, List<Long> skuIdList, List<String> skuSerialNumberList, List<String> jdSkuIdList, List<Long> merchantProductIdList, List<String> merchantProductSerialNumberList, List<BigDecimal> salePriceList, List<BigDecimal> marketPriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList, List<Long> suIdList, List<Long> merchantPictureIdList, List<Long> productUnitIdList, List<String> productUnitSerialNumberList, List<Long> puIdList, List<String> attValueList) {
		logger.info("------------------------异步处理商品的创建开始------------------------------------");

		//标准产品库--属性
		standardProductUnitAttNameWriteService.saveStandardProductUnitAttName( spuIdList, spuAttNameIdList);
		standardProductUnitPictureWriteService.saveStandardProductUnitPicture( spuIdList, pictureIdList);
		//产品库
		productWriteService.saveProductList(productIdList,spuSerialNumberList, productCategoryList,catgoryTreeNodeIdList, nameList);
		pictureWriteService.savePicture(pictureIdList,pictureUrlList);
		attributeValueWriteService.saveAttValue(attValueIdList,attValueList);
		productAttNameWriteService.saveProductAttName(productAttNameIdList,productIdList);
		productAttValueWriteService.saveProductAttValue( productAttNameIdList, attValueIdList);
		productDescriptionWriteService.saveProductDescription( productIdList);
		productPictureWriteService.saveProductPicture( productIdList,pictureIdList);
		//标准产品库--
		standardProductUnitWriteService.saveSPU( spuIdList, spuSerialNumberList,
				productCategoryList, catgoryTreeNodeIdList,
				nameList);
		standardProductUnitAttValueWriteService.saveSPUValue(spuAttNameIdList, attValueIdList);
		standardProductUnitDescriptionWriteService.saveStandardProductUnitDescription( spuIdList);

		skuWriteService.saveSku( skuIdList, spuIdList, skuSerialNumberList, nameList, jdSkuIdList);
		skuAttNameWriteService.saveSkuAttName( skuAttNameIdList, skuIdList);
		skuAttValueWriteService.saveSkuAttValuePO( skuAttNameIdList, attValueIdList);

		//商户产品库
		merchantProductWriteService.saveMerchantProduct(profitList, merchantProductIdList,  spuIdList,  merchantProductSerialNumberList,
				productCategoryList, nameList,  salePriceList,
				marketPriceList,
				demoSalePriceList,
				competingSalePriceList);




		merchantProdClientWriteService.saveMerchantProdClient(merchantProductIdList);
		merchantProdDescribeWriteService.saveMerchantProdDescribe( merchantProductIdList);
		merchantProdPictureWriteService.saveMerchantProdPicture( merchantProductIdList, merchantPictureIdList);
		merchantPictureWriteService.saveMerchantPicture(merchantPictureIdList, pictureIdList);
		merchantProductCompanyWriteService.saveMerchantProductCompany( merchantProductIdList);
		merchantProductStoreWriteService.saveMerchantProductStore( merchantProductIdList);

		//SU更新
		standardUnitWriteService.saveStandardUnit(profitList, suIdList, spuIdList, merchantProductSerialNumberList, productCategoryList,
				nameList, salePriceList, marketPriceList, demoSalePriceList,
				competingSalePriceList);
		//suSerachRuleWriteService.saveSuSerachRule(suIdList,nameList);
		standardUnitDescribeWriteService.saveStandardUnitDescribe(suIdList);
		standardUnitPictureWriteService.saveStandardUnitPicture(suIdList,merchantPictureIdList);
		standardUnitStoreWriteService.saveStandardUnitStore(suIdList);
		standardUnitCompanyWriteService.saveStandardUnitCompany(suIdList);
		standardUnitClientWriteService.saveStandardUnitClient(suIdList);
		productUnitWriteService.saveProductUnit(productUnitIdList, productUnitSerialNumberList, skuIdList,merchantProductIdList,
				nameList,salePriceList, demoSalePriceList,competingSalePriceList);

		commodityProductUnitWriteService.saveCommodityProductUnit(jdSpuIdList, puIdList, productUnitSerialNumberList, productUnitIdList, skuIdList,
				suIdList,nameList,salePriceList,demoSalePriceList,
				competingSalePriceList);

		//处理更新关联表
		jdProductInnerIdWriteService.saveJdProductInnerIdList(jdSkuIdList,productIdList,skuIdList,suIdList,productUnitIdList,puIdList,pictureIdList,attValueIdList);

		//跨库所以单独处理
		commodityProductUnitWarehouseStockWriteService.saveCommodityProductUnitWarehouseStock(com.egeo.utils.StringUtils.longsToStrings(puIdList), com.egeo.utils.StringUtils.longsToStrings(suIdList), com.egeo.utils.StringUtils.longsToStrings(merchantProductIdList));

		logger.info("------------------------异步处理商品的创建完成------------------------------------");
	}


	public void setAllSyncStatus(int status) {
		jdProductWriteService.setAllSyncStatus(status);

	}

	public List<Long> findAllIdList() {
		return jdProductReadService.findAllIdList();
	}
	public void updateJdProductList(List<JdProductDTO> updateList) {
		jdProductWriteService.updateJdProductList(updateList);
	}

	public List<JdProductDTO> findJdProductListByIds(List<Long> skuIdList) {
		return jdProductReadService.findJdProductListByIds(skuIdList);
	}

	public  PageResult<JdProductDTO> getJdProductListByParams(Pagination page, Long skuId, List<String> skuNameList, Long updateTimeStart, Long updateTimeEnd, Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow) {
		return jdProductReadService.getJdProductListByParams(page,skuId,skuNameList,updateTimeStart,updateTimeEnd,
				profitStart,profitEnd,state,sycStatus,catId,isShow);
	}

	public List<Long> findJdCategoryIdByCatClass(int catClass) {
		return jdCategoryReadService.findJdCategoryIdByCatClass(catClass);
	}

	public JsonResult getJdCategoryList(Integer catClass, Long parentId) {
		JdCategoryDTO category1 = new JdCategoryDTO();
		category1.setCatClass(catClass);
		category1.setParentId(parentId);
		List<JdCategoryDTO> jdCategory1 = jdCategoryReadService.findJdCategoryAll(category1);


		return JsonResult.success(jdCategory1);
	}

	public JdPriceLimitUploadDTO getJdPriceLimitUpLoad() {
		JdPriceLimitUploadDTO dto = new JdPriceLimitUploadDTO();
		dto.setId(1L);
		return jdPriceLimitUploadReadService.findJdPriceLimitUploadById(dto);
	}

	public void updateCreateProductList(List<JdProductDTO> jdProductDTOList) {
		for(JdProductDTO dto:jdProductDTOList){
			jdProductWriteService.updateJdProductWithTx(dto);

		}
	}


	//查询当前product最后一个id
	public Long findProductLastId(){
		return productReadService.findLastId();

	}
	public Long findPictureLastId() {
		return  pictureReadService.findLastId();
	}
	public Long findAttributeValueLastId(){
		return  attributeValueReadService.findLastId();
	}
	public Long findProductAttNameLastId(){
		return productAttNameReadService.findLastId();
	}
	public Long findStandardProductUnitLastId(){
		return standardProductUnitReadService.findLastId();
	}
	public Long findStandardProductUnitAttNameLastId(){
		return standardProductUnitAttNameReadService.findLastId();
	}
	public Long findSkuAttNameLastId(){
		return skuAttNameReadService.findLastId();
	}
	public Long findSkuLastId(){
		return skuReadService.findLastId();
	}
	public Long findMerchantProductLastId(){
		return merchantProductReadService.findLastId();
	}
	public Long findStandardUnitLastId(){
		return standardUnitReadService.findLastId();
	}
	public Long findMerchantPictureLastId(){
		return merchantPictureReadService.findLastId();
	}
	public Long findProductUnitLastId(){
		return productUnitReadService.findLastId();
	}
	public Long findCommodityProductUnitLastId(){
		return commodityProductUnitReadService.findLastId();
	}


	public void updateProductByJdProductList(List<JdProductDTO> updateProductList, List<JdProductInnerIdDTO> list) {

		List<Long> productIdList = new ArrayList<>();
		List<Long> standardProductUnitIdList = new ArrayList<>();
		List<Long> skuIdList = new ArrayList<>();
		List<Long> merchantProductIdList = new ArrayList<>();
		List<Long> suIdList = new ArrayList<>();
		List<Long> productUnitIdList = new ArrayList<>();
		List<Long> puIdList = new ArrayList<>();
		List<Long> pictureIdList = new ArrayList<>();
		List<Long> attValueIdList = new ArrayList<>();
		List<String> nameList = new ArrayList<>();
		List<String> pictureUrlList = new ArrayList<>();
		List<String> attValueList = new ArrayList<>();
		List<Long> jdSkuIdList = new ArrayList<>();
		List<BigDecimal> marketPriceList = new ArrayList<>();
		List<BigDecimal> salePriceList = new ArrayList<>();
		List<BigDecimal> demoSalePriceList = new ArrayList<>();
		List<BigDecimal> competingSalePriceList = new ArrayList<>();
		List<Integer> profitList = new ArrayList<>();


		for(int i=0;i<updateProductList.size();i++){
			Long productId=list.get(i).getInnerProductId();
			productIdList.add(productId);
			Long standardProductUnitId = productId;
			standardProductUnitIdList.add(standardProductUnitId);
			Long skuId=list.get(i).getInnerSkuId();
			skuIdList.add(skuId);
			Long merchantProductId = list.get(i).getInnerSuId();
			merchantProductIdList.add(merchantProductId);
			Long suId = merchantProductId;
			suIdList.add(suId);
			Long productUnitId = list.get(i).getInnerProductUnitId();
			productUnitIdList.add(productUnitId);
			Long puId = list.get(i).getInnerPuId();
			puIdList.add(puId);
			Long pictureId = list.get(i).getInnerPictureId();
			pictureIdList.add(pictureId);
			Long attValueId = list.get(i).getInnerAttValueId();
			attValueIdList.add(attValueId);

			List<JdPriceConfigDTO> jdPriceConfigDTOList = jdPriceConfigReadService.findJdPriceConfigAll(new JdPriceConfigDTO());
			if (EmptyUtil.isEmpty(jdPriceConfigDTOList)||jdPriceConfigDTOList.get(0)== null
					|| jdPriceConfigDTOList.get(0).getStandardCompanyRate() == null
					|| jdPriceConfigDTOList.get(0).getCompetingCompanyRate() == null
					|| jdPriceConfigDTOList.get(0).getDemocompanysCompanyRate() == null) {
				throw new BusinessException("请先设置京东商品定价配置");
			}
			BigDecimal salePrice=updateProductList.get(i).getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTOList.get(0).getStandardCompanyRate())).divide(BigDecimal.valueOf(100L), 2);
			BigDecimal demoSalePrice=updateProductList.get(i).getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTOList.get(0).getDemocompanysCompanyRate())).divide(BigDecimal.valueOf(100L), 2);
			BigDecimal competingSalePrice=updateProductList.get(i).getPrice().multiply(BigDecimal.valueOf(jdPriceConfigDTOList.get(0).getCompetingCompanyRate())).divide(BigDecimal.valueOf(100L), 2);

			nameList.add(updateProductList.get(i).getName());
			pictureUrlList.add(updateProductList.get(i).getImagePath());
			attValueList.add(updateProductList.get(i).getName());
			jdSkuIdList.add(updateProductList.get(i).getId());
			marketPriceList.add(updateProductList.get(i).getMarketPrice());
			salePriceList.add(salePrice);
			demoSalePriceList.add(demoSalePrice);
			competingSalePriceList.add(competingSalePrice);
			profitList.add(updateProductList.get(i).getProfit());
		}
		updateProductByJdList(profitList,productIdList ,standardProductUnitIdList ,
				skuIdList , merchantProductIdList , suIdList ,
				productUnitIdList , puIdList , pictureIdList ,
				attValueIdList , nameList , pictureUrlList ,
				attValueList , jdSkuIdList , marketPriceList ,
				salePriceList , demoSalePriceList , competingSalePriceList  );
	}

	private void updateProductByJdList(List<Integer> profitList, List<Long> productIdList, List<Long> standardProductUnitIdList, List<Long> skuIdList, List<Long> merchantProductIdList, List<Long> suIdList, List<Long> productUnitIdList, List<Long> puIdList, List<Long> pictureIdList, List<Long> attValueIdList, List<String> nameList, List<String> pictureUrlList, List<String> attValueList, List<Long> jdSkuIdList, List<BigDecimal> marketPriceList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList) {
		logger.error("开始处理京东商品的本地更新");
		productWriteService.updateProductList(productIdList,nameList);
		pictureWriteService.updatePictureList(pictureIdList,pictureUrlList);
		attributeValueWriteService.updateAttributeValueList(attValueIdList,attValueList);
		standardProductUnitWriteService.updateStandardProductUnitList(standardProductUnitIdList,nameList);
		skuWriteService.updateSkuList(skuIdList,nameList,jdSkuIdList);
		merchantProductWriteService.updateMerchantProductList(profitList,merchantProductIdList,nameList,marketPriceList,salePriceList,demoSalePriceList,competingSalePriceList);
		standardUnitWriteService.updateSUList(profitList,suIdList,nameList,marketPriceList,salePriceList,demoSalePriceList,competingSalePriceList);
		//suSerachRuleWriteService.updateSuSerachRuleList(suIdList,nameList);
		productUnitWriteService.updateProductUnitList(productUnitIdList,nameList,salePriceList,demoSalePriceList,competingSalePriceList);
		commodityProductUnitWriteService.updateCommodityProductUnitList(puIdList,nameList,salePriceList,demoSalePriceList,competingSalePriceList);

	}

    public void updateJdProductAllDown() {
		standardUnitWriteService.updateSuAndPuStatusByJd();
		merchantProductWriteService.updateSuAndPuStatusByJd();
    }

	public void updateJdProductStatusByProfit(Integer productLimitProfit) {
		standardUnitWriteService.updateJdProductStatusByProfit(productLimitProfit);
		merchantProductWriteService.updateJdProductStatusByProfit(productLimitProfit);

	}

	public Integer findJdProductCountByProfit(Integer profit) {
		return jdProductReadService.findJdProductCountByProfit(profit);
	}

	public List<JdProductDTO> findJdProductListByProfit(Integer profit, Integer start, Integer pagSize) {
		return jdProductReadService.findJdProductListByProfit(profit,start,pagSize);
	}

    public void updateJdProductShowStatus(Long skuId, Integer status) {
		JdProductDTO dto = new JdProductDTO();
		dto.setId(skuId);
		dto.setIsShow(status);
		jdProductWriteService.updateJdProductWithTx(dto);
	}

	public void updateProductCreateTime(List<Long> jdId) {
		jdProductWriteService.updateProductCreateTime(jdId);
	}

	/**
	 * 查询京东商品价格
	 * @param token
	 * @param skuIds
	 */
	public Map<Long, JSONObject> getJdSellPrice(String token, List<Long> skuIds){
		Map<Long, JSONObject> priceMap=new HashMap<>();
		List<List<Long>> skuIdsList= FHCollectionUtils.splitList(skuIds,100);
		int i=0;
		for (List<Long> skuIdList:skuIdsList){
			if(i!=0 && (i%100 == 0)){
				//token= jdUtils.getAccessToken(cache);
				token= jdUtils2.getAccessToken(cache);
				i++;
			}
			JSONArray priceResultObj = jdUtils.getSellPrice(token, skuIdList);

			if (EmptyUtil.isNotEmpty(priceResultObj)) {
				if(priceResultObj!=null && priceResultObj.size()>0) {
					for(int index=0;index<priceResultObj.size();index++) {
						JSONObject ob = (JSONObject) priceResultObj.get(index);
						Long priceSkuId = ob.getLong("skuId");
						priceMap.put(priceSkuId, ob);
					}
				}
			} else {
				logger.error("查询商品价格失败,sku={}", skuIdList.toString());
			}
		}
		return priceMap;
	}

	/**
	 * 查询京东商品状态
	 * @param skuIds
	 * @return
	 */
	public Map<Long,Integer> getJdSkuState(List<Long> skuIds){
		Map<Long,Integer> skuStateMap=new HashMap<>();
		List<List<Long>> skuIdsList= FHCollectionUtils.splitList(skuIds,100);
		for (List<Long> skuIdList:skuIdsList){
			Map<Long,Integer> stateMap=jdUtils.getSkuState(cache, skuIdList);
			if (Objects.nonNull(skuStateMap) && Objects.nonNull(stateMap) && !stateMap.isEmpty()){
				skuStateMap.putAll(stateMap);
			}
		}
		return skuStateMap;
	}
}
