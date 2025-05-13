package com.egeo.components.stock.service.write;

import java.util.List;

import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;

public interface MerchantProductWarehouseStockWriteService {

    Long saveMerchantProductWarehouseStockWithTx(MerchantProductWarehouseStockDTO dto);

    Long updateMerchantProductWarehouseStockWithTx(MerchantProductWarehouseStockDTO dto);

    int deleteByProductIdWithTx(MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO);

	boolean lockMerchantProductStockWithTx(MerchantProductWarehouseStockDTO mpsDto);

	boolean batchLockMerchantProductStockWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList);
	//增加商品库存
	boolean addMerchantProductStockWithTx(MerchantProductWarehouseStockDTO mpsDto);

	boolean batchChangeStockStatusLockToRealWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList);

	boolean batchUnlockItemsStockWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList);

	int deleteByParaWithTx(MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO);

	int comeMerchantProductStockWithTx(MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO2);

	
}
	