package com.egeo.components.product.manage.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.condition.MerchantProductCondition;
import com.egeo.components.product.condition.PictureCondition;
import com.egeo.components.product.condition.SkuCondition;
import com.egeo.components.product.condition.StandardProductUnitCondition;
import com.egeo.components.product.dao.read.CommodityProductUnitReadDAO;
import com.egeo.components.product.dao.read.MerchantProdClientReadDAO;
import com.egeo.components.product.dao.read.MerchantProdDescribeReadDAO;
import com.egeo.components.product.dao.read.MerchantProdPictureReadDAO;
import com.egeo.components.product.dao.read.MerchantProductCompanyReadDAO;
import com.egeo.components.product.dao.read.MerchantProductMembershipReadDAO;
import com.egeo.components.product.dao.read.MerchantProductReadDAO;
import com.egeo.components.product.dao.read.MerchantProductStoreReadDAO;
import com.egeo.components.product.dao.read.MerchantProductTagReadDAO;
import com.egeo.components.product.dao.read.MpSerachKeywordReadDAO;
import com.egeo.components.product.dao.read.ProductUnitReadDAO;
import com.egeo.components.product.dao.read.SkuReadDAO;
import com.egeo.components.product.dao.read.StandardProductUnitReadDAO;
import com.egeo.components.product.dao.read.StandardUnitReadDAO;
import com.egeo.components.product.dao.read.StandardUnitStoreReadDAO;
import com.egeo.components.product.dao.write.CommodityProductUnitWriteDAO;
import com.egeo.components.product.dao.write.MerchantPictureWriteDAO;
import com.egeo.components.product.dao.write.MerchantProdClientWriteDAO;
import com.egeo.components.product.dao.write.MerchantProdDescribeWriteDAO;
import com.egeo.components.product.dao.write.MerchantProdPictureWriteDAO;
import com.egeo.components.product.dao.write.MerchantProductCompanyWriteDAO;
import com.egeo.components.product.dao.write.MerchantProductMembershipWriteDAO;
import com.egeo.components.product.dao.write.MerchantProductStoreWriteDAO;
import com.egeo.components.product.dao.write.MerchantProductTagWriteDAO;
import com.egeo.components.product.dao.write.MerchantProductWriteDAO;
import com.egeo.components.product.dao.write.MpSerachKeywordWriteDAO;
import com.egeo.components.product.dao.write.PictureWriteDAO;
import com.egeo.components.product.dao.write.ProductUnitWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitClientRecordWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitClientWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitCompanyRecordWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitCompanyWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitDescribeRecordWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitMembershipWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitPictureRecordWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitPictureWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitRecordMembershipWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitRecordStoreWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitRecordWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitStoreWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitTagRecordWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitTagWriteDAO;
import com.egeo.components.product.dao.write.StandardUnitWriteDAO;
import com.egeo.components.product.dao.write.StoreMenuNodeStandardUnitWriteDAO;
import com.egeo.components.product.dao.write.SuSerachKeywordWriteDAO;
import com.egeo.components.product.dao.write.SurSerachKeywordWriteDAO;
import com.egeo.components.product.enums.SUConstant;
import com.egeo.components.product.manage.read.PictureReadManage;
import com.egeo.components.product.manage.read.SkuReadManage;
import com.egeo.components.product.manage.write.MerchantProductWriteManage;
import com.egeo.components.product.manage.write.StandardUnitDescribeWriteManage;
import com.egeo.components.product.manage.write.StandardUnitPictureWriteManage;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.po.MerchantPicturePO;
import com.egeo.components.product.po.MerchantProdClientPO;
import com.egeo.components.product.po.MerchantProdDescribePO;
import com.egeo.components.product.po.MerchantProdPicturePO;
import com.egeo.components.product.po.MerchantProductCompanyPO;
import com.egeo.components.product.po.MerchantProductMembershipPO;
import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.components.product.po.MerchantProductStorePO;
import com.egeo.components.product.po.MerchantProductTagPO;
import com.egeo.components.product.po.MpSerachKeywordPO;
import com.egeo.components.product.po.PicturePO;
import com.egeo.components.product.po.ProductUnitPO;
import com.egeo.components.product.po.SkuPO;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.components.product.po.StandardUnitClientPO;
import com.egeo.components.product.po.StandardUnitClientRecordPO;
import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.components.product.po.StandardUnitCompanyRecordPO;
import com.egeo.components.product.po.StandardUnitDescribePO;
import com.egeo.components.product.po.StandardUnitDescribeRecordPO;
import com.egeo.components.product.po.StandardUnitMembershipPO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.components.product.po.StandardUnitPicturePO;
import com.egeo.components.product.po.StandardUnitPictureRecordPO;
import com.egeo.components.product.po.StandardUnitRecordMembershipPO;
import com.egeo.components.product.po.StandardUnitRecordPO;
import com.egeo.components.product.po.StandardUnitRecordStorePO;
import com.egeo.components.product.po.StandardUnitStorePO;
import com.egeo.components.product.po.StandardUnitTagPO;
import com.egeo.components.product.po.StandardUnitTagRecordPO;
import com.egeo.components.product.po.SuSerachKeywordPO;
import com.egeo.components.product.po.SurSerachKeywordPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.str.StringUtils;

@Service
public class MerchantProductWriteManageImpl implements MerchantProductWriteManage {
	private static final XLogger logger = XLogger.getLogger(MerchantProductWriteManageImpl.class);
	@Autowired
	private MerchantProductWriteDAO merchantProductWriteDAO;
	
	@Autowired
	private StandardProductUnitReadDAO standardProductUnitReadDAO;
	
	@Autowired
	private PictureWriteDAO pictureWriteDAO;
	
	@Autowired
	private MerchantPictureWriteDAO merchantPictureWriteDAO;
	
	@Autowired
	private MerchantProdPictureWriteDAO merchantProdPictureWriteDAO;
	
	@Autowired
	private MerchantProdClientWriteDAO merchantProdClientWriteDAO;
	
	@Autowired
	private MerchantProductCompanyWriteDAO merchantProductCompanyWriteDAO;
	
	@Autowired
	private MerchantProdDescribeWriteDAO merchantProdDescribeWriteDAO;
	
	@Autowired
	private SkuReadDAO skuReadDAO;
	
	@Autowired
	private ProductUnitWriteDAO productUnitWriteDAO;
	
	@Autowired
	private MerchantProductTagWriteDAO merchantProductTagWriteDAO;
	
	@Autowired
	private MpSerachKeywordWriteDAO mpSerachKeywordWriteDAO;
	
	@Autowired
	private MerchantProductReadDAO merchantProductReadDAO;
	
	@Autowired
	private StandardUnitReadDAO standardUnitReadDAO;
	
	@Autowired
	private StandardUnitWriteDAO standardUnitWriteDAO;
	
	@Autowired
	private StandardUnitRecordWriteDAO standardUnitRecordWriteDAO;
	
	@Autowired
	private MerchantProdDescribeReadDAO merchantProdDescribeReadDAO;
	
	@Autowired
	private StandardUnitDescribeWriteManage standardUnitDescribeWriteManage;
	
	@Autowired
	private StandardUnitDescribeRecordWriteDAO standardUnitDescribeRecordWriteDAO;
	
	@Autowired
	private MerchantProdPictureReadDAO merchantProdPictureReadDAO;
	
	@Autowired
	private StandardUnitPictureWriteDAO standardUnitPictureWriteDAO;
	
	@Autowired
	private StandardUnitPictureRecordWriteDAO standardUnitPictureRecordWriteDAO;
	
	@Autowired
	private MerchantProdClientReadDAO merchantProdClientReadDAO;
	
	@Autowired
	private StandardUnitClientWriteDAO standardUnitClientWriteDAO;
	
	@Autowired
	private StandardUnitClientRecordWriteDAO standardUnitClientRecordWriteDAO;
	
	@Autowired
	private MerchantProductCompanyReadDAO merchantProductCompanyReadDAO;
	
	@Autowired
	private StandardUnitCompanyWriteDAO standardUnitCompanyWriteDAO;
	
	@Autowired
	private StandardUnitCompanyRecordWriteDAO standardUnitCompanyRecordWriteDAO;
	
	@Autowired
	private MerchantProductTagReadDAO merchantProductTagReadDAO;
	
	@Autowired
	private StandardUnitTagWriteDAO standardUnitTagWriteDAO;
	
	@Autowired
	private StandardUnitTagRecordWriteDAO standardUnitTagRecordWriteDAO;
	
	@Autowired
	private ProductUnitReadDAO productUnitReadDAO;
	
	@Autowired
	private SkuReadManage skuReadManage;
	
	@Autowired
	private CommodityProductUnitWriteDAO commodityProductUnitWriteDAO;
	
	@Autowired
	private CommodityProductUnitReadDAO commodityProductUnitReadDAO;
	
	@Autowired
	private PictureReadManage pictureReadManage;
	
	@Autowired
	private StandardUnitPictureWriteManage standardUnitPictureWriteManage;
	
	@Autowired
	private MpSerachKeywordReadDAO mpSerachKeywordReadDAO;
	
	@Autowired
	private SuSerachKeywordWriteDAO suSerachKeywordWriteDAO;
	
	@Autowired
	private SurSerachKeywordWriteDAO surSerachKeywordWriteDAO;
	
	@Autowired
	private MerchantProductStoreWriteDAO merchantProductStoreWriteDAO;
	
	@Autowired
	private MerchantProductMembershipWriteDAO merchantProductMembershipWriteDAO;
	
	@Autowired
	private MerchantProductStoreReadDAO merchantProductStoreReadDAO;
	
	@Autowired
	private StandardUnitStoreWriteDAO standardUnitStoreWriteDAO;
	
	@Autowired
	private StandardUnitRecordStoreWriteDAO standardUnitRecordStoreWriteDAO;
	
	@Autowired
	private MerchantProductMembershipReadDAO merchantProductMembershipReadDAO;
	
	@Autowired
	private StandardUnitMembershipWriteDAO standardUnitMembershipWriteDAO;
	
	@Autowired
	private StandardUnitRecordMembershipWriteDAO standardUnitRecordMembershipWriteDAO;
	
	@Autowired
	private StandardUnitStoreReadDAO standardUnitStoreReadDAO;
	
	@Autowired
	private StoreMenuNodeStandardUnitWriteDAO storeMenuNodeStandardUnitWriteDAO;

	@Override
	public Long insertMerchantProductWithTx(MerchantProductPO po,
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
			List<Long> membershipIds) {
		
		//根据spuid查询spu信息
		StandardProductUnitPO standardProductUnitPO = new StandardProductUnitPO();
		standardProductUnitPO.setId(po.getStandardProductUnitId());
		StandardProductUnitPO standardProductUnitPO2 = standardProductUnitReadDAO.findById(standardProductUnitPO);
		po.setProductCategory(standardProductUnitPO2.getProductCategory());
		if(standardProductUnitPO2.getEnterpriseId()!=null) {
			po.setEnterpriseId(standardProductUnitPO2.getEnterpriseId());
		}
		if(standardProductUnitPO2.getSupplierId()!=null) {
			po.setSupplierId(standardProductUnitPO2.getSupplierId());
		}
		if(standardProductUnitPO2.getCommodityTemplateId().equals(1L)){
			double length = getLength(po.getName());
			if (length > 8) {
				throw new BusinessException("商品名称不能超过8位");
			}
		}

		MerchantProductPO merchantProductPO = new MerchantProductPO();
		merchantProductPO.setName(po.getName());
		merchantProductPO.setPlatformId(po.getPlatformId());
		List<MerchantProductPO> merchantProductPOList = merchantProductReadDAO.findAll(merchantProductPO,null);
		for ( int i = 0; i < merchantProductPOList.size() ; i++) {
			if (merchantProductPO.getName().equals(merchantProductPOList.get(i).getName()) && merchantProductPO.getPlatformId() == merchantProductPOList.get(i).getPlatformId()) {
				logger.info("商品名称重复,是:"+merchantProductPOList.get(i).getName());
				throw new BusinessException("商品名称重复！");

			}else {
				logger.info("insert商品名称不重复！");
			}
		}

		int i ;
		try {
				i = merchantProductWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		Long merchantProductId = po.getId();
		// 保存su商品图片信息 su商品图片类型：1、列表图片 2、轮播图片 3、web轮播图
		saveSUPicture(merchantProductId,picture,1,po.getMerchantId(),po.getPlatformId());
		// 如果app轮播图不为空则循环添加
		if(EmptyUtil.isNotEmpty(pictures)){
			for (String picture_ : pictures) {
				saveSUPicture(merchantProductId,picture_,2,po.getMerchantId(),po.getPlatformId());
			}
		}
		// 如果app轮播图不为空则循环添加
		if(EmptyUtil.isNotEmpty(webBannerPictures)){
			for (String picture_ : webBannerPictures) {
				saveSUPicture(merchantProductId,picture_,3,po.getMerchantId(),po.getPlatformId());
			}
		}
		
		// 批量保存su商品客户端关系信息
		saveClients(merchantProductId,clients);
		// 批量保存su商品公司关系信息
		saveCompanys(merchantProductId,companys,demoCompanys,competingCompanys);
		// 批量保存su商品标签关系信息
		saveTags(merchantProductId,tagList);
		// 批量保存su商品和门店信息
		saveStoreIds(merchantProductId,storeIds,po.getPlatformId());
		// 批量保存su商品和会籍信息
		saveMembershipIds(merchantProductId,membershipIds,po.getPlatformId());
		
		MerchantProdDescribePO merchantProdDescribePO = new MerchantProdDescribePO();
		merchantProdDescribePO.setContent(content);
		merchantProdDescribePO.setMerchantProductId(merchantProductId);
		merchantProdDescribePO.setPlatformId(po.getPlatformId());
		merchantProdDescribeWriteDAO.insert(merchantProdDescribePO);
		
		// 批量保存pu信息
		logger.info("merchantProductId~~~~~~~~"+merchantProductId);
		saveProductUnitList(merchantProductId,po.getStandardProductUnitId(),productUnitList,po.getPlatformId(),companys,demoCompanys,competingCompanys);

		// 保存su草稿关键词信息,引用spu关键词、并且不为空
		if(EmptyUtil.isNotEmpty(po.getIsSpuKeyword()))
			// 是否引用关键词: 1.不使用搜索关键词、2.引用产品关键词并添加自定义关键词、3.仅引用产品关键词、4.仅添加自定义关键词
			if(po.getIsSpuKeyword() == 2 || po.getIsSpuKeyword() == 4)
				if(EmptyUtil.isNotEmpty(keywords))
					saveKeywords(merchantProductId,keywords,po.getPlatformId());
		return merchantProductId;
	}

	/**
	 * 批量保存su商品和会籍信息(会籍不会太多，后期慢了可以批量导入)
	 * @param merchantProductId
	 * @param membershipIds
	 * @param platformId
	 */
	private void saveMembershipIds(Long merchantProductId, List<Long> membershipIds, Long platformId) {
		if(EmptyUtil.isNotEmpty(membershipIds)){
			for (Long membershipId : membershipIds) {
				MerchantProductMembershipPO merchantProductMembershipPO = new MerchantProductMembershipPO();
				merchantProductMembershipPO.setMembershipId(membershipId);
				merchantProductMembershipPO.setMerchantProductId(merchantProductId);
				merchantProductMembershipPO.setPlatformId(platformId);
				merchantProductMembershipWriteDAO.insert(merchantProductMembershipPO);
			}
		}
		
	}
	/**
	 * 批量保存su商品和门店信息(门店不会太多，后期慢了可以批量导入)
	 * @param merchantProductId
	 * @param storeIds
	 */
	private void saveStoreIds(Long merchantProductId, List<Long> storeIds,Long platformId) {
		if(EmptyUtil.isNotEmpty(storeIds)){
			for (Long storeId : storeIds) {
				MerchantProductStorePO merchantProductStorePO = new MerchantProductStorePO();
				merchantProductStorePO.setMerchantProductId(merchantProductId);
				merchantProductStorePO.setStoreId(storeId);
				merchantProductStorePO.setPlatformId(platformId);
				merchantProductStoreWriteDAO.insert(merchantProductStorePO);
			}
		}
		
	}
	/**
	 * 保存su商品图片信息
	 * @param merchantProductId su商品id
	 * @param picture
	 * @param type su商品图片类型：1、列表图片 2、轮播图片 3、web轮播图
	 * @param merchantId 商家id
	 * @param PlatformId 平台id
	 */
	private void saveSUPicture(Long merchantProductId,String picture, int type,Long merchantId, Long PlatformId) {
		// 添加su图片信息
		PicturePO picturePO = new PicturePO();
		picturePO.setUrl(picture);
		picturePO.setSortValue(1);
		picturePO.setPlatformId(PlatformId);
		pictureWriteDAO.insert(picturePO);
		
		// 添加su商家关系表
		MerchantPicturePO merchantPicturePO = new MerchantPicturePO();
		merchantPicturePO.setMerchantId(merchantId);
		merchantPicturePO.setPictureId(picturePO.getId());
		merchantPicturePO.setPlatformId(PlatformId);
		merchantPictureWriteDAO.insert(merchantPicturePO);
		
		// 添加su图片关系表
		MerchantProdPicturePO merchantProdPicturePO = new MerchantProdPicturePO();
		merchantProdPicturePO.setMerchantPictureId(merchantPicturePO.getId());
		merchantProdPicturePO.setMerchantProdId(merchantProductId);
		merchantProdPicturePO.setType(type);
		merchantProdPicturePO.setPlatformId(PlatformId);
		merchantProdPictureWriteDAO.insert(merchantProdPicturePO);
	}
	/**
	 * 批量保存pu信息
	 * @param standardProductUnitId
	 * @param productUnitList
	 */
	private void saveProductUnitList(Long merchantProductId,Long standardProductUnitId, List<ProductUnitPO> productUnitList,Long platformId,
									 List<Long> companys,List<Long> demoCompanys,List<Long> competingCompanys) {
		// 根据spuid查询sku信息
		SkuPO skuPO = new SkuPO();
		skuPO.setStandardProductUnitId(standardProductUnitId);
		List<SkuCondition> skuConditionList = skuReadDAO.findSkuAll(skuPO,null);

		for (SkuCondition skuCondition : skuConditionList) {
			if (EmptyUtil.isNotEmpty(productUnitList)) {
				for (ProductUnitPO productUnitPO : productUnitList) {
					if (skuCondition.getId().equals(productUnitPO.getSkuId())) {
						productUnitPO.setProductUnitSerialNumber(skuCondition.getSkuSerialNumber());
						//productUnitPO.setName(skuCondition.getSkuName());
						productUnitPO.setCode(skuCondition.getCode());
						// 保存pu信息
						productUnitPO.setMerchantProductId(merchantProductId);
						// 前端传递数据默认为可销售
						if (productUnitPO.isChecked()) {
							if (EmptyUtil.isNotEmpty(companys) ){
								if (EmptyUtil.isEmpty(productUnitPO.getSalePrice())
										|| productUnitPO.getSalePrice().doubleValue() <= 0) {
									throw new BusinessException("pu设置为有效后正式公司售价不能为空或小于0");
								}
							}
							if (EmptyUtil.isNotEmpty(demoCompanys) ){
								if (EmptyUtil.isEmpty(productUnitPO.getDemoSalePrice())
										|| productUnitPO.getDemoSalePrice().doubleValue() <= 0) {
									throw new BusinessException("pu设置为有效后演示公司售价不能为空或小于0");
								}
							}
							if (EmptyUtil.isNotEmpty(competingCompanys) ){
								if (EmptyUtil.isEmpty(productUnitPO.getCompetingSalePrice())
										|| productUnitPO.getCompetingSalePrice().doubleValue() <= 0) {
									throw new BusinessException("pu设置为有效后竞品公司售价不能为空或小于0");
								}
							}
							logger.info("开始插入pu名称");
							if (EmptyUtil.isNotBlank(productUnitPO.getName())) {
								Double length = getLength(productUnitPO.getName());
								/*if (length > 30) {
									throw new BusinessException("pu名称不能超过30字");
								}*/
							} else {
								logger.info("pu为空");
								throw new BusinessException("pu名称不能为空");
							}
							productUnitPO.setIsVendible(1);
						} else {
							productUnitPO.setIsVendible(0);
						}

						// 如果前端传递过来的参数为空，默认为sku图片
						/*
						 * if(EmptyUtil.isEmpty(productUnitPO.getPuPicUrl())){
						 * productUnitPO.setPuPicUrl(skuCondition.getSkuPicUrl()
						 * ); }
						 */

						productUnitPO.setPlatformId(platformId);
						productUnitWriteDAO.insert(productUnitPO);
						break;
					}

				}
			}
		}
	}

	private double getLength(String str) {
		double valueLength = 0;
		String chinese = "[\\\\u4e00-\\\\u9fa5]";
		for (int i = 0; i < str.length(); i++) {
			// 获取一个字符
			String temp = str.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 中文字符长度为1
				valueLength += 1;
			} else {
				// 其他字符长度为0.5
				valueLength += 0.5;
			}
		}
		//进位取整
		return Math.ceil(valueLength);
	}
	/**
	 * 批量保存su商品标签关系信息
	 * @param tagList
	 */
	private void saveTags(Long merchantProductId,List<Long> tagList) {
		List<MerchantProductTagPO> merchantProductTags = new ArrayList<>();
		//批量保存商品标签关系
		for (Long  tagId : tagList) {
			MerchantProductTagPO merchantProductTagPO = new MerchantProductTagPO();
			merchantProductTagPO.setTagId(tagId);
			merchantProductTagPO.setMerchantProductId(merchantProductId);
			merchantProductTags.add(merchantProductTagPO);
		}
		if(EmptyUtil.isNotEmpty(merchantProductTags))
			merchantProductTagWriteDAO.insertAll(merchantProductTags);
		
	}
	/**
	 * 批量保存su商品公司关系信息
	 * @param merchantProductId
	 * @param companys
	 */
	private void saveCompanys(Long merchantProductId, List<Long> companys,List<Long> demoCompanys,List<Long> competingCompanys) {
		List<MerchantProductCompanyPO> merchantProductCompanys = new ArrayList<>();
		// 正式公司集合 正式公司类型为0
		if(EmptyUtil.isNotEmpty(companys)){
			for (Long companyId : companys) {
				// 添加su公司关系
				MerchantProductCompanyPO merchantProductCompanyPO = new MerchantProductCompanyPO();
				merchantProductCompanyPO.setMerchantProductId(merchantProductId);
				merchantProductCompanyPO.setCompanyId(companyId);
				merchantProductCompanyPO.setCompanyType(0);
				merchantProductCompanys.add(merchantProductCompanyPO);
			}
		}
		
		// 演示公司集合 演示公司类型为1
		if(EmptyUtil.isNotEmpty(demoCompanys)){
			for (Long companyId : demoCompanys) {
				// 添加su公司关系
				MerchantProductCompanyPO merchantProductCompanyPO = new MerchantProductCompanyPO();
				merchantProductCompanyPO.setMerchantProductId(merchantProductId);
				merchantProductCompanyPO.setCompanyId(companyId);
				merchantProductCompanyPO.setCompanyType(1);
				merchantProductCompanys.add(merchantProductCompanyPO);
			}
		}
		
		// 竞品公司集合 竞品公司类型为2
		if(EmptyUtil.isNotEmpty(competingCompanys)){
			for (Long companyId : competingCompanys) {
				// 添加su公司关系
				MerchantProductCompanyPO merchantProductCompanyPO = new MerchantProductCompanyPO();
				merchantProductCompanyPO.setMerchantProductId(merchantProductId);
				merchantProductCompanyPO.setCompanyId(companyId);
				merchantProductCompanyPO.setCompanyType(2);
				merchantProductCompanys.add(merchantProductCompanyPO);
			}
		}
		
		// 批量保存su商品公司信息
		merchantProductCompanyWriteDAO.insertAll(merchantProductCompanys);
	}
	/**
	 * 批量保存su商品客户端关系信息
	 * @param merchantProductId
	 *
	 */
	private void saveClients(Long merchantProductId, List<Long> clients) {
		List<MerchantProdClientPO> merchantProdClients = new ArrayList<>();
		for (Long clientId : clients) {
			// 添加su客户端关系
			MerchantProdClientPO merchantProdClientPO = new MerchantProdClientPO();
			merchantProdClientPO.setClientId(clientId);
			merchantProdClientPO.setMerchantProductId(merchantProductId);
			merchantProdClients.add(merchantProdClientPO);
		}
		// 批量保存su商品客户端关系信息
		if(EmptyUtil.isNotEmpty(merchantProdClients))
			merchantProdClientWriteDAO.insertAll(merchantProdClients);
	}
	/**
	 * 保存spu关键词
	 * @param merchantProductId su草稿id
	 * @param keywords 关键词集合
	 * @param platformId 平台id
	 */
	private void saveKeywords(Long merchantProductId, List<String> keywords,Long platformId) {
		List<MpSerachKeywordPO> mpSerachKeywords = new ArrayList<>();
		for (String keyword : keywords) {
			MpSerachKeywordPO mpSerachKeywordPO = new MpSerachKeywordPO();
			mpSerachKeywordPO.setKeyword(keyword);
			mpSerachKeywordPO.setMerchantProductId(merchantProductId);
			mpSerachKeywordPO.setPlatformId(platformId);
			mpSerachKeywords.add(mpSerachKeywordPO);
		}
		// 批量保存su商品关键词
		if(EmptyUtil.isNotEmpty(mpSerachKeywords))
			mpSerachKeywordWriteDAO.insertAll(mpSerachKeywords);
	}

	@Override
	public int updateMerchantProductWithTx(
			MerchantProductPO po,
			List<String> keywords,
			List<Long> clients,
			List<Long> tagList,
			List<Long> companys,
			List<Long> demoCompanys,
			List<Long> competingCompanys,
			List<String> webBannerPictures,
			List<Long> storeIdList,
			List<Long> membershipIdList) {


		MerchantProductPO merchantProductPO = new MerchantProductPO();
		merchantProductPO.setId(po.getId());
		merchantProductPO.setName(po.getName());
		merchantProductPO.setPlatformId(po.getPlatformId());

		MerchantProductPO oldMerchantProductPO = merchantProductReadDAO.findMerchantProductById(merchantProductPO);
		String oldName = oldMerchantProductPO.getName();
		List<MerchantProductPO> merchantProductPOList = merchantProductReadDAO.findAll(merchantProductPO,null);
		if (!oldName.equals(merchantProductPO.getName())) {
			logger.info("oldName = "+oldName+",po.getName = "+merchantProductPO.getName());
			for ( int i = 0; i < merchantProductPOList.size() ; i++) {
				if (merchantProductPO.getName().equals(merchantProductPOList.get(i).getName()) && merchantProductPO.getPlatformId() == merchantProductPOList.get(i).getPlatformId()) {
					logger.info("商品名称重复,是:"+merchantProductPOList.get(i).getName());
					throw new BusinessException("商品名称重复！");
				}else {
					logger.info("update商品名称不重复！");
				}
			}
		}


		// 根据su商品草稿id更新su商品信息
		int i = merchantProductWriteDAO.update(po);

		// 批量更新与客户端关系信息
		updateClients(po.getId(),clients);
		// 批量更新与公司关系信息
		updateCompanys(po.getId(),companys,demoCompanys,competingCompanys);
		// 批量更新与标签关系信息
		updatetags(po.getId(),tagList);
		// 批量更新与门店关系信息
		updateStoreIds(po.getId(),storeIdList,po.getPlatformId());
		// 批量更新与会籍关系信息
		updateMembershipIds(po.getId(),membershipIdList,po.getPlatformId());
		
		// 更新su草稿关键词信息,引用spu关键词、并且不为空
		if(EmptyUtil.isNotEmpty(po.getIsSpuKeyword()))
			// 是否引用关键词: 1.不使用搜索关键词、2.引用产品关键词并添加自定义关键词、3.仅引用产品关键词、4.仅添加自定义关键词
			if(po.getIsSpuKeyword() == 2 || po.getIsSpuKeyword() == 4)
				if(EmptyUtil.isNotEmpty(keywords))
					updateKeywords(po.getId(),keywords,po.getPlatformId());
		
		// 根据su商品id删除suweb轮播图商品图片信息
		MerchantProdPicturePO merchantProdPicturePO = new MerchantProdPicturePO();
		merchantProdPicturePO.setMerchantProdId(po.getId());
		merchantProdPicturePO.setType(3);
		merchantProdPictureWriteDAO.deleteByPara(merchantProdPicturePO);
		
		// 如果app轮播图不为空则循环添加
		if(EmptyUtil.isNotEmpty(webBannerPictures)){
			for (String picture_ : webBannerPictures) {
				saveSUPicture(po.getId(),picture_,3,po.getMerchantId(),po.getPlatformId());
			}
		}
				
		return i;
	}
	/**
	 * 批量更新与会籍关系信息

	 * @param platformId
	 */
	private void updateMembershipIds(Long merchantProductId, List<Long> membershipIds, Long platformId) {
		// 根据su商品草稿id删除会籍关系信息
		MerchantProductMembershipPO merchantProductMembershipPO = new MerchantProductMembershipPO();
		merchantProductMembershipPO.setMerchantProductId(merchantProductId);
		merchantProductMembershipWriteDAO.deleteByPara(merchantProductMembershipPO);
		// 批量保存su商品和会籍信息
		saveMembershipIds(merchantProductId, membershipIds, platformId);
	}
	/**
	 * 批量更新与门店关系信息
	 * @param merchantProductId
	 * @param storeIdList
	 */
	private void updateStoreIds(Long merchantProductId, List<Long> storeIds,Long platformId) {
		// 根据su草稿id删除门店关系信息
		MerchantProductStorePO merchantProductStorePO = new MerchantProductStorePO();
		merchantProductStorePO.setMerchantProductId(merchantProductId);
		merchantProductStoreWriteDAO.deleteByPara(merchantProductStorePO);
		// 批量保存su商品和门店信息
		saveStoreIds(merchantProductId, storeIds, platformId);
	}
	/**
	 * 批量更新与标签关系信息
	 * @param tagList
	 */
	private void updatetags(Long merchantProductId, List<Long> tagList) {
		// 根据su商品草稿id删除与标签的信息
		MerchantProductTagPO merchantProductTagPO = new MerchantProductTagPO();
		merchantProductTagPO.setMerchantProductId(merchantProductId);
		merchantProductTagWriteDAO.deleteByPara(merchantProductTagPO);
		// 批量保存su商品标签关系信息
		saveTags(merchantProductId, tagList);
	}
	/**
	 * 批量关系与公司关系信息
	 * @param companys
	 */
	private void updateCompanys(Long merchantProductId, List<Long> companys,List<Long> demoCompanys,List<Long> competingCompanys) {
		// 根据su商品草稿id删除与公司信息
		MerchantProductCompanyPO merchantProductCompanyPO = new MerchantProductCompanyPO();
		merchantProductCompanyPO.setMerchantProductId(merchantProductId);
		merchantProductCompanyWriteDAO.deleteByPara(merchantProductCompanyPO);
		// 批量保存su商品公司关系信息
		saveCompanys(merchantProductId, companys,demoCompanys,competingCompanys);
		
	}
	private void updateClients(Long merchantProductId,List<Long> clients) {
		// 根据su商品草稿id删除su客户端关系信息
		MerchantProdClientPO merchantProdClientPO = new MerchantProdClientPO();
		merchantProdClientPO.setMerchantProductId(merchantProductId);
		merchantProdClientWriteDAO.deleteByPara(merchantProdClientPO);
		// 批量保存su商品客户端关系信息
		saveClients(merchantProductId, clients);
		
	}
	/**
	 * 更新su草稿关键词信息
	 * @param id
	 * @param keywords
	 * @param platformId
	 */
	private void updateKeywords(Long merchantProductId, List<String> keywords, Long platformId) {
		// 根据su商品草稿id删除关键词信息
		MpSerachKeywordPO mpSerachKeywordPO = new MpSerachKeywordPO();
		mpSerachKeywordPO.setMerchantProductId(merchantProductId);
		mpSerachKeywordPO.setPlatformId(platformId);
		mpSerachKeywordWriteDAO.deleteByPara(mpSerachKeywordPO);
		// 保存su草稿关键词信息,因为之前判断了空值、此处不用判断
		saveKeywords(merchantProductId,keywords,platformId);
	}
	@Override
	public int deleteMerchantProductWithTx(MerchantProductPO po) {
		int i;
		i = merchantProductWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 提交审核
	 * @param dto
	 * @return
	 */
	@Override
	public int submitAuditWithTx(MerchantProductPO po) {
		// TODO Auto-generated method stub
		return merchantProductWriteDAO.update(po);
	}
	/**
	 * 修改su草稿状态为已上架
	 * @param merchantProductDTO
	 * @return
	 */
	@Override
	public int updateStatus(MerchantProductPO po) {
		// TODO Auto-generated method stub
		return merchantProductWriteDAO.update(po);
	}

	@Override
	public int merchantProductPassWithTx(MerchantProductPO merchantProductPO) {
		// TODO Auto-generated method stub
		return merchantProductWriteDAO.update(merchantProductPO);
	}
	/**
	 * 根据su商品id通过审核
	 */
	@Override
	public List<Long> merchantProductPassWithTx(Long merchantProductId) {
		List<Long> puIdList = new ArrayList<>();
		MerchantProductPO merchantProductPO = new MerchantProductPO();
		merchantProductPO.setId(merchantProductId);
		// 根据su草稿id查询su草稿信息
		MerchantProductCondition merchantProductCondition = merchantProductReadDAO.findMerchantProductById(merchantProductPO);

		// 根据spuid查询spu信息及su序列号
		StandardProductUnitCondition standardProductUnitCondition = standardProductUnitReadDAO.findSerialNumberByspuId(merchantProductCondition.getStandardProductUnitId());

		// 根据su草稿id查询su信息
		StandardUnitPO standardUnitPO = new StandardUnitPO();
		standardUnitPO.setId(merchantProductId);
		StandardUnitPO standardUnitPO2 = standardUnitReadDAO.findById(standardUnitPO);
		if (EmptyUtil.isEmpty(standardUnitPO2)) {
			// 根据suId和商家id查询su信息
			StandardUnitPO standardUnitPO3 = new StandardUnitPO();
			standardUnitPO3.setMerchantId(merchantProductCondition.getMerchantId());
			standardUnitPO3.setName(merchantProductCondition.getName());
			/*List<StandardUnitPO> standardUnitList = standardUnitReadDAO.findAll(standardUnitPO3);
			if (EmptyUtil.isNotEmpty(standardUnitList)) {
				for ( int i = 0 ;i < standardUnitList.size() ; i++) {
					if (standardUnitPO3.getName().equals(standardUnitList.get(i).getName()) && standardUnitPO3.getPlatformId() == standardUnitList.get(i).getPlatformId()) {
						logger.info("审核时商品名称重复2!merchantProductCondition.getName()="+standardUnitPO3.getName()+",merchantProductPOList的Name="+standardUnitList.get(i).getName());
						logger.info("原PID="+standardUnitPO3.getPlatformId()+"List的Id="+standardUnitList.get(i).getPlatformId());
						throw new BusinessException("商品名称重复");
					}
				}
			}
		*/

			// 同步su商品信息
			// 默认为以下架
			synchronizationSU(merchantProductCondition,standardProductUnitCondition,SUConstant.SU_STATUS_RECEIVED_FINISHED.getStatus());
			// 同步su商品记录信息
			// 默认为以下架
			Long standardUnitRecordId = synchronizationSUR(merchantProductCondition,standardProductUnitCondition,SUConstant.SU_STATUS_RECEIVED_FINISHED.getStatus());
			
			// 根据su草稿id查询su草稿详情
			MerchantProdDescribePO merchantProdDescribePO = merchantProdDescribeReadDAO.findByMerchantProdId(merchantProductId);
			// 同步su商品详情信息
			synchronizationSUD(merchantProdDescribePO);
			// 同步su商品详情记录信息
			synchronizationSUDR(standardUnitRecordId,merchantProdDescribePO);

			// 根据su草稿id查询su草稿图片关系
			MerchantProdPicturePO merchantProdPicturePO = new MerchantProdPicturePO();
			merchantProdPicturePO.setMerchantProdId(merchantProductId);
			List<MerchantProdPicturePO> merchantProdPictureList = merchantProdPictureReadDAO.findAll(merchantProdPicturePO,null);
			// 同步su商品图片信息
			synchronizationSUP(merchantProdPictureList);
			// 同步su商品图片记录信息
			synchronizationSUPR(standardUnitRecordId,merchantProdPictureList);
			
			// 根据su草稿id查询su草稿所属平台关系
			MerchantProdClientPO merchantProdClientPO = new MerchantProdClientPO();
			merchantProdClientPO.setMerchantProductId(merchantProductId);
			List<MerchantProdClientPO> merchantProdClientList = merchantProdClientReadDAO.findAll(merchantProdClientPO,null);
			// 同步su平台关系
			synchronizationSUC(merchantProdClientList);
			// 同步su所属平台关系记录表
			synchronizationSUCR(standardUnitRecordId,merchantProdClientList);
			
			// 根据su草稿id查询su草稿公司关系
			MerchantProductCompanyPO merchantProductCompanyPO = new MerchantProductCompanyPO();
			merchantProductCompanyPO.setMerchantProductId(merchantProductId);
			List<MerchantProductCompanyPO> merchantProductCompanyList = merchantProductCompanyReadDAO.findAll(merchantProductCompanyPO,null);
			// 同步su公司关系信息
			synchronizationSUCompany(merchantProductCompanyList);
			// 同步su记录公司关系信息
			synchronizationSURCompany(standardUnitRecordId,merchantProductCompanyList);
			
			// 根据su草稿id查询su草稿标签信息
			MerchantProductTagPO merchantProductTagPO = new MerchantProductTagPO();
			merchantProductTagPO.setMerchantProductId(merchantProductId);
			List<MerchantProductTagPO> merchantProductTagList = merchantProductTagReadDAO.findAll(merchantProductTagPO,null);
			// 批量保存su标签关系信息
			synchronizationSUT(merchantProductTagList);
			// 批量保存su标签记录信息
			synchronizationSUTR(standardUnitRecordId,merchantProductTagList);
			
			// 根据su草稿id查询关键词信息
			MpSerachKeywordPO mpSerachKeywordPO = new MpSerachKeywordPO();
			mpSerachKeywordPO.setMerchantProductId(merchantProductId);
			mpSerachKeywordPO.setPlatformId(merchantProductCondition.getPlatformId());
			List<MpSerachKeywordPO> mpSerachKeywordList = mpSerachKeywordReadDAO.findAll(mpSerachKeywordPO,null);
			// 同步su关键词信息
			synchronizationSUK(mpSerachKeywordList);
			// 同步su关键词记录信息
			synchronizationSUKR(standardUnitRecordId,mpSerachKeywordList);
			
			// 根据su草稿id查询su商品草稿门店关系
			MerchantProductStorePO merchantProductStorePO = new MerchantProductStorePO();
			merchantProductStorePO.setMerchantProductId(merchantProductId);
			merchantProductStorePO.setPlatformId(merchantProductCondition.getPlatformId());
			List<MerchantProductStorePO> merchantProductStoreList = 
					merchantProductStoreReadDAO.findAll(merchantProductStorePO,null);
			// 同步su门店信息
			synchronizationSuStore(merchantProductId,merchantProductStoreList);
			// 同步su记录门店信息
			synchronizationSURStore(standardUnitRecordId,merchantProductStoreList);
			
			// 根据su草稿id查询su草稿会籍关系
			MerchantProductMembershipPO merchantProductMembershipPO = new MerchantProductMembershipPO();
			merchantProductMembershipPO.setMerchantProductId(merchantProductId);
			merchantProductMembershipPO.setPlatformId(merchantProductCondition.getPlatformId());
			List<MerchantProductMembershipPO> merchantProductMembershipList = 
					merchantProductMembershipReadDAO.findAll(merchantProductMembershipPO,null);
			// 同步su会籍信息
			synchronizationSuMembership(merchantProductId,merchantProductMembershipList);
			// 同步su记录会籍信息
			synchronizationSURMembership(standardUnitRecordId,merchantProductMembershipList);

			// 同步pu信息
			puIdList = synchronizationProductUnitWithTx(merchantProductId);
			// 将生成的su编号回写入草稿信息中
			MerchantProductPO merchantProductPO2 = new MerchantProductPO();
			merchantProductPO2.setId(merchantProductId);
			merchantProductPO2.setMerchantProductSerialNumber(standardProductUnitCondition.getProductSerialNumber()
					+ StringUtils.skuSerialNumber(standardProductUnitCondition.getSerialNumber() + 1));
			// 默认为以下架
			merchantProductPO2.setStatus(SUConstant.SU_STATUS_RECEIVED_FINISHED.getStatus());
			merchantProductWriteDAO.update(merchantProductPO2);
		} else {
			// 根据suId和商家id查询su信息
			StandardUnitPO standardUnitPO3 = new StandardUnitPO();
			standardUnitPO3.setMerchantId(merchantProductCondition.getMerchantId());
			standardUnitPO3.setName(merchantProductCondition.getName());

			MerchantProductPO merchantProductPO2 = new MerchantProductPO();
			merchantProductPO2.setId(merchantProductId);
			MerchantProductPO merchantProductPO3= merchantProductReadDAO.findById(merchantProductPO2);
			String oldName = merchantProductPO3.getName();

			List<StandardUnitPO> standardUnitList = standardUnitReadDAO.findAll(standardUnitPO3,null);
			if (!oldName.equals(standardUnitPO3.getName())){
				logger.info("oldName = "+oldName+",standardUnitPO3的Name = "+standardUnitPO3.getName());
				for ( int i = 0 ;i < standardUnitList.size() ; i++) {
					if (standardUnitPO3.getName().equals(standardUnitList.get(i).getName()) && standardUnitPO3.getPlatformId() == standardUnitList.get(i).getPlatformId()) {
						logger.info("审核时商品名称重复2!merchantProductCondition.getName()="+standardUnitPO3.getName()+",merchantProductPOList的Name="+standardUnitList.get(i).getName());
						logger.info("原PID="+standardUnitPO3.getPlatformId()+"List的Id="+standardUnitList.get(i).getPlatformId());
						throw new BusinessException("商品名称重复");
					}
				}
			}



			// 同步更新su信息
			synchronizationUpdateSU(merchantProductCondition);
			// 同步su商品记录信息
			// 默认为以下架
			Long standardUnitRecordId = synchronizationSUR(merchantProductCondition,standardProductUnitCondition,SUConstant.SU_STATUS_RECEIVED_FINISHED.getStatus());

			// 根据su草稿id查询su草稿详情
			MerchantProdDescribePO merchantProdDescribePO = merchantProdDescribeReadDAO.findByMerchantProdId(merchantProductId);
			// 同步更新su商品详情信息
			synchronizationUpdateSUD(merchantProdDescribePO);
			// 同步su商品详情记录信息
			synchronizationSUDR(standardUnitRecordId,merchantProdDescribePO);

			// 根据su草稿id修改su图片关系、su记录图片关系表
			updateStandardUnitPictureByIdWithTx(merchantProductId, standardUnitRecordId);

			// 根据su草稿id修改su所属平台关系，su记录所属平台关系
			updateStandardUnitClient(merchantProductId, standardUnitRecordId);

			// 根据su草稿id查询su草稿公司关系
			updateStandardUnitCompany(merchantProductId, standardUnitRecordId);

			// 根据su草稿id修改su标签关系信息
			updateStandardUnitTag(merchantProductId, standardUnitRecordId);
			
			// 根据su草稿id修改su关键词关系信息
			updateStandardUnitKeyword(merchantProductId,standardUnitRecordId,merchantProductCondition.getPlatformId());
			
			// 根据su草稿id修改su草稿门店信息
			updateStandardUnitStore(merchantProductId,standardUnitRecordId,merchantProductCondition.getPlatformId());
			
			// 根据su草稿id修改su草稿会籍信息
			updateStandardUnitMembership(merchantProductId,standardUnitRecordId,merchantProductCondition.getPlatformId());
			// 同步修改pu信息
			synchronizationUpdateProductUnitWithTx(merchantProductId);

			// 将su状态回写入草稿信息中
			/*MerchantProductPO merchantProductPO2 = new MerchantProductPO();
			merchantProductPO2.setId(merchantProductId);*/
			merchantProductPO2.setStatus(standardUnitPO2.getStatus());
			merchantProductWriteDAO.update(merchantProductPO2);
		}
		return puIdList;
	}
	/**
	 * 根据su草稿if修改su草稿会籍信息
	 * @param merchantProductId
	 * @param standardUnitRecordId
	 * @param platformId
	 */
	private void updateStandardUnitMembership(Long standardUnitId, Long standardUnitRecordId, Long platformId) {
		// 根据su草稿id删除会籍信息
		StandardUnitMembershipPO standardUnitMembershipPO = new StandardUnitMembershipPO();
		standardUnitMembershipPO.setStandardUnitId(standardUnitId);
		standardUnitMembershipPO.setPlatformId(platformId);
		standardUnitMembershipWriteDAO.deleteByPara(standardUnitMembershipPO);
		
		// 根据su草稿id查询su草稿会籍关系
		MerchantProductMembershipPO merchantProductMembershipPO = new MerchantProductMembershipPO();
		merchantProductMembershipPO.setMerchantProductId(standardUnitId);
		merchantProductMembershipPO.setPlatformId(platformId);
		List<MerchantProductMembershipPO> merchantProductMembershipList = 
				merchantProductMembershipReadDAO.findAll(merchantProductMembershipPO,null);
		// 同步su会籍信息
		synchronizationSuMembership(standardUnitId,merchantProductMembershipList);
		// 同步su记录会籍信息
		synchronizationSURMembership(standardUnitRecordId,merchantProductMembershipList);
		
	}
	/**
	 * 根据su草稿id修改su草稿门店信息
	 * @param merchantProductId
	 * @param standardUnitRecordId
	 * @param platformId
	 */
	private void updateStandardUnitStore(Long standardUnitId, Long standardUnitRecordId, Long platformId) {
		// 根据su草稿id删除草稿门店信息
		MerchantProductStorePO merchantProductStorePO = new MerchantProductStorePO();
		merchantProductStorePO.setMerchantProductId(standardUnitId);
		merchantProductStorePO.setPlatformId(platformId);
		List<MerchantProductStorePO> merchantProductStoreList = merchantProductStoreReadDAO.findAll(merchantProductStorePO,null);
		
		// su草稿商品门店id集合
		List<Long> merchantProductStoreIds = new ArrayList<>();
		for (MerchantProductStorePO merchantProductStorePO2 : merchantProductStoreList) {
			merchantProductStoreIds.add(merchantProductStorePO2.getStoreId());
		}
		
		StandardUnitStorePO standardUnitStorePO = new StandardUnitStorePO();
		standardUnitStorePO.setStandardUnitId(standardUnitId);
		standardUnitStorePO.setPlatformId(platformId);
		List<StandardUnitStorePO> standardUnitStoreList = standardUnitStoreReadDAO.findAll(standardUnitStorePO,null);
		
		// su商品门店id集合
		List<Long> standardUnitStoreIds = new ArrayList<>();
		for (StandardUnitStorePO standardUnitStorePO2 : standardUnitStoreList) {
			standardUnitStoreIds.add(standardUnitStorePO2.getStoreId());
		}
		
		// 新增su商品门店关系信息
		for (MerchantProductStorePO merchantProductStorePO2 : merchantProductStoreList) {
			// 如果正式su商品门店集合中不包含则新增
			if(!standardUnitStoreIds.contains(merchantProductStorePO2.getStoreId())){
				StandardUnitStorePO standardUnitStorePO2 = new StandardUnitStorePO();
				standardUnitStorePO2.setStandardUnitId(standardUnitId);
				standardUnitStorePO2.setStoreId(merchantProductStorePO2.getStoreId());
				standardUnitStorePO2.setPlatformId(platformId);
				standardUnitStoreWriteDAO.insert(standardUnitStorePO2);
			}
		}
		
		// 删除su商品门店关系信息
		for (StandardUnitStorePO standardUnitStorePO2 : standardUnitStoreList) {
			// 如果不包含则删除
			if(!merchantProductStoreIds.contains(standardUnitStorePO2.getStoreId())){
				standardUnitStoreWriteDAO.delete(standardUnitStorePO2);
				
				// 根据门店id商品id删除门店菜单商品关系
				storeMenuNodeStandardUnitWriteDAO.delByStoreIdStandardUnitId(
						standardUnitStorePO2.getStoreId(),standardUnitStorePO2.getStandardUnitId(),standardUnitStorePO2.getPlatformId());
			}
		}
		
		
		
		
		// 同步su记录门店信息
		synchronizationSURStore(standardUnitRecordId,merchantProductStoreList);
	}
	/**
	 * 同步su记录会籍信息(同门店)
	 * @param standardUnitRecordId
	 * @param merchantProductMembershipList
	 */
	private void synchronizationSURMembership(Long standardUnitRecordId,
			List<MerchantProductMembershipPO> merchantProductMembershipList) {
		for (MerchantProductMembershipPO merchantProductMembershipPO : merchantProductMembershipList) {
			StandardUnitRecordMembershipPO standardUnitRecordMembershipPO = new StandardUnitRecordMembershipPO();
			standardUnitRecordMembershipPO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitRecordMembershipPO.setMembershipId(merchantProductMembershipPO.getMembershipId());
			standardUnitRecordMembershipPO.setPlatformId(merchantProductMembershipPO.getPlatformId());
			standardUnitRecordMembershipWriteDAO.insert(standardUnitRecordMembershipPO);
		}
	}
	/**
	 * 同步su会籍信息(同门店)
	 * @param merchantProductId
	 * @param merchantProductMembershipList
	 */
	private void synchronizationSuMembership(Long standardUnitId,
			List<MerchantProductMembershipPO> merchantProductMembershipList) {
		for (MerchantProductMembershipPO merchantProductMembershipPO : merchantProductMembershipList) {
			StandardUnitMembershipPO standardUnitMembershipPO = new StandardUnitMembershipPO();
			standardUnitMembershipPO.setStandardUnitId(standardUnitId);
			standardUnitMembershipPO.setMembershipId(merchantProductMembershipPO.getMembershipId());
			standardUnitMembershipPO.setPlatformId(merchantProductMembershipPO.getPlatformId());
			standardUnitMembershipWriteDAO.insert(standardUnitMembershipPO);
		}
	}
	/**
	 * 同步su记录门店信息(后期如果慢了可以批量执行，目前不会同步很多门店)
	 * @param standardUnitRecordId
	 * @param merchantProductStoreList
	 */
	private void synchronizationSURStore(Long standardUnitRecordId,
			List<MerchantProductStorePO> merchantProductStoreList) {
		for (MerchantProductStorePO merchantProductStorePO : merchantProductStoreList) {
			StandardUnitRecordStorePO standardUnitRecordStorePO = new StandardUnitRecordStorePO();
			standardUnitRecordStorePO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitRecordStorePO.setStoreId(merchantProductStorePO.getStoreId());
			standardUnitRecordStorePO.setPlatformId(merchantProductStorePO.getPlatformId());
			standardUnitRecordStoreWriteDAO.insert(standardUnitRecordStorePO);
		}
	}
	/**
	 * 同步su门店信息(后期如果慢了可以批量执行，目前不会同步很多门店)
	 * @param merchantProductId
	 * @param merchantProductStoreList
	 */
	private void synchronizationSuStore(Long merchantProductId, List<MerchantProductStorePO> merchantProductStoreList) {
		for (MerchantProductStorePO merchantProductStorePO : merchantProductStoreList) {
			StandardUnitStorePO standardUnitStorePO = new StandardUnitStorePO();
			standardUnitStorePO.setStandardUnitId(merchantProductId);
			standardUnitStorePO.setStoreId(merchantProductStorePO.getStoreId());
			standardUnitStorePO.setPlatformId(merchantProductStorePO.getPlatformId());
			standardUnitStoreWriteDAO.insert(standardUnitStorePO);
		}
	}
	private void updateStandardUnitKeyword(Long merchantProductId, Long standardUnitRecordId,Long platformId) {
		// 根据su草稿id查询关键词信息
		MpSerachKeywordPO mpSerachKeywordPO = new MpSerachKeywordPO();
		mpSerachKeywordPO.setMerchantProductId(merchantProductId);
		mpSerachKeywordPO.setPlatformId(platformId);
		List<MpSerachKeywordPO> mpSerachKeywordList = mpSerachKeywordReadDAO.findAll(mpSerachKeywordPO,null);
		
		// 根据suId删除关键词信息
		SuSerachKeywordPO suSerachKeywordPO = new SuSerachKeywordPO();
		suSerachKeywordPO.setStandardUnitId(merchantProductId);
		suSerachKeywordPO.setPlatformId(platformId);
		suSerachKeywordWriteDAO.deleteByPara(suSerachKeywordPO);
		// 同步su关键词信息
		synchronizationSUK(mpSerachKeywordList);
		// 同步su关键词记录信息
		synchronizationSUKR(standardUnitRecordId,mpSerachKeywordList);
		
	}
	/**
	 * 同步su关键词记录信息
	 * @param standardUnitRecordId
	 * @param mpSerachKeywordList
	 */
	private void synchronizationSUKR(Long standardUnitRecordId, List<MpSerachKeywordPO> mpSerachKeywordList) {
		List<SurSerachKeywordPO> surSerachKeywords = new ArrayList<>();
		for (MpSerachKeywordPO mpSerachKeywordPO : mpSerachKeywordList) {
			SurSerachKeywordPO surSerachKeywordPO = new SurSerachKeywordPO();
			surSerachKeywordPO.setKeyword(mpSerachKeywordPO.getKeyword());
			surSerachKeywordPO.setStandardUnitRecordId(standardUnitRecordId);
			surSerachKeywordPO.setPlatformId(mpSerachKeywordPO.getPlatformId());
			surSerachKeywords.add(surSerachKeywordPO);
		}
		// 批量保存su关键词记录信息
		if(EmptyUtil.isNotEmpty(surSerachKeywords))
			surSerachKeywordWriteDAO.insertAll(surSerachKeywords);
	}
	/**
	 * 同步su关键词信息
	 * @param mpSerachKeywordList
	 */
	private void synchronizationSUK(List<MpSerachKeywordPO> mpSerachKeywordList) {
		List<SuSerachKeywordPO> suSerachKeywords = new ArrayList<>();
		for (MpSerachKeywordPO mpSerachKeywordPO : mpSerachKeywordList) {
			SuSerachKeywordPO suSerachKeywordPO = new SuSerachKeywordPO();
			suSerachKeywordPO.setStandardUnitId(mpSerachKeywordPO.getMerchantProductId());
			suSerachKeywordPO.setKeyword(mpSerachKeywordPO.getKeyword());
			suSerachKeywordPO.setPlatformId(mpSerachKeywordPO.getPlatformId());
			suSerachKeywords.add(suSerachKeywordPO);
		}
		// 批量保存su关键词信息
		if(EmptyUtil.isNotEmpty(suSerachKeywords))
			suSerachKeywordWriteDAO.insertAll(suSerachKeywords);
	}
	/**
	 * 同步根据草稿puid修改pu信息
	 * 
	 * @param merchantProductId
	 * @return
	 */
	private boolean synchronizationUpdateProductUnitWithTx(Long merchantProductId) {
		boolean isSucceed = false;
		// 根据su草稿id查询pu草稿信息
		ProductUnitPO productUnitPO = new ProductUnitPO();
		productUnitPO.setMerchantProductId(merchantProductId);
		List<ProductUnitPO> productUnitList = productUnitReadDAO.findAll(productUnitPO,null);
		for (ProductUnitPO productUnitPO2 : productUnitList) {
			// 同步pu信息
			CommodityProductUnitPO commodityProductUnitPO = new CommodityProductUnitPO();
			commodityProductUnitPO.setProductUnitId(productUnitPO2.getId());
			commodityProductUnitPO.setSkuId(productUnitPO2.getSkuId());
			commodityProductUnitPO.setStandardUnitId(productUnitPO2.getMerchantProductId());
			commodityProductUnitPO.setName(productUnitPO2.getName());
			commodityProductUnitPO.setRemark(productUnitPO2.getRemark());
			commodityProductUnitPO.setSalePrice(productUnitPO2.getSalePrice());
			commodityProductUnitPO.setCompetingSalePrice(productUnitPO2.getCompetingSalePrice());
			commodityProductUnitPO.setDemoSalePrice(productUnitPO2.getDemoSalePrice());
			commodityProductUnitPO.setStatus(productUnitPO2.getStatus());
			commodityProductUnitPO.setIsVendible(productUnitPO2.getIsVendible());
			commodityProductUnitPO.setCode(productUnitPO2.getCode());
			commodityProductUnitPO.setPuPicUrl(productUnitPO2.getPuPicUrl());
			commodityProductUnitPO.setPlatformId(productUnitPO2.getPlatformId());
			commodityProductUnitWriteDAO.updateCommodityProductUnitByProductUnitIdWithTx(commodityProductUnitPO);
		}
		isSucceed = true;
		return isSucceed;
	}
	
	private void updateStandardUnitTag(Long merchantProductId, Long standardUnitRecordId) {

		// 根据su草稿id查询su草稿标签信息
		MerchantProductTagPO merchantProductTagPO = new MerchantProductTagPO();
		merchantProductTagPO.setMerchantProductId(merchantProductId);
		List<MerchantProductTagPO> merchantProductTagList = merchantProductTagReadDAO.findAll(merchantProductTagPO,null);
		
		// 根据suid删除su标签关系信息
		StandardUnitTagPO standardUnitTagPO = new StandardUnitTagPO();
		standardUnitTagPO.setStandardUnitId(merchantProductId);
		standardUnitTagWriteDAO.deleteByPara(standardUnitTagPO);
		// 批量保存su标签关系信息
		synchronizationSUT(merchantProductTagList);
		// 批量保存su标签记录信息
		synchronizationSUTR(standardUnitRecordId,merchantProductTagList);
	}
	
	private boolean updateStandardUnitCompany(Long merchantProductId, Long standardUnitRecordId) {
		boolean isUpdateStandardUnitCompany = false;

		// 根据su草稿id查询su草稿公司关系
		MerchantProductCompanyPO merchantProductCompanyPO = new MerchantProductCompanyPO();
		merchantProductCompanyPO.setMerchantProductId(merchantProductId);
		List<MerchantProductCompanyPO> merchantProductCompanyList = merchantProductCompanyReadDAO.findAll(merchantProductCompanyPO,null);
		// 根据suid删除su公司关系信息
		StandardUnitCompanyPO standardUnitCompanyPO = new StandardUnitCompanyPO();
		standardUnitCompanyPO.setStandardUnitId(merchantProductId);
		standardUnitCompanyWriteDAO.deleteByPara(standardUnitCompanyPO);
		// 同步su公司关系信息
		synchronizationSUCompany(merchantProductCompanyList);
		// 同步su记录公司关系信息
		synchronizationSURCompany(standardUnitRecordId,merchantProductCompanyList);
		
		isUpdateStandardUnitCompany = true;
		return isUpdateStandardUnitCompany;
	}
	/**
	 * 修改su客户端关系
	 * 
	 * @param merchantProductId
	 * @param standardUnitRecordId
	 * @return
	 */
	private boolean updateStandardUnitClient(Long merchantProductId, Long standardUnitRecordId) {
		boolean isUpdateStandardUnitClient = false;

		// 根据su草稿id查询su草稿所属平台关系
		MerchantProdClientPO merchantProdClientPO = new MerchantProdClientPO();
		merchantProdClientPO.setMerchantProductId(merchantProductId);
		List<MerchantProdClientPO> merchantProdClientList = merchantProdClientReadDAO.findAll(merchantProdClientPO,null);
		
		// 根据suid删除su平台关系信息
		StandardUnitClientPO standardUnitClientPO = new StandardUnitClientPO();
		standardUnitClientPO.setStandardUnitId(merchantProductId);
		standardUnitClientWriteDAO.deleteByPara(standardUnitClientPO);
		// 同步su平台关系
		synchronizationSUC(merchantProdClientList);
		// 同步su所属平台关系记录表
		synchronizationSUCR(standardUnitRecordId,merchantProdClientList);

		isUpdateStandardUnitClient = true;
		return isUpdateStandardUnitClient;
	}
	private boolean updateStandardUnitPictureByIdWithTx(Long merchantProductId, Long standardUnitRecordId) {
		boolean isUpdateStandardUnitPicture = false;

		// su草稿id查询su草稿图片信息
		List<PictureCondition> pictures = pictureReadManage.findMPictureByMerchantProdId(merchantProductId);

		// 根据suid全部删除
		standardUnitPictureWriteManage.delByStandardUnitId(merchantProductId);

		for (PictureCondition pictureDto : pictures) {
				StandardUnitPicturePO standardUnitPicturePO = new StandardUnitPicturePO();
				standardUnitPicturePO.setMerchantPictureId(pictureDto.getMerchantPictureId());
				standardUnitPicturePO.setType(pictureDto.getType());
				standardUnitPicturePO.setPlatformId(pictureDto.getPlatformId());
				standardUnitPicturePO.setStandardUnitId(merchantProductId);
				standardUnitPictureWriteManage.insertStandardUnitPictureWithTx(standardUnitPicturePO);

			// 同步保存su记录表
			StandardUnitPictureRecordPO standardUnitPictureRecordPO = new StandardUnitPictureRecordPO();
			standardUnitPictureRecordPO.setMerchantPictureId(pictureDto.getMerchantPictureId());
			standardUnitPictureRecordPO.setType(pictureDto.getType());
			standardUnitPictureRecordPO.setPlatformId(pictureDto.getPlatformId());
			standardUnitPictureRecordPO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitPictureRecordWriteDAO.insert(standardUnitPictureRecordPO);
		}

		isUpdateStandardUnitPicture = true;
		return isUpdateStandardUnitPicture;
	}
	/**
	 * 同步更新su商品详情信息
	 * @param merchantProdDescribePO
	 */
	private void synchronizationUpdateSUD(MerchantProdDescribePO merchantProdDescribePO) {
		if (EmptyUtil.isNotEmpty(merchantProdDescribePO)) {
			// 根据suid同步su商品详情信息
			StandardUnitDescribePO standardUnitDescribePO = new StandardUnitDescribePO();
			standardUnitDescribePO.setStandardUnitId(merchantProdDescribePO.getMerchantProductId());
			standardUnitDescribePO.setContent(merchantProdDescribePO.getContent());
			standardUnitDescribePO.setPlatformId(merchantProdDescribePO.getPlatformId());
			standardUnitDescribeWriteManage.updateByStandardUnitIdWithTx(standardUnitDescribePO);
		}
		
	}
	private void synchronizationUpdateSU(MerchantProductCondition merchantProductCondition) {
		StandardUnitPO tar = new StandardUnitPO();
		tar.setId(merchantProductCondition.getId());
		tar.setMerchantId(merchantProductCondition.getMerchantId());
		tar.setStandardProductUnitId(merchantProductCondition.getStandardProductUnitId());
		tar.setMerchantCateTreeNodeId(merchantProductCondition.getMerchantCateTreeNodeId());
		tar.setMerchantSeriesId(merchantProductCondition.getMerchantSeriesId());
		tar.setName(merchantProductCondition.getName());
		tar.setAlias(merchantProductCondition.getAlias());
		tar.setIsVisible(merchantProductCondition.getIsVisible());
		tar.setSubtitle(merchantProductCondition.getSubtitle());
		tar.setSupplierId(merchantProductCondition.getSupplierId());
		tar.setType(merchantProductCondition.getType());
		tar.setRemark(merchantProductCondition.getRemark());
		tar.setSalePrice(merchantProductCondition.getSalePrice());
		tar.setSaleTaxRate(merchantProductCondition.getSaleTaxRate());
		tar.setReturnDays(merchantProductCondition.getReturnDays());
		tar.setReplacementDays(merchantProductCondition.getReplacementDays());
		tar.setGuaranteeDays(merchantProductCondition.getGuaranteeDays());
		tar.setIsVatInvoice(merchantProductCondition.getIsVatInvoice());
		tar.setIsVipCard(merchantProductCondition.getIsVipCard());
		tar.setIsEnableShelflife(merchantProductCondition.getIsEnableShelflife());
		tar.setShelflifeDays(merchantProductCondition.getShelflifeDays());
		tar.setIsVendible(merchantProductCondition.getIsVendible());
		tar.setGrossWeight(merchantProductCondition.getGrossWeight());
		tar.setNetWeight(merchantProductCondition.getNetWeight());
		tar.setCode(merchantProductCondition.getCode());
		tar.setMerchantBrandId(merchantProductCondition.getMerchantBrandId());
		tar.setMarketPrice(merchantProductCondition.getMarketPrice());
		tar.setPromotionPrice(merchantProductCondition.getPromotionPrice());
		tar.setSaleWay(merchantProductCondition.getSaleWay());
		tar.setSoldBase(merchantProductCondition.getSoldBase());
		tar.setFreightExplain(merchantProductCondition.getFreightExplain());
		tar.setShipmentsExplain(merchantProductCondition.getShipmentsExplain());
		tar.setFreightTemplateId(merchantProductCondition.getFreightTemplateId());
		tar.setCreateTime(merchantProductCondition.getCreateTime());
		tar.setUpdateTime(merchantProductCondition.getUpdateTime());
		tar.setPlatformId(merchantProductCondition.getPlatformId());
		tar.setProductCategory(merchantProductCondition.getProductCategory());
		tar.setStockWarning(merchantProductCondition.getStockWarning());
		tar.setUpdateUserid(merchantProductCondition.getUpdateUserid());
		tar.setUpdateUsername(merchantProductCondition.getUpdateUsername());
		tar.setUpdateUserip(merchantProductCondition.getUpdateUserip());
		tar.setUpdateUsermac(merchantProductCondition.getUpdateUsermac());
		tar.setIsSpuKeyword(merchantProductCondition.getIsSpuKeyword());
		tar.setDemoSalePrice(merchantProductCondition.getDemoSalePrice());
		tar.setCompetingSalePrice(merchantProductCondition.getCompetingSalePrice());
		tar.setStoreId(merchantProductCondition.getStoreId());
		tar.setPresellPeriod(merchantProductCondition.getPresellPeriod());
		tar.setRelevanceSuId(merchantProductCondition.getRelevanceSuId());
		//
		tar.setBuyType(merchantProductCondition.getBuyType());
		tar.setFrontSerialNumber(merchantProductCondition.getFrontSerialNumber());
		standardUnitWriteDAO.update(tar);
		
	}
	/**
	 * 同步新增pu信息
	 * 
	 * @param merchantProductId
	 * @return
	 */
	private List<Long> synchronizationProductUnitWithTx(Long merchantProductId) {
		List<Long> list = new ArrayList<>();
		// 根据su草稿id查询pu草稿信息
		ProductUnitPO productUnitPO = new ProductUnitPO();
		productUnitPO.setMerchantProductId(merchantProductId);
		List<ProductUnitPO> productUnitList = productUnitReadDAO.findAll(productUnitPO,null);
		List<CommodityProductUnitPO> commodityProductUnitPOs = new ArrayList<>();
		for (ProductUnitPO productUnitPO2 : productUnitList) {
			// 同步pu信息
			CommodityProductUnitPO commodityProductUnitPO = new CommodityProductUnitPO();
			// 查询pu序列号
			String productUnitSerialNumber = skuReadManage.productUnitSerialNumberBySkuId(productUnitPO2.getSkuId());
			commodityProductUnitPO.setProductUnitSerialNumber(productUnitSerialNumber);
			commodityProductUnitPO.setProductUnitId(productUnitPO2.getId());
			commodityProductUnitPO.setSkuId(productUnitPO2.getSkuId());
			commodityProductUnitPO.setStandardUnitId(productUnitPO2.getMerchantProductId());
			commodityProductUnitPO.setName(productUnitPO2.getName());
			commodityProductUnitPO.setRemark(productUnitPO2.getRemark());
			commodityProductUnitPO.setSalePrice(productUnitPO2.getSalePrice());
			commodityProductUnitPO.setStatus(productUnitPO2.getStatus());
			commodityProductUnitPO.setIsVendible(productUnitPO2.getIsVendible());
			commodityProductUnitPO.setCode(productUnitPO2.getCode());
			commodityProductUnitPO.setPuPicUrl(productUnitPO2.getPuPicUrl());
			commodityProductUnitPO.setPlatformId(productUnitPO2.getPlatformId());
			commodityProductUnitPO.setDemoSalePrice(productUnitPO2.getDemoSalePrice());
			commodityProductUnitPO.setCompetingSalePrice(productUnitPO2.getCompetingSalePrice());
			list.add(productUnitPO2.getId());
			commodityProductUnitPOs.add(commodityProductUnitPO);
		}
		// 批量同步pu数据
		if(EmptyUtil.isNotEmpty(commodityProductUnitPOs))
			commodityProductUnitWriteDAO.insertCommodityProductUnitAllWithTx(commodityProductUnitPOs);
		return list;
	}
	/**
	 * 批量保存su标签记录信息
	 * @param standardUnitRecordId
	 * @param merchantProductTagList
	 */
	private void synchronizationSUTR(Long standardUnitRecordId, List<MerchantProductTagPO> merchantProductTagList) {
		List<StandardUnitTagRecordPO> standardUnitTagRecords = new ArrayList<>();
		for (MerchantProductTagPO merchantProductTagPO2 : merchantProductTagList) {
			// 保存su标签记录信息
			StandardUnitTagRecordPO standardUnitTagRecordPO = new StandardUnitTagRecordPO();
			standardUnitTagRecordPO.setTagId(merchantProductTagPO2.getTagId());
			standardUnitTagRecordPO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitTagRecords.add(standardUnitTagRecordPO);
		}
		// 保存su标签记录信息
		if(EmptyUtil.isNotEmpty(standardUnitTagRecords))
			standardUnitTagRecordWriteDAO.insertAll(standardUnitTagRecords);
		
	}
	/**
	 * 批量保存su标签关系信息
	 * @param merchantProductTagList
	 */
	private void synchronizationSUT(List<MerchantProductTagPO> merchantProductTagList) {
		List<StandardUnitTagPO> standardUnitTags = new ArrayList<>();
		// 同步标签信息
		for (MerchantProductTagPO merchantProductTagPO2 : merchantProductTagList) {
			StandardUnitTagPO standardUnitTagPO = new StandardUnitTagPO();
			standardUnitTagPO.setTagId(merchantProductTagPO2.getTagId());
			standardUnitTagPO.setStandardUnitId(merchantProductTagPO2.getMerchantProductId());
			standardUnitTags.add(standardUnitTagPO);
		}
		// 批量保存su标签关系信息
		if(EmptyUtil.isNotEmpty(standardUnitTags))
			standardUnitTagWriteDAO.insertAll(standardUnitTags);
	}
	/**
	 * 同步su记录公司关系信息
	 * @param standardUnitRecordId
	 * @param merchantProductCompanyList
	 */
	private void synchronizationSURCompany(Long standardUnitRecordId,
			List<MerchantProductCompanyPO> merchantProductCompanyList) {
		List<StandardUnitCompanyRecordPO> standardUnitCompanyRecords = new ArrayList<>();
		for (MerchantProductCompanyPO merchantProductCompanyPO2 : merchantProductCompanyList) {
			// 同步保存su公司关系记录信息
			StandardUnitCompanyRecordPO standardUnitCompanyRecordPO = new StandardUnitCompanyRecordPO();
			standardUnitCompanyRecordPO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitCompanyRecordPO.setCompanyId(merchantProductCompanyPO2.getCompanyId());
			standardUnitCompanyRecordPO.setCreateTime(merchantProductCompanyPO2.getCreateTime());
			standardUnitCompanyRecordPO.setCompanyType(merchantProductCompanyPO2.getCompanyType());
			standardUnitCompanyRecords.add(standardUnitCompanyRecordPO);
		}
		// 批量保存公司关系记录信息
		if(EmptyUtil.isNotEmpty(standardUnitCompanyRecords))
			standardUnitCompanyRecordWriteDAO.insertAll(standardUnitCompanyRecords);
	}
	/**
	 * 同步su公司关系信息
	 * @param merchantProductCompanyList
	 */
	private void synchronizationSUCompany(List<MerchantProductCompanyPO> merchantProductCompanyList) {
		List<StandardUnitCompanyPO> standardUnitCompanys = new ArrayList<>();
		for (MerchantProductCompanyPO merchantProductCompanyPO2 : merchantProductCompanyList) {
			// 同步保存su公司关系
			StandardUnitCompanyPO standardUnitCompanyPO = new StandardUnitCompanyPO();
			standardUnitCompanyPO.setStandardUnitId(merchantProductCompanyPO2.getMerchantProductId());
			standardUnitCompanyPO.setCompanyId(merchantProductCompanyPO2.getCompanyId());
			standardUnitCompanyPO.setCreateTime(merchantProductCompanyPO2.getCreateTime());
			standardUnitCompanyPO.setCompanyType(merchantProductCompanyPO2.getCompanyType());
			standardUnitCompanys.add(standardUnitCompanyPO);
		}
		// 批量保存su商品公司信息
		if(EmptyUtil.isNotEmpty(standardUnitCompanys))
			standardUnitCompanyWriteDAO.insertAll(standardUnitCompanys);
		
	}
	/**
	 * 同步su所属平台关系记录表
	 * @param standardUnitRecordId
	 * @param merchantProdClientList
	 */
	private void synchronizationSUCR(Long standardUnitRecordId, List<MerchantProdClientPO> merchantProdClientList) {
		List<StandardUnitClientRecordPO> standardUnitClientRecords = new ArrayList<>();
		for (MerchantProdClientPO merchantProdClientPO2 : merchantProdClientList) {
			// 同步保存su所属平台关系记录表
			StandardUnitClientRecordPO standardUnitClientRecordPO = new StandardUnitClientRecordPO();
			standardUnitClientRecordPO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitClientRecordPO.setClientId(merchantProdClientPO2.getClientId());
			standardUnitClientRecordPO.setCreateTime(merchantProdClientPO2.getCreateTime());
			standardUnitClientRecords.add(standardUnitClientRecordPO);
		}
		// 批量保存su平台关系记录信息
		if(EmptyUtil.isNotEmpty(standardUnitClientRecords))
			standardUnitClientRecordWriteDAO.insertAll(standardUnitClientRecords);
		
	}
	/**
	 * 同步su平台关系信息
	 * @param merchantProdClientList
	 */
	private void synchronizationSUC(List<MerchantProdClientPO> merchantProdClientList) {
		List<StandardUnitClientPO> standardUnitClients = new ArrayList<>();
		for (MerchantProdClientPO merchantProdClientPO2 : merchantProdClientList) {
			// 同步保存su所属平台关系
			StandardUnitClientPO standardUnitClientPO = new StandardUnitClientPO();
			standardUnitClientPO.setStandardUnitId(merchantProdClientPO2.getMerchantProductId());
			standardUnitClientPO.setClientId(merchantProdClientPO2.getClientId());
			standardUnitClientPO.setCreateTime(merchantProdClientPO2.getCreateTime());
			standardUnitClients.add(standardUnitClientPO);
		}
		// 批量保存su平台关系信息
		if(EmptyUtil.isNotEmpty(standardUnitClients))
			standardUnitClientWriteDAO.insertAll(standardUnitClients);
		
	}
	/**
	 * 同步su商品图片记录信息
	 * @param standardUnitRecordId
	 * @param merchantProdPictureList
	 */
	private void synchronizationSUPR(Long standardUnitRecordId, List<MerchantProdPicturePO> merchantProdPictureList) {
		for (MerchantProdPicturePO merchantProdPicturePO2 : merchantProdPictureList) {
			// 同步保存su记录表
			StandardUnitPictureRecordPO standardUnitPictureRecordPO = new StandardUnitPictureRecordPO();
			standardUnitPictureRecordPO.setMerchantPictureId(merchantProdPicturePO2.getMerchantPictureId());
			standardUnitPictureRecordPO.setType(merchantProdPicturePO2.getType());
			standardUnitPictureRecordPO.setCreateTime(merchantProdPicturePO2.getCreateTime());
			standardUnitPictureRecordPO.setUpdateTime(merchantProdPicturePO2.getUpdateTime());
			standardUnitPictureRecordPO.setPlatformId(merchantProdPicturePO2.getPlatformId());
			standardUnitPictureRecordPO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitPictureRecordWriteDAO.insert(standardUnitPictureRecordPO);
		}
		
	}
	/**
	 * 同步su商品图片信息
	 * @param merchantProdPictureList
	 */
	private void synchronizationSUP(List<MerchantProdPicturePO> merchantProdPictureList) {
		List<StandardUnitPicturePO> standardUnitPictures = new ArrayList<>();
		for (MerchantProdPicturePO merchantProdPicturePO2 : merchantProdPictureList) {
			// 同步保存su图片关系
			StandardUnitPicturePO standardUnitPicturePO = new StandardUnitPicturePO();
			standardUnitPicturePO.setMerchantPictureId(merchantProdPicturePO2.getMerchantPictureId());
			standardUnitPicturePO.setType(merchantProdPicturePO2.getType());
			standardUnitPicturePO.setCreateTime(merchantProdPicturePO2.getCreateTime());
			standardUnitPicturePO.setUpdateTime(merchantProdPicturePO2.getUpdateTime());
			standardUnitPicturePO.setPlatformId(merchantProdPicturePO2.getPlatformId());
			standardUnitPicturePO.setStandardUnitId(merchantProdPicturePO2.getMerchantProdId());
			standardUnitPictures.add(standardUnitPicturePO);
		}
		// 批量保存su商品图片信息
		if(EmptyUtil.isNotEmpty(standardUnitPictures))
			standardUnitPictureWriteDAO.insertAll(standardUnitPictures);
		
	}
	/**
	 * 同步su商品详情记录信息
	 * @param merchantProdDescribePO
	 */
	private void synchronizationSUDR(Long standardUnitRecordId,MerchantProdDescribePO merchantProdDescribePO) {
		if (EmptyUtil.isNotEmpty(merchantProdDescribePO)) {
			// 同步su详情记录信息
			StandardUnitDescribeRecordPO standardUnitDescribeRecordPO = new StandardUnitDescribeRecordPO();
			standardUnitDescribeRecordPO.setStandardUnitRecordId(standardUnitRecordId);
			standardUnitDescribeRecordPO.setContent(merchantProdDescribePO.getContent());
			standardUnitDescribeRecordPO.setPlatformId(merchantProdDescribePO.getPlatformId());
			standardUnitDescribeRecordWriteDAO.insert(standardUnitDescribeRecordPO);
		}
		
	}
	/**
	 * 同步su商品详情信息
	 * @param merchantProdDescribePO
	 */
	private void synchronizationSUD(MerchantProdDescribePO merchantProdDescribePO) {
		if (EmptyUtil.isNotEmpty(merchantProdDescribePO)) {
			// 同步su商品详情信息
			StandardUnitDescribePO standardUnitDescribePO = new StandardUnitDescribePO();
			standardUnitDescribePO.setStandardUnitId(merchantProdDescribePO.getMerchantProductId());
			standardUnitDescribePO.setContent(merchantProdDescribePO.getContent());
			standardUnitDescribePO.setPlatformId(merchantProdDescribePO.getPlatformId());
			standardUnitDescribeWriteManage.insertStandardUnitDescribeWithTx(standardUnitDescribePO);
		}
	}
	/**
	 * 同步su商品记录信息
	 * @param merchantProductCondition
	 * @param standardProductUnitCondition
	 */
	private Long synchronizationSUR(MerchantProductCondition merchantProductCondition,
			StandardProductUnitCondition standardProductUnitCondition,Integer status) {
		// 同步保存su记录表
		StandardUnitRecordPO standardUnitRecordPO = new StandardUnitRecordPO();
		standardUnitRecordPO.setId(merchantProductCondition.getId());
		standardUnitRecordPO.setMerchantProductSerialNumber(standardProductUnitCondition.getProductSerialNumber()
				+ StringUtils.skuSerialNumber(standardProductUnitCondition.getSerialNumber() + 1));
		standardUnitRecordPO.setStandardUnitId(merchantProductCondition.getId());
		standardUnitRecordPO.setMerchantId(merchantProductCondition.getMerchantId());
		standardUnitRecordPO.setStandardProductUnitId(merchantProductCondition.getStandardProductUnitId());
		standardUnitRecordPO.setMerchantCateTreeNodeId(merchantProductCondition.getMerchantCateTreeNodeId());
		standardUnitRecordPO.setProductCategory(merchantProductCondition.getProductCategory());
		standardUnitRecordPO.setMerchantSeriesId(merchantProductCondition.getMerchantSeriesId());
		standardUnitRecordPO.setName(merchantProductCondition.getName());
		standardUnitRecordPO.setAlias(merchantProductCondition.getAlias());
		standardUnitRecordPO.setIsVisible(merchantProductCondition.getIsVisible());
		standardUnitRecordPO.setSubtitle(merchantProductCondition.getSubtitle());
		standardUnitRecordPO.setSupplierId(merchantProductCondition.getSupplierId());
		standardUnitRecordPO.setType(merchantProductCondition.getType());
		standardUnitRecordPO.setRemark(merchantProductCondition.getRemark());
		standardUnitRecordPO.setSalePrice(merchantProductCondition.getSalePrice());
		standardUnitRecordPO.setSaleTaxRate(merchantProductCondition.getSaleTaxRate());
		standardUnitRecordPO.setReturnDays(merchantProductCondition.getReturnDays());
		standardUnitRecordPO.setReplacementDays(merchantProductCondition.getReplacementDays());
		standardUnitRecordPO.setGuaranteeDays(merchantProductCondition.getGuaranteeDays());
		standardUnitRecordPO.setIsVatInvoice(merchantProductCondition.getIsVatInvoice());
		standardUnitRecordPO.setIsVipCard(merchantProductCondition.getIsVipCard());
		standardUnitRecordPO.setIsEnableShelflife(merchantProductCondition.getIsEnableShelflife());
		standardUnitRecordPO.setShelflifeDays(merchantProductCondition.getShelflifeDays());
		standardUnitRecordPO.setIsVendible(merchantProductCondition.getIsVendible());
		standardUnitRecordPO.setGrossWeight(merchantProductCondition.getGrossWeight());
		standardUnitRecordPO.setNetWeight(merchantProductCondition.getNetWeight());
		standardUnitRecordPO.setCode(merchantProductCondition.getCode());
		standardUnitRecordPO.setMerchantBrandId(merchantProductCondition.getMerchantBrandId());
		standardUnitRecordPO.setMarketPrice(merchantProductCondition.getMarketPrice());
		standardUnitRecordPO.setPromotionPrice(merchantProductCondition.getPromotionPrice());
		standardUnitRecordPO.setSaleWay(merchantProductCondition.getSaleWay());
		standardUnitRecordPO.setSoldBase(merchantProductCondition.getSoldBase());
		standardUnitRecordPO.setFreightExplain(merchantProductCondition.getFreightExplain());
		standardUnitRecordPO.setShipmentsExplain(merchantProductCondition.getShipmentsExplain());
		standardUnitRecordPO.setFreightTemplateId(merchantProductCondition.getFreightTemplateId());
		standardUnitRecordPO.setCreateTime(merchantProductCondition.getCreateTime());
		standardUnitRecordPO.setUpdateTime(merchantProductCondition.getUpdateTime());
		standardUnitRecordPO.setPlatformId(merchantProductCondition.getPlatformId());
		standardUnitRecordPO.setStatus(status);
		standardUnitRecordPO.setStockWarning(merchantProductCondition.getStockWarning());
		standardUnitRecordPO.setCreateUserid(merchantProductCondition.getCreateUserid());
		standardUnitRecordPO.setCreateUsername(merchantProductCondition.getCreateUsername());
		standardUnitRecordPO.setCreateUserip(merchantProductCondition.getCreateUserip());
		standardUnitRecordPO.setCreateUserip(merchantProductCondition.getCreateUserip());
		standardUnitRecordPO.setCreateUsermac(merchantProductCondition.getCreateUsermac());
		standardUnitRecordPO.setUpdateUserid(merchantProductCondition.getUpdateUserid());
		standardUnitRecordPO.setUpdateUsername(merchantProductCondition.getUpdateUsername());
		standardUnitRecordPO.setUpdateUserip(merchantProductCondition.getUpdateUserip());
		standardUnitRecordPO.setUpdateUsermac(merchantProductCondition.getUpdateUsermac());
		standardUnitRecordPO.setDemoSalePrice(merchantProductCondition.getDemoSalePrice());
		standardUnitRecordPO.setCompetingSalePrice(merchantProductCondition.getCompetingSalePrice());
		standardUnitRecordPO.setStoreId(merchantProductCondition.getStoreId());
		standardUnitRecordPO.setPresellPeriod(merchantProductCondition.getPresellPeriod());
		standardUnitRecordPO.setRelevanceSuId(merchantProductCondition.getRelevanceSuId());
		standardUnitRecordWriteDAO.insert(standardUnitRecordPO);
		return standardUnitRecordPO.getId();
		
	}
	/**
	 * 同步su商品信息
	 * @param standardProductUnitCondition
	 */
	private void synchronizationSU(MerchantProductCondition merchantProductCondition,StandardProductUnitCondition standardProductUnitCondition,Integer status) {
		// 同步su信息
		StandardUnitPO tar = new StandardUnitPO();
		tar.setId(merchantProductCondition.getId());
		tar.setMerchantProductSerialNumber(standardProductUnitCondition.getProductSerialNumber()
				+ StringUtils.skuSerialNumber(standardProductUnitCondition.getSerialNumber() + 1));
		tar.setMerchantId(merchantProductCondition.getMerchantId());
		tar.setStandardProductUnitId(merchantProductCondition.getStandardProductUnitId());
		tar.setMerchantCateTreeNodeId(merchantProductCondition.getMerchantCateTreeNodeId());
		tar.setMerchantSeriesId(merchantProductCondition.getMerchantSeriesId());
		tar.setName(merchantProductCondition.getName());
		tar.setAlias(merchantProductCondition.getAlias());
		tar.setIsVisible(merchantProductCondition.getIsVisible());
		tar.setSubtitle(merchantProductCondition.getSubtitle());
		tar.setSupplierId(merchantProductCondition.getSupplierId());
		tar.setType(merchantProductCondition.getType());
		tar.setRemark(merchantProductCondition.getRemark());
		tar.setEnterpriseId(merchantProductCondition.getEnterpriseId());
		tar.setSalePrice(merchantProductCondition.getSalePrice());
		tar.setSaleTaxRate(merchantProductCondition.getSaleTaxRate());
		tar.setReturnDays(merchantProductCondition.getReturnDays());
		tar.setReplacementDays(merchantProductCondition.getReplacementDays());
		tar.setGuaranteeDays(merchantProductCondition.getGuaranteeDays());
		tar.setIsVatInvoice(merchantProductCondition.getIsVatInvoice());
		tar.setIsVipCard(merchantProductCondition.getIsVipCard());
		tar.setIsEnableShelflife(merchantProductCondition.getIsEnableShelflife());
		tar.setShelflifeDays(merchantProductCondition.getShelflifeDays());
		tar.setIsVendible(merchantProductCondition.getIsVendible());
		tar.setGrossWeight(merchantProductCondition.getGrossWeight());
		tar.setNetWeight(merchantProductCondition.getNetWeight());
		tar.setCode(merchantProductCondition.getCode());
		tar.setMerchantBrandId(merchantProductCondition.getMerchantBrandId());
		tar.setMarketPrice(merchantProductCondition.getMarketPrice());
		tar.setPromotionPrice(merchantProductCondition.getPromotionPrice());
		tar.setSaleWay(merchantProductCondition.getSaleWay());
		tar.setSoldBase(merchantProductCondition.getSoldBase());
		tar.setFreightExplain(merchantProductCondition.getFreightExplain());
		tar.setShipmentsExplain(merchantProductCondition.getShipmentsExplain());
		tar.setFreightTemplateId(merchantProductCondition.getFreightTemplateId());
		tar.setCreateTime(merchantProductCondition.getCreateTime());
		tar.setUpdateTime(merchantProductCondition.getUpdateTime());
		tar.setPlatformId(merchantProductCondition.getPlatformId());
		tar.setProductCategory(merchantProductCondition.getProductCategory());
		tar.setStockWarning(merchantProductCondition.getStockWarning());
		tar.setCreateUserid(merchantProductCondition.getCreateUserid());
		tar.setCreateUsername(merchantProductCondition.getCreateUsername());
		tar.setCreateUserip(merchantProductCondition.getCreateUserip());
		tar.setCreateUsermac(merchantProductCondition.getCreateUsermac());
		tar.setUpdateUserid(merchantProductCondition.getUpdateUserid());
		tar.setUpdateUsername(merchantProductCondition.getUpdateUsername());
		tar.setUpdateUserip(merchantProductCondition.getUpdateUserip());
		tar.setUpdateUsermac(merchantProductCondition.getUpdateUsermac());
		tar.setIsSpuKeyword(merchantProductCondition.getIsSpuKeyword());
		tar.setDemoSalePrice(merchantProductCondition.getDemoSalePrice());
		tar.setCompetingSalePrice(merchantProductCondition.getCompetingSalePrice());
		tar.setStoreId(merchantProductCondition.getStoreId());
		tar.setPresellPeriod(merchantProductCondition.getPresellPeriod());
		tar.setRelevanceSuId(merchantProductCondition.getRelevanceSuId());
		tar.setEnterpriseId(merchantProductCondition.getEnterpriseId());
		tar.setSupplierId(merchantProductCondition.getSupplierId());
		tar.setMerchantCateTreeNodeId(standardProductUnitCondition.getCategoryTreeNodeId());
		tar.setStatus(status);
		tar.setBuyType(merchantProductCondition.getBuyType());
		tar.setFrontSerialNumber(merchantProductCondition.getFrontSerialNumber());
		standardUnitWriteDAO.insert(tar);
		
	}
	@Override
	public int updateMerchantProductPictureByIdWithTx(Long merchantProductId, String picture, List<String> pictures,
			List<String> webBannerPictures, MerchantProductPO po) {
		MerchantProductPO merchantProductPO = merchantProductReadDAO.findById(po);
		// 根据su商品id删除su商品图片信息
		MerchantProdPicturePO merchantProdPicturePO = new MerchantProdPicturePO();
		merchantProdPicturePO.setMerchantProdId(merchantProductId);
		merchantProdPicturePO.setType(1);
		merchantProdPictureWriteDAO.deleteByPara(merchantProdPicturePO);
		
		MerchantProdPicturePO merchantProdPicture = new MerchantProdPicturePO();
		merchantProdPicture.setMerchantProdId(merchantProductId);
		merchantProdPicture.setType(2);
		merchantProdPictureWriteDAO.deleteByPara(merchantProdPicture);
		
		// 保存su商品图片信息 su商品图片类型：1、列表图片 2、轮播图片 3、web轮播图
		saveSUPicture(merchantProductId,picture,1,merchantProductPO.getMerchantId(),po.getPlatformId());
		// 如果app轮播图不为空则循环添加
		if(EmptyUtil.isNotEmpty(pictures)){
			for (String picture_ : pictures) {
				saveSUPicture(merchantProductId,picture_,2,merchantProductPO.getMerchantId(),po.getPlatformId());
			}
		}
		// 如果app轮播图不为空则循环添加
		if(EmptyUtil.isNotEmpty(webBannerPictures)){
			for (String picture_ : webBannerPictures) {
				saveSUPicture(merchantProductId,picture_,3,merchantProductPO.getMerchantId(),po.getPlatformId());
			}
		}
		return merchantProductWriteDAO.update(po);
	}

	@Override
	public void saveMerchantProduct(List<MerchantProductPO> merchantProductPOList) {
		try{
		merchantProductWriteDAO.saveMerchantProduct(merchantProductPOList);
		}catch (Exception e){
			logger.error("saveMerchantProduct失败,e:"+e.getMessage());
		}
	}

	@Override
	public void updateMerchantProductPrice(List<MerchantProductPO> merchantProductPricePOList) {
		merchantProductWriteDAO.updateMerchantProductPrice(merchantProductPricePOList);
	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		merchantProductWriteDAO.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
	}

	@Override
	public int updateMerchantProductVisible(Long standardUnitId, Integer status) {
		MerchantProductPO po = new MerchantProductPO();
		po.setId(standardUnitId);
		po.setIsVisible(status);
		return merchantProductWriteDAO.update(po);
	}

	@Override
	public void updateMerchantProductList(List<MerchantProductPO> merchantProductPOList) {
		try{
			merchantProductWriteDAO.updateMerchantProductList(merchantProductPOList);
		}catch (Exception e){
			logger.error("updateMerchantProductList失败,e:"+e.getMessage());
		}
	}

	@Override
	public void updateSuAndPuStatusByJd() {
		merchantProductWriteDAO.updateSuAndPuStatusByJd();
	}

	@Override
	public void updateJdProductStatusByProfit(Integer productLimitProfit) {
		merchantProductWriteDAO.updateJdProductStatusByProfit(productLimitProfit);
	}

}
	