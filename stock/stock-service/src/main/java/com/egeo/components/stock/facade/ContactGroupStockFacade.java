package com.egeo.components.stock.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.MerchantClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.client.StandardUnitClient;
import com.egeo.components.product.client.StandardUnitClientClient;
import com.egeo.components.product.client.StandardUnitCompanyClient;
import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.dto.StandardUnitPageDTO;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.dto.ContactGroupSkuStockDTO;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.components.stock.service.read.CommodityProductUnitStockRunningWaterReadService;
import com.egeo.components.stock.service.read.CommodityProductUnitWarehouseStockReadService;
import com.egeo.components.stock.service.read.ContactGroupPuStockReadService;
import com.egeo.components.stock.service.read.ContactGroupSkuStockReadService;
import com.egeo.components.stock.service.read.ContactGroupStockReadService;
import com.egeo.components.stock.service.write.CommodityProductUnitStockRunningWaterWriteService;
import com.egeo.components.stock.service.write.CommodityProductUnitWarehouseStockWriteService;
import com.egeo.components.stock.service.write.ContactGroupPuStockWriteService;
import com.egeo.components.stock.service.write.ContactGroupSkuStockWriteService;
import com.egeo.components.stock.service.write.ContactGroupStockWriteService;
import com.egeo.components.user.client.ClientClient;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserRoleClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.entity.CacheUser;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ContactGroupStockFacade {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactGroupStockFacade.class);
	
	@Resource
	private ContactGroupStockReadService contactGroupStockReadService;
	
	@Resource
	private ContactGroupStockWriteService contactGroupStockWriteService;

	@Autowired
	private StandardProductUnitClient standardProductUnitReadService;

	@Autowired
	private MerchantClient merchantReadService;

	@Autowired
	private StandardUnitClient standardUnitReadService;

	@Autowired
	private StoreClient storeReadService;

	@Autowired
	private StandardUnitClientClient standardUnitClientReadService;

	@Autowired
	private ClientClient clientReadService;

	@Autowired
	private StandardUnitCompanyClient standardUnitCompanyReadService;

	@Autowired
	private CompanyClient companyReadService;

	@Autowired
	private SkuClient skuReadService;

	@Resource
	private ContactGroupSkuStockWriteService ContactGroupSkuStockWriteService;

	@Resource
	private ContactGroupSkuStockReadService contactGroupSkuStockReadService;

	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;

	@Resource
	private ContactGroupPuStockWriteService contactGroupPuStockWriteService;
	
	@Resource
	private CommodityProductUnitWarehouseStockReadService commodityProductUnitWarehouseStockReadService;
	@Resource
	private CommodityProductUnitWarehouseStockWriteService commodityProductUnitWarehouseStockWriteService;
	@Resource
	private CommodityProductUnitStockRunningWaterReadService commodityProductUnitStockRunningWaterReadService;
	@Resource
	private CommodityProductUnitStockRunningWaterWriteService commodityProductUnitStockRunningWaterWriteService;
	
	@Resource
	private ContactGroupPuStockReadService contactGroupPuStockReadService;
	
	@Autowired
	private SoClient soReadService;
	
	@Autowired
	private SoItemClient soItemReadService;
	@Resource
	private UserClient userReadService;
	@Autowired
	private UserRoleClient userRoleReadService;
	@Resource
	private ContactGroupSkuStockWriteService contactGroupSkuStockWriteService;


	public ContactGroupStockDTO findContactGroupStockById(ContactGroupStockDTO dto){
		
		return contactGroupStockReadService.findContactGroupStockById(dto);
	}

	public PageResult<ContactGroupStockDTO> findContactGroupStockOfPage(ContactGroupStockDTO dto,Pagination page){
		
		return contactGroupStockReadService.findContactGroupStockOfPage(dto, page);
		
	}

	public List<ContactGroupStockDTO> findContactGroupStockAll(ContactGroupStockDTO dto){
		
		return contactGroupStockReadService.findContactGroupStockAll(dto);
		
	}

	public Long insertContactGroupStockWithTx(ContactGroupStockDTO dto){
		
		Long i = contactGroupStockWriteService.insertContactGroupStockWithTx(dto);
		
		SkuDTO skuDTO = new SkuDTO();
		skuDTO.setStandardProductUnitId(dto.getSpuId());
		List<SkuDTO> skuAll = skuReadService.findSkuAll(skuDTO);
		List<ContactGroupSkuStockDTO> skuList = new ArrayList<>();
		
		for ( SkuDTO sku :  skuAll ) {
			ContactGroupSkuStockDTO contactGroupSkuStockDTO = new ContactGroupSkuStockDTO();
			contactGroupSkuStockDTO.setSkuId(sku.getId());
			contactGroupSkuStockDTO.setContactGroupStockId(i);
			skuList.add(contactGroupSkuStockDTO);
		}
		
		contactGroupSkuStockWriteService.insertContactGroupSkuStockListWithTx(skuList);
		return i;
	}

	public int updateContactGroupStockWithTx(ContactGroupStockDTO dto){
		
		return contactGroupStockWriteService.updateContactGroupStockWithTx(dto);
	}

	public int deleteContactGroupStockWithTx(ContactGroupStockDTO dto){
		
		return contactGroupStockWriteService.deleteContactGroupStockWithTx(dto);
		
	}

	public List<StandardProductUnitDTO> findStandardProductUnitAll(StandardProductUnitDTO dto) {
		return  standardProductUnitReadService.findStandardProductUnitAll(dto);
	}

	public StandardProductUnitDTO findStandardProductUnitById(StandardProductUnitDTO dto) {
		return  standardProductUnitReadService.findStandardProductUnitById(dto);
	}

	public List<MerchantDTO> findMerchantAll(MerchantDTO dto){
		return merchantReadService.findMerchantAll(dto);
	}

	public MerchantDTO findMerchantById(MerchantDTO dto) {
		return merchantReadService.findMerchantById(dto);
	}

	public PageResult<ContactGroupStockDTO> findContactGroupStockMapOfPage(ContactGroupStockDTO dto, Pagination page, List<Long> spuIds, List<Long> merchantIds) {
		return contactGroupStockReadService.findContactGroupStockMapOfPage(dto, page, spuIds, merchantIds);
	}

	public PageResult<StandardUnitDTO> findStandardUnitOfPage(StandardUnitDTO dto, Pagination page) {
		return  standardUnitReadService.findStandardUnitOfPage(new StandardUnitPageDTO(dto, page));
	}

	public StoreDTO findStoreById(StoreDTO dto ) {
		return storeReadService.findStoreById(dto);
	}

	public List<StandardUnitClientDTO> findStandardUnitClientAll(StandardUnitClientDTO dto) {
		return standardUnitClientReadService.findStandardUnitClientAll(dto);
	}

	public List<String> clientNameByClientIds(List<Long> clientIdList) {
		return clientReadService.clientNameByClientIds(com.egeo.utils.StringUtils.longsToStrings(clientIdList));
	}

	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(StandardUnitCompanyDTO dto){
		return standardUnitCompanyReadService.findStandardUnitCompanyAll(dto);
	}

	public List<CompanyDTO> findCompanyByCompanyIds(List<Long> companyIds) {
		return companyReadService.findCompanyByCompanyIds(com.egeo.utils.StringUtils.longsToStrings(companyIds));
	}

	public ContactGroupStockDTO findContactGroupStockByMerchantIdAndSuId(ContactGroupStockDTO dto, Long suId) {
		return contactGroupStockReadService.findContactGroupStockByMerchantIdAndSuId(dto, suId);
	}

	public List<SkuDTO> findSkuAll(SkuDTO dto) {
		return skuReadService.findSkuAll(dto);
	}

	public Long insertContactGroupSkuStockWithTx(ContactGroupSkuStockDTO dto) {
		return ContactGroupSkuStockWriteService.insertContactGroupSkuStockWithTx(dto);
	}

	public List<ContactGroupSkuStockDTO> findContactGroupSkuStockAll(ContactGroupSkuStockDTO dto) {
		return contactGroupSkuStockReadService.findContactGroupSkuStockAll(dto);
	}

	public List<CommodityProductUnitDTO> findCommodityProductUnitAll(CommodityProductUnitDTO dto) {
		return commodityProductUnitReadService.findCommodityProductUnitAll(dto);
	}

	public Long insertContactGroupPuStockWithTx(ContactGroupPuStockDTO dto) {
		return contactGroupPuStockWriteService.insertContactGroupPuStockWithTx(dto);
	}

	public int deleteContactGroupPuStockWithTx(ContactGroupPuStockDTO dto){
		return contactGroupPuStockWriteService.deleteContactGroupPuStockWithTx(dto);
	}

	public void deleteContactGroupPuStockByParaWithTx(ContactGroupPuStockDTO dto) {
		contactGroupPuStockWriteService.deleteContactGroupPuStockByParaWithTx(dto);
	}

	public void deleteContactGroupSkuStockByParaWithTx(ContactGroupSkuStockDTO dto) {
		ContactGroupSkuStockWriteService.deleteContactGroupSkuStockByParaWithTx(dto);
	}

	public void insertContactGroupSkuStockListWithTx(List<ContactGroupSkuStockDTO> list) {
		ContactGroupSkuStockWriteService.insertContactGroupSkuStockListWithTx(list);
	}

	public void insertContactGroupPuListStockWithTx(List<ContactGroupPuStockDTO> list) {
		contactGroupPuStockWriteService.insertContactGroupPuListStockWithTx(list);
	}


	
	/**
	 * 
	 * @param cgsDto
	 */
	public void confirmContactWithTx(ContactGroupPuStockDTO contactGroupPuStockDTO,
			ContactGroupSkuStockDTO cgsDto,CacheUser user,String ip,String mac) {
		
		List<Long> puIds = new ArrayList<>();
		List<ContactGroupPuStockDTO> list1 = contactGroupPuStockReadService.findContactGroupPuStockAll(new ContactGroupPuStockDTO(cgsDto.getId()));
		if(list1 != null) {
			for (ContactGroupPuStockDTO dto : list1) {
				puIds.add(dto.getPuId());
			}
		}
		logger.info("新加入pu:{},原pu组成员：{} -----",contactGroupPuStockDTO.getPuId(),puIds);
		
		if(puIds.size() > 0) {
			
			findCommodityProductUnitWarehouseStockByPuId(contactGroupPuStockDTO.getPuId(),cgsDto,user,ip,mac);
		}
		
		contactGroupPuStockWriteService.insertContactGroupPuStockWithTx(contactGroupPuStockDTO);
		
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
		
		List<Long> commpuIds = new ArrayList<>();
		List<ContactGroupPuStockDTO> list1 = contactGroupPuStockReadService.findContactGroupPuStockAll(new ContactGroupPuStockDTO(cgsDto.getId()));
		if(list1 != null) {
			for (ContactGroupPuStockDTO dto : list1) {
				commpuIds.add(dto.getPuId());
			}
		}
		if(commpuIds.size() > 0) {
			List<CommodityProductUnitDTO> commodityProductUnitDTOs = new ArrayList<>();
			
			originalDto = commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(commpuIds.get(0));
			commodityProductUnitDTOs = commodityProductUnitReadService.findByPUIds(com.egeo.utils.StringUtils.longsToStrings(commpuIds));
			
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
			logger.info("（开始）新加pu:{},原始pu:{},nowRealFrozenStockNum:{},nowRealStockNum:{}",puId,commpuIds,nowRealFrozenStockNum,nowRealStockNum);
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
			List<CommodityProductUnitStockRunningWaterDTO> commodityProductUnitStockRunningWaterDTOs0 = confirmWater(puId, commpuIds, additionsRealFrozenStockNum, nowRealStockNum,
						originalRealFrozenStockNum, CommodityProductUnitDTONew, commodityProductUnitDTOs, user, ip, mac, cgsDto.getContactGroupStockId(), 0);
			logger.info("开始查询已确认 未发货订单：---------------------------");
			List<CommodityProductUnitStockRunningWaterDTO> commodityProductUnitStockRunningWaterDTOs1 = confirmWater(puId, commpuIds, additionsRealFrozenStockNum, nowRealStockNum,
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
		}
		
		
		commpuIds.add(puId);
		logger.info("(结束)新加pu:{},原始pu:{},nowRealFrozenStockNum:{},nowRealStockNum:{}",puId,commpuIds,nowRealFrozenStockNum,nowRealStockNum);
		commodityProductUnitWarehouseStockWriteService.updateCommodityProductUnitWarehouseStockWithTx(commpuIds,nowRealStockNum,nowRealFrozenStockNum);
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
		logger.info("新加入PU未完成订单条数additionsOrderCodes ：{} ,additionsOrderCodes:{}",additionsOrderCodes.size(),additionsOrderCodes);
		if(additionsOrderCodes.size() > 0) {
			originalWaterDTOs = commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(additionsOrderCodes,type + 1);
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
		logger.info("原PU未完成订单条数originalSoDtos ：{}-originalOrderCodes:{}",originalSoDtos.size(),originalOrderCodes);
		//
		List<CommodityProductUnitStockRunningWaterDTO> waterDTOs = new ArrayList<>();
		if(originalOrderCodes.size() > 0) {
			waterDTOs = commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(originalOrderCodes,type + 1);
			
		}
		
		//-
		List<CommodityProductUnitStockRunningWaterDTO> CommodityProductUnitStockRunningWaterDTOs = new ArrayList<>();
		
		//
		long tempRealStockNum = additionsRealFrozenStockNum;
		long tempStockNum = nowRealStockNum;
		//为新加入Pu增加 原来pu的流水
		for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : waterDTOs) {
			logger.info("commodityProductUnitStockRunningWaterDTO:{},{}",commodityProductUnitStockRunningWaterDTO.getId(),commodityProductUnitStockRunningWaterDTO.getOrderCode());
			CommodityProductUnitStockRunningWaterDTO waterDTO = new CommodityProductUnitStockRunningWaterDTO();
			waterDTO.setProductUnitSerialNumber(commodityProductUnitDTONew.getProductUnitSerialNumber());
			waterDTO.setCommodityProductUnitId(commodityProductUnitDTONew.getId());
			waterDTO.setCommodityProductUnitName(commodityProductUnitDTONew.getName());
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
		
		
		for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitDTOs) {
			if(commodityProductUnitDTO.getId().equals(puId)) {
				continue;
			}
			tempRealStockNum = originalRealFrozenStockNum;
			tempStockNum = nowRealStockNum;
			//
			for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : originalWaterDTOs) {
				logger.info("commodityProductUnitStockRunningWaterDTO:{},{}",commodityProductUnitStockRunningWaterDTO.getId(),commodityProductUnitStockRunningWaterDTO.getOrderCode());
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
	
	public boolean checkConfirm(Long puId,ContactGroupSkuStockDTO cgsDto,Integer type){
		
		List<Long> puIds = new ArrayList<>();
		List<ContactGroupPuStockDTO> list1 = contactGroupPuStockReadService.findContactGroupPuStockAll(new ContactGroupPuStockDTO(cgsDto.getId()));
		if(list1 != null) {
			for (ContactGroupPuStockDTO dto : list1) {
				puIds.add(dto.getPuId());
			}
		}
		logger.info("新加入pu:{},原pu组成员：{} -----",puId,puIds);
		
		if(puIds.size() < 1) {
			return true;
		}
		
		boolean checkConfirm = true;
		
		//未确认订单
		List<SoItemDTO> additionsSoDtos = new ArrayList<>();
		List<SoItemDTO> additionsSoDtos0 = soItemReadService.findSoItemByPuId(puId, type);
		
		if(additionsSoDtos0 != null) {
			logger.info("additionsSoDtos0:{}",additionsSoDtos0.size());
			additionsSoDtos.addAll(additionsSoDtos0);
			
		}
		
		List<String> additionsOrderCodes = new ArrayList<>();
		for (SoItemDTO originalSoDto : additionsSoDtos) {
			additionsOrderCodes.add(originalSoDto.getOrderCode());
		}
		
		List<CommodityProductUnitStockRunningWaterDTO> originalWaterDTOs = new ArrayList<>();
		logger.info("新加入PU未完成订单条数additionsOrderCodes ：{} ,additionsOrderCodes:{}",additionsOrderCodes.size(),additionsOrderCodes);
		if(additionsOrderCodes.size() > 0) {
			originalWaterDTOs = commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(additionsOrderCodes,type + 1);
		}
		
		for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : originalWaterDTOs) {
			for (Long id : puIds) {
				if(commodityProductUnitStockRunningWaterDTO.getCommodityProductUnitId().equals(id)) {
					logger.info("新加入pu订单和原关联组订单冲突， 订单编号：{}",commodityProductUnitStockRunningWaterDTO.getOrderCode());
					throw new BusinessException("该su无法加入关联组！");
				}
			}
			
		}
		List<SoItemDTO> originalSoDtos = new ArrayList<>();
		//未确认订单
		List<SoItemDTO> originalSoDtos0 = soItemReadService.findSoItemByPuIds(com.egeo.utils.StringUtils.longsToStrings(puIds), type);
	
		if(originalSoDtos0 != null) {
			logger.info("originalSoDtos0:{}",additionsSoDtos0.size());
			originalSoDtos.addAll(originalSoDtos0);
		}
		
		List<String> originalOrderCodes = new ArrayList<>();
		
		if(originalSoDtos != null && originalSoDtos.size() > 0) {
			for (SoItemDTO originalSoDto : originalSoDtos) {
				originalOrderCodes.add(originalSoDto.getOrderCode());
			}
		}
		
		List<CommodityProductUnitStockRunningWaterDTO> waterDTOs = new ArrayList<>();
		if(originalOrderCodes.size() > 0) {
			waterDTOs = commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterByOrderCodes(originalOrderCodes,type + 1);
		}
		
		for (CommodityProductUnitStockRunningWaterDTO commodityProductUnitStockRunningWaterDTO : waterDTOs) {
			if(commodityProductUnitStockRunningWaterDTO.getCommodityProductUnitId().equals(puId)) {
				logger.info("新加入pu订单和原关联组订单冲突， 订单编号：{}",commodityProductUnitStockRunningWaterDTO.getOrderCode());
				throw new BusinessException("该su无法加入关联组！");
			}
		}
		
		logger.info("checkConfirm end 新加入pu:{}",puId);
		
		return checkConfirm;
	}
	
	public boolean checkUserByUserId(CacheUser user) {
		
		UserRoleDTO userRole = new UserRoleDTO();
		userRole.setUserId(user.getId());
		List<UserRoleDTO> roleDTOs = userRoleReadService.findUserRoleAll(userRole);
		for (UserRoleDTO userRoleDTO : roleDTOs) {
			//管理员 或者 技术部
			if(userRoleDTO.getRoleId() == 1 || userRoleDTO.getRoleId() == 10021) {
				return true;
			}
		}
		return false;
	}

	public void clearCountWithTx(ContactGroupStockDTO dto) {
		contactGroupStockWriteService.clearCountWithTx(dto);
	}

	public void reduceOneCountWithTx(ContactGroupStockDTO dto) {
		contactGroupStockWriteService.reduceOneCountWithTx(dto);
	}

	public void increaseOneCountWithTx(ContactGroupStockDTO dto) {
		contactGroupStockWriteService.increaseOneCountWithTx(dto);
	}

	/**
	 * 
	 * @param commodityProductUnitId
	 */
	public void findContactGroupStockByPuId(Long commodityProductUnitId) {
		
	}

	public List<ContactGroupStockDTO> findAllByName(ContactGroupStockDTO contactDto) {
		
		return contactGroupStockReadService.findAllByName(contactDto);
	}
}
	