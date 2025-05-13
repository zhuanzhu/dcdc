package com.egeo.components.product.facade;

import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.service.read.ChannelProductBatchReadService;
import com.egeo.components.product.service.write.ChannelProductBatchWriteService;
import com.egeo.components.product.vo.ChannelSupplierProductRequestVO;
import com.egeo.components.product.vo.ChannelSupplierProductResponseVO;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class ChannelProductBatchFacade {

    @Resource
    private ChannelProductBatchReadService channelProductBatchReadService;

    @Resource
    private ChannelProductBatchWriteService channelProductBatchWriteService;


    public ChannelProductBatchDTO findChannelProductBatchById(ChannelProductBatchDTO dto){

        return channelProductBatchReadService.findChannelProductBatchById(dto);
    }

    public PageResult<ChannelProductBatchDTO> findChannelProductBatchOfPage(ChannelProductBatchDTO dto, Pagination page){

        return channelProductBatchReadService.findChannelProductBatchOfPage(dto, page);

    }

    public List<ChannelProductBatchDTO> findChannelProductBatchAll(ChannelProductBatchDTO dto){

        return channelProductBatchReadService.findChannelProductBatchAll(dto);

    }
    public Long insertChannelProductBatchWithTx(ChannelProductBatchDTO dto){

        return channelProductBatchWriteService.insertChannelProductBatchWithTx(dto);
    }

    public int updateChannelProductBatchWithTx(ChannelProductBatchDTO dto){

        return channelProductBatchWriteService.updateChannelProductBatchWithTx(dto);
    }

    public int deleteChannelProductBatchWithTx(ChannelProductBatchDTO dto){

        return channelProductBatchWriteService.deleteChannelProductBatchWithTx(dto);

    }

    public int updateChannelBatchSkuState(List<String> skuIdList, String channelCode){
        return channelProductBatchWriteService.updateChannelBatchSkuState(skuIdList,channelCode);
    }

    public ChannelProductBatchDTO getPriceMinOne(ChannelProductBatchDTO dto){
        List<ChannelProductBatchDTO> list = this.findChannelProductBatchAll(dto);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        Optional<ChannelProductBatchDTO> minSalePriceCondition =  FHCollectionUtils.findMinT(list,ChannelProductBatchDTO::getPriceSettleMent);
        return minSalePriceCondition.get();
    }

    public PageResult<ChannelSupplierProductResponseVO> findChannelProductOfPage(ChannelSupplierProductRequestVO vo, Pagination page){
        return channelProductBatchReadService.findChannelProductOfPage(vo, page);
    }
}
