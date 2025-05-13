package com.egeo.components.user.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.VersionsClient;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.components.user.service.read.VersionsReadService;
import com.egeo.components.user.service.write.VersionsWriteService;

@Controller
@RequestMapping("/client/user/versions") 
public class VersionsController implements VersionsClient{ 

	@Autowired
	private VersionsReadService versionsReadService;
	@Autowired
	private VersionsWriteService versionsWriteService;


	@Override
	@RequestMapping(value = "/findVersionsById", method = { RequestMethod.POST })
	@ResponseBody
	public VersionsDTO findVersionsById(@RequestBody VersionsDTO dto) {
		return versionsReadService.findVersionsById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/queryVerisonByVersionCode", method = { RequestMethod.POST })
	@ResponseBody
	public VersionsDTO queryVerisonByVersionCode(@RequestParam("vCode") Integer vCode,@RequestParam("type")  Integer type,@RequestParam("user")  Integer user,@RequestParam("platformId")  Long platformId) {
		return versionsReadService.queryVerisonByVersionCode(vCode, type, user, platformId);
	} 
 
	@Override
	@RequestMapping(value = "/queryLatestOfficialVersion", method = { RequestMethod.POST })
	@ResponseBody
	public VersionsDTO queryLatestOfficialVersion(@RequestBody VersionsDTO dto) {
		return versionsReadService.queryLatestOfficialVersion(dto);
	} 
}
