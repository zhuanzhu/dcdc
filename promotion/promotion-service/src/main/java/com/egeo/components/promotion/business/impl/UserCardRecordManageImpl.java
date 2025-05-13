package com.egeo.components.promotion.business.impl;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.egeo.components.order.vo.OrderListExportVO;
import com.egeo.components.promotion.business.UserCardRecordManage;
import com.egeo.components.promotion.converter.BuyCardItemConverter;
import com.egeo.components.promotion.dto.*;
import com.egeo.components.promotion.enums.CardStateEnum;
import com.egeo.components.promotion.enums.CardTypeEnum;
import com.egeo.components.promotion.enums.CardUseStateEnum;
import com.egeo.components.promotion.enums.SettleMethodEnum;
import com.egeo.components.promotion.facade.*;
import com.egeo.components.promotion.vo.GrantUserBuyCardVO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountReqVO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.components.promotion.vo.UseCardReqVO;
import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.components.user.vo.OpenAccountReqVO;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.RandomUtil;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.DateUtils;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.Upload;
import com.egeo.web.JsonResult;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("userCardRecord")
public class UserCardRecordManageImpl implements UserCardRecordManage{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserClient userClient;

	@Resource
	private CompanyClient companyClient;

	@Resource
	private UserExtendClient userExtendClient;

	@Autowired
	private Upload uploadService;


	@Resource(name="userCardRecordFacade")
	private UserCardRecordFacade userCardRecordFacade;

	@Resource(name="buyCardBaseFacade")
	private BuyCardBaseFacade buyCardBaseFacade;

	@Resource(name="cardUseRecordFacade")
	private CardUseRecordFacade cardUseRecordFacade;

	@Resource(name="buyCardItemFacade")
	private BuyCardItemFacade buyCardItemFacade;

	@Resource(name="buyCardItemRefundFacade")
	private BuyCardItemRefundFacade buyCardItemRefundFacade;

	@Override
	public UserCardRecordDTO findUserCardRecordById(UserCardRecordDTO dto) {
		UserCardRecordDTO rt = userCardRecordFacade.findUserCardRecordById(dto);
		if(rt ==null){
			return rt;
		}
		if(rt.getUserId() !=null){
			UserDTO userDTO = userClient.findUserByID(rt.getUserId());
			if(userDTO !=null){
				rt.setUserMobile(userDTO.getMobile());
				rt.setUserName(userDTO.getName());
				rt.setUserMail(userDTO.getMail());
			}
			UserExtendDTO userExtendDTO = userExtendClient.findById(rt.getUserId());
			if(userExtendDTO !=null){
				if(EmptyUtil.isEmpty(rt.getUserName())){
					rt.setUserName(userExtendDTO.getName());
				}
				if(EmptyUtil.isEmpty(rt.getUserMobile())){
					rt.setUserMobile(userExtendDTO.getMobile());
				}
			}
		}

		if(rt.getOperator() !=null){
			UserDTO userDTO = userClient.findUserByID(rt.getOperator());
			if(userDTO !=null){
				rt.setOperatorName(userDTO.getLoginName());
			}
		}

		if(rt.getCompanyId() !=null){
			CompanyDTO companyDTO = companyClient.findCompanyById(rt.getCompanyId());
			if(companyDTO !=null){
				rt.setCompanyName(companyDTO.getCompanyName());
			}
		}
		return rt;
	}

	@Override
	public PageResult<UserCardRecordDTO> findUserCardRecordOfPage(UserCardRecordDTO dto, Pagination page) {
		return userCardRecordFacade.findUserCardRecordOfPage(dto, page);
	}
	@Override
	public PageResult<UserCardRecordDTO> findUserCardRecordOfPagePlat(UserCardRecordDTO dto, Pagination page){
		List<Long> uIds = null;
		if(EmptyUtil.isNotEmpty(dto.getUserName()) || EmptyUtil.isNotEmpty(dto.getUserMobile())){
			UserDTO userQueryDTO = new UserDTO();
			userQueryDTO.setName(dto.getUserName());
			userQueryDTO.setMobile(dto.getUserMobile());
			List<UserDTO> userDTOS = userClient.findUser(userQueryDTO);
			if(EmptyUtil.isEmpty(userDTOS)){
				PageResult<UserCardRecordDTO> result = new PageResult<>();
				result.setList(new ArrayList<>());
				result.setPageNo(page.getPageNo());
				result.setPageSize(page.getPageSize());
				result.setTotalSize(0);
				return result;
			}
			uIds = FHCollectionUtils.listToStrList(userDTOS,UserDTO::getId);
			dto.setUserIds(uIds);
		}
		PageResult<UserCardRecordDTO> rt=userCardRecordFacade.findUserCardRecordOfPage(dto, page);
		if(!EmptyUtil.isEmpty(rt.getList())){
			List<String> userIds = new ArrayList<>();
			List<String> companyIds = new ArrayList<>();
			Map<Long,UserDTO> userMaps = null;
			Map<Long, UserExtendDTO> userExtendsMaps = null;
			Map<Long,String> companyMaps = null;
			for (UserCardRecordDTO userCardRecordDTO : rt.getList()) {
				if(userCardRecordDTO.getUserId() !=null && !userIds.contains(String.valueOf(userCardRecordDTO.getUserId()))){
					userIds.add(String.valueOf(userCardRecordDTO.getUserId()));
				}
				if(userCardRecordDTO.getOperator() !=null && !userIds.contains(String.valueOf(userCardRecordDTO.getOperator()))){
					userIds.add(String.valueOf(userCardRecordDTO.getOperator()));
				}
				if(userCardRecordDTO.getCompanyId() !=null && !companyIds.contains(String.valueOf(userCardRecordDTO.getCompanyId()))){
					companyIds.add(String.valueOf(userCardRecordDTO.getCompanyId()));
				}
			}
			if(!EmptyUtil.isEmpty(userIds)){
				List<UserDTO> userDTOS = userClient.queryUserByIds(userIds);
				userMaps = FHCollectionUtils.listToMap(userDTOS,UserDTO::getId,e->e);

				 List<UserExtendDTO> userExtendDTOS = userExtendClient.queryFullUserExtend(userIds);
				userExtendsMaps = FHCollectionUtils.listToMap(userExtendDTOS,UserExtendDTO::getId,e->e);
			}
			if(!EmptyUtil.isEmpty(companyIds)){
				List<CompanyDTO> companyDTOS = companyClient.findCompanyByCompanyIds(companyIds);
				companyMaps = FHCollectionUtils.listToMap(companyDTOS,CompanyDTO::getId,e->e.getCompanyName());
			}

			if(EmptyUtil.isEmpty(userMaps) && EmptyUtil.isEmpty(companyMaps)){
				return rt;
			}
			for (UserCardRecordDTO userCard : rt.getList()) {
				if(!EmptyUtil.isEmpty(userMaps) && userMaps.containsKey(userCard.getUserId())){
					UserDTO userDTO = userMaps.get(userCard.getUserId());
					userCard.setUserMobile(userDTO.getMobile());
					userCard.setUserName(userDTO.getName());
					userCard.setUserMail(userDTO.getMail());
				}
				if(!EmptyUtil.isEmpty(userExtendsMaps) && userExtendsMaps.containsKey(userCard.getUserId())){
					UserExtendDTO userExtendDTO = userExtendsMaps.get(userCard.getUserId());
					if(EmptyUtil.isEmpty(userCard.getUserName())){
						userCard.setUserName(userExtendDTO.getName());
					}
					if(EmptyUtil.isEmpty(userCard.getUserMobile())){
						userCard.setUserMobile(userExtendDTO.getMobile());
					}
				}
				if(!EmptyUtil.isEmpty(companyMaps) && companyMaps.containsKey(userCard.getCompanyId())){
					String companyName = companyMaps.get(userCard.getCompanyId());
					userCard.setCompanyName(companyName);
				}
				if(!EmptyUtil.isEmpty(userMaps) && userCard.getOperator() !=null && userMaps.containsKey(userCard.getOperator())){
					UserDTO userDTO = userMaps.get(userCard.getOperator());
					userCard.setOperatorName(userDTO.getLoginName());
				}
			}
		}
		return rt;
	}

	@Override
	public List<UserCardRecordDTO> findUserCardRecordAll(UserCardRecordDTO dto) {
		return userCardRecordFacade.findUserCardRecordAll(dto);
	}

	@Override
	public Long insertUserCardRecordWithTx(UserCardRecordDTO dto) {
		return userCardRecordFacade.insertUserCardRecordWithTx(dto);
	}

	@Override
	public int updateUserCardRecordWithTx(UserCardRecordDTO dto) {
		return userCardRecordFacade.updateUserCardRecordWithTx(dto);
	}

	@Override
	public int deleteUserCardRecordWithTx(UserCardRecordDTO dto) {
		return userCardRecordFacade.deleteUserCardRecordWithTx(dto);
	}

	@Override
	public JsonResult<String> grantUserBuyCard(List<GrantUserBuyCardVO> list,Long operator){
		if(CollectionUtils.isEmpty(list)){
			return JsonResult.fail("参数为空");
		}
		List<String> userIds = new ArrayList<>();
		List<Long> cardIds = new ArrayList<>();
		for (GrantUserBuyCardVO grantUserBuyCardVO : list) {
			if(EmptyUtil.isEmpty(grantUserBuyCardVO.getUserId())
					|| grantUserBuyCardVO.getCardId()==null
					|| grantUserBuyCardVO.getCardId()<1
					|| grantUserBuyCardVO.getCardNum() ==null
					|| grantUserBuyCardVO.getCardNum()<1){
				return JsonResult.fail("参数存在错误或为空");
			}
			userIds.add(String.valueOf(grantUserBuyCardVO.getUserId()));
			cardIds.add(grantUserBuyCardVO.getCardId());
		}
		List<UserDTO> userDTOS = userClient.queryUserByIds(userIds);
		Map<Long,UserDTO> userMaps = FHCollectionUtils.listToMap(userDTOS,UserDTO::getId,e->e);
		Map<Long,BuyCardBaseDTO> cardBaseDTOS = buyCardBaseFacade.findBuyCardBaseMaps(cardIds);
		if(EmptyUtil.isEmpty(userMaps)){
			return JsonResult.fail("未找到对应的用户信息");
		}
		if(EmptyUtil.isEmpty(cardBaseDTOS)){
			return JsonResult.fail("未找到对应的卡信息");
		}
		//后补开户
		openAccount(userDTOS);
		List<UserCardRecordDTO> addList = new ArrayList<>();
		for (GrantUserBuyCardVO vo : list) {
			BuyCardBaseDTO src = cardBaseDTOS.get(vo.getCardId());
			if(src == null || src.getCardState()==null || src.getCardState().intValue() !=CardStateEnum.EFFECTIVE.getState().intValue()){
				return JsonResult.fail(vo.getCardId()+"对应的卡信息未找到");
			}
			UserDTO userDTO = userMaps.get(vo.getUserId());
			if(userDTO == null){
				return JsonResult.fail(vo.getUserId()+"对应的用户信息未找到");
			}
			Integer num = vo.getCardNum();
			for (int i = 0; i < num; i++) {
				UserCardRecordDTO dto = new UserCardRecordDTO();
				dto.setExpireDateStart(new Date());
				dto.setOperator(operator);
				dto.setUserId(userDTO.getId());
				dto.setCompanyId(userDTO.getCompanyId());
				dto.setCardName(src.getCardName());
				dto.setCardType(src.getCardType());
				dto.setSettleMethod(src.getSettleMethod());
				dto.setCardAmount(src.getCardAmount());
				dto.setCardCash(dto.getCardAmount());
				dto.setSourceCardId(src.getId());
				dto.setCardNo(getCardNo(dto,src.getCardPrefix()));
				dto.setExpireDateEnd(calcDate(src.getExpirationDateUnit(),src.getExpirationDate(),dto.getExpireDateStart()));
				addList.add(dto);
			}
		}
		try {
			userCardRecordFacade.insertUserCardRecordBatchWithTx(addList);
		}catch (Exception e){
			e.printStackTrace();
			return JsonResult.fail("操作异常："+e.getMessage());
		}

		return JsonResult.success("操作完成");
	}

	private String getCardNo(UserCardRecordDTO dto,String cardPrefix){
		StringBuffer sb = new StringBuffer();
		if(EmptyUtil.isNotEmpty(cardPrefix)){
			sb.append(cardPrefix+"-");
		}
		sb.append(dto.getCompanyId()).append("-");
		sb.append(dto.getUserId()).append("-");
		sb.append(DateUtils.format("yyMM-ddHH-mmss",new Date())).append("-");
		sb.append(RandomUtil.createRandom(true,4));
		return sb.toString();
	}

	private Date calcDate(Integer expirationDateUnit,Integer expirationDateNum,Date startDate){
		Integer offset = expirationDateNum;
		if(expirationDateUnit ==2){
			offset = expirationDateNum*7;
		}
		if(expirationDateUnit ==3){
			offset = expirationDateNum*30;
		}
		if(expirationDateUnit ==4){
			offset = expirationDateNum*30*12;
		}
		Date endDate = DateUtil.offsetDay(startDate,offset);
		String endDateDay = DateUtils.format(DateUtils.DATE_FORMAT,endDate);
		endDateDay = endDateDay+" 23:59:59";
		return DateUtils.stringToDates(endDateDay);
	}

	@Override
	public Integer countUserCardRecord(UserCardRecordDTO dto){
		return userCardRecordFacade.countUserCardRecord(dto);
	}

	@Override
	public List<SumUserCardTypeAmountVO> sumUserCardTypeAmount(SumUserCardTypeAmountReqVO vo){
		UserCardRecordDTO dto = new UserCardRecordDTO();
		dto.setUserId(vo.getUserId());
		dto.setCardState(vo.getCardState());
		dto.setUseState(vo.getUseState());
		if(EmptyUtil.isEmpty(dto.getCardState())){
			dto.setCardState(CardStateEnum.EFFECTIVE.getState());
		}
		if(EmptyUtil.isEmpty(dto.getUseState())){
			dto.setUseStates(Arrays.asList(CardUseStateEnum.UN_USE.getUseState(),CardUseStateEnum.USE_ING.getUseState()));
		}
		List<UserCardRecordDTO> queryRT = userCardRecordFacade.sumUserCardTypeAmount(dto);

		List<SumUserCardTypeAmountVO> rt = new ArrayList<>();
		for (CardTypeEnum value : CardTypeEnum.values()) {
			SumUserCardTypeAmountVO sumUserCardTypeAmountVO = new SumUserCardTypeAmountVO();
			sumUserCardTypeAmountVO.setCardType(value.getCardType());
			sumUserCardTypeAmountVO.setTypeName(value.getCardTypeName());
			sumUserCardTypeAmountVO.setCardCash(BigDecimal.ZERO);
			rt.add(sumUserCardTypeAmountVO);
		}

		Map<Integer,BigDecimal> typeMaps = FHCollectionUtils.listToMap(queryRT,UserCardRecordDTO::getCardType,UserCardRecordDTO::getCardCash);
		for (SumUserCardTypeAmountVO sumUserCardTypeAmountVO : rt) {
			if(EmptyUtil.isNotEmpty(typeMaps) && typeMaps.containsKey(sumUserCardTypeAmountVO.getCardType())){
				sumUserCardTypeAmountVO.setCardCash(typeMaps.get(sumUserCardTypeAmountVO.getCardType()));
			}
		}
		return rt;
	}
	@Override
	public PageResult<UserCardRecordDTO> findClientUserCardRecordOfPage(UserCardRecordDTO dto, Pagination page){
		return userCardRecordFacade.findClientUserCardRecordOfPage(dto,page);
	}

	@Override
	public List<UserCardRecordDTO> findClientUserCardRecordAll(UserCardRecordDTO dto){
		return userCardRecordFacade.findClientUserCardRecordAll(dto);
	}

	private void openAccount(List<UserDTO> userDTOS){
		if(CollectionUtils.isEmpty(userDTOS)){
			return;
		}
		List<Long> userIds = FHCollectionUtils.listToStrList(userDTOS,UserDTO::getId);
		List<Integer> types = Arrays.asList(6,7);
		OpenAccountReqVO vo = new OpenAccountReqVO();
		vo.setPlatformId(7L);
		vo.setAccountTypes(types);
		vo.setUserIds(userIds);
		userClient.openUserAccountBack(vo);
	}

	@Override
	public BigDecimal sumUserCardCash(UserCardRecordDTO dto){
		List<UserCardRecordDTO> list = userCardRecordFacade.findUserCardRecordAll(dto);
		if(CollectionUtils.isEmpty(list)){
			return BigDecimal.ZERO;
		}
		BigDecimal totalCardCash = list.stream()
				.map(UserCardRecordDTO::getCardCash)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return totalCardCash;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean useCard(UseCardReqVO vo){
		Map<Long,BigDecimal> map = vo.getMap();
		Long soId = vo.getOrderId();
		if(CollectionUtils.isEmpty(map)){
			return false;
		}
		for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
			Long key = entry.getKey();      // 获取键
			BigDecimal value = entry.getValue(); // 获取值
			UserCardRecordDTO qeueryDTO = new UserCardRecordDTO();
			qeueryDTO.setId(key);
			UserCardRecordDTO rtDTO = userCardRecordFacade.findUserCardRecordById(qeueryDTO);

			 userCardRecordFacade.updateUserCardRecordWithTx(key,value);
			CardUseRecordDTO dto = new CardUseRecordDTO();
			dto.setCardId(rtDTO.getId());
			dto.setCardNo(rtDTO.getCardNo());
			dto.setUserId(rtDTO.getUserId());
			dto.setCompanyId(rtDTO.getCompanyId());
			dto.setSoId(soId);
			dto.setUseAmount(value);
			dto.setOrderCode(vo.getOrderCode());
			dto.setCardName(rtDTO.getCardName());
			dto.setCardType(rtDTO.getCardType());
			dto.setSettleMethod(rtDTO.getSettleMethod());
			cardUseRecordFacade.insertCardUseRecordWithTx(dto);
		}
		List<BuyCardItemDTO> itemDTOS = BuyCardItemConverter.toUseBuyCardItemDetailPO(vo.getUseDetails());
		if(!CollectionUtils.isEmpty(itemDTOS)){
			for (BuyCardItemDTO itemDTO : itemDTOS) {
				buyCardItemFacade.insertBuyCardItemWithTx(itemDTO);
			}
		}

		return true;
	}

	@Override
	public Boolean cancelUserCard(){
		return cardUseRecordFacade.cancelUserCard();
	}

	@Override
	public Boolean buyCardRefund(List<BuyCardItemRefundDTO> dtos){
		if(CollectionUtils.isEmpty(dtos)){
			logger.error("卡劵退款信息为空，不能继续执行");
			return false;
		}

		BuyCardItemRefundDTO buyCardItemRefundDTO = dtos.get(0);
		Long soId = buyCardItemRefundDTO.getSoId();
		logger.info("订单id：{}要退数据:【{}】",soId, JSON.toJSONString(dtos));
		//获取所有的使用记录
		List<BuyCardItemDTO> buyCardItemDTOs = buyCardItemFacade.findBuyCardItemAll(soId);
		if(CollectionUtils.isEmpty(buyCardItemDTOs)){
			logger.info("未使用卡劵支付，无需卡劵退款");
			return true;
		}
		//获取所有的使用记录
		Map<Long,BigDecimal> cardIdUseAmountMap = new HashMap<>();
		Map<Long, BigDecimal> itemIdUseMap =new HashMap<>();
		for (BuyCardItemDTO buyCardItemDTO : buyCardItemDTOs) {
			cardIdUseAmountMap.merge(buyCardItemDTO.getCardId(),buyCardItemDTO.getUseAmount(),BigDecimal::add);
			itemIdUseMap.merge(buyCardItemDTO.getItemId(),buyCardItemDTO.getUseAmount(),BigDecimal::add);
		}
		//按item分组找到使用记录
		Map<Long, List<BuyCardItemDTO>> groupedByItemIdMap = buyCardItemDTOs.stream()
				.collect(Collectors.groupingBy(BuyCardItemDTO::getItemId));

		//获取所有的退款记录
		Map<Long,BigDecimal> cardIdRefundAmountMap = new HashMap<>();
		Map<Long, BigDecimal> itemIdRefundMap =new HashMap<>();
		Map<Long, BigDecimal> cardUseIdRefundMap =new HashMap<>();
		List<BuyCardItemRefundDTO> buyCardItemRefundDTOs = buyCardItemRefundFacade.findBuyCardItemRefundAll(soId);
		if(!CollectionUtils.isEmpty(buyCardItemRefundDTOs)){
			for (BuyCardItemRefundDTO cardItemRefundDTO : buyCardItemRefundDTOs) {
				cardIdRefundAmountMap.merge(cardItemRefundDTO.getCardId(),cardItemRefundDTO.getRefundAmount(),BigDecimal::add);
				itemIdRefundMap.merge(cardItemRefundDTO.getItemId(),cardItemRefundDTO.getRefundAmount(),BigDecimal::add);
				if(null !=cardItemRefundDTO.getCardUseId()){
					cardUseIdRefundMap.merge(cardItemRefundDTO.getCardUseId(),cardItemRefundDTO.getRefundAmount(),BigDecimal::add);
				}
			}
		}
		List<BuyCardItemRefundDTO> addBuyCardItemRefundList = new ArrayList<>();
		for (BuyCardItemRefundDTO dto : dtos) {
			logger.info("要退款订单项:{}",JSON.toJSONString(dto));
			//订单项本次需要退款金额
			BigDecimal needRefundItemAmount = dto.getRefundAmount();
			if(needRefundItemAmount.compareTo(BigDecimal.ZERO)<=0){
				logger.info("要退款订单项id:{}完成:{}",dto.getItemId(),needRefundItemAmount);
				continue;
			}
			//未找到使用记录
			if(!itemIdUseMap.containsKey(dto.getItemId())){
				logger.info("要退款订单项id:{}未找到使用记录,需要退款金额:{}",dto.getItemId(),needRefundItemAmount);
				continue;
			}
			//订单项最大可退金额
			BigDecimal maxCanRefundItemAmount = itemIdUseMap.get(dto.getItemId());
			//订单项已退款金额
			BigDecimal itemAlRefundAmount = itemIdRefundMap.containsKey(dto.getItemId())?itemIdRefundMap.get(dto.getItemId()):BigDecimal.ZERO;
			//剩余可退金额
			BigDecimal supCanRefundItemAmount = maxCanRefundItemAmount.subtract(itemAlRefundAmount);
			logger.info("要退款订单id:{}订单项id:{}最大可退金额:{},已退金额:{},剩余可退金额{}",dto.getSoId(),dto.getItemId(),maxCanRefundItemAmount,itemAlRefundAmount,supCanRefundItemAmount);
			if(needRefundItemAmount.compareTo(supCanRefundItemAmount) >0){
				logger.error("订单编号:{},订单项id:{}本次退款金额大于订单项可退金额",dto.getOrderCode(),dto.getItemId());
				throw new BusinessException("本次退款金额大于订单项可退金额");
			}
			List<BuyCardItemDTO> buyCardItemDTOS = groupedByItemIdMap.get(dto.getItemId());
			logger.info("订单id：{}，订单项id:{}找到所有该订单的使用记录:{}",soId,dto.getItemId(),JSON.toJSONString(buyCardItemDTOS));
			for (BuyCardItemDTO buyCardItemDTO : buyCardItemDTOS) {
				//该订单项退款结束
				if(needRefundItemAmount.compareTo(BigDecimal.ZERO) ==0){
					logger.info("要退款订单id:{}订单项id:{},找到卡id:{}时已完成结束:{}",dto.getSoId(),dto.getItemId(),buyCardItemDTO.getCardId(),needRefundItemAmount);
					break;
				}
				//该卡最大可退金额
				BigDecimal maxCanRefundCardAmount = cardIdUseAmountMap.get(buyCardItemDTO.getCardId());

				//该卡已退金额
				BigDecimal cardAlRefundAmount = cardIdRefundAmountMap.containsKey(buyCardItemDTO.getCardId())?cardIdRefundAmountMap.get(buyCardItemDTO.getCardId()):BigDecimal.ZERO;

				//该卡剩余可退金额
				BigDecimal supCanRefundCardAmount = maxCanRefundCardAmount.subtract(cardAlRefundAmount);
				logger.info("要退款订单id:{}订单项id:{},找到卡id:{},卡最大可退金额：{}，卡已退金额:{},剩余可退金额：{}，订单项还需退款金额：{}",dto.getSoId(),dto.getItemId(),buyCardItemDTO.getCardId(),maxCanRefundCardAmount,cardAlRefundAmount,supCanRefundCardAmount,needRefundItemAmount);
				BuyCardItemRefundDTO buyCardItemRefundAdd = new BuyCardItemRefundDTO();
				buyCardItemRefundAdd.setRefundNo(dto.getRefundNo());
				buyCardItemRefundAdd.setRemark(dto.getRemark());
				buyCardItemRefundAdd.setItemId(dto.getItemId());
				buyCardItemRefundAdd.setSoId(dto.getSoId() !=null?dto.getSoId():buyCardItemDTO.getSoId());
				buyCardItemRefundAdd.setChildId(dto.getChildId() !=null?dto.getChildId():buyCardItemDTO.getChildId());
				buyCardItemRefundAdd.setOrderCode(EmptyUtil.isNotEmpty(dto.getOrderCode())?dto.getOrderCode():buyCardItemDTO.getOrderCode());
				buyCardItemRefundAdd.setUserId(dto.getUserId() !=null?dto.getUserId():buyCardItemDTO.getUserId());
				buyCardItemRefundAdd.setCardId(buyCardItemDTO.getCardId());
				//若需要退款金额大于可退金额,那么该退款金额=该卡剩余可退金额
				/*if(needRefundItemAmount.compareTo(supCanRefundCardAmount) >=0){
					buyCardItemRefundAdd.setRefundAmount(supCanRefundCardAmount);
				}else{
					//否则退款金额=退款金额
					buyCardItemRefundAdd.setRefundAmount(needRefundItemAmount);
				}*/
				BigDecimal alUseRefundAmount= cardUseIdRefundMap.containsKey(buyCardItemDTO.getId())?cardUseIdRefundMap.get(buyCardItemDTO.getId()):BigDecimal.ZERO;
				BigDecimal supUseRefundAmount = buyCardItemDTO.getUseAmount().subtract(alUseRefundAmount);
				//剩余可退大于当前需要退的
				if(supUseRefundAmount.compareTo(needRefundItemAmount)>=0){
					buyCardItemRefundAdd.setRefundAmount(needRefundItemAmount);
				}else{
					//剩余可退小于需要退的，则等于
					buyCardItemRefundAdd.setRefundAmount(supUseRefundAmount);
				}
				logger.info("订单id:{},itemId:{},cardId:{},使用记录Id:{}，所有已退金额:{}当前剩余可退金额:{}，所需退款金额:{}",soId,buyCardItemDTO.getItemId(),buyCardItemDTO.getId(),alUseRefundAmount,supUseRefundAmount,needRefundItemAmount);
				buyCardItemRefundAdd.setCardUseId(buyCardItemDTO.getId());
				needRefundItemAmount = needRefundItemAmount.subtract(buyCardItemRefundAdd.getRefundAmount());
				addBuyCardItemRefundList.add(buyCardItemRefundAdd);

				//追加本次已退金额
				cardIdRefundAmountMap.merge(buyCardItemDTO.getCardId(),buyCardItemRefundAdd.getRefundAmount(),BigDecimal::add);
				itemIdRefundMap.merge(buyCardItemRefundAdd.getItemId(),buyCardItemRefundAdd.getRefundAmount(),BigDecimal::add);
				cardUseIdRefundMap.merge(buyCardItemDTO.getId(),buyCardItemRefundAdd.getRefundAmount(),BigDecimal::add);
			}
		}
		//
		if(!CollectionUtils.isEmpty(addBuyCardItemRefundList)){
			// 使用Collectors.toMap来分组并累加refundAmount
			Map<Long, BigDecimal> refundAmountSumByCardId = addBuyCardItemRefundList.stream()
					.collect(Collectors.toMap(
							BuyCardItemRefundDTO::getCardId,
							BuyCardItemRefundDTO::getRefundAmount,
							BigDecimal::add
					));
			logger.info("订单id:{}最终退款更新记录:{}",soId,JSON.toJSONString(refundAmountSumByCardId));
			//变更卡劵状态以及恢复可用余额
			for (Map.Entry<Long, BigDecimal> refundEntry : refundAmountSumByCardId.entrySet()) {
				userCardRecordFacade.refundUserCardUseIngWithTx(refundEntry.getKey(),refundEntry.getValue());
			}
			//保存退款明细
			for (BuyCardItemRefundDTO cardItemRefundDTO : addBuyCardItemRefundList) {
				buyCardItemRefundFacade.insertBuyCardItemRefundWithTx(cardItemRefundDTO);
			}
			return true;
		}
		return true;
	}

	@Override
	public JsonResult<Map<String,Object>> exportUserCardRecordSearch(UserCardRecordDTO dto){
		List<UserCardRecordDTO> list = findUserCardRecordSearch(dto);
		if(EmptyUtil.isNotEmpty(list) && list.size() >1000){
			return JsonResult.fail("导出数据量过大");
		}
		List<String> userIds = new ArrayList<>();
		List<String> companyIds = new ArrayList<>();
		Map<Long,UserDTO> userMaps = null;
		Map<Long, UserExtendDTO> userExtendsMaps = null;
		Map<Long,String> companyMaps = null;
		for (UserCardRecordDTO userCardRecordDTO : list) {
			if(userCardRecordDTO.getUserId() !=null && !userIds.contains(String.valueOf(userCardRecordDTO.getUserId()))){
				userIds.add(String.valueOf(userCardRecordDTO.getUserId()));
			}
			if(userCardRecordDTO.getOperator() !=null && !userIds.contains(String.valueOf(userCardRecordDTO.getOperator()))){
				userIds.add(String.valueOf(userCardRecordDTO.getOperator()));
			}
			if(userCardRecordDTO.getCompanyId() !=null && !companyIds.contains(String.valueOf(userCardRecordDTO.getCompanyId()))){
				companyIds.add(String.valueOf(userCardRecordDTO.getCompanyId()));
			}
		}
		if(!EmptyUtil.isEmpty(userIds)){
			List<UserDTO> userDTOS = userClient.queryUserByIds(userIds);
			userMaps = FHCollectionUtils.listToMap(userDTOS,UserDTO::getId,e->e);

			List<UserExtendDTO> userExtendDTOS = userExtendClient.queryFullUserExtend(userIds);
			userExtendsMaps = FHCollectionUtils.listToMap(userExtendDTOS,UserExtendDTO::getId,e->e);
		}
		if(!EmptyUtil.isEmpty(companyIds)){
			List<CompanyDTO> companyDTOS = companyClient.findCompanyByCompanyIds(companyIds);
			companyMaps = FHCollectionUtils.listToMap(companyDTOS,CompanyDTO::getId,e->e.getCompanyName());
		}
		for (UserCardRecordDTO userCard : list) {
			if(!EmptyUtil.isEmpty(userMaps) && userMaps.containsKey(userCard.getUserId())){
				UserDTO userDTO = userMaps.get(userCard.getUserId());
				userCard.setUserMobile(userDTO.getMobile());
				userCard.setUserName(userDTO.getName());
				userCard.setUserMail(userDTO.getMail());
			}
			if(!EmptyUtil.isEmpty(userExtendsMaps) && userExtendsMaps.containsKey(userCard.getUserId())){
				UserExtendDTO userExtendDTO = userExtendsMaps.get(userCard.getUserId());
				if(EmptyUtil.isEmpty(userCard.getUserName())){
					userCard.setUserName(userExtendDTO.getName());
				}
				if(EmptyUtil.isEmpty(userCard.getUserMobile())){
					userCard.setUserMobile(userExtendDTO.getMobile());
				}
			}
			if(!EmptyUtil.isEmpty(companyMaps) && companyMaps.containsKey(userCard.getCompanyId())){
				String companyName = companyMaps.get(userCard.getCompanyId());
				userCard.setCompanyName(companyName);
			}
			if(!EmptyUtil.isEmpty(userMaps) && userCard.getOperator() !=null && userMaps.containsKey(userCard.getOperator())){
				UserDTO userDTO = userMaps.get(userCard.getOperator());
				userCard.setOperatorName(userDTO.getLoginName());
			}
		}

		Workbook wb = new XSSFWorkbook();
		Sheet s = wb.createSheet("卡劵信息");
		String[] header = {"卡名", "卡号", "面值", "余额", "类型","卡状态","使用状态", "结算方式","绑定用户","绑定手机号","所属企业","生效时间", "失效时间"};
		// 创建表头
		Row head = s.createRow(0);
		for (int i = 0; i < header.length; i++) {
			head.createCell(i).setCellValue(header[i]);
		}
		completeUserCardListSheet(s,list);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			wb.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail("导出失败!");
		}
		String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
		return JsonResult.success("url", upload);
	}

	private List<UserCardRecordDTO> findUserCardRecordSearch(UserCardRecordDTO dto){
		List<Long> uIds = null;
		if(EmptyUtil.isNotEmpty(dto.getUserName()) || EmptyUtil.isNotEmpty(dto.getUserMobile())){
			UserDTO userQueryDTO = new UserDTO();
			userQueryDTO.setName(dto.getUserName());
			userQueryDTO.setMobile(dto.getUserMobile());
			List<UserDTO> userDTOS = userClient.findUser(userQueryDTO);
			if(EmptyUtil.isEmpty(userDTOS)){
				return new ArrayList<>();
			}
			uIds = FHCollectionUtils.listToStrList(userDTOS,UserDTO::getId);
			dto.setUserIds(uIds);
		}
		return userCardRecordFacade.findUserCardRecordAll(dto);
	}

	/**
	 * 将数据填写进卡劵细腻导出excel表格
	 *卡名、卡号、面值、余额、类型、卡状态、使用状态、结算方式、绑定用户、绑定手机号、所属企业、生效时间、失效时间
	 * @param s
	 * @param list
	 */
	private void completeUserCardListSheet(Sheet s, List<UserCardRecordDTO> list) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < list.size(); i++) {
			Row r = s.createRow(i + 1);
			UserCardRecordDTO vo = list.get(i);
			int cellCunt = 0;
			r.createCell(cellCunt++).setCellValue(vo.getCardName());
			r.createCell(cellCunt++).setCellValue(vo.getCardNo());
			r.createCell(cellCunt++).setCellValue(vo.getCardAmount().doubleValue());
			r.createCell(cellCunt++).setCellValue(vo.getCardCash().doubleValue());
			r.createCell(cellCunt++).setCellValue(vo.getCardName());
			r.createCell(cellCunt++).setCellValue(CardStateEnum.ofDescribe(vo.getCardState()));
			r.createCell(cellCunt++).setCellValue(CardUseStateEnum.ofDescribe(vo.getUseState()));
			r.createCell(cellCunt++).setCellValue(SettleMethodEnum.ofDescribe(vo.getSettleMethod()));
			r.createCell(cellCunt++).setCellValue(vo.getUserName());
			r.createCell(cellCunt++).setCellValue(vo.getUserMobile());
			r.createCell(cellCunt++).setCellValue(vo.getCompanyName());
			r.createCell(cellCunt++).setCellValue(sdf.format(vo.getExpireDateStart()));
			r.createCell(cellCunt++).setCellValue(sdf.format(vo.getExpireDateEnd()));
		}
	}
}
