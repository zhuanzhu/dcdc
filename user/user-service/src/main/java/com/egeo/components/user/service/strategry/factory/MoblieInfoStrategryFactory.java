package com.egeo.components.user.service.strategry.factory;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.manage.read.UserReadManage;
import com.egeo.components.user.service.write.SendInfoStrategryFactory;
import com.egeo.utils.SMSSender;
import com.egeo.utils.log.XLogger;
public class MoblieInfoStrategryFactory  implements SendInfoStrategryFactory{
	private static final XLogger logger = XLogger.getLogger(MoblieInfoStrategryFactory.class);

	private SMSSender sMSSender;
	private UserReadManage userReadManage;
	
	public MoblieInfoStrategryFactory(UserReadManage userReadManage,SMSSender sMSSender){
		this.userReadManage = userReadManage;
		this.sMSSender = sMSSender;
	}
	@Override
	public boolean sendInfo(InfoDTO infoDTO) {
		Long platformId = infoDTO.getPlatformId();
		String platformNmae = null;
        if(platformId.equals(PlatformKeyConstant.MYY_PLATFORM_ID)){
        	platformNmae = PlatformKeyConstant.MYY_PLATFORM_NAME;
        }else if(platformId.equals(PlatformKeyConstant.FGJ_PLATFORM_ID)){
        	if(infoDTO.getInfoHead()!=null && infoDTO.getInfoHead().equals(PlatformKeyConstant.BAI_ER_MESSAGE_HEAD_NAME)){
				platformNmae = PlatformKeyConstant.BAI_ER_MESSAGE_HEAD_NAME;
			}else {
				platformNmae = PlatformKeyConstant.FGJ_PLATFORM_NAME;
			}
        }
		/*// 根据用户id查询用户信息
		UserPO userPO = userReadManage.findUserByID(infoDTO.getUserId());
		if(EmptyUtil.isNotEmpty(userPO) && EmptyUtil.isNotEmpty(userPO.getMobile())){
			String commonSend = sMSSender.commonSend(userPO.getMobile(), infoDTO.getMoblieInfo(),platformNmae);
			logger.info("用户编号：{},手机号:{}发送短信：{}",infoDTO.getUserId(),infoDTO.getMobile(),commonSend);
		}*/
        String commonSend = sMSSender.commonSend(infoDTO.getMobile(), infoDTO.getMoblieInfo(),platformNmae);
		logger.info("用户编号：{},手机号:{}发送短信：{}",infoDTO.getUserId(),infoDTO.getMobile(),commonSend);
		return true;
		
	}

}
