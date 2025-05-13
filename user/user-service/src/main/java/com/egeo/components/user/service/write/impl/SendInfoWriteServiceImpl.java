package com.egeo.components.user.service.write.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.common.InfoTemplateReplaceKeyWordUtil;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.dto.InfoTemplateDTO;
import com.egeo.components.user.dto.InfoTemplateParameterDTO;
import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.service.read.InfoTemplateParameterReadService;
import com.egeo.components.user.service.read.InfoTemplateReadService;
import com.egeo.components.user.service.read.InfoTemplateSendWayReadService;
import com.egeo.components.user.service.read.UserReadService;
import com.egeo.components.user.service.write.InfoWriteService;
import com.egeo.components.user.service.write.SendInfoWriteService;
import com.egeo.utils.ActiveMQUtils;
//import com.egeo.mq.provider.Provider;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;


@Service("sendInfoWriteService")
public class SendInfoWriteServiceImpl implements SendInfoWriteService {
	private static final XLogger logger = XLogger.getLogger(SendInfoWriteServiceImpl.class);

	@Value("${mq.queueName_sendInfo:send-info-queue}")
	private String queueNameSendInfo;

	@Autowired
	private InfoWriteService infoWriteService;
	@Autowired
	private InfoTemplateReadService infoTemplateReadService;
	@Autowired
	private InfoTemplateParameterReadService infoTemplateParameterReadService;
	@Autowired
	private InfoTemplateSendWayReadService infoTemplateSendWayReadService;
	@Autowired
	private UserReadService userReadService;
	
	public SendInfoWriteServiceImpl(){
		
	}
	public SendInfoWriteServiceImpl(
			UserReadService userReadService,
			InfoWriteService infoWriteService,
			InfoTemplateReadService infoTemplateReadService,
			InfoTemplateSendWayReadService infoTemplateSendWayReadService){
		this.userReadService = userReadService;
		this.infoWriteService = infoWriteService;
		this.infoTemplateReadService = infoTemplateReadService;
		this.infoTemplateSendWayReadService = infoTemplateSendWayReadService;
	}
	
	@Override
	public Boolean sendInfo(InfoDTO dto) {
		try {
			ActiveMQUtils.getSender(queueNameSendInfo).send(JSON.toJSONString(dto));
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("发送"+" send-info-queue "+"消息失败："+ JSON.toJSONString(dto));
		}
		return false;
	}
	@Override
	public Long insertOrderPayStatusInfoAndSend(Long infoTemplateId,String orderCode,Integer orderPayStatus, Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.orderPayStatusInfoTemplateReplaceKeyWord(infoTemplateDTO2, orderCode, orderPayStatus);
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());

			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
		
	}
	/**
	 * 保存并发送消息
	 * @param infoDTO
	 * @return
	 */
	private Long insertSendInfo(Long infoTemplateId,InfoDTO infoDTO) {
		logger.info("userService:"+userReadService.toString());
		logger.info("info:"+infoDTO.toString());
		UserDTO userByID = userReadService.findUserByID(infoDTO.getUserId());
		if(userByID.getCompanyId().equals(PlatformKeyConstant.BAI_ER_COMPANY_ID)){
			infoDTO.setInfoHead(PlatformKeyConstant.BAI_ER_MESSAGE_HEAD_NAME);
		}

		List<Long> sendWayIds = new ArrayList<>();
		InfoTemplateSendWayDTO infoTemplateSendWayDTO = new InfoTemplateSendWayDTO();
		infoTemplateSendWayDTO.setInfoTemplateId(infoTemplateId);
		infoTemplateSendWayDTO.setPlatformId(infoDTO.getPlatformId());
		List<InfoTemplateSendWayDTO> list = infoTemplateSendWayReadService.findInfoTemplateSendWayAll(infoTemplateSendWayDTO);
		for (InfoTemplateSendWayDTO infoTemplateSendWayDTO2 : list) {
			sendWayIds.add(infoTemplateSendWayDTO2.getSendWayId());
		}
		Long infoId = infoWriteService.insertInfoWithTx(infoDTO, sendWayIds);
		infoDTO.setId(infoId);
		// 发送消息
		sendInfo(infoDTO);
		return infoId;
	}
	@Override
	public Long insertOrderConfirmStatusInfoAndSend(Long platformId, Long infoTemplateId, String orderCode, Integer orderConfirmStatus,
													Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		logger.info("infoTemplateId="+infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.orderConfirmStatusInfoTemplateReplaceKeyWord(infoTemplateDTO2, orderCode, orderConfirmStatus);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(platformId);
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertOrderDeliveryStatusInfoAndSend(Long infoTemplateId, String orderCode, Integer deliveryStatus,
			Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.orderDeliveryStatusInfoTemplateReplaceKeyWord(infoTemplateDTO2, orderCode, deliveryStatus);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertUserFuBiInfoAndSend(Long infoTemplateId, BigDecimal changeFuBi, String changeCause, Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.userFubiChangeInfoTemplateReplaceKeyWord(infoTemplateDTO2, changeFuBi, changeCause);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertUserPraiseFuBiInfoAndSend(Long infoTemplateId, BigDecimal changeFuBi, String changeCause,
			Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.userPraiseFubiChangeInfoTemplateReplaceKeyWord(infoTemplateDTO2, changeFuBi, changeCause);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertCompanyFuBiInfoAndSend(Long infoTemplateId, BigDecimal changeFuBi, String changeCause,
			List<Long> userIds) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.companyFubiChangeInfoTemplateReplaceKeyWord(infoTemplateDTO2, changeFuBi, changeCause);
			
			List<Long> sendWayIds = new ArrayList<>();
			InfoTemplateSendWayDTO infoTemplateSendWayDTO = new InfoTemplateSendWayDTO();
			infoTemplateSendWayDTO.setInfoTemplateId(infoTemplateId);
			infoTemplateSendWayDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			List<InfoTemplateSendWayDTO> list = infoTemplateSendWayReadService.findInfoTemplateSendWayAll(infoTemplateSendWayDTO);
			for (InfoTemplateSendWayDTO infoTemplateSendWayDTO2 : list) {
				sendWayIds.add(infoTemplateSendWayDTO2.getSendWayId());
			}
			// 保存消息
			Long infoId = infoWriteService.insertInfoWithTx(infoDTO, sendWayIds);
			infoDTO.setId(infoId);
			// 向每个用户发送消息
			for (Long userId : userIds) {
				infoDTO.setUserId(userId);
				// 发送消息
				sendInfo(infoDTO);
			}
			return infoTemplateId;
		}
		return infoTemplateId;
	}
	@Override
	public Long insertUserStateChangeInfoAndSend(Long infoTemplateId, String stateChangeCause, Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.userStateChangeInfoTemplateReplaceKeyWord(infoTemplateDTO2, stateChangeCause);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertUserDimissionInfoAndSend(Long infoTemplateId, String DimissionTime, String assetRecycleTime,
			Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.userDimissionInfoTemplateReplaceKeyWord(infoTemplateDTO2, DimissionTime,assetRecycleTime);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertRelevanceUserInfoAndSend(Long infoTemplateId,String mobile,String bindingTime, String mail,String companyName, Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.relevanceUserInfoTemplateReplaceKeyWord(infoTemplateDTO2,mobile,bindingTime, mail,companyName);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertUnbindUserInfoAndSend(Long infoTemplateId, String mobile, String unbindTime, String mail,
			Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.unbindUserInfoTemplateReplaceKeyWord(infoTemplateDTO2,mobile,unbindTime, mail);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			infoDTO.setMobile(mobile);
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertSendCouponInfoAndSend(Long infoTemplateId, String issuingParty, String couponName,
			Integer couponQuantity, String startTime, String stopTime, Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.sendCouponInfoTemplateReplaceKeyWord(infoTemplateDTO2,issuingParty,couponName, couponQuantity,startTime,stopTime);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertUpdateUserPassWordInfoAndSend(Long infoTemplateId, String mail, Long userId, String mobile) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.updateUserPassWordInfoTemplateReplaceKeyWord(infoTemplateDTO2,mail);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			if(EmptyUtil.isNotEmpty(mobile)){
				infoDTO.setMobile(mobile);
			}
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertUpdateUserPayPassWordInfoAndSend(Long infoTemplateId, String mail, Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.updateUserPayPassWordInfoTemplateReplaceKeyWord(infoTemplateDTO2,mail);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	@Override
	public Long insertSignActivityInfoAndSend(Long infoTemplateId, String activityName, String startTime, Long userId) {
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = InfoTemplateReplaceKeyWordUtil.signActivityInfoTemplateReplaceKeyWord(infoTemplateDTO2,activityName,startTime);
			
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	
	public Long insertAndSendMessage(Long infoTemplateId, Map<String, String> params, Long userId, String mobile) {
		if (infoTemplateId == null) {
			return null;
		}
		InfoTemplateDTO infoTemplateDTO = new InfoTemplateDTO();
		infoTemplateDTO.setId(infoTemplateId);
		InfoTemplateDTO infoTemplateDTO2 = infoTemplateReadService.findInfoTemplateById(infoTemplateDTO);
		
		InfoTemplateParameterDTO templateParamDTO = new InfoTemplateParameterDTO();
		templateParamDTO.setInfoTemplateId(infoTemplateId);
		List<InfoTemplateParameterDTO> infoTemplateParameterDTOList = infoTemplateParameterReadService.findInfoTemplateParameterAll(templateParamDTO);
		
		if(infoTemplateDTO2.getIsStart() == 1){
			InfoDTO infoDTO = constructInfo(infoTemplateDTO2, infoTemplateParameterDTOList, params);
			// 保存并发送消息
			infoDTO.setUserId(userId);
			infoDTO.setPlatformId(infoTemplateDTO2.getPlatformId());
			if(EmptyUtil.isNotEmpty(mobile)){
				infoDTO.setMobile(mobile);
			} else {
				UserDTO user = userReadService.findUserByID(userId);
				if (user != null) {
					infoDTO.setMobile(user.getMobile());
				} 
			}
			return insertSendInfo(infoTemplateId,infoDTO);
		}
		return infoTemplateId;
	}
	
	private InfoDTO constructInfo(InfoTemplateDTO infoTemplateDTO, List<InfoTemplateParameterDTO> infoTemplateParameterDTOList, Map<String, String> params) {
		InfoDTO infoDTO = new InfoDTO();
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getSystemInfo()))
			infoDTO.setSystemInfo(replaceParam(infoTemplateDTO.getSystemInfo(), infoTemplateParameterDTOList, params));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getInfoInform()))
			infoDTO.setInfoInform(replaceParam(infoTemplateDTO.getInfoInform(), infoTemplateParameterDTOList, params));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMoblieInfo()))
			infoDTO.setMoblieInfo(replaceParam(infoTemplateDTO.getMoblieInfo(), infoTemplateParameterDTOList, params));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getWeChatOfficialInfo()))
			infoDTO.setWeChatOfficialInfo(replaceParam(infoTemplateDTO.getMailInfo(), infoTemplateParameterDTOList, params));
		if(EmptyUtil.isNotEmpty(infoTemplateDTO.getMailInfo())) {
			infoDTO.setMailInfoTitle(replaceParam(infoTemplateDTO.getMailInfoTitle(), infoTemplateParameterDTOList, params));
			infoDTO.setMailInfo(replaceParam(infoTemplateDTO.getMailInfo(), infoTemplateParameterDTOList, params));
		}
		return infoDTO;
	}
	
	private String replaceParam(String source, List<InfoTemplateParameterDTO> infoTemplateParameterDTOList, Map<String, String> params) {
		if (EmptyUtil.isNotEmpty(infoTemplateParameterDTOList)) {
			for (int i = 0; i < infoTemplateParameterDTOList.size(); i++) {
				String paramName = infoTemplateParameterDTOList.get(i).getName();
				String paramVal = params.get(paramName);
				source = source.replaceFirst("【" + paramName + "】", paramVal == null ? "" : paramVal);
			}
		}
		return source;
	}
	
}

	