package com.egeo.components.user.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.user.dto.StoreAdminDTO;
import com.egeo.components.user.service.read.ChannelActivityReadService;
import com.egeo.components.user.service.read.QrCodeReadService;
import com.egeo.components.user.service.read.StoreAdminReadService;
import com.egeo.components.user.service.write.StoreAdminWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class StoreAdminFacade {
	
	@Resource
	private StoreAdminReadService storeAdminReadService;
	
	@Resource
	private StoreAdminWriteService storeAdminWriteService;
	
	@Autowired
	private StoreClient storeReadService;
	
	@Resource
	private ChannelActivityReadService channelActivityReadService;
	
	@Resource
	private QrCodeReadService qrCodeReadService;
	
	
	public StoreAdminDTO findStoreAdminById(StoreAdminDTO dto){
		
		return storeAdminReadService.findStoreAdminById(dto);
	}

	public PageResult<StoreAdminDTO> findStoreAdminOfPage(StoreAdminDTO dto,Pagination page){
		
		return storeAdminReadService.findStoreAdminOfPage(dto, page);
		
	}

	public Map<String, Object> findStoreAdminAll(StoreAdminDTO dto){
		Map<String, Object> data = new HashMap<>();
		List<StoreAdminDTO> storeAdminList = storeAdminReadService.findStoreAdminAll(dto);
		List<Map<String, Object>> stores = new ArrayList<>(storeAdminList.size());
		for (StoreAdminDTO storeAdminDTO : storeAdminList) {
			Map<String, Object> map = new HashMap<>();
			StoreDTO storeDTO = new StoreDTO();
			storeDTO.setId(storeAdminDTO.getStoreId());
			storeDTO.setPlatformId(dto.getPlatformId());
			StoreDTO storeDTO2 = storeReadService.findStoreById(storeDTO);
			// 根据门店id查询门店信息
			map.put("id", storeDTO2.getId());
			map.put("name", storeDTO2.getName());
			// 拼接门店详情地址
			String detailAddress = jointDetailAddress(storeDTO2);
			map.put("detailAddress", detailAddress);
			map.put("storeMenuTreeId", storeDTO2.getStoreMenuTreeId());
			map.put("activityCode", storeDTO2.getActivityCode());
			// 根据门店id查询总店信息
			StoreDTO store = storeReadService.findHeadStoreByStoreId(storeDTO2.getId());
			map.put("headStoreId", store.getId());
			map.put("headStoreName", store.getName());
			map.put("headActivityCode", storeDTO2.getActivityCode());
			stores.add(map);
		}
		data.put("stores", stores);
		return data;
		
	}
	/**
	 * 拼接门店详情地址
	 * @param storeDTO2
	 * @return
	 */
	private String jointDetailAddress(StoreDTO storeDTO2) {
		StringBuffer detailAddress = new StringBuffer();
		detailAddress.append(storeDTO2.getProvince());
		detailAddress.append(storeDTO2.getCity());
		detailAddress.append(storeDTO2.getCounty());
		detailAddress.append(storeDTO2.getDetailAddress());
		return detailAddress.toString();
	}

	public Long insertStoreAdminWithTx(StoreAdminDTO dto){
		
		return storeAdminWriteService.insertStoreAdminWithTx(dto);
	}

	public int updateStoreAdminWithTx(StoreAdminDTO dto){
		
		return storeAdminWriteService.updateStoreAdminWithTx(dto);
	}

	public int deleteStoreAdminWithTx(StoreAdminDTO dto){
		
		return storeAdminWriteService.deleteStoreAdminWithTx(dto);
		
	}

}
	