package com.egeo.components.config.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.config.dto.ImportRecordsDTO;


@FeignClient(name = "service-config-fgj",contextId="ImportRecordsClient")
public interface ImportRecordsClient {

	@RequestMapping(value = { "/client/config/importRecords/insertImportRecordsWithTx" }, method = { RequestMethod.POST }) 
	public Long insertImportRecordsWithTx(ImportRecordsDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/importRecords/deleteBySn" }, method = { RequestMethod.POST }) 
	public int deleteBySn(String sn); 
 
 
	@RequestMapping(value = { "/client/config/importRecords/deleteImportRecordsWithTx" }, method = { RequestMethod.POST }) 
	public int deleteImportRecordsWithTx(ImportRecordsDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/importRecords/queryRecordTempBySn" }, method = { RequestMethod.POST }) 
	public ImportRecordsDTO queryRecordTempBySn(String sn); 
 
 
	@RequestMapping(value = { "/client/config/importRecords/findImportRecordsById" }, method = { RequestMethod.POST }) 
	public ImportRecordsDTO findImportRecordsById(ImportRecordsDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/importRecords/findImportRecordsAll" }, method = { RequestMethod.POST }) 
	public List<ImportRecordsDTO> findImportRecordsAll(ImportRecordsDTO dto); 
 
 
}