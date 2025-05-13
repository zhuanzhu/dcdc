package com.egeo.components.product.facade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.product.dto.CommodityDetailsDTO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MerchantProdPictureDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.dto.SellPlatformMerchantProdDTO;
import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.enums.SUConstant;
import com.egeo.components.product.service.read.CommodityProductUnitReadService;
import com.egeo.components.product.service.read.JdProductInnerIdReadService;
import com.egeo.components.product.service.read.MerchantProdPictureReadService;
import com.egeo.components.product.service.read.MerchantProductReadService;
import com.egeo.components.product.service.read.MerchantProductTagReadService;
import com.egeo.components.product.service.read.ProductUnitReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttNameReadService;
import com.egeo.components.product.service.read.StandardProductUnitAttValueReadService;
import com.egeo.components.product.service.read.StandardProductUnitReadService;
import com.egeo.components.product.service.read.StandardUnitReadService;
import com.egeo.components.product.service.read.StandardUnitStoreReadService;
import com.egeo.components.product.service.read.StoreProductUnitReadService;
import com.egeo.components.product.service.read.StoreReadService;
import com.egeo.components.product.service.read.StoreTreeNodeReadService;
import com.egeo.components.product.service.write.MerchantProductWriteService;
import com.egeo.components.product.service.write.SkuWriteService;
import com.egeo.components.stock.client.CommodityProductUnitStockRunningWaterClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.ContactGroupPuStockClient;
import com.egeo.components.stock.client.ContactGroupSkuStockClient;
import com.egeo.components.stock.client.ContactGroupStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.user.client.ClientClient;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.ClientDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.entity.CacheUser;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class MerchantProductFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(MerchantProductFacade.class);
	
	@Resource
	private MerchantProductReadService merchantProductReadService;
	
	@Resource
	private MerchantProductWriteService merchantProductWriteService;
	
	@Autowired
	private ClientClient clientReadService;
	
	@Autowired
	private CompanyClient companyReadService;
	
	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockWriteService;
	
	@Resource
	private SkuWriteService skuWriteService;
	
	@Resource
	private MerchantProductTagReadService merchantProductTagReadService;
	
	@Resource
	private MerchantProdPictureReadService merchantProdPictureReadService;
	
	@Resource
	private StandardProductUnitAttNameReadService standardProductUnitAttNameReadService;
	
	@Resource
	private StandardProductUnitAttValueReadService standardProductUnitAttValueReadService;
	
	@Resource
	private StandardProductUnitReadService standardProductUnitReadService;
	
	@Resource
	private StandardUnitStoreReadService standardUnitStoreReadService;
	
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockWriteService;
	
	@Resource
	private StoreProductUnitReadService storeProductUnitReadService;
	
	@Resource
	private StandardUnitReadService standardUnitReadService;

	@Resource
	private StoreTreeNodeReadService storeTreeNodeReadService;

	@Resource
	private StoreReadService storeReadService;
	@Resource
	private CommodityProductUnitReadService commodityProductUnitReadService;
	
	@Autowired
	private ContactGroupPuStockClient contactGroupPuStockReadService;
	@Resource
	private ProductUnitReadService productUnitReadService;
	@Autowired
	private ContactGroupPuStockClient contactGroupPuStockWriteService;
	@Autowired
	private ContactGroupStockClient contactGroupStockReadService;
	
	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;
	@Autowired
	private CommodityProductUnitStockRunningWaterClient commodityProductUnitStockRunningWaterWriteService;
	
	@Autowired
	private SoItemClient soItemReadService;
	@Resource
	private JdProductInnerIdReadService jdProductInnerIdReadService;
	@Autowired
	private CommodityProductUnitStockRunningWaterClient commodityProductUnitStockRunningWaterReadService;
	@Autowired
	private ContactGroupSkuStockClient contactGroupSkuStockReadService;
	
	public MerchantProductDTO findMerchantProductById(MerchantProductDTO dto){
		
		return merchantProductReadService.findMerchantProductById(dto);
	}

	public PageResult<MerchantProductDTO> findMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, Date startTime, Date endTime, MerchantProductDTO dto, Pagination page, List<String> nameList){
		String storeName = dto.getStoreName();
		List<Long> storeIds=storeReadService.findStoreByName(dto.getStoreName());
		dto.setStoreName(null);
		return merchantProductReadService.findMerchantProductOfPage(priceStart,priceEnd,startProfit,endProfit,storeIds,startTime, endTime, dto, page,nameList);
	}

	public PageResult<MerchantProductDTO> findPlatformMerchantProductOfPage(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, Integer endProfit, Date startTime, Date endTime, MerchantProductDTO dto, Pagination page, List<String> nameList){
		String storeName = dto.getStoreName();
		List<Long> storeIds=storeReadService.findStoreByName(dto.getStoreName());
		dto.setStoreName(null);
		return merchantProductReadService.findPlatformMerchantProductOfPage(priceStart,priceEnd,startProfit,endProfit,storeIds,startTime, endTime, dto, page,nameList);
	}
	public List<MerchantProductDTO> findMerchantProductAll(MerchantProductDTO dto){
		
		return merchantProductReadService.findMerchantProductAll(dto);
		
	}

	public Long insertMerchantProductWithTx(MerchantProductDTO dto,
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
			List<Long> membershipIds){
		
		return merchantProductWriteService.insertMerchantProductWithTx(
				dto,picture,pictureList,webBannerPictureList,sellPlatformMerchantProdVOList,
				clientList,content,productUnitList,tagList,keywords,companys,demoCompanys,competingCompanys,
				storeIds,membershipIds);
	}
	/**
	 * 根据su商品草稿id更新su商品草稿信息
	 * @param dto su商品信息
	 * @param sellPlatformMerchantProdList su商品平台关系（此功能去除）
	 * @param clientList 客户端id集合
	 * @param content su商品详情
	 * @param tagList 标签id集合
	 * @param keywords 关键词id集合
	 * @param companys 正式公司id集合
	 * @param demoCompanys 演示公司id集合
	 * @param competingCompanys 竞品公司id集合
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
			List<Long> membershipIdList){
		
		return merchantProductWriteService.updateMerchantProductWithTx(
				dto,sellPlatformMerchantProdVOList,clientList,tagList,keywords,companys,demoCompanys,competingCompanys,webBannerPictureList,
				storeIdList,membershipIdList);
	}

	public int deleteMerchantProductWithTx(MerchantProductDTO dto){
		
		return merchantProductWriteService.deleteMerchantProductWithTx(dto);
		
	}
	/**
	 * 提交审核
	 * @param dto
	 * @return
	 */
	public int submitAuditWithTx(MerchantProductDTO dto) {
		dto.setStatus(SUConstant.SU_STATUS_DELIVERED.getStatus());
		return merchantProductWriteService.submitAuditWithTx(dto);
	}
	/**
	 * 根据所属平台id集合查询所属平台信息
	 * @param clientIds
	 * @return
	 */
	public List<ClientDTO> findClientByClientIds(List<Long> clientIds) {
		// TODO Auto-generated method stub
		return clientReadService.findClientByClientIds(com.egeo.utils.StringUtils.longsToStrings(clientIds));
	}
	
	/**
	 * 查询所有所属平台
	 * @param clientIds
	 * @return
	 */
	public List<ClientDTO> findClientAll(ClientDTO clientDTO) {
		// TODO Auto-generated method stub
		return clientReadService.findClientAll(clientDTO);
	}
	/**
	 * 根据福利公司id集合查询福利公司信息
	 * @param companyIds
	 * @return
	 */
	public List<CompanyDTO> findCompanyByCompanyIds(List<Long> companyIds) {
		// TODO Auto-generated method stub
		return companyReadService.findCompanyByCompanyIds(com.egeo.utils.StringUtils.longsToStrings(companyIds));
	}
	
	/**
	 * 查询所有福利公司
	 * @param companyIds
	 * @return
	 */
	public List<CompanyDTO> findCompanyAll(CompanyDTO dto) {
		// TODO Auto-generated method stub
		return companyReadService.findCompanyAll(dto);
	}
	/**
	 * 修改su草稿状态为已上架
	 * @param merchantProductDTO
	 * @return
	 */
	public int updateStatus(MerchantProductDTO merchantProductDTO) {
		// TODO Auto-generated method stub
		return merchantProductWriteService.updateStatusWithTx(merchantProductDTO);
	}
	/**
	 * 根据su草稿id更新su草稿图片信息
	 * @param merchantProductId
	 * @param picture
	 * @param pictureList
	 * @return
	 */
	public int updateMerchantProductPictureByIdWithTx(Long merchantProductId, String picture,
			List<String> pictures,List<String> webBannerPictures,MerchantProductDTO merchantProductDTO) {
		// TODO Auto-generated method stub
		return merchantProductWriteService.updateMerchantProductPictureByIdWithTx(merchantProductId, picture,pictures,webBannerPictures,merchantProductDTO);
	}
	/**
	 * 根据su草稿id更新supu信息
	 * @param merchantProductId
	 * @param picture
	 * @param pictureList
	 * @return
	 */
	public int updateProductUnitByIdWithTx(List<ProductUnitDTO> dto,MerchantProductDTO merchantProductDTO,
										   List<Long> companyIds,List<Long> demoCompanyIds,List<Long> competingCompanyIds) {
		// TODO Auto-generated method stub
		return merchantProductWriteService.updateProductUnitByIdWithTx(dto,merchantProductDTO,
				companyIds,demoCompanyIds,competingCompanyIds);
	}
	/**
	 * 根据id通过
	 * @param vo
	 * @param req
	 * @return
	 */
	public int merchantProductPassWithTx(Long merchantProductId,Long platformId) {
		int i = 0;
		List<Long> puIdList = merchantProductWriteService.merchantProductPassWithTx(merchantProductId,platformId);
		//批量根据pu生成库存信息
		if(puIdList!=null && puIdList.size()>0) {
			savePuStock(puIdList,merchantProductId,platformId);
		}
		i = 1;
		return i;
	}
	/**
	 * 批量根据pu生成库存信息
	 * @param puIdList
	 */
	private void savePuStock(List<Long> puIdList,Long standardUnitId,Long platformId) {
		
		commodityProductUnitWarehouseStockWriteService.insertCommodityProductUnitWarehouseStockAllWithTx(com.egeo.utils.StringUtils.longsToStrings(puIdList),standardUnitId,platformId);
			
		// 根据suid查询是否存在su信息
		StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
		standardUnitDTO.setId(standardUnitId);
		StandardUnitDTO standardUnitDTO2 = standardUnitReadService.findStandardUnitById(standardUnitDTO);
		if(EmptyUtil.isNotEmpty(standardUnitDTO2)){
			// 创建门店库存信息集合
			List<StorePuWarehouseStockDTO> list = createStorePuWarehouseStock(standardUnitId,platformId);
			
			// 批量保存门店pu库存信息
			storePuWarehouseStockWriteService.insertAllWithTx(list);
		}
		
	}
	
	/**
	 * 存在共用库存关系时 根据pu生成库存信息
	 * @param puIdList
	 */
	private void savePuStock(List<Long> puIdList,Long standardUnitId,Long platformId,List<CommodityProductUnitDTO> commodityProductUnitDTOs,
			CacheUser user,String ip) {
		
		ContactGroupPuStockDTO contactGroupPuStockDTO = new ContactGroupPuStockDTO();
		contactGroupPuStockDTO.setSuId(standardUnitId);
		List<ContactGroupPuStockDTO> dtos = contactGroupPuStockReadService.findContactGroupPuStockAll(contactGroupPuStockDTO);
		
		//判断是否有共用库存 有的话 进行联动修改
		if(dtos != null && dtos.size() > 0) {
			//
			logger.info("suID:{},共用库存条数：{}",standardUnitId,dtos.size());
			for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitDTOs) {
				
				List<ContactGroupSkuStockDTO> contactGroupSkuStockDTOs = contactGroupSkuStockReadService.findContactGroupSkuStockBySuId(standardUnitId);
				for (ContactGroupSkuStockDTO contactGroupSkuStockDTO : contactGroupSkuStockDTOs) {
					if(contactGroupSkuStockDTO.getSkuId().equals(commodityProductUnitDTO.getSkuId())) {

						CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO = new CommodityProductUnitWarehouseStockDTO();
				    	commodityProductUnitWarehouseStockDTO.setCommodityProductUnitId(commodityProductUnitDTO.getId());
				    	commodityProductUnitWarehouseStockDTO.setCommodityProductUnitName(commodityProductUnitDTO.getStandardUnitName());
				    	commodityProductUnitWarehouseStockDTO.setPlatformId(platformId);
				    	commodityProductUnitWarehouseStockDTO.setProductUnitSerialNumber(commodityProductUnitDTO.getProductUnitSerialNumber());
				    	commodityProductUnitWarehouseStockDTO.setMerchantProductId(standardUnitId);
				    	commodityProductUnitWarehouseStockDTO.setStandardUnitId(standardUnitId);
				    	commodityProductUnitWarehouseStockDTO.setMerchantId(1L);
				    	commodityProductUnitWarehouseStockDTO.setRealFrozenStockNum(0L);
				    	commodityProductUnitWarehouseStockDTO.setRealStockNum(0L);
				    	commodityProductUnitWarehouseStockWriteService.insertCommodityProductUnitWarehouseStockWithTx(commodityProductUnitWarehouseStockDTO);
						
				    	findCommodityProductUnitWarehouseStockByPuId(commodityProductUnitDTO.getId(), contactGroupSkuStockDTO,user,ip,null);
						contactGroupPuStockDTO.setPuId(commodityProductUnitDTO.getId());
						contactGroupPuStockDTO.setContactGroupSkuId(contactGroupSkuStockDTO.getId());
						contactGroupPuStockWriteService.insertContactGroupPuStockWithTx(contactGroupPuStockDTO);
					}
				}
			}
			
		}else {
			commodityProductUnitWarehouseStockWriteService.insertCommodityProductUnitWarehouseStockAllWithTx(com.egeo.utils.StringUtils.longsToStrings(puIdList),standardUnitId,platformId);
			
		}
		// 根据suid查询是否存在su信息
		StandardUnitDTO standardUnitDTO = new StandardUnitDTO();
		standardUnitDTO.setId(standardUnitId);
		StandardUnitDTO standardUnitDTO2 = standardUnitReadService.findStandardUnitById(standardUnitDTO);
		if(EmptyUtil.isNotEmpty(standardUnitDTO2)){
			// 创建门店库存信息集合
			List<StorePuWarehouseStockDTO> list = createStorePuWarehouseStock(standardUnitId,platformId);
			
			// 批量保存门店pu库存信息
			storePuWarehouseStockWriteService.insertAllWithTx(list);
		}
		
	}
	
	
	/**
	 * 创建门店库存信息集合
	 * @param standardUnitId
	 * @param platformId
	 * @return
	 */
	private List<StorePuWarehouseStockDTO> createStorePuWarehouseStock(Long standardUnitId, Long platformId) {
		// 商家默认为1
		Long merchantId = 1L;
		// 根据su商品id查询门店pu信息
		StoreProductUnitDTO storeProductUnitDTO = new StoreProductUnitDTO();
		storeProductUnitDTO.setStandardUnitId(standardUnitId);
		storeProductUnitDTO.setPlatformId(platformId);
		List<StoreProductUnitDTO> storeProductUnitList = storeProductUnitReadService.findStoreProductUnitAll(storeProductUnitDTO);
		List<StorePuWarehouseStockDTO> list = new ArrayList<>(storeProductUnitList.size());
		for (StoreProductUnitDTO storeProductUnitDTO2 : storeProductUnitList) {
			StorePuWarehouseStockDTO storePuWarehouseStockDTO = new StorePuWarehouseStockDTO();
			storePuWarehouseStockDTO.setMerchantId(merchantId);
			storePuWarehouseStockDTO.setRealFrozenStockNum(0L);
			storePuWarehouseStockDTO.setRealStockNum(0L);
			storePuWarehouseStockDTO.setStandardUnitId(storeProductUnitDTO2.getStandardUnitId());
			storePuWarehouseStockDTO.setStoreProductUnitId(storeProductUnitDTO2.getId());
			storePuWarehouseStockDTO.setStoreId(storeProductUnitDTO2.getStoreId());
			storePuWarehouseStockDTO.setPlatformId(platformId);
			list.add(storePuWarehouseStockDTO);
		}
		return list;
	}

	/**
	 * 批量通过
	 * @param vo
	 * @param req
	 * @return
	 */
	public int passAllAuditWithTx(String ids,Long platformId) {
		int i = 0;
		List<Long> list = JSONArray.parseArray(ids, Long.class);
		for (Long merchantProductId : list) {
			i = i + merchantProductPassWithTx(merchantProductId,platformId);
		}
		return i;
	}
	/**
	 * 根据spuid更新supu信息(共用库存)
	 * @param standardProductUnitId
	 * @param id
	 */
	public void updateSuPu(Long standardProductUnitId, Long merchantProductId,Long platformId,CacheUser user,String ip) {
		//新建共用库存关联：如果正式su表有数据
		//根据su查询所有sku
		List<CommodityProductUnitDTO> puDtoList = skuWriteService.updateSuPuWithTx(standardProductUnitId, merchantProductId);
		List<Long> puIdList = new ArrayList<>();
		for (CommodityProductUnitDTO dto : puDtoList) {
			puIdList.add(dto.getId());
		}
		logger.info("新增Pu数量：{},puIds:{}",puDtoList.size(),puIdList);
		//批量根据pu生成库存信息
		savePuStock(puIdList,merchantProductId,platformId,puDtoList,user,ip);
		
	}
	
	/**
	 * 根据spuid更新supu信息
	 * @param standardProductUnitId
	 * @param id
	 */
	public void updateSuPu(Long standardProductUnitId, Long merchantProductId,Long platformId) {
		//新建共用库存关联：如果正式su表有数据
		//根据su查询所有sku
		List<CommodityProductUnitDTO> puDtoList = skuWriteService.updateSuPuWithTx(standardProductUnitId, merchantProductId);
		List<Long> puIdList = new ArrayList<>();
		for (CommodityProductUnitDTO dto : puDtoList) {
			puIdList.add(dto.getId());
		}
		
		//批量根据pu生成库存信息
		logger.info("新增Pu数量：{},puIds:{}",puDtoList.size(),puIdList);
		savePuStock(puIdList,merchantProductId,platformId);
		
	}
	
	/**
	 * 根据su草稿id查询标签信息集合
	 * @param id
	 * @return
	 */
	public List<MerchantProductTagDTO> findTagAllByMerchantProductId(Long merchantProductId) {
		// TODO Auto-generated method stub
		return merchantProductTagReadService.findTagAllByMerchantProductId(merchantProductId);
	}
	/**
	 * 根据su草稿id查询基本信息（app预览）
	 * @param merchantProductId
	 * @return
	 */
	public Map<String, Object> findMerchantProductAppById(Long merchantProductId) {
		MerchantProductDTO merchantProductDTO = merchantProductReadService.findMerchantProductAppById(merchantProductId);
		
		//根据su草稿id查询su草稿图片信息
		List<MerchantProdPictureDTO> merchantProdPictureList = merchantProdPictureReadService.findByMerchantProductId(merchantProductId);
		
		Map<String, Object> merchantProductMap = new HashMap<String, Object>();
		merchantProductMap.put("merchantProductId", merchantProductDTO.getId());
		merchantProductMap.put("merchantProductSerialNumber", merchantProductDTO.getMerchantProductSerialNumber());
		merchantProductMap.put("name", merchantProductDTO.getName());
		merchantProductMap.put("salePrice", merchantProductDTO.getSalePrice());
		merchantProductMap.put("marketPrice", merchantProductDTO.getMarketPrice());
		merchantProductMap.put("promotionPrice", merchantProductDTO.getPromotionPrice());
		merchantProductMap.put("status", merchantProductDTO.getStatus());
		merchantProductMap.put("saleWay", merchantProductDTO.getSaleWay());
		merchantProductMap.put("freightExplain", merchantProductDTO.getFreightExplain());
		merchantProductMap.put("shipmentsExplain", merchantProductDTO.getShipmentsExplain());
		merchantProductMap.put("freightTemplateId", merchantProductDTO.getFreightTemplateId());
		merchantProductMap.put("content", merchantProductDTO.getContent());
		merchantProductMap.put("stockWarning", merchantProductDTO.getStockWarning());
		merchantProductMap.put("picture", null);
		List<String> picturelist = new ArrayList<>();
		for (MerchantProdPictureDTO merchantProdPictureDTO2 : merchantProdPictureList) {
			if(merchantProdPictureDTO2.getType() == 1){
				merchantProductMap.put("picture", merchantProdPictureDTO2.getPictureUrl());
			}
			if(merchantProdPictureDTO2.getType() == 2){
				picturelist.add(merchantProdPictureDTO2.getPictureUrl());
			}
		}
		merchantProductMap.put("picturelist", picturelist);
		
		saveProduct(merchantProductDTO.getStandardProductUnitId(),merchantProductMap);
		return merchantProductMap;
	}

	private void saveProduct(Long standardProductUnitId,Map<String, Object> merchantProductMap) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> maps = new ArrayList<>();
		//根据suid查询su参数属性
		List<StandardProductUnitAttNameDTO> standardProductUnitAttNameList = standardProductUnitAttNameReadService.findByStandardProductUnitId(standardProductUnitId);
		for (StandardProductUnitAttNameDTO standardProductUnitAttNameDTO2 : standardProductUnitAttNameList) {
			Map<String, Object> map = new HashMap<>();
			map.put("attName", standardProductUnitAttNameDTO2.getAttName());
			if(standardProductUnitAttNameDTO2.getMode() == 2){
				//根据su属性id查询su属性值信息
				StandardProductUnitAttValueDTO standardProductUnitAttValueDTO = new StandardProductUnitAttValueDTO();
				standardProductUnitAttValueDTO.setStandardProductUnitAttNameId(standardProductUnitAttNameDTO2.getId());
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(standardProductUnitAttValueDTO);
				if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList)){
					map.put("attValue", standardProductUnitAttValueList.get(0).getAttValueCustom());
				}
				
			}else if(standardProductUnitAttNameDTO2.getMode() == 5 || standardProductUnitAttNameDTO2.getMode() == 6){
				//根据su属性id查询su属性值信息
				StandardProductUnitAttValueDTO standardProductUnitAttValueDTO = new StandardProductUnitAttValueDTO();
				standardProductUnitAttValueDTO.setStandardProductUnitAttNameId(standardProductUnitAttNameDTO2.getId());
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(standardProductUnitAttValueDTO);
				if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList)){
					map.put("attValue", new String(standardProductUnitAttValueList.get(0).getAttValueCustom() + standardProductUnitAttNameDTO2.getUnit()));
				}
			}else if(standardProductUnitAttNameDTO2.getMode() == 1 || standardProductUnitAttNameDTO2.getMode() == 3){
				//根据su属性id查询属性值信息
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findByStandardProductUnitAttNameId(standardProductUnitAttNameDTO2.getId());
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < standardProductUnitAttValueList.size(); i++) {
					if(i >= standardProductUnitAttValueList.size() - 1){
						stringBuffer.append(standardProductUnitAttValueList.get(i).getAttValue());
					}else{
						stringBuffer.append(standardProductUnitAttValueList.get(i).getAttValue());
						stringBuffer.append(",");
					}
					
				}
				map.put("attValue", stringBuffer.toString());
				map.put("unit", standardProductUnitAttNameDTO2.getUnit());
			}else if(standardProductUnitAttNameDTO2.getMode() == 4){
				//根据su属性id查询su属性值信息
				StandardProductUnitAttValueDTO standardProductUnitAttValueDTO = new StandardProductUnitAttValueDTO();
				standardProductUnitAttValueDTO.setStandardProductUnitAttNameId(standardProductUnitAttNameDTO2.getId());
				List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList = standardProductUnitAttValueReadService.findStandardProductUnitAttValueAll(standardProductUnitAttValueDTO);
				if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList)){
					if(EmptyUtil.isNotEmpty(standardProductUnitAttValueList.get(0).getAttValueCustom())){
						String string = standardProductUnitAttValueList.get(0).getAttValueCustom();
				        String str = sdf.format(new Date(Long.valueOf(string)));    
						map.put("attValue", str);
					}else{
						map.put("attValue", null);
					}
					
				}
				
			}
			maps.add(map);
			
		}
		merchantProductMap.put("attList", maps);
	}

	/**
	 * 判断spu是否是unit商品
	 * @param standardProductUnitId
	 * @return
	 */
	public boolean queryIsUnit(Long standardProductUnitId) {
		return standardProductUnitReadService.queryIsUnit(standardProductUnitId);
	}


	public PageResult<MerchantProductDTO> findCommodityDetailsOfPage(CommodityDetailsDTO dto, Pagination page) {
		return merchantProductReadService.findCommodityDetailsOfPage(dto, page);
	}

    public List<Map<String,Object>> findPuNameBySuIdBackStage(Long suId) {
		List<CommodityProductUnitDTO> puList=commodityProductUnitReadService.findPuListBySuId(suId);
		if(EmptyUtil.isEmpty(puList)){
			return null;
		}
		List<Map<String, Object>> result = new ArrayList<>();
		for(CommodityProductUnitDTO dto:puList){
			Map<String, Object> map = new HashMap<>();
			map.put("id", dto.getId());
			map.put("puName", dto.getName());
			result.add(map);
		}
		return result;

    }
    
    public void findCommodityProductUnitWarehouseStockByPuId(Long puId,ContactGroupSkuStockDTO cgsDto,CacheUser user,String ip,String mac) {
    	
		CommodityProductUnitWarehouseStockDTO dtoNew =	commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(puId);
		
		CommodityProductUnitDTO CommodityProductUnitDTONew = new CommodityProductUnitDTO();
		CommodityProductUnitDTONew.setId(puId);
		CommodityProductUnitDTONew = commodityProductUnitReadService.findCommodityProductUnitById(CommodityProductUnitDTONew);
		CommodityProductUnitWarehouseStockDTO originalDto =	null;
		//原冻结库存
		long originalRealFrozenStockNum = 0L;
		//原真实库存
		long originalRealStockNum = 0L;
		
		long nowRealFrozenStockNum = 0L;
		long nowRealStockNum = 0L;
		
		List<Long> puIds = new ArrayList<>();
		List<ContactGroupPuStockDTO> list1 = contactGroupPuStockReadService.findContactGroupPuStockAll(new ContactGroupPuStockDTO(cgsDto.getId()));
		if(list1 != null) {
			for (ContactGroupPuStockDTO dto : list1) {
				puIds.add(dto.getPuId());
			}
		}
		if(puIds.size() > 0) {
			
			List<CommodityProductUnitDTO> commodityProductUnitDTOs = new ArrayList<>();
			
			originalDto = commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(puIds.get(0));
			commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIds(puIds);
			
			//新加冻结库存
			long additionsRealFrozenStockNum = dtoNew.getRealFrozenStockNum(); 
			logger.info("新加pu:{} ,冻结库存additionsRealFrozenStockNum : {}" ,puId,additionsRealFrozenStockNum);
			
			//新加真实库存
			long additionsRealStockNum = dtoNew.getRealStockNum(); 
			if(originalDto != null) {
				originalRealFrozenStockNum = originalDto.getRealFrozenStockNum(); 
				originalRealStockNum = originalDto.getRealStockNum(); 
				logger.info("原始pu:{} ,冻结库存originalRealFrozenStockNum : {}" ,originalDto.getCommodityProductUnitId(),originalRealFrozenStockNum);
				logger.info("原始pu:{} ,真实库存originalRealStockNum : {}" ,originalDto.getCommodityProductUnitId(),originalRealStockNum);
			}
			logger.info("新加pu:{} ,真实库存additionsRealStockNum : {}" ,puId,additionsRealStockNum);
			
			//
			//最终生成冻结库存
			nowRealFrozenStockNum = additionsRealFrozenStockNum + originalRealFrozenStockNum;
			
			//原始真实库存-原始冻结库存
			long originalNum = originalRealStockNum - originalRealFrozenStockNum;
			//新增真实库存-真实冻结库存
			long additionsNum = additionsRealStockNum - additionsRealFrozenStockNum;
			
			logger.info("原组内共用在线库存-原组内共用冻结量: {}" ,originalNum);
			logger.info("新增加 PU在线库存 - 新增加 PU 的冻结量: {}" ,additionsNum);
			//
			//新共用在线库存量 =原组内共用冻结量+新增加 PU 的冻结量+ max{原组内共用在线库存-原组内共用冻结量，新增加 PU在线库存 - 新增加 PU 的冻结量}
			nowRealStockNum = nowRealFrozenStockNum + (additionsNum > originalNum ?additionsNum :originalNum);
			logger.info("（开始）新加pu:{},原始pu:{},nowRealFrozenStockNum:{},nowRealStockNum:{}",puId,puIds,nowRealFrozenStockNum,nowRealStockNum);
			//
			commodityProductUnitDTOs.add(CommodityProductUnitDTONew);
			List<CommodityProductUnitStockRunningWaterDTO> dtos = new ArrayList<>();
			
			for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitDTOs) {
				CommodityProductUnitStockRunningWaterDTO WaterDTO = new CommodityProductUnitStockRunningWaterDTO();
				WaterDTO.setProductUnitSerialNumber(commodityProductUnitDTO.getProductUnitSerialNumber());
				WaterDTO.setCommodityProductUnitId(commodityProductUnitDTO.getId());
				WaterDTO.setCommodityProductUnitName(commodityProductUnitDTO.getName());
				//共用库存
				WaterDTO.setType(StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
				WaterDTO.setPlatformId(commodityProductUnitDTO.getPlatformId());
				
				if(commodityProductUnitDTO.getId() == CommodityProductUnitDTONew.getId()) {
					//冻结库存
					WaterDTO.setPreoperativeRealStockNum(additionsRealFrozenStockNum);
					WaterDTO.setOperationBackRealStockNum(nowRealFrozenStockNum);
					
					//真实库存
					WaterDTO.setPreoperativeStockNum(additionsRealStockNum);
					WaterDTO.setOperationBackStockNum(nowRealStockNum);
					WaterDTO.setStockChange(nowRealStockNum - additionsRealStockNum);
					
				} else {
					//冻结库存
					WaterDTO.setPreoperativeRealStockNum(originalRealFrozenStockNum);
					WaterDTO.setOperationBackRealStockNum(nowRealFrozenStockNum);
					
					//真实库存
					WaterDTO.setPreoperativeStockNum(originalRealStockNum);
					WaterDTO.setOperationBackStockNum(nowRealStockNum);
					WaterDTO.setStockChange(nowRealStockNum - originalRealStockNum);
				}
				
				WaterDTO.setCreateUserid(user.getId());
				WaterDTO.setCreateUserip(ip);
				WaterDTO.setCreateUsermac(mac);
				WaterDTO.setCreateUsername(user.getLoginName());
				WaterDTO.setContactGroupStockId(cgsDto.getContactGroupStockId());
				dtos.add(WaterDTO);
			}
			
			logger.info("共用库存同步-流水 条数 ：{}", dtos.size());
			if(dtos.size() > 0) {
				int a = commodityProductUnitStockRunningWaterWriteService.insertBatchCommodityProductUnitStockRunningWaterWithTx(dtos);
				logger.info("流水表成功插入条数：{}",a);
			}
			
			//----
			
			//-
			//
			List<CommodityProductUnitStockRunningWaterDTO> commodityProductUnitStockRunningWaterDTOs = new ArrayList<>();
			logger.info("开始查询未完成订单：---------------------------");
			
			logger.info("开始查询未确认订单：---------------------------");
			List<CommodityProductUnitStockRunningWaterDTO> commodityProductUnitStockRunningWaterDTOs0 = confirmWater(puId, puIds, additionsRealFrozenStockNum, nowRealStockNum,
						originalRealFrozenStockNum, CommodityProductUnitDTONew, commodityProductUnitDTOs, user, ip, mac, cgsDto.getContactGroupStockId(), 0);
			logger.info("开始查询已确认 未发货订单：---------------------------");
			List<CommodityProductUnitStockRunningWaterDTO> commodityProductUnitStockRunningWaterDTOs1 = confirmWater(puId, puIds, additionsRealFrozenStockNum, nowRealStockNum,
					originalRealFrozenStockNum, CommodityProductUnitDTONew, commodityProductUnitDTOs, user, ip, mac, cgsDto.getContactGroupStockId(), 1);
			
			if(commodityProductUnitStockRunningWaterDTOs0 != null) {
				commodityProductUnitStockRunningWaterDTOs.addAll(commodityProductUnitStockRunningWaterDTOs0);
			}
			
			if(commodityProductUnitStockRunningWaterDTOs1 != null) {
				commodityProductUnitStockRunningWaterDTOs.addAll(commodityProductUnitStockRunningWaterDTOs1);
			}
			
			logger.info("开始生成订单流水 流水条数 ：{}-",commodityProductUnitStockRunningWaterDTOs.size());
			
			if(commodityProductUnitStockRunningWaterDTOs.size() > 0) {
				commodityProductUnitStockRunningWaterWriteService.insertBatchCommodityProductUnitStockRunningWaterWithTx(commodityProductUnitStockRunningWaterDTOs);
			}
			
			puIds.add(puId);
			logger.info("(结束)新加pu:{},原始pu:{},nowRealFrozenStockNum:{},nowRealStockNum:{}",puId,puIds,nowRealFrozenStockNum,nowRealStockNum);
			
			commodityProductUnitWarehouseStockWriteService.updateCommodityProductUnitWarehouseStockWithTx(com.egeo.utils.StringUtils.longsToStrings(puIds),nowRealStockNum,nowRealFrozenStockNum);
			
		}
		
	}

	public List<CommodityProductUnitStockRunningWaterDTO> confirmWater(Long puId,List<Long> puIds,Long additionsRealFrozenStockNum,
			Long nowRealStockNum ,Long originalRealFrozenStockNum,CommodityProductUnitDTO commodityProductUnitDTONew,
			List<CommodityProductUnitDTO> commodityProductUnitDTOs,CacheUser user,String ip,String mac,Long contactStockId,
			Integer type){
		
		logger.info("开始查询未完成订单：---------------------------");
		
		List<SoItemDTO> additionsSoDtos = soItemReadService.findSoItemByPuId(puId, type);
		List<String> additionsOrderCodes = new ArrayList<>();
		for (SoItemDTO originalSoDto : additionsSoDtos) {
			additionsOrderCodes.add(originalSoDto.getOrderCode());
		}
		
		//
		List<CommodityProductUnitStockRunningWaterDTO> originalWaterDTOs = new ArrayList<>();
		logger.info("新加入PU未完成订单条数additionsOrderCodes ：{}",additionsOrderCodes.size());
		if(additionsOrderCodes.size() > 0) {
			originalWaterDTOs = commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(additionsOrderCodes,type + 1);
		}
		
		//原组内订单列表
		List<SoItemDTO> originalSoDtos = new ArrayList<>();
		
		originalSoDtos = soItemReadService.findSoItemByPuIds(com.egeo.utils.StringUtils.longsToStrings(puIds), type);
		
		
		List<String> originalOrderCodes = new ArrayList<>();
		
		if(originalSoDtos != null && originalSoDtos.size() > 0) {
			for (SoItemDTO originalSoDto : originalSoDtos) {
				originalOrderCodes.add(originalSoDto.getOrderCode());
			}
		}
		logger.info("原PU未完成订单条数originalSoDtos ：{}-",originalSoDtos.size());
		//
		List<CommodityProductUnitStockRunningWaterDTO> waterDTOs = new ArrayList<>();
		if(originalOrderCodes.size() > 0) {
			waterDTOs = commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodesAndStatus(originalOrderCodes,type + 1);
			
		}
		
		//-
		List<CommodityProductUnitStockRunningWaterDTO> CommodityProductUnitStockRunningWaterDTOs = new ArrayList<>();
		
		//
		long tempRealStockNum = additionsRealFrozenStockNum;
		long tempStockNum = nowRealStockNum;
		//为新加入Pu增加 原来pu的流水
		for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : waterDTOs) {
			
			CommodityProductUnitStockRunningWaterDTO waterDTO = new CommodityProductUnitStockRunningWaterDTO();
			waterDTO.setProductUnitSerialNumber(commodityProductUnitDTONew.getProductUnitSerialNumber());
			waterDTO.setCommodityProductUnitId(commodityProductUnitDTONew.getId());
			waterDTO.setCommodityProductUnitName(commodityProductUnitDTONew.getName());
			//
//			StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus();
			waterDTO.setType(StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
			waterDTO.setOrderCode(commodityProductUnitStockRunningWaterDTO.getOrderCode());
			waterDTO.setPlatformId(commodityProductUnitStockRunningWaterDTO.getPlatformId());
			//
			waterDTO.setPreoperativeRealStockNum(tempRealStockNum);
			tempRealStockNum += (commodityProductUnitStockRunningWaterDTO.getOperationBackRealStockNum() - commodityProductUnitStockRunningWaterDTO.getPreoperativeRealStockNum());
			waterDTO.setOperationBackRealStockNum(tempRealStockNum);
			
			//
			waterDTO.setOperationBackStockNum(tempStockNum);
			tempStockNum += (commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum() - commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum());
			waterDTO.setPreoperativeStockNum(tempStockNum);
			
			waterDTO.setStockChange(commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum() - commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum());
			
			waterDTO.setCreateUserid(user.getId());
			waterDTO.setCreateUserip(ip);
			waterDTO.setCreateUsermac(mac);
			waterDTO.setCreateUsername(user.getLoginName());
			waterDTO.setContactGroupStockId(contactStockId);
			CommodityProductUnitStockRunningWaterDTOs.add(waterDTO);
			
		}
		
		
		for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitDTOs) {
			
			tempRealStockNum = originalRealFrozenStockNum;
			tempStockNum = nowRealStockNum;
			//
			for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : originalWaterDTOs) {
			
				CommodityProductUnitStockRunningWaterDTO waterDTO = new CommodityProductUnitStockRunningWaterDTO();
				
				waterDTO.setProductUnitSerialNumber(commodityProductUnitDTO.getProductUnitSerialNumber());
				waterDTO.setCommodityProductUnitId(commodityProductUnitDTO.getId());
				waterDTO.setCommodityProductUnitName(commodityProductUnitDTO.getName());
				//
				waterDTO.setType(StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
				waterDTO.setOrderCode(commodityProductUnitStockRunningWaterDTO.getOrderCode());
				waterDTO.setPlatformId(commodityProductUnitStockRunningWaterDTO.getPlatformId());
				//
				waterDTO.setPreoperativeRealStockNum(tempRealStockNum);
				tempRealStockNum += (commodityProductUnitStockRunningWaterDTO.getOperationBackRealStockNum() - commodityProductUnitStockRunningWaterDTO.getPreoperativeRealStockNum());
				waterDTO.setOperationBackRealStockNum(tempRealStockNum);
				
				//
				waterDTO.setOperationBackStockNum(tempStockNum);
				tempStockNum += (commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum() - commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum());
				waterDTO.setPreoperativeStockNum(tempStockNum);
				
				waterDTO.setStockChange(commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum() - commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum());
				
				waterDTO.setCreateUserid(user.getId());
				waterDTO.setCreateUserip(ip);
				waterDTO.setCreateUsermac(mac);
				waterDTO.setCreateUsername(user.getLoginName());
				waterDTO.setContactGroupStockId(contactStockId);
				CommodityProductUnitStockRunningWaterDTOs.add(waterDTO);
				
			}
		}
		return CommodityProductUnitStockRunningWaterDTOs;
	}
    
    public void findCommodityProductUnitWarehouseStockByPuId(CommodityProductUnitDTO CommodityProductUnitDTONew,ContactGroupSkuStockDTO cgsDto,CacheUser user,String ip,String mac) {
		
    	//新加入pu的库存信息
		CommodityProductUnitWarehouseStockDTO dtoNew =	commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(CommodityProductUnitDTONew.getId());
		
		CommodityProductUnitWarehouseStockDTO originalDto =	null;
		//原冻结库存
		Long originalRealFrozenStockNum = 0L;
		//原真实库存
		Long originalRealStockNum = 0L;
		List<Long> puIds = new ArrayList<>();
		List<ContactGroupPuStockDTO> list1 = contactGroupPuStockReadService.findContactGroupPuStockAll(new ContactGroupPuStockDTO(cgsDto.getId()));
		if(list1 != null) {
			for (ContactGroupPuStockDTO contactGroupPuStockDTO : list1) {
				puIds.add(contactGroupPuStockDTO.getPuId());
			}
		}
		List<CommodityProductUnitDTO> commodityProductUnitDTOs = new ArrayList<>();
		if(puIds != null && puIds.size() > 0) {
			originalDto = commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(puIds.get(0));
			commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIds(puIds);
		}
		//新加冻结库存
		Long additionsRealFrozenStockNum = dtoNew == null ? 0 :dtoNew.getRealFrozenStockNum(); 
		
		//新加真实库存
		Long additionsRealStockNum = dtoNew == null ? 0 :dtoNew.getRealStockNum(); 
		if(originalDto != null) {
			originalRealFrozenStockNum = originalDto.getRealFrozenStockNum(); 
			originalRealStockNum = originalDto.getRealStockNum(); 
		}
	
		//
		//计算后的冻结库存
		Long nowRealFrozenStockNum = additionsRealFrozenStockNum + originalRealFrozenStockNum;
		
		//原来的真实库存 - 冻结库存
		Long originalNum = originalRealStockNum - originalRealFrozenStockNum;
		//新加入的真实库存 - 冻结库存
		Long additionsNum = additionsRealStockNum - additionsRealFrozenStockNum;
		
		//
		//计算后的真实库存
		Long nowRealStockNum = nowRealFrozenStockNum + additionsNum > originalNum ?additionsNum :originalNum;
		
		
		//----
		
		//-
		//查询所有未确认订单
		List<SoItemDTO> additionsSoDtos = soItemReadService.findSoItemByPuId(CommodityProductUnitDTONew.getId(), 0);
		List<String> additionsOrderCodes = new ArrayList<>();
		if(additionsSoDtos != null && additionsSoDtos.size() > 0) {
			for (SoItemDTO originalSoDto : additionsSoDtos) {
				additionsOrderCodes.add(originalSoDto.getOrderCode());
			}
		}
		//新增pu订单
		List<CommodityProductUnitStockRunningWaterDTO> originalWaterDTOs = new ArrayList<>();
		if(additionsOrderCodes.size() > 0) {
			originalWaterDTOs = commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(additionsOrderCodes);
		}
		
		
		//
		List<SoItemDTO> originalSoDtos = new ArrayList<>();
		if(puIds != null && puIds.size() > 0) {
			originalSoDtos = soItemReadService.findSoItemByPuIds(com.egeo.utils.StringUtils.longsToStrings(puIds), 0);
		}
		
		List<String> originalOrderCodes = new ArrayList<>();
		if(originalSoDtos != null && originalSoDtos.size() > 0) {
			for (SoItemDTO originalSoDto : originalSoDtos) {
				originalOrderCodes.add(originalSoDto.getOrderCode());
			}
		}
		//原pu订单
		List<CommodityProductUnitStockRunningWaterDTO> waterDTOs = new ArrayList<>();
		if(originalOrderCodes.size() > 0) {
			waterDTOs = commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(originalOrderCodes);
		}		
		//-
		List<CommodityProductUnitStockRunningWaterDTO> CommodityProductUnitStockRunningWaterDTOs = new ArrayList<>();
		
		//
		Long tempRealStockNum = additionsRealFrozenStockNum;
		Long tempStockNum = nowRealStockNum;
		//
		for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : waterDTOs) {
			
			CommodityProductUnitStockRunningWaterDTO waterDTO = new CommodityProductUnitStockRunningWaterDTO();
			waterDTO.setProductUnitSerialNumber(CommodityProductUnitDTONew.getProductUnitSerialNumber());
			waterDTO.setCommodityProductUnitId(CommodityProductUnitDTONew.getProductUnitId());
			waterDTO.setCommodityProductUnitName(CommodityProductUnitDTONew.getName());
			//
//			StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus();
			waterDTO.setType(StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
			waterDTO.setOrderCode(commodityProductUnitStockRunningWaterDTO.getOrderCode());
			waterDTO.setPlatformId(commodityProductUnitStockRunningWaterDTO.getPlatformId());
			//
			waterDTO.setPreoperativeRealStockNum(tempRealStockNum);
			tempRealStockNum += (commodityProductUnitStockRunningWaterDTO.getOperationBackRealStockNum() - commodityProductUnitStockRunningWaterDTO.getPreoperativeRealStockNum());
			waterDTO.setOperationBackRealStockNum(tempRealStockNum);
			
			//
			waterDTO.setOperationBackStockNum(tempStockNum);
			tempStockNum += (commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum() - commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum());
			waterDTO.setPreoperativeStockNum(tempStockNum);
			
			waterDTO.setStockChange(commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum() - commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum());
			
			waterDTO.setCreateUserid(user.getId());
			waterDTO.setCreateUserip(ip);
			waterDTO.setCreateUsermac(mac);
			waterDTO.setCreateUsername(user.getLoginName());
			waterDTO.setContactGroupStockId(cgsDto.getContactGroupStockId());
			CommodityProductUnitStockRunningWaterDTOs.add(waterDTO);
			
		}
		
		
		for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitDTOs) {
			
			tempRealStockNum = originalRealFrozenStockNum;
			tempStockNum = nowRealStockNum;
			//
			for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : originalWaterDTOs) {
			
				CommodityProductUnitStockRunningWaterDTO waterDTO = new CommodityProductUnitStockRunningWaterDTO();
				
				waterDTO.setProductUnitSerialNumber(commodityProductUnitDTO.getProductUnitSerialNumber());
				waterDTO.setCommodityProductUnitId(commodityProductUnitDTO.getProductUnitId());
				waterDTO.setCommodityProductUnitName(commodityProductUnitDTO.getName());
				//
				waterDTO.setType(StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
				waterDTO.setOrderCode(commodityProductUnitStockRunningWaterDTO.getOrderCode());
				waterDTO.setPlatformId(commodityProductUnitStockRunningWaterDTO.getPlatformId());
				//
				waterDTO.setPreoperativeRealStockNum(tempRealStockNum);
				tempRealStockNum += (commodityProductUnitStockRunningWaterDTO.getOperationBackRealStockNum() - commodityProductUnitStockRunningWaterDTO.getPreoperativeRealStockNum());
				waterDTO.setOperationBackRealStockNum(tempRealStockNum);
				
				//
				waterDTO.setOperationBackStockNum(tempStockNum);
				tempStockNum += (commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum() - commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum());
				waterDTO.setPreoperativeStockNum(tempStockNum);
				
				waterDTO.setStockChange(commodityProductUnitStockRunningWaterDTO.getOperationBackStockNum() - commodityProductUnitStockRunningWaterDTO.getPreoperativeStockNum());
				
				waterDTO.setCreateUserid(user.getId());
				waterDTO.setCreateUserip(ip);
				waterDTO.setCreateUsermac(mac);
				waterDTO.setCreateUsername(user.getLoginName());
				waterDTO.setContactGroupStockId(cgsDto.getContactGroupStockId());
				
				CommodityProductUnitStockRunningWaterDTOs.add(waterDTO);
				
			}
		}
		if(CommodityProductUnitStockRunningWaterDTOs.size() > 0) {
			commodityProductUnitStockRunningWaterWriteService.insertBatchCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTOs);
		}
		
		puIds.add(CommodityProductUnitDTONew.getId());
		commodityProductUnitWarehouseStockWriteService.updateCommodityProductUnitWarehouseStockWithTx(com.egeo.utils.StringUtils.longsToStrings(puIds),nowRealStockNum,nowRealFrozenStockNum);
	}

    public Integer findSuProfitById(Long suId) {
		return jdProductInnerIdReadService.findSuProfitById(suId);
    }
    
    public long findMaxfrontSerialNumber() {
    	return merchantProductReadService.findMaxfrontSerialNumber();
    }
}
	