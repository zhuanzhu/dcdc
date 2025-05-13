package com.egeo.components.product.service.write.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.converter.BrandConverter;
import com.egeo.components.product.converter.PictureConverter;
import com.egeo.components.product.converter.ProductConverter;
import com.egeo.components.product.converter.ProductDescriptionConverter;
import com.egeo.components.product.dto.AttNameValueDTO;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.BrandDTO;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.dto.ProductDescriptionDTO;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.product.manage.read.ProductPictureReadManage;
import com.egeo.components.product.manage.read.ProductReadManage;
import com.egeo.components.product.manage.write.ProductWriteManage;
import com.egeo.components.product.po.AttNameValuePO;
import com.egeo.components.product.po.ProductPO;
import com.egeo.components.product.po.ProductPicturePO;
import com.egeo.components.product.service.read.AttributeNameReadService;
import com.egeo.components.product.service.read.CategoryReadService;
import com.egeo.components.product.service.read.CategoryTreeNodeReadService;
import com.egeo.components.product.service.read.ProductAttNameReadService;
import com.egeo.components.product.service.write.BrandWriteService;
import com.egeo.components.product.service.write.PictureWriteService;
import com.egeo.components.product.service.write.ProductAttNameWriteService;
import com.egeo.components.product.service.write.ProductAttValueWriteService;
import com.egeo.components.product.service.write.ProductDescriptionWriteService;
import com.egeo.components.product.service.write.ProductPictureWriteService;
import com.egeo.components.product.service.write.ProductWriteService;
import com.egeo.components.product.service.write.impl.Thread.SaveListFactory;
import com.egeo.components.product.service.write.impl.Thread.UpdateListFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;
import com.egeo.utils.str.StringUtils;

@Service("productWriteService")
public class ProductWriteServiceImpl  implements ProductWriteService {
	private static final XLogger logger = XLogger.getLogger(ProductWriteServiceImpl.class);
	@Autowired
	private ProductWriteManage productWriteManage;

	@Autowired
	private ProductReadManage productReadManage;

	@Autowired
	private ProductAttNameWriteService productAttNameWriteService;

	@Autowired
	private ProductAttNameReadService productAttNameReadService;

	@Autowired
	private AttributeNameReadService attributeNameReadService;

	@Autowired
	private ProductAttValueWriteService productAttValueWriteService;

	@Autowired
	private ProductDescriptionWriteService productDescriptionWriteService;

	@Autowired
	private PictureWriteService pictureWriteService;

	@Autowired
	private ProductPictureWriteService productPictureWriteService;

	@Autowired
	private ProductPictureReadManage productPictureReadManage;

	@Autowired
	private BrandWriteService brandWriteService;

	@Autowired
	private CategoryReadService categoryReadService;

	@Autowired
	private CategoryTreeNodeReadService categoryTreeNodeReadService;
	@Resource
	private JedisUtil jedisUtil;


	@Override
	public Long saveProductWithTx(ProductDTO dto, ProductDescriptionDTO productDescriptionDTO, PictureDTO pictureDTO,
			BrandDTO brandDTO, List<AttNameValueDTO> lists) {
		// 根据类目id查询类目信息
		CategoryDTO categoryDTO = categoryReadService.findCategoryById(dto.getCategoryId());

		// 根据类目id和类目树类型id查询其节点信息
		CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
		categoryTreeNodeDTO.setCategoryTreeId(PlatformKeyConstant.BACKGROUND_TREE_ID);
		categoryTreeNodeDTO.setCategoryId(categoryDTO.getId());
		List<CategoryTreeNodeDTO> categoryTreeNodeList = categoryTreeNodeReadService.findAll(categoryTreeNodeDTO);

		// 添加品牌信息
		Long brandId = brandWriteService.saveBrandWithTx(brandDTO);
		dto.setBrandId(brandId);
		dto.setStatus(1);
		dto.setCategoryTreeNodeId(categoryTreeNodeList.get(0).getId());
		dto.setProductSerialNumber(StringUtils.protectCategoryProductSerialNumber(categoryDTO.getSerialNumber()));
		Long productId = productWriteManage.saveProductWithTx(ProductConverter.toPO(dto));
		// 添加属性值
		if (lists.size() > 0) {
			for (AttNameValueDTO attNameValue : lists) {
				Long attNameId = Long.valueOf(attNameValue.getKey());
				String value = attNameValue.getValue();
				/*
				 * if(!attNameValue.getValue().equals("null")){ value =
				 * attNameValue.getValue().substring(1,
				 * attNameValue.getValue().length()-1); }
				 */

				// 保存产品与属性的关系并返回id
				ProductAttNameDTO productAttNameDTO = new ProductAttNameDTO();
				productAttNameDTO.setAttNameId(attNameId);
				productAttNameDTO.setProductId(productId);
				productAttNameDTO.setType(attNameValue.getType());
				Long productAttNameId = productAttNameWriteService.saveProductAttNameWithTx(productAttNameDTO);

				// 根据属性id查询属性信息
				AttributeNameDTO attributeNameDTO = new AttributeNameDTO();
				attributeNameDTO.setId(attNameId);
				AttributeNameDTO attributeName = attributeNameReadService.findById(attributeNameDTO);

				// 保存产品属性与属性值的关系
				ProductAttValueDTO productAttValueDTO = new ProductAttValueDTO();
				productAttValueDTO.setProductAttNameId(productAttNameId);
				if (attributeName.getMode() == 1) {
					if (value != null && !value.equals("") && !value.equals("[]")) {
						List<String> attValueIdList = Arrays.asList(value.split(","));
						// 选取字符串最后一位id
						productAttValueDTO.setAttValueId(Long.valueOf(attValueIdList.get(attValueIdList.size() - 1)));
					}
					productAttValueWriteService.saveProductAttValueWithTx(productAttValueDTO);
				} else if (attributeName.getMode() == 2) {
					if (value != null && !value.equals("") && !value.equals("[]")) {
						productAttValueDTO.setAttValueCustom(value);
						productAttValueWriteService.saveProductAttValueWithTx(productAttValueDTO);
					}

				} else if (attributeName.getMode() == 3) {
					if (value != null && !value.equals("") && !value.equals("[]")) {
						List<String> list = Arrays.asList(value.substring(1, value.length() - 1).split(","));
						for (String string2 : list) {
							productAttValueDTO.setAttValueId(Long.valueOf(string2));
							productAttValueWriteService.saveProductAttValueWithTx(productAttValueDTO);
						}
					} else {
						productAttValueWriteService.saveProductAttValueWithTx(productAttValueDTO);
					}

				}
			}
		}

		// 添加产品描述信息
		productDescriptionDTO.setProductId(productId);

		// 添加cp描述信息
		productDescriptionDTO.setContent(productDescriptionDTO.getContent());

		productDescriptionWriteService.saveProductDescriptionWithTx(productDescriptionDTO);

		// 添加产品列表图片
		List<Long> longList = new ArrayList<Long>();
		if (pictureDTO.getUrl() != null && !pictureDTO.getUrl().equals("") && !pictureDTO.getUrl().equals("[]")) {
			pictureDTO.setType(1);
			Long PId = pictureWriteService.savePictureWithTx(pictureDTO);
			ProductPictureDTO productPictureDTO = new ProductPictureDTO();
			productPictureDTO.setPictureId(PId);
			productPictureDTO.setProductId(productId);
			productPictureDTO.setType(1);
			productPictureWriteService.insertProductPictureWithTx(productPictureDTO);
		}

		// 添加产品轮播图片
		List<PictureDTO> listStyleImage = pictureDTO.getStyleImage();
		for (PictureDTO pictureDTO2 : listStyleImage) {
			pictureDTO2.setType(2);
			Long pictureId = pictureWriteService.savePictureWithTx(pictureDTO2);
			longList.add(pictureId);
		}
		for (Long long1 : longList) {
			// 根据添加产品图片信息返回的id添加产品与图片关系表
			ProductPictureDTO productPictureDTO = new ProductPictureDTO();
			productPictureDTO.setPictureId(long1);
			productPictureDTO.setProductId(productId);
			productPictureDTO.setType(2);
			productPictureWriteService.insertProductPictureWithTx(productPictureDTO);
		}

		return productId;

	}

	@Override
	public String updateProductWithTx(ProductDTO dto, ProductDescriptionDTO dto2, PictureDTO dto3, BrandDTO brandDTO,
			List<AttNameValueDTO> lists, Long showProductAttNameId) {

		List<AttNameValuePO> attNameValuePOList = new ArrayList<>();
		for (AttNameValueDTO attNameValueDTO : lists) {
			AttNameValuePO attNameValuePO2 = new AttNameValuePO();
			attNameValuePO2.setKey(attNameValueDTO.getKey());
			attNameValuePO2.setMode(attNameValueDTO.getMode());
			attNameValuePO2.setName(attNameValueDTO.getName());
			attNameValuePO2.setType(attNameValueDTO.getType());
			attNameValuePO2.setUrl(attNameValueDTO.getUrl());
			attNameValuePO2.setValue(attNameValueDTO.getValue());
			attNameValuePOList.add(attNameValuePO2);
		}

		return productWriteManage.updateProductWithTx(ProductConverter.toPO(dto),
				ProductDescriptionConverter.toPO(dto2), PictureConverter.toPO(dto3), BrandConverter.toPO(brandDTO),
				attNameValuePOList, showProductAttNameId);
	}

	@Override
	public String deleteProductWithTx(ProductDTO dto) {
		// 查询与产品有关的所有属性
		ProductAttNameDTO productAttNameDTO = new ProductAttNameDTO();
		productAttNameDTO.setProductId(dto.getId());
		List<ProductAttNameDTO> productAttNameList = productAttNameReadService.findAll(productAttNameDTO);

		// 拼接产品属性id字符串
		StringBuffer productAttNameIds = new StringBuffer();
		for (ProductAttNameDTO productAttNameDTO2 : productAttNameList) {
			productAttNameIds.append(productAttNameDTO2.getId());
			productAttNameIds.append(",");
		}
		if (productAttNameIds.length() > 0) {
			productAttNameIds.deleteCharAt(productAttNameIds.length() - 1);
		}
		if (productAttNameIds.length() > 0) {
			// 根据所有产品属性id批量删除产品属性值
			productAttValueWriteService.deleteByMuchProductAttNameIdWithTx(productAttNameIds.toString());
		}

		// 根据产品id删除所有产品属性关系
		productAttNameWriteService.deleteByProductIdWithTx(dto.getId());

		// 根据产品id删除产品描述信息
		productDescriptionWriteService.deleteByProductIdWithTx(dto.getId());

		// 根据产品id删除图片及产品与图片的关系
		// 根据产品id查询所有与产品相关的图片
		ProductPicturePO productPicturePO = new ProductPicturePO();
		productPicturePO.setProductId(dto.getId());
		List<ProductPicturePO> productPictureList = productPictureReadManage.findProductPictureAll(productPicturePO);

		// 根据id批量删除图片
		StringBuffer ids = new StringBuffer();
		for (ProductPicturePO productPicturePO2 : productPictureList) {
			ids.append(productPicturePO2.getPictureId());
			ids.append(",");
		}
		if (ids.length() > 0) {
			ids.deleteCharAt(ids.length() - 1);
		}
		if (productAttNameIds.length() > 0) {
			pictureWriteService.deleteByIdsWithTx(ids.toString());
		}

		// 根据产品id删除产品与图片的关系
		productPictureWriteService.deleteByProductIdWithTx(dto.getId());

		return productWriteManage.deleteProductWithTx(ProductConverter.toPO(dto));
	}

	@Override
	public Long auditWithTx(ProductDTO dto) {
		// 根据产品id查询产品信息
		ProductPO productPO2 = productReadManage.findById(ProductConverter.toPO(dto));
		productPO2.setStatus(2);
		return productWriteManage.auditWithTx(productPO2);
	}

	@Override
	public int updateWithTx(ProductDTO productDTO2) {

		return productWriteManage.updateWithTx(ProductConverter.toPO(productDTO2));
	}

	@Override
	public void cleanLink(ProductDTO productDTO2) {

		productWriteManage.cleanLink(ProductConverter.toPO(productDTO2));
	}
	@Override
	public int updateStatusWithTx(ProductDTO dto, Long userId, String userName, String ip, String mac) {
		return synchronizationStandardProductUnitWithTx(dto.getId(), userId, userName, ip, mac);
	}

	/**
	 * 保存spu草稿信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Long insertProductWithTx(ProductDTO productDTO, ProductDescriptionDTO productDescriptionDTO) {
		// TODO Auto-generated method stub
		return productWriteManage.insertProductWithTx(ProductConverter.toPO(productDTO),
				ProductDescriptionConverter.toPO(productDescriptionDTO));
	}

	/**
	 * 设置是否启用
	 * 
	 * @param productVO
	 * @return
	 */
	@Override
	public int updateAvailableWithTx(ProductDTO dto) {
		// TODO Auto-generated method stub
		return productWriteManage.updateAvailableWithTx(ProductConverter.toPO(dto));
	}

	/**
	 * 同步spu信息和记录信息
	 */
	@Override
	public int synchronizationStandardProductUnitWithTx(Long productId, Long userId, String userName, String ip,
			String mac) {
		/*
		 * StandardProductUnitPO standardProductUnitPO = new
		 * StandardProductUnitPO(); standardProductUnitPO.setId(productId);
		 * StandardProductUnitPO standardProductUnitDTO2 =
		 * standardProductUnitReadManage.findStandardProductUnitById(
		 * standardProductUnitPO);
		 * if(EmptyUtil.isNotEmpty(standardProductUnitDTO2)){ //通过spu名称查询spu信息
		 * StandardProductUnitPO standardProductUnitPO2 = new
		 * StandardProductUnitPO();
		 * standardProductUnitPO2.setName(Product.getName());
		 * List<StandardProductUnitPO> findStandardProductUnitAll =
		 * standardProductUnitReadManage.findStandardProductUnitAll(
		 * standardProductUnitPO2);
		 * if(!standardProductUnitDTO2.getName().equals(
		 * findStandardProductUnitAll.get(0).getName())){
		 * if(EmptyUtil.isNotEmpty(findStandardProductUnitAll)){
		 * logger.error("spu名称不能重复,spuId:"+productId); throw new
		 * BusinessException("spu名称不能重复"); } }
		 * 
		 *//**
			 * 后期优化
			 *//*
			 * //根据spuid查询spu属性信息 StandardProductUnitAttNamePO
			 * standardProductUnitAttNamePO = new
			 * StandardProductUnitAttNamePO();
			 * standardProductUnitAttNamePO.setStandardProductUnitId(
			 * standardProductUnitPO.getId());
			 * List<StandardProductUnitAttNamePO> standardProductUnitAttNameList
			 * = standardProductUnitAttNameReadManage.
			 * findStandardProductUnitAttNameAll(standardProductUnitAttNamePO);
			 * 
			 * }
			 */
		return productWriteManage.synchronizationStandardProductUnitWithTx(productId, userId, userName, ip, mac);
	}

	/**
	 * 删除所有点击添加规格属性没有点下一步所创建的空spu草稿信息
	 * 
	 * @return
	 */
	@Override
	public int delByNullProductWithTx() {
		// TODO Auto-generated method stub
		return productWriteManage.delByNullProductWithTx();
	}

	public List<Long> assembleSku(Long productId, String standardProductUnitName, String productSerialNumber) {
		return productWriteManage.assembleSku(productId, standardProductUnitName, productSerialNumber);
	}
	@Override
	public void saveProductList(List<Long> productIdList, List<String> spuSerialNumberList,
								List<String> productCategoryList, List<Long> catgoryTreeNodeIdList,
								List<String> nameList) {
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();

		List<ProductPO> res = new ArrayList<>();
		for (int i=0;i<productIdList.size();i++) {
			ProductPO productPO = new ProductPO();
			productPO.setId(productIdList.get(i));
			productPO.setProductSerialNumber(spuSerialNumberList.get(i));
			productPO.setProductCategory(productCategoryList.get(i));
			productPO.setCategoryTreeNodeId(catgoryTreeNodeIdList.get(i));
			productPO.setName(nameList.get(i));
			productPO.setStatus(Integer.valueOf(3));
			productPO.setIsAvailable(Integer.valueOf(1));
			productPO.setPlatformId(7L);
			productPO.setCommodityTemplateId(2L);
			res.add(productPO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理product保存");
		CountDownLatch countDownLatch = new CountDownLatch(1);


		int page=((res.size()-1)/SIZE)+1;
		try {
			jedisUtil.set("ProductList",24*60*60,page);
		}catch (Exception e){
			logger.info("[ProductList]设置redis失败");
		}
		for(int i=0;i<page;i++) {
			List<ProductPO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * SIZE; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
					list.add(res.get(j));
				}
			}
			SaveListFactory saveListFactory = new SaveListFactory();
			saveListFactory.setLatch(countDownLatch);
			saveListFactory.setSaveType("ProductList");
			saveListFactory.setProductPOList(list);
			saveListFactory.setProductWriteManage(productWriteManage);
			saveListFactory.setJedisUtil(jedisUtil);

			executor.execute(saveListFactory);
		}
		countDownLatch.countDown();
		executor.shutdown();

	}

	@Override
	public void updateProductList(List<Long> productIdList, List<String> nameList) {
		Integer SIZE=10000;
		ExecutorService executor = Executors.newCachedThreadPool();

		List<ProductPO> res = new ArrayList<>();
		for (int i=0;i<productIdList.size();i++) {
			ProductPO productPO = new ProductPO();
			productPO.setId(productIdList.get(i));
			productPO.setName(nameList.get(i));
			res.add(productPO);
		}
		if(EmptyUtil.isEmpty(res)){
			return;
		}
		logger.info("开始异步处理product更新");
		CountDownLatch countDownLatch = new CountDownLatch(1);

		int page=((res.size()-1)/SIZE)+1;
		for(int i=0;i<page;i++) {
			List<ProductPO> list = new ArrayList<>();
			if (i == (page - 1)) {
				for (int j = i * SIZE; j < res.size(); j++) {
					list.add(res.get(j));
				}
			} else {
				for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
					list.add(res.get(j));
				}
			}
			UpdateListFactory factory = new UpdateListFactory();
			factory.setLatch(countDownLatch);
			factory.setSaveType("ProductList");
			factory.setProductPOList(list);
			factory.setProductWriteManage(productWriteManage);
			factory.setJedisUtil(jedisUtil);

			executor.execute(factory);
		}
		countDownLatch.countDown();
		executor.shutdown();
	}

}
