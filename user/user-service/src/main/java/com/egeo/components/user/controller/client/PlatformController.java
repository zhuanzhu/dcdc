package com.egeo.components.user.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.user.client.PlatformClient;
import com.egeo.components.user.dto.PlatformDTO;
import com.egeo.components.user.service.read.PlatformReadService;
import com.egeo.components.user.service.write.PlatformWriteService;

@Controller
@RequestMapping("/client/user/platform") 
public class PlatformController implements PlatformClient{ 

	@Autowired
	private PlatformReadService platformReadService;
	@Autowired
	private PlatformWriteService platformWriteService;


	@Override
	@RequestMapping(value = "/findAllPlatform", method = { RequestMethod.POST })
	@ResponseBody
	public List<PlatformDTO> findAllPlatform() {
		return platformReadService.findAllPlatform();
	}


	@Override
	@RequestMapping(value = "/find", method = { RequestMethod.POST })
	@ResponseBody
	public PlatformDTO find(@RequestBody Long id) {
		// TODO Auto-generated method stub
		return platformReadService.find(id);
	} 
 
}