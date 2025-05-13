package com.egeo.components.config.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.config.dto.HeadImportRecordsDTO;


@FeignClient(name = "service-config-fgj",contextId="HeadImportRecordsClient")
public interface HeadImportRecordsClient {

	@RequestMapping(value = { "/client/config/headImportRecords/queryRecordBySn" }, method = { RequestMethod.POST }) 
	public HeadImportRecordsDTO queryRecordBySn(String sn); 
 
 
	@RequestMapping(value = { "/client/config/headImportRecords/queryRecordsBySn" }, method = { RequestMethod.POST }) 
	public List<HeadImportRecordsDTO> queryRecordsBySn(String sn); 
 
 
	@RequestMapping(value = { "/client/config/headImportRecords/findHeadImportRecordsAll" }, method = { RequestMethod.POST }) 
	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/headImportRecords/insertHeadImportRecordsWithTx" }, method = { RequestMethod.POST }) 
	public Long insertHeadImportRecordsWithTx(HeadImportRecordsDTO dto); 
 
 
}