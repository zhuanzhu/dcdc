package com.egeo.components.product.facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.egeo.components.product.manage.write.ProductWriteManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.product.dto.AttNameValueDTO;
import com.egeo.components.product.dto.AttValueDTO;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.dto.BrandDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.dto.ProductCauseDTO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.dto.ProductDescriptionDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.service.read.AttributeNameReadService;
import com.egeo.components.product.service.read.AttributeValueReadService;
import com.egeo.components.product.service.read.CardThirdpartyAttValueReadService;
import com.egeo.components.product.service.read.CategoryAttNameReadService;
import com.egeo.components.product.service.read.CategoryReadService;
import com.egeo.components.product.service.read.MerchantProdAttNameReadService;
import com.egeo.components.product.service.read.MerchantProdAttValueReadService;
import com.egeo.components.product.service.read.MerchantProductReadService;
import com.egeo.components.product.service.read.PictureReadService;
import com.egeo.components.product.service.read.ProductAttNameReadService;
import com.egeo.components.product.service.read.ProductAttValueReadService;
import com.egeo.components.product.service.read.ProductPictureReadService;
import com.egeo.components.product.service.read.ProductReadService;
import com.egeo.components.product.service.read.SkuAttNameReadService;
import com.egeo.components.product.service.read.SkuAttValueReadService;
import com.egeo.components.product.service.read.SkuReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.read.StandardProductUnitReadService;
import com.egeo.components.product.service.write.ProductAttNameWriteService;
import com.egeo.components.product.service.write.ProductAttValueWriteService;
import com.egeo.components.product.service.write.ProductCauseWriteService;
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
import com.egeo.components.product.vo.AttName;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.vo.ProductVO;
import com.egeo.components.product.vo.Value;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.ContactGroupSkuStockClient;
import com.egeo.components.stock.client.ContactGroupStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Component
public class ProductFacade {
	@Resource
	private ProductReadService productReadService;

	@Resource
	private ProductWriteService productWriteService;

	@Resource
	private ProductAttNameReadService productAttNameReadService;

	@Resource
	private ProductAttNameWriteService productAttNameWriteService;

	@Resource
	private ProductAttValueReadService productAttValueReadService;

	@Resource
	private ProductAttValueWriteService productAttValueWriteService;

	@Resource
	private MerchantProductReadService merchantProductReadService;

	@Resource
	private SkuReadService skuReadService;

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
	private CardThirdpartyAttValueReadService cardThirdpartyAttValueReadService;

	@Resource(name = "categoryTreeNodeFacade")
	private CategoryTreeNodeFacade categoryTreeNodeFacade;
	@Autowired
	private ECardClient eCardWriteService;
	@Autowired
	private ContactGroupSkuStockClient contactGroupSkuStockWriteService;
	@Autowired
	private ContactGroupStockClient contactGroupStockReadService;
	@Autowired
	private ProductWriteManage productWriteManage;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public PageResult<ProductDTO> findPage(Pagination page, ProductDTO dto, List<String> nameList) {
		if (dto.getCategoryId() != null) {
			// 根据类目id查询类目节点
			CategoryTreeNodeDTO categoryTreeNodeDTO = new CategoryTreeNodeDTO();
			categoryTreeNodeDTO.setCategoryId(dto.getCategoryId());
			List<CategoryTreeNodeDTO> list = categoryTreeNodeFacade.findAll(categoryTreeNodeDTO);
			if (list.size() > 0) {
				dto.setCategoryTreeNodeId(list.get(0).getId());
			}
		}
		PageResult<ProductDTO> findPage = productReadService.findPage(page, dto,nameList);
		if (EmptyUtil.isNotEmpty(findPage)) {
			// 查询spu草稿已存在的spu，除状态以外全部覆盖
			return findStandardProductUnit(findPage);
		}
		return null;
	}

	public Long saveProduct(ProductDTO dto, ProductDescriptionDTO productDescriptionDTO, PictureDTO pictureDTO,
			BrandDTO brandDTO, List<AttNameValueDTO> lists, List<AttName> apecification) {
		Long productId = productWriteService.saveProductWithTx(dto, productDescriptionDTO, pictureDTO, brandDTO, lists);
		for (AttName attName : apecification) {
			Long productAttNameId = null;
			// 根据产品id和属性id查询产品属性
			ProductAttNameDTO productAttName = new ProductAttNameDTO();
			productAttName.setAttNameId(attName.getId());
			productAttName.setProductId(productId);
			List<ProductAttNameDTO> ProductAttNameList = productAttNameReadService.findAll(productAttName);
			// 根据产品id和属性id查询产品属性只可能为1
			if (ProductAttNameList.size() > 0) {
				productAttNameId = ProductAttNameList.get(0).getId();
			} else {
				// 保存产品属性
				ProductAttNameDTO productAttNameDTO = new ProductAttNameDTO();
				productAttNameDTO.setAttNameId(attName.getId());
				productAttNameDTO.setProductId(productId);
				productAttNameDTO.setType(2);
				productAttNameDTO.setPlatformId(dto.getPlatformId());
				productAttNameId = productAttNameWriteService.saveProductAttNameWithTx(productAttNameDTO);
			}

			List<Value> list = attName.getList();
			for (Value value : list) {
				// 保存产品属性值
				ProductAttValueDTO productAttValueDTO = new ProductAttValueDTO();
				productAttValueDTO.setProductAttNameId(productAttNameId);
				productAttValueDTO.setAttValueCustom(value.getValue());
				productAttValueDTO.setPlatformId(dto.getPlatformId());
				productAttValueWriteService.saveProductAttValueWithTx(productAttValueDTO);
			}

		}
		return productId;
	}

	public ProductDTO findById(ProductDTO dto) {

		return productReadService.findById(dto);
	}

	public String updateProduct(ProductDTO dto, ProductDescriptionDTO dto2, PictureDTO dto3, BrandDTO brandDTO,
			List<AttNameValueDTO> lists, Long showProductAttNameId) {
		return productWriteService.updateProductWithTx(dto, dto2, dto3, brandDTO, lists, showProductAttNameId);
	}

	public String deleteProduct(ProductDTO dto) {

		return productWriteService.deleteProductWithTx(dto);
	}

	public Long audit(ProductDTO dto) {

		return productWriteService.auditWithTx(dto);
	}

	public List<ProductDTO> findAll(ProductDTO dto) {

		return productReadService.findAll(dto);
	}

	/**
	 * 批量通过
	 * 
	 * @param ids
	 * @param userId
	 * @param userName
	 * @param ip
	 * @param mac
	 * @return
	 */
	public String passAllAudit(String ids, Long userId, String userName, String ip, String mac,Long platformId) {
		List<Long> list = JSONArray.parseArray(ids, Long.class);
		for (Long productId : list) {
			ProductDTO dto = new ProductDTO();
			dto.setId(productId);
			dto.setStatus(3);
			dto.setPlatformId(platformId);
			updateStatus(dto, userId, userName, ip, mac);
		}
		return "批量通过成功";
	}
	public int updateReferLink(ProductVO vo) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(vo.getId());
		if(vo.getSupplierId()!=null) {
			productDTO.setSupplierId(vo.getSupplierId());
		}
		
		if(vo.getReferlink()==null) {
			//清理
			productWriteService.cleanLink(productDTO);
			return 1;
		}
		productDTO.setReferlink(vo.getReferlink());
		return productWriteService.updateWithTx(productDTO);
	}
	public boolean updateStatus(ProductDTO dto, Long userId, String userName, String ip, String mac) {
		boolean i = false;
		if (dto.getStatus() == 3) {
			StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
			standardProductUnitDTO.setId(dto.getId());
			StandardProductUnitDTO standardProductUnitDTO2 = standardProductUnitReadService
					.findStandardProductUnitById(standardProductUnitDTO);
			int rows = productWriteService.synchronizationStandardProductUnitWithTx(dto.getId(), userId, userName, ip,
					mac);
			if (rows != 0) {
				// 根据分组的spu查询spu第三方参数其对应的卡类型
				Integer cardType = cardThirdpartyAttValueReadService.findCardTypeByStandardProductUnitId(dto.getId());
				if(EmptyUtil.isEmpty(cardType))
					throw new BusinessException("编号" + dto.getId() + "卡类型异常");
				
				// 根据spuid修改卡密类型
				eCardWriteService.updateCardTypeBySpuId(dto.getId(),cardType);
				if (EmptyUtil.isEmpty(standardProductUnitDTO2)) {
					// 保存sku信息
					SaveSkuThread(dto.getId());
				}else{
//					productWriteManage.updateProductSkuWithTx(dto.getId());
					updateSkuStock(dto.getId(),dto.getPlatformId());
				}

			}
			i = true;
		} else if (dto.getStatus() == 4) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(dto.getId());
			productDTO.setStatus(dto.getStatus());
			productWriteService.updateWithTx(productDTO);
			ProductCauseDTO productCauseDTO = new ProductCauseDTO();
			productCauseDTO.setProductId(dto.getId());
			productCauseDTO.setCause(dto.getCause());
			productCauseDTO.setPlatformId(dto.getPlatformId());
			Long productCauseId = productCauseWriteService.saveProductCauseWithTx(productCauseDTO);
			if (EmptyUtil.isNotEmpty(productCauseId)) {
				return true;
			}
		}

		return i;
	}
	
	
	
	
	/**
	 * 根据spuId更新sku库存信息
	 * @param productId
	 * @param platformId
	 */
	private void updateSkuStock(Long productId,Long platformId) {
		// 根据spuId查询skuId信息
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setStandardProductUnitId(productId);
		List<SkuDTO> skuList = skuReadService.findSkuAll(skuDTO);
		List<Long> skuIds = new ArrayList<>();
		for (SkuDTO skuDTO2 : skuList) {
			skuIds.add(skuDTO2.getId());
		}
		// 根据Skuid集合查询sku库存信息
		if(EmptyUtil.isNotEmpty(skuIds)){
			List<Long> skuIdList = com.egeo.utils.StringUtils.stringsToLongs(commodityProductUnitWarehouseStockReadService.findIsSkuIdsBySkuIds(com.egeo.utils.StringUtils.longsToStrings(skuIds)));
			if(EmptyUtil.isNotEmpty(skuIdList)){
				// 存在的skuId集合不为空则进行判断
				for (Long skuId : skuIds) {
					List<Long> skus = new ArrayList<>();
					// 不包含则新增
					if(!skuIdList.contains(skuId)){
						MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = new MerchantProductVirtualStockDTO();
						merchantProductVirtualStockDTO.setSkuId(skuId);
						merchantProductVirtualStockDTO.setStandardProductUnitId(productId);
						merchantProductVirtualStockDTO.setRealFrozenStockNum(0L);
						merchantProductVirtualStockDTO.setRealStockNum(0L);
						merchantProductVirtualStockDTO.setPlatformId(platformId);
						merchantProductVirtualStockWriteService.saveMerchantProductVirtualStockDTO(merchantProductVirtualStockDTO);
						skus.add(skuId);
					}
					saveContactSkuStock(productId,skus);	
				}
			}
			// 存在的skuId集合为空直接新增
			else{
				for (Long skuId : skuIds) {
					MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = new MerchantProductVirtualStockDTO();
					merchantProductVirtualStockDTO.setSkuId(skuId);
					merchantProductVirtualStockDTO.setStandardProductUnitId(productId);
					merchantProductVirtualStockDTO.setRealFrozenStockNum(0L);
					merchantProductVirtualStockDTO.setRealStockNum(0L);
					merchantProductVirtualStockDTO.setPlatformId(platformId);
					merchantProductVirtualStockWriteService.saveMerchantProductVirtualStockDTO(merchantProductVirtualStockDTO);
				}
				saveContactSkuStock(productId,skuIds);
			}
			
		}
			
		
		
	}
	
	/**
	 * 同步共用库存关联
	 * @param productId
	 * @param skuIdList
	 */
	private void saveContactSkuStock(Long productId,List<Long> skuIdList) {
		//如果有新增sku 则添加库存记录
		if(skuIdList.size() > 0) {
			ContactGroupStockDTO dto = new ContactGroupStockDTO();
			dto.setSpuId(productId);
			List<ContactGroupStockDTO> list = contactGroupStockReadService.findContactGroupStockAll(dto);
			if(list != null && list.size() > 0) {
				List<ContactGroupSkuStockDTO> contactGroupSkuStockDTOList = new ArrayList<>();
				for (ContactGroupStockDTO contactGroupStockPO : list) {
					for (Long sku : skuIdList) {
						ContactGroupSkuStockDTO skuPo = new ContactGroupSkuStockDTO();
						skuPo.setContactGroupStockId(contactGroupStockPO.getId());
						skuPo.setSkuId(sku);
						contactGroupSkuStockDTOList.add(skuPo);
					}
				}
				contactGroupSkuStockWriteService.insertContactGroupSkuStockListWithTx(contactGroupSkuStockDTOList);
			}
		}
	}

	private void SaveSkuThread(Long productId) {
		// 根据spuid查询spu信息
		StandardProductUnitDTO standardProductUnitDTO = new StandardProductUnitDTO();
		standardProductUnitDTO.setId(productId);
		StandardProductUnitDTO standardProductUnitDTO2 = standardProductUnitReadService
				.findStandardProductUnitById(standardProductUnitDTO);
		List<Long> skuIdList = productWriteService.assembleSku(productId, standardProductUnitDTO2.getName(),
				standardProductUnitDTO2.getProductSerialNumber());
		for (Long skuId : skuIdList) {
			MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = new MerchantProductVirtualStockDTO();
			merchantProductVirtualStockDTO.setSkuId(skuId);
			merchantProductVirtualStockDTO.setStandardProductUnitId(standardProductUnitDTO2.getId());
			merchantProductVirtualStockDTO.setRealFrozenStockNum(0L);
			merchantProductVirtualStockDTO.setRealStockNum(0L);
			merchantProductVirtualStockDTO.setPlatformId(standardProductUnitDTO2.getPlatformId());
			merchantProductVirtualStockWriteService.saveMerchantProductVirtualStockDTO(merchantProductVirtualStockDTO);
		}

	}

	public ProductDTO ProductById(ProductDTO dto) {

		return productReadService.findById(dto);
	}

	/**
	 * 根据产品id查询产品图片信息
	 * 
	 * @param id
	 * @return
	 */
	public List<PictureDTO> pictureByProductId(Long pictureId) {

		return pictureReadService.pictureByProductId(pictureId);
	}

	/**
	 * 根据产品id查询产品信息及产品详情
	 * 
	 * @param id
	 * @return
	 */
	public ProductDTO productAndProductDescriptionById(Long productId) {

		return productReadService.productAndProductDescriptionById(productId);
	}

	/**
	 * 保存spu草稿信息
	 * 
	 * @param id
	 * @return
	 */
	public Long insertProductWithTx(ProductDTO productDTO, ProductDescriptionDTO productDescriptionDTO) {

		return productWriteService.insertProductWithTx(productDTO, productDescriptionDTO);
	}

	/**
	 * 设置是否启用
	 * 
	 * @param productVO
	 * @return
	 */
	public int updateAvailable(ProductDTO dto) {
		return productWriteService.updateAvailableWithTx(dto);
	}

	/**
	 * 通过spu草稿信息查询spu信息并将之前信息覆盖
	 * 
	 * @return
	 */
	private PageResult<ProductDTO> findStandardProductUnit(PageResult<ProductDTO> findPage) {
		List<ProductDTO> list = findPage.getList();
		List<Long> ids = new ArrayList<Long>();
		for (ProductDTO productDTO : list) {
			ids.add(productDTO.getId());
		}
		List<StandardProductUnitDTO> standardProductUnitList = null;
		if (EmptyUtil.isNotEmpty(ids)) {
			// 根据以通过的spu草稿id集合查询spu信息
			standardProductUnitList = standardProductUnitReadService.findProductByIds(ids);
		} else {
			standardProductUnitList = new ArrayList<>();
		}

		for (ProductDTO productDTO : list) {
			for (StandardProductUnitDTO standardProductUnitDTO : standardProductUnitList) {
				if (productDTO.getId().equals(standardProductUnitDTO.getId())) {
					productDTO.setProductSerialNumber(standardProductUnitDTO.getProductSerialNumber());
					productDTO.setBrandId(standardProductUnitDTO.getBrandId());
					productDTO.setCategoryTreeNodeId(standardProductUnitDTO.getCategoryTreeNodeId());
					productDTO.setTitle(standardProductUnitDTO.getTitle());
					productDTO.setName(standardProductUnitDTO.getName());
					productDTO.setChineseName(standardProductUnitDTO.getChineseName());
					productDTO.setMarketPrice(standardProductUnitDTO.getMarketPrice());
					productDTO.setTaxNo(standardProductUnitDTO.getTaxNo());
					productDTO.setEanNo(standardProductUnitDTO.getEanNo());
					productDTO.setPlaceOfOrigin(standardProductUnitDTO.getPlaceOfOrigin());
					productDTO.setCalculationUnit(standardProductUnitDTO.getCalculationUnit());
					productDTO.setSpuStatus(standardProductUnitDTO.getStatus());
					productDTO.setIsAvailable(standardProductUnitDTO.getIsAvailable());
					productDTO.setProductDetails(standardProductUnitDTO.getProductDetails());
					productDTO.setCreateTime(standardProductUnitDTO.getCreateTime());
					productDTO.setUpdateTime(standardProductUnitDTO.getUpdateTime());
					productDTO.setPlatformId(standardProductUnitDTO.getPlatformId());
					break;
				}
			}
		}

		PageResult<ProductDTO> result = new PageResult<ProductDTO>();
		result.setList(list);
		result.setPageNo(findPage.getPageNo());
		result.setPageSize(findPage.getPageSize());
		result.setTotalSize(findPage.getTotalSize());
		return result;
	}

	public AttributeNameVO findById(AttributeNameDTO dto) {
		// 根据属性id查询属性信息
		AttributeNameDTO attributeNameDTO = attributeNameReadService.findById(dto);
		if (EmptyUtil.isNotEmpty(attributeNameDTO)) {
			AttributeNameVO attributeNameVO = new AttributeNameVO();
			// 根据类目id和属性id查询类目属性是否必填写 0否、1是
			int isRequired = categoryAttNameReadService.isRequiredByCategoryIdAttNameId(dto.getCategoryId(),
					dto.getId());
			attributeNameDTO.setIsRequired(isRequired);

			if (attributeNameDTO.getMode() == 2) {
				/*String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0,
						attributeNameDTO.getBeginDecimal().toString().length() - 3);
				Integer begin = Integer.valueOf(beginDecimal);
				String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0,
						attributeNameDTO.getFinishDecimal().toString().length() - 3);
				Integer finish = Integer.valueOf(finishDecim);
				attributeNameDTO.setReminder((isRequired == 1) ? "请输入" + begin + "~" + finish + "位字符" + "(必填)"
						: "请输入" + begin + "~" + finish + "位字符");*/
				Integer begin = null ;
				Integer finish = null;
				if ( EmptyUtil.isNotEmpty(attributeNameDTO.getBeginDecimal()) ) {
					//String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0, attributeNameDTO.getBeginDecimal().toString().length()-3);
					begin = attributeNameDTO.getBeginDecimal().intValue();
				}
				if ( EmptyUtil.isNotEmpty(attributeNameDTO.getFinishDecimal()) ) {
					//String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0, attributeNameDTO.getFinishDecimal().toString().length()-3);
					finish = attributeNameDTO.getFinishDecimal().intValue();
				}
				if ( EmptyUtil.isNotEmpty(begin) && EmptyUtil.isNotEmpty(finish) ) {
					attributeNameDTO.setReminder((isRequired == 1)?"请输入"+ begin + "~" +finish +"位字符"+"(必填)":"请输入"+ begin + "~" +finish +"位字符");
				}
			} else if (attributeNameDTO.getMode() == 4) {
				String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0,
						attributeNameDTO.getBeginDecimal().toString().length() - 3);
				Long begin = Long.valueOf(beginDecimal);
				String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0,
						attributeNameDTO.getFinishDecimal().toString().length() - 3);
				Long finish = Long.valueOf(finishDecim);
				attributeNameDTO.setReminder(
						(isRequired == 1) ? "请输入" + format.format(begin) + "~" + format.format(finish) + "时间" + "(必填)"
								: "请输入" + format.format(begin) + "~" + format.format(finish) + "时间");
			} else if (attributeNameDTO.getMode() == 5) {
				if (attributeNameDTO.getImportHint() == null) {
					String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0,
							attributeNameDTO.getBeginDecimal().toString().length() - 3);
					Integer begin = Integer.valueOf(beginDecimal);
					String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0,
							attributeNameDTO.getFinishDecimal().toString().length() - 3);
					Integer finish = Integer.valueOf(finishDecim);
					attributeNameDTO.setReminder((isRequired == 1) ? "请输入" + begin + "~" + finish + "之内整数数字" + "(必填)"
							: "请输入" + begin + "~" + finish + "之内整数数字");
				} else {
					attributeNameDTO.setReminder(attributeNameDTO.getImportHint());
				}
			} else if (attributeNameDTO.getMode() == 6) {
				if (attributeNameDTO.getImportHint() == null) {
					String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0,
							attributeNameDTO.getBeginDecimal().toString().length() - 3);
					Integer begin = Integer.valueOf(beginDecimal);
					String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0,
							attributeNameDTO.getFinishDecimal().toString().length() - 3);
					Integer finish = Integer.valueOf(finishDecim);
					attributeNameDTO.setReminder((isRequired == 1) ? "请输入" + begin + "~" + finish + "之内数字" + "(必填)"
							: "请输入" + begin + "~" + finish + "之内数字");
				} else {
					attributeNameDTO.setReminder(attributeNameDTO.getImportHint());
				}
			}

			attributeNameVO.setId(attributeNameDTO.getId());
			attributeNameVO.setName(attributeNameDTO.getName());
			attributeNameVO.setMode(attributeNameDTO.getMode());
			attributeNameVO.setType(attributeNameDTO.getType());
			attributeNameVO.setReminder(attributeNameDTO.getReminder());
			attributeNameVO.setImportHint(attributeNameDTO.getImportHint());
			attributeNameVO.setIsRequired(attributeNameDTO.getIsRequired());

			// 根据属性id查询属性值信息
			AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
			attributeValueDTO.setAttributeNameId(dto.getId());
			List<AttributeValueDTO> list = attributeValueReadService.findAll(attributeValueDTO);
			List<AttValueDTO> lists = new ArrayList<AttValueDTO>();
			for (AttributeValueDTO attributeValueDTO2 : list) {
				AttValueDTO attValue = new AttValueDTO();
				attValue.setId(attributeValueDTO2.getId());
				attValue.setParentId(attributeValueDTO2.getParentId());
				attValue.setAttValue(attributeValueDTO2.getValue());
				attValue.setSortValue(attributeValueDTO2.getSortValue());
				attValue.setSpecificationCode(attributeValueDTO2.getSpecificationCode());
				lists.add(attValue);
			}
			attributeNameVO.setLists(lists);
			return attributeNameVO;
		}
		return null;

	}

}
