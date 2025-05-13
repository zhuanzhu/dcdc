package com.egeo.components.stock.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.MerchantProductWarehouseStockReadService;
import com.egeo.components.stock.manage.read.CommodityProductUnitWarehouseStockReadManage;
import com.egeo.components.stock.manage.read.MerchantProductWarehouseStockReadManage;
import com.egeo.components.stock.converter.MerchantProductWarehouseStockConverter;
import com.egeo.components.stock.dto.MerchantProductWarehouseStockDTO;
import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.components.stock.po.MerchantProductWarehouseStockPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("merchantProductWarehouseStockReadService")
public class MerchantProductWarehouseStockReadServiceImpl  implements MerchantProductWarehouseStockReadService {
	@Autowired
	private MerchantProductWarehouseStockReadManage merchantProductWarehouseStockReadManage;
	
	@Autowired
	private CommodityProductUnitWarehouseStockReadManage commodityProductUnitWarehouseStockReadManage;

        @Override
        public MerchantProductWarehouseStockDTO findById(MerchantProductWarehouseStockDTO dto) {
            MerchantProductWarehouseStockPO po = merchantProductWarehouseStockReadManage.findById(MerchantProductWarehouseStockConverter.toPO(dto));
            return MerchantProductWarehouseStockConverter.toDTO(po);
        }

        @Override
        public PageResult<MerchantProductWarehouseStockDTO> findPageMerchantProductWarehouseStock(
                Pagination page,MerchantProductWarehouseStockDTO dto) {
            MerchantProductWarehouseStockPO po = MerchantProductWarehouseStockConverter.toPO(dto);
            PageResult<MerchantProductWarehouseStockPO> pageResult = merchantProductWarehouseStockReadManage.findPage(page, po);
            
            List<MerchantProductWarehouseStockDTO> list = new ArrayList<MerchantProductWarehouseStockDTO>();
            for (MerchantProductWarehouseStockPO tmp : pageResult.getList()) {
                MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO = MerchantProductWarehouseStockConverter.toDTO(tmp);
                    list.add(merchantProductWarehouseStockDTO);
            }
            PageResult<MerchantProductWarehouseStockDTO> result = new PageResult<MerchantProductWarehouseStockDTO>();
            result.setList(list);
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
            return result;
        }

        @Override
        public List<MerchantProductWarehouseStockDTO> findAll(MerchantProductWarehouseStockDTO dto) {
            List<MerchantProductWarehouseStockPO> list = merchantProductWarehouseStockReadManage.findAll(MerchantProductWarehouseStockConverter.toPO(dto));
            return MerchantProductWarehouseStockConverter.toDTO(list);
        }

        @Override
        public MerchantProductWarehouseStockDTO findByMerchantProductId(
                MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO) {
            MerchantProductWarehouseStockPO merchantProductWarehouseStockPO = merchantProductWarehouseStockReadManage.findByMerchantProductId(
                    MerchantProductWarehouseStockConverter.toPO(merchantProductWarehouseStockDTO));
            return MerchantProductWarehouseStockConverter.toDTO(merchantProductWarehouseStockPO);
        }
        /**
    	 * 根据skuid查询sku库存信息
    	 * @param dto
    	 * @return
    	 */
		@Override
		public MerchantProductWarehouseStockDTO findBySkuId(Long skuId,List<Long> commodityProductUnitIds) {
			MerchantProductWarehouseStockPO merchantProductWarehouseStockPO = merchantProductWarehouseStockReadManage.findBySkuId(skuId);
			MerchantProductWarehouseStockDTO merchantProductWarehouseStockDTO = MerchantProductWarehouseStockConverter.toDTO(merchantProductWarehouseStockPO);
			List<CommodityProductUnitWarehouseStockPO> commodityProductUnitWarehouseStockList = null;
			if(EmptyUtil.isNotEmpty(commodityProductUnitIds)){
				//根据skuid查询pu库存
				commodityProductUnitWarehouseStockList = commodityProductUnitWarehouseStockReadManage.findByPUId(commodityProductUnitIds);
			}
			
			//拼接所有pu真实库存之和
			Long realStockNums = 0L;
			if(EmptyUtil.isNotEmpty(commodityProductUnitWarehouseStockList)){
				for (CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO : commodityProductUnitWarehouseStockList) {
					realStockNums = realStockNums + commodityProductUnitWarehouseStockPO.getRealStockNum();
				}
			}
			
			merchantProductWarehouseStockDTO.setRealStockNums(realStockNums);
			return merchantProductWarehouseStockDTO;
		}
}
	