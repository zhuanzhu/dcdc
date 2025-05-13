package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.MerchantProductWarehouseStockReadManage;
import com.egeo.components.stock.dao.read.MerchantProductWarehouseStockReadDAO;
import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service
public class MerchantProductWarehouseStockReadManageImpl implements MerchantProductWarehouseStockReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductWarehouseStockReadDAO merchantProductWarehouseStockReadDAO;
	
        @Override
        public MerchantProductWarehouseStockPO findById(MerchantProductWarehouseStockPO po) {
            
            return merchantProductWarehouseStockReadDAO.findById(po);
        }

        @Override
        public PageResult<MerchantProductWarehouseStockPO> findPage(Pagination page,
                MerchantProductWarehouseStockPO po) {
            int cnt = merchantProductWarehouseStockReadDAO.countOfPage(po);
            List<MerchantProductWarehouseStockPO> list = new ArrayList<MerchantProductWarehouseStockPO>();
            if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
                page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
                list = merchantProductWarehouseStockReadDAO.findOfPage(po, page);
            }
            PageResult<MerchantProductWarehouseStockPO> pageResult = new PageResult<MerchantProductWarehouseStockPO>();
            pageResult.setList(list);
            pageResult.setTotalSize(cnt);
            pageResult.setPageNo(page.getPageNo());
            pageResult.setPageSize(page.getPageSize());
            return pageResult;
        }

        @Override
        public List<MerchantProductWarehouseStockPO> findAll(MerchantProductWarehouseStockPO po) {
            
            return merchantProductWarehouseStockReadDAO.findAll(po,null);
        }

        @Override
        public MerchantProductWarehouseStockPO findByMerchantProductId(MerchantProductWarehouseStockPO po) {
            
            return merchantProductWarehouseStockReadDAO.findByMerchantProductId(po);
        }
        /**
    	 * 根据skuid查询sku库存信息
    	 * @param dto
    	 * @return
    	 */
		@Override
		public MerchantProductWarehouseStockPO findBySkuId(Long skuId) {
			// TODO Auto-generated method stub
			return merchantProductWarehouseStockReadDAO.findBySkuId(skuId);
		}
	
}
	