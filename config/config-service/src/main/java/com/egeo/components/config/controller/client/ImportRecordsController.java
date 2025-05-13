package com.egeo.components.config.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.service.read.ImportRecordsReadService;
import com.egeo.components.config.service.write.ImportRecordsWriteService;

@Controller
@RequestMapping("/client/config/importRecords") 
public class ImportRecordsController implements ImportRecordsClient{ 

	@Autowired
	private ImportRecordsReadService importRecordsReadService;
	@Autowired
	private ImportRecordsWriteService importRecordsWriteService;


	@Override
	@RequestMapping(value = "/insertImportRecordsWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertImportRecordsWithTx(@RequestBody ImportRecordsDTO dto) {
		return importRecordsWriteService.insertImportRecordsWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/deleteBySn", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteBySn(@RequestBody String sn) {
		return importRecordsWriteService.deleteBySn(sn);
	} 
 
	@Override
	@RequestMapping(value = "/deleteImportRecordsWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteImportRecordsWithTx(@RequestBody ImportRecordsDTO dto) {
		return importRecordsWriteService.deleteImportRecordsWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/queryRecordTempBySn", method = { RequestMethod.POST })
	@ResponseBody
	public ImportRecordsDTO queryRecordTempBySn(@RequestBody String sn) {
		return importRecordsReadService.queryRecordTempBySn(sn);
	} 
 
	@Override
	@RequestMapping(value = "/findImportRecordsById", method = { RequestMethod.POST })
	@ResponseBody
	public ImportRecordsDTO findImportRecordsById(@RequestBody ImportRecordsDTO dto) {
		return importRecordsReadService.findImportRecordsById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findImportRecordsAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ImportRecordsDTO> findImportRecordsAll(@RequestBody ImportRecordsDTO dto) {
		return importRecordsReadService.findImportRecordsAll(dto);
	} 
 
}