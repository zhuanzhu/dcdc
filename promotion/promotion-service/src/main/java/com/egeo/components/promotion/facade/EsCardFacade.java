package com.egeo.components.promotion.facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.CardSaltClient;
import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.product.client.CardThirdpartyAttValueClient;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.SkuClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.promotion.common.DateUtils;
import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.service.read.ECardReadService;
import com.egeo.components.promotion.service.write.ECardWriteService;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.MerchantProductVirtualStockClient;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.util.security.SaltUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.UUIDBuild;
import com.egeo.utils.encrypt.QEncodeUtil;


@Component
public class EsCardFacade {

	@Autowired
	private ECardReadService eCardReadService;

	@Autowired
	private ECardWriteService eCardWriteService;

	@Autowired
	private ImportRecordsClient importRecordsReadService;

	@Autowired
	private ImportRecordsClient importRecordsWriteService;

	@Autowired
	private SkuClient skuReadService;

	@Autowired
	private CardSaltClient cardSaltWriteService;

	@Autowired
	private UserClient userReadService;

	@Autowired
	private CompanyClient companyReadService;

	@Autowired
	private CardThirdpartyAttValueClient cardThirdpartyAttValueReadService;

	@Autowired
	private MerchantProductVirtualStockClient merchantProductVirtualStockReadService;

	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	@Autowired
	private CommodityProductUnitWarehouseStockClient puWarehouseStockReadService;

	public ECardDTO findECardById(ECardDTO dto){

		return eCardReadService.findECardById(dto);
	}

	public PageResult<ECardDTO> findECardOfPage(ECardDTO dto,Pagination page){

		return eCardReadService.findECardOfPage(dto, page);

	}

	public List<ECardDTO> findECardAll(ECardDTO dto){

		return eCardReadService.findECardAll(dto);

	}

	public Long insertECardWithTx(ECardDTO dto){

		return eCardWriteService.insertECardWithTx(dto);
	}

	public int updateECardWithTx(ECardDTO dto){

		return eCardWriteService.updateECardWithTx(dto);
	}

	public int deleteECardWithTx(ECardDTO dto){

		return eCardWriteService.deleteECardWithTx(dto);

	}

	public int findImportRecordsAll(String templateType,Date generatedTime,String fileSequenceNumber){
		ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
		importRecordsDTO.setTemplateType(templateType);
		importRecordsDTO.setGeneratedTime(generatedTime);
		importRecordsDTO.setFileSequenceNumber(fileSequenceNumber);
		List<ImportRecordsDTO> list = importRecordsReadService.findImportRecordsAll(importRecordsDTO);
		return list.size();
	}

	public Long insertImportRecordsWithTx(String companyName,String templateType,Date generatedTime,String fileSequenceNumber){
		ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
		importRecordsDTO.setCompanyName(companyName);
		importRecordsDTO.setTemplateType(templateType);
		importRecordsDTO.setGeneratedTime(generatedTime);
		importRecordsDTO.setFileSequenceNumber(fileSequenceNumber);
		return importRecordsWriteService.insertImportRecordsWithTx(importRecordsDTO);
	}
	/**
	 * 根据sku编号查询sku电子卡券信息
	 * @param skuSerialNumber
	 * @return
	 */
	public SkuDTO findSkuECardBySkuSerialNumber(String skuSerialNumber){
		return skuReadService.findSkuECardBySkuSerialNumber(skuSerialNumber);
	}

	public SkuDTO findSkuById(Long skuId){
		SkuDTO dto = new SkuDTO();
		dto.setId(skuId);
		return skuReadService.findSkuById(dto);
	}
	/**
	 * 批量导入
	 * @param list
	 * @param source
	 * @param remark
	 * @param tmplType
	 * @return
	 */
	public int importECardWithTx(List<Map<String, Object>> list,
								 String source,
								 String remark,
								 Long importRecordsId,
								 Long platformId,
								 Long userId,
								 String userName,
								 String ip,
								 String mac,
								 int tmplType) {
		for (int i = 2; i < list.size(); i++) {
			try {
				//卡密
				String cardThick = list.get(i).get("CELL3").toString();
				String salt = SaltUtils.getRandomSalt();
				String encrypt = QEncodeUtil.aesEncrypt(cardThick, salt);
				list.get(i).put("CELL3", encrypt);
				CardSaltDTO cardSaltDTO = new CardSaltDTO();
				String uuid = UUIDBuild.getUUID();
				cardSaltDTO.setUuid(uuid);
				cardSaltDTO.setSaltValue(salt);
				list.get(i).put("uuid", uuid);
				// 
				cardSaltWriteService.insertCardSaltWithTx(cardSaltDTO);

			} catch (Exception e) {
				throw new BusinessException("卡密加密失败");
			}
		}
		// 组合卡密记录信息
		List<ErCardRecordDTO> erCardRecords = groupErCardRecord(list, source, remark,importRecordsId,platformId,userId,userName,ip,mac,0,tmplType);

		CardBatchDTO cardBatchDTO = new CardBatchDTO();
		// 卡密批次编号暂定毫秒级时间
		cardBatchDTO.setBatchCode(DateUtils.getDefaultDateTimeNowNUM());
		cardBatchDTO.setImportSum(Long.valueOf(erCardRecords.size()));
		cardBatchDTO.setSource(source);
		cardBatchDTO.setRemark(remark);
		cardBatchDTO.setCreateUserid(userId);
		cardBatchDTO.setCreateUsername(userName);
		cardBatchDTO.setCreateUserip(ip);
		cardBatchDTO.setCreateUsermac(mac);
		cardBatchDTO.setPlatformId(platformId);

		return eCardWriteService.importECardWithTx(cardBatchDTO,erCardRecords);
	}
	/**
	 * 组合卡密记录信息
	 * @param list
	 * @param source
	 * @param remark
	 * @param importRecordsId
	 * @param platformId
	 * @param userId
	 * @param userName
	 * @param ip
	 * @param mac
	 * @param isAllocation 是否分配：0否、1是
	 * @param tmplType 卡券导入：5	 团检券导入：8
	 * @return
	 */
	private List<ErCardRecordDTO> groupErCardRecord(List<Map<String, Object>> list, String source, String remark,
													Long importRecordsId, Long platformId, Long userId, String userName, String ip, String mac,Integer isAllocation,int tmplType) {
		List<ErCardRecordDTO> erCardRecords = new ArrayList<>();
		// 根据spuId分组卡密信息
		Map<Long, List<ErCardRecordDTO>> map = new HashMap<>();
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMdd" );
		for (int i = 2; i < list.size(); i++) {
			String skuSerialNumber = list.get(i).get("CELL1").toString();
			//uuid
			String uuid = list.get(i).get("uuid").toString();
			//sku名称
			String skuName = list.get(i).get("skuName").toString();
			//skuId
			String skuId = list.get(i).get("skuId").toString();
			//spuId
			String standardProductUnitId = list.get(i).get("standardProductUnitId").toString();
			String cardNumber = null;
			String cardThick = null;
			String start = null;
			String end = null;
			if (tmplType == 8){//团检券
				//卡号
				 cardNumber = list.get(i).get("CELL4").toString();
				//卡密
				 cardThick = list.get(i).get("CELL5").toString();
				//开始时间
				 start = list.get(i).get("CELL6").toString();
				//结束时间
				 end = list.get(i).get("CELL7").toString();
			}else {//普通卡券
				//卡号
				 cardNumber = list.get(i).get("CELL2").toString();
				//卡密
				 cardThick = list.get(i).get("CELL3").toString();
				//开始时间
				 start = list.get(i).get("CELL4").toString();
				//结束时间
				 end = list.get(i).get("CELL5").toString();
			}

			String userId_ = null;
			String loginName = null;
			if(isAllocation == 1){
				userId_ = list.get(i).get("userId").toString(); // 卡密被分配的用户id
				loginName = list.get(i).get("loginName").toString(); // 卡密被分配的用户名称
			}

			Date startTime = null;
			if(EmptyUtil.isNotEmpty(start)){
				try {
					startTime=sdf.parse(start);
				} catch (Exception e) {
					throw new BusinessException("开始日期输入值不对，请输入如20171207");
				}
			}
			//结束时间
			Date endTime = null;
			if(EmptyUtil.isNotEmpty(end)){
				try {
					endTime=sdf.parse(end);
				} catch (Exception e) {
					throw new BusinessException("结束日期输入值不对，请输入如20171207");
				}
			}
			ErCardRecordDTO erCardRecordDTO = new ErCardRecordDTO();
			erCardRecordDTO.setSkuId(Long.valueOf(skuId));
			erCardRecordDTO.setSkuName(skuName);
			erCardRecordDTO.setSkuSerialNumber(skuSerialNumber);
			erCardRecordDTO.setCardNumber(cardNumber);
			erCardRecordDTO.setCardThick(cardThick);
			erCardRecordDTO.setUuid(uuid);
			erCardRecordDTO.setStartTime(startTime);
			erCardRecordDTO.setEndTime(endTime);
			erCardRecordDTO.setImportRecordsId(importRecordsId);
			erCardRecordDTO.setPlatformId(platformId);
			erCardRecordDTO.setCreateUserid(userId);
			erCardRecordDTO.setCreateUsername(userName);
			erCardRecordDTO.setCreateUserip(ip);
			erCardRecordDTO.setCreateUsermac(mac);
			erCardRecordDTO.setUpdateUserid(userId);
			erCardRecordDTO.setUpdateUsername(userName);
			erCardRecordDTO.setUpdateUserip(ip);
			erCardRecordDTO.setUpdateUsermac(mac);
			if(EmptyUtil.isNotEmpty(userId_))
				erCardRecordDTO.setUserId(Long.valueOf(userId_));
			if(EmptyUtil.isNotEmpty(loginName))
				erCardRecordDTO.setUserLoginName(loginName);
			erCardRecordDTO.setIsValid(1);
			erCardRecordDTO.setSource(source);
			erCardRecordDTO.setRemark(remark);
			if(isAllocation == 1){
				erCardRecordDTO.setIsAllocation(isAllocation);
				erCardRecordDTO.setAllocationTime(new Date());
			}else{
				erCardRecordDTO.setIsAllocation(isAllocation);
			}

			if(EmptyUtil.isNotEmpty(standardProductUnitId)){
				erCardRecordDTO.setStandardProductUnitId(Long.valueOf(standardProductUnitId));
			}else{
				throw new BusinessException(skuSerialNumber + "异常sku编号");
			}

			// 根据spuId分组卡密信息
			if(map.containsKey(erCardRecordDTO.getStandardProductUnitId())){
				List<ErCardRecordDTO> cardRecordList = map.get(erCardRecordDTO.getStandardProductUnitId());
				cardRecordList.add(erCardRecordDTO);
				map.put(erCardRecordDTO.getStandardProductUnitId(), cardRecordList);
			}else{
				List<ErCardRecordDTO> cardRecordList = new ArrayList<>();
				cardRecordList.add(erCardRecordDTO);
				map.put(erCardRecordDTO.getStandardProductUnitId(), cardRecordList);
			}

		}
		
		for (Map.Entry<Long, List<ErCardRecordDTO>> entry : map.entrySet()) {
			// 根据分组的mapkey查询spu第三方参数其对应的卡类型
			Integer cardType = cardThirdpartyAttValueReadService.findCardTypeByStandardProductUnitId(entry.getKey());
			List<ErCardRecordDTO> erCardRecordList = entry.getValue();
			for (ErCardRecordDTO erCardRecordDTO2 : erCardRecordList) {
				erCardRecordDTO2.setType(cardType);
			}
			erCardRecords.addAll(erCardRecordList);
		}

		return erCardRecords;
	}

	/**
	 * 批量导入并且自动发货（导入用户数据、状态为已分配）
	 * @param list
	 * @param source
	 * @param remark
	 * @return
	 */
	public int importECardAutoSend(List<Map<String, Object>> list,
								   String source,
								   String remark,
								   Long importRecordsId,
								   Long platformId,
								   Long userId,
								   String userName,
								   String ip,
								   String mac,
								   int tmplType) {
		for (int i = 2; i < list.size(); i++) {
			try {
				//卡密
				String cardThick = list.get(i).get("CELL5").toString();
				String salt = SaltUtils.getRandomSalt();
				String encrypt = QEncodeUtil.aesEncrypt(cardThick, salt);
				list.get(i).put("CELL5", encrypt);
				CardSaltDTO cardSaltDTO = new CardSaltDTO();
				String uuid = UUIDBuild.getUUID();
				cardSaltDTO.setUuid(uuid);
				cardSaltDTO.setSaltValue(salt);
				list.get(i).put("uuid", uuid);

				cardSaltWriteService.insertCardSaltWithTx(cardSaltDTO);

			} catch (Exception e) {
				throw new BusinessException("卡密加密失败");
			}
		}
		// 组合卡密记录信息
		List<ErCardRecordDTO> erCardRecords = groupErCardRecord(list, source, remark,importRecordsId,platformId,userId,userName,ip,mac,1,tmplType);

		CardBatchDTO cardBatchDTO = new CardBatchDTO();
		// 卡密批次编号暂定毫秒级时间
		cardBatchDTO.setBatchCode(DateUtils.getDefaultDateTimeNowNUM());
		cardBatchDTO.setImportSum(Long.valueOf(erCardRecords.size()));
		cardBatchDTO.setSource(source);
		cardBatchDTO.setRemark(remark);
		cardBatchDTO.setCreateUserid(userId);
		cardBatchDTO.setCreateUsername(userName);
		cardBatchDTO.setCreateUserip(ip);
		cardBatchDTO.setCreateUsermac(mac);
		cardBatchDTO.setPlatformId(platformId);

		return eCardWriteService.importECardWithTx(cardBatchDTO,erCardRecords);
	}
	/**
	 * 根据头部信息草稿表id删除头部信息草稿信息
	 * @param dto
	 * @return
	 */
	public int deleteImportRecordsWithTx(ImportRecordsDTO dto){
		return importRecordsWriteService.deleteImportRecordsWithTx(dto);
	}
	/**
	 * 根据邮箱查询用户信息
	 */
	public UserDTO findByMail(String mail){
		return userReadService.findByMail(mail);
	}

	/**
	 * 根据公司名称查询公司
	 * @param name
	 * @return
	 */
	public CompanyDTO queryCompanyByName(String name){
		return companyReadService.queryCompanyByName(name);
	}

	/**
	 * 根据skuId查询sku商品库存信息
	 * @param skuId
	 * @return
     */
	public MerchantProductVirtualStockDTO queryMerchantProductVirtualStock(Long skuId) {
		return merchantProductVirtualStockReadService.merchantProductVirtualStockBySkuId(skuId);
	}
	/**
	 * 查询pu
	 * @param skuId
	 * @return
     */
	public List<CommodityProductUnitDTO> queryPuList(Long skuId) {
		CommodityProductUnitDTO pu = new CommodityProductUnitDTO();
		pu.setSkuId(skuId);
		return commodityProductUnitReadService.findCommodityProductUnitAll(pu);
	}

	/**
	 * 根据puId查询pu库存
	 * @param puIds
	 * @return
     */
	public List<CommodityProductUnitWarehouseStockDTO> queryPuWarehouseStockList(List<Long> puIds) {
		return puWarehouseStockReadService.findByPUId(com.egeo.utils.StringUtils.longsToStrings(puIds));
	}

	/**
	 * 查询卡库集合
	 * @param keys
	 * @return
     */
	public List<ECardDTO> queryECardList(Map<String, Object> keys){
		return eCardReadService.queryECardListByKey(keys);
	}

	/**
	 * 批量更新卡库
	 * @param keys
	 * @return
     */
	public int updateECardList(Map<String, Object> keys){
		return eCardWriteService.updateECardList(keys);
	}

}
	