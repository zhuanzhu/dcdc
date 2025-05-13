package com.egeo.components.user.controller.client;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.InfoClient;
import com.egeo.components.user.dto.InfoDTO;
import com.egeo.components.user.service.read.InfoReadService;
import com.egeo.components.user.service.write.InfoWriteService;

@Controller
@RequestMapping("/client/user/info") 
public class InfoController implements InfoClient{ 

	@Autowired
	private InfoReadService infoReadService;
	@Autowired
	private InfoWriteService infoWriteService;


	@Override
	@RequestMapping(value = "/findUserInfoForMsgBox", method = { RequestMethod.POST })
	@ResponseBody
	public List<InfoDTO> findUserInfoForMsgBox(@RequestParam("userId") Long userId,@RequestParam("isRead")  Integer isRead,@RequestParam("type")  Integer type,@RequestParam("platformId")  Long platformId,@RequestParam("createTime")  Date createTime,@RequestParam("count")  Integer count) {
		return infoReadService.findUserInfoForMsgBox(userId, isRead, type, platformId, createTime, count);
	} 
} 
