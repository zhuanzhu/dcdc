package com.egeo.components.stock.service.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.MerchantProductWarehouseStockWriteService;
import com.egeo.components.stock.manage.write.MerchantProductWarehouseStockWriteManage;
import com.egeo.components.stock.converter.MerchantProductWarehouseStockConverter;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;

@Service("merchantProductWarehouseStockWriteService")
public class MerchantProductWarehouseStockWriteServiceImpl  implements MerchantProductWarehouseStockWriteService {
	@Autowired
	private MerchantProductWarehouseStockWriteManage merchantProductWarehouseStockWriteManage;

        @Override
        public Long saveMerchantProductWarehouseStockWithTx(MerchantProductWarehouseStockDTO dto) {
            
            return merchantProductWarehouseStockWriteManage.saveMerchantProductWarehouseStock(MerchantProductWarehouseStockConverter.toPO(dto));
        }

        //直接修改商品库存，不建议使用
        @Deprecated
        @Override
        public Long updateMerchantProductWarehouseStockWithTx(MerchantProductWarehouseStockDTO dto) {
            
            return merchantProductWarehouseStockWriteManage.updateMerchantProductWarehouseStock(MerchantProductWarehouseStockConverter.toPO(dto));
        }

        @Override
        public int deleteByProductIdWithTx(MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO) {
            
            return merchantProductWarehouseStockWriteManage.deleteByProductId(MerchantProductWarehouseStockConverter.toPO(merchantProductWarehouseStockDTO));
        }

		@Override
		public boolean lockMerchantProductStockWithTx(MerchantProductWarehouseStockDTO src) {
			return merchantProductWarehouseStockWriteManage.lockMerchantProductStockWithTx(converDTOToPO(src));
		}

		@Override
		public boolean batchLockMerchantProductStockWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList) {
			List<MerchantProductWarehouseStockPO> mpsPoList = new ArrayList<MerchantProductWarehouseStockPO>();
			for(MerchantProductWarehouseStockDTO mpsdto:mpsDtoList){
				mpsPoList.add(converDTOToPO(mpsdto));
			}

			return merchantProductWarehouseStockWriteManage.batchLockMerchantProductStockWithTx(mpsPoList);
		}

		private MerchantProductWarehouseStockPO converDTOToPO(MerchantProductWarehouseStockDTO src) {
			MerchantProductWarehouseStockPO tar = new MerchantProductWarehouseStockPO();
			tar.setSkuId(src.getSkuId());
			tar.setMerchantId(src.getMerchantId());
			tar.setRealFrozenStockNum(src.getRealFrozenStockNum());
			return tar;
		}

		
		//增加商品库存
		@Override
		public boolean addMerchantProductStockWithTx(MerchantProductWarehouseStockDTO src) {
			MerchantProductWarehouseStockPO tar = new MerchantProductWarehouseStockPO();
			tar.setSkuId(src.getSkuId());
			tar.setMerchantId(src.getMerchantId());
			tar.setRealStockNum(src.getRealStockNum());
			return merchantProductWarehouseStockWriteManage.addMerchantProductStockWithTx(tar);
		
		}

		@Override
		public boolean batchChangeStockStatusLockToRealWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList) {
			List<MerchantProductWarehouseStockPO> mpsPoList = new ArrayList<MerchantProductWarehouseStockPO>();
			for(MerchantProductWarehouseStockDTO mpsdto:mpsDtoList){
				mpsPoList.add(converDTOToPO(mpsdto));
			}
			return merchantProductWarehouseStockWriteManage.batchChangeStockStatusLockToRealWithTx(mpsPoList);
		}

		@Override
		public boolean batchUnlockItemsStockWithTx(List<MerchantProductWarehouseStockDTO> mpsDtoList) {
			List<MerchantProductWarehouseStockPO> mpsPoList = new ArrayList<MerchantProductWarehouseStockPO>();
			for(MerchantProductWarehouseStockDTO mpsdto:mpsDtoList){
				mpsPoList.add(converDTOToPO(mpsdto));
			}
			return merchantProductWarehouseStockWriteManage.batchUnlockItemsStockWithTx(mpsPoList);
		}

		@Override
		public int deleteByParaWithTx(MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO) {
			
			return merchantProductWarehouseStockWriteManage.deleteByPara(MerchantProductWarehouseStockConverter.toPO(merchantProductWarehouseStockDTO));
		}

		@Override
		public int comeMerchantProductStockWithTx(MerchantProductWarehouseStockDTO src) {
			MerchantProductWarehouseStockPO tar = new MerchantProductWarehouseStockPO();
			tar.setSkuId(src.getSkuId());
			tar.setMerchantId(src.getMerchantId());
			tar.setRealStockNum(src.getRealStockNum());
			return merchantProductWarehouseStockWriteManage.comeMerchantProductStockWithTx(tar);
		}
        
		
        
}
	