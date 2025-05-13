package com.egeo.components.user.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.DownloadClient;
import com.egeo.components.user.service.read.DownloadReadService;
import com.egeo.components.user.service.write.DownloadWriteService;

@Controller
@RequestMapping("/client/user/download") 
public class DownloadController implements DownloadClient{ 

	@Autowired
	private DownloadReadService downloadReadService;
	@Autowired
	private DownloadWriteService downloadWriteService;


	@Override
	@RequestMapping(value = "/refreshDownloadDailyDownloadCount", method = { RequestMethod.POST })
	@ResponseBody
	public int refreshDownloadDailyDownloadCount() {
		return downloadWriteService.refreshDownloadDailyDownloadCount();
	} 
 
}