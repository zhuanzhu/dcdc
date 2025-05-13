package com.egeo.components.promotion.business.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.xmlbeans.impl.common.ConcurrentReaderHashMap;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessConstant;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.promotion.business.ECardManage;
import com.egeo.components.promotion.common.Constant;
import com.egeo.components.promotion.common.DateUtils;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.dto.ECardStatusDTO;
import com.egeo.components.promotion.dto.ErCardRecordDTO;
import com.egeo.components.promotion.facade.AccountFacade;
import com.egeo.components.promotion.facade.ErCardRecordFacade;
import com.egeo.components.promotion.facade.EsCardFacade;
import com.egeo.components.promotion.facade.ProductFacade;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.KANGUtils;
import com.egeo.utils.SequenceUtil;
import com.egeo.utils.encrypt.QEncodeUtil;
import com.egeo.utils.excel2.PropblemReportRowVO;
import com.egeo.utils.str.StringUtils;
import com.egeo.web.JsonResult;

@Service("eCard")
public class ECardManageImpl implements ECardManage{


	@Resource(name="esCardFacade")
	private EsCardFacade esCardFacade;

	@Resource(name="erCardRecordFacade")
	private ErCardRecordFacade erCardRecordFacade;

	@Resource(name = "productFacade")
	private ProductFacade productFacade;

	@Resource(name = "accountFacade")
	private AccountFacade accountFacade;

	@Override
	public ECardDTO findECardById(ECardDTO dto) {
		return esCardFacade.findECardById(dto);
	}

	@Override
	public PageResult<Map<String, Object>> findECardOfPage(ECardDTO dto, Pagination page) {
		PageResult<ECardDTO> pageResult = esCardFacade.findECardOfPage(dto, page);
		List<ECardDTO> eCardList = pageResult.getList();
		List<Map<String, Object>> list = new ArrayList<>();
		for (ECardDTO eCardDTO : eCardList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", StringUtils.protectProductSerialNumber(eCardDTO.getId()));
			map.put("batch", eCardDTO.getBatch());
			map.put("skuId", eCardDTO.getSkuId());
			map.put("skuName", eCardDTO.getSkuName());
			map.put("skuSerialNumber", eCardDTO.getSkuSerialNumber());
			map.put("cardNumber", eCardDTO.getCardNumber());
			map.put("cardThick", eCardDTO.getCardThick());
			map.put("startTime", eCardDTO.getStartTime());
			map.put("endTime", eCardDTO.getEndTime());
			map.put("source", eCardDTO.getSource());
			map.put("remark", eCardDTO.getRemark());
			map.put("createUserid", eCardDTO.getCreateUserid());
			map.put("createUsername", eCardDTO.getCreateUsername());
			map.put("isValid", eCardDTO.getIsValid());
			map.put("orderCode", eCardDTO.getOrderCode());
			map.put("userId", eCardDTO.getUserId());
			map.put("userLoginName", eCardDTO.getUserLoginName());
			map.put("isAllocation", eCardDTO.getIsAllocation());
			map.put("allocationTime", eCardDTO.getAllocationTime());
			map.put("createTime", eCardDTO.getCreateTime());
			map.put("shortUrl", eCardDTO.getShortUrl());
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<ECardDTO> findECardAll(ECardDTO dto) {
		return esCardFacade.findECardAll(dto);
	}

	@Override
	public Long insertECardWithTx(ECardDTO dto) {
		return esCardFacade.insertECardWithTx(dto);
	}

	@Override
	public int updateECardWithTx(ECardDTO dto) {
		return esCardFacade.updateECardWithTx(dto);
	}

	@Override
	public int deleteECardWithTx(ECardDTO dto) {
		return esCardFacade.deleteECardWithTx(dto);
	}

	/**
	 * 电子卡券导入
	 * @param list
	 * @param source
	 * @param remark
	 * @param platformId
	 * @param userId
	 * @param userName
	 * @param ip
	 * @param mac
	 * @return
	 */
	@Override
	public Map<String, Object> importECardWithTx(List<Map<String, Object>> list, String source, String remark,Long platformId,Long userId,String userName,String ip,String mac, int tmplType) {
		Map<String, Object> map = new HashMap<>();
		List<PropblemReportRowVO> propblemReportRowList = new ArrayList<>();
		//头文件
		Map<String,Object> row0=list.get(0);
		//空值校验
		String companyName=row0.get("CELL2").toString();
		String tmplName=row0.get("CELL4").toString();
		String createTime=row0.get("CELL6").toString();
		String sn=row0.get("CELL8").toString();
		// 数量校验
		if (list.size() > 1002){
			throw new BusinessException("单次导入数据不能超过1000条");
		}
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMdd" );
		Date generatedTime = null;
		if(EmptyUtil.isNotEmpty(createTime)){
			try {
				generatedTime = sdf.parse(createTime);
			} catch (Exception e) {
				throw new BusinessException("头文件创建输入值不对，请输入如20171207");
			}
		}
		int rows = esCardFacade.findImportRecordsAll(tmplName,generatedTime,sn);
		if(rows != 0){
			throw new BusinessException("重复导入");
		}
		Set<String> s = new HashSet<String>();

		for (int i = 2; i < list.size(); i++) {
			StringBuffer value = new StringBuffer();
			//数据验证
			String skuSerialNumber = list.get(i).get("CELL1").toString();
			if(EmptyUtil.isEmpty(skuSerialNumber)){

				value.append("sku编号不能为空 ; ");
			}
			//查询所有卡密信息
			ECardDTO cardDTO = new ECardDTO();
			cardDTO.setSkuSerialNumber(skuSerialNumber);
			List<ECardDTO> eCardList = esCardFacade.findECardAll(cardDTO);
			//将以存在的卡密卡号存入set集合
			for (ECardDTO eCardDTO : eCardList) {
				s.add(eCardDTO.getCardNumber());
			}
			//卡号
			String cardNumber = list.get(i).get("CELL2").toString();
			if(EmptyUtil.isEmpty(cardNumber)){
				value.append("卡号不能为空; ");
			}
			boolean b = s.add(cardNumber);
			if(!b){
				value.append("卡号重复 ; ");
			}
			//卡密
			String cardThick = list.get(i).get("CELL3").toString();
			if(EmptyUtil.isEmpty(cardThick)){
				value.append("卡密不能为空; ");
			}
			//开始时间
			String start = list.get(i).get("CELL4").toString();
			if(EmptyUtil.isNotEmpty(start)){
				if(!StringUtils.isNotFigure(start)){
					value.append("开始日期输入值不对，请输入如20171207; ");
				}

			}else{
				value.append("开始日期输入值不能为空; ");
			}
			//结束时间
			String end = list.get(i).get("CELL5").toString();
			if(EmptyUtil.isNotEmpty(end)){
				if(!StringUtils.isNotFigure(end)){
					value.append("结束日期输入值不对，请输入如20171207; ");
				}

			}else{
				value.append("结束日期输入值不能为空; ");
			}

			//根据sku编号查询skuid信息
			SkuDTO skuDTO = esCardFacade.findSkuECardBySkuSerialNumber(skuSerialNumber);
			if(EmptyUtil.isEmpty(skuDTO)){
				value.append(" 请确定该sku为卡券sku; ");
			}else{
				list.get(i).put("skuName", skuDTO.getSkuName());
				list.get(i).put("skuId", skuDTO.getId());
				list.get(i).put("standardProductUnitId", skuDTO.getStandardProductUnitId());
			}
			if(value.length() > 0){
				PropblemReportRowVO propblemReportRowVO = new PropblemReportRowVO<>(value.toString(), i,null);
				propblemReportRowList.add(propblemReportRowVO);
			}


		}
		if(EmptyUtil.isEmpty(propblemReportRowList)){
			//写入记录表
			Long importRecordsId = esCardFacade.insertImportRecordsWithTx(companyName, tmplName,generatedTime,sn);
			map.put("importRecordsId", importRecordsId);
			//批量导入
			esCardFacade.importECardWithTx(list, source, remark,importRecordsId,platformId,userId,userName,ip,mac,tmplType);
			//确认导入
			ErCardRecordDTO erCardRecordDTO = new ErCardRecordDTO();
			erCardRecordDTO.setImportRecordsId(importRecordsId);
			erCardRecordFacade.confirmTheImport(erCardRecordDTO,1);

			//根据记录id删除unit
			erCardRecordFacade.findErCardRecordByImportRecordsId(erCardRecordDTO);
			//根据记录id删除同步记录信息
			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
			importRecordsDTO.setId(importRecordsId);
			esCardFacade.deleteImportRecordsWithTx(importRecordsDTO);
		}
		map.put("propblemReportRowList", propblemReportRowList);
		return map;
	}

	@Override
	public Map<String, Object> importECardAutoSend(List<Map<String, Object>> list, String source, String remark,
												   Long platformId,Long userId,String userName,String ip,String mac,int tmplType) {
		Map<String, Object> map = new HashMap<>();
		List<PropblemReportRowVO> propblemReportRowList = new ArrayList<>();
		//头文件
		Map<String,Object> row0=list.get(0);
		//空值校验
		String companyName=row0.get("CELL2").toString();
		// 根据公司名称查询公司信息
		CompanyDTO companyDTO = esCardFacade.queryCompanyByName(companyName);
		if(EmptyUtil.isEmpty(companyDTO))
			throw new BusinessException("没有"+companyName+"公司存在");

		String tmplName=row0.get("CELL4").toString();
		String createTime=row0.get("CELL6").toString();
		String sn=row0.get("CELL8").toString();
		// 数量校验
		if (list.size() > 1002){
			throw new BusinessException("单次导入数据不能超过1000条");
		}
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyyMMdd" );
		Date generatedTime = null;
		if(EmptyUtil.isNotEmpty(createTime)){
			try {
				generatedTime = sdf.parse(createTime);
			} catch (Exception e) {
				throw new BusinessException("头文件创建输入值不对，请输入如20171207");
			}
		}
		int rows = esCardFacade.findImportRecordsAll(tmplName,generatedTime,sn);
		if(rows != 0){
			throw new BusinessException("重复导入");
		}
		Set<String> s = new HashSet<String>();

		for (int i = 2; i < list.size(); i++) {
			StringBuffer value = new StringBuffer();
			//数据验证
			// 用户邮箱
			String email = list.get(i).get("CELL1").toString();
			if(EmptyUtil.isEmpty(email))
				value.append("用户邮箱不能为空 ; ");
			// 根据邮箱查询用户信息
			UserDTO userDTO = esCardFacade.findByMail(email);
			if(EmptyUtil.isEmpty(userDTO)) {
				value.append("邮箱未认证 ; ");
			}else {
				if(userDTO.getIsAvailable() != 1)
					value.append("该邮箱账户已离职 ; ");
				if(!userDTO.getCompanyId().equals(companyDTO.getId()))
					value.append("该邮箱账户不是"+companyName+"企业员工 ; ");
				String username = list.get(i).get("CELL2").toString();
				if(EmptyUtil.isEmpty(username))
					value.append("用户姓名不能为空 ; ");
				if(!userDTO.getName().equals(username))
					value.append("用户姓名不 匹配 ; ");
			}

			String skuSerialNumber = list.get(i).get("CELL3").toString();
			if(EmptyUtil.isEmpty(skuSerialNumber))
				value.append("sku编号不能为空 ; ");
			//查询所有卡密信息
			ECardDTO cardDTO = new ECardDTO();
			cardDTO.setSkuSerialNumber(skuSerialNumber);
			List<ECardDTO> eCardList = esCardFacade.findECardAll(cardDTO);
			//将以存在的卡密卡号存入set集合
			for (ECardDTO eCardDTO : eCardList) {
				s.add(eCardDTO.getCardNumber());
			}
			//卡号
			String cardNumber = list.get(i).get("CELL4").toString();
			if(EmptyUtil.isEmpty(cardNumber))
				value.append("卡号不能为空; ");
			boolean b = s.add(cardNumber);
			if(!b){
				value.append("卡号重复 ; ");
			}
			//卡密
			String cardThick = list.get(i).get("CELL5").toString();
			if(EmptyUtil.isEmpty(cardThick)){
				value.append("卡密不能为空; ");
			}
			//开始时间
			String start = list.get(i).get("CELL6").toString();
			if(EmptyUtil.isNotEmpty(start)){
				if(!StringUtils.isNotFigure(start)){
					value.append("开始日期输入值不对，请输入如20171207; ");
				}

			}else{
				value.append("开始日期输入值不能为空; ");
			}
			//结束时间
			String end = list.get(i).get("CELL7").toString();
			if(EmptyUtil.isNotEmpty(end)){
				if(!StringUtils.isNotFigure(end)){
					value.append("结束日期输入值不对，请输入如20171207; ");
				}

			}else{
				value.append("结束日期输入值不能为空; ");
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
			// 获取当前时间
			/*long millis = DateUtils.curTimeMillis();
			// 判断当前时间是否在限购规则时间内
			if(startTime.getTime() > millis && millis > endTime.getTime()){
				
			}*/
			if(EmptyUtil.isNotEmpty(userDTO)) {
				list.get(i).put("userId", userDTO.getId());
				list.get(i).put("loginName", userDTO.getLoginName());
			}
			//根据sku编号查询skuid信息
			SkuDTO skuDTO = esCardFacade.findSkuECardBySkuSerialNumber(skuSerialNumber);
			if(EmptyUtil.isEmpty(skuDTO)){
				value.append(" 请确定该sku为卡券sku; ");
			}else{
				list.get(i).put("skuName", skuDTO.getSkuName());
				list.get(i).put("skuId", skuDTO.getId());
				list.get(i).put("standardProductUnitId", skuDTO.getStandardProductUnitId());
			}
			if(value.length() > 0){
				PropblemReportRowVO propblemReportRowVO = new PropblemReportRowVO<>(value.toString(), i,null);
				propblemReportRowList.add(propblemReportRowVO);
			}


		}
		if(EmptyUtil.isEmpty(propblemReportRowList)){
			//写入记录表
			Long importRecordsId = esCardFacade.insertImportRecordsWithTx(companyName, tmplName,generatedTime,sn);
			map.put("importRecordsId", importRecordsId);
			//批量导入
			esCardFacade.importECardAutoSend(list, source, remark,importRecordsId,platformId,userId,userName,ip,mac,tmplType);
			//确认导入
			ErCardRecordDTO erCardRecordDTO = new ErCardRecordDTO();
			erCardRecordDTO.setImportRecordsId(importRecordsId);
			erCardRecordFacade.confirmTheImport(erCardRecordDTO,0);

			//根据记录id删除unit
			//erCardRecordFacade.findErCardRecordByImportRecordsId(erCardRecordDTO);
			//根据记录id删除同步记录信息
			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
			importRecordsDTO.setId(importRecordsId);
			esCardFacade.deleteImportRecordsWithTx(importRecordsDTO);
		}
		map.put("propblemReportRowList", propblemReportRowList);
		return map;
	}
	/**
	 * 查询被分配给当前用户的体检券
	 */
	@Override
	public JsonResult<Map<String, Object>> myBodyCheck(ECardDTO dto,Pagination page) {
		List<Map<String, Object>> list = new ArrayList<>();
		Integer companyType = productFacade.findCompanyTypeByUserId(dto.getUserId());
		if(companyType != 0){
			Map<String, Object> map1 = new HashMap<>();
			map1.put("skuName", "爱康国宾体检卡券 团检券");
			map1.put("cardNumber", SequenceUtil.genCardNumberCode(dto.getUserId()));
			map1.put("cardThick", "123456");
			map1.put("endTime", DateUtils.stampToDate(DateUtils.getSomedayStart(365), DateUtils.DATE_FORMAT));
			map1.put("isAppUse", true);
			map1.put("isValid", 0); // add by paul ,0:无效、1:有效
			list.add(map1);
			Map<String, Object> map2 = new HashMap<>();
			map2.put("skuName", "爱康国宾体检卡券");
			map2.put("cardNumber", SequenceUtil.genCardNumberCode(dto.getUserId()));
			map2.put("cardThick", "123456");
			map2.put("endTime", DateUtils.stampToDate(DateUtils.getSomedayStart(365), DateUtils.DATE_FORMAT));
			map2.put("isAppUse", true);
			map2.put("isValid", 0); // add by paul ,0:无效、1:有效
			list.add(map2);
			PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
			result.setList(list);
			result.setPageNo(1);
			result.setPageSize(2);
			result.setTotalSize(2);
			return JsonResult.success("cardPage", result);
		}
		PageResult<ECardDTO> pageResult = esCardFacade.findECardOfPage(dto, page);

		List<ECardDTO> eCardList = pageResult.getList();
		for (ECardDTO eCardDTO : eCardList) {
			Map<String, Object> map = new HashMap<>();
			// 根据skuid查询sku信息
			String imgUrl = productFacade.queryPuNullImgUrl(eCardDTO.getSkuId());
			map.put("imgUrl", imgUrl);
			map.put("skuId", eCardDTO.getSkuId());
			map.put("skuName", eCardDTO.getSkuName());
			map.put("endTime", DateUtils.stampToDate(eCardDTO.getEndTime(), DateUtils.DATE_FORMAT));
			map.put("cardNumber", eCardDTO.getCardNumber());
			String cipher = eCardDTO.getCardThick();
			// 根据uuid查询加密信息
			CardSaltDTO salt = accountFacade.queryCardSaltByUUID(eCardDTO.getUuid());
			if (salt == null) {
				map.put("cardNumber", "该电子卡券密码有误");
			} else {
				String cipherDecrypt;
				try {
					cipherDecrypt = QEncodeUtil.aesDecrypt(cipher, salt.getSaltValue());
				} catch (Exception e) {
					cipherDecrypt = "该电子卡券密码存在异常";
					e.printStackTrace();
				}
				map.put("cardThick", cipherDecrypt);
			}
			// 根据skuid查询是否在app内使用
			boolean isAppUse = productFacade.isAppUseBySkuId(eCardDTO.getSkuId());
			// 根据skuId查询属性值Id
			// 第三方参数 7、内部 8、话费充值 9、爱康国宾体检套餐券
			Long linkAttValueId = productFacade.findLinkAttValueIdBySkuId(eCardDTO.getSkuId(),BusinessConstant.THIRDPARTY_BUTT_JOINT_PARAMETER);
			if(linkAttValueId.equals(9L))
				map.put("linkUrl", KANGUtils.linkUrl(eCardDTO.getCardNumber()));
			map.put("isAppUse", isAppUse);
			map.put("isValid", eCardDTO.getIsValid()); // add by paul ,0:无效、1:有效
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return JsonResult.success("cardPage", result);
	}

	@Override
	public Map<String, Object> updateECardStatus(String type, List<String> ids, List<Long> skuIds,
												 List<ECardStatusDTO> eCardList) throws BusinessException {
		Map<String, Object> key = new ConcurrentReaderHashMap();
		Map<String, Object> res = new HashedMap();
		boolean flag = false; int result = 0;
		avoidValidCheck(type, ids);
		if (Integer.valueOf(type).intValue() == Constant.INVALID_STATUS) { 	//设置无效
			//库存校验
			invalidStockCheck(eCardList);
			//状态更改
			key.put("ids", ids);
			key.put("isValid", Constant.INVALID_STATUS);
			result = esCardFacade.updateECardList(key);
		} else if (Integer.valueOf(type).intValue() == Constant.VALID_STATUS) { 	//设置有效
			result = validStockCheck(eCardList);
		}
		if (result > 0) {
			flag = true;
		}
		res.put("result", flag);
		return res;
	}

	/**
	 * 判断复选框所选卡券的有效性,后台过滤掉无需处理的卡券
	 * @param type
	 * @param ids
	 */
	private void avoidValidCheck(String type, List<String> ids) {
		Map<String, Object> params = new HashMap<>();
		params.put("ids", ids);
		params.put("isValid", type);
		List<ECardDTO> validCardList = esCardFacade.queryECardList(params);
		if (!EmptyUtil.isEmpty(validCardList)) {
			if (Constant.INVALID_STATUS == Integer.valueOf(type)) {
				new BusinessException(Constant.SELECT_INVALID_ECARD_ERROR);
			}
			new BusinessException(Constant.SELECT_VALID_ECARD_ERROR);
		}
	}

	/**
	 * 库存校验
	 * @param eCardStatusDTOList
	 */
	private void invalidStockCheck(List<ECardStatusDTO> eCardStatusDTOList) {
		for (ECardStatusDTO eCardStatusDTO:eCardStatusDTOList) {
			ECardDTO eCardDto = new ECardDTO();
			List<Long> puIds = new ArrayList<>();
			int allocateECardSum = 0;
			int realFrozenStockNum = 0;
			int puStockNums = 0;
			//SKU
			SkuDTO dto = esCardFacade.findSkuById(eCardStatusDTO.getSkuId());
			if (EmptyUtil.isEmpty(dto)) {
				new BusinessException(Constant.INVALID_ECARD_SKU_ERROR);
			}
			//当前卡券可分配unit数量
			eCardDto.setSkuId(eCardStatusDTO.getSkuId());
			eCardDto.setIsValid(Constant.VALID_STATUS);
			eCardDto.setIsAllocation(Constant.ECARD_NOT_ALLOCATION);
			List<ECardDTO> eCardList = esCardFacade.findECardAll(eCardDto);
			if (!EmptyUtil.isEmpty(eCardList)) {
				allocateECardSum = eCardList.size();
			}

			//冻结unit数量
			MerchantProductVirtualStockDTO merchantProductVirtualStock =
					esCardFacade.queryMerchantProductVirtualStock(eCardStatusDTO.getSkuId());
			if(!EmptyUtil.isEmpty(merchantProductVirtualStock)){
				realFrozenStockNum = merchantProductVirtualStock.getRealFrozenStockNum().intValue();
			}

			//该sku所对应所有的pu的在线库存量之和
			List<CommodityProductUnitDTO> puList = esCardFacade.queryPuList(eCardStatusDTO.getSkuId());
			if(EmptyUtil.isEmpty(puList)){
				throw new BusinessException(Constant.ECARD_PU_NOT_EXIST);
			}
			for (CommodityProductUnitDTO tempPu : puList) {
				puIds.add(tempPu.getId());
			}
			//pu库存
			List<CommodityProductUnitWarehouseStockDTO> puWarehouseStockList =
					esCardFacade.queryPuWarehouseStockList(puIds);

			//拼接所有pu真实库存之和
			for (CommodityProductUnitWarehouseStockDTO puWarehouseStock : puWarehouseStockList) {
				puStockNums += puWarehouseStock.getRealStockNum();
			}

			int allocate = allocateECardSum + realFrozenStockNum;
			if (puStockNums > allocate) {
				int sum = puStockNums - allocate;
				throw new BusinessException(Constant.SET_INVALID_ECARD_ERROR + sum + "份");
			}
		}
	}

	/**
	 * 设置卡券为有效状态
	 * @param eCardList
	 * @return
	 */
	private int validStockCheck(List<ECardStatusDTO> eCardList) {
		int sum = 0;
		for (ECardStatusDTO eCard:eCardList) {
			ECardDTO dto = new ECardDTO();
			if (eCard.getEndTime().compareTo(new Date()) < 0) {
				throw new BusinessException(Constant.ECARD_VALID_NOW_DATE_ERROR);
			}
			if (eCard.getEndTime().compareTo(eCard.getStartTime()) < 0) {
				throw new BusinessException(Constant.ECARD_VALID_DATE_ERROR);
			}
			dto.setId(Long.parseLong(eCard.getId()));
			dto.setStartTime(eCard.getStartTime());
			dto.setEndTime(eCard.getEndTime());
			dto.setIsValid(Constant.VALID_STATUS);
			dto.setUpdateTime(DateUtils.parseDate(DateUtils.getDefaultDateTimeNow()));
			int i = esCardFacade.updateECardWithTx(dto);
			sum += i;
		}
		return sum;
	}

}
	