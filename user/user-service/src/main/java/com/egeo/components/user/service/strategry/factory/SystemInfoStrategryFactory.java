package com.egeo.components.user.service.strategry.factory;

import com.egeo.components.user.common.PushMessageNew;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.manage.read.UserExtendReadManage;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.service.write.SendInfoStrategryFactory;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
public class SystemInfoStrategryFactory implements SendInfoStrategryFactory{
	private static final XLogger logger = XLogger.getLogger(SystemInfoStrategryFactory.class);
	private UserExtendReadManage userExtendReadManage;
	
	public SystemInfoStrategryFactory(UserExtendReadManage userExtendReadManage){
		this.userExtendReadManage = userExtendReadManage;
	}
	@Override
	public boolean sendInfo(InfoDTO infoDTO) {
		// 根据用户id查询百度云推送ChannelId
		UserExtendPO userExtendPO = userExtendReadManage.findById(infoDTO.getUserId());
		if(EmptyUtil.isNotEmpty(userExtendPO.getBaiduChannelId()) && EmptyUtil.isNotEmpty(userExtendPO.getDeviceType())){
			try {
				if(userExtendPO.getDeviceType() == 0){
					logger.info("安卓设备类型消息:" + infoDTO.getSystemInfo());
					PushMessageNew.pushMsgToSingleDevice(userExtendPO.getBaiduChannelId(), 3600, 1, "大厨管家系统消息通知", infoDTO.getSystemInfo(), 0, "www.baidu.com", 3, infoDTO.getSystemInfo(),"");
				}else if(userExtendPO.getDeviceType() == 1){
					/*try {
			            Thread.sleep(5000);
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }*/
					logger.info("IOS设备类型消息:" + infoDTO.getSystemInfo());
					PushMessageNew.pushMsgToSingleDevice(userExtendPO.getBaiduChannelId(), 3600, 1, "大厨管家系统消息通知", infoDTO.getSystemInfo(), 0, "www.baidu.com", 4, infoDTO.getSystemInfo(),"");
				}
			} catch (Exception e) {
				logger.info("百度云推送发送系统消息失败：" + e.getMessage());
				return false;
			}
			
		}
		return true;
	}

}
