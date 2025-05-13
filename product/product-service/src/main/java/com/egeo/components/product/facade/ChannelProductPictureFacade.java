package com.egeo.components.product.facade;

import com.egeo.components.product.condition.ChannelProductPicCondition;
import com.egeo.components.product.dto.channel.ChannelProductPictureDTO;
import com.egeo.components.product.po.ChannelProductPicturePO;
import com.egeo.components.product.service.read.ChannelProductPictureReadService;
import com.egeo.components.product.service.write.ChannelProductPictureWriteService;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Component
public class ChannelProductPictureFacade {

    @Resource
    private ChannelProductPictureReadService channelProductPictureReadService;

    @Resource
    private ChannelProductPictureWriteService channelProductPictureWriteService;


    public ChannelProductPictureDTO findChannelProductPictureById(ChannelProductPictureDTO dto){

        return channelProductPictureReadService.findChannelProductPictureById(dto);
    }

    public PageResult<ChannelProductPictureDTO> findChannelProductPictureOfPage(ChannelProductPictureDTO dto, Pagination page){

        return channelProductPictureReadService.findChannelProductPictureOfPage(dto, page);

    }

    public List<ChannelProductPictureDTO> findChannelProductPictureAll(ChannelProductPictureDTO dto){

        return channelProductPictureReadService.findChannelProductPictureAll(dto);

    }
    public Long insertChannelProductPictureWithTx(ChannelProductPictureDTO dto){

        return channelProductPictureWriteService.insertChannelProductPictureWithTx(dto);
    }

    public int updateChannelProductPictureWithTx(ChannelProductPictureDTO dto){

        return channelProductPictureWriteService.updateChannelProductPictureWithTx(dto);
    }

    public int deleteChannelProductPictureWithTx(ChannelProductPictureDTO dto){

        return channelProductPictureWriteService.deleteChannelProductPictureWithTx(dto);

    }

    public int deleteByParaWithTx(ChannelProductPictureDTO dto){

        return channelProductPictureWriteService.deleteByParaWithTx(dto);

    }

    public List<ChannelProductPicturePO> findChannelPicByProductIds(List<String> productIds, String channelCode){
        return channelProductPictureReadService.findChannelPicByProductIds(productIds,channelCode);
    }

    public Map<String,String> findChannelPicByProductIdsToMap(List<String> productIds, String channelCode){
        if(CollectionUtils.isEmpty(productIds)){
            return Maps.newHashMap();
        }
        List<ChannelProductPicturePO> list = findChannelPicByProductIds(productIds,channelCode);
        if(CollectionUtils.isEmpty(list)){
            return Maps.newHashMap();
        }
        //return FHCollectionUtils.listToMap(list,ChannelProductPicturePO::getProductId,ChannelProductPicturePO::getPictureUrl);
        Map<String,String> resultMap = new HashMap<>();
        for (ChannelProductPicturePO po : list) {
            if(resultMap.containsKey(po.getProductId())){
                continue;
            }
            resultMap.put(po.getProductId(),po.getPictureUrl());
        }
        return resultMap;
    }
}
