package com.egeo.components.config.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.service.read.HeadImportRecordsReadService;
import com.egeo.components.config.service.write.HeadImportRecordsWriteService;

@Controller
@RequestMapping("/client/config/headImportRecords") 
public class HeadImportRecordsController implements HeadImportRecordsClient{ 

	@Autowired
	private HeadImportRecordsReadService headImportRecordsReadService;
	@Autowired
	private HeadImportRecordsWriteService headImportRecordsWriteService;


	@Override
	@RequestMapping(value = "/queryRecordBySn", method = { RequestMethod.POST })
	@ResponseBody
	public HeadImportRecordsDTO queryRecordBySn(@RequestBody String sn) {
		return headImportRecordsReadService.queryRecordBySn(sn);
	} 
 
	@Override
	@RequestMapping(value = "/queryRecordsBySn", method = { RequestMethod.POST })
	@ResponseBody
	public List<HeadImportRecordsDTO> queryRecordsBySn(@RequestBody String sn) {
		return headImportRecordsReadService.queryRecordsBySn(sn);
	} 
 
	@Override
	@RequestMapping(value = "/findHeadImportRecordsAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(@RequestBody HeadImportRecordsDTO dto) {
		return headImportRecordsReadService.findHeadImportRecordsAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/insertHeadImportRecordsWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertHeadImportRecordsWithTx(@RequestBody HeadImportRecordsDTO dto) {
		return headImportRecordsWriteService.insertHeadImportRecordsWithTx(dto);
	} 
 
}