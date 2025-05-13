package com.egeo.components.order.facade;

import java.util.*;

import javax.annotation.Resource;

import com.egeo.components.order.enums.ThirdConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.order.business.SoDeliveryManage;
import com.egeo.components.order.controller.api.SoDeliveryAction;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.dto.DeliveryImportExcelDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.components.order.dto.SoPackageDTO;
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.components.order.dto.SoPackageTempDTO;
import com.egeo.components.order.service.read.DeliveryCompanyReadService;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.components.order.service.read.SoPackageBoxReadService;
import com.egeo.components.order.service.read.SoPackageItemReadService;
import com.egeo.components.order.service.read.SoPackageReadService;
import com.egeo.components.order.service.read.SoPackageTempReadService;
import com.egeo.components.order.service.read.SoReadService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.components.order.service.write.SoPackageBoxWriteService;
import com.egeo.components.order.service.write.SoPackageTempWriteService;
import com.egeo.components.order.service.write.SoPackageWriteService;
import com.egeo.components.product.client.SkuAttNameClient;
import com.egeo.components.utils.DateUtil;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;


@Component
public class SoPackageFacade {

	@Resource
	private SoPackageReadService soPackageReadService;

	@Resource
	private SoPackageWriteService soPackageWriteService;

	@Resource
	private SoPackageBoxReadService soPackageBoxReadService;

	@Resource
	private SoPackageBoxWriteService soPackageBoxWriteService;

	@Resource
	private SoReadService soReadService;

	@Resource
	private SoPackageItemReadService soPackageItemReadService;

	@Resource
	private DeliveryCompanyReadService deliveryCompanyReadService;

	@Resource
	private SoItemReadService soItemReadService;

	@Autowired
	private SkuAttNameClient skuAttNameReadService;

	@Autowired
	private HeadImportRecordsClient headImportRecordsReadService;

	@Autowired
	private SoChildReadService  soChildReadService;

	@Autowired
	private ImportRecordsClient importRecordsWriteService;

	@Resource
	private SoPackageTempWriteService soPackageTempWriteService;

	@Resource
	private SoPackageTempReadService soPackageTempReadService;

	@Resource
	private SoChildWriteService soChildWriteService;

	@Autowired
	private DeliveryCompanyFacade deliveryCompanyFacade;
	@Autowired
	private SoDeliveryAction soDelivery;
	@Autowired
	private ImportRecordsClient importRecordsReadService;

	@Resource(name="soDelivery")
	private SoDeliveryManage soDeliveryManage;
	@Autowired
	private HeadImportRecordsClient headImportRecordsWriteService;


	public SoPackageDTO findSoPackageById(SoPackageDTO dto){

		return soPackageReadService.findSoPackageById(dto);
	}

	public PageResult<SoPackageDTO> findSoPackageOfPage(SoPackageDTO dto,Pagination page){

		return soPackageReadService.findSoPackageOfPage(dto, page);

	}

	public List<SoPackageDTO> findSoPackageAll(SoPackageDTO dto){

		return soPackageReadService.findSoPackageAll(dto);

	}

	public List<SoPackageDTO> findSoPackageUnReceive(Integer dateBefore){
		SoPackageDTO dto = new SoPackageDTO();
		Date now = new Date();
		Date beforeDay = new Date(now.getTime()-24*3600*dateBefore*1000);
		dto.setCreateTime(beforeDay);
		return soPackageReadService.findUnReceive(dto);

	}
	public void refreshSoPackageUnReceive(Integer dateBefore){
		List<SoPackageDTO> soPackageList = findSoPackageUnReceive(dateBefore);
		DeliveryCompanyDTO deliveryCompanyDTO = new DeliveryCompanyDTO();
		deliveryCompanyDTO.setPlatformId(7l);
		List<DeliveryCompanyDTO> deliveryCompanyDatas = deliveryCompanyFacade.findDeliveryCompanyAll(deliveryCompanyDTO);
		HashMap<String,DeliveryCompanyDTO> deliveryCompanyMap = new HashMap<String,DeliveryCompanyDTO>();
		for(DeliveryCompanyDTO one : deliveryCompanyDatas) {
			if(!deliveryCompanyMap.containsKey(one.getId().intValue()+"")) {
				deliveryCompanyMap.put(one.getId().intValue()+"", one);
			}
		}
		for(SoPackageDTO pkg : soPackageList) {
			if(EmptyUtil.isNotEmpty(pkg.getMerchantId()) && Objects.equals(pkg.getMerchantId(), ThirdConst.Merchant.JD) && pkg.getDeliveryCompanyId()==null){
				soDelivery.openDeliveryTrace(pkg.getDeliveryCode(), null);
				continue;
			}
			if(pkg.getDeliveryCompanyId()==null || pkg.getDeliveryCode()==null ) {
				continue;
			}
			if(!deliveryCompanyMap.containsKey(pkg.getDeliveryCompanyId().longValue()+"")) {
				continue;
			}
			soDelivery.openDeliveryTrace(pkg.getDeliveryCode(), deliveryCompanyMap.get(pkg.getDeliveryCompanyId().longValue()+"").getCoding());
		}

	}
	public void refreshSoPackageUnReceiveAll(){
		SoPackageDTO dto = new SoPackageDTO();
		List<SoPackageDTO> soPackageList = findSoPackageAll(dto);
		DeliveryCompanyDTO deliveryCompanyDTO = new DeliveryCompanyDTO();
		deliveryCompanyDTO.setPlatformId(7l);
		List<DeliveryCompanyDTO> deliveryCompanyDatas = deliveryCompanyFacade.findDeliveryCompanyAll(deliveryCompanyDTO);
		HashMap<String,DeliveryCompanyDTO> deliveryCompanyMap = new HashMap<String,DeliveryCompanyDTO>();
		for(DeliveryCompanyDTO one : deliveryCompanyDatas) {
			if(!deliveryCompanyMap.containsKey(one.getId().intValue()+"")) {
				deliveryCompanyMap.put(one.getId().intValue()+"", one);
			}
		}
		for(SoPackageDTO pkg : soPackageList) {
			if(pkg.getDeliveryCompanyId()==null || pkg.getDeliveryCode()==null ) {
				continue;
			}
			if(!deliveryCompanyMap.containsKey(pkg.getDeliveryCompanyId().longValue()+"")) {
				continue;
			}
			soDeliveryManage.openDeliveryTrace(pkg, deliveryCompanyMap.get(pkg.getDeliveryCompanyId().longValue()+"").getCoding());
		}

	}
	public Long insertSoPackageWithTx(SoPackageDTO dto){

		return soPackageWriteService.insertSoPackageWithTx(dto);
	}

	public int updateSoPackageWithTx(SoPackageDTO dto){
		//根据物流公司编码查询物流公司信息
		DeliveryCompanyDTO deliveryCompanyDTO = new DeliveryCompanyDTO();
//		deliveryCompanyDTO.setCoding(dto.getShipCompanyCode());
		List<DeliveryCompanyDTO> deliveryCompanyList = deliveryCompanyReadService.findDeliveryCompanyAll(deliveryCompanyDTO);
		if(deliveryCompanyList.size() > 0){
			dto.setDeliveryCompanyId(deliveryCompanyList.get(0).getId());
			dto.setDeliveryCompanyName(deliveryCompanyList.get(0).getName());
		}
		return soPackageWriteService.updateSoPackageWithTx(dto);
	}

	public int deleteSoPackageWithTx(SoPackageDTO dto){

		return soPackageWriteService.deleteSoPackageWithTx(dto);

	}

	public List<SoPackageItemDTO> findSoPackageItemAll(SoPackageItemDTO soPackageItemDTO) {

		return soPackageItemReadService.findSoPackageItemAll(soPackageItemDTO);
	}

	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(DeliveryCompanyDTO dto) {
		return deliveryCompanyReadService.findDeliveryCompanyAll(dto);
	}
	/**
	 * 根据物流公司id查询物流公司信息
	 * @param dto
	 * @return
	 */
	public DeliveryCompanyDTO findDeliveryCompanyById(Long id){

		return deliveryCompanyReadService.findDeliveryCompanyById(id);

	}

	/**
	 * 根据订单编号查询包裹列表
	 * @param orderCode
	 * @param platformId
	 * @return
	 */
	public List<SoPackageDTO> queryPackageListByOrderCode(String orderCode, Long platformId) {
		SoPackageDTO dto=new SoPackageDTO();
		dto.setOrderCode(orderCode);
		dto.setPlatformId(platformId);
		return soPackageReadService.findSoPackageAll(dto);
	}

	public SoDTO querySoById(Long orderId){

		return soReadService.querySoById(orderId);
	}

	public List<SoItemDTO> findSoItemAll(SoItemDTO soItemDTO) {
		return soItemReadService.findAll(soItemDTO);
	}

	public List<SoItemDTO> soItemByPackageId(Long id) {

		return soItemReadService.soItemByPackageId(id);
	}

	public List<SoPackageDTO> packageByOrderCode(String orderCode) {

		return soPackageReadService.packageByOrderCode(orderCode);
	}

	public String attNameValueBySkuId(Long skuId) {
		return null;
		//return skuAttNameReadService.attNameValueBySkuId(skuId);
	}

	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO headImportRecordsDTO) {
		return headImportRecordsReadService.findHeadImportRecordsAll(headImportRecordsDTO);
	}

	/**
	 * 查询子订单，根据条件
	 * @param soChildDTO
	 * @return
	 */
	public List<SoChildDTO> findAllSochildByCondition(SoChildDTO soChildDTO) {

		return soChildReadService.findSoChildAll(soChildDTO);
	}

	/**
	 * 插入临时记录表的记录和导入的临时记录
	 * @param importRecordsDTO
	 * @param dtoList
	 * @return
	 */
	public Map<String, Object> insertimportTempRecordsViewList(ImportRecordsDTO importRecordsDTO,
			List<SoPackageTempDTO> dtoList) {
		Map<String, Object> data =new HashMap<>();

		//将导入信息，插入到记录表
		Long insertImportRecordsWithTx = importRecordsWriteService.insertImportRecordsWithTx(importRecordsDTO);

		data.put("importSoPackageInfo", insertImportRecordsWithTx);

		//将导入的发货记录存储到临时表
		String soPackageTempListIdStr = soPackageTempWriteService.insertSoPackageTempListWithTx(dtoList);

		data.put("soPackageInfo", soPackageTempListIdStr);
		return data;
	}

	@Deprecated
	public Long assureImportDeliveryOrSign(Long platformId, Long ImportSoPackageInfo, String soPackageInfo,Integer deliveryStatus) {

		String soPackageIdStr = soPackageInfo.substring(1);

		String[] soPackageIdArr = soPackageIdStr.split(",");

		List<SoPackageTempDTO> soPackageTempDTOList = new ArrayList<>();

		//设置一个集合，封装子订单的id
		List<Long> soChildIdList = new ArrayList<>();
		for (int i = 0; i < soPackageIdArr.length; i++) {
			//根据id查询临时sopackage临时表的数据
			SoPackageTempDTO soPackageTempDTO = new SoPackageTempDTO();
			soPackageTempDTO.setId(Long.valueOf(soPackageIdArr[i]));
			SoPackageTempDTO dto = soPackageTempReadService.findSoPackageTempById(soPackageTempDTO);

			soPackageTempDTOList.add(dto);
			soChildIdList.add(dto.getSoChildId());
		}
		//1.跟新子订单的状态/将数据同步到soPackage这张表里面

		soChildWriteService.updateSochildStatusAndSynchrosoPackageTemp(soChildIdList,soPackageTempDTOList,deliveryStatus);

		//2.将临时记录表的数据同步到正式表
		ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
		importRecordsDTO.setId(ImportSoPackageInfo);
		ImportRecordsDTO findImportRecord = importRecordsReadService.findImportRecordsById(importRecordsDTO);

		if(EmptyUtil.isNotEmpty(findImportRecord)){
			HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();

			headImportRecordsDTO.setId(findImportRecord.getId());
			headImportRecordsDTO.setTemplateType(findImportRecord.getTemplateType());
			headImportRecordsDTO.setCompanyName(findImportRecord.getCompanyName());
			headImportRecordsDTO.setFileSequenceNumber(findImportRecord.getFileSequenceNumber());
			headImportRecordsDTO.setGeneratedTime(findImportRecord.getGeneratedTime());

			headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
		}
		return (long)soPackageTempDTOList.size();
	}

	public PageResult<SoPackageDTO> findSoPackageAndBoxOfPage(SoPackageDTO dto, Pagination page) {
		return soPackageReadService.findSoPackageAndBoxOfPage(dto, page);
	}

	/**
	 * 根据子订单id查询包裹
	 * @param scId
	 * @return
	 */
	public List<SoPackageDTO> queryPackageBySoChildId(Long scId) {
		return soPackageReadService.queryPackageBySoChildId(scId);
	}
	public SoPackageDTO queryPackageBySoChildIdDistince(Long scId) {
		List<SoPackageDTO> pks = queryPackageBySoChildId(scId);
		return (pks==null ||pks.size()==0)?null:soPackageReadService.queryPackageBySoChildId(scId).get(0);
	}

	/**
	 * 根据子订单id查询箱子
	 * @param soChildId
	 * @return
	 */
	public List<SoPackageBoxDTO> queryBoxListBySoChildId(Long soChildId) {
		SoPackageBoxDTO cond=new SoPackageBoxDTO();
		cond.setSoChildId(soChildId);
		return soPackageBoxReadService.findSoPackageBoxAll(cond);
	}

	/**
	 * 新增箱子
	 * @param insertBox
	 */
	public Long insertSoPackageBox(SoPackageBoxDTO dto) {
		return soPackageBoxWriteService.insertSoPackageBoxWithTx(dto);

	}

	/**
	 * 根据箱号查询箱子
	 * @param boxCode
	 * @param soChildId
	 * @return
	 */
	public SoPackageBoxDTO queryBoxByBoxCodeAndChildId(Long boxCode, Long soChildId) {
		return soPackageBoxReadService.queryBoxByBoxCodeAndChildId(boxCode,soChildId);
	}

	/**
	 * 事务完成订单发货导入
	 * @param voList
	 * @param platformId
	 * @param operatorId
	 */
	public void completeDeliveryImport(List<DeliveryImportExcelDTO> dtoList, Long operatorId, Long platformId) {
		//为子订单插入运单,有箱号时插入箱子,更改子订单的状态为已发货,插入操作记录
		soPackageWriteService.completeDeliveryImport(dtoList,operatorId,platformId);
		//插入导入文件头至head_import_records

	}

	/**
	 * 通过发货id数组查询发货信息和子订单信息
	 * @param soPackageIdArrStr
	 * @return
	 */
	public Map<String,Object> getSoChildListByPackageIds(List<Long> soChildIdList) {

		Map<String,Object> map = new HashMap<String,Object>();

		List<SoPackageDTO> soPackageDTOList = new ArrayList<>();
		List<SoChildDTO> soChildDTOList = new ArrayList<>();

		for (Long soChildId : soChildIdList) {
			SoChildDTO soChildDTO = soChildReadService.findSoChildById(soChildId);
			soChildDTOList.add(soChildDTO);

			SoPackageDTO soPackageDTO = new SoPackageDTO();
			soPackageDTO.setSoChildId(soChildId);
			List<SoPackageDTO> dto = soPackageReadService.findSoPackageAll(soPackageDTO);
			soPackageDTOList.addAll(dto);
		}

		map.put("soPackageDTOList", soPackageDTOList);
		map.put("soChildDTOList", soChildDTOList);

		return map;
	}

	public List<SoPackageBoxDTO> findSoPackageBox(SoPackageBoxDTO cond) {
		return soPackageBoxReadService.findSoPackageBoxAll(cond);
	}

	/**
	 * 事务完成订单发货导入
	 * @param dtoList
	 * @param platformId
	 * @param operatorId
	 */
	public void completeChildDeliveryImport(List<DeliveryImportExcelDTO> dtoList, Long operatorId, Long platformId) {
		//为子订单插入运单,有箱号时插入箱子,更改子订单的状态为已发货,插入操作记录
		soPackageWriteService.completeChildDeliveryImport(dtoList,operatorId,platformId);
		//插入导入文件头至head_import_records

	}
}
