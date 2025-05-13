package com.egeo.components.user.service.strategry.factory;

import com.alibaba.fastjson.JSONArray;
import com.belerweb.social.bean.Result;
import com.belerweb.social.weixin.bean.Message;
import com.belerweb.social.weixin.bean.MsgType;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.manage.read.UserExtendReadManage;
import com.egeo.components.user.po.UserExtendPO;
import com.egeo.components.user.service.write.SendInfoStrategryFactory;
import com.egeo.components.utils.weixin.WeiXinUtil;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;
public class WeChatOfficialInfoStrategryFactory implements SendInfoStrategryFactory{

	private static final XLogger logger = XLogger.getLogger(WeChatOfficialInfoStrategryFactory.class);
	private UserExtendReadManage userExtendReadManage;
	
	public WeChatOfficialInfoStrategryFactory(UserExtendReadManage userExtendReadManage){
		this.userExtendReadManage = userExtendReadManage;
	}
	@Override
	public boolean sendInfo(InfoDTO infoDTO) {
		UserExtendPO userExtendPO = userExtendReadManage.findById(infoDTO.getUserId());
		if(EmptyUtil.isNotEmpty(userExtendPO) && EmptyUtil.isNotEmpty(userExtendPO.getWeixin())){
			Message message = new Message(MsgType.TEXT);
		    message.setToUser(userExtendPO.getWeixin());
		    message.setContent(infoDTO.getWeChatOfficialInfo());
		    Result<Boolean> result = WeiXinUtil.getWeixin(infoDTO.getPlatformId()).sendCustomMessage(message);
		    logger.info("微信通知：" + JSONArray.toJSONString(result));
		}
		return true;
		
	}

}
