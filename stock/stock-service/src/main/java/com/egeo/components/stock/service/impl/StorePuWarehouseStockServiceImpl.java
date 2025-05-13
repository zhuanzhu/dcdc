package com.egeo.components.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.client.SoDeviceClient;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.product.client.StoreProductUnitClient;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.components.stock.service.StorePuWarehouseStockService;
import com.egeo.components.stock.service.write.StorePuWarehouseStockWriteService;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.UserExtendDTO;
import com.egeo.utils.EmptyUtil;
@Service("storePuWarehouseStockService")
public class StorePuWarehouseStockServiceImpl implements StorePuWarehouseStockService {
	@Autowired
	private StorePuWarehouseStockWriteService storePuWarehouseStockWriteService;
	@Autowired
	private StoreProductUnitClient storeProductUnitReadService;
	@Autowired
	private SoDeviceClient soDeviceReadService;
	@Autowired
	private UserExtendClient userExtendReadService;
	
	
	public StorePuWarehouseStockServiceImpl() {
		super();
	}

	public StorePuWarehouseStockServiceImpl(StorePuWarehouseStockWriteService storePuWarehouseStockWriteService,
			StoreProductUnitClient storeProductUnitReadService) {
		super();
		this.storePuWarehouseStockWriteService = storePuWarehouseStockWriteService;
		this.storeProductUnitReadService = storeProductUnitReadService;
	}

	@Override
	public Boolean updateStorePuWarehouseStock(int type,SoDTO sodto, List<SoItemDTO> soItems) {
		if(EmptyUtil.isNotEmpty(sodto.getStoreId()) && sodto.getStoreId() != 1L && sodto.getStoreId() != 2L){
			// 更新门店pu库存信息，排除1福管加总店和2慢有优总店
			List<StorePuStockRunningWaterDTO> storePuStockRunningWaterDTOs = new ArrayList<>(soItems.size());
			for (SoItemDTO item : soItems) {
				StorePuStockRunningWaterDTO storePuStockRunningWaterDTO = new StorePuStockRunningWaterDTO();
				// 根据门店id和puid查询门店pu信息
				StoreProductUnitDTO storeProductUnitDTO = new StoreProductUnitDTO();
				storeProductUnitDTO.setStoreId(sodto.getStoreId());
				storeProductUnitDTO.setCommodityProductUnitId(item.getPuId());
				List<StoreProductUnitDTO> storeProductUnitList = storeProductUnitReadService.findStoreProductUnitAll(storeProductUnitDTO);
				// 总店puid不存在门店pu库存，不进行库存冻结
				if(EmptyUtil.isNotEmpty(storeProductUnitList)){
					// 根据门店puid查询门店pu信息
					StoreProductUnitDTO storeProductUnitDTO2 = new StoreProductUnitDTO();
					storeProductUnitDTO2.setId(storeProductUnitList.get(0).getId());
					StoreProductUnitDTO storeProductUnitDTO3 = storeProductUnitReadService.findStoreProductUnitById(storeProductUnitDTO2);
					SoDeviceDTO soDeviceDTO = new SoDeviceDTO();
					soDeviceDTO.setOrderId(sodto.getId());
					List<SoDeviceDTO> soDeviceDTOList = soDeviceReadService.findSoDeviceAll(soDeviceDTO);
					UserExtendDTO userExtendDTO = userExtendReadService.findById(sodto.getUserId());
					if(EmptyUtil.isNotEmpty(storeProductUnitDTO3)){
						storePuStockRunningWaterDTO.setStoreProductUnitId(storeProductUnitDTO3.getId());
						storePuStockRunningWaterDTO.setCommodityProductUnitName(storeProductUnitDTO3.getCommodityProductUnitName());
						storePuStockRunningWaterDTO.setProductUnitSerialNumber(storeProductUnitDTO3.getProductUnitSerialNumber());
						storePuStockRunningWaterDTO.setOrderCode(sodto.getOrderCode());
						storePuStockRunningWaterDTO.setPlatformId(sodto.getPlatformId());
						storePuStockRunningWaterDTO.setStockChange(Long.valueOf(item.getPuCount()));
						storePuStockRunningWaterDTO.setType(type);
						storePuStockRunningWaterDTO.setCreateUserid(sodto.getUserId());
						if (EmptyUtil.isNotEmpty(soDeviceDTOList)) {
							storePuStockRunningWaterDTO.setCreateUserip(soDeviceDTOList.get(0).getIp());
							storePuStockRunningWaterDTO.setCreateUsermac(soDeviceDTOList.get(0).getDeviceId());
						}
						if (EmptyUtil.isNotEmpty(userExtendDTO)) {
							storePuStockRunningWaterDTO.setCreateUsername(userExtendDTO.getName());
						}
						storePuStockRunningWaterDTOs.add(storePuStockRunningWaterDTO);
					}
				}
			}
			storePuWarehouseStockWriteService.updateStorePuWarehouseStock(storePuStockRunningWaterDTOs,type);
		}
		return true;
	}

}
