package com.egeo.components.stock.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.MerchantProductWarehouseStockManage;
import com.egeo.components.stock.converter.MerchantProductWarehouseStockConverter;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.facade.MerchantProductWarehouseStockFacade;
import com.egeo.components.stock.vo.MerchantProductStock;
import com.egeo.components.stock.vo.MerchantProductWarehouseStockVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("merchantProductWarehouseStock")
public class MerchantProductWarehouseStockManageImpl implements MerchantProductWarehouseStockManage{

	
	@Resource(name="merchantProductWarehouseStockFacade")
	private MerchantProductWarehouseStockFacade merchantProductWarehouseStockFacade;

        @Override
        public Long saveMerchantProductWarehouseStock(
                MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
            
            return merchantProductWarehouseStockFacade.saveMerchantProductWarehouseStock(
                    MerchantProductWarehouseStockConverter.toDTO(merchantProductWarehouseStockVO));
        }

        @Override
        public Long updateMerchantProductWarehouseStock(
                MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
            
            return merchantProductWarehouseStockFacade.updateMerchantProductWarehouseStock(
                    MerchantProductWarehouseStockConverter.toDTO(merchantProductWarehouseStockVO));
        }

        @Override
        public MerchantProductWarehouseStockDTO findById(
                MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
            
            
            return merchantProductWarehouseStockFacade.findById(
                    MerchantProductWarehouseStockConverter.toDTO(merchantProductWarehouseStockVO));
        }

        @Override
        public PageResult<MerchantProductStock> findPageMerchantProductWarehouseStock(
                Pagination page, MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
            
            return merchantProductWarehouseStockFacade.findPageMerchantProductWarehouseStock(page,
                    MerchantProductWarehouseStockConverter.toDTO(merchantProductWarehouseStockVO));
        }

        @Override
        public List<MerchantProductWarehouseStockVO> findAll(
                MerchantProductWarehouseStockVO merchantProductWarehouseStockVO) {
            List<MerchantProductWarehouseStockDTO> list = merchantProductWarehouseStockFacade.findAll(
                    MerchantProductWarehouseStockConverter.toDTO(merchantProductWarehouseStockVO));
            return MerchantProductWarehouseStockConverter.toVO(list);
        }
	


}
	