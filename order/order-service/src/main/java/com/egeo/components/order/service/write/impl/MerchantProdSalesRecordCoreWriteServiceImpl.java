package com.egeo.components.order.service.write.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.egeo.components.order.enums.ThirdConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.facade.ProductFacade;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.components.order.service.write.MerchantProdSalesRecordCoreWriteService;
import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.client.MerchantProdSalesRecordClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.MerchantProdSalesRecordDTO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;


@Service("merchantProdSalesRecordCoreWriteService")
public class MerchantProdSalesRecordCoreWriteServiceImpl  implements MerchantProdSalesRecordCoreWriteService {
	@Autowired
	private SoItemReadService soItemReadService;
	@Autowired
	private MerchantProdSalesRecordClient merchantProdSalesRecordReadService;
	@Autowired
	private CommodityProductUnitClient commodityProductUnitReadService;
	@Autowired
	private MerchantProdSalesRecordClient merchantProdSalesRecordWriteService;
	@Resource(name = "productFacade")
	private ProductFacade productFacade;
	
	@Override
	public boolean recordSalesVolume(Long soId) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		// 根据订单id查询订单项信息
		List<SoItemDTO> soItemList = soItemReadService.querySoItemBySoId(soId);
		for (SoItemDTO soItemDTO : soItemList) {
			// 根据puid查询pu销售信息
			MerchantProdSalesRecordDTO merchantProdSalesRecordDTO = new MerchantProdSalesRecordDTO();
			merchantProdSalesRecordDTO.setCommodityProductUnitId(soItemDTO.getPuId());
			String format2 = format.format(new Date());
			/*if(soItemDTO.isJd()) {
				continue;
			}*/
			merchantProdSalesRecordDTO.setSalesDate(format2);
			List<MerchantProdSalesRecordDTO> merchantProdSalesRecordList = merchantProdSalesRecordReadService.findMerchantProdSalesRecordAll(merchantProdSalesRecordDTO);
			if (EmptyUtil.isEmpty(merchantProdSalesRecordList)) {
				// 根据puid查询su信息spu信息
				MerchantProdSalesRecordDTO merchantProdSalesRecord = new MerchantProdSalesRecordDTO();
				if(soItemDTO.getSource()!=null && soItemDTO.getSource().intValue()==3) {
					JdProductDTO jdProductDTO = JSON.parseObject(soItemDTO.getSnapshot(),JdProductDTO.class);
					merchantProdSalesRecord.setCommodityProductUnitId(soItemDTO.getPuId());
					merchantProdSalesRecord.setStandardUnitId(soItemDTO.getPuId());
					merchantProdSalesRecord.setStandardProductUnitId(soItemDTO.getPuId());
					merchantProdSalesRecord.setMerchantId(6l);
					merchantProdSalesRecord.setSalesDate(format2);
					merchantProdSalesRecord.setSalesVolume(Long.valueOf(soItemDTO.getPuCount()));
					merchantProdSalesRecord.setChineseName(jdProductDTO.getName());
				}else if(soItemDTO.getSource()!=null && (soItemDTO.getSource().intValue()==4 || soItemDTO.getSource().intValue()== ThirdConst.Source.QM)){
					//蛋糕叔叔
					merchantProdSalesRecord.setCommodityProductUnitId(soItemDTO.getPuId());
					merchantProdSalesRecord.setStandardUnitId(soItemDTO.getPuId());
					merchantProdSalesRecord.setStandardProductUnitId(soItemDTO.getPuId());
					long merchantId=soItemDTO.getSource().intValue()== ThirdConst.Source.QM?ThirdConst.Merchant.QM:7L;
					merchantProdSalesRecord.setMerchantId(merchantId);
					merchantProdSalesRecord.setSalesDate(format2);
					merchantProdSalesRecord.setSalesVolume(Long.valueOf(soItemDTO.getPuCount()));
					merchantProdSalesRecord.setChineseName(soItemDTO.getPuName());
				}else {
					CommodityProductUnitDTO commodityProductUnitDTO = commodityProductUnitReadService.findSUSPUByPUId(soItemDTO.getPuId());
					if(EmptyUtil.isEmpty(commodityProductUnitDTO)){
						if(commodityProductUnitDTO==null) {
							throw new BusinessException("未查询到pu");
						}
					}
					merchantProdSalesRecord.setCommodityProductUnitId(soItemDTO.getPuId());
					merchantProdSalesRecord.setStandardUnitId(commodityProductUnitDTO.getStandardUnitId());
					merchantProdSalesRecord.setStandardProductUnitId(commodityProductUnitDTO.getStandardProductUnitId());
					merchantProdSalesRecord.setMerchantId(commodityProductUnitDTO.getMerchantId());
					merchantProdSalesRecord.setSalesDate(format2);
					merchantProdSalesRecord.setSalesVolume(Long.valueOf(soItemDTO.getPuCount()));
					merchantProdSalesRecord.setChineseName(commodityProductUnitDTO.getName());
				}
				merchantProdSalesRecordWriteService.insertMerchantProdSalesRecordWithTx(merchantProdSalesRecord);
			} else {
				// 根据id增加销售数量
				merchantProdSalesRecordWriteService.addSalesVolumeByIdWithTx(merchantProdSalesRecordList.get(0).getId(),Long.valueOf(soItemDTO.getPuCount()).longValue());
			}

		}
		return true;
	}

}

	