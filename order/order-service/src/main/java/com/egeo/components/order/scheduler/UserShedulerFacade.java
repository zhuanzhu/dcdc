package com.egeo.components.order.scheduler;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.user.client.DownloadClient;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.WorkWeChatUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserShedulerFacade {

	/*@Autowired
	private UserWelfareReadService uwrService;

	@Autowired
	private UserWelfareWriteService uwwService;*/
	/*
	@Autowired
	private ChannelWriteService channelWriteService;*/
	
	@Autowired
	private DownloadClient downloadWriteService;
/*
	@Autowired
	private CompanyCoreReadService companyCoreReadService;
	@Autowired
	private CompanyReadService companyReadService;*/

	/*public void refreshUserDayPraiseCount() {

		uwwService.refreshUserDayPraiseCount();
	}

	public void refreshUserMonthPraiseCount() {
		uwwService.refreshUserMonthPraiseCount();
	}*/
	
	public void refreshDownloadDailyDownloadCount(){
		downloadWriteService.refreshDownloadDailyDownloadCount();
	}
}
