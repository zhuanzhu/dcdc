package com.egeo.components.stock.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.MerchantProductWarehouseStockWriteManage;
import com.egeo.components.stock.dao.write.MerchantProductWarehouseStockWriteDAO;
import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantProductWarehouseStockWriteManageImpl implements MerchantProductWarehouseStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductWarehouseStockWriteDAO merchantProductWarehouseStockWriteDAO;
	
        @Override
        public Long saveMerchantProductWarehouseStock(MerchantProductWarehouseStockPO po) {
            merchantProductWarehouseStockWriteDAO.insert(po);
            return po.getId();
        }

        @Override
        public Long updateMerchantProductWarehouseStock(MerchantProductWarehouseStockPO po) {
            merchantProductWarehouseStockWriteDAO.update(po);
            return po.getId();
        }

        @Override
        public int deleteByProductId(MerchantProductWarehouseStockPO po) {
            
            return merchantProductWarehouseStockWriteDAO.deleteByProductId(po);
        }

		@Override
		public boolean lockMerchantProductStockWithTx(MerchantProductWarehouseStockPO po) {
			boolean  result = false;
			int rt = merchantProductWarehouseStockWriteDAO.lockMerchantProductStockWithTx(po);
			if(rt ==1){
				result = true;
			}else if(rt ==0){
				throw new BusinessException("商品id = " + po.getSkuId() + "的库存不足，锁定库存失败");
			}
			return result;
		}

		@Override
		public boolean batchLockMerchantProductStockWithTx(List<MerchantProductWarehouseStockPO> mpsPoList) {
			boolean  result = true;
			for(MerchantProductWarehouseStockPO po:mpsPoList){
				if(!lockMerchantProductStockWithTx(po)){
					result = false;
				}
			}
			
			return result;
		}

		@Override
		public boolean addMerchantProductStockWithTx(MerchantProductWarehouseStockPO po) {
			boolean  result = false;
			int rt = merchantProductWarehouseStockWriteDAO.addMerchantProductStockWithTx(po);
			if(rt ==1){
				result = true;
			}else if(rt ==0){
				throw new BusinessException("增加商品id = " + po.getSkuId() + "失败");
			}
			return result;
		}

		@Override
		public boolean batchChangeStockStatusLockToRealWithTx(List<MerchantProductWarehouseStockPO> mpsPoList) {

			boolean  result = true;
			for(MerchantProductWarehouseStockPO po:mpsPoList){
				if(!changeStockStatusLockToRealWithTx(po)){
					result = false;
				}
			}
			
			return result;
		
		}
		
		@Override
		public boolean changeStockStatusLockToRealWithTx(MerchantProductWarehouseStockPO po) {
			boolean  result = false;
			int rt = merchantProductWarehouseStockWriteDAO.changeStockStatusLockToRealWithTx(po);
			if(rt ==1){
				result = true;
			}else if(rt ==0){
				throw new BusinessException("商品id = " + po.getSkuId() + "从锁定到减少库存存在异常");
			}
			return result;
		}

		@Override
		public boolean batchUnlockItemsStockWithTx(List<MerchantProductWarehouseStockPO> mpsPoList) {
			boolean  result = true;
			for(MerchantProductWarehouseStockPO po:mpsPoList){
				if(!unlockItemsStockWithTx(po)){
					result = false;
				}
			}
			
			return result;
		}

		@Override
		public boolean unlockItemsStockWithTx(MerchantProductWarehouseStockPO po) {
			boolean  result = false;
			int rt = merchantProductWarehouseStockWriteDAO.unlockItemsStockWithTx(po);
			if(rt ==1){
				result = true;
			}else if(rt ==0){
				throw new BusinessException("商品id = " + po.getSkuId() + "解锁锁定库存存在异常");
			}
			return result;
		}

		@Override
		public int deleteByPara(MerchantProductWarehouseStockPO po) {
			
			return merchantProductWarehouseStockWriteDAO.deleteByPara(po);
		}

		@Override
		public int comeMerchantProductStockWithTx(MerchantProductWarehouseStockPO po) {
			
			return merchantProductWarehouseStockWriteDAO.comeMerchantProductStockWithTx(po);
		}


}
	