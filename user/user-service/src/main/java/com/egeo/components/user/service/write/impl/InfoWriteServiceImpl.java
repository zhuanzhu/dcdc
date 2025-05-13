package com.egeo.components.user.service.write.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.InfoConverter;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.manage.read.InfoSendWayReadManage;
import com.egeo.components.user.manage.read.UserExtendReadManage;
import com.egeo.components.user.manage.read.UserReadManage;
import com.egeo.components.user.manage.write.InfoWriteManage;
import com.egeo.components.user.manage.write.UserInfoWriteManage;
import com.egeo.components.user.po.InfoPO;
import com.egeo.components.user.po.InfoSendWayPO;
import com.egeo.components.user.po.UserInfoPO;
import com.egeo.components.user.service.strategry.factory.InfoInformStrategryFactory;
import com.egeo.components.user.service.strategry.factory.MailStrategryFactory;
import com.egeo.components.user.service.strategry.factory.MoblieInfoStrategryFactory;
import com.egeo.components.user.service.strategry.factory.SystemInfoStrategryFactory;
import com.egeo.components.user.service.strategry.factory.WeChatOfficialInfoStrategryFactory;
import com.egeo.components.user.service.write.InfoWriteService;
import com.egeo.components.user.service.write.SendInfoStrategryFactory;
/*import com.egeo.components.utils.EmptyUtil;
import com.egeo.components.utils.SMSSender;*/
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SMSSender;

@Service("infoWriteService")
public class InfoWriteServiceImpl implements InfoWriteService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoWriteManage infoWriteManage;
	
	@Autowired
	private InfoSendWayReadManage infoSendWayReadManage;
	
	@Autowired
	private UserInfoWriteManage userInfoWriteManage;
	
	@Autowired
	private UserReadManage userReadManage;
	
	@Autowired
	private UserExtendReadManage userExtendReadManage;
	
	@Autowired
	private SMSSender sMSSender;

	@Override
	public Long insertInfoWithTx(InfoDTO dto,List<Long> sendWayIds) {
		InfoPO po = InfoConverter.toPO(dto);
		Long rt = infoWriteManage.insertInfoWithTx(po,sendWayIds);		
		return rt;
	}

	@Override
	public int updateInfoWithTx(InfoDTO dto) {
		InfoPO po = InfoConverter.toPO(dto);
		int rt = infoWriteManage.updateInfoWithTx(po);		
		return rt;
	}

	@Override
	public int deleteInfoWithTx(InfoDTO dto) {
		InfoPO po = InfoConverter.toPO(dto);
		int rt = infoWriteManage.deleteInfoWithTx(po);		
		return rt;
	}

	@Override
	public int sendInfoWithTx(InfoDTO infoDTO) {
		InfoSendWayPO infoSendWayPO = new InfoSendWayPO();
		infoSendWayPO.setInfoTemplateId(infoDTO.getId());
		List<InfoSendWayPO> list = infoSendWayReadManage.findInfoSendWayAll(infoSendWayPO);
		for (InfoSendWayPO infoSendWayPO2 : list) {
			SendInfoStrategryFactory systemInfoStrategryFactory = null;
			// 用户消息类型：1、系统通知 2、消息通知 3、短信 4、公众号推送 5、邮件
			if(infoSendWayPO2.getSendWayId().equals(1L)){
				systemInfoStrategryFactory = new SystemInfoStrategryFactory(userExtendReadManage);
			}else if(infoSendWayPO2.getSendWayId().equals(2L)){
				systemInfoStrategryFactory = new InfoInformStrategryFactory(userExtendReadManage);
			}else if(infoSendWayPO2.getSendWayId().equals(3L)){
				systemInfoStrategryFactory = new MoblieInfoStrategryFactory(userReadManage,sMSSender);
			}else if(infoSendWayPO2.getSendWayId().equals(4L)){
				systemInfoStrategryFactory = new WeChatOfficialInfoStrategryFactory(userExtendReadManage);
			}else if(infoSendWayPO2.getSendWayId().equals(5L)){
				systemInfoStrategryFactory = new MailStrategryFactory(userReadManage);
			}
			if(EmptyUtil.isNotEmpty(systemInfoStrategryFactory)){
				systemInfoStrategryFactory.sendInfo(infoDTO);
			}
		}
		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setInfoId(infoDTO.getId());
		userInfoPO.setUserId(infoDTO.getUserId());
		userInfoPO.setPlatformId(infoDTO.getPlatformId());
		userInfoWriteManage.insertUserInfoWithTx(userInfoPO);
		return 1;
	}
}
	