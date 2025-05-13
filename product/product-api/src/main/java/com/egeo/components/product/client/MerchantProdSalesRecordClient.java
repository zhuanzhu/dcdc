package com.egeo.components.product.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;


@FeignClient(name = "service-product-fgj",contextId="MerchantProdSalesRecordClient")
public interface MerchantProdSalesRecordClient {
	@RequestMapping(value = { "/client/product/merchantProdSalesRecord/findMerchantProdSalesRecordAll" }, method = { RequestMethod.POST })
	public List<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordAll(MerchantProdSalesRecordDTO dto);

	@RequestMapping(value = { "/client/product/merchantProdSalesRecord/insertMerchantProdSalesRecordWithTx" }, method = { RequestMethod.POST })
	public Long insertMerchantProdSalesRecordWithTx(MerchantProdSalesRecordDTO dto);

	
	@RequestMapping(value = { "/client/product/merchantProdSalesRecord/addSalesVolumeByIdWithTx" }, method = { RequestMethod.POST })
	public int addSalesVolumeByIdWithTx(@RequestParam("merchantProdSalesRecordId") Long merchantProdSalesRecordId,@RequestParam("salesVolume")  Long salesVolume);
} 
 
