package com.egeo.components.product.manage.write.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import com.egeo.components.product.dao.read.*;
import com.egeo.components.product.service.read.impl.SkuAttValueReadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.condition.ProductCondition;
import com.egeo.components.product.condition.SkuCondition;
import com.egeo.components.product.dao.read.AttributeNameReadDAO;
import com.egeo.components.product.dao.read.CategoryAttNameReadDAO;
import com.egeo.components.product.dao.read.ProductAttNameReadDAO;
import com.egeo.components.product.dao.read.ProductAttValueReadDAO;
import com.egeo.components.product.dao.read.ProductDescriptionReadDAO;
import com.egeo.components.product.dao.write.ProductAttNameWriteDAO;
import com.egeo.components.product.dao.write.ProductDescriptionWriteDAO;
import com.egeo.components.product.dao.write.ProductWriteDAO;
import com.egeo.components.product.dao.write.StandardProductUnitAttNameWriteDAO;
import com.egeo.components.product.dao.write.StandardProductUnitAttValueRecordWriteDAO;
import com.egeo.components.product.dao.write.StandardProductUnitAttValueWriteDAO;
import com.egeo.components.product.manage.read.AttributeNameReadManage;
import com.egeo.components.product.manage.read.AttributeValueReadManage;
import com.egeo.components.product.manage.read.CategoryAttNameReadManage;
import com.egeo.components.product.manage.read.CategoryReadManage;
import com.egeo.components.product.manage.read.CategoryTreeNodeReadManage;
import com.egeo.components.product.manage.read.PictureReadManage;
import com.egeo.components.product.manage.read.ProductAttNameReadManage;
import com.egeo.components.product.manage.read.ProductAttValueReadManage;
import com.egeo.components.product.manage.read.ProductPictureReadManage;
import com.egeo.components.product.manage.read.ProductReadManage;
import com.egeo.components.product.manage.read.SkuReadManage;
import com.egeo.components.product.manage.read.StandardProductUnitAttNameReadManage;
import com.egeo.components.product.manage.read.StandardProductUnitAttValueReadManage;
import com.egeo.components.product.manage.read.StandardProductUnitReadManage;
import com.egeo.components.product.manage.write.BrandWriteManage;
import com.egeo.components.product.manage.write.PictureWriteManage;
import com.egeo.components.product.manage.write.ProductAttNameWriteManage;
import com.egeo.components.product.manage.write.ProductAttValueWriteManage;
import com.egeo.components.product.manage.write.ProductDescriptionWriteManage;
import com.egeo.components.product.manage.write.ProductPictureWriteManage;
import com.egeo.components.product.manage.write.ProductWriteManage;
import com.egeo.components.product.manage.write.SkuAttNameWriteManage;
import com.egeo.components.product.manage.write.SkuAttValueWriteManage;
import com.egeo.components.product.manage.write.SkuWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitAttNameRecordWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitAttNameWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitAttValueRecordWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitAttValueWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitDescriptionRecordWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitDescriptionWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitPictureRecordWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitPictureWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitRecordWriteManage;
import com.egeo.components.product.manage.write.StandardProductUnitWriteManage;
import com.egeo.components.product.po.AttNameValuePO;
import com.egeo.components.product.po.AttributeNamePO;
import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.components.product.po.BrandPO;
import com.egeo.components.product.po.CategoryPO;
import com.egeo.components.product.po.CategoryTreeNodePO;
import com.egeo.components.product.po.PicturePO;
import com.egeo.components.product.po.ProductAttNamePO;
import com.egeo.components.product.po.ProductAttValuePO;
import com.egeo.components.product.po.ProductDescriptionPO;
import com.egeo.components.product.po.ProductPO;
import com.egeo.components.product.po.ProductPicturePO;
import com.egeo.components.product.po.SkuAttNamePO;
import com.egeo.components.product.po.SkuAttValuePO;
import com.egeo.components.product.po.SkuPO;
import com.egeo.components.product.po.StandardProductUnitAttNamePO;
import com.egeo.components.product.po.StandardProductUnitAttNameRecordPO;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;
import com.egeo.components.product.po.StandardProductUnitDescriptionPO;
import com.egeo.components.product.po.StandardProductUnitDescriptionRecordPO;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.components.product.po.StandardProductUnitPicturePO;
import com.egeo.components.product.po.StandardProductUnitPictureRecordPO;
import com.egeo.components.product.po.StandardProductUnitRecordPO;
import com.egeo.config.RuntimeContext;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.str.StringUtils;

@Service
public class ProductWriteManageImpl implements ProductWriteManage {
	private static final XLogger logger = XLogger.getLogger(ProductWriteManageImpl.class);
	@Autowired
	private ProductWriteDAO productWriteDAO;

	@Autowired
	private ProductReadManage productReadManage;

	@Autowired
	private ProductAttNameWriteManage productAttNameWriteManage;

	@Autowired
	private ProductAttNameReadManage productAttNameReadManage;

	@Autowired
	private AttributeNameReadManage attributeNameReadManage;

	@Autowired
	private ProductAttValueWriteManage productAttValueWriteManage;

	@Autowired
	private ProductDescriptionWriteManage productDescriptionWriteManage;

	@Autowired
	private PictureWriteManage pictureWriteManage;

	@Autowired
	private PictureReadManage pictureReadManage;

	@Autowired
	private ProductPictureWriteManage productPictureWriteManage;

	@Autowired
	private ProductPictureReadManage productPictureReadManage;

	@Autowired
	private BrandWriteManage brandWriteManage;

	@Autowired
	private CategoryReadManage categoryReadManage;

	@Autowired
	private CategoryTreeNodeReadManage categoryTreeNodeReadManage;

	@Autowired
	private StandardProductUnitWriteManage standardProductUnitWriteManage;

	@Autowired
	private StandardProductUnitReadManage standardProductUnitReadManage;

	@Autowired
	private StandardProductUnitAttNameWriteManage standardProductUnitAttNameWriteManage;

	@Autowired
	private StandardProductUnitAttNameReadManage standardProductUnitAttNameReadManage;

	@Autowired
	private StandardProductUnitAttValueWriteManage standardProductUnitAttValueWriteManage;

	@Autowired
	private StandardProductUnitPictureWriteManage standardProductUnitPictureWriteManage;

	@Autowired
	private StandardProductUnitRecordWriteManage standardProductUnitRecordWriteManage;

	@Autowired
	private StandardProductUnitDescriptionWriteManage standardProductUnitDescriptionWriteManage;

	@Autowired
	private StandardProductUnitDescriptionRecordWriteManage standardProductUnitDescriptionRecordWriteManage;

	@Autowired
	private StandardProductUnitAttNameRecordWriteManage standardProductUnitAttNameRecordWriteManage;

	@Autowired
	private ProductAttValueReadManage productAttValueReadManage;

	@Autowired
	private StandardProductUnitAttValueRecordWriteManage standardProductUnitAttValueRecordWriteManage;

	@Autowired
	private StandardProductUnitPictureRecordWriteManage standardProductUnitPictureRecordWriteManage;

	@Autowired
	private StandardProductUnitAttValueReadManage standardProductUnitAttValueReadManage;

	@Autowired
	private AttributeValueReadManage attributeValueReadManage;

	@Autowired
	private SkuReadManage skuReadManage;

	@Autowired
	private SkuWriteManage skuWriteManage;

	@Autowired
	private SkuAttNameWriteManage skuAttNameWriteManage;

	@Autowired
	private SkuAttValueWriteManage skuAttValueWriteManage;

	@Autowired
	private CategoryAttNameReadManage categoryAttNameReadManage;

	@Autowired
	private ProductDescriptionWriteDAO productDescriptionWriteDAO;

	@Autowired
	private ProductDescriptionReadDAO productDescriptionReadDAO;

	@Autowired
	private AttributeNameReadDAO attributeNameReadDAO;

	@Autowired
	private ProductAttNameReadDAO productAttNameReadDAO;

	@Autowired
	private ProductAttNameWriteDAO productAttNameWriteDAO;

	@Autowired
	private StandardProductUnitAttNameWriteDAO standardProductUnitAttNameWriteDAO;

	@Autowired
	private ProductAttValueReadDAO productAttValueReadDAO;

	@Autowired
	private StandardProductUnitAttValueWriteDAO standardProductUnitAttValueWriteDAO;

	@Autowired
	private CategoryAttNameReadDAO categoryAttNameReadDAO;

	@Autowired
	private StandardProductUnitAttValueRecordWriteDAO standardProductUnitAttValueRecordWriteDAO;
	@Autowired
    private SkuReadDAO skuReadDAO;

	@Override
	public Long saveProductWithTx(ProductPO po) {
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null && RuntimeContext.cacheUser().getEnterpriseId()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				if(po.getSupplierId()==null) {
					po.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
				}
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				if(po.getEnterpriseId()==null) {
					po.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
				}
			}
		}
		productWriteDAO.insert(po);
		if (po.getId() != null) {
			ProductPO productPO2 = new ProductPO();
			productPO2.setId(po.getId());
			productPO2.setProductSerialNumber(
					po.getProductSerialNumber() + StringUtils.protectProductSerialNumber(po.getId()));
			productWriteDAO.update(productPO2);
			return po.getId();
		} else {
			throw new BusinessException("新增产品信息失败");
		}

	}

	@Override
	public String updateProductWithTx(ProductPO productPO, ProductDescriptionPO productDescriptionPO,
			PicturePO picturePO, BrandPO brandPO, List<AttNameValuePO> attNameValuePOList, Long showProductAttNameId) {

		// 根据spuid查询spu属性信息
		ProductAttNamePO productAttNamePo = new ProductAttNamePO();
		productAttNamePo.setProductId(productPO.getId());
		productAttNamePo.setType(2);
		List<ProductAttNamePO> productAttNames = productAttNameReadDAO.findAll(productAttNamePo,null);

		// 商品模版1规格属性只能为一个
		if (productPO.getCommodityTemplateId().equals(1L)) {
			if (productAttNames.size() > 1) {
				throw new BusinessException("电子卡券模板1规格属性只能为一个");
			}
		}

		// 根据类目id查询类目规格数量
		int CategoryAttNameSum = categoryAttNameReadDAO.findSumByCategoryId(productPO.getCategoryId());
		if (productAttNames.size() != CategoryAttNameSum) {
			throw new BusinessException("spu规格属性不能为空");
		}

		for (ProductAttNamePO productAttNamePO2 : productAttNames) {
			// 根据spu属性id查询属性值信息
			int sum = productAttValueReadDAO.findProductAttValueSum(productAttNamePO2.getId());
			if (sum < 1) {
				throw new BusinessException("spu规格属性不能为空");
			}
		}

		// 修改显示spu图片的的规格属性id
		updateShowProductAttValueId(productPO.getId(), showProductAttNameId);

		// 根据productId查询product信息
		ProductDescriptionPO productDescriptionPO2 = new ProductDescriptionPO();
		productDescriptionPO2.setProductId(productPO.getId());
		List<ProductDescriptionPO> productDescriptionList = productDescriptionReadDAO.findAll(productDescriptionPO2,null);
		if (EmptyUtil.isNotEmpty(productDescriptionList)) {

			// 修改产品描述信息
			productDescriptionPO.setId(productDescriptionList.get(0).getId());
			productDescriptionPO.setProductId(productPO.getId());
			productDescriptionPO.setPlatformId(productPO.getPlatformId());
			productDescriptionWriteManage.updateProductDescriptionWithTx(productDescriptionPO);
		} else {
			// 修改产品描述信息
			productDescriptionPO.setProductId(productPO.getId());
			productDescriptionPO.setPlatformId(productPO.getPlatformId());
			productDescriptionWriteManage.saveProductDescription(productDescriptionPO);
		}

		// 根据类目id查询类目信息
		CategoryPO categoryPO = categoryReadManage.findCategoryById(productPO.getCategoryId());

		productPO.setProductSerialNumber(StringUtils.protectCategoryProductSerialNumber(categoryPO.getSerialNumber()));
		productPO.setProductSerialNumber(
				productPO.getProductSerialNumber() + StringUtils.protectProductSerialNumber(productPO.getId()));

		// 根据类目id和类目树类型id查询其节点信息
		CategoryTreeNodePO categoryTreeNodeDTO = new CategoryTreeNodePO();
		categoryTreeNodeDTO.setCategoryTreeId(PlatformKeyConstant.BACKGROUND_TREE_ID);
		categoryTreeNodeDTO.setCategoryId(categoryPO.getId());
		List<CategoryTreeNodePO> categoryTreeNodeList = categoryTreeNodeReadManage.findAll(categoryTreeNodeDTO);

		// 根据产品id删除图片信息
		productPictureWriteManage.deleteByProductIdWithTx(productPO.getId());

		// 修改产品封面图片信息
		if (picturePO.getUrl() != null && !picturePO.getUrl().equals("") && !picturePO.getUrl().equals("[]")) {
			picturePO.setType(1);
			Long PId = pictureWriteManage.savePictureWithTx(picturePO);
			ProductPicturePO productPictureDTO = new ProductPicturePO();
			productPictureDTO.setPictureId(PId);
			productPictureDTO.setProductId(productPO.getId());
			productPictureDTO.setType(1);
			productPictureDTO.setPlatformId(productPO.getPlatformId());
			productPictureWriteManage.insertProductPictureWithTx(productPictureDTO);
		}

		// 修改产品轮播图片
		List<PicturePO> listStyleImage = picturePO.getStyleImage();

		// 获取最新的所有产品图片id，通过url查询，有值返回id插入，没有就添加成功后返回id插入
		for (PicturePO picturePO1 : listStyleImage) {
			picturePO1.setType(2);
			Long pictureId = pictureWriteManage.savePictureWithTx(picturePO1);
			ProductPicturePO productPicturePO1 = new ProductPicturePO();
			productPicturePO1.setPictureId(pictureId);
			productPicturePO1.setProductId(productPO.getId());
			productPicturePO1.setType(2);
			productPicturePO1.setPlatformId(productPO.getPlatformId());
			productPictureWriteManage.insertProductPictureWithTx(productPicturePO1);
		}

		// 把当前的关系和最新的关系进行对比，如果没有就删除关系
		/*for (ProductPicturePO productPicturePO2 : ProductPictureList) {
			if (!longList.contains(productPicturePO2.getPictureId())) {
				// 删除图片信息
				PicturePO picturePO1 = new PicturePO();
				picturePO1.setId(productPicturePO2.getPictureId());
				pictureWriteManage.deleteWithTx(picturePO1);
				// 删除关系
				productPictureWriteManage.deleteProductPictureWithTx(productPicturePO2);
			}
		}*/

		// 修改产品信息
		// 添加类目节点信息
		productPO.setCategoryTreeNodeId(categoryTreeNodeList.get(0).getId());
		// 修改品牌信息
		Long brandId = null;
		if (EmptyUtil.isNotEmpty(brandPO.getChineseName())) {
			brandPO.setPlatformId(productPO.getPlatformId());
			brandId = brandWriteManage.saveBrandWithTx(brandPO);
			productPO.setBrandId(brandId);
		}

		if (EmptyUtil.isNotEmpty(productPO.getStatus())) {
			productPO.setStatus(productPO.getStatus());
		} else {
			productPO.setStatus(1);
		}

		String rows = productWriteDAO.update(productPO) + "";

		// 添加属性值
		if (attNameValuePOList.size() > 0) {
			for (AttNameValuePO attNameValue : attNameValuePOList) {
				if (EmptyUtil.isEmpty(attNameValue.getKey())) {
					throw new BusinessException("产品属性id不能为空");
				}
				Long attNameId = Long.valueOf(attNameValue.getKey());
				String value = attNameValue.getValue();
				/*
				 * if(!attNameValue.getValue().equals("null") &&
				 * !attNameValue.getValue().equals("[]")){ value =
				 * attNameValue.getValue().substring(1,
				 * attNameValue.getValue().length()-1); }
				 */

				// 根据产品id与属性id查询产品属性(肯定为一个)
				ProductAttNamePO productAttNamePO = new ProductAttNamePO();
				productAttNamePO.setAttNameId(attNameId);
				productAttNamePO.setProductId(productPO.getId());
				productAttNamePO.setPlatformId(productPO.getPlatformId());
				List<ProductAttNamePO> productAttNameList = productAttNameReadManage.findAll(productAttNamePO);//第一次为空

				// 根据类目id和属性id查询类目属性是否必填写 0否、1是
				int isRequired = categoryAttNameReadManage.isRequiredByCategoryIdAttNameId(productPO.getCategoryId(),
						attNameId);

				if (EmptyUtil.isEmpty(productAttNameList)) {
					// 保存产品与属性的关系并返回id
					ProductAttNamePO productAttName = new ProductAttNamePO();
					productAttName.setAttNameId(attNameId);
					productAttName.setProductId(productPO.getId());
					productAttName.setType(attNameValue.getType());
					productAttName.setPlatformId(productPO.getPlatformId());
					Long productAttNameId = productAttNameWriteManage.saveProductAttNameWithTx(productAttName);

					// 根据属性id查询属性信息
					AttributeNamePO attributeNamePO = new AttributeNamePO();
					attributeNamePO.setId(attNameId);
					AttributeNamePO attributeName = attributeNameReadManage.findById(attributeNamePO);

					// 保存产品属性与属性值的关系
					ProductAttValuePO productAttValuePO = new ProductAttValuePO();
					productAttValuePO.setProductAttNameId(productAttNameId);
					// 保存产品属性
					saveProductAttName(attributeName, productAttValuePO, value, isRequired, productPO.getPlatformId());
				} else {
					// 根据产品属性id删除产品属性所有属性值
					ProductAttValuePO productAttValuePO1 = new ProductAttValuePO();
					productAttValuePO1.setProductAttNameId(productAttNameList.get(0).getId());
					productAttValueWriteManage.deleteByProductAttNameIdWithTx(productAttValuePO1);

					// 根据属性id查询属性信息
					AttributeNamePO attributeNamePO = new AttributeNamePO();
					attributeNamePO.setId(attNameId);
					AttributeNamePO attributeName = attributeNameReadManage.findById(attributeNamePO);

					// 保存产品属性与属性值的关系
					ProductAttValuePO productAttValuePO = new ProductAttValuePO();
					productAttValuePO.setProductAttNameId(productAttNameList.get(0).getId());
					// 保存产品属性
					saveProductAttName(attributeName, productAttValuePO, value, isRequired, productPO.getPlatformId());
				}
			}

		} else {
			// 根据产品id查询产品属性
			ProductAttNamePO productAttNamePO = new ProductAttNamePO();
			productAttNamePO.setProductId(productPO.getId());
			productAttNamePO.setType(1);
			List<ProductAttNamePO> productAttNameList = productAttNameReadManage.findAll(productAttNamePO);

			// 根据所有产品属性id批量删除产品属性值
			StringBuffer productAttNameIds = new StringBuffer();
			for (ProductAttNamePO productAttNameDTO2 : productAttNameList) {
				productAttNameIds.append(productAttNameDTO2.getId());
				productAttNameIds.append(",");
			}
			if (productAttNameIds.length() > 0) {
				productAttNameIds.deleteCharAt(productAttNameIds.length() - 1);
			}
			if (productAttNameIds.length() > 0) {
				productAttValueWriteManage.deleteByMuchProductAttNameIdWithTx(productAttNameIds.toString());
			}

			// 根据产品id删除所有产品属性关系
			productAttNameWriteManage.deleteByProductIdWithTx(productPO.getId());
		}
		return rows;
	}

	/**
	 *
	 * @param showProductAttNameId
	 */
	private boolean updateShowProductAttValueId(Long productId, Long showProductAttNameId) {
		boolean ifTrue = false;
		// 根据spu草稿id和规格类型查询显示的spu规格属性草稿信息
		ProductAttNamePO productAttNamePO = new ProductAttNamePO();
		productAttNamePO.setProductId(productId);
		productAttNamePO.setType(2);
		productAttNamePO.setShowPicture(1);
		List<ProductAttNamePO> productAttNameList = productAttNameReadDAO.findAll(productAttNamePO,null);
		for (ProductAttNamePO productAttNamePO2 : productAttNameList) {
			// 批量把显示的spu规格属性设为停用
			ProductAttNamePO po = new ProductAttNamePO();
			po.setId(productAttNamePO2.getId());
			po.setShowPicture(0);
			productAttNameWriteDAO.update(po);
		}
		// 根据spuId和属性id设为启用spu图片
		ProductAttNamePO po = new ProductAttNamePO();
		po.setProductId(productId);
		po.setAttNameId(showProductAttNameId);
		po.setShowPicture(1);
		productAttNameWriteDAO.updateByProductIdAndAttNameId(po);
		ifTrue = true;
		return ifTrue;

	}

	/**
	 * 保存产品属性
	 *
	 * @param attributeName
	 * @param productAttValueDTO
	 * @param value
	 * @param isRequired
	 * @param platformId
	 */
	private void saveProductAttName(AttributeNamePO attributeName, ProductAttValuePO productAttValueDTO, String value,
			int isRequired, Long platformId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (attributeName.getMode() == 1) {
			// 1为必须填写
			if (isRequired == 1) {
				if (EmptyUtil.isEmpty(value) || value.equals("[]")) {
					throw new BusinessException(attributeName.getName() + "为必填项");
				}
			}
			if (EmptyUtil.isNotEmpty(value) && !value.equals("[]")) {
				List<String> attValueIdList = Arrays.asList(value.split(","));
				// 选取字符串最后一位id
				productAttValueDTO.setAttValueId(Long.valueOf(attValueIdList.get(attValueIdList.size() - 1)));
			}
			productAttValueDTO.setPlatformId(platformId);
			productAttValueWriteManage.saveProductAttValueWithTx(productAttValueDTO);
		} else if (attributeName.getMode() == 2 || attributeName.getMode() == 4 || attributeName.getMode() == 5
				|| attributeName.getMode() == 6) {
			if (EmptyUtil.isEmpty(value) || !value.equals("[]")) {
				// 1为必须填写
				if (isRequired == 1) {
					if (EmptyUtil.isEmpty(value)) {
						throw new BusinessException(attributeName.getName() + "为必填项");
					}
				}
				// 如果不为空，判断是否为属性规定范围
				if (EmptyUtil.isNotEmpty(value)) {
					if (attributeName.getMode() == 2) {
						/*String beginDecimal = attributeName.getBeginDecimal().toString().substring(0,
								attributeName.getBeginDecimal().toString().length() - 3);
						Integer begin = Integer.valueOf(beginDecimal);
						String finishDecim = attributeName.getFinishDecimal().toString().substring(0,
								attributeName.getFinishDecimal().toString().length() - 3);
						Integer finish = Integer.valueOf(finishDecim);*/

						Integer begin = null ;
						Integer finish = null;
						if ( EmptyUtil.isNotEmpty(attributeName.getBeginDecimal()) ) {
							//String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0, attributeNameDTO.getBeginDecimal().toString().length()-3);
							begin = attributeName.getBeginDecimal().intValue();
						}
						if ( EmptyUtil.isNotEmpty(attributeName.getFinishDecimal()) ) {
							//String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0, attributeNameDTO.getFinishDecimal().toString().length()-3);
							finish = attributeName.getFinishDecimal().intValue();
						}
						if ( EmptyUtil.isNotEmpty(begin) && EmptyUtil.isNotEmpty(finish) ) {
							attributeName.setReminder((isRequired == 1)?"请输入"+ begin + "~" +finish +"位字符"+"(必填)":"请输入"+ begin + "~" +finish +"位字符");
						}
						if (EmptyUtil.isNotEmpty(begin) && EmptyUtil.isNotEmpty(finish) ) {
							if (begin > value.length() || value.length() > finish) {
								// 属性值长度小于起始值或大于终始值
								throw new BusinessException(
										attributeName.getName() + "：请输入" + begin + "~" + finish + "位字符");
							}
						}

					} else if (attributeName.getMode() == 4) {
						String beginDecimal = attributeName.getBeginDecimal().toString().substring(0,
								attributeName.getBeginDecimal().toString().length() - 3);
						Long begin = Long.valueOf(beginDecimal);
						String finishDecim = attributeName.getFinishDecimal().toString().substring(0,
								attributeName.getFinishDecimal().toString().length() - 3);
						Long finish = Long.valueOf(finishDecim);
						if (begin.longValue() > Long.valueOf(value).longValue()
								|| finish.longValue() < Long.valueOf(value).longValue()) {
							// 属性值长度小于起始值或大于终始值
							throw new BusinessException(attributeName.getName() + "：请输入" + format.format(begin) + "~"
									+ format.format(finish) + "时间");
						}
					} else if (attributeName.getMode() == 5) {
						String beginDecimal = attributeName.getBeginDecimal().toString().substring(0,
								attributeName.getBeginDecimal().toString().length() - 3);
						Integer begin = Integer.valueOf(beginDecimal);
						String finishDecim = attributeName.getFinishDecimal().toString().substring(0,
								attributeName.getFinishDecimal().toString().length() - 3);
						Integer finish = Integer.valueOf(finishDecim);
						if (!StringUtils.isNotFigure(value) || begin > Integer.valueOf(value)
								|| Integer.valueOf(value) > finish) {
							// 属性值长度小于起始值或大于终始值
							throw new BusinessException(
									attributeName.getName() + "：请输入" + Integer.parseInt(beginDecimal.split("\\.")[0])
											+ "~" + Integer.parseInt(finishDecim.split("\\.")[0]) + "之内整数数字");
						}
					} else if (attributeName.getMode() == 6) {

						if (!StringUtils.isNotFigure(value)
								|| attributeName.getBeginDecimal().doubleValue() > new BigDecimal(value).doubleValue()
								|| new BigDecimal(value).doubleValue() > attributeName.getFinishDecimal()
										.doubleValue()) {
							// 属性值长度小于起始值或大于终始值
							throw new BusinessException(
									attributeName.getName() + "：请输入" + attributeName.getBeginDecimal() + "~"
											+ attributeName.getFinishDecimal() + "之内的数字");
						}
						String[] split = value.split("\\.");
						if (split.length > 2) {
							throw new BusinessException(attributeName.getName() + "：请输入保留2位小数的数字");
						}
						if (split.length == 2) {
							String parseInt = split[1];
							if (parseInt.length() > 2) {
								throw new BusinessException(attributeName.getName() + "：请输入保留2位小数的数字");
							} else if (parseInt.length() == 2) {
								productAttValueDTO.setAttValueCustom(value);
							}
							if (parseInt.length() == 1) {
								productAttValueDTO.setAttValueCustom(value + "0");
							}
						}
						if (split.length == 1) {
							if (value.contains(".")) {
								productAttValueDTO.setAttValueCustom(value + "00");
							} else {
								productAttValueDTO.setAttValueCustom(value + ".00");
							}

						}

					}
				}
				productAttValueDTO.setAttValueCustom(value);
				productAttValueDTO.setPlatformId(platformId);
				productAttValueWriteManage.saveProductAttValueWithTx(productAttValueDTO);
			}

		} else if (attributeName.getMode() == 3) {
			// 1为必须填写
			if (isRequired == 1) {
				if (EmptyUtil.isEmpty(value) || value.equals("[]")) {
					throw new BusinessException(attributeName.getName() + "为必填项");
				}
			}
			// 如果不为空、循环添加复选框选中值
			if (EmptyUtil.isEmpty(value) || !value.equals("[]")) {
				List<String> list = JSONArray.parseArray(value, String.class);
				for (String string2 : list) {
					productAttValueDTO.setAttValueId(Long.valueOf(string2));
					productAttValueDTO.setPlatformId(platformId);
					productAttValueWriteManage.saveProductAttValueWithTx(productAttValueDTO);
				}
			} /*else {
				productAttValueDTO.setPlatformId(platformId);
				productAttValueWriteManage.saveProductAttValueWithTx(productAttValueDTO);
			}*/

		}else if (attributeName.getMode() == 7) {
			// 1为必须填写
			if (isRequired == 1) {
				if (EmptyUtil.isEmpty(value) || value.equals("[]")) {
					throw new BusinessException(attributeName.getName() + "为必填项");
				}
			}
			// 循环添加多文本框的值
			if (EmptyUtil.isEmpty(value) || !value.equals("[]")) {//[10,5,2]
				List<String> list = JSONArray.parseArray(value, String.class);
				for (String string2 : list) {
					productAttValueDTO.setAttValueCustom(string2);
					productAttValueDTO.setPlatformId(platformId);
					productAttValueWriteManage.saveProductAttValueWithTx(productAttValueDTO);
				}
			} /*else {
				productAttValueDTO.setPlatformId(platformId);
				productAttValueWriteManage.saveProductAttValueWithTx(productAttValueDTO);
			}*/

		}
	}

	@Override
	public String deleteProductWithTx(ProductPO po) {
		return productWriteDAO.delete(po) + "";
	}

	@Override
	public Long auditWithTx(ProductPO po) {
		productWriteDAO.update(po);
		return po.getId();
	}

	@Override
	public int updateWithTx(ProductPO po) {
		return productWriteDAO.update(po);
	}

	@Override
	public void cleanLink(ProductPO po) {
		productWriteDAO.cleanLink(po.getId());
	}
	public int synchronizationStandardProductUnitWithTx(Long productId, Long userId, String userName, String ip,
			String mac) {
		SkuPO sku = new SkuPO();
		sku.setStandardProductUnitId(productId);
		List<SkuCondition> skuList = skuReadManage.findSkuAll(sku);
		int skuSerialNumber = skuList.size();

		Long standardProductUnitRecordId = null;
		Long showStandardProductUnitAttNameId = null;
		// 修改spu草稿状态为上架
		ProductPO productPO = new ProductPO();
		productPO.setId(productId);
		productPO.setStatus(3);
		logger.info("修改spu草稿状态为上架 ,spuId:" + productId);
		int rows = updateWithTx(productPO);

		if (rows != 0) {
			// 根据spu草稿id查询spu草稿信息
			ProductCondition Product = productReadManage.productAndProductDescriptionById(productId);

			// 根据类目id查询类目信息
			CategoryPO categoryPO = categoryReadManage.categoryByPIdNode(Product.getCategoryTreeNodeId());

			// 同步添加spu信息
			StandardProductUnitPO standardProductUnitPO = new StandardProductUnitPO();
			standardProductUnitPO.setId(Product.getId());

			// 根据spuid查询spu信息
			logger.info("根据spuid查询spu信息,spuId:" + productId);
			StandardProductUnitPO standardProductUnitDTO2 = standardProductUnitReadManage
					.findStandardProductUnitById(standardProductUnitPO);

			if (EmptyUtil.isNotEmpty(standardProductUnitDTO2)) {
				//产品修改编辑提交
				logger.info("根据spuid查询spu信息不为空,开始更新spu信息,并生成spu产品记录,spuId:" + productId);
				// 通过spu名称查询spu信息
				StandardProductUnitPO standardProductUnitPO2 = new StandardProductUnitPO();
				standardProductUnitPO2.setName(Product.getName());
				standardProductUnitPO2.setPlatformId(Product.getPlatformId());
				List<StandardProductUnitPO> findStandardProductUnitAll = standardProductUnitReadManage
						.findStandardProductUnitAll(standardProductUnitPO2);
				if (EmptyUtil.isNotEmpty(findStandardProductUnitAll)) {
					if (!productId.equals(findStandardProductUnitAll.get(0).getId())) {
						logger.error("spu名称不能重复,spuId:" + productId);
						throw new BusinessException("spu名称不能重复");
					}

				}

				// 同步spu信息、生成spu记录信息
				standardProductUnitPO.setBrandId(Product.getBrandId());
				standardProductUnitPO.setCategoryTreeNodeId(Product.getCategoryTreeNodeId());
				standardProductUnitPO.setProductCategory(Product.getProductCategory());
				standardProductUnitPO.setTitle(Product.getTitle());
				standardProductUnitPO.setName(Product.getName());
				standardProductUnitPO.setChineseName(Product.getChineseName());
				standardProductUnitPO.setMarketPrice(Product.getMarketPrice());
				standardProductUnitPO.setTaxNo(Product.getTaxNo());
				standardProductUnitPO.setEanNo(Product.getEanNo());
				standardProductUnitPO.setPlaceOfOrigin(Product.getPlaceOfOrigin());
				standardProductUnitPO.setCalculationUnit(Product.getCalculationUnit());
				standardProductUnitPO.setIsAvailable(Product.getIsAvailable());
				standardProductUnitPO.setProductDetails(Product.getProductDetails());
				standardProductUnitPO.setPlatformId(Product.getPlatformId());
				standardProductUnitPO.setContent(Product.getContent());
				standardProductUnitPO.setEnterpriseId(Product.getEnterpriseId());
				standardProductUnitPO.setSupplierId(Product.getSupplierId());
				standardProductUnitPO.setCommodityTemplateId(Product.getCommodityTemplateId());
				if(Product.getEnterpriseId()!=null) {
					standardProductUnitPO.setEnterpriseId(Product.getEnterpriseId());
				}
				if(Product.getSupplierId()!=null) {
					standardProductUnitPO.setSupplierId(Product.getSupplierId());
				}
				standardProductUnitWriteManage.updateStandardProductUnitWithTx(standardProductUnitPO);
				standardProductUnitPO.setStatus(standardProductUnitDTO2.getStatus());

				// 同步保存spu记录信息
				standardProductUnitRecordId = saveStandardProductUnitRecord(standardProductUnitPO, userId, userName, ip,mac);

				/**
				 * 后期优化
				 */
				// 根据spuid查询spu属性信息
				StandardProductUnitAttNamePO standardProductUnitAttNamePO = new StandardProductUnitAttNamePO();
				standardProductUnitAttNamePO.setStandardProductUnitId(standardProductUnitPO.getId());
				List<StandardProductUnitAttNamePO> standardProductUnitAttNameList = standardProductUnitAttNameReadManage
						.findStandardProductUnitAttNameAll(standardProductUnitAttNamePO);

				logger.info("批量更新spu属性信息,spuId:" + productId);
				for (StandardProductUnitAttNamePO standardProductUnitAttNamePO2 : standardProductUnitAttNameList) {
					standardProductUnitAttNamePO.setAttNameId(standardProductUnitAttNamePO2.getAttNameId());

					// 根据属性id查询属性信息
					AttributeNamePO attributeNamePO = new AttributeNamePO();
					attributeNamePO.setId(standardProductUnitAttNamePO2.getAttNameId());
					AttributeNamePO attributeNamePO2 = attributeNameReadDAO.findById(attributeNamePO);

					// 根据spuid和属性id查询spu草稿属性信息(结果集只可能为一)
					ProductAttNamePO productAttNamePO = new ProductAttNamePO();
					productAttNamePO.setAttNameId(standardProductUnitAttNamePO2.getAttNameId());
					productAttNamePO.setProductId(standardProductUnitAttNamePO2.getStandardProductUnitId());
					List<ProductAttNamePO> productAttNameList = productAttNameReadManage.findAll(productAttNamePO);

					ProductAttNamePO productAttNamePO2 = productAttNameList.get(0);

					StandardProductUnitAttNamePO standardProductUnitAttNamePo = new StandardProductUnitAttNamePO();
					standardProductUnitAttNamePo.setId(standardProductUnitAttNamePO2.getId());
					standardProductUnitAttNamePo.setSortValue(productAttNamePO2.getSortValue());
					standardProductUnitAttNamePo.setShowPicture(productAttNamePO2.getShowPicture());
					standardProductUnitAttNameWriteDAO.update(standardProductUnitAttNamePo);

					// 同步保存spu属性记录信息
					StandardProductUnitAttNameRecordPO standardProductUnitAttNameRecordPO = new StandardProductUnitAttNameRecordPO();
					standardProductUnitAttNameRecordPO.setStandardProductUnitRecordId(standardProductUnitRecordId);
					standardProductUnitAttNameRecordPO.setParentId(productAttNamePO2.getParentId());
					standardProductUnitAttNameRecordPO.setAttNameId(productAttNamePO2.getAttNameId());
					standardProductUnitAttNameRecordPO.setSortValue(productAttNamePO2.getSortValue());
					standardProductUnitAttNameRecordPO.setType(productAttNamePO2.getType());
					standardProductUnitAttNameRecordPO.setPlatformId(productAttNamePO2.getPlatformId());
					standardProductUnitAttNameRecordPO.setShowPicture(productAttNamePO2.getShowPicture());
					Long standardProductUnitAttNameRecordId = standardProductUnitAttNameRecordWriteManage
							.insertStandardProductUnitAttNameRecordWithTx(standardProductUnitAttNameRecordPO);

					if (productAttNameList.size() == 1) {
						if (attributeNamePO2.getMode() == 2 || attributeNamePO2.getMode() == 1
								|| attributeNamePO2.getMode() == 4 || attributeNamePO2.getMode() == 5
								|| attributeNamePO2.getMode() == 6) {
							// 根据spu属性id查询spu属性值信息
							StandardProductUnitAttValuePO standardProductUnitAttValuePO = new StandardProductUnitAttValuePO();
							standardProductUnitAttValuePO
									.setStandardProductUnitAttNameId(standardProductUnitAttNamePO2.getId());
							List<StandardProductUnitAttValuePO> standardProductUnitAttValuePOList = standardProductUnitAttValueReadManage
									.findStandardProductUnitAttValueAll(standardProductUnitAttValuePO);

							// 根据spu草稿属性id查询spu草稿属性值信息
							ProductAttValuePO productAttValuePO = new ProductAttValuePO();
							productAttValuePO.setProductAttNameId(productAttNameList.get(0).getId());
							List<ProductAttValuePO> productAttValuePOList = productAttValueReadManage
									.findAll(productAttValuePO);
							// 如果属性类型为文本框则只有一个
							StandardProductUnitAttValuePO standardProductUnitAttValuePO2 = standardProductUnitAttValuePOList
									.get(0);
							standardProductUnitAttValuePO2
									.setAttValueCustom(productAttValuePOList.get(0).getAttValueCustom());
							standardProductUnitAttValuePO2.setAttValueId(productAttValuePOList.get(0).getAttValueId());
							standardProductUnitAttValuePO2.setSortValue(productAttValuePOList.get(0).getSortValue());
							standardProductUnitAttValueWriteManage
									.updateStandardProductUnitAttValueWithTx(standardProductUnitAttValuePO2);

						} else if(attributeNamePO2.getMode() == 3) {

							if (productAttNameList.get(0).getShowPicture() == 1) {
								showStandardProductUnitAttNameId = standardProductUnitAttNamePO2.getId();
							}
							// 根据spu属性id查询spu属性值信息
							StandardProductUnitAttValuePO standardProductUnitAttValuePO = new StandardProductUnitAttValuePO();
							standardProductUnitAttValuePO
									.setStandardProductUnitAttNameId(standardProductUnitAttNamePO2.getId());
							List<StandardProductUnitAttValuePO> standardProductUnitAttValuePOList =
									standardProductUnitAttValueReadManage.findStandardProductUnitAttValueAll(standardProductUnitAttValuePO);

							List<Long> standardProductUnitAttValueIds = new ArrayList<>();
							for (StandardProductUnitAttValuePO standardProductUnitAttValuePO2 : standardProductUnitAttValuePOList) {
								standardProductUnitAttValueIds.add(standardProductUnitAttValuePO2.getAttValueId());
							}

							// 根据spu草稿属性id查询spu草稿属性值信息
							ProductAttValuePO productAttValuePO = new ProductAttValuePO();
							productAttValuePO.setProductAttNameId(productAttNameList.get(0).getId());
							List<ProductAttValuePO> productAttValuePOList = productAttValueReadManage
									.findAll(productAttValuePO);

							List<Long> productAttValueIds = new ArrayList<>();
							for (ProductAttValuePO productAttValuePO2 : productAttValuePOList) {
								productAttValueIds.add(productAttValuePO2.getAttValueId());
							}

							// 添加不存在的属性值信息
							for (ProductAttValuePO productAttValuePO2 : productAttValuePOList) {
								if (!standardProductUnitAttValueIds.contains(productAttValuePO2.getAttValueId())) {
									List<StandardProductUnitAttNamePO> standardProductUnitAttNamePOList = new ArrayList<>();
									StandardProductUnitAttValuePO standardProductUnitAttValue = new StandardProductUnitAttValuePO();
									standardProductUnitAttValue.setStandardProductUnitAttNameId(standardProductUnitAttNamePO2.getId());
									standardProductUnitAttValue.setAttValueId(productAttValuePO2.getAttValueId());
									standardProductUnitAttValue.setAttValueCustom(productAttValuePO2.getAttValueCustom());
									standardProductUnitAttValue.setSortValue(productAttValuePO2.getSortValue());
									standardProductUnitAttValue.setPlatformId(productAttValuePO2.getPlatformId());
									standardProductUnitAttValue.setPictureUrl(productAttValuePO2.getPictureUrl());
									standardProductUnitAttValueWriteManage.insertStandardProductUnitAttValueWithTx(standardProductUnitAttValue);

									// 若属性为规格属性则需添加sku信息
									if (standardProductUnitAttNamePO2.getType() == 2) {
										// 因为是修改是新增的属性值，所以属性只需set新增的属性值与其余属性完成拼接
										List<StandardProductUnitAttValuePO> standardProductUnitAttValuePoList = new ArrayList<>();
										standardProductUnitAttValuePoList.add(standardProductUnitAttValue);
										standardProductUnitAttNamePO.setStandardProductUnitAttValueList(standardProductUnitAttValuePoList);
										// 根据spuid去除属性id为的所有spu属性信息
										List<StandardProductUnitAttNamePO> standardProductUnitAttName =
												standardProductUnitAttNameReadManage.findByStandardProductUnitIdAttNameId(
														standardProductUnitAttNamePO2.getStandardProductUnitId(),
														standardProductUnitAttNamePO2.getAttNameId());

										for (StandardProductUnitAttNamePO SPUAttName : standardProductUnitAttName) {
											if (EmptyUtil.isEmpty(showStandardProductUnitAttNameId)) {
												// 搜寻需要显示规格图片的规格属性id
												if (SPUAttName.getShowPicture() == 1) {
													showStandardProductUnitAttNameId = SPUAttName.getId();
												}
											}
											// 根据spu属性id查询spu属性值信息
											StandardProductUnitAttValuePO standardProductUnitAttValuePo = new StandardProductUnitAttValuePO();
											standardProductUnitAttValuePo.setStandardProductUnitAttNameId(SPUAttName.getId());
											List<StandardProductUnitAttValuePO> standardProductUnitAttValueList = standardProductUnitAttValueReadManage
													.findStandardProductUnitAttValueAll(standardProductUnitAttValuePo);
											SPUAttName.setStandardProductUnitAttValueList(standardProductUnitAttValueList);
										}
										// 其余属性
										standardProductUnitAttNamePOList.addAll(standardProductUnitAttName);
										// 当前新增属性
										standardProductUnitAttNamePOList.add(standardProductUnitAttNamePO);

										List<List<StandardProductUnitAttValuePO>> standardProductUnitAttValueLsit = new ArrayList<>();
										List<List<StandardProductUnitAttValuePO>> standardProductUnitAttValues = new ArrayList<>();
										List<List<StandardProductUnitAttValuePO>> acquire = acquire(
												standardProductUnitAttNamePOList, standardProductUnitAttValueLsit,
												standardProductUnitAttValues, 0);
										// 根据spuid查询sku默认图片
										/*
										 * String skuPicUrl = pictureReadManage
										 * .skuPicUrlByStandardProductUnitId(
										 * productId);
										 */
										skuSerialNumber++;
										saveSKU(skuSerialNumber,acquire, productId, standardProductUnitDTO2.getName(),
												standardProductUnitDTO2.getProductSerialNumber(),
												showStandardProductUnitAttNameId);
									}

								} else {
									for (StandardProductUnitAttValuePO standardProductUnitAttValuePO2 : standardProductUnitAttValuePOList) {
										if (productAttValuePO2.getAttValueId().equals(standardProductUnitAttValuePO2.getAttValueId())) {
											standardProductUnitAttValuePO2.setAttValueCustom(productAttValuePO2.getAttValueCustom());
											standardProductUnitAttValuePO2.setSortValue(productAttValuePO2.getSortValue());
											standardProductUnitAttValuePO2.setPlatformId(productAttValuePO2.getPlatformId());
											standardProductUnitAttValuePO2.setPictureUrl(productAttValuePO2.getPictureUrl());
											standardProductUnitAttValueWriteManage.updateStandardProductUnitAttValueWithTx(standardProductUnitAttValuePO2);
										}
									}

								}
							}

							// 删除不需要的属性值信息
							for (StandardProductUnitAttValuePO standardProductUnitAttValuePO2 : standardProductUnitAttValuePOList) {
								// 如果不包含则删除
								if (!productAttValueIds.contains(standardProductUnitAttValuePO2.getAttValueId())) {
									standardProductUnitAttValueWriteDAO.delete(standardProductUnitAttValuePO2);
								}
							}
						}else if(attributeNamePO2.getMode() == 7){
							// 因为多文本框判断哪些是新增的哪些是没有的比较复杂、此处采用先删除后添加的操作
							// 根据spu属性id删除属性值信息
							StandardProductUnitAttValuePO standardProductUnitAttValuePO = new StandardProductUnitAttValuePO();
							standardProductUnitAttValuePO.setStandardProductUnitAttNameId(standardProductUnitAttNamePO2.getId());
							standardProductUnitAttValueWriteDAO.deleteByPara(standardProductUnitAttValuePO);

							// 根据spu草稿属性id查询spu草稿属性值信息
							ProductAttValuePO productAttValuePO = new ProductAttValuePO();
							productAttValuePO.setProductAttNameId(productAttNameList.get(0).getId());
							List<ProductAttValuePO> productAttValuePOList = productAttValueReadManage.findAll(productAttValuePO);
							if(EmptyUtil.isNotEmpty(productAttValuePOList)){
								// 保存spu属性值信息
								saveSPUAttValue(standardProductUnitAttNamePO2.getId(),standardProductUnitAttNameRecordId,productAttValuePOList);
							}
						}

					} else {
						throw new BusinessException("spu草稿属性异常");
					}

				}

				// 同步保存spu详情记录表
				StandardProductUnitDescriptionRecordPO standardProductUnitDescriptionRecordPO = new StandardProductUnitDescriptionRecordPO();
				standardProductUnitDescriptionRecordPO.setStandardProductUnitRecordId(standardProductUnitRecordId);
				standardProductUnitDescriptionRecordPO.setContent(Product.getContent());
				standardProductUnitDescriptionRecordPO.setPlatformId(standardProductUnitDTO2.getPlatformId());
				standardProductUnitDescriptionRecordWriteManage
						.insertStandardProductUnitDescriptionRecordWithTx(standardProductUnitDescriptionRecordPO);

				// 根据spuid删除spu图片关系
				standardProductUnitPictureWriteManage.deleteByStandardProductUnitId(productId);

				// 同步spu图片
				savePicture(productId, standardProductUnitRecordId);
			} else {
				logger.info("根据spuid查询spu信息为空,开始新增spu信息,并生成spu产品记录,spuId:" + productId);
				// 通过spu名称查询spu信息
				StandardProductUnitPO standardProductUnitPO2 = new StandardProductUnitPO();
				standardProductUnitPO2.setName(Product.getName());
				standardProductUnitPO2.setPlatformId(Product.getPlatformId());
				List<StandardProductUnitPO> findStandardProductUnitAll = standardProductUnitReadManage
						.findStandardProductUnitAll(standardProductUnitPO2);
				if (EmptyUtil.isNotEmpty(findStandardProductUnitAll)) {
					throw new BusinessException("spu名称不能重复");
				}
				standardProductUnitPO.setProductSerialNumber(
						StringUtils.protectCategoryProductSerialNumber(categoryPO.getSerialNumber()) + StringUtils.protectProductSerialNumber(Product.getId()));
				standardProductUnitPO.setBrandId(Product.getBrandId());
				standardProductUnitPO.setCategoryTreeNodeId(Product.getCategoryTreeNodeId());
				standardProductUnitPO.setProductCategory(Product.getProductCategory());
				standardProductUnitPO.setTitle(Product.getTitle());
				standardProductUnitPO.setName(Product.getName());
				standardProductUnitPO.setChineseName(Product.getChineseName());
				standardProductUnitPO.setMarketPrice(Product.getMarketPrice());
				standardProductUnitPO.setTaxNo(Product.getTaxNo());
				standardProductUnitPO.setEanNo(Product.getEanNo());
				standardProductUnitPO.setPlaceOfOrigin(Product.getPlaceOfOrigin());
				standardProductUnitPO.setCalculationUnit(Product.getCalculationUnit());
				standardProductUnitPO.setStatus(3);
				standardProductUnitPO.setIsAvailable(Product.getIsAvailable());

				if(Product.getEnterpriseId()!=null) {
					standardProductUnitPO.setEnterpriseId(Product.getEnterpriseId());
				}
				if(Product.getSupplierId()!=null) {
					standardProductUnitPO.setSupplierId(Product.getSupplierId());
				}
				standardProductUnitPO.setProductDetails(Product.getProductDetails());
				standardProductUnitPO.setPlatformId(Product.getPlatformId());
				standardProductUnitPO.setCommodityTemplateId(Product.getCommodityTemplateId());
				standardProductUnitWriteManage.insertStandardProductUnitWithTx(standardProductUnitPO);
				// 同步保存spu记录信息
				standardProductUnitRecordId = saveStandardProductUnitRecord(standardProductUnitPO, userId, userName, ip,mac);

				// 同步保存spu草稿详情
				StandardProductUnitDescriptionPO standardProductUnitDescriptionPO = new StandardProductUnitDescriptionPO();
				standardProductUnitDescriptionPO.setStandardProductUnitId(productId);
				standardProductUnitDescriptionPO.setContent(Product.getContent());
				standardProductUnitDescriptionPO.setPlatformId(Product.getPlatformId());
				standardProductUnitDescriptionWriteManage
						.insertStandardProductUnitDescriptionWithTx(standardProductUnitDescriptionPO);

				// 同步保存spu详情记录表
				StandardProductUnitDescriptionRecordPO standardProductUnitDescriptionRecordPO = new StandardProductUnitDescriptionRecordPO();
				standardProductUnitDescriptionRecordPO.setStandardProductUnitRecordId(standardProductUnitRecordId);
				standardProductUnitDescriptionRecordPO.setContent(Product.getContent());
				standardProductUnitDescriptionRecordPO.setPlatformId(Product.getPlatformId());
				standardProductUnitDescriptionRecordWriteManage
						.insertStandardProductUnitDescriptionRecordWithTx(standardProductUnitDescriptionRecordPO);

				// 根据spu草稿id查询spu草稿属性信息
				ProductAttNamePO productAttNameDTO = new ProductAttNamePO();
				productAttNameDTO.setProductId(Product.getId());
				List<ProductAttNamePO> productAttNameList = productAttNameReadManage.findAll(productAttNameDTO);
				for (ProductAttNamePO productAttNameDTO2 : productAttNameList) {
					// 同步保存spu属性信息
					StandardProductUnitAttNamePO standardProductUnitAttNamePo = new StandardProductUnitAttNamePO();
					standardProductUnitAttNamePo.setStandardProductUnitId(productId);
					standardProductUnitAttNamePo.setParentId(productAttNameDTO2.getParentId());
					standardProductUnitAttNamePo.setAttNameId(productAttNameDTO2.getAttNameId());
					standardProductUnitAttNamePo.setSortValue(productAttNameDTO2.getSortValue());
					standardProductUnitAttNamePo.setType(productAttNameDTO2.getType());
					standardProductUnitAttNamePo.setPlatformId(productAttNameDTO2.getPlatformId());
					standardProductUnitAttNamePo.setShowPicture(productAttNameDTO2.getShowPicture());
					Long standardProductUnitAttNameId = standardProductUnitAttNameWriteManage
							.insertStandardProductUnitAttNameWithTx(standardProductUnitAttNamePo);

					// 同步保存spu属性记录信息
					StandardProductUnitAttNameRecordPO standardProductUnitAttNameRecordPO = new StandardProductUnitAttNameRecordPO();
					standardProductUnitAttNameRecordPO.setStandardProductUnitRecordId(standardProductUnitRecordId);
					standardProductUnitAttNameRecordPO.setParentId(productAttNameDTO2.getParentId());
					standardProductUnitAttNameRecordPO.setAttNameId(productAttNameDTO2.getAttNameId());
					standardProductUnitAttNameRecordPO.setSortValue(productAttNameDTO2.getSortValue());
					standardProductUnitAttNameRecordPO.setType(productAttNameDTO2.getType());
					standardProductUnitAttNameRecordPO.setPlatformId(productAttNameDTO2.getPlatformId());
					standardProductUnitAttNameRecordPO.setShowPicture(productAttNameDTO2.getShowPicture());
					Long standardProductUnitAttNameRecordId = standardProductUnitAttNameRecordWriteManage
							.insertStandardProductUnitAttNameRecordWithTx(standardProductUnitAttNameRecordPO);
					// 根据spu草稿属性id查询spu草稿属性值信息
					ProductAttValuePO productAttValuePO = new ProductAttValuePO();
					productAttValuePO.setProductAttNameId(productAttNameDTO2.getId());
					List<ProductAttValuePO> productAttValueList = productAttValueReadManage.findAll(productAttValuePO);

					if(EmptyUtil.isNotEmpty(productAttValueList)){
						// 保存spu属性值信息
						saveSPUAttValue(standardProductUnitAttNameId,standardProductUnitAttNameRecordId,productAttValueList);
					}
				}
				// 同步spu图片
				savePicture(productId, standardProductUnitRecordId);
			}
		}

		return rows;
	}
	/**
	 * 保存spu属性值信息
	 * @param productAttValueList
	 */
	private void saveSPUAttValue(Long standardProductUnitAttNameId,Long standardProductUnitAttNameRecordId,List<ProductAttValuePO> productAttValueList) {
		List<StandardProductUnitAttValuePO> standardProductUnitAttValueList = new ArrayList<>();
		List<StandardProductUnitAttValueRecordPO> standardProductUnitAttValueRecordList = new ArrayList<>();
		for (ProductAttValuePO productAttValueDTO2 : productAttValueList) {
			// 同步保存spu属性值信息
			StandardProductUnitAttValuePO standardProductUnitAttValuePO = new StandardProductUnitAttValuePO();
			standardProductUnitAttValuePO.setStandardProductUnitAttNameId(standardProductUnitAttNameId);
			standardProductUnitAttValuePO.setAttValueId(productAttValueDTO2.getAttValueId());
			standardProductUnitAttValuePO.setAttValueCustom(productAttValueDTO2.getAttValueCustom());
			standardProductUnitAttValuePO.setSortValue(productAttValueDTO2.getSortValue());
			standardProductUnitAttValuePO.setPictureUrl(productAttValueDTO2.getPictureUrl());
			standardProductUnitAttValuePO.setPlatformId(productAttValueDTO2.getPlatformId());
			standardProductUnitAttValueList.add(standardProductUnitAttValuePO);

			// 同步保存spu属性值记录信息
			StandardProductUnitAttValueRecordPO standardProductUnitAttValueRecordPO = new StandardProductUnitAttValueRecordPO();
			standardProductUnitAttValueRecordPO.setStandardProductUnitAttNameRecordId(standardProductUnitAttNameRecordId);
			standardProductUnitAttValueRecordPO.setAttValueId(productAttValueDTO2.getAttValueId());
			standardProductUnitAttValueRecordPO.setAttValueCustom(productAttValueDTO2.getAttValueCustom());
			standardProductUnitAttValueRecordPO.setSortValue(productAttValueDTO2.getSortValue());
			standardProductUnitAttValueRecordPO.setPictureUrl(productAttValueDTO2.getPictureUrl());
			standardProductUnitAttValueRecordPO.setPlatformId(productAttValueDTO2.getPlatformId());
			standardProductUnitAttValueRecordList.add(standardProductUnitAttValueRecordPO);
		}
		// 批量保存spu属性值信息
		standardProductUnitAttValueWriteDAO.insertAll(standardProductUnitAttValueList);

		// 批量保存spu属性值记录信息
		standardProductUnitAttValueRecordWriteDAO.insertAll(standardProductUnitAttValueRecordList);

	}

	public List<Long> assembleSku(Long productId, String standardProductUnitName, String productSerialNumber) {
		SkuPO sku = new SkuPO();
		sku.setStandardProductUnitId(productId);
		List<SkuCondition> skuList = skuReadManage.findSkuAll(sku);
		int skuSerialNumber = skuList.size();

		Long showStandardProductUnitAttNameId = null;
		// 根据spuid查询spu属性信息
		StandardProductUnitAttNamePO standardProductUnitAttNamePO = new StandardProductUnitAttNamePO();
		standardProductUnitAttNamePO.setStandardProductUnitId(productId);
		standardProductUnitAttNamePO.setType(2);
		List<StandardProductUnitAttNamePO> standardProductUnitAttName = standardProductUnitAttNameReadManage
				.findStandardProductUnitAttNameAll(standardProductUnitAttNamePO);
		for (StandardProductUnitAttNamePO standardProductUnitAttNamePO1 : standardProductUnitAttName) {
			// 根据spu属性id查询spu属性值信息
			StandardProductUnitAttValuePO standardProductUnitAttValuePO = new StandardProductUnitAttValuePO();
			standardProductUnitAttValuePO.setStandardProductUnitAttNameId(standardProductUnitAttNamePO1.getId());
			List<StandardProductUnitAttValuePO> standardProductUnitAttValueList = standardProductUnitAttValueReadManage
					.findStandardProductUnitAttValueAll(standardProductUnitAttValuePO);
			if (standardProductUnitAttNamePO1.getShowPicture() == 1) {
				showStandardProductUnitAttNameId = standardProductUnitAttNamePO1.getId();
			}
			standardProductUnitAttNamePO1.setStandardProductUnitAttValueList(standardProductUnitAttValueList);
		}

		List<List<StandardProductUnitAttValuePO>> standardProductUnitAttValueLsit = new ArrayList<>();
		List<List<StandardProductUnitAttValuePO>> standardProductUnitAttValues = new ArrayList<>();
		List<List<StandardProductUnitAttValuePO>> acquire = acquire(standardProductUnitAttName,
				standardProductUnitAttValueLsit, standardProductUnitAttValues, 0);
		// 根据spuid查询sku默认图片
		// String skuPicUrl =
		// pictureReadManage.skuPicUrlByStandardProductUnitId(productId);
		skuSerialNumber++;
		List<Long> list = saveSKU(skuSerialNumber ,acquire, productId, standardProductUnitName, productSerialNumber,
				showStandardProductUnitAttNameId);
		return list;
	}

	@Override
	public void saveProductList(List<ProductPO> productPOList) {
		try {
			productWriteDAO.saveProductList(productPOList);
		}catch (Exception e){
			logger.error("saveProductList失败,e:"+e.getMessage());
		}
	}

	@Override
	public void updateProductList(List<ProductPO> productPOList) {
		try {
			productWriteDAO.updateProductList(productPOList);
		}catch (Exception e){
			logger.error("updateProductList失败,e:"+e.getMessage());
		}
	}

	/**
	 * 根据规格信息拼接sku
	 *
	 * @param standardProductUnitAttName
	 * @param standardProductUnitAttValueLsit
	 * @param standardProductUnitAttValues
	 * @param j
	 * @return
	 */
	private List<List<StandardProductUnitAttValuePO>> acquire(
			List<StandardProductUnitAttNamePO> standardProductUnitAttName,
			List<List<StandardProductUnitAttValuePO>> standardProductUnitAttValueLsit,
			List<List<StandardProductUnitAttValuePO>> standardProductUnitAttValues, int j) {
		List<List<StandardProductUnitAttValuePO>> standardProductUnitAttValue = new ArrayList<>();
		if (j < standardProductUnitAttName.size()) {
			StandardProductUnitAttNamePO standardProductUnitAttNameDTO2 = standardProductUnitAttName.get(j);
			// 取属性值集合
			List<StandardProductUnitAttValuePO> standardProductUnitAttValueList = standardProductUnitAttNameDTO2
					.getStandardProductUnitAttValueList();

			for (StandardProductUnitAttValuePO standardProductUnitAttValuePO : standardProductUnitAttValueList) {
				standardProductUnitAttValuePO.setAttNameId(standardProductUnitAttNameDTO2.getAttNameId());

				if (j != 0) {

					for (List<StandardProductUnitAttValuePO> list : standardProductUnitAttValueLsit) {
						List<StandardProductUnitAttValuePO> attValueDTOs = new ArrayList<>();
						attValueDTOs.add(standardProductUnitAttValuePO);
						attValueDTOs.addAll(list);
						standardProductUnitAttValue.add(attValueDTOs);
					}
				} else {
					List<StandardProductUnitAttValuePO> lsit = new ArrayList<>();
					lsit.add(standardProductUnitAttValuePO);
					standardProductUnitAttValue.add(lsit);
				}

			}
			j = j + 1;
			return acquire(standardProductUnitAttName, standardProductUnitAttValue, standardProductUnitAttValues, j);
		}
		standardProductUnitAttValues = standardProductUnitAttValueLsit;
		return standardProductUnitAttValues;

	}

	/**
	 * 批量保存sku
	 *
	 * @param acquire
	 * @param standardProductUnitId
	 * @param standardProductUnitName
	 * @param productSerialNumber
	 * @return
	 */

	private List<Long> saveSKU(int skuSerialNumber,List<List<StandardProductUnitAttValuePO>> acquire, Long standardProductUnitId,
			String standardProductUnitName, String productSerialNumber, Long showStandardProductUnitAttNameId) {
		List<Long> skuIdList = new ArrayList<>();
		/*SkuPO sku = new SkuPO();
		sku.setStandardProductUnitId(standardProductUnitId);
		List<SkuCondition> skuList = skuReadManage.findSkuAll(sku);
		skuSerialNumber=skuList.size()+1;*/

		Integer priceType = null;
		String commonMarketPrice = null;
		String commonSupplierPrice = null;
		String commonTaxRate=null;
		ProductCondition product = productReadManage.productAndProductDescriptionById(standardProductUnitId);
		List<Map<String,Object>> prices = null;
		if(product.getPriceDetail()!=null && product.getPriceDetail().length()>5) {
			try {


				JSONObject priceDetail = JSON.parseObject(product.getPriceDetail());
				if(priceDetail.getIntValue("priceType")==1) {
					//统一价格
					priceType = 1 ;
					commonMarketPrice = priceDetail.getString("marketPrice");
					commonSupplierPrice = priceDetail.getString("supplierPrice");
					commonTaxRate = priceDetail.getString("taxRate");
				}else if(priceDetail.getIntValue("priceType")==2){
					priceType = 2 ;
					commonMarketPrice = null;
					commonSupplierPrice = null;
					JSONArray priceList = priceDetail.getJSONArray("priceList");
					for(int index = 0 ; index<priceList.size();index++) {
						JSONObject priceObj = priceList.getJSONObject(index);
						JSONArray attrValueArray = priceObj.getJSONArray("attrValueIds");
						long[] result = new long[attrValueArray.size()]; // 创建与 JSONArray 长度相同的 long 类型数组

				        for (int i = 0; i < attrValueArray.size(); i++) {
				            result[i] = attrValueArray.getLong(i); // 从 JSONArray 中获取第 i 个元素的值，并保存到 result 数组中
				        }
				        Arrays.sort(result);
				        StringBuilder sb = new StringBuilder();
				        for (long num : result) {
				            sb.append(num).append("-");
				        }

				        String ids = sb.toString().trim();
						String supplierPrice = priceObj.getString("supplierPrice");
						String marketPrice = priceObj.getString("marketPrice");
						String taxRate = priceObj.getString("taxRate");
						if(prices==null) {
							prices = new ArrayList<Map<String,Object>>();
						}
						Map<String,Object> pp = new HashMap<String,Object>();
						pp.put("supplierPrice", supplierPrice);
						pp.put("marketPrice", marketPrice);
						pp.put("taxRate",taxRate);
						pp.put("ids", ids);
						prices.add(pp);
					}
				}



			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		for (List<StandardProductUnitAttValuePO> list : acquire) {
			SkuPO skuPO = new SkuPO();

			// 拼接sku规格码
			StringBuffer stringBuffer = new StringBuffer();
			// 拼接sku名称
			StringBuffer skuName = new StringBuffer();
			skuName.append(standardProductUnitName);
			long[] attValueIds = new long[list.size()];
			for (int j = 0; j < list.size(); j++) {
				// 通过spu属性值id查询属性值名称
				AttributeValuePO attributeValuePO = new AttributeValuePO();
				attributeValuePO.setId(list.get(j).getAttValueId());
				attValueIds[j] = list.get(j).getAttValueId();
				AttributeValuePO attributeValuePO2 = attributeValueReadManage.findById(attributeValuePO);
				if (EmptyUtil.isEmpty(attributeValuePO2)) {
					throw new BusinessException("根据属性id：" + list.get(j).getAttValueId() + "未查询到属性值数据");
				}
				skuName.append(" " + attributeValuePO2.getValue());
				stringBuffer.append(attributeValuePO2.getSpecificationCode());
				if (j < list.size() - 1) {
					stringBuffer.append("-");
				}
				// 根据显示的spu规格属性id将spu属性值的spu图片赋值
					if (showStandardProductUnitAttNameId.equals(list.get(j).getStandardProductUnitAttNameId())) {
						skuPO.setSkuPicUrl(list.get(j).getPictureUrl());
					}
			}
			Arrays.sort(attValueIds);
			StringBuilder sb = new StringBuilder();
	        for (long num : attValueIds) {
	            sb.append(num).append("-");
	        }

	        String attValueIdsStr = sb.toString().trim();
	        if(priceType!=null) {
	        	if(priceType.intValue()==1) {
	        		if(commonSupplierPrice!=null && commonSupplierPrice.length()>1) {
		        		skuPO.setSkuCostingPrice(new BigDecimal(commonSupplierPrice));
	        		}
	        		if(commonMarketPrice!=null && commonMarketPrice.length()>1) {
		        		skuPO.setSkuMarketPrice(new BigDecimal(commonMarketPrice));
	        		}
					if(commonTaxRate!=null && commonTaxRate.length()>0) {
						skuPO.setTaxRate(new BigDecimal(commonTaxRate));
					}
	        	}else if(priceType.intValue()==2) {
	        		for(Map<String,Object> price :prices) {
	        			if(price.containsKey("ids") && price.get("ids").toString().equalsIgnoreCase(attValueIdsStr)) {
	        				if(price.containsKey("supplierPrice")) {
	    		        		skuPO.setSkuCostingPrice(new BigDecimal(price.get("supplierPrice").toString()));
	    	        		}
	    	        		if(price.containsKey("marketPrice")) {
	    		        		skuPO.setSkuMarketPrice(new BigDecimal(price.get("marketPrice").toString()));
	    	        		}
							if(price.containsKey("taxRate") && Objects.nonNull(price.get("taxRate"))) {
								skuPO.setTaxRate(new BigDecimal(price.get("taxRate").toString()));
							}
	        			}
	        		}
	        	}
	        }
			skuPO.setStandardProductUnitId(standardProductUnitId);
			skuPO.setCode(stringBuffer.toString());

			skuPO.setSkuSerialNumber(productSerialNumber + StringUtils.skuSerialNumber(skuSerialNumber));
			skuSerialNumber = skuSerialNumber + 1;
			// 默认有效
			skuPO.setIsAvailable(1);
			// 默认1迩格
			skuPO.setMerchantId(1L);
			skuPO.setPlatformId(list.get(0).getPlatformId());
			skuPO.setSkuName(skuName.toString());
			Long skuId = skuWriteManage.insertSkuWithTx(skuPO);

			for (StandardProductUnitAttValuePO standardProductUnitAttValueDTO : list) {
				SkuAttNamePO skuAttNamePO = new SkuAttNamePO();
				skuAttNamePO.setAttNameId(standardProductUnitAttValueDTO.getAttNameId());
				skuAttNamePO.setSkuId(skuId);
				Long skuAttNameId = skuAttNameWriteManage.insertSkuAttNameWithTx(skuAttNamePO);
				SkuAttValuePO skuAttValuePO = new SkuAttValuePO();
				skuAttValuePO.setAttValueId(standardProductUnitAttValueDTO.getAttValueId());
				skuAttValuePO.setSkuAttNameId(skuAttNameId);
				skuAttValueWriteManage.insertSkuAttValueWithTx(skuAttValuePO);

			}
			skuIdList.add(skuId);

		}

		return skuIdList;

	}

	/**
	 * 更新sku信息
	 * @param standardProductUnitId
	 */
	public void updateProductSkuWithTx(Long standardProductUnitId) {
		ProductCondition product = productReadManage.productAndProductDescriptionById(standardProductUnitId);
		if(product.getPriceDetail()!=null && product.getPriceDetail().length()>5) {
			try {
				JSONObject priceDetail = JSON.parseObject(product.getPriceDetail());
				if(priceDetail.getIntValue("priceType")==1) {
					//统一价格
					String commonMarketPrice = priceDetail.getString("marketPrice");
					String commonSupplierPrice = priceDetail.getString("supplierPrice");
					SkuPO sku = new SkuPO();
					sku.setStandardProductUnitId(standardProductUnitId);
					List<SkuCondition> skuList = skuReadManage.findSkuAll(sku);
					if (EmptyUtil.isNotEmpty(skuList)){
						sku=new SkuPO();
						sku.setId(skuList.get(0).getId());
						if(commonSupplierPrice!=null && commonSupplierPrice.length()>1) {
							sku.setSkuCostingPrice(new BigDecimal(commonSupplierPrice));
						}
						if(commonMarketPrice!=null && commonMarketPrice.length()>1) {
							sku.setSkuMarketPrice(new BigDecimal(commonMarketPrice));
						}
						if ((commonSupplierPrice!=null && commonSupplierPrice.length()>1)
								||(commonMarketPrice!=null && commonMarketPrice.length()>1)){
							skuWriteManage.updateSkuWithTx(sku);
						}
					}
				}else if(priceDetail.getIntValue("priceType")==2){
					JSONArray priceList = priceDetail.getJSONArray("priceList");
					for(int index = 0 ; index<priceList.size();index++) {
						JSONObject priceObj = priceList.getJSONObject(index);
						JSONArray attrValueArray = priceObj.getJSONArray("attrValueIds");
						long[] result = new long[attrValueArray.size()]; // 创建与 JSONArray 长度相同的 long 类型数组
						for (int i = 0; i < attrValueArray.size(); i++) {
							result[i] = attrValueArray.getLong(i); // 从 JSONArray 中获取第 i 个元素的值，并保存到 result 数组中
						}
						String supplierPrice = priceObj.getString("supplierPrice");
						String marketPrice = priceObj.getString("marketPrice");
						if (EmptyUtil.isNotEmpty(result)
								&& ((marketPrice!=null && marketPrice.length()>1) ||(supplierPrice!=null && supplierPrice.length()>1))){
							Long skuId=skuReadDAO.findSkuIdAndAttValueId(standardProductUnitId,result[0]);
							if (Objects.nonNull(skuId)){
								SkuPO sku=new SkuPO();
								sku.setId(skuId);
								if(marketPrice!=null && marketPrice.length()>1) {
									sku.setSkuMarketPrice(new BigDecimal(marketPrice));
								}
								if(supplierPrice!=null && supplierPrice.length()>1) {
									sku.setSkuCostingPrice(new BigDecimal(supplierPrice));
								}
								skuWriteManage.updateSkuWithTx(sku);
							}
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 生成spu记录信息
	 *
	 * @param standardProductUnitPO
	 * @param userId
	 * @param userName
	 * @param ip
	 * @param mac
	 * @return
	 */
	private Long saveStandardProductUnitRecord(StandardProductUnitPO standardProductUnitPO, Long userId,
			String userName, String ip, String mac) {
		// 同步添加spu记录
		StandardProductUnitRecordPO standardProductUnitRecordPO = new StandardProductUnitRecordPO();
		standardProductUnitRecordPO.setStandardProductUnitId(standardProductUnitPO.getId());
		standardProductUnitRecordPO.setProductSerialNumber(standardProductUnitPO.getProductSerialNumber());
		standardProductUnitRecordPO.setBrandId(standardProductUnitPO.getBrandId());
		standardProductUnitRecordPO.setCategoryTreeNodeId(standardProductUnitPO.getCategoryTreeNodeId());
		standardProductUnitRecordPO.setTitle(standardProductUnitPO.getTitle());
		standardProductUnitRecordPO.setName(standardProductUnitPO.getName());
		standardProductUnitRecordPO.setChineseName(standardProductUnitPO.getChineseName());
		standardProductUnitRecordPO.setMarketPrice(standardProductUnitPO.getMarketPrice());
		standardProductUnitRecordPO.setTaxNo(standardProductUnitPO.getTaxNo());
		standardProductUnitRecordPO.setEanNo(standardProductUnitPO.getEanNo());
		standardProductUnitRecordPO.setPlaceOfOrigin(standardProductUnitPO.getPlaceOfOrigin());
		standardProductUnitRecordPO.setCalculationUnit(standardProductUnitPO.getCalculationUnit());
		standardProductUnitRecordPO.setStatus(3);
		standardProductUnitRecordPO.setIsAvailable(standardProductUnitPO.getIsAvailable());
		standardProductUnitRecordPO.setProductDetails(standardProductUnitPO.getProductDetails());
		standardProductUnitRecordPO.setCreateUserid(userId);
		standardProductUnitRecordPO.setCreateUsername(userName);
		standardProductUnitRecordPO.setCreateUserip(ip);
		standardProductUnitRecordPO.setCreateUsermac(mac);
		standardProductUnitRecordPO.setPlatformId(standardProductUnitPO.getPlatformId());
		return standardProductUnitRecordWriteManage.insertStandardProductUnitRecordWithTx(standardProductUnitRecordPO);
	}

	/**
	 *
	 * @param productId
	 * @param standardProductUnitRecordId
	 * @return
	 */
	private boolean savePicture(Long productId, Long standardProductUnitRecordId) {
		// 根据spu草稿id查询spu草稿图片信息
		ProductPicturePO productPicturePO = new ProductPicturePO();
		productPicturePO.setProductId(productId);
		List<ProductPicturePO> ProductPictureList = productPictureReadManage.findProductPictureAll(productPicturePO);
		for (ProductPicturePO productPictureDTO2 : ProductPictureList) {
			// 同步保存spu图片信息
			StandardProductUnitPicturePO standardProductUnitPicturePO = new StandardProductUnitPicturePO();
			standardProductUnitPicturePO.setStandardProductUnitId(productId);
			standardProductUnitPicturePO.setPictureId(productPictureDTO2.getPictureId());
			standardProductUnitPicturePO.setSortValue(productPictureDTO2.getSortValue());
			standardProductUnitPicturePO.setType(productPictureDTO2.getType());
			standardProductUnitPicturePO.setPlatformId(productPictureDTO2.getPlatformId());
			standardProductUnitPictureWriteManage.insertStandardProductUnitPictureWithTx(standardProductUnitPicturePO);

			// 同步保存sputp记录信息
			StandardProductUnitPictureRecordPO standardProductUnitPictureRecordPO = new StandardProductUnitPictureRecordPO();
			standardProductUnitPictureRecordPO.setStandardProductUnitRecordId(standardProductUnitRecordId);
			standardProductUnitPictureRecordPO.setPictureId(productPictureDTO2.getPictureId());
			standardProductUnitPictureRecordPO.setSortValue(productPictureDTO2.getSortValue());
			standardProductUnitPicturePO.setType(productPictureDTO2.getType());
			standardProductUnitPictureRecordPO.setPlatformId(productPictureDTO2.getPlatformId());
			standardProductUnitPictureRecordWriteManage
					.insertStandardProductUnitPictureRecordWithTx(standardProductUnitPictureRecordPO);
		}
		return false;
	}

	@Override
	public Long insertProductWithTx(ProductPO po, ProductDescriptionPO po2) {
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getType()!=null && RuntimeContext.cacheUser().getEnterpriseId()!=null ) {
			if(RuntimeContext.cacheUser().getType().intValue()==5) {
				if(po.getSupplierId()==null) {
					po.setSupplierId(RuntimeContext.cacheUser().getEnterpriseId());
				}
			}else if(RuntimeContext.cacheUser().getType().intValue()>1		) {
				if(po.getEnterpriseId()==null) {
					po.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
				}
			}
		}
		productWriteDAO.insert(po);

		if (EmptyUtil.isNotEmpty(po.getId())) {
			po2.setProductId(po.getId());
			productDescriptionWriteDAO.insert(po2);
		}
		return po.getId();
	}

	/**
	 * 设置是否启用
	 *
	 * @return
	 */
	@Override
	public int updateAvailableWithTx(ProductPO po) {
		// TODO Auto-generated method stub
		return productWriteDAO.update(po);
	}

	/**
	 * 删除所有点击添加规格属性没有点下一步所创建的空spu草稿信息
	 *
	 * @return
	 */
	@Override
	public int delByNullProductWithTx() {
		// TODO Auto-generated method stub
		return productWriteDAO.delByNullProduct();
	}
}
