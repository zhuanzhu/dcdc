package com.egeo.components.product.strategy.impl.receive;

import com.egeo.components.product.bean.ReceiveProductBean;
import com.egeo.components.product.common.ProductChannelCodeEnum;
import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.dto.world.WorldBuyBaseNoticeDTO;
import com.egeo.components.product.dto.world.WorldGoodsChangeNoticeDTO;
import com.egeo.components.product.dto.world.WorldGoodsSkuChangeNoticeDTO;
import com.egeo.components.product.dto.world.WorldNoticeResponseDTO;
import com.egeo.components.product.facade.ChannelProductBatchFacade;
import com.egeo.components.product.facade.ChannelProductFacade;
import com.egeo.components.product.facade.ChannelProductSkuFacade;
import com.egeo.components.product.strategy.ProductUpOrDownStrategy;
import com.egeo.components.utils.FHCollectionUtils;
import com.egeo.components.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description 全球购产品上下线服务策略
 * @Author lsl
 * @Version V1.0
 **/
@Service("worldUpOrDownStrategyImpl")
public class WorldUpOrDownStrategyImpl implements ProductUpOrDownStrategy {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChannelProductFacade channelProductFacade;

    @Autowired
    private ChannelProductSkuFacade channelProductSkuFacade;

    @Autowired
    private ChannelProductBatchFacade channelProductBatchFacade;

    @Override
    public String getProductCode() {
        return ProductChannelCodeEnum.WORLD_BUY.getCode();
    }

    @Override
    public WorldNoticeResponseDTO receiveProductState(ReceiveProductBean bean) {
        String channelCode = bean.getChannelCode();
        try {
            logger.info("全球购产品上下线通知报文{}",JsonUtils.objectToJson(bean));
            if(bean.getParamObject() ==null){
                return WorldNoticeResponseDTO.success();
            }
            WorldBuyBaseNoticeDTO dto =(WorldBuyBaseNoticeDTO)bean.getParamObject();
            WorldGoodsSkuChangeNoticeDTO worldGoodsSkuChangeNoticeDTO = JsonUtils.jsonToPojo(JsonUtils.objectToJson(dto.getData()), WorldGoodsSkuChangeNoticeDTO.class);
            /*List<ChannelProductSkuDTO>  list = channelProductSkuFacade.findChannelProductSkuBySkuIds(worldGoodsSkuChangeNoticeDTO.getSkuList(),channelCode);
            boolean isSkuCode = false;
            if(CollectionUtils.isEmpty(list)){
                list = channelProductSkuFacade.findChannelProductSkuBySkuCodes(worldGoodsSkuChangeNoticeDTO.getSkuList(),channelCode);
                isSkuCode = true;
            }*/
            List<ChannelProductSkuDTO>  list = channelProductSkuFacade.findChannelProductSkuBySkuCodes(worldGoodsSkuChangeNoticeDTO.getSkuList(),channelCode);
            if(CollectionUtils.isEmpty(list)){
                return WorldNoticeResponseDTO.success();
            }
            List<String> skuList = FHCollectionUtils.listToStrList(list,ChannelProductSkuDTO::getExternalSkuId);
            if(CollectionUtils.isEmpty(skuList)){
                return WorldNoticeResponseDTO.fail("接收失败,商品未找到");
            }
            channelProductSkuFacade.updateChannelProductSkuWithTx(skuList,channelCode);
            channelProductBatchFacade.updateChannelBatchSkuState(skuList,channelCode);
            return WorldNoticeResponseDTO.success();
        }catch (Exception e){
            logger.error("商品下架消息通知接口->渠道{}通知发生异常{}",channelCode,e);
            return WorldNoticeResponseDTO.fail("接收失败");
        }

    }

    @Override
    public WorldNoticeResponseDTO receiveAllProductState(ReceiveProductBean bean) {
        logger.info("全球购全部产品下架通知报文{}",JsonUtils.objectToJson(bean));
        channelProductSkuFacade.updateChannelProductSkuWithTx(null,getProductCode());
        channelProductBatchFacade.updateChannelBatchSkuState(null,getProductCode());
        return WorldNoticeResponseDTO.success();
    }
}
