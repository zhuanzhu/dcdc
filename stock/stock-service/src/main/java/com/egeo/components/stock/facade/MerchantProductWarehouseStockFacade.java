package com.egeo.components.stock.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.MerchantProductClient;
import com.egeo.components.product.dto.FindMerchantProductOfPageDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.service.read.MerchantProductWarehouseStockReadService;
import com.egeo.components.stock.service.write.MerchantProductWarehouseStockWriteService;
import com.egeo.components.stock.vo.MerchantProductStock;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProductWarehouseStockFacade {
	
	@Resource
	private MerchantProductWarehouseStockReadService merchantProductWarehouseStockReadService;

	@Resource
        private MerchantProductWarehouseStockWriteService merchantProductWarehouseStockWriteService;
	
		@Autowired
        private MerchantProductClient merchantProductReadService;

        public Long saveMerchantProductWarehouseStock(MerchantProductWarehouseStockDTO dto) {
            
            return merchantProductWarehouseStockWriteService.saveMerchantProductWarehouseStockWithTx(dto);
        }

        public Long updateMerchantProductWarehouseStock(MerchantProductWarehouseStockDTO dto) {
            
            return merchantProductWarehouseStockWriteService.updateMerchantProductWarehouseStockWithTx(dto);
        }

        public MerchantProductWarehouseStockDTO findById(MerchantProductWarehouseStockDTO dto) {
            
            return merchantProductWarehouseStockReadService.findById(dto);
        }

        public PageResult<MerchantProductStock> findPageMerchantProductWarehouseStock(
                Pagination page,MerchantProductWarehouseStockDTO dto) {
        	List<MerchantProductStock> merchantProductStocks = new ArrayList<MerchantProductStock>();
        	MerchantProductDTO merchantProductDTO = new MerchantProductDTO();
        	merchantProductDTO.setPlatformId(dto.getPlatformId());
        	PageResult<MerchantProductDTO> findOfPage = merchantProductReadService.findMerchantProductOfPage(new FindMerchantProductOfPageDTO(null, null, null, null, null, null, null, merchantProductDTO, page, null));
        	List<MerchantProductDTO> MerchantProductList = findOfPage.getList();
        	for (MerchantProductDTO merchantProductDTO2 : MerchantProductList) {
        		MerchantProductStock merchantProductStock = new MerchantProductStock();
        		merchantProductStock.setId(merchantProductDTO2.getId());
        		merchantProductStock.setName(merchantProductDTO2.getName());
        		Long stocks = 0L;
				//根据商品id查询库存信息
        		MerchantProductWarehouseStockDTO productWarehouseStockDTO = new MerchantProductWarehouseStockDTO();
        		productWarehouseStockDTO.setStandardProductUnitId(merchantProductDTO2.getStandardProductUnitId());
        		List<MerchantProductWarehouseStockDTO> MerchantProductWarehouseStockList = merchantProductWarehouseStockReadService.findAll(productWarehouseStockDTO);
        		for (MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO : MerchantProductWarehouseStockList) {
        			Long stock = merchantProductWarehouseStockDTO.getRealStockNum() - merchantProductWarehouseStockDTO.getRealFrozenStockNum();
        			stocks = stocks + stock;
				}
        		merchantProductStock.setStock(stocks);
        		merchantProductStocks.add(merchantProductStock);
			}
        	
            PageResult<MerchantProductStock> result = new PageResult<MerchantProductStock>();
            result.setList(merchantProductStocks);
            result.setPageNo(findOfPage.getPageNo());
            result.setPageSize(findOfPage.getPageSize());
            result.setTotalSize(findOfPage.getTotalSize());
            return result;
        }

        public List<MerchantProductWarehouseStockDTO> findAll(MerchantProductWarehouseStockDTO dto) {
            
            return merchantProductWarehouseStockReadService.findAll(dto);
        }
	


}
	