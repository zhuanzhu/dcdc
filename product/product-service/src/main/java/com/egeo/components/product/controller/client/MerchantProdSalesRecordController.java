package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.MerchantProdSalesRecordClient;
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.components.product.service.read.MerchantProdSalesRecordReadService;
import com.egeo.components.product.service.write.MerchantProdSalesRecordWriteService;

@Controller
@RequestMapping("/client/product/merchantProdSalesRecord") 
public class MerchantProdSalesRecordController implements MerchantProdSalesRecordClient{ 

	@Autowired
	private MerchantProdSalesRecordReadService merchantProdSalesRecordReadService;
	@Autowired
	private MerchantProdSalesRecordWriteService merchantProdSalesRecordWriteService;


	@Override
	@RequestMapping(value = "/findMerchantProdSalesRecordAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<MerchantProdSalesRecordDTO> findMerchantProdSalesRecordAll(@RequestBody MerchantProdSalesRecordDTO dto) {
		// TODO Auto-generated method stub
		return merchantProdSalesRecordReadService.findMerchantProdSalesRecordAll(dto);
	}


	@Override
	@RequestMapping(value = "/insertMerchantProdSalesRecordWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertMerchantProdSalesRecordWithTx(@RequestBody MerchantProdSalesRecordDTO dto) {
		// TODO Auto-generated method stub
		return merchantProdSalesRecordWriteService.insertMerchantProdSalesRecordWithTx(dto);
	}


	@Override
	@RequestMapping(value = "/addSalesVolumeByIdWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int addSalesVolumeByIdWithTx(@RequestParam("merchantProdSalesRecordId") Long merchantProdSalesRecordId,@RequestParam("salesVolume")  Long salesVolume) {
		// TODO Auto-generated method stub
		return merchantProdSalesRecordWriteService.addSalesVolumeByIdWithTx(merchantProdSalesRecordId, salesVolume);
	} 
 
} 
