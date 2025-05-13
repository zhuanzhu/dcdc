package com.egeo.components.user.service.strategry.factory;

import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.manage.read.UserReadManage;
import com.egeo.components.user.po.UserPO;
import com.egeo.components.user.service.write.SendInfoStrategryFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SendMail;
import com.egeo.utils.SpringContextTool;
import com.egeo.utils.log.XLogger;
public class MailStrategryFactory  implements SendInfoStrategryFactory {

	private static final XLogger logger = XLogger.getLogger(MailStrategryFactory.class);
	private UserReadManage userReadManage;
	
	public MailStrategryFactory(UserReadManage userReadManage){
		this.userReadManage = userReadManage;
	}
	@Override
	public boolean sendInfo(InfoDTO infoDTO) {
		// 根据用户id查询用户信息
		UserPO userPO = userReadManage.findUserByID(infoDTO.getUserId());
		if(EmptyUtil.isNotEmpty(userPO) && EmptyUtil.isNotEmpty(userPO.getMail())){
			try {
				SendMail sendMail = SpringContextTool.getBean(SendMail.class);
				sendMail.sendMail(userPO.getMail(), infoDTO.getMailInfoTitle(), infoDTO.getMailInfo());
			} catch (Exception e) {
				logger.info("用户编号"+ infoDTO.getUserId() +"发送邮件通知失败：" + e.getMessage());
				return false;
			}
		}
			
		return true;
	}

}
