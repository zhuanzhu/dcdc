package com.egeo.components.product.business.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.product.business.*;
import com.egeo.components.product.controller.api.StandardUnitAction;
import com.egeo.components.product.dto.*;
import com.egeo.components.product.facade.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.egeo.common.BusinessConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.converter.AttributeNameConverter;
import com.egeo.components.product.converter.BrandConverter;
import com.egeo.components.product.converter.PictureConverter;
import com.egeo.components.product.converter.ProductConverter;
import com.egeo.components.product.converter.ProductDescriptionConverter;
import com.egeo.components.product.converter.ProductPictureConverter;
import com.egeo.components.product.converter.StandardProductUnitConverter;
import com.egeo.components.product.vo.AttName;
import com.egeo.components.product.vo.AttNameValueVO;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.vo.AttributeValueVO;
import com.egeo.components.product.vo.BrandVO;
import com.egeo.components.product.vo.CategoryAttValueVO;
import com.egeo.components.product.vo.PictureVO;
import com.egeo.components.product.vo.ProductAttNameVO;
import com.egeo.components.product.vo.ProductAttValueVO;
import com.egeo.components.product.vo.ProductDescriptionVO;
import com.egeo.components.product.vo.ProductPictureVO;
import com.egeo.components.product.vo.ProductVO;
import com.egeo.components.product.vo.Value;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("product")
public class ProductManageImpl implements ProductManage {
	public Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Resource(name = "categoryTreeNodeFacade")
	private CategoryTreeNodeFacade categoryTreeNodeFacade;

	@Resource(name = "attributeValueFacade")
	private AttributeValueFacade attributeValueFacade;

	@Resource(name = "categoryAttNameFacade")
	private CategoryAttNameFacade categoryAttNameFacade;

	@Resource(name = "categoryAttValueFacade")
	private CategoryAttValueFacade categoryAttValueFacade;

	@Resource(name = "productDescription")
	private ProductDescriptionManage productDescriptionManage;

	@Resource(name = "picture")
	private PictureManage pictureManage;

	@Resource(name = "productPicture")
	private ProductPictureManage productPictureManage;

	@Resource(name = "attributeName")
	private AttributeNameManage attributeNameManage;

	@Resource(name = "productAttName")
	private ProductAttNameManage productAttNameManage;

	@Resource(name = "productAttValue")
	private ProductAttValueManage productAttValueManage;

	@Resource(name = "categoryTreeNode")
	private CategoryTreeNodeManage categoryTreeNodeManage;

	@Resource(name = "categoryFacade")
	private CategoryFacade categoryFacade;

	@Resource(name = "brandFacade")
	private BrandFacade brandFacade;

	@Resource(name = "standardProductUnitFacade")
	private StandardProductUnitFacade standardProductUnitFacade;

	@Resource(name = "standardUnitFacade")
	private StandardUnitFacade standardUnitFacade;

	@Autowired
	public StandardUnitAction standardUnitAction;

	@Override
	public PageResult<ProductDTO> findPage(Pagination page, ProductVO productVO, List<String> nameList) {

		return productFacade.findPage(page, ProductConverter.toDTO(productVO),nameList);
	}

	/**
	 * 废除
	 */
	@Override
	public String saveProduct(ProductVO productVO, ProductDescriptionVO productDescriptionVO, PictureVO pictureVO,
			BrandVO brandVO, List<AttNameValueVO> lists, List<AttName> apecification,
			List<AttNameValueVO> parameterAtts) {

		// 通过类目名称类目信息（类目名称唯一），通过类目id查询他所在的节点
		ProductDTO dto = ProductConverter.toDTO(productVO);
		/*
		 * CacheUser userCache = (CacheUser) req.getAttribute("ut");
		 * dto.setPlatformId(userCache.getPlatformId());
		 * dto.setCreateUserid(userCache.getId());
		 * dto.setCreateUserip(CommonUtils.getUserIp(req));
		 * dto.setCreateTime(new Date());
		 * dto.setCreateUsername(userCache.getName());
		 * dto.setUpdateUserid(userCache.getId());
		 * dto.setUpdateUserip(CommonUtils.getUserIp(req));
		 * dto.setUpdateUsername(userCache.getName()); dto.setUpdateTime(new
		 * Date());
		 */

		if (EmptyUtil.isEmpty(productVO.getName())) {
			throw new BusinessException("产品名称不能为空");
		}
		if (EmptyUtil.isEmpty(productVO.getChineseName())) {
			throw new BusinessException("品牌名称不能为空");
		}

		List<AttNameValueDTO> list = new ArrayList<AttNameValueDTO>();
		for (AttNameValueVO attNameValueVO : lists) {
			AttNameValueDTO attNameValueDTO = new AttNameValueDTO();
			attNameValueDTO.setKey(attNameValueVO.getKey());
			attNameValueDTO.setValue(attNameValueVO.getValue());
			attNameValueDTO.setUrl(attNameValueVO.getUrl());
			attNameValueDTO.setType(1);
			list.add(attNameValueDTO);
		}
		for (AttNameValueVO attNameValueVO : parameterAtts) {
			AttNameValueDTO attNameValueDTO = new AttNameValueDTO();
			attNameValueDTO.setKey(attNameValueVO.getKey());
			attNameValueDTO.setValue(attNameValueVO.getValue());
			attNameValueDTO.setUrl(attNameValueVO.getUrl());
			attNameValueDTO.setType(3);
			list.add(attNameValueDTO);
		}
		Long productId = productFacade.saveProduct(dto, ProductDescriptionConverter.toDTO(productDescriptionVO),
				PictureConverter.toDTO(pictureVO), BrandConverter.toDTO(brandVO), list, apecification);

		return String.valueOf(productId);
	}

	@Override
	public ProductVO productById(ProductVO productVO, CategoryAttValueVO categoryAttValueVO,
			ProductDescriptionVO productDescriptionVO, PictureVO pictureVO, ProductPictureVO productPictureVO,
			AttributeValueVO attributeValueVO, AttributeNameVO attributeNameVO) {
		// 根据产品id查询产品信息及产品详情
		ProductDTO productDTO = productFacade.productAndProductDescriptionById(productVO.getId());
		ProductVO productVO2 = ProductConverter.toVO(productDTO);
		// 将产品描述信息添加到产品信息里
		productVO2.setContent(productDTO.getContent());

		// 根据节点id查询节点与类目的关系信息
		CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
		categoryTreeNodeDTO.setId(productDTO.getCategoryTreeNodeId());
		CategoryTreeNodeDTO treeNodeDTO = categoryTreeNodeFacade.findById(categoryTreeNodeDTO);
		if (EmptyUtil.isEmpty(treeNodeDTO)) {
			logger.info("没有该类目信息，节点id为:" + productDTO.getCategoryTreeNodeId());
			throw new BusinessException("没有该类目信息，节点id为:" + productDTO.getCategoryTreeNodeId());
		}
		productVO2.setCategoryId(treeNodeDTO.getCategoryId());
		List<Long> lists = new ArrayList<Long>();
		lists.add(treeNodeDTO.getCategoryId());
		List<Long> pId = this.getPId(treeNodeDTO, lists);
		productVO2.setCategoryPId(pId);
		// 根据产品id查询图片id
		/*
		 * productPictureVO.setProductId(productVO.getId());
		 * List<ProductPictureDTO> ProductPictureList =
		 * productPictureManage.findAll(productPictureVO);
		 */

		// 根据产品id查询产品图片信息
		List<PictureDTO> pictureList = productFacade.pictureByProductId(productVO.getId());
		for (PictureDTO pictureDTO : pictureList) {
			if (pictureDTO.getType() == 1) {
				productVO2.setUrl(pictureDTO.getUrl());
				pictureList.remove(pictureDTO);
				break;
			}
		}
		// 根据图片id查询图片信息
		/*
		 * for (ProductPictureDTO productPictureDTO : ProductPictureList) {
		 * pictureVO.setId(productPictureDTO.getPictureId()); PictureDTO
		 * pictureDTO = pictureManage.findById(pictureVO);
		 * //列表图片只可能有一个所以不可能发生覆盖情况 if(pictureDTO.getType()==1){
		 * productVO2.setUrl(pictureDTO.getUrl()); }else{
		 * pictureList.add(pictureDTO); } }
		 */
		// 将图片信息添加到产品信息里
		productVO2.setPictureList(pictureList);

		// 根据产品id查询属性id
		ProductAttNameVO productAttNameVO = new ProductAttNameVO();
		productAttNameVO.setProductId(productVO.getId());
		List<ProductAttNameDTO> productAttNameList = productAttNameManage.findAll(productAttNameVO);

		List<CategoryAttNameValuse> list = new ArrayList<CategoryAttNameValuse>();
		List<CategoryAttNameValuse> categoryAttNameValuseList = new ArrayList<CategoryAttNameValuse>();
		List<AttName> attNames = new ArrayList<AttName>();
		Long showProductAttNameId = null;
		for (ProductAttNameDTO productAttNameDTO : productAttNameList) {
			if (productAttNameDTO.getType() == 1 || productAttNameDTO.getType() == 3) {
				CategoryAttNameValuse categoryAttNameValuse = new CategoryAttNameValuse();
				// 根据属性名称id查询属性信息
				attributeNameVO.setId(productAttNameDTO.getAttNameId());
				attributeNameVO.setCategoryId(treeNodeDTO.getCategoryId());
				AttributeNameVO attributeName = productFacade.findById(AttributeNameConverter.toDTO(attributeNameVO));
				if (EmptyUtil.isEmpty(attributeName)) {
					throw new BusinessException("类目不存在此属性，类目id：" + treeNodeDTO.getCategoryId() + "属性id："
							+ productAttNameDTO.getAttNameId());
				}
				AttributeNameDTO attributeNameDTO = AttributeNameConverter.toDTO(attributeName);
				// 根据产品属性关系id查询属性值id
				ProductAttValueVO productAttValueVO = new ProductAttValueVO();
				productAttValueVO.setProductAttNameId(productAttNameDTO.getId());
				List<ProductAttValueDTO> productAttValueList = productAttValueManage.findAll(productAttValueVO);
				if(productAttValueList.size() == 1)
					attributeNameDTO.setAttValueCustom(productAttValueList.get(0).getAttValueCustom());
				// 存放属性值所选择中的值
				List<AttributeValueDTO> attributeValueList = new ArrayList<AttributeValueDTO>();
				for (ProductAttValueDTO productAttValueDTO : productAttValueList) {
					if (EmptyUtil.isNotEmpty(productAttValueDTO.getAttValueId())) {
						AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
						attributeValueDTO.setId(productAttValueDTO.getAttValueId());
						AttributeValueDTO attributeValueDTO2 = attributeValueFacade.findById(attributeValueDTO);
						if (EmptyUtil.isEmpty(attributeValueDTO2)) {
							throw new BusinessException("属性值id为" + productAttValueDTO.getAttValueId() + "属性值不存在");
						}
						attributeValueList.add(attributeValueDTO2);
					}

				}
				// 根据属性id查询属性值信息
				AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
				attributeValueDTO.setAttributeNameId(productAttNameDTO.getAttNameId());
				List<AttributeValueDTO> attributeValueListAll = attributeValueFacade.findAll(attributeValueDTO);
				// 循环根据属性值id获取属性值信息
				List<AttributeValueDTO> sortList = new ArrayList<AttributeValueDTO>();
				// 递归成树结构
				if (attributeValueListAll.size() > 0) {
					for (AttributeValueDTO tree : attributeValueListAll) {
						for (AttributeValueDTO t : attributeValueListAll) {
							if (t.getParentId().equals(tree.getId())) {
								if (tree.getLists() == null) {
									List<AttributeValueDTO> mylistss = new ArrayList<AttributeValueDTO>();
									mylistss.add(t);
									tree.setLists(mylistss);
								} else {
									tree.getLists().add(t);
								}
							}
						}
						if (tree.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
							sortList.add(tree);
						}
					}
				}

				if (attributeNameDTO.getMode() == 2 || attributeNameDTO.getMode() == 5
						|| attributeNameDTO.getMode() == 6) {
					categoryAttNameValuse.setValue(attributeNameDTO.getAttValueCustom());
				} else if (attributeNameDTO.getMode() == 4) {
					if (EmptyUtil.isNotEmpty(attributeNameDTO.getAttValueCustom())) {
						categoryAttNameValuse.setValue(Long.valueOf(attributeNameDTO.getAttValueCustom()));
					}
				} else if (attributeNameDTO.getMode() == 1) {
					if (attributeValueList.size() > 0) {
						categoryAttNameValuse.setValue(attributeValueList.get(0).getId());
					} else {
						categoryAttNameValuse.setValue(null);
					}

				} else if (attributeNameDTO.getMode() == 3) {
					for (AttributeValueDTO attributeValue : attributeValueListAll) {
						for (AttributeValueDTO attributeValueDTO2 : attributeValueList) {
							if (attributeValue.getId().equals(attributeValueDTO2.getId())) {
								if (categoryAttNameValuse.getValues() == null) {
									List<Object> value = new ArrayList<Object>();
									value.add(attributeValue.getId());
									categoryAttNameValuse.setValues(value);
								} else {
									categoryAttNameValuse.getValues().add(attributeValue.getId());
								}

							}
						}
					}

				}else if(attributeNameDTO.getMode() == 7){
					List<Object> values = new ArrayList<>();
					for (ProductAttValueDTO productAttValue : productAttValueList) {
						values.add(productAttValue.getAttValueCustom());
					}
					categoryAttNameValuse.setValues(values);
				}

				categoryAttNameValuse.setAttributeNameDTO(attributeNameDTO);
				if (attributeNameDTO.getMode() == 1) {
					categoryAttNameValuse.setLists(sortList);
				} else if (attributeNameDTO.getMode() == 3) {
					categoryAttNameValuse.setLists(attributeValueListAll);
					categoryAttNameValuse.setValues(categoryAttNameValuse.getValues());
				}else if(attributeNameDTO.getMode() == 7){
					categoryAttNameValuse.setLists(attributeValueList);
				}
				if (productAttNameDTO.getType() == 1) {
					if(categoryAttNameValuse.getAttributeNameDTO().getId().equals(BusinessConstant.THIRDPARTY_BUTT_JOINT_PARAMETER)){
						List<AttributeValueDTO> attributeValues = categoryAttNameValuse.getLists();
						if(EmptyUtil.isNotEmpty(attributeValues)){
							for (AttributeValueDTO attributeValue : attributeValues) {
								if(attributeValue.getId().equals(7L) || attributeValue.getId().equals(12L)||attributeValue.getId().equals(13L)){
									attributeValue.setAttributeNameId(3L);
								}else{
									attributeValue.setAttributeNameId(1L);
								}
								
							}
						}
					}
					list.add(categoryAttNameValuse);
				} else if (productAttNameDTO.getType() == 3) {
					categoryAttNameValuseList.add(categoryAttNameValuse);
				}

			} else if (productAttNameDTO.getType() == 2) {
				if (productAttNameDTO.getShowPicture() == 1) {
					showProductAttNameId = productAttNameDTO.getAttNameId();
				}
				AttName attName = new AttName();
				// 根据属性名称id查询属性信息
				attributeNameVO.setId(productAttNameDTO.getAttNameId());
				attributeNameVO.setCategoryId(treeNodeDTO.getCategoryId());
				AttributeNameVO attributeName = productFacade.findById(AttributeNameConverter.toDTO(attributeNameVO));
				if (EmptyUtil.isEmpty(attributeName)) {
					throw new BusinessException("类目不存在此属性，类目id：" + treeNodeDTO.getCategoryId() + "属性id："
							+ productAttNameDTO.getAttNameId());
				}
				AttributeNameDTO attributeNameDTO = AttributeNameConverter.toDTO(attributeName);
				attName.setId(attributeNameDTO.getId());
				attName.setName(attributeNameDTO.getName());
				List<Value> valueList = new ArrayList<Value>();
				// 根据产品属性关系id查询属性值id
				ProductAttValueVO productAttValueVO = new ProductAttValueVO();
				productAttValueVO.setProductAttNameId(productAttNameDTO.getId());
				List<ProductAttValueDTO> productAttValueList = productAttValueManage.findAll(productAttValueVO);
				for (ProductAttValueDTO productAttValueDTO : productAttValueList) {
					Value value = new Value();
					value.setId(productAttValueDTO.getId());
					value.setValue(productAttValueDTO.getAttValueCustom());
					value.setPictureUrl(productAttValueDTO.getPictureUrl());
					valueList.add(value);
				}
				attName.setList(valueList);
				attNames.add(attName);
			}
		}
		productVO2.setApecificationList(attNames);
		productVO2.setCategoryAttNameValuseList(list);
		productVO2.setParameterCategoryAttNameValuse(categoryAttNameValuseList);
		productVO2.setShowProductAttNameId(showProductAttNameId);
		return productVO2;
	}
	@Override
	public int updateProductReferLink(ProductVO vo) {

		return productFacade.updateReferLink(vo);
	}

	@Override
	public boolean checkProductPriceDetail(ProductVO productVO) {
		if(EmptyUtil.isEmpty(productVO.getPriceDetail()) || productVO.getPriceDetail().length()<=5) {
			return false;
		}
		boolean isOk=true;
		JSONObject priceDetail = JSON.parseObject(productVO.getPriceDetail());
		if(priceDetail.getIntValue("priceType")==1) {
			if (EmptyUtil.isEmpty(priceDetail.getString("taxRate"))){
				isOk=false;
			}
		}else if(priceDetail.getIntValue("priceType")==2){
			JSONArray priceList = priceDetail.getJSONArray("priceList");
			for(int index = 0 ; index<priceList.size();index++) {
				JSONObject priceObj = priceList.getJSONObject(index);
				if (EmptyUtil.isEmpty(priceObj.getString("taxRate"))){
					isOk=false;
					break;
				}
			}
		}
		return isOk;
	}

	@Override
	public String updateProduct(ProductVO productVO, ProductDescriptionVO productDescriptionVO, PictureVO pictureVO,
			BrandVO brandVO, List<AttNameValueVO> lists, List<AttNameValueVO> parameterAtts,
			Long showProductAttNameId) {
		if (productVO.getName() == null || productVO.getName().equals("")) {
			throw new BusinessException("产品名称不能为空");
		}
		if (EmptyUtil.isEmpty(productVO.getId())) {
			// 如果前端没有传id则用户在新增spu的时候没有选择规格信息
			throw new BusinessException("请选择规格信息");
		}
		List<AttNameValueDTO> list = new ArrayList<AttNameValueDTO>();
		for (AttNameValueVO attNameValueVO : lists) {
			AttNameValueDTO attNameValueDTO = new AttNameValueDTO();
			attNameValueDTO.setKey(attNameValueVO.getKey());
			attNameValueDTO.setValue(attNameValueVO.getValue());
			attNameValueDTO.setUrl(attNameValueVO.getUrl());
			attNameValueDTO.setType(1);
			list.add(attNameValueDTO);
		}
		for (AttNameValueVO attNameValueVO : parameterAtts) {
			AttNameValueDTO attNameValueDTO = new AttNameValueDTO();
			attNameValueDTO.setKey(attNameValueVO.getKey());
			attNameValueDTO.setValue(attNameValueVO.getValue());
			attNameValueDTO.setUrl(attNameValueVO.getUrl());
			attNameValueDTO.setType(3);
			list.add(attNameValueDTO);
		}

		return productFacade.updateProduct(ProductConverter.toDTO(productVO),
				ProductDescriptionConverter.toDTO(productDescriptionVO), PictureConverter.toDTO(pictureVO),
				BrandConverter.toDTO(brandVO), list, showProductAttNameId);
	}

	@Override
	public String deleteProduct(ProductVO productVO) {
		// 根据产品id查询产品信息
		ProductDTO productDTO = productFacade.findById(ProductConverter.toDTO(productVO));
		if (productDTO.getStatus() == 2) {
			throw new BusinessException("该产品正在审核中，无法删除");
		}
		return productFacade.deleteProduct(ProductConverter.toDTO(productVO));
	}

	@Override
	public Long audit(ProductVO productVO) {

		return productFacade.audit(ProductConverter.toDTO(productVO));
	}

	// 循环获取pid
	public List<Long> getPId(CategoryTreeNodeDTO dto, List<Long> list) {
		if (!dto.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
			CategoryTreeNodeDTO categoryTreeNodeDTO = categoryTreeNodeManage
					.CategoryTreeNodeByCategoryId(dto.getParentId());
			if (EmptyUtil.isEmpty(categoryTreeNodeDTO)) {
				logger.info("没有该类目信息，节点id为:" + dto.getParentId());
				throw new BusinessException("没有该类目信息，节点id为:" + dto.getParentId());
			}
			list.add(0, categoryTreeNodeDTO.getCategoryId());
			getPId(categoryTreeNodeDTO, list);
		}
		return list;
	}

	// 循环获取属性值pid
	public List<Long> getValuePId(AttributeValueDTO dto, List<Long> list) {
		if (!dto.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
			list.add(0, dto.getParentId());
			AttributeValueDTO valueDTO = new AttributeValueDTO();
			valueDTO.setId(dto.getParentId());
			AttributeValueDTO attributeValueDTO = attributeValueFacade.findById(valueDTO);
			getValuePId(attributeValueDTO, list);
		}
		return list;
	}

	@Override
	public List<ProductVO> findAll(ProductVO productVO) {
		List<ProductDTO> list = productFacade.findAll(ProductConverter.toDTO(productVO));
		return ProductConverter.toVO(list);
	}

	@Override
	public String passAllAudit(String ids, Long userId, String userName, String ip, String mac,Long platformId) {

		return productFacade.passAllAudit(ids, userId, userName, ip, mac,platformId);
	}

	@Override
	public boolean updateStatus(ProductVO productVO, Long userId, String userName, String ip, String mac) {

		return productFacade.updateStatus(ProductConverter.toDTO(productVO), userId, userName, ip, mac);
	}

	@Override
	public PageResult<StandardProductUnitDTO> productByCategoryId(Pagination page, ProductVO productVO,
			Long categoryId) {
		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		if (categoryId != null) {
			// 根据类目id查询类目节点
			CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
			categoryTreeNodeDTO.setCategoryId(categoryId);
			List<CategoryTreeNodeDTO> list = categoryTreeNodeFacade.findAll(categoryTreeNodeDTO);
			if (list.size() > 0) {
				standardProductUnitDTO.setCategoryTreeNodeId(list.get(0).getId());
			}
		}
		// 查询状态为3以通过的spu
		standardProductUnitDTO.setStatus(3);
		// 查询状态为启用的spu
		standardProductUnitDTO.setIsAvailable(1);
		standardProductUnitDTO.setName(productVO.getName());
		if(productVO.getEnterpriseId()!=null) {
			standardProductUnitDTO.setEnterpriseId(productVO.getEnterpriseId());
		}
		standardProductUnitDTO.setPlatformId(productVO.getPlatformId());

		return standardProductUnitFacade.findStandardProductUnitOfPage(standardProductUnitDTO, page);
	}

	@Override
	public PageResult<Map<String, Object>> productByCategoryIdQuick(Pagination page, ProductVO productVO,
			Long categoryId) {
		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		if (categoryId != null) {
			// 根据类目id查询类目节点
			CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
			categoryTreeNodeDTO.setCategoryId(categoryId);
			List<CategoryTreeNodeDTO> list = categoryTreeNodeFacade.findAll(categoryTreeNodeDTO);
			if (list.size() > 0) {
				standardProductUnitDTO.setCategoryTreeNodeId(list.get(0).getId());
			}
		}
		// 查询状态为3以通过的spu
		standardProductUnitDTO.setStatus(3);
		// 查询状态为启用的spu
		standardProductUnitDTO.setIsAvailable(1);
		if(productVO.getSupplierId()!=null) {
			standardProductUnitDTO.setSupplierId(productVO.getSupplierId());
		}
		standardProductUnitDTO.setName(productVO.getName());
		if(productVO.getEnterpriseId()!=null) {
			standardProductUnitDTO.setEnterpriseId(productVO.getEnterpriseId());
		}
		standardProductUnitDTO.setPlatformId(productVO.getPlatformId());

		return standardProductUnitFacade.findStandardProductUnitOfPageQuick(standardProductUnitDTO, page);
	}
	@Override
	public Long categoryIdByProductId(Long productId) {
		// 通过产品id查询产品信息
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productId);
		ProductDTO productDTO2 = productFacade.findById(productDTO);

		// 通过节点id查询节点信息
		CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
		categoryTreeNodeDTO.setId(productDTO2.getCategoryTreeNodeId());
		CategoryTreeNodeDTO categoryTreeNodeDTO2 = categoryTreeNodeFacade.findById(categoryTreeNodeDTO);
		return categoryTreeNodeDTO2.getCategoryId();
	}

	@Override
	public List<String> idToName(List<Long> categoryPId) {
		StringBuffer stringBuffer = new StringBuffer();
		// 和之前数据格式一致
		stringBuffer.append("[");
		for (Long long1 : categoryPId) {
			stringBuffer.append(long1);
			stringBuffer.append(",");
		}
		String ids = stringBuffer.toString();
		return categoryFacade.idToName(ids);
	}

	@Override
	public String idTobrandName(Long brandId) {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(brandId);
		BrandDTO brandDTO2 = brandFacade.findById(brandDTO);
		return brandDTO2.getChineseName();
	}

	@Override
	public ProductVO productByProductId(ProductVO productVO, CategoryAttValueVO categoryAttValueVO,
			ProductDescriptionVO productDescriptionVO, PictureVO pictureVO, ProductPictureVO productPictureVO,
			AttributeValueVO attributeValueVO, AttributeNameVO attributeNameVO) {
		// 根据产品id查询产品信息
		ProductDTO productDTO = productFacade.findById(ProductConverter.toDTO(productVO));
		ProductVO vo = ProductConverter.toVO(productDTO);

		// 根据产品id查询图片id
		productPictureVO.setProductId(productVO.getId());
		List<ProductPictureDTO> ProductPictureList = productPictureManage
				.findProductPictureAll(ProductPictureConverter.toDTO(productPictureVO));

		// 根据图片id查询图片信息
		List<PictureDTO> pictureList = new ArrayList<PictureDTO>();
		for (ProductPictureDTO productPictureDTO : ProductPictureList) {
			pictureVO.setId(productPictureDTO.getPictureId());
			PictureDTO pictureDTO = pictureManage.findById(pictureVO);
			// 列表图片只可能有一个所以不可能发生覆盖情况
			if (pictureDTO.getType() == 1) {
				vo.setUrl(pictureDTO.getUrl());
			} else {
				pictureList.add(pictureDTO);
			}
		}
		// 将图片信息添加到产品信息里
		vo.setPictureList(pictureList);

		// 根据产品id查询产品描述信息
		productDescriptionVO.setProductId(productVO.getId());
		List<ProductDescriptionDTO> productDescriptionList = productDescriptionManage.findAll(productDescriptionVO);

		// 将产品描述信息添加到产品信息里
		if (productDescriptionList.size() > 0) {
			vo.setContent(productDescriptionList.get(0).getContent());
		}

		// 根据产品id查询属性id
		ProductAttNameVO productAttNameVO = new ProductAttNameVO();
		productAttNameVO.setProductId(productVO.getId());
		List<ProductAttNameDTO> productAttNameList = productAttNameManage.findAll(productAttNameVO);
		List<AttNameValueVO> list = new ArrayList<AttNameValueVO>();
		List<AttName> attNames = new ArrayList<AttName>();
		for (ProductAttNameDTO productAttNameDTO : productAttNameList) {
			if (productAttNameDTO.getType() == 1) {
				CategoryAttNameValuse categoryAttNameValuse = new CategoryAttNameValuse();
				// 根据属性名称id查询属性信息
				attributeNameVO.setId(productAttNameDTO.getAttNameId());
				AttributeNameVO attributeName = attributeNameManage.findById(attributeNameVO);
				// 根据产品属性关系id查询属性值id
				ProductAttValueVO productAttValueVO = new ProductAttValueVO();
				productAttValueVO.setProductAttNameId(productAttNameDTO.getId());
				List<ProductAttValueDTO> productAttValueList = productAttValueManage.findAll(productAttValueVO);

				if (attributeName.getMode() == 1) {
					AttNameValueVO attNameValueVO = new AttNameValueVO();
					attNameValueVO.setName(attributeName.getName());
					// 根据属性值id查询属性值信息
					if (productAttValueList.size() > 0) {
						AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
						attributeValueDTO.setId(productAttValueList.get(0).getAttValueId());
						AttributeValueDTO attributeValueDTO2 = attributeValueFacade.findById(attributeValueDTO);
						attNameValueVO.setValue(attributeValueDTO2.getValue());
					}
					list.add(attNameValueVO);
				} else if (attributeName.getMode() == 2) {
					AttNameValueVO attNameValueVO = new AttNameValueVO();
					attNameValueVO.setName(attributeName.getName());
					attNameValueVO.setValue(productAttValueList.get(0).getAttValueCustom());
					list.add(attNameValueVO);
				} else if (attributeName.getMode() == 3) {
					AttNameValueVO attNameValueVO = new AttNameValueVO();
					attNameValueVO.setName(attributeName.getName());

					StringBuffer attValue = new StringBuffer();
					for (ProductAttValueDTO productAttValueDTO : productAttValueList) {
						AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
						attributeValueDTO.setId(productAttValueDTO.getAttValueId());
						AttributeValueDTO attributeValueDTO2 = attributeValueFacade.findById(attributeValueDTO);

						attValue.append(attributeValueDTO2.getValue());
						attValue.append(",");
					}
					attNameValueVO.setValue(attValue.toString().substring(0, attValue.toString().length() - 1));
					list.add(attNameValueVO);
				}
			} else if (productAttNameDTO.getType() == 2) {
				AttName attName = new AttName();
				// 根据属性名称id查询属性信息
				attributeNameVO.setId(productAttNameDTO.getAttNameId());
				AttributeNameVO attributeName = attributeNameManage.findById(attributeNameVO);
				AttributeNameDTO attributeNameDTO = AttributeNameConverter.toDTO(attributeName);
				attName.setId(attributeNameDTO.getId());
				attName.setName(attributeNameDTO.getName());
				List<Value> valueList = new ArrayList<Value>();
				// 根据产品属性关系id查询属性值id
				ProductAttValueVO productAttValueVO = new ProductAttValueVO();
				productAttValueVO.setProductAttNameId(productAttNameDTO.getId());
				List<ProductAttValueDTO> productAttValueList = productAttValueManage.findAll(productAttValueVO);
				for (ProductAttValueDTO productAttValueDTO : productAttValueList) {
					Value value = new Value();
					value.setId(productAttValueDTO.getId());
					value.setValue(productAttValueDTO.getAttValueCustom());
					valueList.add(value);
				}
				attName.setList(valueList);
				attNames.add(attName);
			}

		}
		vo.setApecificationList(attNames);
		vo.setList(list);
		// 根据节点id查询节点与类目的关系信息
		CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
		categoryTreeNodeDTO.setId(productDTO.getCategoryTreeNodeId());
		CategoryTreeNodeDTO treeNodeDTO = categoryTreeNodeFacade.findById(categoryTreeNodeDTO);
		List<Long> lists = new ArrayList<Long>();
		lists.add(treeNodeDTO.getCategoryId());
		List<Long> pId = this.getPId(treeNodeDTO, lists);

		List<String> categoryName = this.idToName(pId);
		vo.setCategoryName(categoryName);
		return vo;
	}

	@Override
	public ProductVO ProductById(ProductVO productVO) {
		ProductDTO productDTO = productFacade.ProductById(ProductConverter.toDTO(productVO));
		return ProductConverter.toVO(productDTO);
	}

	/**
	 * 设置是否启用
	 * 
	 * @param productVO
	 * @return
	 */
	@Override
	public int updateAvailable(ProductVO productVO, String ids) {
		int i = 0;
		List<Long> asList = new ArrayList<Long>(JSONArray.parseArray(ids, Long.class));
		if (EmptyUtil.isEmpty(asList)) {
			throw new BusinessException("请选择一个产品");
		}
		if (EmptyUtil.isNotEmpty(asList)) {
			boolean hasPutAway=false;
			for (Long productId : asList) {
				if (Objects.equals(0,productVO.getIsAvailable())){
					StandardUnitDTO standardUnitDTO=new StandardUnitDTO();
					standardUnitDTO.setStandardProductUnitId(productId);
					List<StandardUnitDTO> standardUnitDTOList=standardUnitFacade.findStandardUnitAll(standardUnitDTO);
					if (EmptyUtil.isNotEmpty(standardUnitDTOList)){
						hasPutAway=true;
						for (StandardUnitDTO suDto:standardUnitDTOList){
							//下架操作
							standardUnitAction.putawaySoldOut(4,suDto.getId(),1);
							//设置隐藏,不显示
							standardUnitAction.updateSuVisible(1,suDto.getId());
						}
						logger.info("供应商产品管理停用操作,关联商品下架,productId = {}", productId);
					}
				}
				StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
				standardProductUnitDTO.setId(productId);
				standardProductUnitDTO.setIsAvailable(productVO.getIsAvailable());
				// 根据spuid设置是否启用
				i = i + standardProductUnitFacade.updateAvailableWithTx(standardProductUnitDTO);
				ProductDTO pdto =  new ProductDTO();
				pdto.setId(productId);
				pdto.setIsAvailable(productVO.getIsAvailable());
				productFacade.updateAvailable(pdto);
			}
			if (hasPutAway){
				standardUnitAction.syncSaveSuSerachRule();
			}
		}

		return i;
	}

}
